package youlin.xinhua.com.youlin.widget.vote;

import android.content.Context;
import android.view.View;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.bean.VoteItemBean;
import youlin.xinhua.com.youlin.utils.ToastUtils;

/**
 * <pre>
 * desc   : 人物投票
 * author : 罗顺翔
 * time   : 2017-09-05 17:31
 * version: 1.0
 * </pre>
 */

public class VoteItemCharacter extends AbsVoteItem {

  int mIndex;

  public VoteItemCharacter(Context context, VoteItemBean.Question.Option option, int state,
      int index) {
    super(context, option, state);
    mIndex = index;
  }

  @OnClick({ R.id.item_container, R.id.btn_vote }) public void click(View view) {
    switch (view.getId()) {
      case R.id.item_container: {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onItemClick(mOption);
        }
        ToastUtils.show("详情");
      }
      break;
      case R.id.btn_vote: {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onVoteClick(mOption);
        }
        ToastUtils.show("投票");
      }
      break;
    }
  }

  @Override public int getLayoutId() {
    return R.layout.item_vote_character;
  }

  @Override protected void initView() {

  }

  @Override protected void updateView() {

  }
}
