package youlin.xinhua.com.youlin.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-01 09:54
 * version: 1.0
 * </pre>
 */

public class ShareUtils2 implements View.OnClickListener {

  PopupWindow mPopupWindow;

  public void shareWeb(final Activity activity, View view) {

    View contentView = View.inflate(view.getContext(), R.layout.layout_pop_share, null);
    mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
    WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
    attributes.alpha = 0.4f;
    activity.getWindow().setAttributes(attributes);
    mPopupWindow.setFocusable(true);// 获得焦点
    mPopupWindow.setBackgroundDrawable(new ColorDrawable());
    mPopupWindow.setTouchable(true);
    mPopupWindow.setAnimationStyle(R.style.popAnimtion);
    mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
      @Override public void onDismiss() {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.alpha = 1.0f;
        activity.getWindow().setAttributes(attributes);
      }
    });

    contentView.findViewById(R.id.ll_qq).setOnClickListener(this);
    contentView.findViewById(R.id.ll_qzone).setOnClickListener(this);
    contentView.findViewById(R.id.ll_wechat).setOnClickListener(this);
    contentView.findViewById(R.id.ll_wxcircle).setOnClickListener(this);
    contentView.findViewById(R.id.ll_sina).setOnClickListener(this);
    contentView.findViewById(R.id.btn_cancel).setOnClickListener(this);
    mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
  }

  @Override public void onClick(View v) {
    mPopupWindow.dismiss();
    if (R.id.btn_cancel == v.getId()) {
      return;
    }

    switch (v.getId()) {
      case R.id.ll_qq: {
        ToastUtils.showToast("qq");
      }
      break;
      case R.id.ll_qzone: {
        ToastUtils.showToast("ll_qzone");
      }
      break;
      case R.id.ll_wechat: {
        ToastUtils.showToast("ll_wechat");
      }
      break;
      case R.id.ll_wxcircle: {
        ToastUtils.showToast("ll_wxcircle");
      }
      break;
      case R.id.ll_sina: {
        ToastUtils.showToast("showToast");
      }
      break;
      default:
        break;
    }
  }
}
