package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * Description:
 * Author：Mari on 2017-07-31 23:08
 * Contact：289168296@qq.com
 */
public class MyNestedScrollView extends NestedScrollView {

  private ScrollInterface scrollInterface;

  public MyNestedScrollView(Context context) {
    super(context);
  }

  public MyNestedScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    if (scrollInterface != null) {
      scrollInterface.onScrollChange(l, t, oldl, oldt);
    }
    super.onScrollChanged(l, t, oldl, oldt);
  }

  /**
   * 定义滑动接口
   */
  public interface ScrollInterface {
    void onScrollChange(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
  }

  public void setOnMyScrollChangeListener(ScrollInterface t) {
    this.scrollInterface = t;
  }
}
