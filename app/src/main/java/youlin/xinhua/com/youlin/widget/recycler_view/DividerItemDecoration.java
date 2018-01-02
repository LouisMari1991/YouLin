package youlin.xinhua.com.youlin.widget.recycler_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/01/02
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

  Drawable drawable;
  boolean hasHeadGap;

  public DividerItemDecoration(Context context) {
    this(context, false);
  }

  public DividerItemDecoration(Context context, boolean hasHeadGap) {
    drawable = ContextCompat.getDrawable(context, R.drawable.shape_item_gap);
    this.hasHeadGap = hasHeadGap;
  }

  @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    final int left = parent.getPaddingLeft();
    final int right = parent.getWidth() - parent.getPaddingRight();
    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
      final int top =
          child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
      final int bottom = top + drawable.getIntrinsicHeight();
      drawable.setBounds(left, top, right, bottom);
      drawable.draw(c);
    }
  }

  /**
   * getItemOffsets 中为 outRect 设置的4个方向的值，将被计算进所有 decoration 的尺寸中，
   * 而这个尺寸，被用来计算 RecyclerView 每个 item
   * view 的大小（包括 item view 的宽高，padding，以及这个 insets）
   */
  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {

    if (hasHeadGap) {
      int viewLayoutPosition =
          ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

      if (viewLayoutPosition == 0) {

      } else {

      }
    } else {
      outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
    }
  }
}
