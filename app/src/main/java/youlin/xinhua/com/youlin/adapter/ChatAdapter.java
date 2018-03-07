package youlin.xinhua.com.youlin.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.tencent.tim.message.Message;
import java.util.List;
import youlin.xinhua.com.youlin.R;

import static com.tencent.tim.message.Message.TYPE_EVENT;
import static com.tencent.tim.message.Message.TYPE_GROUP_INVITE_RECEIVER;
import static com.tencent.tim.message.Message.TYPE_GROUP_INVITE_SEND;
import static com.tencent.tim.message.Message.TYPE_GROUP_TIP;
import static com.tencent.tim.message.Message.TYPE_IMG_RECEIVE;
import static com.tencent.tim.message.Message.TYPE_IMG_SEND;
import static com.tencent.tim.message.Message.TYPE_MEETING_INVITE_RECEIVER;
import static com.tencent.tim.message.Message.TYPE_MEETING_INVITE_SEND;
import static com.tencent.tim.message.Message.TYPE_TEXT_RECEIVE;
import static com.tencent.tim.message.Message.TYPE_TEXT_SEND;
import static com.tencent.tim.message.Message.TYPE_VOICE_RECEIVE;
import static com.tencent.tim.message.Message.TYPE_VOICE_SEND;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/06
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class ChatAdapter extends BaseMultiItemQuickAdapter<Message, MessageViewHolder> {

  public ChatAdapter(@Nullable List<Message> data) {
    super(data);
    addItemType(TYPE_TEXT_SEND, R.layout.item_chat_send_text);
    addItemType(TYPE_TEXT_RECEIVE, R.layout.item_chat_received_text);
    addItemType(TYPE_IMG_SEND, R.layout.item_chat_send_picture);
    addItemType(TYPE_IMG_RECEIVE, R.layout.item_chat_received_picture);
    addItemType(TYPE_VOICE_SEND, R.layout.item_chat_send_voice);
    addItemType(TYPE_VOICE_RECEIVE, R.layout.item_chat_received_voice);
    addItemType(TYPE_GROUP_TIP, R.layout.item_chat_event_message);
    addItemType(TYPE_GROUP_INVITE_SEND, R.layout.item_chat_received_group_invite_send);
    addItemType(TYPE_GROUP_INVITE_RECEIVER, R.layout.item_chat_received_group_invite);
    addItemType(TYPE_MEETING_INVITE_SEND, R.layout.item_chat_received_meeting_group_invite_send);
    addItemType(TYPE_MEETING_INVITE_RECEIVER, R.layout.item_chat_received_meeting_group_invite);
    addItemType(TYPE_EVENT, R.layout.item_chat_event_message);
  }

  @Override protected void convert(MessageViewHolder helper, Message item) {

  }
}


