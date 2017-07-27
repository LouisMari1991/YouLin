package youlin.xinhua.com.youlin;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-13 14:42
 * version: 1.0
 * </pre>
 */

public abstract class BaseActivity extends AppCompatActivity {

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(attachLayoutRes());
    ButterKnife.bind(this);
  }

  protected abstract @LayoutRes int attachLayoutRes();
}
