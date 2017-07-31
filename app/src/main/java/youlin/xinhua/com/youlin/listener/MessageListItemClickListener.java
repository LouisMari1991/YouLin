package youlin.xinhua.com.youlin.listener;

import com.hyphenate.chat.EMMessage;

/**
 * <pre>
 * desc   : 消息列表Item点击事件
 * author : 罗顺翔
 * time   : 2017-07-31 14:47
 * version: 1.0
 * </pre>
 */

public interface MessageListItemClickListener {

  void onResendClick(EMMessage message);

  /**
   * there is default handling when bubble is clicked, if you want handle it, return true
   * another way is you implement in onBubbleClick() of chat row
   */
  boolean onBubbleClick(EMMessage message);

  void onBubbleLongClick(EMMessage message);

  void onUserAvatarClick(String username);

  void onUserAvatarLongClick(String username);
}
