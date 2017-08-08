package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.OnClick;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.constant.EaseConstant;
import youlin.xinhua.com.youlin.utils.LogUtils;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-13 16:42
 * version: 1.0
 * </pre>
 */

public class ImActivity extends BaseActivity {

  public static void lunch(Context context) {
    Intent intent = new Intent(context, ImActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_im;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogUtils.i(EMClient.getInstance().getCurrentUser());
  }

  ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

  @OnClick({
      R.id.create_group, R.id.group_search, R.id.group_info, R.id.group_join, R.id.member_join
  }) public void onclick(View view) {
    switch (view.getId()) {
      case R.id.create_group: {
        createNewGroup();
      }
      break;

      case R.id.group_search: {
        searchGroup();
      }
      break;
      case R.id.group_info: {
        getGroupInfo();
      }
      break;
      case R.id.group_join: {
        joinGroup();
      }
      break;
      case R.id.member_join: {
        memberJoined();
      }
      break;
    }
  }

  /**
   * 新建群组
   */
  private void createNewGroup() {

    cachedThreadPool.execute(new Runnable() {
      @Override public void run() {
        String groupName = "createNewGroup," + UUID.randomUUID().toString().substring(0, 5);
        String[] members = { "18664569168", "18664569167" };
        EMGroupManager.EMGroupOptions option = new EMGroupManager.EMGroupOptions();
        option.maxUsers = 2000; // 可以设置群组最大用户数(默认200)
        option.inviteNeedConfirm = true; // 表示邀请对方进群是否需要对方同意，默认是需要用户同意才能加群的。
        option.extField = "adasdadasd"; // 创建群时可以为群组设定扩展字段，方便个性化订制。目前作为群头像
        option.style = EMGroupManager.EMGroupStyle.EMGroupStylePublicJoinNeedApproval;
        try {
          final EMGroup emGroup = EMClient.getInstance()
              .groupManager()
              .createGroup(groupName, "desc", members, "reason", option);
          runOnUiThread(new Runnable() {
            @Override public void run() {
              LogUtils.e(
                  "suc , emGroupId : " + emGroup.getGroupId() + " , " + emGroup.getGroupName());
            }
          });
        } catch (final HyphenateException e) {
          LogUtils.e(e.getErrorCode() + " , " + e.getMessage());
        }
      }
    });
  }

  private void memberJoined() {

    String body = getResources().getString(R.string.row_group_member_joined, "hm01");

    // EventMessage
    EMMessage emMessage = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
    emMessage.setAttribute(EaseConstant.MESSAGE_ATTR_EVENT_MESSAGE, true);
    emMessage.setChatType(EMMessage.ChatType.Chat);
    emMessage.setTo(EMClient.getInstance().getCurrentUser());
    emMessage.setStatus(EMMessage.Status.SUCCESS);
    emMessage.setFrom("hm01");
    emMessage.setMsgId(UUID.randomUUID().toString());

    emMessage.addBody(new EMTextMessageBody(body));

    // save accept message
    EMClient.getInstance().chatManager().saveMessage(emMessage);
  }

  /**
   * 申请加群
   */
  private void joinGroup() {
    new Thread() {
      @Override public void run() {
        try {
          EMClient.getInstance().groupManager().applyJoinToGroup("22079993872385", "让我来玩玩");
        } catch (HyphenateException e) {
          e.printStackTrace();
          LogUtils.e(e.getErrorCode() + " , " + e.getMessage());
        }
      }
    }.start();
  }

  /**
   * 获取已加入的群
   */
  public void getGroupInfo() {
    List<EMGroup> groups = EMClient.getInstance().groupManager().getAllGroups();
    LogUtils.i("内存获取 : " + groups.size());
    for (EMGroup group : groups) {
      LogUtils.i(group.getGroupId());
      LogUtils.i(group.getGroupName());
      LogUtils.i(group.getAdminList());
      LogUtils.i(group.getMembers());
      LogUtils.i(group.getMemberCount());
    }

    cachedThreadPool.execute(new Runnable() {
      @Override public void run() {
        try {
          List<EMGroup> groups0 = EMClient.getInstance().groupManager().getJoinedGroupsFromServer();
          LogUtils.i("服务端获取 : " + groups0.size());
          for (EMGroup group : groups0) {
            LogUtils.i(group.getGroupId());
            LogUtils.i(group.getGroupName());
            LogUtils.i(group.getAdminList());
            LogUtils.i(group.getMembers());
            LogUtils.i(group.getMemberCount());
          }
        } catch (HyphenateException e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * 搜索群,获取群信息
   */
  public void searchGroup() {

    EMGroup emGroup = EMClient.getInstance().groupManager().getGroup("23326586109953");
    LogUtils.i(emGroup.getGroupName());
    LogUtils.i(emGroup.getMemberCount());
    LogUtils.i(emGroup.getMembers());
    LogUtils.i(emGroup.getAdminList().toString());
    LogUtils.i(emGroup.getOwner());
    LogUtils.i(emGroup.getDescription());

    //cachedThreadPool.execute(new Runnable() {
    //  @Override public void run() {
    //    try {
    //      EMGroup emGroup =
    //          EMClient.getInstance().groupManager().getGroupFromServer("23326586109953", true);
    //      LogUtils.i(emGroup.getGroupName());
    //      LogUtils.i(emGroup.getMemberCount());
    //      LogUtils.i(emGroup.getMembers());
    //      LogUtils.i(emGroup.getAdminList().toString());
    //      LogUtils.i(emGroup.getOwner());
    //      LogUtils.i(emGroup.getDescription());
    //
    //      //EMCursorResult<String> result =
    //      //    EMClient.getInstance().groupManager().fetchGroupMembers("21533013639169", null, 20);
    //      //
    //      //LogUtils.i("从服务端获取群成员 : " + result.getData().toString());
    //    } catch (HyphenateException e) {
    //      LogUtils.e(e.getErrorCode() + " , " + e.getMessage());
    //      e.printStackTrace();
    //    }
    //  }
    //});
  }
}
