package youlin.xinhua.com.youlin.widget.recycler_view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/04/16
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class ItemUtil {

  private ItemUtil() {
  }

  /**
   * 判断指定位置的Item是否是最后一列
   *
   * @param parent RecyclerView
   * @param position 位置
   * @param childCount 条目数
   * @param view 当前view
   * @return true为最后一列，反之不是
   */
  public static boolean isLastColumn(RecyclerView parent, int position, int childCount, View view) {

    RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
    if (layoutManager instanceof GridLayoutManager) {
      GridLayoutManager manager = (GridLayoutManager) layoutManager;
      int orientation = manager.getOrientation();
      int spanCount = manager.getSpanCount();
      if (orientation == LinearLayoutManager.VERTICAL) {
        // 判断是否是最后一列
        if ((position + 1) % spanCount == 0) {
          return true;
        }
      } else {
        int remainder = childCount % spanCount;
        childCount = childCount - remainder - (remainder == 0 ? spanCount : 0);
        // 判断方向横是否是最后一列
        if (position >= childCount) {
          return true;
        }
      }
    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
      StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
      int orientation = manager.getOrientation();
      int spanCount = manager.getSpanCount();
      if (orientation == StaggeredGridLayoutManager.VERTICAL) {
        // 判断方向竖是否是最后一列
        final StaggeredGridLayoutManager.LayoutParams slp =
            (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        return slp.getSpanIndex() == spanCount - 1;
      } else {
        int remainder = childCount % spanCount;
        childCount = childCount - remainder - (remainder == 0 ? spanCount : 0);
        // 判断方向横是否是最后一列
        if (position >= childCount) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 判断指定位置的Item是否是最后一列
   *
   * @param position 位置
   * @param childCount 条目数
   * @param spanCount 列数
   * @param isVertical 是否纵向
   * @param isGrid 是否为Grid
   * @param view 当前view
   * @return true为最后一列，反之不是
   */
  public static boolean isLastColumn(int position, int childCount, int spanCount,
      boolean isVertical, boolean isGrid, View view) {

    if (isGrid) {
      if (isVertical) {
        // 判断是否是最后一列
        if ((position + 1) % spanCount == 0) {
          return true;
        }
      } else {
        int remainder = childCount % spanCount;
        childCount = childCount - remainder - (remainder == 0 ? spanCount : 0);
        // 判断方向横是否是最后一列
        if (position >= childCount) {
          return true;
        }
      }
    } else {
      if (isVertical) {
        final StaggeredGridLayoutManager.LayoutParams slp =
            (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        return slp.getSpanIndex() == spanCount - 1;
      } else {
        int remainder = childCount % spanCount;
        childCount = childCount - remainder - (remainder == 0 ? spanCount : 0);
        // 判断方向横是否是最后一列
        if (position >= childCount) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 判断指定位置的Item是否是最后一行
   *
   * @param position 位置
   * @param childCount 条目数
   * @param spanCount 列数
   * @param isVertical 是否纵向
   * @param isGrid 是否grid
   * @param view 当前view
   * @return true为最后一行，反之不是
   */
  public static boolean isLastRaw(int position, int childCount, int spanCount, boolean isVertical,
      boolean isGrid, View view) {
    if (isGrid) {
      if (isVertical) {
        int remainder = childCount % spanCount;
        childCount = childCount - remainder - (remainder == 0 ? spanCount : 0);
        // 判断方向竖是否是最后一行
        if (position >= childCount) {
          return true;
        }
      } else {
        // 判断方向横是否是最后一行
        if ((position + 1) % spanCount == 0) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * 判断指定位置的Item是否是最后一行
   *
   * @param parent RecyclerView
   * @param position 位置
   * @param childCount 条目数
   * @return true为最后一行，反之不是
   */
  public static boolean isLastRaw(RecyclerView parent, int position, int childCount, View view) {

    RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
    if (layoutManager instanceof GridLayoutManager) {
      GridLayoutManager manager = (GridLayoutManager) layoutManager;
      int spanCount = manager.getSpanCount();
      int orientation = manager.getOrientation();
      if (orientation == LinearLayoutManager.VERTICAL) {
        int remainder = childCount % spanCount;
        childCount = childCount - remainder - (remainder == 0 ? spanCount : 0);
        // 判断方向竖是否是最后一行
        if (position >= childCount) {
          return true;
        }
      } else {
        // 判断方向横是否是最后一行
        if ((position + 1) % spanCount == 0) {
          return true;
        }
      }
    }
    //        else if (layoutManager instanceof StaggeredGridLayoutManager) {
    //            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
    //            int orientation = manager.getOrientation();
    //            int spanCount = manager.getSpanCount();
    //            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
    //                childCount = childCount - childCount % spanCount;
    //                // 判断方向竖是否是最后一行
    //                if (position >= childCount) {
    //                    return true;
    //                }
    //            } else {
    //                // 判断方向横是否是最后一行
    //                final StaggeredGridLayoutManager.LayoutParams slp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
    //                return slp.getSpanIndex() == spanCount - 1;
    //            }
    //        }
    return false;
  }

  /**
   * 判断指定位置的Item是否是第一行
   *
   * @param parent recyclerview
   * @param position 位置
   * @return true为第一行，反之不是
   */
  public static boolean isFirstRaw(RecyclerView parent, int position, View view) {

    RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
    if (layoutManager instanceof GridLayoutManager) {
      GridLayoutManager manager = (GridLayoutManager) layoutManager;
      int spanCount = manager.getSpanCount();
      int orientation = manager.getOrientation();
      if (orientation == LinearLayoutManager.VERTICAL) {
        return position / spanCount == 0;
      } else {
        return position % spanCount == 0;
      }
    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
      StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
      int orientation = manager.getOrientation();
      int spanCount = manager.getSpanCount();
      if (orientation == StaggeredGridLayoutManager.VERTICAL) {
        return position / spanCount == 0;
      } else {
        final StaggeredGridLayoutManager.LayoutParams slp =
            (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        return slp.getSpanIndex() == 0;
      }
    }
    return false;
  }

  /**
   * 判断指定位置的Item是否是第一列
   *
   * @param parent RecyclerView
   * @param position 位置
   * @return true为第一列，反之不是
   */
  public static boolean isFirstColumn(RecyclerView parent, int position, View view) {

    RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
    if (layoutManager instanceof GridLayoutManager) {
      GridLayoutManager manager = (GridLayoutManager) layoutManager;
      int orientation = manager.getOrientation();
      int spanCount = manager.getSpanCount();
      if (orientation == LinearLayoutManager.VERTICAL) {
        return position % spanCount == 0;
      } else {
        return position / spanCount == 0;
      }
    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
      StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
      int orientation = manager.getOrientation();
      if (orientation == StaggeredGridLayoutManager.VERTICAL) {
        final StaggeredGridLayoutManager.LayoutParams slp =
            (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        return slp.getSpanIndex() == 0;
      } else {
        int spanCount = manager.getSpanCount();
        return position / spanCount == 0;
      }
    }
    return false;
  }
}
