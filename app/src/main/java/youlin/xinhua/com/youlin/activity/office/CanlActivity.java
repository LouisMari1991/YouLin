package youlin.xinhua.com.youlin.activity.office;

import android.content.Context;
import android.content.Intent;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.base.BaseActivity;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/05/07
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class CanlActivity extends BaseActivity {

  public static void start(Context context) {
    Intent starter = new Intent(context, CanlActivity.class);
    context.startActivity(starter);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_office_canl;
  }
}
