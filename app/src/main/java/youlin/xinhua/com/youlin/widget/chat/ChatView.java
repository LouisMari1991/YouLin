//package youlin.xinhua.com.youlin.widget.chat;
//
//import android.content.Context;
//import android.support.v4.view.ViewCompat;
//import android.support.v4.view.animation.FastOutLinearInInterpolator;
//import android.support.v7.widget.RecyclerView;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import youlin.xinhua.com.youlin.R;
//import youlin.xinhua.com.youlin.listener.OnClickEditTextListener;
//import youlin.xinhua.com.youlin.listener.OnMenuClickListener;
//import youlin.xinhua.com.youlin.listener.RecordVoiceListener;
//import youlin.xinhua.com.youlin.widget.MeetOperationView;
//import youlin.xinhua.com.youlin.widget.chat.chatinput.ChatInputView;
//import youlin.xinhua.com.youlin.widget.chat.record.RecordVoiceButton;
//
//import static youlin.xinhua.com.youlin.widget.chat.chatinput.ChatInputView.KEYBOARD_STATE_HIDE;
//import static youlin.xinhua.com.youlin.widget.chat.chatinput.ChatInputView.KEYBOARD_STATE_INIT;
//import static youlin.xinhua.com.youlin.widget.chat.chatinput.ChatInputView.KEYBOARD_STATE_SHOW;
//
//public class ChatView extends RelativeLayout {
//
//  //private EaseChatMessageList mMsgList;
//  private RecyclerView messageList;
//  private ChatInputView mChatInput;
//  private LinearLayout mMenuLl;
//  private RecordVoiceButton mRecordVoiceBtn;
//
//  private View addContactsContainer;
//  private TextView textAddContacts;
//  private Button btnAddContacts;
//  private View cancelContacts;
//
//  private Button btnMeetFile;// 公示文件
//  private MeetOperationView mMeetOperationView;// 会议群右下角layout
//
//  private boolean mHasInit;
//  private boolean mHasKeyboard;
//  private int mHeight;
//
//  private OnKeyboardChangedListener mKeyboardListener;
//
//  public ChatView(Context context) {
//    super(context);
//  }
//
//  public ChatView(Context context, AttributeSet attrs) {
//    super(context, attrs);
//  }
//
//  public ChatView(Context context, AttributeSet attrs, int defStyleAttr) {
//    super(context, attrs, defStyleAttr);
//  }
//
//  public void initModule() {
//    mMenuLl = (LinearLayout) findViewById(R.id.aurora_ll_menuitem_container);
//    mChatInput = (ChatInputView) findViewById(R.id.chat_input);
//
//    mRecordVoiceBtn = mChatInput.getRecordVoiceButton();
//
//    mChatInput.setMenuContainerHeight(
//        getContext().getResources().getDimensionPixelSize(R.dimen.menu_container_height));
//
//    messageList = (RecyclerView) findViewById(R.id.message_list);
//
//    initAddContactContainer();
//    initBtnMeetView();
//  }
//
//  /**
//   * 初始化会议群View
//   */
//  private void initBtnMeetView() {
//    btnMeetFile = (Button) findViewById(R.id.btn_meet_file);
//    mMeetOperationView = (MeetOperationView) findViewById(R.id.meet_operation_view);
//  }
//
//  /**
//   * 设置会议群右下角View,并且有会议正在进行
//   */
//  public void showMeetView() {
//    btnMeetFile.setVisibility(View.VISIBLE);
//    mMeetOperationView.setVisibility(VISIBLE);
//  }
//
//  /**
//   * 开始倒计时统计
//   */
//  public void startCountDownTimer(int index, long time) {
//    mMeetOperationView.startTimerCountDown(index, time);
//  }
//
//  /**
//   * 签到人数 or 换届选择总票总数
//   *
//   * @param type 0: 签到状态 -> 已签到%s人 1: 投票结果公示 -> 总票数: %s, 2,3: 投票中, 投票公示: 同意票 %s, 否决票 %s
//   */
//  public void setMeetOperationViewContentText(int type, String... params) {
//    mMeetOperationView.setContentText(type, params);
//  }
//
//  /**
//   * listview 滑动监听, 设置透明度
//   */
//  public void changMeetOperationViewState(int state) {
//    if (mMeetOperationView.getVisibility() == View.GONE) {
//      return;
//    }
//    if (state == 0) { // 停止滑动
//      ViewCompat.animate(mMeetOperationView).alpha(1.0f).setDuration(500).start();
//    } else { //正在滑动
//      ViewCompat.animate(mMeetOperationView).alpha(0.1f).setDuration(500).start();
//    }
//  }
//
//  /**
//   * 　右下角layout点击事件
//   */
//  public void setMeetOperationViewOnClickListener(
//      MeetOperationView.MeetOperationViewOnClickListener l) {
//    mMeetOperationView.setMeetOperationViewOnClickListener(l);
//  }
//
//  /**
//   * 查看群聊文件
//   */
//  public void setBtnMeetFileOnClickListener(OnClickListener l) {
//    btnMeetFile.setOnClickListener(l);
//  }
//
//  private void initAddContactContainer() {
//    addContactsContainer = findViewById(R.id.rl_add_contacts);
//    textAddContacts = (TextView) findViewById(R.id.text_add_contacts);
//    btnAddContacts = (Button) findViewById(R.id.btn_add_contacts);
//    cancelContacts = findViewById(R.id.img_cancel);
//    cancelContacts.setOnClickListener(new OnClickListener() {
//      @Override public void onClick(View v) {
//        ViewCompat.animate(addContactsContainer)
//            .alpha(0)
//            .setInterpolator(new FastOutLinearInInterpolator())
//            .setDuration(200)
//            .start();
//      }
//    });
//  }
//
//  public void showAddContactContainer(String fromUserName) {
//    addContactsContainer.setVisibility(View.VISIBLE);
//    getResources().getString(R.string.tip_add_contacts, fromUserName);
//  }
//
//  public void setAddContactsBtnListener(OnClickListener listener) {
//    btnAddContacts.setOnClickListener(listener);
//  }
//
//  public void setMenuClickListener(OnMenuClickListener listener) {
//    mChatInput.setMenuClickListener(listener);
//  }
//
//  public void setRecordVoiceFile(String path, String fileName) {
//    mRecordVoiceBtn.setVoiceFilePath(path, fileName);
//  }
//
//  public void setRecordVoiceListener(RecordVoiceListener listener) {
//    mRecordVoiceBtn.setRecordVoiceListener(listener);
//  }
//
//  public void setKeyboardChangedListener(OnKeyboardChangedListener listener) {
//    mKeyboardListener = listener;
//  }
//
//  public void setOnTouchListener(OnTouchListener listener) {
//    messageList.setOnTouchListener(listener);
//  }
//
//  public void setOnTouchEditTextListener(OnClickEditTextListener listener) {
//    mChatInput.setOnClickEditTextListener(listener);
//  }
//
//  @Override public boolean performClick() {
//    super.performClick();
//    return true;
//  }
//
//  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//    super.onSizeChanged(w, h, oldw, oldh);
//    if (oldh - h > 300) {
//      setMenuHeight(oldh - h);
//    }
//  }
//
//  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
//    super.onLayout(changed, l, t, r, b);
//    if (!mHasInit) {
//      mHasInit = true;
//      mHeight = b;
//      if (null != mKeyboardListener) {
//        mKeyboardListener.onKeyBoardStateChanged(KEYBOARD_STATE_INIT);
//      }
//    } else {
//      if (null != mKeyboardListener) {
//        mKeyboardListener.onKeyBoardStateChanged(KEYBOARD_STATE_INIT);
//      }
//      mHeight = mHeight < b ? b : mHeight;
//    }
//    if (mHasInit && mHeight > b) {
//      mHasKeyboard = true;
//      if (null != mKeyboardListener) {
//        mKeyboardListener.onKeyBoardStateChanged(KEYBOARD_STATE_SHOW);
//      }
//    }
//    if (mHasInit && mHasKeyboard && mHeight == b) {
//      mHasKeyboard = false;
//      if (null != mKeyboardListener) {
//        mKeyboardListener.onKeyBoardStateChanged(KEYBOARD_STATE_HIDE);
//      }
//    }
//  }
//
//  public ChatInputView getChatInputView() {
//    return mChatInput;
//  }
//
//  public RecyclerView getMessageListView() {
//    return messageList;
//  }
//
//  public void setMenuHeight(int height) {
//    mChatInput.setMenuContainerHeight(height);
//  }
//
//  /**
//   * Keyboard status changed will invoke onKeyBoardStateChanged
//   */
//  public interface OnKeyboardChangedListener {
//
//    /**
//     * Soft keyboard status changed will invoke this callback, use this callback to do you logic.
//     *
//     * @param state Three states: init, show, hide.
//     */
//    void onKeyBoardStateChanged(int state);
//  }
//}
