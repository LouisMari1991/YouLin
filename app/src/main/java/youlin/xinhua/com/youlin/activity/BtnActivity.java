package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-13 14:37
 * version: 1.0
 * </pre>
 */

public class BtnActivity extends BaseActivity {

  public static void lunch(Context context) {
    Intent intent = new Intent(context, BtnActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_btn;
  }

  @BindView(R.id.yellow)
  Button yel;
  @BindView(R.id.blue)
  Button blue;

  @OnClick({ R.id.blue, R.id.yellow }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.blue:
        if (yel.isEnabled()) {
          yel.setEnabled(false);
        } else {
          yel.setEnabled(true);
        }
        break;
      case R.id.yellow:
        if (blue.isEnabled()) {
          blue.setEnabled(false);
        } else {
          blue.setEnabled(true);
        }
        break;
    }
  }
}
