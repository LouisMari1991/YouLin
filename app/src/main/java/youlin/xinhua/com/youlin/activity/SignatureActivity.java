package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;

/**
 * Description:
 * Author：Mari on 2017-08-13 21:33
 * Contact：289168296@qq.com
 */
public class SignatureActivity extends BaseActivity {

  public static void launch(Context context) {
    Intent intent = new Intent(context, SignatureActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_signture;
  }
}
