package youlin.xinhua.com.youlin.activity.share;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.ShareUtils2;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-01 09:12
 * version: 1.0
 * </pre>
 */

public class SharePopActivity extends BaseActivity {

  @BindView(R.id.btn_share_pop) Button mButton;

  public static void launch(Context context) {
    Intent intent = new Intent(context, SharePopActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_share_pop;
  }

  @OnClick(R.id.btn_share_pop) public void click() {
    new ShareUtils2().shareWeb(this, mButton);
    //PopUtil.createPopupWind(this, mButton, R.layout.layout_pop_share,
    //    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM,
    //    new PopUtil.PopInitListener() {
    //      @Override public void popInit(BaseViewHolder holder, PopupWindow popupWindow) {
    //
    //      }
    //    });
  }
}
