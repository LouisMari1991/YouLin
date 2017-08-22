package youlin.xinhua.com.youlin.activity.yeweihui;

import android.content.Context;
import android.content.Intent;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : 完善信息
 * author : 罗顺翔
 * time   : 2017-08-22 20:13
 * version: 1.0
 * </pre>
 */

public class PerfectInformationActivity extends BaseActivity {

  public static void launch(Context context) {
    Intent intent = new Intent(context, PerfectInformationActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_committee_perfect_information;
  }
}
