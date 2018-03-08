package com.tencent.tim.message;

import android.widget.ImageView;
import com.tencent.imsdk.TIMImage;
import com.tencent.imsdk.TIMImageElem;
import com.tencent.imsdk.TIMMessage;
import java.util.ArrayList;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.ImageLoader;

import static com.tencent.imsdk.TIMImageType.Large;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/02/23
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class ImageMessage extends Message {

  public ImageMessage(TIMMessage message) {
    super(message);
  }

  /**
   * 图片消息构造函数
   *
   * @param path 图片路径
   * @param isOri 是否原图发送
   * level - 0: 原图发送 1: 高压缩率图发送(图片较小，默认值) 2:高清图发送(图片较大)
   */
  public ImageMessage(String path) {
    message = new TIMMessage();
    TIMImageElem elem = new TIMImageElem();
    elem.setPath(path);
    elem.setLevel(2);
    message.addElement(elem);
  }

  @Override public String getSummary() {
    return "[图片]";
  }

  @Override protected void onBubbleClick() {

  }

  @Override protected void onSetUpView() {

    TIMImageElem e = (TIMImageElem) message.getElement(0);

    ImageView image = helper.getView(R.id.image);

    if (isSelf()) {
      ImageLoader.displayChatRowPicture(e.getPath(), image);
    } else {
      ArrayList<TIMImage> imageList = e.getImageList();
      for (int i = 0; i < imageList.size(); i++) {
        TIMImage item = imageList.get(i);
        if (item.getType() == Large) {
          ImageLoader.displayChatRowPicture(item.getUrl(), image);
        }
      }
    }

    showStatus();
  }

  @Override public int getItemType() {
    if (isSelf()) {
      return TYPE_IMG_SEND;
    } else {
      return TYPE_IMG_RECEIVE;
    }
  }
}
