package youlin.xinhua.com.youlin.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-11 19:49
 * version: 1.0
 * </pre>
 */

public class LoadingProgressDialog extends Dialog {

  @BindView(R.id.text) TextView  mTextView;
  @BindView(R.id.pb)   ImageView mImageView;

  public LoadingProgressDialog(@NonNull Context context) {

    super(context, com.xinhua.dialoglib.R.style.alert_dialog);
    setCancelable(true);
    setCanceledOnTouchOutside(true);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dialog_progress_bar);
    ButterKnife.bind(this);
  }

  @Override public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    AnimationDrawable animationDrawable = (AnimationDrawable) mImageView.getBackground();
    animationDrawable.start();
  }

  public void setText(String text) {
    mTextView.setText(text);
  }
}
