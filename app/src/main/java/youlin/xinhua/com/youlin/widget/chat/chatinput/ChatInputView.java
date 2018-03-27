//package youlin.xinhua.com.youlin.widget.chat.chatinput;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.os.SystemClock;
//import android.support.v4.content.ContextCompat;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.AttributeSet;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.Chronometer;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import java.io.File;
//import java.io.FileDescriptor;
//import java.io.FileInputStream;
//import java.io.IOException;
//import youlin.xinhua.com.youlin.R;
//import youlin.xinhua.com.youlin.listener.OnClickEditTextListener;
//import youlin.xinhua.com.youlin.listener.OnFileSelectedListener;
//import youlin.xinhua.com.youlin.listener.OnMenuClickListener;
//import youlin.xinhua.com.youlin.utils.LogUtils;
//import youlin.xinhua.com.youlin.widget.CheckableView;
//import youlin.xinhua.com.youlin.widget.chat.emoj.SelectEmojView;
//import youlin.xinhua.com.youlin.widget.chat.photo.SelectPhotoView;
//import youlin.xinhua.com.youlin.widget.chat.record.ProgressButton;
//import youlin.xinhua.com.youlin.widget.chat.record.RecordControllerView;
//import youlin.xinhua.com.youlin.widget.chat.record.RecordVoiceButton;
//
//public class ChatInputView extends LinearLayout
//    implements View.OnClickListener, TextWatcher, RecordControllerView.OnRecordActionListener,
//    OnFileSelectedListener {
//
//  public static final byte KEYBOARD_STATE_SHOW = -3;
//  public static final byte KEYBOARD_STATE_HIDE = -2;
//  public static final byte KEYBOARD_STATE_INIT = -1;
//
//  public static final int REQUEST_CODE_TAKE_PHOTO   = 0x0001;
//  public static final int REQUEST_CODE_SELECT_PHOTO = 0x0002;
//
//  private EditText mChatInput;
//  private TextView mSendCountTv;
//  private CharSequence mInput = "";
//
//  private CheckableView mVoiceBtn;
//  private CheckableView mPhotoBtn;
//  private CheckableView mCameraBtn;
//  private CheckableView mEmojBtn;
//  private Button        mSendBtn; // 发送的Button
//
//  private LinearLayout         mChatInputContainer;
//  private LinearLayout         mMenuItemContainer;
//  private FrameLayout          mMenuContainer;
//  private RelativeLayout       mRecordVoiceRl;
//  private LinearLayout         mPreviewPlayLl;
//  private ProgressButton       mPreviewPlayBtn;
//  private Button               mSendAudioBtn;
//  private Button               mCancelSendAudioBtn;
//  private LinearLayout         mRecordContentLl;
//  private RecordControllerView mRecordControllerView;
//  private Chronometer          mChronometer;
//  private TextView             mRecordHintTv;
//  private RecordVoiceButton    mRecordVoiceBtn;
//
//  SelectPhotoView mSelectPhotoView;
//  SelectEmojView  mSelectEmojView;
//
//  //private FrameLayout             mCameraFl;
//  //private TextureView             mTextureView;
//  //private ImageButton mCaptureBtn;
//  //private ImageButton mCloseBtn;
//  //private ImageButton mSwitchCameraBtn;
//  //private ImageButton mFullScreenBtn;
//  //private ImageButton mRecordVideoBtn;
//  private OnMenuClickListener     mListener;
//  private OnClickEditTextListener mEditTextListener;
//
//  private InputMethodManager mImm;
//  private Window             mWindow;
//  private int mLastClickId = 0;
//
//  private int mWidth;
//  private int mHeight;
//  public static int sMenuHeight = 800;
//
//  private boolean mShowSoftInput = false;
//
//  private long mRecordTime;
//  private boolean     mPlaying     = false;
//  private MediaPlayer mMediaPlayer = new MediaPlayer();
//
//  // To judge if it is record video mode
//  private boolean mIsRecordVideoMode = false;
//
//  // To judge if it is recording video now
//  private boolean mIsRecordingVideo = false;
//
//  // To judge if is finish recording video
//  private boolean mFinishRecordingVideo = false;
//
//  // Video file to be saved at
//  private String mVideoFilePath;
//  private int    mVideoDuration;
//
//  // If audio file has been set
//  private boolean         mSetData;
//  private FileInputStream mFIS;
//  private FileDescriptor  mFD;
//  private boolean         mIsEarPhoneOn;
//
//  private File mPhoto;
//  private int     mCameraId     = -1;
//  private boolean mIsBackCamera = true;
//  private boolean mIsFullScreen = false;
//  private Context mContext;
//
//  public ChatInputView(Context context) {
//    super(context);
//    init(context);
//  }
//
//  public ChatInputView(Context context, AttributeSet attrs) {
//    super(context, attrs);
//    init(context);
//  }
//
//  public ChatInputView(Context context, AttributeSet attrs, int defStyleAttr) {
//    super(context, attrs, defStyleAttr);
//    init(context);
//  }
//
//  private void init(Context context) {
//    mContext = context;
//    inflate(context, R.layout.view_chat_input, this);
//
//    // menu buttons
//    mChatInput = (EditText) findViewById(R.id.aurora_et_chat_input);
//    mVoiceBtn = (CheckableView) findViewById(R.id.aurora_menuitem_ib_voice);
//    mPhotoBtn = (CheckableView) findViewById(R.id.aurora_menuitem_ib_photo);
//    mCameraBtn = (CheckableView) findViewById(R.id.aurora_menuitem_ib_camera);
//    mEmojBtn = (CheckableView) findViewById(R.id.aurora_menuitem_ib_emoji);
//    mSendBtn = (Button) findViewById(R.id.aurora_menuitem_ib_send);
//
//    View voiceBtnContainer = findViewById(R.id.aurora_framelayout_menuitem_voice);// 语音按钮
//    View photoBtnContainer = findViewById(R.id.aurora_framelayout_menuitem_photo);// 图片按钮
//    View cameraBtnContainer = findViewById(R.id.aurora_framelayout_menuitem_camera);// 相机按钮
//    View emojBtnContainer = findViewById(R.id.aurora_framelayout_menuitem_emoji); // emoj表情按钮
//    voiceBtnContainer.setOnClickListener(onMenuItemClickListener);
//    photoBtnContainer.setOnClickListener(onMenuItemClickListener);
//    cameraBtnContainer.setOnClickListener(onMenuItemClickListener);
//    emojBtnContainer.setOnClickListener(onMenuItemClickListener);
//    mSendBtn.setOnClickListener(onMenuItemClickListener);
//
//    mSendCountTv = (TextView) findViewById(R.id.aurora_menuitem_tv_send_count);
//    mChatInputContainer = (LinearLayout) findViewById(R.id.aurora_ll_input_container);
//    mMenuItemContainer = (LinearLayout) findViewById(R.id.aurora_ll_menuitem_container);
//    mMenuContainer = (FrameLayout) findViewById(R.id.aurora_fl_menu_container);
//    mRecordVoiceRl = (RelativeLayout) findViewById(R.id.aurora_rl_recordvoice_container);
//    mPreviewPlayLl = (LinearLayout) findViewById(R.id.aurora_ll_recordvoice_preview_container);
//    mPreviewPlayBtn = (ProgressButton) findViewById(R.id.aurora_pb_recordvoice_play_audio);
//    mRecordContentLl = (LinearLayout) findViewById(R.id.aurora_ll_recordvoice_content_container);
//
//    mRecordControllerView =
//        (RecordControllerView) findViewById(R.id.aurora_rcv_recordvoice_controller);
//    mChronometer = (Chronometer) findViewById(R.id.aurora_chronometer_recordvoice);
//    mRecordHintTv = (TextView) findViewById(R.id.aurora_tv_recordvoice_hint);
//    mSendAudioBtn = (Button) findViewById(R.id.aurora_btn_recordvoice_send);
//    mCancelSendAudioBtn = (Button) findViewById(R.id.aurora_btn_recordvoice_cancel);
//    mRecordVoiceBtn = (RecordVoiceButton) findViewById(R.id.aurora_rvb_recordvoice_record);
//    //mCameraFl = (FrameLayout) findViewById(R.id.aurora_fl_camera_container);
//    //mTextureView = (TextureView) findViewById(R.id.aurora_txtv_camera_texture);
//    //mCloseBtn = (ImageButton) findViewById(R.id.aurora_ib_camera_close);
//    //mFullScreenBtn = (ImageButton) findViewById(R.id.aurora_ib_camera_full_screen);
//    //mRecordVideoBtn = (ImageButton) findViewById(R.id.aurora_ib_camera_record_video);
//    //mCaptureBtn = (ImageButton) findViewById(R.id.aurora_ib_camera_capture);
//    //mSwitchCameraBtn = (ImageButton) findViewById(R.id.aurora_ib_camera_switch);
//
//    mSelectPhotoView = (SelectPhotoView) findViewById(R.id.aurora_view_selectphoto);
//    mSelectPhotoView.setOnFileSelectedListener(this);
//    mSelectPhotoView.initData();
//
//    mSelectEmojView = (SelectEmojView) findViewById(R.id.aurora_view_selectemoj);
//
//    mMenuContainer.setVisibility(GONE);
//
//    mChatInput.addTextChangedListener(this);
//
//    mRecordVoiceBtn.setRecordController(mRecordControllerView);
//    mPreviewPlayBtn.setOnClickListener(this);
//    mCancelSendAudioBtn.setOnClickListener(this);
//    mSendAudioBtn.setOnClickListener(this);
//    //mCloseBtn.setOnClickListener(this);
//    //mFullScreenBtn.setOnClickListener(this);
//    //mRecordVideoBtn.setOnClickListener(this);
//    //mCaptureBtn.setOnClickListener(this);
//    //mSwitchCameraBtn.setOnClickListener(this);
//
//    mImm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//    mWindow = ((Activity) context).getWindow();
//    DisplayMetrics dm = getResources().getDisplayMetrics();
//    mWidth = dm.widthPixels;
//    mHeight = dm.heightPixels;
//    mRecordControllerView.setWidth(mWidth);
//    mRecordControllerView.setOnControllerListener(this);
//
//    mChatInput.setOnTouchListener(new OnTouchListener() {
//      @Override public boolean onTouch(View view, MotionEvent motionEvent) {
//        if (mEditTextListener != null) {
//          mEditTextListener.onTouchEditText();
//        }
//        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && !mShowSoftInput) {
//          mShowSoftInput = true;
//          invisibleMenuLayout();
//          mChatInput.requestFocus();
//        }
//        return false;
//      }
//    });
//
//    mSelectEmojView.setEmojMenuClickListener(new SelectEmojView.EmojMenuClickListener() {
//      @Override public void onExpressionClick(CharSequence emojiText) {
//        LogUtils.i(" setEmojMenuClickListener ,   emojiText : " + emojiText);
//        mChatInput.append(emojiText);
//      }
//    });
//
//    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
//    mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//      @Override public boolean onError(MediaPlayer mp, int what, int extra) {
//        return false;
//      }
//    });
//  }
//
//  public void setMenuClickListener(OnMenuClickListener listener) {
//    mListener = listener;
//  }
//
//  public void setOnClickEditTextListener(OnClickEditTextListener listener) {
//    mEditTextListener = listener;
//  }
//
//  @Override
//  public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
//
//  }
//
//  @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
//    mInput = s;
//
//    if (mSelectPhotoView.getSelectFiles() == null
//        || mSelectPhotoView.getSelectFiles().size() == 0) {
//      if (s.length() >= 1 && start == 0 && before == 0) { // Starting input
//        mSendBtn.setEnabled(true);
//      } else if (s.length() == 0 && before >= 1) { // Clear content
//        mSendBtn.setEnabled(false);
//      }
//    }
//  }
//
//  @Override public void afterTextChanged(Editable editable) {
//
//  }
//
//  public EditText getInputView() {
//    return mChatInput;
//  }
//
//  public RecordVoiceButton getRecordVoiceButton() {
//    return mRecordVoiceBtn;
//  }
//
//  private OnClickListener onMenuItemClickListener = new OnClickListener() {
//    @Override public void onClick(View view) {
//      if (view.getId() == R.id.aurora_menuitem_ib_send) { // 内容发送按钮: 文字,图片
//        // Allow send text and photos at the same time.
//        if (onSubmit()) { // 发送文字
//          mChatInput.setText("");
//        }
//        if (mSelectPhotoView.getSelectFiles() != null
//            && mSelectPhotoView.getSelectFiles().size() > 0) {
//          mListener.onSendFiles(mSelectPhotoView.getSelectFiles());
//
//          mSendBtn.setEnabled(false); // 按钮不设置可用
//          mSendCountTv.setVisibility(View.INVISIBLE);
//          mSelectPhotoView.resetCheckState();
//          dismissMenuLayout();
//        }
//      } else if (view.getId() == R.id.aurora_framelayout_menuitem_camera) { // 相机按钮被点击
//        if (mListener != null) {
//          mListener.switchToCameraMode();
//        }
//        showCameraLayout(); // 相机按钮被点击
//      } else {
//        if (mMenuContainer.getVisibility() != VISIBLE) { // 底部菜单容器未显示: 隐藏键盘和显示菜单栏
//          dismissSoftInputAndShowMenu(); // 显示菜单栏
//        } else if (view.getId() == mLastClickId) { // 再次点击相同菜单按钮,隐藏菜单和软键盘
//          setMenuUnChecked();// 设置按钮为未选中
//          dismissMenuAndResetSoftMode();
//          return;
//        }
//
//        if (view.getId() == R.id.aurora_framelayout_menuitem_voice) { // 语音按钮
//          if (mListener != null) {
//            mListener.switchToMicrophoneMode();
//          }
//          showRecordVoiceLayout();
//        } else if (view.getId() == R.id.aurora_framelayout_menuitem_photo) { // 图集按钮
//          if (mListener != null) {
//            mListener.switchToGalleryMode();
//          }
//          if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
//              != PackageManager.PERMISSION_GRANTED) {
//            return;
//          }
//          showPhotoSelectLayout(); // 显示图片选择
//        } else if (view.getId() == R.id.aurora_framelayout_menuitem_emoji) { // emoj表情按钮
//          showEmojLayout();
//        }
//        mLastClickId = view.getId();
//      }
//    }
//  };
//
//  @Override public void onClick(View view) {
//    if (view.getId() == R.id.aurora_pb_recordvoice_play_audio) { // 播放录音
//      // press preview play audio button
//      if (!mPlaying) {
//        if (mSetData) {
//          mPreviewPlayBtn.startPlay();
//          mMediaPlayer.start();
//          mPlaying = true;
//          mChronometer.setBase(convertStrTimeToLong(mChronometer.getText().toString()));
//          mChronometer.start();
//        } else {
//          playVoice();
//        }
//      } else {
//        mSetData = true;
//        mMediaPlayer.pause();
//        mChronometer.stop();
//        mPlaying = false;
//        mPreviewPlayBtn.stopPlay();
//      }
//    } else if (view.getId() == R.id.aurora_btn_recordvoice_cancel) { // 取消发送语音
//      // preview play audio widget cancel sending audio
//      mPreviewPlayLl.setVisibility(GONE);
//      mRecordContentLl.setVisibility(VISIBLE);
//      mRecordVoiceBtn.cancelRecord();
//      mChronometer.setText("00:00");
//    } else if (view.getId() == R.id.aurora_btn_recordvoice_send) { // 发送语音
//      // preview play audio widget send audio
//      mPreviewPlayLl.setVisibility(GONE);
//      dismissMenuLayout();
//      mRecordVoiceBtn.finishRecord();
//      mChronometer.setText("00:00");
//    }
//  }
//
//  // play audio
//  private void playVoice() {
//    try {
//      mMediaPlayer.reset();
//      mFIS = new FileInputStream(mRecordVoiceBtn.getRecordFile());
//      mFD = mFIS.getFD();
//      mMediaPlayer.setDataSource(mFD);
//      if (mIsEarPhoneOn) {
//        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
//      } else {
//        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//      }
//      mMediaPlayer.prepare();
//      mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//        @Override public void onPrepared(final MediaPlayer mp) {
//          mChronometer.setBase(SystemClock.elapsedRealtime());
//          mPreviewPlayBtn.startPlay();
//          mChronometer.start();
//          mp.start();
//          mPlaying = true;
//        }
//      });
//      mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//        @Override public void onCompletion(MediaPlayer mp) {
//          mp.stop();
//          mSetData = false;
//          mChronometer.stop();
//          mPlaying = false;
//          mPreviewPlayBtn.finishPlay();
//        }
//      });
//    } catch (Exception e) {
//      Toast.makeText(getContext(), getContext().getString(R.string.file_not_found_toast),
//          Toast.LENGTH_SHORT).show();
//      e.printStackTrace();
//    } finally {
//      try {
//        if (mFIS != null) {
//          mFIS.close();
//        }
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//  }
//
//  public void setAudioPlayByEarPhone(int state) {
//    AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
//    int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
//    audioManager.setMode(AudioManager.MODE_IN_CALL);
//    if (state == 0) {
//      mIsEarPhoneOn = false;
//      audioManager.setSpeakerphoneOn(true);
//      audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
//          audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL),
//          AudioManager.STREAM_VOICE_CALL);
//    } else {
//      mIsEarPhoneOn = true;
//      audioManager.setSpeakerphoneOn(false);
//      audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, currVolume,
//          AudioManager.STREAM_VOICE_CALL);
//    }
//  }
//
//  //private void initCamera() {
//  public void dismissMenuLayout() {
//    setMenuUnChecked();
//    mMenuContainer.setVisibility(GONE);
//  }
//
//  public void invisibleMenuLayout() {
//    mMenuContainer.setVisibility(INVISIBLE);
//  }
//
//  /**
//   * 设置Menu菜单为未选中
//   */
//  public void setMenuUnChecked() {
//    mEmojBtn.setChecked(false);
//    mVoiceBtn.setChecked(false);
//    mPhotoBtn.setChecked(false);
//  }
//
//  /**
//   * 显示总的layout
//   */
//  public void showMenuLayout() {
//    mMenuContainer.setVisibility(VISIBLE);
//  }
//
//  /**
//   * 显示图集选择
//   */
//  public void showPhotoSelectLayout() {
//
//    mEmojBtn.setChecked(false);
//    mVoiceBtn.setChecked(false);
//    mPhotoBtn.setChecked(true);
//
//    mSelectPhotoView.setVisibility(VISIBLE);
//    mSelectPhotoView.initData();
//
//    dismissRecordVoiceLayout();
//    dimissEmojLayout();
//  }
//
//  /**
//   * 显示语音layout
//   */
//  public void showRecordVoiceLayout() {
//
//    mEmojBtn.setChecked(false);
//    mVoiceBtn.setChecked(true);
//    mPhotoBtn.setChecked(false);
//
//    mRecordVoiceRl.setVisibility(VISIBLE);
//    //mRecordContentLl.setVisibility(VISIBLE);
//
//    dismissPhotoLayout();
//    dimissEmojLayout();
//  }
//
//  /**
//   * 显示表情layout
//   */
//  public void showEmojLayout() {
//
//    mEmojBtn.setChecked(true);
//    mVoiceBtn.setChecked(false);
//    mPhotoBtn.setChecked(false);
//
//    mSelectEmojView.setVisibility(View.VISIBLE);
//
//    dismissRecordVoiceLayout();
//    dismissPhotoLayout();
//  }
//
//  public void dismissRecordVoiceLayout() {
//    mRecordVoiceRl.setVisibility(GONE);
//  }
//
//  public void dismissPhotoLayout() {
//    mSelectPhotoView.setVisibility(View.GONE);
//  }
//
//  public void dimissEmojLayout() {
//    mSelectEmojView.setVisibility(View.GONE);
//  }
//
//  /**
//   * 显示相机
//   */
//  public void showCameraLayout() {
//
//    dismissMenuLayout(); // 隐藏总的layout
//
//    dismissRecordVoiceLayout();
//    dismissPhotoLayout();
//    dimissEmojLayout();
//  }
//
//  /**
//   * Set menu container's height, invoke this method once the menu was initialized.
//   *
//   * @param height Height of menu, set same height as soft keyboard so that display to perfection.
//   */
//  public void setMenuContainerHeight(int height) {
//    if (height > 0) {
//      sMenuHeight = height;
//      mMenuContainer.setLayoutParams(
//          new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height));
//    }
//  }
//
//  private boolean onSubmit() {
//    return mListener != null && mListener.onSendTextMessage(mInput);
//  }
//
//  public boolean getSoftInputState() {
//    return mShowSoftInput;
//  }
//
//  public void setSoftInputState(boolean state) {
//    mShowSoftInput = state;
//  }
//
//  public int getMenuState() {
//    return mMenuContainer.getVisibility();
//  }
//
//  /**
//   * Select aurora_menuitem_photo callback
//   */
//  @Override public void onFileSelected() {
//    if (mInput.length() == 0 && mSelectPhotoView.getSelectFiles().size() == 1) {
//      mSendBtn.setEnabled(true);
//      mSendCountTv.setVisibility(View.VISIBLE);
//    } else if (mInput.length() > 0 && mSendCountTv.getVisibility() != View.VISIBLE) {
//      mSendCountTv.setVisibility(View.VISIBLE);
//    }
//    mSendCountTv.setText(String.valueOf(mSelectPhotoView.getSelectFiles().size()));
//  }
//
//  /**
//   * Cancel select aurora_menuitem_photo callback
//   */
//  @Override public void onFileDeselected() {
//    int size = mSelectPhotoView.getSelectFiles().size();
//    if (size > 0) {
//      mSendCountTv.setText(String.valueOf(size));
//      mSendCountTv.setVisibility(View.VISIBLE);
//    } else {
//      if (mInput.length() == 0) {
//        mSendBtn.setEnabled(false);
//        mSendCountTv.setVisibility(View.INVISIBLE);
//      }
//    }
//  }
//
//  /**
//   * Set aurora_menuitem_camera capture file path and file name. If user didn't invoke this method,
//   * will save in
//   * default path.
//   *
//   * @param path Photo to be saved in.
//   * @param fileName File name.
//   */
//  public void setCameraCaptureFile(String path, String fileName) {
//    File destDir = new File(path);
//    if (!destDir.exists()) {
//      destDir.mkdirs();
//    }
//    mPhoto = new File(path, fileName + ".png");
//  }
//
//  /**
//   * Record audio widget finger on touch record button callback
//   */
//  @Override public void onStart() {
//    Log.e("ChatInputView", "starting chronometer");
//    mChronometer.setBase(SystemClock.elapsedRealtime());
//    mChronometer.start();
//    mChronometer.setVisibility(VISIBLE);
//    mRecordHintTv.setVisibility(INVISIBLE);
//  }
//
//  /**
//   * Recording audio mode, finger moving callback
//   */
//  @Override public void onMoving() {
//    mChronometer.setVisibility(VISIBLE);
//    mRecordHintTv.setVisibility(INVISIBLE);
//  }
//
//  /**
//   * Recording audio mode, finger moved left button (preview button) callback
//   */
//  @Override public void onMovedLeft() {
//    mChronometer.setVisibility(INVISIBLE);
//    mRecordHintTv.setVisibility(VISIBLE);
//    mRecordHintTv.setText(getContext().getString(R.string.preview_play_audio_hint));
//  }
//
//  /**
//   * Recording audio mode, finger moved right button (cancel button)
//   */
//  @Override public void onMovedRight() {
//    mChronometer.setVisibility(INVISIBLE);
//    mRecordHintTv.setVisibility(VISIBLE);
//    mRecordHintTv.setText(getContext().getString(R.string.cancel_record_voice_hint));
//  }
//
//  /**
//   * Recording audio mode, finger moved left button and release
//   */
//  @Override public void onLeftUpTapped() {
//    mChronometer.stop();
//    mRecordTime = SystemClock.elapsedRealtime() - mChronometer.getBase();
//    mPreviewPlayBtn.setMax((int) (mRecordTime / 1000));
//    mChronometer.setVisibility(VISIBLE);
//    mRecordHintTv.setVisibility(INVISIBLE);
//    mPreviewPlayLl.setVisibility(VISIBLE);
//    mRecordContentLl.setVisibility(GONE);
//  }
//
//  /**
//   * Recording audio mode, finger moved right button and release
//   */
//  @Override public void onRightUpTapped() {
//    mChronometer.stop();
//    mChronometer.setVisibility(INVISIBLE);
//    mRecordHintTv.setText(getContext().getString(R.string.record_voice_hint));
//    mRecordHintTv.setVisibility(VISIBLE);
//  }
//
//  private long convertStrTimeToLong(String strTime) {
//    String[] timeArray = strTime.split(":");
//    long longTime = 0;
//    if (timeArray.length == 2) { // If time format is MM:SS
//      longTime = Integer.parseInt(timeArray[0]) * 60 * 1000 + Integer.parseInt(timeArray[1]) * 1000;
//    }
//    return SystemClock.elapsedRealtime() - longTime;
//  }
//
//  public void dismissMenuAndResetSoftMode() {
//    mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
//        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//    try {
//      Thread.sleep(100);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//
//    dismissMenuLayout();
//    mChatInput.requestFocus();
//  }
//
//  public void dismissSoftInputAndShowMenu() {
//    // dismiss soft input
//    mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
//        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//    try {
//      Thread.sleep(100);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//    showMenuLayout();
//    if (mImm != null) {
//      mImm.hideSoftInputFromWindow(mChatInput.getWindowToken(), 0);
//    }
//    setMenuContainerHeight(sMenuHeight);
//    mShowSoftInput = false;
//  }
//
//  @Override protected void onDetachedFromWindow() {
//    super.onDetachedFromWindow();
//    //if (mCameraSupport != null) {
//    //    mCameraSupport.release();
//    //}
//    mMediaPlayer.release();
//    mMediaPlayer = null;
//  }
//
//  @Override public void onWindowVisibilityChanged(int visibility) {
//    super.onWindowVisibilityChanged(visibility);
//    if (visibility == GONE) {
//    }
//  }
//}
