///**
// * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * http://www.apache.org/licenses/LICENSE-2.0
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package youlin.xinhua.com.youlin.widget.chat.list;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.os.Handler;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ListView;
//import com.hyphenate.chat.EMClient;
//import com.hyphenate.chat.EMConversation;
//import com.hyphenate.chat.EMMessage;
//import youlin.xinhua.com.youlin.constant.EaseConstant;
//import youlin.xinhua.com.youlin.im.EaseCommonUtils;
//import youlin.xinhua.com.youlin.listener.MessageListItemClickListener;
//import youlin.xinhua.com.youlin.utils.LogUtils;
//import youlin.xinhua.com.youlin.widget.chat.chatrow.EaseChatRow;
//import youlin.xinhua.com.youlin.widget.chat.chatrow.EaseChatRowFile;
//import youlin.xinhua.com.youlin.widget.chat.chatrow.EaseChatRowImage;
//import youlin.xinhua.com.youlin.widget.chat.chatrow.EaseChatRowText;
//import youlin.xinhua.com.youlin.widget.chat.chatrow.EaseChatRowVoice;
//import youlin.xinhua.com.youlin.widget.chat.chatrow.EventMessageChatRow;
//import youlin.xinhua.com.youlin.widget.chat.chatrow.GroupInviteChatRow;
//
//public class EaseMessageAdapter extends BaseAdapter {
//
//  private final static String TAG = "msg";
//
//  private Context context;
//
//  private static final int HANDLER_MESSAGE_REFRESH_LIST = 0;
//  private static final int HANDLER_MESSAGE_SELECT_LAST  = 1;
//  private static final int HANDLER_MESSAGE_SEEK_TO      = 2;
//
//  private static final int MESSAGE_TYPE_RECV_TXT        = 0;
//  private static final int MESSAGE_TYPE_SENT_TXT        = 1;
//  private static final int MESSAGE_TYPE_SENT_IMAGE      = 2;
//  private static final int MESSAGE_TYPE_SENT_LOCATION   = 3;
//  private static final int MESSAGE_TYPE_RECV_LOCATION   = 4;
//  private static final int MESSAGE_TYPE_RECV_IMAGE      = 5;
//  private static final int MESSAGE_TYPE_SENT_VOICE      = 6;
//  private static final int MESSAGE_TYPE_RECV_VOICE      = 7;
//  private static final int MESSAGE_TYPE_SENT_VIDEO      = 8;
//  private static final int MESSAGE_TYPE_RECV_VIDEO      = 9;
//  private static final int MESSAGE_TYPE_SENT_FILE       = 10;
//  private static final int MESSAGE_TYPE_RECV_FILE       = 11;
//  private static final int MESSAGE_TYPE_SENT_EXPRESSION = 12;
//  private static final int MESSAGE_TYPE_RECV_EXPRESSION = 13;
//
//  public int itemTypeCount;
//
//  // reference to conversation object in chatsdk
//  private EMConversation conversation;
//  EMMessage[] messages = null;
//
//  private String toChatUsername;
//
//  private MessageListItemClickListener itemClickListener;
//
//  private ListView listView;
//
//  public EaseMessageAdapter(Context context, String username, int chatType, ListView listView) {
//    this.context = context;
//    this.listView = listView;
//    toChatUsername = username;
//    this.conversation = EMClient.getInstance()
//        .chatManager()
//        .getConversation(username, EaseCommonUtils.getConversationType(chatType), true);
//  }
//
//  Handler handler = new Handler() {
//    private void refreshList() {
//      // you should not call getAllMessages() in UI thread
//      // otherwise there is problem when refreshing UI and there is new message arrive
//      java.util.List<EMMessage> var = conversation.getAllMessages();
//      messages = var.toArray(new EMMessage[var.size()]);
//      conversation.markAllMessagesAsRead();
//      notifyDataSetChanged();
//    }
//
//    @Override public void handleMessage(android.os.Message message) {
//      switch (message.what) {
//        case HANDLER_MESSAGE_REFRESH_LIST:
//          refreshList();
//          break;
//        case HANDLER_MESSAGE_SELECT_LAST:
//          if (messages.length > 0) {
//            listView.setSelection(messages.length - 1);
//          }
//          break;
//        case HANDLER_MESSAGE_SEEK_TO:
//          int position = message.arg1;
//          listView.setSelection(position);
//          break;
//        default:
//          break;
//      }
//    }
//  };
//
//  public void refresh() {
//    if (handler.hasMessages(HANDLER_MESSAGE_REFRESH_LIST)) {
//      return;
//    }
//    android.os.Message msg = handler.obtainMessage(HANDLER_MESSAGE_REFRESH_LIST);
//    handler.sendMessage(msg);
//  }
//
//  /**
//   * refresh and select the last
//   */
//  public void refreshSelectLast() {
//    final int TIME_DELAY_REFRESH_SELECT_LAST = 100;
//    handler.removeMessages(HANDLER_MESSAGE_REFRESH_LIST);
//    handler.removeMessages(HANDLER_MESSAGE_SELECT_LAST);
//    handler.sendEmptyMessageDelayed(HANDLER_MESSAGE_REFRESH_LIST, TIME_DELAY_REFRESH_SELECT_LAST);
//    handler.sendEmptyMessageDelayed(HANDLER_MESSAGE_SELECT_LAST, TIME_DELAY_REFRESH_SELECT_LAST);
//  }
//
//  /**
//   * refresh and seek to the position
//   */
//  public void refreshSeekTo(int position) {
//    handler.sendMessage(handler.obtainMessage(HANDLER_MESSAGE_REFRESH_LIST));
//    android.os.Message msg = handler.obtainMessage(HANDLER_MESSAGE_SEEK_TO);
//    msg.arg1 = position;
//    handler.sendMessage(msg);
//  }
//
//  public EMMessage getItem(int position) {
//    if (messages != null && position < messages.length) {
//      return messages[position];
//    }
//    return null;
//  }
//
//  public long getItemId(int position) {
//    return position;
//  }
//
//  /**
//   * get count of messages
//   */
//  public int getCount() {
//    return messages == null ? 0 : messages.length;
//  }
//
//  /**
//   * get number of message type, here 14 = (EMMessage.Type) * 2
//   */
//  public int getViewTypeCount() {
//    //if (customRowProvider != null && customRowProvider.getCustomChatRowTypeCount() > 0) {
//    //  return customRowProvider.getCustomChatRowTypeCount() + 14;
//    //}
//    return 14;
//  }
//
//  /**
//   * get type of item
//   */
//  public int getItemViewType(int position) {
//    EMMessage message = getItem(position);
//    if (message == null) {
//      return -1;
//    }
//
//    if (message.getType() == EMMessage.Type.TXT) {
//      if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_BIG_EXPRESSION, false)) {
//        return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_EXPRESSION
//            : MESSAGE_TYPE_SENT_EXPRESSION;
//      }
//      return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TXT
//          : MESSAGE_TYPE_SENT_TXT;
//    }
//    if (message.getType() == EMMessage.Type.IMAGE) {
//      return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_IMAGE
//          : MESSAGE_TYPE_SENT_IMAGE;
//    }
//    if (message.getType() == EMMessage.Type.LOCATION) {
//      return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_LOCATION
//          : MESSAGE_TYPE_SENT_LOCATION;
//    }
//    if (message.getType() == EMMessage.Type.VOICE) {
//      return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_VOICE
//          : MESSAGE_TYPE_SENT_VOICE;
//    }
//    if (message.getType() == EMMessage.Type.VIDEO) {
//      return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_VIDEO
//          : MESSAGE_TYPE_SENT_VIDEO;
//    }
//    if (message.getType() == EMMessage.Type.FILE) {
//      return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_FILE
//          : MESSAGE_TYPE_SENT_FILE;
//    }
//
//    return -1;// invalid
//  }
//
//  protected EaseChatRow createChatRow(Context context, EMMessage message, int position) {
//    EaseChatRow chatRow = null;
//
//    switch (message.getType()) {
//      case TXT:
//        //if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_BIG_EXPRESSION, false)) {
//        //  chatRow = new EaseChatRowBigExpression(context, message, position, this);
//        //} else {
//        //  chatRow = new EaseChatRowText(context, message, position, this);
//        //}
//        LogUtils.i(" createChatRow , text Attr : " + message.getBooleanAttribute(
//            EaseConstant.MESSAGE_ATTR_GROUP_INVITE, false));
//        LogUtils.i(" createChatRow , text Attr : " + message.getBooleanAttribute(
//            EaseConstant.MESSAGE_ATTR_EVENT_MESSAGE, false));
//        if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_GROUP_INVITE, false)) {
//          // 群组邀请消息
//          chatRow = new GroupInviteChatRow(context, message, position, this);
//        } else if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_EVENT_MESSAGE, false)) {
//          // Event 事件消息
//          chatRow = new EventMessageChatRow(context, message, position, this);
//        } else {
//          chatRow = new EaseChatRowText(context, message, position, this);
//        }
//        break;
//      case LOCATION:
//        //chatRow = new EaseChatRowLocation(context, message, position, this);
//        break;
//      case FILE:
//        chatRow = new EaseChatRowFile(context, message, position, this);
//        break;
//      case IMAGE:
//        chatRow = new EaseChatRowImage(context, message, position, this);
//        break;
//      case VOICE:
//        chatRow = new EaseChatRowVoice(context, message, position, this);
//        break;
//      case VIDEO:
//        //chatRow = new EaseChatRowVideo(context, message, position, this);
//        break;
//      default:
//        throw new UnsupportedOperationException("不支持的Item类型");
//    }
//
//    return chatRow;
//  }
//
//  @SuppressLint("NewApi")
//  public View getView(final int position, View convertView, ViewGroup parent) {
//    EMMessage message = getItem(position);
//    if (convertView == null) {
//      convertView = createChatRow(context, message, position);
//    }
//
//    //refresh ui with messages
//    ((EaseChatRow) convertView).setUpView(message, position, itemClickListener);
//
//    return convertView;
//  }
//
//  public void setItemClickListener(MessageListItemClickListener listener) {
//    itemClickListener = listener;
//  }
//}
