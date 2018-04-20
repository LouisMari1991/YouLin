package youlin.xinhua.com.youlin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.AppUtils;
import youlin.xinhua.com.youlin.utils.ImageLoader;
import youlin.xinhua.com.youlin.utils.ListUtils;
import youlin.xinhua.com.youlin.utils.MeasureUtils;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/04/16
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class ImagesAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

  int itemHeight;

  private static final String FIX_RES = "android.resource://";
  private String res;

  public ImagesAdapter(Context context) {
    super(R.layout.item_image_picker, null);
    int screenWidth = MeasureUtils.getScreenWidth();
    itemHeight = (screenWidth - MeasureUtils.dp2px(44)) / 3;
    res = FIX_RES
        + AppUtils.getPackageInfo(context).packageName
        + "/mipmap/"
        + R.mipmap.icon_yewubanli_add;
    if (mData == null) {
      mData = new ArrayList<>();
    }
    mData.add(res);
  }

  @Override protected void convert(BaseViewHolder helper, String item) {

    ImageView imageView = helper.getView(R.id.img);

    if (item.startsWith(FIX_RES)) {
      helper.setGone(R.id.img_del, false);
    } else {
      helper.setGone(R.id.img_del, true);
    }

    helper.addOnClickListener(R.id.img_del);

    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
    layoutParams.height = itemHeight;
    imageView.setLayoutParams(layoutParams);

    ImageLoader.displayFadeImage(1, item, imageView);
  }

  @Override public void onViewAttachedToWindow(BaseViewHolder holder) {
    super.onViewAttachedToWindow(holder);
  }

  @Override public void addData(@NonNull String data) {
    checkData();
    super.addData(mData.size() == 0 ? 0 : mData.size() - 1, data);
  }

  @Override public void addData(@NonNull Collection<? extends String> newData) {

    int sum = mData.size() + newData.size();
    if (sum > 9) {
      mData.remove(mData.size() - 1);
    }
    super.addData(mData.size() == 0 ? 0 : mData.size() - 1, newData);
  }

  public List<String> getImages() {
    List<String> result = new ArrayList<>();
    for (String item : mData) {
      if (!item.startsWith(FIX_RES)) {
        result.add(item);
      }
    }
    return result;
  }

  private void checkData() {
    if (mData.size() == 8 && hasFixRes()) {
      mData.remove(mData.size() - 1);
    }
  }

  public boolean hasFixRes() {
    boolean result = false;
    if (!ListUtils.isEmpty(mData)) {
      String item = mData.get(mData.size() - 1);
      if (item.startsWith(FIX_RES)) {
        result = true;
      }
    }
    return result;
  }

  @Override public void remove(int position) {
    mData.remove(position);
    if (!hasFixRes()) {
      mData.add(res);
    }
    notifyDataSetChanged();
  }
}
