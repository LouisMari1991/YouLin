package youlin.xinhua.com.youlin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.bean.UserInfo;
import youlin.xinhua.com.youlin.widget.CheckImageView;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-21 15:15
 * version: 1.0
 * </pre>
 */

public class ContactPickAdapter extends RecyclerView.Adapter<ContactPickAdapter.ItemViewHolder> {

  private final LayoutInflater mInfalter;
  private       List<UserInfo> mDatas;

  private Map<String, UserInfo> map = new HashMap<>();

  public ContactPickAdapter(Context context, List<UserInfo> datas) {
    mDatas = datas;
    mInfalter = LayoutInflater.from(context);
  }

  @Override public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ItemViewHolder(mInfalter.inflate(R.layout.item_checkbox, parent, false));
  }

  @Override public void onBindViewHolder(final ItemViewHolder holder, final int position) {

    holder.checkBox.setChecked(isItemChecked(position));

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (isItemChecked(holder.getAdapterPosition())) {
          setItemChecked(holder.getAdapterPosition(), false);
          holder.checkBox.setChecked(false);
        } else {
          setItemChecked(holder.getAdapterPosition(), true);
          holder.checkBox.setChecked(true);
        }
      }
    });

    //holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    //  @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    //    LogUtils.i(" checkBox , position : " + position);
    //  }
    //});

    holder.checkBox.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (isItemChecked(holder.getAdapterPosition())) {
          setItemChecked(holder.getAdapterPosition(), false);
          holder.checkBox.setChecked(false);
        } else {
          setItemChecked(holder.getAdapterPosition(), true);
          holder.checkBox.setChecked(true);
        }
      }
    });

    holder.name.setText(mDatas.get(position).getMobilePhone());
  }

  @Override public int getItemCount() {
    return mDatas.size();
  }

  class ItemViewHolder extends RecyclerView.ViewHolder {

    CheckImageView checkBox;
    TextView       name;

    public ItemViewHolder(View itemView) {
      super(itemView);
      checkBox = (CheckImageView) itemView.findViewById(R.id.checkbox);
      name = (TextView) itemView.findViewById(R.id.name);
    }
  }

  SparseBooleanArray mSelectedPositions = new SparseBooleanArray();

  private void setItemChecked(int position, boolean isChecked) {
    mSelectedPositions.put(position, isChecked);
  }

  //根据位置判断条目是否选中
  private boolean isItemChecked(int position) {
    return mSelectedPositions.get(position);
  }
}
