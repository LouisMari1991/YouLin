package com.tencent.tim.message;

import com.chad.library.adapter.base.entity.MultiItemEntity;
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
public abstract class Message implements MultiItemEntity {

  public static final int TYPE_TEXT_SEND = 1;
  public static final int TYPE_TEXT_RECEIVE = 2;
  public static final int TYPE_IMG_SEND = 3;
  public static final int TYPE_IMG_RECEIVE = 4;
  public static final int TYPE_VOICE_SEND = 5;
  public static final int TYPE_VOICE_RECEIVE = 6;
  public static final int TYPE_GROUP_TIP = 7;
  public static final int TYPE_GROUP_INVITE_SEND = 8;
  public static final int TYPE_GROUP_INVITE_RECEIVER = 9;

  protected TIMMessage message;

  public Message() {
  }

  public Message(TIMMessage message) {
    this.message = message;
  }

  private boolean hasTime;

  /**
   * 消息描述信息
   */
  private String desc;

  public TIMMessage getMessage() {
    return message;
  }

  /**
   * 判断是否是自己发的
   */
  public boolean isSelf() {
    return message.isSelf();
  }

  /**
   * 获取消息摘要
   */
  public abstract String getSummary();

  /**
   * 获取发送者
   */
  public String getSender() {
    if (message.getSender() == null) {
      return "";
    }
    return message.getSender();
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public boolean isHasTime() {
    return hasTime;
  }

  public void setHasTime(boolean hasTime) {
    this.hasTime = hasTime;
  }
}
