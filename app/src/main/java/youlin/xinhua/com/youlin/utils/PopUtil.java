package youlin.xinhua.com.youlin.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.xinhua.recycler.adapter.BaseViewHolder;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc:PopWindow
 * author:TanXueMei
 * time:2017/5/20
 * version:1.0
 * </pre>
 */

public class PopUtil {

  /**
   * @param view PopupWindow显示
   * @param product_popupwid PopupWindow界面id
   * @param width PopupWindow宽
   * @param height PopupWindow高
   * @param gravity PopupWindow显示位置
   * @param popInitListener PopupWindow界面初始化回调
   */
  public static void createPopupWind(final Activity activity, final View view, int product_popupwid,
      int width, int height, int gravity, PopInitListener popInitListener) {
    View contentView = View.inflate(view.getContext(), product_popupwid, null);
    PopupWindow window = new PopupWindow(contentView, width, height);
    WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
    attributes.alpha = 0.4f;
    activity.getWindow().setAttributes(attributes);
    window.setFocusable(true);// 获得焦点
    window.setBackgroundDrawable(new ColorDrawable());
    window.setTouchable(true);
    window.setAnimationStyle(R.style.popAnimtion);
    window.setOnDismissListener(new PopupWindow.OnDismissListener() {
      @Override public void onDismiss() {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.alpha = 1.0f;
        activity.getWindow().setAttributes(attributes);
      }
    });
    window.showAtLocation(view, gravity, 0, 0);
    BaseViewHolder baseViewHolder = new BaseViewHolder(contentView);
    popInitListener.popInit(baseViewHolder, window);
  }

  /**
   * @param animationStyle 动画样式
   */
  public static PopupWindow createPopupWindIndex(final View view, int product_popupwid, int width,
      int height, int animationStyle, PopInitListener popInitListener) {
    View contentView = View.inflate(view.getContext(), product_popupwid, null);
    PopupWindow window = new PopupWindow(contentView, width, height);
    window.setFocusable(true);// 获得焦点
    window.setBackgroundDrawable(new ColorDrawable());
    window.setTouchable(true);
    window.setAnimationStyle(animationStyle);
    int showX = (int) (view.getX() + view.getWidth() / 2);
    int showY = (int) (view.getY() + view.getHeight() / 2);
    BaseViewHolder baseViewHolder = new BaseViewHolder(contentView);
    popInitListener.popInit(baseViewHolder, window);
    return window;
  }

  public interface PopInitListener {
    void popInit(BaseViewHolder holder, PopupWindow popupWindow);
  }
}
