package youlin.xinhua.com.youlin.utils;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import youlin.xinhua.com.youlin.widget.recycler_view.ImagePickerItemDecoration;

/**
 * Created by long on 2016/3/30.
 * 视图帮助类
 */
public class RecyclerViewHelper {

  private RecyclerViewHelper() {
    throw new RuntimeException("RecyclerViewHelper cannot be initialized!");
  }

  /**
   * 配置垂直列表RecyclerView
   */
  public static void initRecyclerViewV(Context context, RecyclerView view, boolean isDivided,
      RecyclerView.Adapter adapter) {
    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    //        view.setHasFixedSize(true);
    view.setLayoutManager(layoutManager);
    view.setItemAnimator(new DefaultItemAnimator());
    //if (isDivided) {
    //  view.addItemDecoration(
    //      new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
    //}
    view.setAdapter(adapter);
  }

  /**
   * 配置垂直列表RecyclerView
   */
  public static void initRecyclerViewV(Context context, RecyclerView view,
      RecyclerView.Adapter adapter, RecyclerView.ItemDecoration itemDecoration) {
    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    view.setLayoutManager(layoutManager);
    view.setItemAnimator(new DefaultItemAnimator());
    view.addItemDecoration(itemDecoration);
    view.setAdapter(adapter);
  }

  public static void initRecyclerViewV(Context context, RecyclerView view,
      RecyclerView.Adapter adapter) {
    initRecyclerViewV(context, view, false, adapter);
  }

  public static void initImagePicker(RecyclerView recyclerView, BaseQuickAdapter adapter) {
    GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 3);
    recyclerView.setLayoutManager(gridLayoutManager);
    recyclerView.addItemDecoration(new ImagePickerItemDecoration());
    recyclerView.setAdapter(adapter);
    adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
      @Override public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        adapter.remove(position);
      }
    });
  }

  //public static void initRecyclerViewV(Context context, RecyclerView view, boolean isDivided,
  //    BaseQuickAdapter adapter, OnRequestDataListener listener) {
  //  initRecyclerViewV(context, view, isDivided, adapter);
  //  adapter.setRequestDataListener(listener);
  //}

  //public static void initRecyclerViewV(Context context, RecyclerView view, BaseQuickAdapter adapter,
  //    OnRequestDataListener listener) {
  //  initRecyclerViewV(context, view, false, adapter, listener);
  //}

  /**
   * 配置水平列表RecyclerView
   */
  //public static void initRecyclerViewH(Context context, RecyclerView view, boolean isDivided,
  //    RecyclerView.Adapter adapter) {
  //  LinearLayoutManager layoutManager = new LinearLayoutManager(context);
  //  layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
  //  view.setLayoutManager(layoutManager);
  //  view.setItemAnimator(new DefaultItemAnimator());
  //  if (isDivided) {
  //    view.addItemDecoration(
  //        new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL_LIST));
  //  }
  //  view.setAdapter(adapter);
  //}

  //public static void initRecyclerViewH(Context context, RecyclerView view,
  //    RecyclerView.Adapter adapter) {
  //  initRecyclerViewH(context, view, false, adapter);
  //}

  ///**
  // * 配置网格列表RecyclerView
  // */
  //public static void initRecyclerViewG(Context context, RecyclerView view, boolean isDivided,
  //    RecyclerView.Adapter adapter, int column) {
  //  GridLayoutManager layoutManager =
  //      new GridLayoutManager(context, column, LinearLayoutManager.VERTICAL, false);
  //  view.setLayoutManager(layoutManager);
  //  view.setItemAnimator(new DefaultItemAnimator());
  //  if (isDivided) {
  //    view.addItemDecoration(new DividerGridItemDecoration(context));
  //  }
  //  view.setAdapter(adapter);
  //}

  //public static void initRecyclerViewG(Context context, RecyclerView view,
  //    RecyclerView.Adapter adapter, int column) {
  //  initRecyclerViewG(context, view, false, adapter, column);
  //}

  ///**
  // * 配置瀑布流列表RecyclerView
  // */
  //public static void initRecyclerViewSV(Context context, RecyclerView view, boolean isDivided,
  //    RecyclerView.Adapter adapter, int column) {
  //  StaggeredGridLayoutManager layoutManager =
  //      new StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL);
  //  view.setLayoutManager(layoutManager);
  //  view.setItemAnimator(new DefaultItemAnimator());
  //  if (isDivided) {
  //    view.addItemDecoration(
  //        new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
  //  }
  //  view.setAdapter(adapter);
  //}

  //public static void initRecyclerViewSV(Context context, RecyclerView view,
  //    RecyclerView.Adapter adapter, int column) {
  //  initRecyclerViewSV(context, view, false, adapter, column);
  //}

  ///**
  // * 启动拖拽和滑动
  // *
  // * @param view 视图
  // * @param adapter 适配器
  // */
  //public static void startDragAndSwipe(RecyclerView view, BaseQuickAdapter adapter) {
  //  SimpleItemTouchHelperCallback callback = new SimpleItemTouchHelperCallback(adapter);
  //  final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
  //  itemTouchHelper.attachToRecyclerView(view);
  //  adapter.setDragStartListener(new OnStartDragListener() {
  //    @Override public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
  //      itemTouchHelper.startDrag(viewHolder);
  //    }
  //  });
  //  adapter.setDragCallback(callback);
  //  adapter.setDragColor(Color.LTGRAY);
  //}

  ///**
  // * 启动拖拽和滑动
  // *
  // * @param view 视图
  // * @param adapter 适配器
  // * @param dragFixCount 固定数量
  // */
  //public static void startDragAndSwipe(RecyclerView view, BaseQuickAdapter adapter,
  //    int dragFixCount) {
  //  startDragAndSwipe(view, adapter);
  //  adapter.setDragFixCount(dragFixCount);
  //}

  ///**
  // * 启动拖拽和滑动
  // *
  // * @param view 视图
  // * @param adapter 适配器
  // * @param dragFixCount 固定数量
  // * @param fixColor 固定背景色
  // */
  //public static void startDragAndSwipe(RecyclerView view, BaseQuickAdapter adapter,
  //    int dragFixCount, int fixColor) {
  //  startDragAndSwipe(view, adapter, dragFixCount);
  //  adapter.setDragFixDrawable(fixColor);
  //}

  ///**
  // * 启动拖拽和滑动
  // *
  // * @param view 视图
  // * @param adapter 适配器
  // * @param dragFixCount 固定数量
  // * @param fixDrawable 固定背景色
  // */
  //public static void startDragAndSwipe(RecyclerView view, BaseQuickAdapter adapter,
  //    int dragFixCount, Drawable fixDrawable) {
  //  startDragAndSwipe(view, adapter, dragFixCount);
  //  adapter.setDragFixDrawable(fixDrawable);
  //}
}
