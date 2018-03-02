package com.tencent.tim.impl;

import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.ext.message.TIMMessageExt;
import java.util.List;
import youlin.xinhua.com.youlin.utils.LogUtils;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/01/30
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class TIMMessageListenerImpl implements TIMMessageListener {

  public TIMMessageListenerImpl() {
  }

  @Override public boolean onNewMessages(List<TIMMessage> list) {

    for (int i = 0; i < list.size(); i++) {
      TIMMessage message = list.get(i);
      TIMMessageExt ext = new TIMMessageExt(message);
      TIMElem element = message.getElement(0);
      LogUtils.i(message.getSender()
          + " , "
          + message.getElementCount()
          + " , "
          + ext.hasGap()
          + " , "
          + element.getType());
    }

    return false;
  }
}
