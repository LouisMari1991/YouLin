package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import butterknife.BindView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.PathUtil;
import java.io.File;
import java.util.List;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.constant.CacheConsts;
import youlin.xinhua.com.youlin.listener.OnMenuClickListener;
import youlin.xinhua.com.youlin.listener.RecordVoiceListener;
import youlin.xinhua.com.youlin.model.FileItem;
import youlin.xinhua.com.youlin.utils.ToastUtils;
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

  protected static final int REQUEST_CODE_CAMERA = 2;

  @BindView(R.id.chat_view) ChatView mChatView;

  protected File cameraFile;

  private InputMethodManager mImm;
  private Window             mWindow;

  @Override protected int attachLayoutRes() {
    return R.layout.activity_im_chat;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initChatInput();
  }

  private void initChatInput() {
    mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    mWindow = getWindow();

    mChatView.initModule();
    mChatView.setOnTouchListener(this);
    mChatView.setKeyboardChangedListener(this);
    mChatView.setMenuClickListener(new OnMenuClickListener() {
      @Override public boolean onSendTextMessage(CharSequence input) {
        return false;
      }

      @Override public void onSendFiles(List<FileItem> list) {

      }

      @Override public void switchToMicrophoneMode() {

      }

      @Override public void switchToGalleryMode() {

      }

      @Override public void switchToCameraMode() {
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
      }

      @Override public void onCancelRecord() {
        // 取消了录音的回调,已经删除了文件

      }
    });
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
    if (resultCode == RESULT_OK) {
      if (requestCode == REQUEST_CODE_CAMERA) { // capture new image
        if (cameraFile != null && cameraFile.exists()) {
          ToastUtils.showToast("获取照片成功 , path : " + cameraFile.getPath());
        }
        //sendImageMessage(cameraFile.getAbsolutePath());
      }
    }
  }
}
