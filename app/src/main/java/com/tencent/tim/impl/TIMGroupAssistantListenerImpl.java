package com.tencent.tim.impl;

import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.ext.group.TIMGroupAssistantListener;
import com.tencent.imsdk.ext.group.TIMGroupCacheInfo;
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
public class TIMGroupAssistantListenerImpl implements TIMGroupAssistantListener {

  public TIMGroupAssistantListenerImpl() {
  }

  @Override public void onMemberJoin(String s, List<TIMGroupMemberInfo> list) {
    LogUtils.i("[TIMGroupAssistantListenerImpl] onMemberJoin ! list : " + list);
  }

  @Override public void onMemberQuit(String s, List<String> list) {
    LogUtils.i("[TIMGroupAssistantListenerImpl] onMemberQuit ! list : " + list);
  }

  @Override public void onMemberUpdate(String s, List<TIMGroupMemberInfo> list) {
    LogUtils.i("[TIMGroupAssistantListenerImpl] onMemberUpdate ! list : " + list);
  }

  @Override public void onGroupAdd(TIMGroupCacheInfo timGroupCacheInfo) {
    LogUtils.i(
        "[TIMGroupAssistantListenerImpl] onGroupAdd ! timGroupCacheInfo : " + timGroupCacheInfo);
  }

  @Override public void onGroupDelete(String s) {
    LogUtils.i("[TIMGroupAssistantListenerImpl] onGroupDelete ! s : " + s);
  }

  @Override public void onGroupUpdate(TIMGroupCacheInfo timGroupCacheInfo) {
    LogUtils.i(
        "[TIMGroupAssistantListenerImpl] onGroupUpdate ! timGroupCacheInfo : " + timGroupCacheInfo);
  }
}
