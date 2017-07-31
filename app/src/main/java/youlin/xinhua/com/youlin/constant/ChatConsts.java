package youlin.xinhua.com.youlin.constant;

/**
 * <pre>
 *     desc   : 聊天相关常量
 *     author : 罗顺翔
 *     time   : 2017-07-06 17:36
 *     version: 1.0
 * </pre>
 */

public interface ChatConsts {

  /**
   * 用户id,使用手机号
   */
  String USER_ID = "userId";

  /**
   * 聊天类型:单聊,群聊,聊天室,群组(暂不支持)
   */
  String CHAT_TYPE = "chatType";

  /**
   * 当前用户登录是否异常: 1.用户被删除; 2.用户重复登录; 3.用户登录受限
   */
  String IS_CONFLICT = "isConflict";



}
