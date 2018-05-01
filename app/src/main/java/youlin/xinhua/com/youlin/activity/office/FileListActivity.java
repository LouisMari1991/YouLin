package youlin.xinhua.com.youlin.activity.office;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import sample.mari.com.filelib.FileCategoryHelper;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.base.BaseActivity;
import youlin.xinhua.com.youlin.utils.LogUtils;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/04/28
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class FileListActivity extends BaseActivity {

  public static void start(Context context) {
      Intent starter = new Intent(context, FileListActivity.class);
      context.startActivity(starter);
  }

  FileCategoryHelper fileCategoryHelper;

  @BindView(R.id.rv_list) RecyclerView recyclerView;

  @Override protected int attachLayoutRes() {
    return R.layout.activity_file_list;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    fileCategoryHelper = new FileCategoryHelper(this);

    Cursor query = fileCategoryHelper.query(FileCategoryHelper.Picture, FileCategoryHelper.DATE);

    while (query.moveToNext()) {
      String string = query.getString(FileCategoryHelper.COLUMN_PATH);
      LogUtils.i(string);
    }
  }
}
