package youlin.xinhua.com.youlin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import java.util.List;
import youlin.xinhua.com.im.widget.EMGroupChangeListenerAdapter;
import youlin.xinhua.com.im.widget.EMMessageListenerAdapter;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.constant.EaseConstant;
import youlin.xinhua.com.youlin.im.EaseAtMessageHelper;
import youlin.xinhua.com.youlin.im.EaseCommonUtils;
import youlin.xinhua.com.youlin.im.IMPlatform;
import youlin.xinhua.com.youlin.listener.MessageListItemClickListener;
import youlin.xinhua.com.youlin.utils.LogUtils;
import youlin.xinhua.com.youlin.widget.chat.list.EaseChatMessageList;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-08-01 10:11
 * version: 1.0
 * </pre>
 */

public class BaseChatActivity extends BaseActivity {

  EaseChatMessageList messageList;

  int    chatType       = EaseConstant.CHATTYPE_SINGLE;
  String toChatUsername = "18664569168";
  boolean isMessageListInited;

  EMConversation conversation;

  Handler handler = new Handler();

  SwipeRefreshLayout swipeRefreshLayout;
  ListView           listView;

  boolean isloading;

  boolean haveMoreData = true;
  int     pagesize     = 20;

  GroupListener mGroupListener;
  MessageListener mMessageListener = new MessageListener();

  @Override protected int attachLayoutRes() {
    return R.layout.activity_im_chat;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initViews();
  }

  void initViews() {
    if (chatType == EaseConstant.CHATTYPE_SINGLE) {
      // set title
      //if(EaseUserUtils.getUserInfo(toChatUsername) != null){
      //  EaseUser user = EaseUserUtils.getUserInfo(toChatUsername);
      //  if (user != null) {
      //    titleBar.setTitle(user.getNick());
      //  }
      //}
      //titleBar.setRightImageResource(R.drawable.ease_mm_title_remove);
    } else {
      //titleBar.setRightImageResource(R.drawable.ease_to_group_details_normal);
      if (chatType == EaseConstant.CHATTYPE_GROUP) {
        //group chat
        EMGroup group = EMClient.getInstance().groupManager().getGroup(toChatUsername);
        //if (group != null)
        //  titleBar.setTitle(group.getGroupName());
        // listen the event that user moved out group or group is dismissed
        mGroupListener = new GroupListener();
        EMClient.getInstance().groupManager().addGroupChangeListener(mGroupListener);
        //} else {
        //  chatRoomListener = new ChatRoomListener();
        //  EMClient.getInstance().chatroomManager().addChatRoomChangeListener(chatRoomListener);
        //  onChatRoomViewCreation();
        //}

      }
    }
  }

  @Override protected void onResume() {
    super.onResume();
    if (isMessageListInited) {
      messageList.refresh();
    }

    // register the event listener when enter the foreground
    EMClient.getInstance().chatManager().addMessageListener(mMessageListener);

    if (chatType == EaseConstant.CHATTYPE_GROUP) {
      EaseAtMessageHelper.get().removeAtMeGroup(toChatUsername);
    }
  }

  @Override protected void onStop() {
    super.onStop();
    // unregister this event listener when this activity enters the
    // background
    EMClient.getInstance().chatManager().removeMessageListener(mMessageListener);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (mGroupListener != null) {
      EMClient.getInstance().groupManager().removeGroupChangeListener(mGroupListener);
    }
  }

  protected void onConversationInit() {
    conversation = EMClient.getInstance()
        .chatManager()
        .getConversation(toChatUsername, EaseCommonUtils.getConversationType(chatType), true);
    conversation.markAllMessagesAsRead();
    // the number of messages loaded into conversation is getChatOptions().getNumberOfMessagesLoaded
    // you can change this number
    final List<EMMessage> msgs = conversation.getAllMessages();
    int msgCount = msgs != null ? msgs.size() : 0;
    if (msgCount < conversation.getAllMsgCount() && msgCount < pagesize) {
      String msgId = null;
      if (msgs != null && msgs.size() > 0) {
        msgId = msgs.get(0).getMsgId();
      }
      conversation.loadMoreMsgFromDB(msgId, pagesize - msgCount);
    }
  }

  protected void onMessageListInit() {
    messageList.init(toChatUsername, chatType);
    setListItemClickListener();
    isMessageListInited = true;
  }

  private void setListItemClickListener() {
    messageList.setItemClickListener(new MessageListItemClickListener() {
      @Override public void onResendClick(EMMessage message) {
        resendMessage(message);
      }

      @Override public boolean onBubbleClick(EMMessage message) {

        return false;
      }

      @Override public void onBubbleLongClick(EMMessage message) {

      }

      @Override public void onUserAvatarClick(String username) {

      }

      @Override public void onUserAvatarLongClick(String username) {

      }
    });

  }

  protected void setRefreshLayoutListener() {
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

      @Override public void onRefresh() {
        new Handler().postDelayed(new Runnable() {

          @Override public void run() {
            if (listView.getFirstVisiblePosition() == 0 && !isloading && haveMoreData) {
              List<EMMessage> messages;
              try {
                if (chatType == EaseConstant.CHATTYPE_SINGLE) {
                  messages =
                      conversation.loadMoreMsgFromDB(messageList.getItem(0).getMsgId(), pagesize);
                } else {
                  messages =
                      conversation.loadMoreMsgFromDB(messageList.getItem(0).getMsgId(), pagesize);
                }
              } catch (Exception e1) {
                swipeRefreshLayout.setRefreshing(false);
                return;
              }
              if (messages.size() > 0) {
                messageList.refreshSeekTo(messages.size() - 1);
                if (messages.size() != pagesize) {
                  haveMoreData = false;
                }
              } else {
                haveMoreData = false;
              }
              isloading = false;
            } else {
              Toast.makeText(BaseChatActivity.this,
                  getResources().getString(R.string.no_more_messages), Toast.LENGTH_SHORT).show();
            }
            swipeRefreshLayout.setRefreshing(false);
          }
        }, 600);
      }
    });
  }

  /**
   * input @
   */
  protected void inputAtUsername(String username, boolean autoAddAtSymbol) {
    if (EMClient.getInstance().getCurrentUser().equals(username)
        || chatType != EaseConstant.CHATTYPE_GROUP) {
      return;
    }
    EaseAtMessageHelper.get().addAtUser(username);
    //EaseUser user = EaseUserUtils.getUserInfo(username);
    //if (user != null){
    //  username = user.getNick();
    //}
    //if(autoAddAtSymbol){
    //  inputMenu.insertText("@" + username + " ");
    //}
    //else{
    //  inputMenu.insertText(username + " ");
    //}
  }

  /**
   * input @
   */
  protected void inputAtUsername(String username) {
    inputAtUsername(username, true);
  }

  //send message
  protected void sendTextMessage(String content) {
    //if(EaseAtMessageHelper.get().containsAtUsername(content)){
    //  sendAtMessage(content);
    //}else{
    EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
    sendMessage(message);
    //}
  }

  /**
   * send @ message, only support group chat message
   */
  @SuppressWarnings("ConstantConditions") private void sendAtMessage(String content) {
    if (chatType != EaseConstant.CHATTYPE_GROUP) {
      LogUtils.e("only support group chat message");
      return;
    }
    EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
    EMGroup group = EMClient.getInstance().groupManager().getGroup(toChatUsername);
    if (EMClient.getInstance().getCurrentUser().equals(group.getOwner())
        && EaseAtMessageHelper.get().containsAtAll(content)) {
      message.setAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG,
          EaseConstant.MESSAGE_ATTR_VALUE_AT_MSG_ALL);
    } else {
      message.setAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG, EaseAtMessageHelper.get()
          .atListToJsonArray(EaseAtMessageHelper.get().getAtMessageUsernames(content)));
    }
    sendMessage(message);
  }

  protected void sendVoiceMessage(String filePath, int length) {
    EMMessage message = EMMessage.createVoiceSendMessage(filePath, length, toChatUsername);
    sendMessage(message);
  }

  protected void sendImageMessage(String imagePath) {
    EMMessage message = EMMessage.createImageSendMessage(imagePath, false, toChatUsername);
    sendMessage(message);
  }

  protected void sendLocationMessage(double latitude, double longitude, String locationAddress) {
    EMMessage message =
        EMMessage.createLocationSendMessage(latitude, longitude, locationAddress, toChatUsername);
    sendMessage(message);
  }

  protected void sendVideoMessage(String videoPath, String thumbPath, int videoLength) {
    EMMessage message =
        EMMessage.createVideoSendMessage(videoPath, thumbPath, videoLength, toChatUsername);
    sendMessage(message);
  }

  protected void sendFileMessage(String filePath) {
    EMMessage message = EMMessage.createFileSendMessage(filePath, toChatUsername);
    sendMessage(message);
  }

  protected void sendMessage(EMMessage message) {
    if (message == null) {
      return;
    }
    //if(chatFragmentHelper != null){
    //  //set extension
    //  chatFragmentHelper.onSetMessageAttributes(message);
    //}
    if (chatType == EaseConstant.CHATTYPE_GROUP) {
      message.setChatType(EMMessage.ChatType.GroupChat);
    } else if (chatType == EaseConstant.CHATTYPE_CHATROOM) {
      message.setChatType(EMMessage.ChatType.ChatRoom);
    }
    //send message
    EMClient.getInstance().chatManager().sendMessage(message);
    //refresh ui
    if (isMessageListInited) {
      messageList.refreshSelectLast();
    }
  }

  public void resendMessage(EMMessage message) {
    message.setStatus(EMMessage.Status.CREATE);
    EMClient.getInstance().chatManager().sendMessage(message);
    messageList.refresh();
  }

  class MessageListener extends EMMessageListenerAdapter {

    @Override public void onMessageReceived(List<EMMessage> messages) {
      for (EMMessage message : messages) {
        String username = null;
        // group message
        if (message.getChatType() == EMMessage.ChatType.GroupChat
            || message.getChatType() == EMMessage.ChatType.ChatRoom) {
          username = message.getTo();
        } else {
          // single chat message
          username = message.getFrom();
        }

        // if the message is for current conversation
        if (username.equals(toChatUsername) || message.getTo().equals(toChatUsername)) {
          messageList.refreshSelectLast();
          IMPlatform.get().getNotifier().vibrateAndPlayTone(message);
          conversation.markMessageAsRead(message.getMsgId());
        }
      }
    }

    @Override public void onCmdMessageReceived(List<EMMessage> list) {

    }

    @Override public void onMessageChanged(EMMessage emMessage, Object o) {
      if (isMessageListInited) {
        messageList.refresh();
      }
    }
  }

  /**
   * listen the group event
   */
  class GroupListener extends EMGroupChangeListenerAdapter {

    @Override public void onUserRemoved(final String groupId, String groupName) {
      runOnUiThread(new Runnable() {

        public void run() {
          if (toChatUsername.equals(groupId)) {
            Toast.makeText(BaseChatActivity.this, R.string.you_are_group, Toast.LENGTH_LONG).show();
            Activity activity = BaseChatActivity.this;
            if (!activity.isFinishing()) {
              activity.finish();
            }
          }
        }
      });
    }

    @Override public void onGroupDestroyed(final String groupId, String groupName) {
      // prompt group is dismissed and finish this activity
      runOnUiThread(new Runnable() {
        public void run() {
          if (toChatUsername.equals(groupId)) {
            Toast.makeText(BaseChatActivity.this, R.string.the_current_group_destroyed,
                Toast.LENGTH_LONG).show();
            Activity activity = BaseChatActivity.this;
            if (!activity.isFinishing()) {
              activity.finish();
            }
          }
        }
      });
    }
  }
}
