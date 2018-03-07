package com.tencent.tim.message;

import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMSoundElem;

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

  /**
   * 语音消息构造方法
   *
   * @param duration 时长
   * @param filePath 语音数据地址
   */
  public VoiceMessage(long duration, String filePath) {
    message = new TIMMessage();
    TIMSoundElem elem = new TIMSoundElem();
    elem.setPath(filePath);
    elem.setDuration(duration);  //填写语音时长
    message.addElement(elem);
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
