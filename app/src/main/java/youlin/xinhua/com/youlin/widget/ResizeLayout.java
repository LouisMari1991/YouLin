package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-22 14:31
 * version: 1.0
 * </pre>
 */

public class ResizeLayout extends RelativeLayout {

  public static final byte KEYBOARD_STATE_SHOW = -3;
  public static final byte KEYBOARD_STATE_HIDE = -2;
  public static final byte KEYBOARD_STATE_INIT = -1;

  private OnKeyboardChangedListener mKeyboardListener;

  private boolean mHasInit;
  private boolean mHasKeyboard;
  private int     mHeight;

  public ResizeLayout(Context context) {
    this(context, null);
  }

  public ResizeLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ResizeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {

  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    super.onLayout(changed, l, t, r, b);
    if (!mHasInit) {
      mHasInit = true;
      mHeight = b;
      if (null != mKeyboardListener) {
        mKeyboardListener.onKeyBoardStateChanged(KEYBOARD_STATE_INIT);
      }
    } else {
      mHeight = mHeight < b ? b : mHeight;
    }
    if (mHasInit && mHeight > b) {
      mHasKeyboard = true;
      if (null != mKeyboardListener) {
        mKeyboardListener.onKeyBoardStateChanged(KEYBOARD_STATE_SHOW);
      }
    }
    if (mHasInit && mHasKeyboard && mHeight == b) {
      mHasKeyboard = false;
      if (null != mKeyboardListener) {
        mKeyboardListener.onKeyBoardStateChanged(KEYBOARD_STATE_HIDE);
      }
    }
  }

  public void setKeyboardChangedListener(OnKeyboardChangedListener listener) {
    mKeyboardListener = listener;
  }

  /**
   * Keyboard status changed will invoke onKeyBoardStateChanged
   */
  public interface OnKeyboardChangedListener {

    /**
     * Soft keyboard status changed will invoke this callback, use this callback to do you logic.
     *
     * @param state Three states: init, show, hide.
     */
    void onKeyBoardStateChanged(int state);
  }
}
