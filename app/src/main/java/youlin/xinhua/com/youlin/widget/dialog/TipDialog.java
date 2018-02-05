//package youlin.xinhua.com.youlin.widget.dialog;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.IntDef;
//import android.view.View;
//import android.view.WindowManager;
//import android.view.animation.Animation;
//import android.view.animation.AnimationSet;
//import android.view.animation.Transformation;
//import android.widget.Button;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import com.pnikosis.materialishprogress.ProgressWheel;
//import com.xinhua.dialoglib.OptAnimationLoader;
//import com.xinhua.dialoglib.ProgressHelper;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import youlin.xinhua.com.youlin.R;
//import youlin.xinhua.com.youlin.utils.LogUtils;
//
//public class TipDialog extends Dialog implements View.OnClickListener {
//
//  public static final int NORMAL_TYPE   = 0;
//  public static final int PROGRESS_TYPE = 5;
//  public static final int TIP_TYPE      = 1;
//
//  private AnimationSet mModalInAnim;
//  private AnimationSet mModalOutAnim;
//  private Animation    mOverlayOutAnim;
//
//  private String mTitleText;
//  private String mContentText;
//  private String mTipText;
//
//  private View         mDialogView;
//  private TextView     mTitleTextView;
//  private TextView     mContentTextView;
//  private FrameLayout  mProgressFrame;
//  private LinearLayout mNormalContainer;
//  private TextView     mTipTextView;
//
//  private int    mAlertType;
//  private String mCancelText;
//  private String mConfirmText;
//  private Button mConfirmButton;
//  private Button mCancelButton;
//
//  private OnSweetClickListener mCancelClickListener;
//  private OnSweetClickListener mConfirmClickListener;
//
//  private ProgressHelper mProgressHelper;
//
//  private boolean mShowCancel;
//  private boolean mCloseFromCancel;
//  private boolean mShowContent;
//  private boolean mShowTitle;
//  private boolean mShowTip;
//
//  @Retention(RetentionPolicy.SOURCE) @IntDef({
//      NORMAL_TYPE, PROGRESS_TYPE, TIP_TYPE
//  }) public @interface AlertType {
//
//  }
//
//  public interface OnSweetClickListener {
//    void onClick(TipDialog tipDialog);
//  }
//
//  public TipDialog(Context context) {
//    this(context, NORMAL_TYPE);
//  }
//
//  public TipDialog(Context context, @AlertType int alertType) {
//    super(context, R.style.alert_dialog);
//    init(context, alertType);
//  }
//
//  public TipDialog(Context context, @AlertType int alertType, boolean fullScreen) {
//    super(context, R.style.full_screen_dialog);
//    init(context, alertType);
//  }
//
//  private void init(Context context, @AlertType int alertType) {
//    setCancelable(true);
//    setCanceledOnTouchOutside(false);
//    mProgressHelper = new ProgressHelper(context);
//    mAlertType = alertType;
//    mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(),
//        com.xinhua.dialoglib.R.anim.modal_in);
//    mModalOutAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(),
//        com.xinhua.dialoglib.R.anim.modal_out);
//    mModalOutAnim.setAnimationListener(new Animation.AnimationListener() {
//      @Override public void onAnimationStart(Animation animation) {
//
//      }
//
//      @Override public void onAnimationEnd(Animation animation) {
//        mDialogView.setVisibility(View.GONE);
//        mDialogView.post(new Runnable() {
//          @Override public void run() {
//            if (mCloseFromCancel) {
//              TipDialog.super.cancel();
//            } else {
//              TipDialog.super.dismiss();
//            }
//          }
//        });
//      }
//
//      @Override public void onAnimationRepeat(Animation animation) {
//
//      }
//    });
//    // dialog overlay fade out
//    mOverlayOutAnim = new Animation() {
//      @Override protected void applyTransformation(float interpolatedTime, Transformation t) {
//        WindowManager.LayoutParams wlp = getWindow().getAttributes();
//        wlp.alpha = 1 - interpolatedTime;
//        getWindow().setAttributes(wlp);
//      }
//    };
//    mOverlayOutAnim.setDuration(120);
//  }
//
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.dialog_tip);
//
//    mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
//    mNormalContainer = (LinearLayout) findViewById(R.id.normal_container);
//    mTipTextView = (TextView) findViewById(R.id.text_tip);
//    mTitleTextView = (TextView) findViewById(R.id.title_text);
//    mContentTextView = (TextView) findViewById(R.id.content_text);
//    mProgressFrame = (FrameLayout) findViewById(com.xinhua.dialoglib.R.id.progress_dialog);
//    mConfirmButton = (Button) findViewById(com.xinhua.dialoglib.R.id.confirm_button);
//    mCancelButton = (Button) findViewById(com.xinhua.dialoglib.R.id.cancel_button);
//    mProgressHelper.setProgressWheel(
//        (ProgressWheel) findViewById(com.xinhua.dialoglib.R.id.progressWheel));
//    mConfirmButton.setOnClickListener(this);
//    mCancelButton.setOnClickListener(this);
//
//    setTitleText(mTitleText);
//    setContentText(mContentText);
//    setCancelText(mCancelText);
//    setConfirmText(mConfirmText);
//    setTipText(mTipText);
//    changeAlertType(mAlertType, true);
//  }
//
//  private void restore() {
//    mProgressFrame.setVisibility(View.GONE);
//    mConfirmButton.setVisibility(View.VISIBLE);
//  }
//
//  private void playAnimation() {
//
//  }
//
//  private void changeAlertType(int alertType, boolean fromCreate) {
//    mAlertType = alertType;
//    // call after created views
//    if (mDialogView != null) {
//      if (!fromCreate) {
//        // restore all of views state before switching alert type
//        restore();
//      }
//      switch (mAlertType) {
//        case PROGRESS_TYPE:
//          mProgressFrame.setVisibility(View.VISIBLE);
//          mTitleTextView.setVisibility(View.GONE);
//          mConfirmButton.setVisibility(View.GONE);
//          mCancelButton.setVisibility(View.GONE);
//          break;
//        case TIP_TYPE:
//          mTipTextView.setVisibility(View.VISIBLE);
//          mConfirmButton.setVisibility(View.VISIBLE);
//          mProgressFrame.setVisibility(View.GONE);
//          mNormalContainer.setVisibility(View.GONE);
//          mCancelButton.setVisibility(View.GONE);
//          break;
//        case NORMAL_TYPE:
//          mNormalContainer.setVisibility(View.VISIBLE);
//          mTitleTextView.setVisibility(View.VISIBLE);
//          mConfirmButton.setVisibility(View.VISIBLE);
//          mContentTextView.setVisibility(View.VISIBLE);
//          mCancelButton.setVisibility(View.VISIBLE);
//          mProgressFrame.setVisibility(View.GONE);
//          mTipTextView.setVisibility(View.GONE);
//          break;
//      }
//      if (!fromCreate) {
//        playAnimation();
//      }
//    }
//  }
//
//  public int getAlerType() {
//    return mAlertType;
//  }
//
//  public void changeAlertType(int alertType) {
//    changeAlertType(alertType, false);
//  }
//
//  public TipDialog setTipText(String text) {
//    mTipText = text;
//    LogUtils.i("setTipText , mTipTextView : " + mTipTextView + " , text : " + text);
//    if (mTipTextView != null && mTipText != null) {
//      showTipText(true);
//      mTipTextView.setText(mTipText);
//    }
//    return this;
//  }
//
//  public String getTitleText() {
//    return mTitleText;
//  }
//
//  public TipDialog setTitleText(String text) {
//    mTitleText = text;
//    if (mTitleTextView != null && mTitleText != null) {
//      showTitleText(true);
//      mTitleTextView.setText(mTitleText);
//    }
//    return this;
//  }
//
//  public String getContentText() {
//    return mContentText;
//  }
//
//  public TipDialog setContentText(String text) {
//    mContentText = text;
//    if (mContentTextView != null && mContentText != null) {
//      showContentText(true);
//      mContentTextView.setText(mContentText);
//    }
//    return this;
//  }
//
//  public boolean isShowCancelButton() {
//    return mShowCancel;
//  }
//
//  public TipDialog showCancelButton(boolean isShow) {
//    mShowCancel = isShow;
//    if (mCancelButton != null) {
//      mCancelButton.setVisibility(mShowCancel ? View.VISIBLE : View.GONE);
//    }
//    return this;
//  }
//
//  public boolean isShowContentText() {
//    return mShowContent;
//  }
//
//  public TipDialog showTipText(boolean isShow) {
//    mShowTip = isShow;
//    if (mTipTextView != null) {
//      mTipTextView.setVisibility(mShowTip ? View.VISIBLE : View.GONE);
//    }
//    return this;
//  }
//
//  public TipDialog showTitleText(boolean isShow) {
//    mShowTitle = isShow;
//    if (mTitleTextView != null) {
//      mTitleTextView.setVisibility(mShowTitle ? View.VISIBLE : View.GONE);
//    }
//    return this;
//  }
//
//  public TipDialog showContentText(boolean isShow) {
//    mShowContent = isShow;
//    if (mContentTextView != null) {
//      mContentTextView.setVisibility(mShowContent ? View.VISIBLE : View.GONE);
//    }
//    return this;
//  }
//
//  public String getCancelText() {
//    return mCancelText;
//  }
//
//  public TipDialog setCancelText(String text) {
//    mCancelText = text;
//    if (mCancelButton != null && mCancelText != null) {
//      showCancelButton(true);
//      mCancelButton.setText(mCancelText);
//    }
//    return this;
//  }
//
//  public String getConfirmText() {
//    return mConfirmText;
//  }
//
//  public TipDialog setConfirmText(String text) {
//    mConfirmText = text;
//    if (mConfirmButton != null && mConfirmText != null) {
//      mConfirmButton.setText(mConfirmText);
//    }
//    return this;
//  }
//
//  public TipDialog setCancelClickListener(OnSweetClickListener listener) {
//    mCancelClickListener = listener;
//    return this;
//  }
//
//  public TipDialog setConfirmClickListener(OnSweetClickListener listener) {
//    mConfirmClickListener = listener;
//    return this;
//  }
//
//  @Override protected void onStart() {
//    mDialogView.startAnimation(mModalInAnim);
//    //playAnimation();
//  }
//
//  /**
//   * The real Dialog.cancel() will be invoked async-ly after the animation finishes.
//   */
//  @Override public void cancel() {
//    dismissWithAnimation(true);
//  }
//
//  /**
//   * The real Dialog.dismiss() will be invoked async-ly after the animation finishes.
//   */
//  public void dismissWithAnimation() {
//    dismissWithAnimation(false);
//  }
//
//  private void dismissWithAnimation(boolean fromCancel) {
//    mCloseFromCancel = fromCancel;
//    mConfirmButton.startAnimation(mOverlayOutAnim);
//    mDialogView.startAnimation(mModalOutAnim);
//  }
//
//  @Override public void onClick(View v) {
//    if (v.getId() == com.xinhua.dialoglib.R.id.cancel_button) {
//      if (mCancelClickListener != null) {
//        mCancelClickListener.onClick(TipDialog.this);
//      } else {
//        dismissWithAnimation();
//      }
//    } else if (v.getId() == com.xinhua.dialoglib.R.id.confirm_button) {
//      if (mConfirmClickListener != null) {
//        mConfirmClickListener.onClick(TipDialog.this);
//      } else {
//        dismissWithAnimation();
//      }
//    }
//  }
//
//  public ProgressHelper getProgressHelper() {
//    return mProgressHelper;
//  }
//}