package com.tencent.tim.impl;

import com.tencent.imsdk.TIMConnListener;
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
public class TIMConnListenerImpl implements TIMConnListener {

  public TIMConnListenerImpl() {

  }

  @Override public void onConnected() {
    LogUtils.i("[TIMConnListenerImpl] onConnected ! ");
  }

  @Override public void onDisconnected(int i, String s) {
    LogUtils.i("[TIMConnListenerImpl] onDisconnected ! i : "
        + i
        + " , TIMFriendshipProxyListenerImpl :"
        + s);
  }

  @Override public void onWifiNeedAuth(String s) {
    LogUtils.i(
        "[TIMConnListenerImpl] onWifiNeedAuth ! TIMFriendshipProxyListenerImpl :" + s);
  }
}
