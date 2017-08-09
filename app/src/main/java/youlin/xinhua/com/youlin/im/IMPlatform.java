package youlin.xinhua.com.youlin.im;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMError;
import com.hyphenate.EMGroupChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMucSharedFile;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import youlin.xinhua.com.youlin.BuildConfig;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.activity.MainActivity;
import youlin.xinhua.com.youlin.bean.UserInfo;
import youlin.xinhua.com.youlin.constant.EaseConstant;
import youlin.xinhua.com.youlin.utils.LogUtils;
import youlin.xinhua.com.youlin.utils.SPUtils;
import youlin.xinhua.com.youlin.utils.ToastUtils;

import static youlin.xinhua.com.youlin.utils.ToastUtils.showToast;

/**
 * <pre>
 *     desc   : TO-DO
 *     author : 罗顺翔
 *     time   : 2017-07-04 11:10
 *     version: 1.0
 * </pre>
 */

public class IMPlatform {

  private static IMPlatform INSTANCE = null;

  public static IMPlatform get() {
    if (INSTANCE == null) {
      synchronized (IMPlatform.class) {
        if (INSTANCE == null) {
          INSTANCE = new IMPlatform();
        }
      }
    }
    return INSTANCE;
  }

  private Context mContext;

  /**
   * the notifier
   */
  private EaseNotifier notifier = null;

  private boolean isInit = false;

  private ConcurrentHashMap<String, UserInfo> contactList;

  private EMConnectionListener connectionListener;

  private LocalBroadcastManager broadcastManager;
  //private CallReceiver          mCallReceiver;

  /**
   * EMEventListener
   */
  protected EMMessageListener messageListener = null;

  private boolean isGroupAndContactListenerRegisted = false;

  private boolean isSyncingGroupsWithServer    = false;
  private boolean isSyncingContactsWithServer  = false;
  private boolean isSyncingBlackListWithServer = false;
  private boolean isGroupsSyncedWithServer     = false;
  private boolean isContactsSyncedWithServer   = false;
  private boolean isBlackListSyncedWithServer  = false;

  /**
   * 是否登录
   */
  public static boolean isLogin() {
    return EMClient.getInstance().isLoggedInBefore();
  }

  /**
   * Todo 语音聊天设置
   *
   * 初始化
   */
  public void init(Context context) {
    if (isInit) {
      return;
    }
    isInit = true;
    mContext = context;
    EMOptions emOptions = initChatOptions();
    EMClient.getInstance().init(context, emOptions);
    //debug mode, you'd better set it to false, if you want release your App officially.
    EMClient.getInstance().setDebugMode(BuildConfig.DEBUG);
    initNotifier();
    registerMessageListener();
    setCallOptions();
    // Offline call push, 通话离线推送
    EMClient.getInstance().callManager().getCallOptions().setIsSendPushIfOffline(true);
    setGlobalListeners();
    broadcastManager = LocalBroadcastManager.getInstance(mContext);
  }

  /**
   * set global listener
   */
  private void setGlobalListeners() {

    isGroupsSyncedWithServer =
        (boolean) SPUtils.get(EaseConstant.SHARED_KEY_SETTING_GROUPS_SYNCED, false);
    isContactsSyncedWithServer =
        (boolean) SPUtils.get(EaseConstant.SHARED_KEY_SETTING_CONTACT_SYNCED, false);
    isBlackListSyncedWithServer =
        (boolean) SPUtils.get(EaseConstant.SHARED_KEY_SETTING_BALCKLIST_SYNCED, false);

    LogUtils.i(isGroupsSyncedWithServer
        + " , "
        + isContactsSyncedWithServer
        + " , "
        + isBlackListSyncedWithServer);

    // create the global connection listener
    connectionListener = new EMConnectionListener() {
      @Override public void onConnected() {
        if (isGroupsSyncedWithServer && isContactsSyncedWithServer) {
          LogUtils.i("group and contact already synced with server");
        } else {
          if (!isGroupsSyncedWithServer) {
            asyncFetchGroupsFromServer();
          }

          if (!isContactsSyncedWithServer) {
            asyncFetchContactsFromServer();
          }

          if (!isBlackListSyncedWithServer) {
            asyncFetchBlackListFromServer();
          }
        }
      }

      @Override public void onDisconnected(int error) {
        LogUtils.i("global listener", "onDisconnect" + error);
        if (error == EMError.USER_REMOVED) {
          onUserException(EaseConstant.ACCOUNT_REMOVED);
        } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
          onUserException(EaseConstant.ACCOUNT_CONFLICT);
        } else if (error == EMError.SERVER_SERVICE_RESTRICTED) {
          onUserException(EaseConstant.ACCOUNT_FORBIDDEN);
        }
      }
    };

    //IntentFilter callFilter =
    //    new IntentFilter(EMClient.getInstance().callManager().getIncomingCallBroadcastAction());
    //if (mCallReceiver == null) {
    //  mCallReceiver = new CallReceiver();
    //}

    //register incoming call receiver
    //mContext.registerReceiver(mCallReceiver, callFilter);
    //register connection listener
    EMClient.getInstance().addConnectionListener(connectionListener);
    //register group and contact event listener
    registerGroupAndContactListener();
    //register message event listener
    registerMessageListener2();
  }

  /**
   * Global listener
   * If this event already handled by an activity, you don't need handle it again
   * activityList.size() <= 0 means all activities already in background or not in Activity Stack
   */
  private void registerMessageListener2() {
    messageListener = new EMMessageListener() {

      /**
       * 接受消息接口，在接受到文本消息，图片，视频，语音，地理位置，文件这些消息体的时候，会通过此接口通知用户。
       * @param messages 消息
       */
      @Override public void onMessageReceived(List<EMMessage> messages) {
        for (EMMessage message : messages) {
          LogUtils.d("onMessageReceived id : " + message.getMsgId());
          // in background, do not refresh UI, notify it in notification bar
          //if (!easeUI.hasForegroundActivies()) {
          getNotifier().onNewMsg(message);
          //}
        }
      }

      /**
       * 区别于{onMessageReceived(List<EMMessage> messages)}, 这个接口只包含命令的消息体，包含命令的消息体通常不对用户展示。
       * @param messages 只包含命令的消息体
       */
      @Override public void onCmdMessageReceived(List<EMMessage> messages) {
        for (EMMessage message : messages) {
          LogUtils.d("receive command message");
          //get message body
          EMCmdMessageBody cmdMsgBody = (EMCmdMessageBody) message.getBody();
          final String action = cmdMsgBody.action();//获取自定义action
          //red packet code : 处理红包回执透传消息
          //if (!easeUI.hasForegroundActivies()) {
          //  if (action.equals(RPConstant.REFRESH_GROUP_RED_PACKET_ACTION)) {
          //    RedPacketUtil.receiveRedPacketAckMessage(message);
          //    broadcastManager.sendBroadcast(
          //        new Intent(RPConstant.REFRESH_GROUP_RED_PACKET_ACTION));
          //  }
          //}

          //if (action.equals("__Call_ReqP2P_ConferencePattern")) {
          //  String title = message.getStringAttribute("em_apns_ext", "conference call");
          //  Toast.makeText(appContext, title, Toast.LENGTH_LONG).show();
          //}
          //end of red packet code
          //获取扩展属性 此处省略
          //maybe you need get extension of your message
          //message.getStringAttribute("");
          LogUtils.d(String.format("Command：action:%s,message:%s", action, message.toString()));
        }
      }

      /**
       * 接受到消息体的已读回执, 消息的接收方已经阅读此消息。
       * @param messages 消息
       */
      @Override public void onMessageRead(List<EMMessage> messages) {
      }

      /**
       * 收到消息体的发送回执，消息体已经成功发送到对方设备。
       * @param message 消息
       */
      @Override public void onMessageDelivered(List<EMMessage> message) {
      }

      @Override public void onMessageRecalled(List<EMMessage> list) {

      }

      /**
       * 接受消息发生改变的通知，包括消息ID的改变。消息是改变后的消息。
       * @param message 发生改变的消息
       * @param change
       */
      @Override public void onMessageChanged(EMMessage message, Object change) {
        LogUtils.d("change:");
        LogUtils.d("change:" + change);
      }
    };

    EMClient.getInstance().chatManager().addMessageListener(messageListener);
  }

  private void registerGroupAndContactListener() {
    if (!isGroupAndContactListenerRegisted) {
      EMClient.getInstance().groupManager().addGroupChangeListener(new MyGroupChangeListener());
      EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
      isGroupAndContactListenerRegisted = true;
    }
  }

  private void asyncFetchBlackListFromServer() {

    if (isSyncingBlackListWithServer) {
      return;
    }

    isSyncingBlackListWithServer = true;

    new Thread() {
      @Override public void run() {
        try {
          List<String> usernames = EMClient.getInstance().contactManager().getBlackListFromServer();

          // in case that logout already before server returns, we should return immediately
          if (!isLogin()) {
            isBlackListSyncedWithServer = false;
            isSyncingBlackListWithServer = false;
            return;
          }
          SPUtils.put(EaseConstant.SHARED_KEY_SETTING_BALCKLIST_SYNCED, true);
          isBlackListSyncedWithServer = true;
          isSyncingBlackListWithServer = false;
        } catch (HyphenateException e) {
          SPUtils.put(EaseConstant.SHARED_KEY_SETTING_BALCKLIST_SYNCED, false);
          isBlackListSyncedWithServer = false;
          isSyncingBlackListWithServer = true;
          e.printStackTrace();
          LogUtils.e(e.toString());
        }
      }
    }.start();
  }

  private void asyncFetchContactsFromServer() {
    LogUtils.i(" isSyncingContactsWithServer :" + isSyncingContactsWithServer);
    if (isSyncingContactsWithServer) {
      return;
    }

    isSyncingContactsWithServer = true;

    new Thread() {
      @Override public void run() {
        List<String> usernames = null;
        try {
          usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
          // in case that logout already before server returns, we should return immediately
          LogUtils.i("isLogin : " + isLogin());
          if (!isLogin()) {
            isContactsSyncedWithServer = false;
            isSyncingContactsWithServer = false;
            return;
          }
          LogUtils.i(usernames.toString());
          Map<String, UserInfo> userlist = new HashMap<>();
          for (String username : usernames) {
            UserInfo user = new UserInfo(username);
            //EaseCommonUtils.setUserInitialLetter(user);
            userlist.put(username, user);
          }
          // save the contact list to cache
          getContactList().clear();
          getContactList().putAll(userlist);

          List<UserInfo> users = new ArrayList<>(userlist.values());
          // 插入或更新数据库
          //UserDao.get().saveContactList(users);

          SPUtils.put(EaseConstant.SHARED_KEY_SETTING_CONTACT_SYNCED, true);
          LogUtils.d("set contact syn status to true");
          isContactsSyncedWithServer = true;
          isSyncingContactsWithServer = false;

          // Todo 获取服务器端用户信息

        } catch (HyphenateException e) {
          e.printStackTrace();
          LogUtils.e(e.toString());
          SPUtils.put(EaseConstant.SHARED_KEY_SETTING_CONTACT_SYNCED, true);
          isContactsSyncedWithServer = false;
          isSyncingContactsWithServer = false;
        }
      }
    }.start();
  }

  public Map<String, UserInfo> getContactList() {
    if (isLogin() && contactList == null) {
      //contactList = UserDao.get().getContactList();
    }
    if (contactList == null) {
      return new ConcurrentHashMap<>();
    }

    return contactList;
  }

  private synchronized void asyncFetchGroupsFromServer() {
    if (isSyncingGroupsWithServer) {
      return;
    }

    isSyncingGroupsWithServer = true;

    new Thread() {
      @Override public void run() {
        try {
          EMClient.getInstance().groupManager().getJoinedGroupsFromServer();
          if (!isLogin()) {
            isGroupsSyncedWithServer = false;
            isSyncingGroupsWithServer = false;
            return;
          }
          SPUtils.put(EaseConstant.SHARED_KEY_SETTING_GROUPS_SYNCED, true);
          isGroupsSyncedWithServer = true;
          isSyncingGroupsWithServer = false;
        } catch (HyphenateException e) {
          e.printStackTrace();
          LogUtils.e(e.toString());
          SPUtils.put(EaseConstant.SHARED_KEY_SETTING_GROUPS_SYNCED, false);
          isGroupsSyncedWithServer = false;
          isSyncingGroupsWithServer = false;
        }
      }
    }.start();
  }

  /**
   * user met some exception: conflict, removed or forbidden
   */
  protected void onUserException(String exception) {
    LogUtils.e("onUserException: " + exception);
    Intent intent = new Intent(mContext, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra(exception, true);
    mContext.startActivity(intent);
  }

  private void initNotifier() {
    notifier = new EaseNotifier();
    notifier.init(mContext);
    notifier.setNotificationInfoProvider(new EaseNotifier.EaseNotificationInfoProvider() {
      @Override public String getTitle(EMMessage message) {
        //you can update title here
        return null;
      }

      @Override public int getSmallIcon(EMMessage message) {
        //you can update icon here
        return 0;
      }

      @Override public String getDisplayedText(EMMessage message) {
        // be used on notification bar, different text according the message type.
        //String ticker = EaseCommonUtils.getMessageDigest(message, mContext);
        //if (message.getType() == EMMessage.Type.TXT) {
        //  ticker = ticker.replaceAll("\\[.{2,3}\\]", "[表情]");
        //}
        //UserInfo user = getUserInfo(message.getFrom());
        //if (user != null) {
        //  if (EaseAtMessageHelper.get().isAtMeMsg(message)) {
        //    return String.format(mContext.getString(R.string.at_your_in_group), user.getNickname());
        //  }
        //  return user.getNickname() + ": " + ticker;
        //} else {
        //  if (EaseAtMessageHelper.get().isAtMeMsg(message)) {
        //    return String.format(mContext.getString(R.string.at_your_in_group), message.getFrom());
        //  }
        //  return message.getFrom() + ": " + ticker;
        //}
        return "";
      }

      @Override public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
        // here you can customize the text.
        // return fromUsersNum + "contacts send " + messageNum + "messages to you";
        return null;
      }

      @Override public Intent getLaunchIntent(EMMessage message) {
        // you can set what activity you want display when user click the notification
        //Intent intent = new Intent(mContext, ChatActivity.class);
        //// Todo 视频和语音聊天跳转
        //// open calling activity if there is call
        ////if (isVideoCalling) {
        ////  intent = new Intent(appContext, VideoCallActivity.class);
        ////} else if (isVoiceCalling) {
        ////  intent = new Intent(appContext, VoiceCallActivity.class);
        ////} else {
        //EMMessage.ChatType chatType = message.getChatType();
        //if (chatType == EMMessage.ChatType.Chat) { // single chat message
        //  intent.putExtra(ChatConsts.USER_ID, message.getFrom());
        //  intent.putExtra(ChatConsts.CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        //} else { // group chat message
        //  // message.getTo() is the group id
        //  intent.putExtra(ChatConsts.USER_ID, message.getTo());
        //  if (chatType == EMMessage.ChatType.GroupChat) {
        //    intent.putExtra(ChatConsts.CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
        //  } else {
        //    intent.putExtra(ChatConsts.CHAT_TYPE, EaseConstant.CHATTYPE_CHATROOM);
        //  }
        //}
        //}
        //return intent;
        return null;
      }
    });
  }

  private UserInfo getUserInfo(String from) {
    // To get instance of EaseUser, here we get it from the user list in memory
    // You'd better cache it if you get it from your server
    UserInfo user = null;
    if (from.equals(EMClient.getInstance().getCurrentUser())) {
      //return UserInfoCache.getUserInfo();
    }
    // 从缓存中获取联系人信息
    user = getContactList().get(from);
    // if user is not in your contacts, set inital letter for him/her
    //if (user == null) {
    //  user = new UserInfo(from);
    //  EaseCommonUtils.setUserInitialLetter(user);
    //}
    return user;
  }

  private EMOptions initChatOptions() {
    //Log.d(TAG, "init HuanXin Options");

    EMOptions options = new EMOptions();
    // set if accept the invitation automatically, 自动接受好友邀请
    options.setAcceptInvitationAlways(false);
    // set if you need read ack, 已读回执
    options.setRequireAck(false);
    // set if you need delivery ack
    options.setRequireDeliveryAck(false);

    // Todo 设置华为和小米推送
    //you need apply & set your own id if you want to use Mi push notification
    //options.setMipushConfig("2882303761517426801", "5381742660801");
    //you need apply & set your own id if you want to use Huawei push notification
    //options.setHuaweiPushAppId("10492024");

    // 设置是否允许聊天室owner离开并删除会话记录，意味着owner再不会受到任何消息
    options.allowChatroomOwnerLeave(true);
    // 设置退出(主动和被动退出)群组时是否删除聊天消息
    options.setDeleteMessagesAsExitGroup(true);
    // 设置是否自动接受加群邀请
    options.setAutoAcceptGroupInvitation(false);
    // 设置自动登录
    options.setAutoLogin(true);

    return options;
  }

  private void registerMessageListener() {
    EMClient.getInstance().chatManager().addMessageListener(new EMMessageListener() {

      @Override public void onMessageReceived(List<EMMessage> messages) {
        //EaseAtMessageHelper.get().parseMessages(messages);
      }

      @Override public void onMessageRead(List<EMMessage> messages) {

      }

      @Override public void onMessageDelivered(List<EMMessage> messages) {
      }

      @Override public void onMessageRecalled(List<EMMessage> list) {

      }

      @Override public void onMessageChanged(EMMessage message, Object change) {

      }

      @Override public void onCmdMessageReceived(List<EMMessage> messages) {

      }
    });
  }

  private void setCallOptions() {
    // enabled fixed sample rate
    //boolean enableFixSampleRate = PreferenceManager.getInstance().isCallFixedVideoResolution();
    EMClient.getInstance().callManager().getCallOptions().enableFixedVideoResolution(true);

    // Offline call push
    EMClient.getInstance().callManager().getCallOptions().setIsSendPushIfOffline(true);
  }

  /**
   * 获取通知栏消息管理器
   */
  public EaseNotifier getNotifier() {
    return notifier;
  }

  /**
   * group change listener
   */
  class MyGroupChangeListener implements EMGroupChangeListener {

    /**
     * //接收到群组加入邀请
     *
     * @param groupId 群组id
     * @param groupName 群组名字
     * @param inviter 邀请人
     * @param reason 理由
     */
    @Override public void onInvitationReceived(String groupId, String groupName, String inviter,
        String reason) {

      ToastUtils.showToast("接收到群组加入邀请 , groupId : "
          + groupId
          + " , groupName : "
          + groupName
          + " , inviter : "
          + inviter
          + " , reason : "
          + reason);

      LogUtils.i("接收到群组加入邀请 , groupId : "
          + groupId
          + " , groupName : "
          + groupName
          + " , inviter : "
          + inviter
          + " , reason : "
          + reason);

      EMMessage emMessage = EMMessage.createReceiveMessage(EMMessage.Type.TXT);

      emMessage.setAttribute(EaseConstant.MESSAGE_ATTR_GROUP_INVITE, true);
      emMessage.setAttribute(EaseConstant.MESSAGE_ATTR_VALUE_GROUP_ID, groupId);
      emMessage.setAttribute(EaseConstant.MESSAGE_ATTR_VALUE_GROUP_NAME, groupName);
      emMessage.setAttribute(EaseConstant.MESSAGE_ATTR_VALUE_GROUP_REASON_AVATAR, reason);

      emMessage.setMsgId(UUID.randomUUID().toString());
      emMessage.setChatType(EMMessage.ChatType.Chat);
      emMessage.setFrom(inviter);
      emMessage.setTo(EMClient.getInstance().getCurrentUser());
      emMessage.addBody(new EMTextMessageBody(reason));
      emMessage.setStatus(EMMessage.Status.SUCCESS);

      // save accept message
      EMClient.getInstance().chatManager().saveMessage(emMessage);

      // notify the accept message
      getNotifier().vibrateAndPlayTone(emMessage);
      broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));

      //InviteMsgDao.get().deleteMessage(groupId);
      //
      //// user invite you to join group
      //InviteMessage msg = new InviteMessage();
      //msg.setFrom(groupId);
      //msg.setTime(System.currentTimeMillis());
      //msg.setGroupId(groupId);
      //msg.setGroupName(groupName);
      //msg.setReason(reason);
      //msg.setGroupInviter(inviter);
      //LogUtils.d("receive invitation to join the group：" + groupName);
      //msg.setStatus(InviteMessageStatus.GROUPINVITATION);
      //notifyNewInviteMessage(msg);
      //broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
    }

    /**
     * 群组邀请被同意
     *
     * @param groupId 群组id
     * @param invitee 被邀请者
     */
    @Override public void onInvitationAccepted(String groupId, String invitee, String reason) {

      ToastUtils.showToast(
          "群组邀请被同意 , groupId :" + groupId + " , invitee : " + invitee + " , reason : " + reason);

      //InviteMsgDao.get().deleteMessage(groupId);
      //
      ////user accept your invitation
      //boolean hasGroup = false;
      //EMGroup _group = null;
      //for (EMGroup group : EMClient.getInstance().groupManager().getAllGroups()) {
      //  if (group.getGroupId().equals(groupId)) {
      //    hasGroup = true;
      //    _group = group;
      //    break;
      //  }
      //}
      //if (!hasGroup) return;
      //
      //InviteMessage msg = new InviteMessage();
      //msg.setFrom(groupId);
      //msg.setTime(System.currentTimeMillis());
      //msg.setGroupId(groupId);
      //msg.setGroupName(_group == null ? groupId : _group.getGroupName());
      //msg.setReason(reason);
      //msg.setGroupInviter(invitee);
      //LogUtils.d(
      //    invitee + "Accept to join the group：" + _group == null ? groupId : _group.getGroupName());
      //msg.setStatus(InviteMessageStatus.GROUPINVITATION_ACCEPTED);
      //notifyNewInviteMessage(msg);
      //broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
    }

    /**
     * 群聊邀请拒绝
     *
     * @param groupId 群组id
     * @param invitee 被邀请者
     * @param reason 理由
     */
    @Override public void onInvitationDeclined(String groupId, String invitee, String reason) {

      ToastUtils.showToast(
          "群聊邀请拒绝 , groupId :" + groupId + " , invitee : " + invitee + " , reason : " + reason);

      //InviteMsgDao.get().deleteMessage(groupId);
      //
      ////user declined your invitation
      //EMGroup group = null;
      //for (EMGroup _group : EMClient.getInstance().groupManager().getAllGroups()) {
      //  if (_group.getGroupId().equals(groupId)) {
      //    group = _group;
      //    break;
      //  }
      //}
      //if (group == null) return;
      //
      //InviteMessage msg = new InviteMessage();
      //msg.setFrom(groupId);
      //msg.setTime(System.currentTimeMillis());
      //msg.setGroupId(groupId);
      //msg.setGroupName(group.getGroupName());
      //msg.setReason(reason);
      //msg.setGroupInviter(invitee);
      //LogUtils.d(invitee + "Declined to join the group：" + group.getGroupName());
      //msg.setStatus(InviteMessageStatus.GROUPINVITATION_DECLINED);
      //notifyNewInviteMessage(msg);
      //broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
    }

    /**
     * 当前登录用户被管理员移除出群组
     *
     * @param groupId 群组的ID
     * @param groupName 群组的名称
     */
    @Override public void onUserRemoved(String groupId, String groupName) {
      //user is removed from group
      broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
    }

    /**
     * 群组被解散。 sdk 会先删除本地的这个群组，之后通过此回调通知应用，此群组被删除了
     *
     * @param groupId 群组的ID
     * @param groupName 群组的名称
     */
    @Override public void onGroupDestroyed(String groupId, String groupName) {

      // group is dismissed,
      broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
    }

    /**
     * 用户申请加入群
     *
     * @param groupId 群组id
     * @param groupName 群名字
     */
    @Override public void onRequestToJoinReceived(String groupId, String groupName, String applyer,
        String reason) {

      // user apply to join group
      //InviteMessage msg = new InviteMessage();
      //msg.setFrom(applyer);
      //msg.setTime(System.currentTimeMillis());
      //msg.setGroupId(groupId);
      //msg.setGroupName(groupName);
      //msg.setReason(reason);
      //LogUtils.d(applyer + " Apply to join group：" + groupName);
      //msg.setStatus(InviteMessageStatus.BEAPPLYED);
      //notifyNewInviteMessage(msg);
      //broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
    }

    /**
     * 加群申请被同意
     *
     * @param groupId 群组id
     * @param groupName 群名字
     * @param accepter 同意的人
     */
    @Override public void onRequestToJoinAccepted(String groupId, String groupName,
        String accepter) {

      showToast("加群申请被同意");

      String st4 = mContext.getString(R.string.Agreed_to_your_group_chat_application);
      // your application was accepted
      EMMessage msg = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
      msg.setChatType(EMMessage.ChatType.GroupChat);
      msg.setFrom(accepter);
      msg.setTo(groupId);
      msg.setMsgId(UUID.randomUUID().toString());
      msg.addBody(new EMTextMessageBody(accepter + " " + st4));
      msg.setStatus(EMMessage.Status.SUCCESS);
      // save accept message
      EMClient.getInstance().chatManager().saveMessage(msg);
      // notify the accept message
      //getNotifier().vibrateAndPlayTone(msg);
      //broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
    }

    /**
     * 加群申请被拒绝
     *
     * @param groupId 群组id
     * @param groupName 群名字
     * @param decliner 拒绝的人
     * @param reason 理由
     */
    @Override public void onRequestToJoinDeclined(String groupId, String groupName, String decliner,
        String reason) {
      // your application was declined, we do nothing here in demo
      showToast("加群申请被拒绝");
    }

    /**
     * 接收邀请时自动加入到群组的通知
     *
     * @param groupId 群组id
     */
    @Override public void onAutoAcceptInvitationFromGroup(String groupId, String inviter,
        String inviteMessage) {
      // got an invitation
      String st3 = mContext.getString(R.string.Invite_you_to_join_a_group_chat);
      EMMessage msg = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
      msg.setChatType(EMMessage.ChatType.GroupChat);
      msg.setFrom(inviter);
      msg.setTo(groupId);
      msg.setMsgId(UUID.randomUUID().toString());
      msg.addBody(new EMTextMessageBody(inviter + " " + st3));
      msg.setStatus(EMMessage.Status.SUCCESS);
      // save invitation as messages
      EMClient.getInstance().chatManager().saveMessage(msg);
      // notify invitation message
      getNotifier().vibrateAndPlayTone(msg);
      LogUtils.d("onAutoAcceptInvitationFromGroup groupId:" + groupId);
      broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
    }

    // ============================= group_reform new add api begin

    /**
     * 成员禁言的通知
     */
    @Override public void onMuteListAdded(String groupId, final List<String> mutes,
        final long muteExpire) {
      StringBuilder sb = new StringBuilder();
      for (String member : mutes) {
        sb.append(member).append(",");
      }
      showToast("成员禁言的通知 : " + sb.toString());
    }

    /**
     * 成员从禁言列表里移除通知
     */
    @Override public void onMuteListRemoved(String groupId, final List<String> mutes) {
      StringBuilder sb = new StringBuilder();
      for (String member : mutes) {
        sb.append(member).append(",");
      }
      showToast("成员从禁言列表里移除通知 : " + sb.toString());
    }

    /**
     * 增加管理员的通知
     */
    @Override public void onAdminAdded(String groupId, String administrator) {
      showToast("增加管理员的通知 : " + administrator);
    }

    /**
     * 管理员移除的通知
     */
    @Override public void onAdminRemoved(String groupId, String administrator) {
      showToast("管理员移除的通知 : " + administrator);
    }

    /**
     * 群所有者变动通知
     */
    @Override public void onOwnerChanged(String groupId, String newOwner, String oldOwner) {
      showToast("群所有者变动通知 :" + newOwner + " old:" + oldOwner);
    }

    /**
     * 群组加入新成员通知
     *
     * @param groupId 群组id
     * @param member 成员
     */
    @Override public void onMemberJoined(String groupId, String member) {
      LogUtils.d("onMemberJoined");
      showToast("群组加入新成员通知 : " + member);

      String body = mContext.getResources().getString(R.string.row_group_member_joined, member);

      // EventMessage
      EMMessage emMessage = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
      emMessage.setAttribute(EaseConstant.MESSAGE_ATTR_EVENT_MESSAGE, true);
      emMessage.setChatType(EMMessage.ChatType.GroupChat);
      emMessage.setTo(EMClient.getInstance().getCurrentUser());
      emMessage.setStatus(EMMessage.Status.SUCCESS);
      emMessage.setFrom(groupId);
      emMessage.setMsgId(UUID.randomUUID().toString());
      emMessage.addBody(new EMTextMessageBody(body));

      // save accept message
      EMClient.getInstance().chatManager().saveMessage(emMessage);
    }

    /**
     * 群成员退出通知
     *
     * @param groupId 群组id
     * @param member 成员
     */
    @Override public void onMemberExited(String groupId, String member) {
      LogUtils.d("onMemberJoined");
      //showToast("群成员退出通知 : " + member);
    }

    /**
     * 群所有者变动通知
     */
    @Override public void onAnnouncementChanged(String groupId, String announcement) {
      //showToast("群所有者变动通知 : " + groupId);
    }

    /**
     * 增加共享文件的通知
     */
    @Override public void onSharedFileAdded(String groupId, EMMucSharedFile sharedFile) {
      //showToast("增加共享文件的通知 : " + groupId);
    }

    /**
     * 群共享文件删除通知
     */
    @Override public void onSharedFileDeleted(String groupId, String fileId) {
      //showToast("群共享文件删除通知 : " + groupId);
    }
    // ============================= group_reform new add api end
  }

  /**
   * 联系人监听器，监听联系变化，包括添加好友的申请，对方删除好友的通知, 对方同意好友请求，对方拒绝好友请求。
   * 注册联系人监听，
   * 请执行EMClient.getInstance().contactManager().setContactListener(EMContactListener listener);
   */
  private class MyContactListener implements EMContactListener {

    /**
     * 增加联系人时回调此方法
     *
     * @param username 增加的联系人
     */
    @Override public void onContactAdded(String username) {
      // save contact
      //Map<String, UserInfo> localUsers = getContactList();
      //Map<String, UserInfo> toAddUsers = new ConcurrentHashMap<>();
      //UserInfo user = new UserInfo(username);
      //
      //if (!localUsers.containsKey(username)) {
      //  UserDao.get().saveContact(user);
      //}
      //toAddUsers.put(username, user);
      //localUsers.putAll(toAddUsers);
      //
      //broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_CONTACT_CHANAGED));
    }

    /**
     * 被删除时回调此方法
     *
     * @param username 删除的联系人
     */
    @Override public void onContactDeleted(String username) {
      Map<String, UserInfo> localUsers = getContactList();
      localUsers.remove(username);
      //UserDao.get().deleteContact(username);
      //InviteMsgDao.get().deleteMessage(username);
      EMClient.getInstance().chatManager().deleteConversation(username, false);
      broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_CONTACT_CHANAGED));
    }

    /**
     * 收到好友邀请
     *
     * @param username 发起加为好友用户的名称
     * @param reason 对方发起好友邀请时发出的文字性描述
     */
    @Override public void onContactInvited(String username, String reason) {
      //List<InviteMessage> msgs = InviteMsgDao.get().getMessagesList();
      //
      //for (InviteMessage inviteMessage : msgs) {
      //  if (inviteMessage.getGroupId() == null && inviteMessage.getFrom().equals(username)) {
      //    InviteMsgDao.get().deleteMessage(username);
      //  }
      //}
      //// save invitation as message
      //InviteMessage msg = new InviteMessage();
      //msg.setFrom(username);
      //msg.setTime(System.currentTimeMillis());
      //msg.setReason(reason);
      //LogUtils.d(username + "apply to be your friend,reason: " + reason);
      //// set invitation status
      //msg.setStatus(InviteMessageStatus.BEINVITEED);
      //notifyNewInviteMessage(msg);
      //broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_CONTACT_CHANAGED));
    }

    /**
     * 好友请求被同意
     *
     * @param username 用户的名称
     */
    @Override public void onFriendRequestAccepted(String username) {
      //List<InviteMessage> msgs = InviteMsgDao.get().getMessagesList();
      //for (InviteMessage inviteMessage : msgs) {
      //  if (inviteMessage.getFrom().equals(username)) {
      //    return;
      //  }
      //}
      //// save invitation as message
      //InviteMessage msg = new InviteMessage();
      //msg.setFrom(username);
      //msg.setTime(System.currentTimeMillis());
      //LogUtils.d(username + "accept your request");
      //msg.setStatus(InviteMessageStatus.BEAGREED);
      //notifyNewInviteMessage(msg);
      //broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_CONTACT_CHANAGED));
    }

    /***
     * 好友请求被拒绝
     * @param username 用户的名称
     */
    @Override public void onFriendRequestDeclined(String username) {
      // your request was refused
      LogUtils.d(username, username + " refused to your request");
    }
  }

  //private void notifyNewInviteMessage(InviteMessage msg) {
  //  InviteMsgDao.get().saveMessage(msg);
  //  InviteMsgDao.get().setUnreadNotifyCount(1);
  //  getNotifier().vibrateAndPlayTone(null);
  //}
}
