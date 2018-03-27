package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import youlin.xinhua.com.youlin.base.BaseActivity;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-08-31 19:03
 * version: 1.0
 * </pre>
 */

public class EmptyLayoutActivity extends BaseActivity {

  public static void launch(Context context) {
    Intent intent = new Intent(context, EmptyLayoutActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_empty_layout;
  }
}
