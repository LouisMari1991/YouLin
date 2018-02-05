package com.tencent.tim.impl;

import com.tencent.imsdk.ext.message.TIMMessageLocator;
import com.tencent.imsdk.ext.message.TIMMessageRevokedListener;
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
public class TIMMessageRevokedListenerImpl implements TIMMessageRevokedListener {

  @Override public void onMessageRevoked(TIMMessageLocator timMessageLocator) {
    LogUtils.i("[TIMMessageRevokedListenerImpl] onMessageRevoked ! TIMMessageLocator : "
        + timMessageLocator);
  }
}
