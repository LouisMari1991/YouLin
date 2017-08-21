package youlin.xinhua.com.youlin.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 *     desc   : TO-DO
 *     author : 罗顺翔
 *     time   : 2017-07-03 15:24
 *     version: 1.0
 * </pre>
 */

public class AddJobDialog extends Dialog {

  @BindView(R.id.et_job_name) EditText mEdJobName;
  @BindView(R.id.ll_btn)      View     btnContainer;
  @BindView(R.id.ll_custom)   View     customContainer;

  @OnClick({ R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_custom, R.id.btn_save })
  public void click(View v) {

    if (mDialogClickListener == null) {
      return;
    }
    switch (v.getId()) {
      case R.id.btn_custom: {
        mEdJobName.getText().clear();
        btnContainer.setVisibility(View.GONE);
        customContainer.setVisibility(View.VISIBLE);
        mEdJobName.requestFocus();
        mDialogClickListener.onCustomItemClick();
      }
      break;
      case R.id.btn_save: {
        mDialogClickListener.onDialogSaveClick(mEdJobName.getText().toString().trim());
      }
      break;
      default: {
        mDialogClickListener.onDialogItemClick(((Button) v).getText().toString());
      }
      break;
    }
  }

  public AddJobDialog(@NonNull Context context) {
    super(context, R.style.full_screen_dialog);
    setCancelable(true);
    setCanceledOnTouchOutside(true);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dialog_add_job);
    ButterKnife.bind(this);
  }

  public void restore() {
    mEdJobName.getText().clear();
    btnContainer.setVisibility(View.VISIBLE);
    customContainer.setVisibility(View.GONE);
  }

  private DialogClickListener mDialogClickListener;

  public void setDialogClickListener(DialogClickListener dialogClickListener) {
    mDialogClickListener = dialogClickListener;
  }

  public interface DialogClickListener {
    void onDialogItemClick(String string);

    void onDialogSaveClick(String jobStr);

    void onCustomItemClick();
  }
}
