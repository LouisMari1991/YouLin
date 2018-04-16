package youlin.xinhua.com.youlin.office;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import java.util.ArrayList;
import java.util.List;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.adapter.ImagesAdapter;
import youlin.xinhua.com.youlin.base.BaseActivity;
import youlin.xinhua.com.youlin.utils.AppUtils;
import youlin.xinhua.com.youlin.utils.RecyclerViewHelper;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/04/13
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class OfficeMainActivity extends BaseActivity {

  public static void start(Context context) {
    Intent starter = new Intent(context, OfficeMainActivity.class);
    context.startActivity(starter);
  }

  @BindView(R.id.rv_list) RecyclerView recyclerView;

  @Override protected int attachLayoutRes() {
    return R.layout.activity_office_main;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ImagesAdapter adapter = new ImagesAdapter(this, R.layout.item_image_picker);

    RecyclerViewHelper.initImagePicker(recyclerView, adapter);

    List<String> strings = new ArrayList<>();

    strings.add("http://www.taopic.com/uploads/allimg/140320/235013-14032020515270.jpg");
    strings.add("http://f0.topitme.com/0/7a/63/113144393585b637a0o.jpg");
    strings.add("http://a3.topitme.com/1/21/79/1128833621e7779211o.jpg");
    strings.add("http://f9.topitme.com/9/37/30/11224703137bb30379o.jpg");
    strings.add("http://img.taopic.com/uploads/allimg/121119/240509-1211191U15232.jpg");
    strings.add("http://pic17.nipic.com/20111021/8289149_105725398120_2.jpg");
    strings.add("http://pic.qiantucdn.com/58pic/14/35/64/56t58PIC2wJ_1024.jpg");
    strings.add("http://pic23.nipic.com/20120906/5395930_103351439195_2.jpg");

    Context context = this;

    String plusPath = context.getString(R.string.glide_plus_icon_string) + AppUtils.getPackageInfo(
        context).packageName + "/mipmap/" + R.mipmap.icon_yewubanli_add;

    strings.add(plusPath);

    adapter.setNewData(strings);
  }
}
