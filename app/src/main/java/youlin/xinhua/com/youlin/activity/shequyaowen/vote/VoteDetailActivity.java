package youlin.xinhua.com.youlin.activity.shequyaowen.vote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.widget.statusbar.StatusBarHelper;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-05 09:13
 * version: 1.0
 * </pre>
 */

public class VoteDetailActivity extends BaseActivity {

  @BindView(R.id.fl_container) FrameLayout  mFLContainer;
  @BindView(R.id.rv_list)      RecyclerView mRecyclerView;

  RecyclerView.Adapter mAdapter;

  public static void launch(Context context) {
    Intent intent = new Intent(context, VoteDetailActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_vote_detail;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    StatusBarHelper.setColor(this, ContextCompat.getColor(this, R.color.white));

    LinearLayout ll = new LinearLayout(this);
    ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT));
    ll.setOrientation(LinearLayout.VERTICAL);
    LayoutInflater inflater = LayoutInflater.from(this);
    for (int i = 0; i < 10; i++) {
      //VoteNormalItem item = new VoteNormalItem(this);
      View item = inflater.inflate(R.layout.item_vote_atlas, ll, false);
      ll.addView(item);
    }
    mFLContainer.addView(ll);

    LinearLayoutManager
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    mRecyclerView.setLayoutManager(manager);
    mAdapter = new RecyclerView.Adapter() {
      @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new Holder(view);
      }

      @Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Holder holder = (Holder) viewHolder;
        holder.mTextView.setText("current position: " + position);
      }

      @Override public int getItemCount() {
        return 10;
      }
    };
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setNestedScrollingEnabled(false);
    mRecyclerView.setHasFixedSize(false);
  }

  @Override protected void onResume() {
    super.onResume();
    mRecyclerView.setFocusable(false);
  }

  static class Holder extends RecyclerView.ViewHolder {
    TextView mTextView;

    public Holder(View itemView) {
      super(itemView);
      mTextView = (TextView) itemView.findViewById(R.id.tv);
    }
  }

}
