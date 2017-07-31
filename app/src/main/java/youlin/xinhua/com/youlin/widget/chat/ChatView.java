package youlin.xinhua.com.youlin.widget.chat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.listener.OnClickEditTextListener;
import youlin.xinhua.com.youlin.listener.OnMenuClickListener;
import youlin.xinhua.com.youlin.listener.RecordVoiceListener;
import youlin.xinhua.com.youlin.widget.chat.chatinput.ChatInputView;
import youlin.xinhua.com.youlin.widget.chat.record.RecordVoiceButton;

import static youlin.xinhua.com.youlin.widget.chat.chatinput.ChatInputView.KEYBOARD_STATE_HIDE;
import static youlin.xinhua.com.youlin.widget.chat.chatinput.ChatInputView.KEYBOARD_STATE_INIT;
import static youlin.xinhua.com.youlin.widget.chat.chatinput.ChatInputView.KEYBOARD_STATE_SHOW;

public class ChatView extends RelativeLayout {

  //private MessageList mMsgList;
  private ChatInputView     mChatInput;
  private LinearLayout      mMenuLl;
  private RecordVoiceButton mRecordVoiceBtn;

  private boolean mHasInit;
  private boolean mHasKeyboard;
  private int     mHeight;

  private OnKeyboardChangedListener mKeyboardListener;

  public ChatView(Context context) {
    super(context);
  }

  public ChatView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ChatView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void initModule() {
    mMenuLl = (LinearLayout) findViewById(R.id.aurora_ll_menuitem_container);
    mChatInput = (ChatInputView) findViewById(R.id.chat_input);

    mRecordVoiceBtn = mChatInput.getRecordVoiceButton();

    mChatInput.setMenuContainerHeight(
        getContext().getResources().getDimensionPixelSize(R.dimen.menu_container_height));

    //mMsgList = (MessageList) findViewById(R.id.msg_list);
    //mMsgList.setHasFixedSize(true);
  }

  public void setMenuClickListener(OnMenuClickListener listener) {
    mChatInput.setMenuClickListener(listener);
  }

  //public void setAdapter(MsgListAdapter adapter) {
  //    mMsgList.setAdapter(adapter);
  //}

  //public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
  //    mMsgList.setLayoutManager(layoutManager);
  //}

  public void setRecordVoiceFile(String path, String fileName) {
    mRecordVoiceBtn.setVoiceFilePath(path, fileName);
  }

  public void setRecordVoiceListener(RecordVoiceListener listener) {
    mRecordVoiceBtn.setRecordVoiceListener(listener);
  }

  public void setKeyboardChangedListener(OnKeyboardChangedListener listener) {
    mKeyboardListener = listener;
  }

  //public void setOnTouchListener(OnTouchListener listener) {
  //    mMsgList.setOnTouchListener(listener);
  //}

  public void setOnTouchEditTextListener(OnClickEditTextListener listener) {
    mChatInput.setOnClickEditTextListener(listener);
  }

  @Override public boolean performClick() {
    super.performClick();
    return true;
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    if (oldh - h > 300) {
      setMenuHeight(oldh - h);
    }
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
      if (null != mKeyboardListener) {
        mKeyboardListener.onKeyBoardStateChanged(KEYBOARD_STATE_INIT);
      }
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

  public ChatInputView getChatInputView() {
    return mChatInput;
  }

  //public MessageList getMessageListView() {
  //    return mMsgList;
  //}

  public void setMenuHeight(int height) {
    mChatInput.setMenuContainerHeight(height);
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
