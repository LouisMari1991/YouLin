package com.tencent.tim.impl;

import com.tencent.imsdk.TIMSNSChangeInfo;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.ext.sns.TIMFriendshipProxyListener;
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
public class TIMFriendshipProxyListenerImpl implements TIMFriendshipProxyListener {

  public TIMFriendshipProxyListenerImpl() {
  }

  @Override public void OnAddFriends(List<TIMUserProfile> list) {
    LogUtils.i("[TIMFriendshipProxyListenerImpl] OnAddFriends ! list : " + list);
  }

  @Override public void OnDelFriends(List<String> list) {
    LogUtils.i("[TIMFriendshipProxyListenerImpl] OnDelFriends ! list : " + list);
  }

  @Override public void OnFriendProfileUpdate(List<TIMUserProfile> list) {
    LogUtils.i("[TIMFriendshipProxyListenerImpl] OnFriendProfileUpdate ! list : " + list);
  }

  @Override public void OnAddFriendReqs(List<TIMSNSChangeInfo> list) {
    LogUtils.i("[TIMFriendshipProxyListenerImpl] OnAddFriendReqs ! list : " + list);
  }
}
