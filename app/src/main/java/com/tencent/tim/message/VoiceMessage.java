package com.tencent.tim.message;

import com.tencent.imsdk.TIMMessage;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/02/23
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class VoiceMessage extends Message {

  public VoiceMessage(TIMMessage message) {
    super(message);
  }

  @Override public String getSummary() {
    return null;
  }

  @Override public int getItemType() {
    if (isSelf()) {
      return TYPE_VOICE_SEND;
    } else {
      return TYPE_VOICE_RECEIVE;
    }
  }
}
