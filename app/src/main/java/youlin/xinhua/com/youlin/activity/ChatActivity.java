package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import butterknife.BindView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.PathUtil;
import java.io.File;
import java.util.List;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.constant.CacheConsts;
import youlin.xinhua.com.youlin.constant.EaseConstant;
import youlin.xinhua.com.youlin.listener.OnMenuClickListener;
import youlin.xinhua.com.youlin.listener.RecordVoiceListener;
import youlin.xinhua.com.youlin.model.FileItem;
import youlin.xinhua.com.youlin.utils.LogUtils;
import youlin.xinhua.com.youlin.utils.ToastUtils;
import youlin.xinhua.com.youlin.widget.MeetOperationView;
import youlin.xinhua.com.youlin.widget.chat.ChatView;
import youlin.xinhua.com.youlin.widget.chat.chatinput.ChatInputView;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-24 15:06
 * version: 1.0
 * </pre>
 */

public class ChatActivity extends BaseChatActivity
    implements View.OnTouchListener, ChatView.OnKeyboardChangedListener {

  public static void lunch(Context context) {
    Intent intent = new Intent(context, ChatActivity.class);
    context.startActivity(intent);
  }

  protected static final int REQUEST_CODE_CAMERA = 2;

  @BindView(R.id.chat_view) ChatView mChatView;

  protected File cameraFile;

  private InputMethodManager mImm;
  private Window             mWindow;

  private boolean isMeetGroup = true;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initChatInput();
  }

  @Override void initViews() {
    super.initViews();
    mChatView.initModule();
    messageList = mChatView.getMessageListView();
    messageList.init(toChatUsername, chatType);
    listView = messageList.getListView();

    //swipeRefreshLayout = messageList.getSwipeRefreshLayout();

    if (chatType != EaseConstant.CHATTYPE_CHATROOM) {
      onConversationInit();
      onMessageListInit();
    }
    setRefreshLayoutListener();
  }

  private void initChatInput() {
    mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    mWindow = getWindow();

    mChatView.setOnTouchListener(this);
    mChatView.setKeyboardChangedListener(this);
    mChatView.setMenuClickListener(new OnMenuClickListener() {
      @Override public boolean onSendTextMessage(CharSequence input) {

        if (TextUtils.isEmpty(input)) {
          return false;
        }

        // 发送文字消息
        sendTextMessage(input.toString());
        return true;
      }

      @Override public void onSendFiles(List<FileItem> list) {
        // 发送文件
        for (int i = 0; list != null && i < list.size(); i++) {
          FileItem item = list.get(i);
          sendImageMessage(item.getFilePath());
        }
      }

      @Override public void switchToMicrophoneMode() {
        // 录音, 记得申请打开麦克风权限

      }

      @Override public void switchToGalleryMode() {
        // 图片选择,记得申请读取sd卡权限

      }

      @Override public void switchToCameraMode() {
        // 拍照
        cameraFile = new File(PathUtil.getInstance().getImagePath(),
            EMClient.getInstance().getCurrentUser() + System.currentTimeMillis() + ".jpg");
        //noinspection ResultOfMethodCallIgnored
        cameraFile.getParentFile().mkdirs();
        startActivityForResult(
            new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(cameraFile)), REQUEST_CODE_CAMERA);
      }
    });
    mChatView.setRecordVoiceListener(new RecordVoiceListener() {
      @Override public void onStartRecord() {
        // 开始录音,记得设置录音文件路径
        mChatView.setRecordVoiceFile(CacheConsts.ExternalStorage.VOICE_DIR,
            EMClient.getInstance().getCurrentUser() + System.currentTimeMillis());
      }

      @Override public void onFinishRecord(File voiceFile, int duration) {
        // 结束录音
        ToastUtils.showToast(" 结束录音 , voiceFile : " + voiceFile + ", 录音时间 : " + duration);
        sendVoiceMessage(voiceFile.getPath(), duration);
      }

      @Override public void onCancelRecord() {
        // 取消了录音的回调,已经删除了文件

      }
    });
    mChatView.setBtnMeetFileOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        LogUtils.i("查看公示文件");
      }
    });
    mChatView.setMeetOperationViewOnClickListener(
        new MeetOperationView.MeetOperationViewOnClickListener() {
          @Override public void onClick(View view) {
            LogUtils.i("MeetOperationViewOnClickListener");
          }
        });
    mChatView.startCountDownTimer(0, System.currentTimeMillis());
    messageList.getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
      @Override public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (!isMeetGroup) {
          return;
        }
        mChatView.changMeetOperationViewState(scrollState);
      }

      @Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
          int totalItemCount) {

      }
    });
    //mChatView.setMeetOperationViewContentText(3, "152", "200");
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

  @Override public void onBackPressed() {
    if (mChatView.getChatInputView().getMenuState() == View.VISIBLE) {
      mChatView.getChatInputView().dismissMenuLayout();
    } else {
      super.onBackPressed();
    }
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    LogUtils.i("onActivityResult : requestCode : " + requestCode + ", resultCode :  " + resultCode);
    if (resultCode == RESULT_OK) {
      if (requestCode == REQUEST_CODE_CAMERA) { // capture new image
        if (cameraFile != null && cameraFile.exists()) {
          ToastUtils.showToast("获取照片成功 , path : " + cameraFile.getPath());
          sendImageMessage(cameraFile.getAbsolutePath());
        }
      }
    }
  }
}
