package youlin.xinhua.com.youlin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.activity.camera.CameraActivity;
import youlin.xinhua.com.youlin.activity.share.SharePopActivity;
import youlin.xinhua.com.youlin.activity.shequyaowen.huodong.ActDetailActivity;
import youlin.xinhua.com.youlin.activity.shequyaowen.vote.VoteDetailActivity;
import youlin.xinhua.com.youlin.activity.shequyaowen.vote.VoteDetailActivityTest;
import youlin.xinhua.com.youlin.activity.yeweihui.AddCandidateInfoActivity;
import youlin.xinhua.com.youlin.activity.yeweihui.InputInformationActivity;
import youlin.xinhua.com.youlin.activity.yeweihui.PerfectInformationActivity;

public class MainActivity extends BaseActivity {

  @OnClick({
      R.id.btn_color, R.id.btn_im, R.id.contact, R.id.contact_pick, R.id.chat, R.id.btn_signature,
      R.id.btn_picker, R.id.btn_input_information, R.id.btn_perfect_information, R.id.btn_empty,
      R.id.btn_share_pop, R.id.btn_vote_detail_test, R.id.btn_vote_detail, R.id.btn_dialog,
      R.id.btn_banner, R.id.btn_camera, R.id.btn_huodong, R.id.btn_add_candidate_info,
      R.id.btn_message_toolbar
  }) public void click(View view) {
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
      case R.id.btn_signature: { // 手写签名
        SignatureActivity.launch(this);
      }
      break;
      case R.id.btn_picker: {// 选择器
        PickActivity.launch(this);
      }
      break;
      case R.id.btn_input_information: {// 填写资料
        InputInformationActivity.launch(this);
      }
      break;
      case R.id.btn_perfect_information: { // 完善信息
        PerfectInformationActivity.launch(this);
      }
      break;
      case R.id.btn_empty: {
        EmptyLayoutActivity.launch(this);
      }
      break;
      case R.id.btn_share_pop: {
        SharePopActivity.launch(this);
      }
      break;
      case R.id.btn_vote_detail_test: {
        VoteDetailActivityTest.launch(this);
      }
      break;
      case R.id.btn_vote_detail: {
        VoteDetailActivity.launch(this);
      }
      break;
      case R.id.btn_dialog: {
        DialogTestActivity.launch(this);
      }
      break;
      case R.id.btn_banner: {
        BannerActivity.launch(this);
      }
      break;
      case R.id.btn_camera: {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivityForResult(intent, 100);
      }
      break;
      case R.id.btn_huodong: {
        ActDetailActivity.launch(this);
      }
      break;
      case R.id.btn_add_candidate_info: {
        AddCandidateInfoActivity.launch(this);
      }
      break;
      case R.id.btn_message_toolbar: {
        //MessageToolbarActivity.start(this);
        WaterDropStyleActivity.start(this);
      }
      break;
      default:
        throw new UnsupportedOperationException(" id : " + view.getId());
    }
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_main;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //EMClient.getInstance().login("123456", "123456", new EMCallBack() {//回调
    //  @Override public void onSuccess() {
    //    LogUtils.i("登录聊天服务器成功！");
    //    // ** manually load all local groups and conversation
    //    EMClient.getInstance().groupManager().loadAllGroups();
    //    EMClient.getInstance().chatManager().loadAllConversations();
    //  }
    //
    //  @Override public void onProgress(int progress, String status) {
    //
    //  }
    //
    //  @Override public void onError(int code, String message) {
    //    LogUtils.i("登录聊天服务器失败！, code : " + code + " , msg : " + message);
    //  }
    //});
  }
}
