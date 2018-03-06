package com.tencent.tim.message;

import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.ext.message.TIMMessageDraft;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/02/23
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class TextMessage extends Message {
  public TextMessage(TIMMessage message) {
    super(message);
  }

  public TextMessage(String s) {
    message = new TIMMessage();
    TIMTextElem elem = new TIMTextElem();
    elem.setText(s);
    message.addElement(elem);
  }

  public TextMessage(TIMMessageDraft draft) {
    message = new TIMMessage();
    for (TIMElem elem : draft.getElems()) {
      message.addElement(elem);
    }
  }

  @Override public String getSummary() {
    return "";
  }

  @Override public int getItemType() {
    if (isSelf()) {
      return TYPE_TEXT_SEND;
    } else {
      return TYPE_TEXT_RECEIVE;
    }
  }
}
