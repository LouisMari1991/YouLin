package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.OnClick;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.exceptions.HyphenateException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
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

  @OnClick({ R.id.group_search, R.id.group_info, R.id.group_join }) public void onclick(View view) {
    switch (view.getId()) {
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
    }
  }

  /**
   * 申请加群
   */
  private void joinGroup() {
    new Thread(){
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

    EMGroup emGroup = EMClient.getInstance().groupManager().getGroup("21873920376835");
    LogUtils.i(emGroup.getGroupName());
    LogUtils.i(emGroup.getMemberCount());
    LogUtils.i(emGroup.getMembers());
    LogUtils.i(emGroup.getAdminList().toString());
    LogUtils.i(emGroup.getDescription());

    //cachedThreadPool.execute(new Runnable() {
    //  @Override public void run() {
    //    try {
    //      EMGroup emGroup =
    //          EMClient.getInstance().groupManager().getGroupFromServer("21873920376835", true);
    //      LogUtils.i(emGroup.getGroupName());
    //      LogUtils.i(emGroup.getMemberCount());
    //      LogUtils.i(emGroup.getMembers());
    //      LogUtils.i(emGroup.getAdminList().toString());
    //      LogUtils.i(emGroup.getDescription());
    //
    //      //EMCursorResult<String> result =
    //      //    EMClient.getInstance().groupManager().fetchGroupMembers("21533013639169", null, 20);
    //      //
    //      //LogUtils.i("从服务端获取群成员 : " + result.getData().toString());
    //    } catch (HyphenateException e) {
    //      e.printStackTrace();
    //    }
    //  }
    //});
  }
}
