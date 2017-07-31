package youlin.xinhua.com.youlin.widget.chat.chatrow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMFileMessageBody;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.ChatType;
import java.io.File;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.FileUtil;
import youlin.xinhua.com.youlin.utils.ImageLoader;

public class EaseChatRowImage extends EaseChatRowFile {

  protected ImageView          imageView;
  private   EMImageMessageBody imgBody;

  public EaseChatRowImage(Context context, EMMessage message, int position, BaseAdapter adapter) {
    super(context, message, position, adapter);
  }

  @Override protected void onInflateView() {
    inflater.inflate(
        message.direct() == EMMessage.Direct.RECEIVE ? R.layout.item_chat_received_picture
            : R.layout.item_chat_send_picture, this);
  }

  @Override protected void onFindViewById() {
    percentageView = (TextView) findViewById(R.id.percentage);
    imageView = (ImageView) findViewById(R.id.image);
  }

  @Override protected void onSetUpView() {
    imgBody = (EMImageMessageBody) message.getBody();
    // received messages
    if (message.direct() == EMMessage.Direct.RECEIVE) { // 接收图片消息
      if (imgBody.thumbnailDownloadStatus() == EMFileMessageBody.EMDownloadStatus.DOWNLOADING
          || imgBody.thumbnailDownloadStatus() == EMFileMessageBody.EMDownloadStatus.PENDING) {
        //set the receive message callback
        setMessageReceiveCallback();
      } else {
        progressBar.setVisibility(View.GONE);
        percentageView.setVisibility(View.GONE);
        //imageView.setImageResource(R.drawable.ease_default_image);
        String thumbPath = imgBody.thumbnailLocalPath();
        if (!new File(thumbPath).exists()) {
          // to make it compatible with thumbnail received in previous version
          thumbPath = FileUtil.getThumbnailImagePath(imgBody.getLocalUrl());
        }
        showImageView(thumbPath, imgBody.getLocalUrl());
      }
      return;
    }

    String filePath = imgBody.getLocalUrl();
    String thumbPath = FileUtil.getThumbnailImagePath(imgBody.getLocalUrl());
    showImageView(thumbPath, filePath);
    handleSendMessage();
  }

  @Override protected void onUpdateView() {
    super.onUpdateView();
  }

  @Override protected void onBubbleClick() {
    if (imgBody.thumbnailDownloadStatus() == EMFileMessageBody.EMDownloadStatus.DOWNLOADING
        || imgBody.thumbnailDownloadStatus() == EMFileMessageBody.EMDownloadStatus.PENDING) {
      //thumbnail image downloading
      return;
    } else if (imgBody.thumbnailDownloadStatus() == EMFileMessageBody.EMDownloadStatus.FAILED) {
      progressBar.setVisibility(View.VISIBLE);
      percentageView.setVisibility(View.VISIBLE);
      // retry download with click event of user
      EMClient.getInstance().chatManager().downloadThumbnail(message);
    }

    //Intent intent = new Intent(context, EaseShowBigImageActivity.class);
    Intent intent = null;
    File file = new File(imgBody.getLocalUrl());
    if (file.exists()) {
      Uri uri = Uri.fromFile(file);
      intent.putExtra("uri", uri);
    } else {
      // The local full size pic does not exist yet.
      // ShowBigImage needs to download it from the server
      // first
      String msgId = message.getMsgId();
      intent.putExtra("messageId", msgId);
      intent.putExtra("localUrl", imgBody.getLocalUrl());
    }
    if (message != null
        && message.direct() == EMMessage.Direct.RECEIVE
        && !message.isAcked()
        && message.getChatType() == ChatType.Chat) {
      try {
        EMClient.getInstance().chatManager().ackMessageRead(message.getFrom(), message.getMsgId());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    context.startActivity(intent);
  }

  public void showImageView(final String thumbernailPath, final String localFullSizePath) {
    String filePath;
    if (new File(localFullSizePath).exists()) {
      filePath = localFullSizePath;
    } else if (new File(thumbernailPath).exists()) {
      filePath = thumbernailPath;
    } else  {
      filePath = FileUtil.getThumbnailImagePath(imgBody.getLocalUrl());
    }
    ImageLoader.displayChatRowPicture(filePath, imageView);
  }

  /**
   * load image into image view
   */
  //private void showImageView(final String thumbernailPath, final String localFullSizePath,
  //    final EMMessage message) {
  //  // first check if the thumbnail image already loaded into cache
  //  Bitmap bitmap = EaseImageCache.getInstance().get(thumbernailPath);
  //  if (bitmap != null) {
  //    // thumbnail image is already loaded, reuse the drawable
  //    imageView.setImageBitmap(bitmap);
  //  } else {
  //    AsyncTaskCompat.executeParallel(new AsyncTask<Object, Void, Bitmap>() {
  //
  //      @Override protected Bitmap doInBackground(Object... args) {
  //        File file = new File(thumbernailPath);
  //        if (file.exists()) {
  //          return EaseImageUtils.decodeScaleImage(thumbernailPath, 160, 160);
  //        } else if (new File(imgBody.thumbnailLocalPath()).exists()) {
  //          return EaseImageUtils.decodeScaleImage(imgBody.thumbnailLocalPath(), 160, 160);
  //        } else {
  //          if (message.direct() == EMMessage.Direct.SEND) {
  //            if (localFullSizePath != null && new File(localFullSizePath).exists()) {
  //              return EaseImageUtils.decodeScaleImage(localFullSizePath, 160, 160);
  //            } else {
  //              return null;
  //            }
  //          } else {
  //            return null;
  //          }
  //        }
  //      }
  //
  //      protected void onPostExecute(Bitmap image) {
  //        if (image != null) {
  //          imageView.setImageBitmap(image);
  //          EaseImageCache.getInstance().put(thumbernailPath, image);
  //        }
  //      }
  //    });
  //  }
  //}
}
