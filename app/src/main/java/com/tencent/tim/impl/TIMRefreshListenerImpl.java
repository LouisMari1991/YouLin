package com.tencent.tim.impl;

import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMRefreshListener;
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
public class TIMRefreshListenerImpl implements TIMRefreshListener {

  @Override public void onRefresh() {
    LogUtils.i("[TIMRefreshListenerImpl] onRefresh ! ");
  }

  @Override public void onRefreshConversation(List<TIMConversation> list) {
    LogUtils.i("[TIMRefreshListenerImpl] onRefreshConversation ! list : " + list);
  }
}
