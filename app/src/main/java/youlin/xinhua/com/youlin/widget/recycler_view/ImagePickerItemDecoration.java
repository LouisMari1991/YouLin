package youlin.xinhua.com.youlin.widget.recycler_view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
public class ImagePickerItemDecoration extends RecyclerView.ItemDecoration {

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {

    int childCount = parent.getAdapter().getItemCount();
    int viewLayoutPosition =
        ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

    boolean firstRaw = ItemUtil.isFirstRaw(parent, viewLayoutPosition, view);
    boolean lastRaw = ItemUtil.isLastRaw(parent, viewLayoutPosition, childCount, view);
    boolean firstColumn = ItemUtil.isFirstColumn(parent, viewLayoutPosition, view);
    boolean lastColumn = ItemUtil.isLastColumn(parent, viewLayoutPosition, childCount, view);

    int left;
    int top;
    int right;
    int bottom;

    if (firstRaw && lastRaw) {
      top = MeasureUtils.dp2px(15);
      bottom = MeasureUtils.dp2px(15);
    } else if (firstRaw) {
      top = MeasureUtils.dp2px(15);
      bottom = MeasureUtils.dp2px(1.5f);
    } else if (lastRaw) {
      top = MeasureUtils.dp2px(1.5f);
      bottom = MeasureUtils.dp2px(15);
    } else {
      top = MeasureUtils.dp2px(1.5f);
      bottom = MeasureUtils.dp2px(1.5f);
    }

    if (firstColumn && lastColumn) {
      left = MeasureUtils.dp2px(10);
      right = MeasureUtils.dp2px(10);
    } else if (firstColumn) {
      left = MeasureUtils.dp2px(10);
      right = MeasureUtils.dp2px(1.5f);
    } else if (lastColumn) {
      left = MeasureUtils.dp2px(1.5f);
      right = MeasureUtils.dp2px(10);
    } else {
      left = MeasureUtils.dp2px(1.5f);
      right = MeasureUtils.dp2px(1.5f);
    }

    outRect.set(left, top, right, bottom);
  }
}
