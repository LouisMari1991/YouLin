package youlin.xinhua.com.youlin.activity.tim;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import butterknife.OnClick;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMFaceElem;
import com.tencent.imsdk.TIMFriendAllowType;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMGroupAddOpt;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupAssistant;
import com.tencent.imsdk.ext.group.TIMGroupCacheInfo;
import com.tencent.imsdk.ext.group.TIMGroupDetailInfo;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;
import com.tencent.imsdk.ext.group.TIMGroupMemberResult;
import com.tencent.imsdk.ext.sns.TIMAddFriendRequest;
import com.tencent.imsdk.ext.sns.TIMFriendResult;
import com.tencent.imsdk.ext.sns.TIMFriendshipManagerExt;
import com.tencent.imsdk.ext.sns.TIMFriendshipProxy;
import com.tencent.tim.consts.TIMConsts;
import java.util.Collections;
import java.util.List;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.LogUtils;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/01/31
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class TIMActivity extends BaseActivity {

  public static void start(Context context) {
    Intent starter = new Intent(context, TIMActivity.class);
    context.startActivity(starter);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_tim;
  }

  @OnClick({
      R.id.btn_login, R.id.btn_add_contacts, R.id.btn_group_add, R.id.btn_get_group,
      R.id.btn_group_intevier, R.id.btn_send_msg, R.id.btn_get_contacts, R.id.btn_group_request
  }) public void click(View v) {
    switch (v.getId()) {
      case R.id.btn_login:
        showNameDialog();
        break;
      case R.id.btn_add_contacts:
        // 好友邀请
        addContacts();
        break;
      case R.id.btn_get_contacts:
        // 获取所有好友
        getAllContracts();
        break;
      case R.id.btn_group_add:
        // 创建群
        createGroup();
        break;
      case R.id.btn_get_group:
        // 获取所有群
        getAllGroup();
        break;
      case R.id.btn_group_intevier:
        // 群邀请
        groupIntervite();
        break;
      case R.id.btn_send_msg:
        // 发送消息
        sendMsg();
        break;
      case R.id.btn_group_request:
        // 申请加群
        applyJoinGroup();
        break;
    }
  }

  private void applyJoinGroup() {

    // @TGS#24NF24CFU
    // @TGS#27PW24CFL

    TIMGroupManager.getInstance()
        .applyJoinGroup("@TGS#24NF24CFU", "some reason", new TIMCallBack() {
          @Override public void onError(int i, String s) {
            LogUtils.i(i + " , " + s);
          }

          @Override public void onSuccess() {
            LogUtils.i("onSuccess");
          }
        });
  }

  private void getAllContracts() {
    List<TIMUserProfile> friends = TIMFriendshipProxy.getInstance().getFriends();
    LogUtils.i(friends);
  }

  private void getAllGroup() {

    List<TIMGroupCacheInfo> groupInfos = TIMGroupAssistant.getInstance().getGroups(null);

    for (TIMGroupCacheInfo item : groupInfos) {
      TIMGroupDetailInfo groupInfo = item.getGroupInfo();
      LogUtils.i(groupInfo.getGroupName()
          + " , "
          + groupInfo.getGroupId()
          + " , "
          + groupInfo.getGroupAddOpt());
    }
  }

  private void createGroup() {
    TIMGroupManager.CreateGroupParam param =
        new TIMGroupManager.CreateGroupParam(TIMConsts.PUBLIC_GROUP, "群创建测试001");

    param.setAddOption(TIMGroupAddOpt.TIM_GROUP_ADD_AUTH);

    TIMGroupManager.getInstance().createGroup(param, new TIMValueCallBack<String>() {
      @Override public void onError(int i, String s) {
        LogUtils.i("创建群失败 onError， i ： " + i + " s : " + s);
      }

      @Override public void onSuccess(String s) {
        LogUtils.i("创建群成功 onSuccess ");
      }
    });
  }

  private void groupIntervite() {

    String groupName = "@TGS#24WC22BFX";

    TIMGroupManagerExt.getInstance()
        .inviteGroupMember(groupName, Collections.singletonList(TIMConsts.PHONE_186),
            new TIMValueCallBack<List<TIMGroupMemberResult>>() {
              @Override public void onError(int i, String s) {
                LogUtils.i("群邀请 onError， i ： " + i + " s : " + s);
              }

              @Override public void onSuccess(List<TIMGroupMemberResult> timGroupMemberResults) {
                LogUtils.i("群邀请 onSuccess ");
              }
            });
  }

  private void addContacts() {

    TIMAddFriendRequest request = new TIMAddFriendRequest("18664569168");
    request.setAddrSource("AddSource_Type_搜索");

    TIMFriendshipManagerExt.getInstance()
        .addFriend(Collections.singletonList(request),
            new TIMValueCallBack<List<TIMFriendResult>>() {
              @Override public void onError(int i, String s) {
                LogUtils.i("请求好友 onError， i ： " + i + " s : " + s);
              }

              @Override public void onSuccess(List<TIMFriendResult> timFriendResults) {
                LogUtils.i("请求好友 onSuccess");
              }
            });
  }

  private void showNameDialog() {
    String[] nameArray = { TIMConsts.PHONE_173, TIMConsts.PHONE_181, TIMConsts.PHONE_186 };
    AlertDialog alertDialog =
        new AlertDialog.Builder(this).setItems(nameArray, new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {
            switch (which) {
              case 0:
                login(TIMConsts.PHONE_173, TIMConsts.USER_SIGN_173);
                break;
              case 1:
                login(TIMConsts.PHONE_181, TIMConsts.USER_SIGN_181);
                break;
              case 2:
                login(TIMConsts.PHONE_186, TIMConsts.USER_SIGN_186);
                break;
            }
          }
        }).create();
    alertDialog.show();
  }

  private void login(String identification, String sig) {
    TIMManager.getInstance().login(identification, sig, new TIMCallBack() {
      @Override public void onError(int i, String s) {
        LogUtils.i("登录聊天服务器失败！ i ： " + i + " s : " + s);
      }

      @Override public void onSuccess() {
        LogUtils.i("登录聊天服务器成功！");
        TIMFriendshipManager.ModifyUserProfileParam param =
            new TIMFriendshipManager.ModifyUserProfileParam();
        param.setAllowType(TIMFriendAllowType.TIM_FRIEND_NEED_CONFIRM);
        TIMFriendshipManager.getInstance().modifyProfile(param, new TIMCallBack() {
          @Override public void onError(int i, String s) {
            LogUtils.i("修改好友验证失败！ i : " + i + " , s : " + s);
          }

          @Override public void onSuccess() {
            LogUtils.i("修改好友验证成功！");
          }
        });
      }
    });
  }

  private void sendMsg() {

    //获取单聊会话
    String peer = TIMConsts.PHONE_186;  //获取与用户 "sample_user_1" 的会话
    TIMConversation conversation =
        TIMManager.getInstance().getConversation(TIMConversationType.C2C,    //会话类型：单聊
            peer);                      //会话对方用户帐号//对方id

    TIMMessage msg = new TIMMessage();

    //添加文本内容
    TIMTextElem elem = new TIMTextElem();
    elem.setText("a new msg");

    TIMFaceElem faceElem = new TIMFaceElem();

    int i = msg.addElement(elem);

    LogUtils.i("addElement , i : " + i);

    conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {
      @Override public void onError(int i, String s) {
        LogUtils.i("onError ! i : " + i + " , s :" + s);
      }

      @Override public void onSuccess(TIMMessage timMessage) {
        LogUtils.i("onSuccess ! timMessage : " + timMessage);
      }
    });
  }
}
