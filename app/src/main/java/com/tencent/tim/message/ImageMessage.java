package com.tencent.tim.message;

import com.tencent.imsdk.TIMImageElem;
import com.tencent.imsdk.TIMMessage;
import java.util.List;
import youlin.xinhua.com.youlin.model.FileItem;

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
   */
  public ImageMessage(String path, boolean isOri) {
    message = new TIMMessage();
    TIMImageElem elem = new TIMImageElem();
    elem.setPath(path);
    elem.setLevel(isOri ? 0 : 1);
    message.addElement(elem);
  }

  public ImageMessage(List<FileItem> pathList, boolean isOri) {
    message = new TIMMessage();
    for (int i = 0; i < pathList.size(); i++) {
      TIMImageElem elem = new TIMImageElem();
      elem.setPath(pathList.get(i).getFilePath());
      elem.setLevel(isOri ? 0 : 1);
      message.addElement(elem);
    }
  }

  @Override public String getSummary() {
    return "";
  }

  @Override public int getItemType() {
    if (isSelf()) {
      return TYPE_IMG_SEND;
    } else {
      return TYPE_IMG_RECEIVE;
    }
  }
}
