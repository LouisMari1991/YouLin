//package youlin.xinhua.com.youlin.widget.chat.chatrow;
//
//import android.content.Context;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import com.hyphenate.chat.EMMessage;
//import youlin.xinhua.com.youlin.R;
//import youlin.xinhua.com.youlin.constant.EaseConstant;
//import youlin.xinhua.com.youlin.utils.ImageLoader;
//import youlin.xinhua.com.youlin.utils.LogUtils;
//
///**
// * <pre>
// * desc   : Todo
// * author : 罗顺翔
// * time   : 2017-07-31 14:57
// * version: 1.0
// * </pre>
// */
//
//public class GroupInviteChatRow extends EaseChatRow {
//
//  TextView  inviteContent;
//  ImageView groupAvatar;
//
//  TextView textTitle;
//  TextView textContent;
//
//  String groupId;
//  String groupName;
//  String groupUrl;
//
//  boolean isMeetGroupInvite;
//
//  public GroupInviteChatRow(Context context, EMMessage message, int position, BaseAdapter adapter) {
//    super(context, message, position, adapter);
//  }
//
//  @Override protected void onInflateView() {
//    isMeetGroupInvite =
//        message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_MEETING_GTOUP, false);
//    inflater.inflate(isMeetGroupInvite ? R.layout.item_chat_received_meeting_group_invite
//        : R.layout.item_chat_received_group_invite, this);
//  }
//
//  @Override protected void onFindViewById() {
//    if (isMeetGroupInvite) {
//      textTitle = (TextView) findViewById(R.id.text_title);
//      textContent = (TextView) findViewById(R.id.text_content);
//    } else {
//      groupAvatar = (ImageView) findViewById(R.id.image);
//      inviteContent = (TextView) findViewById(R.id.text_invite_content);
//    }
//  }
//
//  @Override protected void onSetUpView() {
//
//    if (isMeetGroupInvite) {
//      String title = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_VALUE_MEETING_TITLE, "");
//      String content =
//          message.getStringAttribute(EaseConstant.MESSAGE_ATTR_VALUE_MEETING_CONTENT, "");
//      textTitle.setText(title);
//      textContent.setText(content);
//    } else {
//      groupId = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_VALUE_GROUP_ID, "");
//      groupName = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_VALUE_GROUP_NAME, "");
//      groupUrl =
//          message.getStringAttribute(EaseConstant.MESSAGE_ATTR_VALUE_GROUP_REASON_AVATAR, "");
//
//      LogUtils.i(" onSetUpView , groupUrl : " + groupUrl + " , groupAvatar : " + groupAvatar);
//      inviteContent.setText(getResources().getString(R.string.row_group_invite, groupName));
//      ImageLoader.displayChatRowPicture(groupUrl, groupAvatar);
//    }
//  }
//
//  @Override protected void onUpdateView() {
//    adapter.notifyDataSetChanged();
//  }
//
//  @Override protected void onBubbleClick() {
//
//  }
//}
