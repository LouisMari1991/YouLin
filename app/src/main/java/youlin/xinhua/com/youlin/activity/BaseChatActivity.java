package youlin.xinhua.com.youlin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageStatus;
import com.tencent.tim.consts.TIMConsts;
import com.tencent.tim.message.Message;
import com.tencent.tim.message.MessageFactory;
import com.tencent.tim.util.TIMCommUtils;
import java.util.ArrayList;
import java.util.List;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.adapter.ChatAdapter;
import youlin.xinhua.com.youlin.constant.EaseConstant;
import youlin.xinhua.com.youlin.presenter.ChatPresenter;
import youlin.xinhua.com.youlin.presenter.view.IChatView;
import youlin.xinhua.com.youlin.utils.RecyclerViewHelper;
import youlin.xinhua.com.youlin.widget.chat.ChatView;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-08-01 10:11
 * version: 1.0
 * </pre>
 */

public class BaseChatActivity extends BaseActivity implements IChatView {

  protected List<Message> messageList = new ArrayList<>();

  int chatType = EaseConstant.CHATTYPE_SINGLE;
  String toChatUsername = TIMConsts.PHONE_181;

  @BindView(R.id.chat_view) ChatView mChatView;

  protected RecyclerView recyclerView;

  protected ChatAdapter adapter;

  protected ChatPresenter mPresenter;

  boolean isloading;

  boolean haveMoreData = true;

  @Override protected int attachLayoutRes() {
    return R.layout.activity_im_chat;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter =
        new ChatPresenter(this, toChatUsername, TIMCommUtils.getConversationType(chatType));
    initViews();
    mPresenter.start();
  }

  public void initViews() {
    adapter = new ChatAdapter(messageList);
    mChatView.initModule();
    recyclerView = mChatView.getMessageListView();
    RecyclerViewHelper.initRecyclerViewV(this, recyclerView, adapter);
    adapter.setUpFetchEnable(true);
  }

  /**
   * 显示消息
   */
  @Override public void showMessage(List<TIMMessage> messages) {

    int newMsgNum = 0;
    for (int i = 0; i < messages.size(); ++i) {
      Message mMessage = MessageFactory.getMessage(messages.get(i));

      if (mMessage == null || messages.get(i).status() == TIMMessageStatus.HasDeleted) {
        continue;
      }

      //// 自定义消息
      //if (mMessage instanceof CustomMessage && (((CustomMessage) mMessage).getType()
      //    == CustomMessage.Type.TYPING
      //    || ((CustomMessage) mMessage).getType() == CustomMessage.Type.INVALID)) {
      //  continue;
      //}

      ++newMsgNum;
      if (i != messages.size() - 1) {
        mMessage.setHasTime(messages.get(i + 1));
        messageList.add(0, mMessage);
      } else {
        mMessage.setHasTime(null);
        messageList.add(0, mMessage);
      }
    }
    adapter.notifyDataSetChanged();

    recyclerView.scrollToPosition(adapter.getItemCount() - 1);

    //listView.setSelection(newMsgNum);
  }
}
