//package youlin.xinhua.com.youlin.widget.chat.chatrow;
//
//import android.content.Context;
//import android.text.Spannable;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//import com.hyphenate.chat.EMMessage;
//import com.hyphenate.chat.EMTextMessageBody;
//import youlin.xinhua.com.im.utils.EaseSmileUtils;
//import youlin.xinhua.com.youlin.R;
//import youlin.xinhua.com.youlin.utils.LogUtils;
//
///**
// * <pre>
// * desc   : Todo
// * author : 罗顺翔
// * time   : 2017-07-31 17:07
// * version: 1.0
// * </pre>
// */
//
//public class EventMessageChatRow extends EaseChatRow {
//
//  TextView textEventMessage;
//
//  public EventMessageChatRow(Context context, EMMessage message, int position,
//      BaseAdapter adapter) {
//    super(context, message, position, adapter);
//  }
//
//  @Override protected void onInflateView() {
//    inflater.inflate(R.layout.item_chat_event_message, this);
//  }
//
//  @Override protected void onFindViewById() {
//    textEventMessage = (TextView) findViewById(R.id.text_event);
//  }
//
//  @Override protected void onSetUpView() {
//    EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
//
//    LogUtils.i(" EventMessageChatRow , msg : " + txtBody.getMessage());
//
//    Spannable span = EaseSmileUtils.getSmiledText(context, txtBody.getMessage());
//    // 设置内容
//    textEventMessage.setText(span, TextView.BufferType.SPANNABLE);
//  }
//
//  @Override protected void onUpdateView() {
//    adapter.notifyDataSetChanged();
//  }
//
//
//
//  @Override protected void onBubbleClick() {
//
//  }
//}
