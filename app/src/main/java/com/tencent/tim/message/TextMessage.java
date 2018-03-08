package com.tencent.tim.message;

import android.text.TextUtils;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMFaceElem;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageStatus;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.ext.message.TIMMessageDraft;
import com.xinhua.tim.util.EaseSmileUtils;
import java.nio.charset.Charset;
import youlin.xinhua.com.youlin.MApp;
import youlin.xinhua.com.youlin.R;

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

    String str = getRevokeSummary();

    if (!TextUtils.isEmpty(str)) {
      return str;
    }

    StringBuilder result = new StringBuilder();

    for (int i = 0; i < message.getElementCount(); ++i) {
      switch (message.getElement(i).getType()) {
        case Face:
          TIMFaceElem faceElem = (TIMFaceElem) message.getElement(i);
          byte[] data = faceElem.getData();
          if (data != null) {
            result.append(new String(data, Charset.forName("UTF-8")));
          }
          break;
        case Text:
          TIMTextElem textElem = (TIMTextElem) message.getElement(i);
          result.append(EaseSmileUtils.getSmiledText(MApp.get(), textElem.getText()));
          break;
      }
    }

    return result.toString();
  }

  @Override protected void onBubbleClick() {

  }

  @Override protected void onSetUpView() {

    // 设置内容

    helper.setText(R.id.text_message_content, getSummary());

    showStatus();
  }

  String getRevokeSummary() {
    if (message.status() == TIMMessageStatus.HasRevoked) {
      return getSender() + "撤回了一条消息";
    }
    return null;
  }

  @Override public int getItemType() {
    if (isSelf()) {
      return TYPE_TEXT_SEND;
    } else {
      return TYPE_TEXT_RECEIVE;
    }
  }
}
