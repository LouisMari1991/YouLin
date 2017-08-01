package youlin.xinhua.com.im.widget;

import com.hyphenate.EMContactListener;

/**
 * <pre>
 * desc   : 联系人改变
 * author : 罗顺翔
 * time   : 2017-07-21 11:53
 * version: 1.0
 * </pre>
 */

public class EMContactListenerAdapter implements EMContactListener {

  /**
   * 增加联系人时回调此方法
   *
   * @param username 增加的联系人
   */
  @Override public void onContactAdded(String username) {

  }

  /**
   * 被删除时回调此方法
   *
   * @param username 删除的联系人
   */
  @Override public void onContactDeleted(String username) {

  }

  /**
   * 收到好友邀请
   *
   * @param username 发起加为好友用户的名称
   * @param reason 对方发起好友邀请时发出的文字性描述
   */
  @Override public void onContactInvited(String username, String reason) {

  }

  /**
   * 好友请求被同意
   *
   * @param username 用户的名称
   */
  @Override public void onFriendRequestAccepted(String username) {

  }

  /***
   * 好友请求被拒绝
   * @param username 用户的名称
   */
  @Override public void onFriendRequestDeclined(String username) {

  }
}
