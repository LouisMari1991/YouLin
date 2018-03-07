package youlin.xinhua.com.youlin.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/06
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class MessageViewHolder extends BaseViewHolder {

  TextView textTimestamp;
  TextView textName;
  ImageView imgAvatar;

  public MessageViewHolder(View view) {
    super(view);
    textTimestamp = (TextView) view.findViewById(R.id.timestamp);
    textName = (TextView) view.findViewById(R.id.text_name);
    imgAvatar = (ImageView) view.findViewById(R.id.avatar);
  }




}
