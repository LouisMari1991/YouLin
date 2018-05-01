package youlin.xinhua.com.youlin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.activity.office.FileListActivity;
import youlin.xinhua.com.youlin.activity.share.SharePopActivity;
import youlin.xinhua.com.youlin.activity.shequyaowen.vote.VoteDetailActivity;
import youlin.xinhua.com.youlin.activity.tim.TIMActivity;
import youlin.xinhua.com.youlin.activity.yeweihui.AddCandidateInfoActivity;
import youlin.xinhua.com.youlin.base.BaseActivity;
import youlin.xinhua.com.youlin.activity.office.OfficeMainActivity;

//import youlin.xinhua.com.youlin.activity.shequyaowen.huodong.ActDetailActivity;

public class MainActivity extends BaseActivity {

  @OnClick({
      R.id.btn_color, R.id.btn_im, R.id.contact, R.id.contact_pick, R.id.chat, R.id.btn_signature,
      R.id.btn_picker, R.id.btn_input_information, R.id.btn_perfect_information, R.id.btn_empty,
      R.id.btn_share_pop, R.id.btn_vote_detail_test, R.id.btn_vote_detail, R.id.btn_dialog,
      R.id.btn_banner, R.id.btn_camera, R.id.btn_huodong, R.id.btn_add_candidate_info,
      R.id.btn_message_toolbar, R.id.btn_mohu, R.id.btn_rv, R.id.btn_tim, R.id.btn_image_picker,
      R.id.btn_file_manager
  }) public void click(View view) {
    switch (view.getId()) {
      case R.id.btn_color: {
        BtnActivity.lunch(this);
      }
      break;
      case R.id.btn_im: {
        //ImActivity.lunch(this);
      }
      break;
      case R.id.contact: {
        //ContactListActivity.lunch(this);
      }
      break;
      case R.id.contact_pick: {
        ContactsPickActivity.lunch(this);
      }
      break;
      case R.id.chat: {
        //ChatActivity.lunch(this);
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
      case R.id.btn_vote_detail: {
        VoteDetailActivity.launch(this);
      }
      break;
      case R.id.btn_huodong: {
        //ActDetailActivity.launch(this);
      }
      break;
      case R.id.btn_add_candidate_info: {
        AddCandidateInfoActivity.launch(this);
      }
      break;
      case R.id.btn_mohu: {
        GlideTransformationActivity.start(this);
      }
      break;
      case R.id.btn_tim: {
        TIMActivity.start(view.getContext());
        break;
      }
      case R.id.btn_image_picker: {
        OfficeMainActivity.start(view.getContext());
        break;
      }
      case R.id.btn_file_manager: {
        FileListActivity.start(view.getContext());
        break;
      }
      default:
        throw new UnsupportedOperationException(" id : " + view.getId());
    }
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_main;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //TIMManager.getInstance().login("18163972621", TIMConsts.USER_SIGN_181, new TIMCallBack() {
    //  @Override public void onError(int i, String s) {
    //    LogUtils.i("登录聊天服务器失败！ i ： " + i + " s : " + s);
    //  }
    //
    //  @Override public void onSuccess() {
    //    LogUtils.i("登录聊天服务器成功！");
    //  }
    //});

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

  @Override public boolean isSupportSwipeBack() {
    return false;
  }
}
