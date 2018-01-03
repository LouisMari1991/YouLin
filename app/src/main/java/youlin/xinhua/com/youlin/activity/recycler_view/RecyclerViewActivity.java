package youlin.xinhua.com.youlin.activity.recycler_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.adapter.RvAdapter;
import youlin.xinhua.com.youlin.widget.recycler_view.YJDividerItemDecoration;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2017/12/28
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class RecyclerViewActivity extends BaseActivity {

  public static void start(Context context) {
    Intent starter = new Intent(context, RecyclerViewActivity.class);
    context.startActivity(starter);
  }

  @BindView(R.id.recycler_view) RecyclerView recyclerView;

  @Override protected int attachLayoutRes() {
    return R.layout.activity_recycler_view;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    RvAdapter adapter = new RvAdapter();
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    recyclerView.addItemDecoration(new YJDividerItemDecoration(this, true));
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setAdapter(adapter);
  }
}
