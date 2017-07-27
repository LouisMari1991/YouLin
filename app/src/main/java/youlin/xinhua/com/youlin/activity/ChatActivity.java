package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-24 15:06
 * version: 1.0
 * </pre>
 */

public class ChatActivity extends BaseActivity {

  public static void lunch(Context context) {
    Intent intent = new Intent(context, ChatActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_im_chat;
  }
}
