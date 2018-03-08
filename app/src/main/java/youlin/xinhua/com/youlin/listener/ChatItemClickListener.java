package youlin.xinhua.com.youlin.listener;

import com.tencent.tim.message.Message;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/08
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public interface ChatItemClickListener {

  void onResendClick(Message message);

  /**
   * there is default handling when bubble is clicked, if you want handle it, return true
   * another way is you implement in onBubbleClick() of chat row
   */
  boolean onBubbleClick(Message message);

  void onBubbleLongClick(Message message);

  void onUserAvatarClick(String username);

  void onUserAvatarLongClick(String username);
}
