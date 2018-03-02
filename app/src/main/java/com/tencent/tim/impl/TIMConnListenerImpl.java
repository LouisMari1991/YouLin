package com.tencent.tim.impl;

import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMManager;
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
public class TIMConnListenerImpl implements TIMConnListener {

  public TIMConnListenerImpl() {

  }

  @Override public void onConnected() {
    String loginUser = TIMManager.getInstance().getLoginUser();
    LogUtils.i("[TIMConnListenerImpl] onConnected ! " + loginUser);
  }

  @Override public void onDisconnected(int i, String s) {
    String loginUser = TIMManager.getInstance().getLoginUser();
    LogUtils.i("[TIMConnListenerImpl] onDisconnected ! i : "
        + i
        + " , s :"
        + s
        + " , "
        + loginUser);
  }

  @Override public void onWifiNeedAuth(String s) {
    LogUtils.i("[TIMConnListenerImpl] onWifiNeedAuth ! TIMFriendshipProxyListenerImpl :" + s);
  }
}
