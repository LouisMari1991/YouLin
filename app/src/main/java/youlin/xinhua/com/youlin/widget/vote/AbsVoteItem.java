package youlin.xinhua.com.youlin.widget.vote;

import android.content.Context;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import youlin.xinhua.com.youlin.bean.VoteItemBean;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-06 11:27
 * version: 1.0
 * </pre>
 */

public abstract class AbsVoteItem extends LinearLayout {

  int mVoteState;

  VoteItemBean.Question.Option mOption;

  OnItemClickListener mOnItemClickListener;

  public AbsVoteItem(Context context, VoteItemBean.Question.Option option, int state) {
    super(context);
    mOption = option;
    mVoteState = state;
    inflate(context, getLayoutId(), this);
    ButterKnife.bind(this);
    initView();
  }

  protected abstract int getLayoutId();

  protected abstract void initView();

  protected abstract void updateView();

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }

  public interface OnItemClickListener {
    void onItemClick(VoteItemBean.Question.Option option);

    void onVoteClick(VoteItemBean.Question.Option option);
  }
}
