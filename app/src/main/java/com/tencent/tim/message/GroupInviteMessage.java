package com.tencent.tim.message;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/07
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class GroupInviteMessage extends Message {

  @Override public String getSummary() {
    return null;
  }

  @Override protected void onBubbleClick() {

  }

  @Override protected void onSetUpView() {

  }

  @Override public int getItemType() {

    if (isSelf()) {
      return TYPE_GROUP_INVITE_SEND;
    } else {
      return TYPE_GROUP_INVITE_RECEIVER;
    }
  }
}
