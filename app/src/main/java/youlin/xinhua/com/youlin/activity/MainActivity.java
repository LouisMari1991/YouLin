package youlin.xinhua.com.youlin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.OnClick;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;

public class MainActivity extends BaseActivity {

  @OnClick({ R.id.btn_color, R.id.btn_im, R.id.contact, R.id.contact_pick, R.id.chat })
  public void click(View view) {
    switch (view.getId()) {
      case R.id.btn_color: {
        BtnActivity.lunch(this);
      }
      break;
      case R.id.btn_im: {
        ImActivity.lunch(this);
      }
      break;
      case R.id.contact: {
        ContactListActivity.lunch(this);
      }
      break;
      case R.id.contact_pick: {
        ContactsPickActivity.lunch(this);
      }
      break;
      case R.id.chat: {
        ChatActivity.lunch(this);
      }
      break;
    }
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_main;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EMClient.getInstance().login("123456", "123456", new EMCallBack() {//回调
      @Override public void onSuccess() {
        //LogUtils.i("登录聊天服务器成功！");
        // ** manually load all local groups and conversation
        EMClient.getInstance().groupManager().loadAllGroups();
        EMClient.getInstance().chatManager().loadAllConversations();
      }

      @Override public void onProgress(int progress, String status) {

      }

      @Override public void onError(int code, String message) {
        //LogUtils.i("登录聊天服务器失败！, code : " + code + " , msg : " + message);
      }
    });
  }
}
