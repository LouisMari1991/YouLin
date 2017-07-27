package youlin.xinhua.com.youlin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.bean.UserInfo;
import youlin.xinhua.com.youlin.utils.LogUtils;
import youlin.xinhua.com.youlin.widget.SwipeMenuLayout;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-20 10:37
 * version: 1.0
 * </pre>
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

  private final LayoutInflater mInfalter;
  private       List<UserInfo> mDatas;

  public ContactAdapter(Context context, List<UserInfo> datas) {
    mDatas = datas;
    mInfalter = LayoutInflater.from(context);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(mInfalter.inflate(R.layout.item_contact, parent, false));
  }

  @Override public void onBindViewHolder(final ViewHolder holder, final int position) {
    //SwipeMenuLayout itemView = (SwipeMenuLayout) holder.itemView;

    UserInfo userInfo = mDatas.get(position);
    holder.name.setText(userInfo.getMobilePhone());

    holder.itemContainer.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        LogUtils.i(" itemContainer onClick , position : " + position);
      }
    });

    holder.avatar.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        LogUtils.i(" avatar onClick , position : " + position);
      }
    });

    holder.btnDel.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        LogUtils.i(" btnDel onClick , position : " + position);
        ((SwipeMenuLayout) holder.itemView).quickClose();
        mDatas.remove(holder.getAdapterPosition());
        notifyDataSetChanged();
      }
    });
  }

  @Override public int getItemCount() {
    return null != mDatas ? mDatas.size() : 0;
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    View      itemContainer;
    ImageView avatar;
    TextView  name;
    Button    btnDel;

    ViewHolder(View itemView) {
      super(itemView);
      itemContainer = itemView.findViewById(R.id.item_container);
      avatar = (ImageView) itemView.findViewById(R.id.avatar);
      name = (TextView) itemView.findViewById(R.id.name);
      btnDel = (Button) itemView.findViewById(R.id.btn_del);
    }
  }
}
