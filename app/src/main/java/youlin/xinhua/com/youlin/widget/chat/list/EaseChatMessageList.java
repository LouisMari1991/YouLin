package youlin.xinhua.com.youlin.widget.chat.list;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.im.EaseCommonUtils;
import youlin.xinhua.com.youlin.listener.MessageListItemClickListener;


public class EaseChatMessageList extends RelativeLayout {

  protected static final String TAG = "EaseChatMessageList";
  protected ListView           listView;
  protected SwipeRefreshLayout swipeRefreshLayout;
  protected Context            context;
  protected EMConversation     conversation;
  protected int                chatType;
  protected String             toChatUsername;
  protected EaseMessageAdapter messageAdapter;

  public EaseChatMessageList(Context context, AttributeSet attrs, int defStyle) {
    this(context, attrs);
  }

  public EaseChatMessageList(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public EaseChatMessageList(Context context) {
    super(context);
    init(context);
  }

  private void init(Context context) {
    this.context = context;
    LayoutInflater.from(context).inflate(R.layout.ease_chat_message_list, this);
    swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.chat_swipe_layout);
    listView = (ListView) findViewById(R.id.list);
  }

  /**
   * init widget
   */
  public void init(String toChatUsername, int chatType) {
    this.chatType = chatType;
    this.toChatUsername = toChatUsername;

    conversation = EMClient.getInstance()
        .chatManager()
        .getConversation(toChatUsername, EaseCommonUtils.getConversationType(chatType), true);
    messageAdapter = new EaseMessageAdapter(context, toChatUsername, chatType, listView);
    // set message adapter
    listView.setAdapter(messageAdapter);

    refreshSelectLast();
  }

  /**
   * refresh
   */
  public void refresh() {
    if (messageAdapter != null) {
      messageAdapter.refresh();
    }
  }

  /**
   * refresh and jump to the last
   */
  public void refreshSelectLast() {
    if (messageAdapter != null) {
      messageAdapter.refreshSelectLast();
    }
  }

  /**
   * refresh and jump to the position
   */
  public void refreshSeekTo(int position) {
    if (messageAdapter != null) {
      messageAdapter.refreshSeekTo(position);
    }
  }

  public ListView getListView() {
    return listView;
  }

  public SwipeRefreshLayout getSwipeRefreshLayout() {
    return swipeRefreshLayout;
  }

  public EMMessage getItem(int position) {
    return messageAdapter.getItem(position);
  }

  /**
   * set click listener
   */
  public void setItemClickListener(MessageListItemClickListener listener) {
    if (messageAdapter != null) {
      messageAdapter.setItemClickListener(listener);
    }
  }
}
