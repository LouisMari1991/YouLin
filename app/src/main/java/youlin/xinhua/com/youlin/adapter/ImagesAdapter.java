package youlin.xinhua.com.youlin.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.ArrayList;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.AppUtils;
import youlin.xinhua.com.youlin.utils.ImageLoader;
import youlin.xinhua.com.youlin.utils.LogUtils;
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

  public ImagesAdapter(Context context, int layoutResId) {
    super(layoutResId);
    int screenWidth = MeasureUtils.getScreenWidth();
    itemHeight = (screenWidth - MeasureUtils.dp2px(44)) / 3;
  }

  @Override protected void convert(BaseViewHolder helper, String item) {

    LogUtils.i("item : " + item);

    ImageView imageView = helper.getView(R.id.img);

    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
    layoutParams.height = itemHeight;
    imageView.setLayoutParams(layoutParams);

    ImageLoader.displayFadeImage(1, item, imageView);
  }

  @Override public void onViewAttachedToWindow(BaseViewHolder holder) {
    super.onViewAttachedToWindow(holder);
  }
}
