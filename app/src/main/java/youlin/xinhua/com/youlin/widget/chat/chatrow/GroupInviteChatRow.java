package youlin.xinhua.com.youlin.widget.chat.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.constant.EaseConstant;
import youlin.xinhua.com.youlin.utils.ImageLoader;
import youlin.xinhua.com.youlin.utils.ToastUtils;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-31 14:57
 * version: 1.0
 * </pre>
 */

public class GroupInviteChatRow extends EaseChatRow {

  TextView  inviteContent;
  ImageView groupAvatar;

  String groupId;
  String groupName;
  String groupUrl;

  public GroupInviteChatRow(Context context, EMMessage message, int position, BaseAdapter adapter) {
    super(context, message, position, adapter);
  }

  @Override protected void onInflateView() {
    inflater.inflate(R.layout.item_chat_received_group_invite, this, false);
  }

  @Override protected void onFindViewById() {
    inviteContent = (TextView) findViewById(R.id.text_invite_content);
    groupAvatar = (ImageView) findViewById(R.id.image);
  }

  @Override protected void onSetUpView() {
    EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();

    ToastUtils.showToast(txtBody.getMessage());

    groupId = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_VALUE_GROUP_ID, "");
    groupName = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_VALUE_GROUP_NAME, "");
    groupUrl = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_VALUE_GROUP_REASON_AVATAR, "");

    ImageLoader.displayChatRowPicture(groupUrl, groupAvatar);
  }

  @Override protected void onUpdateView() {
    adapter.notifyDataSetChanged();
  }

  @Override protected void onBubbleClick() {
    ToastUtils.showToast("群组邀请点击事件, groupName : " + groupName);
  }
}
