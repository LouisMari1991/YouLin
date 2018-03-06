package youlin.xinhua.com.youlin.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.tencent.tim.message.Message;
import java.util.List;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/06
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class ChatAdapter extends BaseMultiItemQuickAdapter<Message, MessageViewHolder<Message>> {

  public ChatAdapter(@Nullable List<Message> data) {
    super(data);
  }

  @Override protected void convert(MessageViewHolder<Message> helper, Message item) {

  }
}


