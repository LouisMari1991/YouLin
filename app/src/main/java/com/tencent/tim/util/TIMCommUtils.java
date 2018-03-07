package com.tencent.tim.util;

import com.tencent.imsdk.TIMConversationType;
import youlin.xinhua.com.youlin.constant.EaseConstant;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/07
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class TIMCommUtils {

  /**
   * change the chat type to EMConversationType
   */
  public static TIMConversationType getConversationType(int chatType) {
    if (chatType == EaseConstant.CHATTYPE_SINGLE) {
      return TIMConversationType.C2C;
    } else if (chatType == EaseConstant.CHATTYPE_GROUP) {
      return TIMConversationType.Group;
    } else {
      return TIMConversationType.Invalid;
    }
  }

}
