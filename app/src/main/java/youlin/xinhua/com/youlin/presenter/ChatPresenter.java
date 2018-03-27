package youlin.xinhua.com.youlin.presenter;

import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.message.TIMConversationExt;
import java.util.List;
import youlin.xinhua.com.youlin.presenter.view.IChatView;
import youlin.xinhua.com.youlin.utils.LogUtils;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/07
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class ChatPresenter {

  IChatView view;
  TIMConversation conversation;

  private final int pageSize = 20;

  private boolean isGetingMessage;

  public ChatPresenter(IChatView view, String identify, TIMConversationType type) {
    this.view = view;
    conversation = TIMManager.getInstance().getConversation(type, identify);
  }

  /**
   * 加载页面逻辑
   */
  public void start() {
    //注册消息监听
    getMessage(null);
    TIMConversationExt timConversationExt = new TIMConversationExt(conversation);
    if (timConversationExt.hasDraft()) {
      //view.showDraft(timConversationExt.getDraft());
    }
  }

  /**
   * 发送消息
   *
   * @param message 发送的消息
   */
  public void sendMessage(final TIMMessage message) {
    conversation.sendMessage(message, new TIMValueCallBack<TIMMessage>() {
      @Override public void onError(int code, String desc) {//发送消息失败
        //错误码code和错误描述desc，可用于定位请求失败原因
        //错误码code含义请参见错误码表
        //view.onSendMessageFail(code, desc, message);
      }

      @Override public void onSuccess(TIMMessage msg) {
        //发送消息成功,消息状态已在sdk中修改，此时只需更新界面
        //MessageEvent.getInstance().onNewMessage(null);
      }
    });
    //message对象为发送中状态
    //MessageEvent.getInstance().onNewMessage(message);
  }

  /**
   * 获取消息
   *
   * @param message 最后一条消息
   */
  public void getMessage(TIMMessage message) {
    if (!isGetingMessage) {
      isGetingMessage = true;
      TIMConversationExt timConversationExt = new TIMConversationExt(conversation);
      timConversationExt.getMessage(pageSize, message, new TIMValueCallBack<List<TIMMessage>>() {
        @Override public void onError(int i, String s) {
          isGetingMessage = false;
          LogUtils.e("get message error" + s);
        }

        @Override public void onSuccess(List<TIMMessage> timMessages) {
          isGetingMessage = false;
          LogUtils.i("onSuccess , " + timMessages.size());
          view.showMessage(timMessages);
        }
      });
    }
  }
}
