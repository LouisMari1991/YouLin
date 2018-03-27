package youlin.xinhua.com.youlin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import butterknife.BindView;
import youlin.xinhua.com.youlin.base.BaseActivity;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-02 15:20
 * version: 1.0
 * </pre>
 */

public class TabLayoutActivity extends BaseActivity {

  @BindView(R.id.tab_layout) TabLayout mTabLayout;

  @Override protected int attachLayoutRes() {
    return R.layout.activity_tab_layout;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }
}
