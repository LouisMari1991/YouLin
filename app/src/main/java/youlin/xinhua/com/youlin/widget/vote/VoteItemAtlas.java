package youlin.xinhua.com.youlin.widget.vote;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.bean.VoteItemBean;
import youlin.xinhua.com.youlin.utils.ToastUtils;

/**
 * <pre>
 * desc   : 图集投票
 * author : 罗顺翔
 * time   : 2017-09-05 15:37
 * version: 1.0
 * </pre>
 */

public class VoteItemAtlas extends AbsVoteItem {

  VoteItemBean.Question.Option[] mOptions;

  @BindView(R.id.ll_item_0)    View      item_0;
  @BindView(R.id.ll_item_1)    View      item_1;
  @BindView(R.id.text_title_0) TextView  textTitle_0;
  @BindView(R.id.text_title_1) TextView  textTitle_1;
  @BindView(R.id.text_num_0)   TextView  textNum_0;
  @BindView(R.id.text_num_1)   TextView  textNum_1;
  @BindView(R.id.img_cover_0)  ImageView imgCover_0;
  @BindView(R.id.img_cover_1)  ImageView imgCover_1;

  public VoteItemAtlas(Context context, int state, VoteItemBean.Question.Option... options) {
    super(context, options[0], state);
    mOptions = options;
  }

  @OnClick({
      R.id.fl_container_0, R.id.fl_container_1, R.id.btn_vote_0, R.id.btn_vote_1
  }) public void click(View view) {
    switch (view.getId()) {
      case R.id.fl_container_0: {
        ToastUtils.show("fl_container_0");
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onItemClick(mOptions[0]);
        }
      }
      break;
      case R.id.fl_container_1: {
        ToastUtils.show("fl_container_1");
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onItemClick(mOptions[1]);
        }
      }
      break;
      case R.id.btn_vote_0: {
        ToastUtils.show("btn_vote_0");
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onVoteClick(mOptions[0]);
        }
      }
      break;
      case R.id.btn_vote_1: {
        ToastUtils.show("btn_vote_1");
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onVoteClick(mOptions[1]);
        }
      }
      break;
      default:
        break;
    }
  }

  @Override protected int getLayoutId() {
    return R.layout.item_vote_atlas;
  }

  @Override protected void initView() {
    if (mOptions.length == 1) {
      item_1.setVisibility(INVISIBLE);
      VoteItemBean.Question.Option option = mOptions[0];
    }
  }

  @Override protected void updateView() {
    
  }
}
