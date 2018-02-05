package youlin.xinhua.com.youlin.activity.tim;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import butterknife.OnClick;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMFriendAllowType;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;
import com.tencent.imsdk.ext.group.TIMGroupMemberResult;
import com.tencent.imsdk.ext.sns.TIMAddFriendRequest;
import com.tencent.imsdk.ext.sns.TIMFriendResult;
import com.tencent.imsdk.ext.sns.TIMFriendshipManagerExt;
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

  @OnClick({ R.id.btn_login, R.id.btn_add_contacts,R.id.btn_group_add, R.id.btn_group_intevier, R.id.btn_send_msg })
  public void click(View v) {
    switch (v.getId()) {
      case R.id.btn_login:
        showNameDialog();
        break;
      case R.id.btn_add_contacts:
        // 好友邀请
        addContacts();
        break;
      case R.id.btn_group_add:
        // 创建群
        addGroup();
        break;
      case R.id.btn_group_intevier:
        // 群邀请
        groupIntervite();
        break;
      case R.id.btn_send_msg:
        sendMsg();
        break;
    }
  }

  private void addGroup() {
    TIMGroupManager.CreateGroupParam param = new TIMGroupManager.CreateGroupParam();

    TIMGroupManager.getInstance().createGroup();
  }

  private void groupIntervite() {

    String groupName = "@TGS#2UW33ZBFT";

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

    TIMFriendshipManagerExt.getInstance()
        .addFriend(Collections.singletonList(new TIMAddFriendRequest("18664569168")),
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

    //TIMFriendAddResponse response = new TIMFriendAddResponse(TIMConsts.PHONE_173);
    //response.setType(TIMFriendResponseType.AgreeAndAdd);
    //TIMFriendshipManagerExt.getInstance()
    //    .addFriendResponse(response, new TIMValueCallBack<TIMFriendResult>() {
    //      @Override public void onError(int i, String s) {
    //        LogUtils.i(i + " , " + s);
    //      }
    //
    //      @Override public void onSuccess(TIMFriendResult timFriendResult) {
    //        LogUtils.i(timFriendResult.getStatus() + " , " + timFriendResult.getIdentifer());
    //      }
    //    });

    String loginUser = TIMManager.getInstance().getLoginUser();

    LogUtils.i("loginUser : " + loginUser);

    TIMFriendshipManagerExt.getInstance()
        .getFriendList(new TIMValueCallBack<List<TIMUserProfile>>() {
          @Override public void onError(int i, String s) {
            LogUtils.i(i + " , " + s);
          }

          @Override public void onSuccess(List<TIMUserProfile> timUserProfiles) {
            LogUtils.i("onSuccess , timUserProfiles :" + timUserProfiles);
          }
        });

    //List<TIMUserProfile> friends = TIMFriendshipProxy.getInstance().getFriends();
    //
    //LogUtils.i(friends);

    //List<TIMAddFriendRequest> reqList = new ArrayList<>();
    //TIMAddFriendRequest req = new TIMAddFriendRequest(TIMConsts.PHONE_186);
    //req.setAddWording("test");
    //reqList.add(req);
    //TIMFriendshipManagerExt.getInstance()
    //    .addFriend(reqList, new TIMValueCallBack<List<TIMFriendResult>>() {
    //
    //      @Override public void onError(int i, String s) {
    //        LogUtils.i(i + " , " + s);
    //      }
    //
    //      @Override public void onSuccess(List<TIMFriendResult> arg0) {
    //        TIMFriendResult timFriendResult = arg0.get(0);
    //        LogUtils.i(timFriendResult.getStatus() + " , " + timFriendResult.getIdentifer());
    //      }
    //    });

    //TIMFriendshipManagerExt.getInstance()
    //    .getFriendList(new TIMValueCallBack<List<TIMUserProfile>>() {
    //      @Override public void onError(int i, String s) {
    //        LogUtils.i(i + " , " + s);
    //      }
    //
    //      @Override public void onSuccess(List<TIMUserProfile> timUserProfiles) {
    //        LogUtils.i("onSuccess , timUserProfiles :" + timUserProfiles);
    //      }
    //    });

    //TIMFriendshipManager.getInstance().getUsersProfile();
    //
    //TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
    //  @Override public void onError(int i, String s) {
    //    LogUtils.i(i + " , " + s);
    //  }
    //
    //  @Override public void onSuccess(TIMUserProfile timUserProfile) {
    //    LogUtils.i(timUserProfile.getNickName()
    //        + " , "
    //        + timUserProfile.getFaceUrl() + " , "
    //        + timUserProfile.getIdentifier());
    //  }
    //});

    //TIMManager.getInstance().logout(new TIMCallBack() {
    //  @Override public void onError(int i, String s) {
    //    LogUtils.i("onError , i : " + i + " , s : " + s);
    //  }
    //
    //  @Override public void onSuccess() {
    //    LogUtils.i("onSuccess！");
    //  }
    //});

    //List<TIMConversation> list = TIMManagerExt.getInstance().getConversationList();
    //
    //LogUtils.i(list.size() + " , " + list.toString());

    //String loginUser = TIMManager.getInstance().getLoginUser();
    //
    //LogUtils.i(loginUser);

    //TIMConversation conversation =
    //    TIMManager.getInstance().getConversation(TIMConversationType.C2C, "18664569168");
    //
    //TIMMessage msg = new TIMMessage();
    //
    ////添加文本内容
    //TIMTextElem elem = new TIMTextElem();
    //elem.setText("a new msg");
    //
    ////将elem添加到消息
    //if (msg.addElement(elem) != 0) {
    //  return;
    //}
    //
    //conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {
    //  @Override public void onError(int i, String s) {
    //    ToastUtils.show("onError ! i : " + i + " , s :" + s);
    //  }
    //
    //  @Override public void onSuccess(TIMMessage timMessage) {
    //    ToastUtils.show("onSuccess ! timMessage : " + timMessage);
    //  }
    //});
  }
}
