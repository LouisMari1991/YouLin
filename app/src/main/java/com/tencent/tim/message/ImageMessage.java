package com.tencent.tim.message;

import com.tencent.imsdk.TIMMessage;

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
