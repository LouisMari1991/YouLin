package youlin.xinhua.com.youlin.activity.shequyaowen.huodong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import butterknife.BindView;
import java.util.ArrayList;
import java.util.List;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.bean.HuoDongItemBean;
import youlin.xinhua.com.youlin.widget.AvatarNameContainer;
import youlin.xinhua.com.youlin.widget.CommentLayout;
import youlin.xinhua.com.youlin.widget.ResizeLayout;
import youlin.xinhua.com.youlin.widget.html.HtmlTextView;

import static youlin.xinhua.com.youlin.widget.ResizeLayout.KEYBOARD_STATE_HIDE;
import static youlin.xinhua.com.youlin.widget.ResizeLayout.KEYBOARD_STATE_INIT;
import static youlin.xinhua.com.youlin.widget.ResizeLayout.KEYBOARD_STATE_SHOW;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-19 10:28
 * version: 1.0
 * </pre>
 */

public class ActDetailActivity extends BaseActivity implements View.OnTouchListener {

  private String detailc =
      "<div><p>手机越来越普及，手机应用的使用频率以及重要性也在直线上升。微信、支付宝早已成为了广大智能手机用户的必装软件之一。与此同时，应用的功能也在频频升级，比如手机上的支付宝，其功能就愈加强大，为手机用户的日常生活带来了诸多便利。</p><p><img src=\"http://p3.pstatp.com/large/2434000317d3801ba09f\" img_width=\"640\" img_height=\"360\" inline=\"0\" alt=\"支付宝又放大招，500000元账户流水可免！\" onerror=\"javascript:errorimg.call(this);\"></p><p>近日，支付宝公布了一项新的利好消息：日本五年签证简化。在此之前，办理日本五年签证，需要提供多项材料，其中就包括一份资产证明：一年的银行活期储蓄卡对账单（能体现工资进账记录，<strong>年薪五十万以上</strong>），余额高于5万人民币（<strong>必备</strong>）。不仅资料繁琐，而且门槛颇高。</p><p><img src=\"http://p9.pstatp.com/large/243800012449e0f6526b\" img_width=\"640\" img_height=\"402\" inline=\"0\" alt=\"支付宝又放大招，500000元账户流水可免！\" onerror=\"javascript:errorimg.call(this);\"></p><p>现在，手机支付宝用户的<strong>芝麻信用评分700分及以上，</strong>凭芝麻签证报告办理日本5年多次往返签证， 享材料减免，<strong>仅需：护照+照片+申请表+芝麻签证报告即可申请，无需50万元的银行账户流水，有效降低了门槛。</strong></p><p><img src=\"http://p3.pstatp.com/large/24390003fbb58688d2dd\" img_width=\"640\" img_height=\"469\" inline=\"0\" alt=\"支付宝又放大招，500000元账户流水可免！\" onerror=\"javascript:errorimg.call(this);\"></p><p><img src=\"http://p3.pstatp.com/large/243b0003155cf8dcd292\" img_width=\"640\" img_height=\"360\" inline=\"0\" alt=\"支付宝又放大招，500000元账户流水可免！\" onerror=\"javascript:errorimg.call(this);\"></p><p>以我个人为例，尽管年薪不足五十万元，但是手机支付宝的芝麻信用评分已然超过了700分。相信跟我一样的手机支付宝用户不在少数。早前可能无法申请日本签证，现在享受支付宝<strong>芝麻信用带来的福利之后，就可以愉快的办理签证了。</strong></p><p><img src=\"http://p3.pstatp.com/large/26e30000761ed176d536\" img_width=\"640\" img_height=\"637\" inline=\"0\" alt=\"支付宝又放大招，500000元账户流水可免！\" onerror=\"javascript:errorimg.call(this);\"></p></div>";

  @BindView(R.id.resize_layout)    ResizeLayout        mResizeLayout;
  @BindView(R.id.text_html)        HtmlTextView        mHtmlTextView;
  @BindView(R.id.comment_layout)   CommentLayout       mCommentLayout;
  @BindView(R.id.btn_sure)         Button              btnSure;
  @BindView(R.id.avatar_container) AvatarNameContainer mAvatarNameContainer;

  public static void launch(Context context) {
    Intent intent = new Intent(context, ActDetailActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_yunshe_act_detail;
  }

  @Override public void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mHtmlTextView.setRichText(detailc);
    initResizeLayout();
    List<HuoDongItemBean.Applicant> applicantList = new ArrayList<>();
    HuoDongItemBean.Applicant applicant0 = new HuoDongItemBean.Applicant();
    applicant0.setApplicantMemo(
        "http://img4.imgtn.bdimg.com/it/u=1762973822,121126736&fm=27&gp=0.jpg");
    applicant0.setApplicantName("abc");
    HuoDongItemBean.Applicant applicant1 = new HuoDongItemBean.Applicant();
    applicant1.setApplicantMemo(
        "http://img4.imgtn.bdimg.com/it/u=1762973822,121126736&fm=27&gp=0.jpg");
    applicant1.setApplicantName("18865155451");
    applicantList.add(applicant0);
    applicantList.add(applicant1);
    mAvatarNameContainer.initView(applicantList);
  }

  private void initResizeLayout() {
    mResizeLayout.setKeyboardChangedListener(new ResizeLayout.OnKeyboardChangedListener() {
      @Override public void onKeyBoardStateChanged(int state) {
        switch (state) {
          case KEYBOARD_STATE_INIT:
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            mCommentLayout.changedInputType(0);
            break;
          case KEYBOARD_STATE_HIDE:
            mCommentLayout.changedInputType(0);
            btnSure.setVisibility(View.VISIBLE);
            break;
          case KEYBOARD_STATE_SHOW:
            mCommentLayout.changedInputType(1);
            btnSure.setVisibility(View.GONE);
            break;
        }
      }
    });
  }

  @Override public boolean onTouch(View v, MotionEvent event) {

    return false;
  }
}
