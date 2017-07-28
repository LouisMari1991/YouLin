package youlin.xinhua.com.youlin.widget.emoj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import youlin.xinhua.com.im.domain.EaseEmojicon;
import youlin.xinhua.com.im.model.EaseDefaultEmojiconDatas;
import youlin.xinhua.com.im.utils.EaseSmileUtils;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-28 10:53
 * version: 1.0
 * </pre>
 */

public class EmojAdapter extends BaseAdapter {

  private final LayoutInflater mLayoutInflater;
  private final EaseEmojicon[] EmojIcons = EaseDefaultEmojiconDatas.getData();

  private SelectEmojView.EmojMenuClickListener mListener;

  public EmojAdapter(Context context) {
    mLayoutInflater = LayoutInflater.from(context);
  }

  @Override public int getCount() {
    return EmojIcons.length;
  }

  @Override public EaseEmojicon getItem(int position) {
    return EmojIcons[position];
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(final int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    if (convertView == null) {
      convertView = mLayoutInflater.inflate(R.layout.item_emoji_select, parent, false);
      holder = new ViewHolder(convertView);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    convertView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (mListener != null) {
          EaseEmojicon emojicon = EmojIcons[position];
          mListener.onExpressionClick(EaseSmileUtils.getSmiledText(v.getContext(), emojicon.getEmojiText()));
        }
      }
    });
    EaseEmojicon emojicon = EmojIcons[position];
    holder.imgEmoj.setImageResource(emojicon.getIcon());
    return convertView;
  }

  private class ViewHolder {
    ImageView imgEmoj;

    public ViewHolder(View itemView) {
      imgEmoj = (ImageView) itemView.findViewById(R.id.img_emoj);
    }
  }

  public void setEmojMenuClickListener(SelectEmojView.EmojMenuClickListener listener) {
    this.mListener = listener;
  }
}
