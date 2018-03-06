package youlin.xinhua.com.youlin.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tencent.tim.message.Message;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/06
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class MessageViewHolder<MessageImpl extends Message> extends BaseViewHolder {

  MessageImpl impl;

  public MessageViewHolder(View view) {
    super(view);
    String avatarUrl;
    String nickname;
    if (impl.isSelf()) {

    } else {

    }
  }
}
