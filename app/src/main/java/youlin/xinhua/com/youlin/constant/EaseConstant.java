/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package youlin.xinhua.com.youlin.constant;

public interface EaseConstant {

  String MESSAGE_ATTR_IS_VOICE_CALL = "is_voice_call";
  String MESSAGE_ATTR_IS_VIDEO_CALL = "is_video_call";

  /**
   * gif图片
   */
  String MESSAGE_ATTR_IS_BIG_EXPRESSION = "em_is_big_expression";
  /**
   * 表情或gif图片资源id
   */
  String MESSAGE_ATTR_EXPRESSION_ID     = "em_expression_id";

  /**
   * 群聊@
   */
  String MESSAGE_ATTR_AT_MSG           = "em_at_list";
  /**
   * 群聊@ALL
   */
  String MESSAGE_ATTR_VALUE_AT_MSG_ALL = "ALL";

  /**
   * event类型事件: 群成员加入,群成员离开, 成为好友关系
   */
  String MESSAGE_ATTR_EVENT_MESSAGE = "Event";

  /**
   * 群组邀请消息,本地生成此消息 {@see youlin.xinhua.com.youlin.im.IMPlatform#MyGroupChangeListener}
   */
  String MESSAGE_ATTR_GROUP_INVITE = "Group_Invite";

  /**
   * GROUP_NAME
   */
  String MESSAGE_ATTR_VALUE_GROUP_NAME = "Group_Name";

  String MESSAGE_ATTR_VALUE_GROUP_ID = "Group_ID";

  String MESSAGE_ATTR_VALUE_GROUP_REASON_AVATAR = "Group_Reason_Avatar";

  /**
   * 单聊
   */
  int CHATTYPE_SINGLE   = 1;
  /**
   * 群聊
   */
  int CHATTYPE_GROUP    = 2;
  /**
   * 聊天室
   */
  int CHATTYPE_CHATROOM = 3;

  String EXTRA_CHAT_TYPE = "chatType";
  String EXTRA_USER_ID   = "userId";
  String EXTRA_IMG_URL = "imageUrl";

  String NEW_FRIENDS_USERNAME       = "item_new_friends";
  String GROUP_USERNAME             = "item_groups";
  String CHAT_ROOM                  = "item_chatroom";
  String ACCOUNT_REMOVED            = "account_removed";
  String ACCOUNT_CONFLICT           = "conflict";
  String ACCOUNT_FORBIDDEN          = "user_forbidden";
  String CHAT_ROBOT                 = "item_robots";
  String MESSAGE_ATTR_ROBOT_MSGTYPE = "msgtype";
  String ACTION_GROUP_CHANAGED      = "action_group_changed";
  String ACTION_CONTACT_CHANAGED    = "action_contact_changed";

  /**
   * 群组同步key
   */
  String SHARED_KEY_SETTING_GROUPS_SYNCED    = "SHARED_KEY_SETTING_GROUPS_SYNCED";
  /**
   * 联系人同步key
   */
  String SHARED_KEY_SETTING_CONTACT_SYNCED   = "SHARED_KEY_SETTING_CONTACT_SYNCED";
  /**
   * 黑名单同步key
   */
  String SHARED_KEY_SETTING_BALCKLIST_SYNCED = "SHARED_KEY_SETTING_BALCKLIST_SYNCED";
}
