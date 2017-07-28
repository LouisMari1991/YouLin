package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import butterknife.BindView;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.widget.ChatInputView;
import youlin.xinhua.com.youlin.widget.ChatView;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-24 15:06
 * version: 1.0
 * </pre>
 */

public class ChatActivity extends BaseActivity
    implements View.OnTouchListener, ChatView.OnKeyboardChangedListener {

  public static void lunch(Context context) {
    Intent intent = new Intent(context, ChatActivity.class);
    context.startActivity(intent);
  }

  @BindView(R.id.chat_view) ChatView mChatView;

  private InputMethodManager mImm;
  private Window             mWindow;

  @Override protected int attachLayoutRes() {
    return R.layout.activity_im_chat;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    mWindow = getWindow();

    mChatView.initModule();
    mChatView.setOnTouchListener(this);
    mChatView.setKeyboardChangedListener(this);

  }

  @Override public boolean onTouch(View view, MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        ChatInputView chatInputView = mChatView.getChatInputView();

        if (view.getId() == chatInputView.getInputView().getId()) {

          if (chatInputView.getMenuState() == View.VISIBLE && !chatInputView.getSoftInputState()) {
            chatInputView.dismissMenuAndResetSoftMode();
            return false;
          } else {
            return false;
          }
        }
        if (chatInputView.getMenuState() == View.VISIBLE) {
          chatInputView.dismissMenuLayout();
        }
        if (chatInputView.getSoftInputState()) {
          View v = getCurrentFocus();

          if (mImm != null && v != null) {
            mImm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            chatInputView.setSoftInputState(false);
          }
        }
        break;
    }
    return false;
  }

  @Override public void onKeyBoardStateChanged(int state) {
    switch (state) {
      case ChatInputView.KEYBOARD_STATE_INIT:
        ChatInputView chatInputView = mChatView.getChatInputView();
        if (mImm != null) {
          mImm.isActive();
        }
        if (chatInputView.getMenuState() == View.INVISIBLE || (!chatInputView.getSoftInputState()
            && chatInputView.getMenuState() == View.GONE)) {

          mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
              | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          chatInputView.dismissMenuLayout();
        }
        break;
    }
  }
}
