package youlin.xinhua.com.youlin.widget.chat.chatrow;

import android.content.Context;
import android.text.Spannable;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.ChatType;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import youlin.xinhua.com.im.utils.EaseSmileUtils;
import youlin.xinhua.com.youlin.R;

public class EaseChatRowText extends EaseChatRow {

  private TextView contentView;

  public EaseChatRowText(Context context, EMMessage message, int position, BaseAdapter adapter) {
    super(context, message, position, adapter);
  }

  @Override protected void onInflateView() {
    inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ? R.layout.item_chat_received_text
        : R.layout.item_chat_send_text, this);
  }

  @Override protected void onFindViewById() {
    contentView = (TextView) findViewById(R.id.text_message_content);
  }

  @Override public void onSetUpView() {
    EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
    Spannable span = EaseSmileUtils.getSmiledText(context, txtBody.getMessage());
    // 设置内容
    contentView.setText(span, BufferType.SPANNABLE);

    handleTextMessage();
  }

  protected void handleTextMessage() {
    if (message.direct() == EMMessage.Direct.SEND) {
      setMessageSendCallback();
      switch (message.status()) {
        case CREATE:
          progressBar.setVisibility(View.GONE);
          statusView.setVisibility(View.VISIBLE);
          break;
        case SUCCESS:
          progressBar.setVisibility(View.GONE);
          statusView.setVisibility(View.GONE);
          break;
        case FAIL:
          progressBar.setVisibility(View.GONE);
          statusView.setVisibility(View.VISIBLE);
          break;
        case INPROGRESS:
          progressBar.setVisibility(View.VISIBLE);
          statusView.setVisibility(View.GONE);
          break;
        default:
          break;
      }
    } else {
      if (!message.isAcked() && message.getChatType() == ChatType.Chat) {
        try {
          EMClient.getInstance()
              .chatManager()
              .ackMessageRead(message.getFrom(), message.getMsgId());
        } catch (HyphenateException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override protected void onUpdateView() {
    adapter.notifyDataSetChanged();
  }

  @Override protected void onBubbleClick() {
    // TODO Auto-generated method stub

  }
}
