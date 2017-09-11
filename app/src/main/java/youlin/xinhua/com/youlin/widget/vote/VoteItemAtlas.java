package youlin.xinhua.com.youlin.widget.vote;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.bean.VoteItemBean;

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
  @BindView(R.id.btn_vote_0)   Button    btnVote_0;
  @BindView(R.id.btn_vote_1)   Button    btnVote_1;

  public VoteItemAtlas(Context context, int state, VoteItemBean.Question.Option... options) {
    super(context, options[0], state);
    mOptions = options;
    initView();
  }

  @OnClick({
      R.id.fl_container_0, R.id.fl_container_1, R.id.btn_vote_0, R.id.btn_vote_1
  }) public void click(View view) {
    switch (view.getId()) {
      case R.id.fl_container_0: {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onItemClick(mOptions[0]);
        }
      }
      break;
      case R.id.fl_container_1: {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onItemClick(mOptions[1]);
        }
      }
      break;
      case R.id.btn_vote_0: {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onVoteClick(mOptions[0]);
        }
      }
      break;
      case R.id.btn_vote_1: {
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
    updateView();
  }

  public void notifyDataChanged(int state, VoteItemBean.Question.Option... options) {
    mVoteState = state;
    mOptions = options;
    updateView();
  }

  @Override protected void updateView() {
    if (mOptions.length == 1) {
      item_1.setVisibility(INVISIBLE);
    } else {
      item_1.setVisibility(VISIBLE);
    }

    VoteItemBean.Question.Option option0 = mOptions[0];
    textTitle_0.setText(option0.getOptionContent());
    // Todo 封面图加载

    if (mVoteState != 2) {
      if (option0.getIsVote() == 0) {
        btnVote_0.setEnabled(true);
        textNum_0.setText(getResources().getString(R.string.num_of_ticket, 0));
      } else {
        btnVote_0.setEnabled(false);
        textNum_0.setText(
            getResources().getString(R.string.num_of_ticket, option0.getAnswerTotal()));
      }
    } else {
      btnVote_0.setEnabled(false);
      textNum_0.setText(getResources().getString(R.string.num_of_ticket, option0.getAnswerTotal()));
    }

    if (mOptions.length == 1) {
      return;
    }

    VoteItemBean.Question.Option option1 = mOptions[1];

    textTitle_1.setText(option1.getOptionContent());
    // Todo 封面图

    if (mVoteState != 2) {
      if (option1.getIsVote() == 0) {
        btnVote_1.setEnabled(true);
        textNum_1.setText(getResources().getString(R.string.num_of_ticket, 0));
      } else {
        btnVote_1.setEnabled(false);
        textNum_1.setText(
            getResources().getString(R.string.num_of_ticket, option1.getAnswerTotal()));
      }
    } else {
      btnVote_1.setEnabled(false);
      textNum_1.setText(getResources().getString(R.string.num_of_ticket, option1.getAnswerTotal()));
    }
  }
}
