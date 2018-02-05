package com.tencent.tim.impl;

import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import java.util.List;
import youlin.xinhua.com.youlin.utils.LogUtils;
import youlin.xinhua.com.youlin.utils.ToastUtils;

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

    LogUtils.i("[TIMMessageListenerImpl] onNewMessages ! list : " + list);

    return false;
  }
}
