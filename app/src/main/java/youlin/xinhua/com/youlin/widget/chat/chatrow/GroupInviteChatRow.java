package youlin.xinhua.com.youlin.widget.chat.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hyphenate.chat.EMMessage;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.constant.EaseConstant;
import youlin.xinhua.com.youlin.utils.ImageLoader;
import youlin.xinhua.com.youlin.utils.LogUtils;

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
    inflater.inflate(R.layout.item_chat_received_group_invite, this);
  }

  @Override protected void onFindViewById() {
    inviteContent = (TextView) findViewById(R.id.text_invite_content);
    groupAvatar = (ImageView) findViewById(R.id.image);
    LogUtils.i(" onFindViewById , groupAvatar : " + groupAvatar);
  }

  @Override protected void onSetUpView() {
    //EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();

    groupId = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_VALUE_GROUP_ID, "");
    groupName = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_VALUE_GROUP_NAME, "");
    groupUrl = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_VALUE_GROUP_REASON_AVATAR, "");

    LogUtils.i(" onSetUpView , groupUrl : " + groupUrl + " , groupAvatar : " + groupAvatar);
    inviteContent.setText(getResources().getString(R.string.row_group_invite, groupName));
    ImageLoader.displayChatRowPicture(groupUrl, groupAvatar);
  }

  @Override protected void onUpdateView() {
    adapter.notifyDataSetChanged();
  }

  @Override protected void onBubbleClick() {

  }
}
