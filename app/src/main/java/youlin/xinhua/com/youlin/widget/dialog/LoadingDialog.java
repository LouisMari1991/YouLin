package youlin.xinhua.com.youlin.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import butterknife.ButterKnife;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-11 10:42
 * version: 1.0
 * </pre>
 */

public class LoadingDialog extends Dialog {

  public LoadingDialog(@NonNull Context context) {
    super(context, com.xinhua.dialoglib.R.style.alert_dialog);
    setCancelable(false);
    setCanceledOnTouchOutside(false);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dialog_loading);
    ButterKnife.bind(this);
  }
}
