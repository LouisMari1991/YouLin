package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.adapter.ContactPickAdapter;
import youlin.xinhua.com.youlin.bean.UserInfo;
import youlin.xinhua.com.youlin.utils.LogUtils;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-21 15:10
 * version: 1.0
 * </pre>
 */

public class ContactsPickActivity extends BaseActivity {

  public static void lunch(Context context) {
    Intent intent = new Intent(context, ContactsPickActivity.class);
    context.startActivity(intent);
  }

  @BindView(R.id.rv_list) RecyclerView mRecyclerView;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    List<UserInfo> list = new ArrayList<>();
    for (int i = 0; i < 50; i++) {
      UserInfo userInfo = new UserInfo(UUID.randomUUID().toString());
      list.add(userInfo);
    }

    LogUtils.i(list.toString());

    ContactPickAdapter adapter = new ContactPickAdapter(this, list);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(linearLayoutManager);
    mRecyclerView.setAdapter(adapter);

  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_contact_pick;
  }
}
