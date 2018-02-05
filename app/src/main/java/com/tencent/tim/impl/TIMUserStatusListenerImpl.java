package com.tencent.tim.impl;

import com.tencent.imsdk.TIMUserStatusListener;
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
public class TIMUserStatusListenerImpl implements TIMUserStatusListener {

  @Override public void onForceOffline() {
    // 强制下线
    LogUtils.i("[TIMUserStatusListenerImpl] onForceOffline ! ");
  }

  @Override public void onUserSigExpired() {
    // 票据过期，需要重新登录
    LogUtils.i("[TIMUserStatusListenerImpl] onUserSigExpired ! ");
  }
}
