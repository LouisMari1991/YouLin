package youlin.xinhua.com.youlin.activity.phonecontacts;

import android.content.Context;
import android.content.Intent;
import youlin.xinhua.com.youlin.BaseActivity;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2017/12/22
 *   desc   : 手机联系人列表
 *   version: 1.0
 * </pre>
 */
public class PhoneContactsListActivity extends BaseActivity {

  public static void start(Context context) {
    Intent starter = new Intent(context, PhoneContactsListActivity.class);
    context.startActivity(starter);
  }

  @Override protected int attachLayoutRes() {
    return 0;
  }
}
