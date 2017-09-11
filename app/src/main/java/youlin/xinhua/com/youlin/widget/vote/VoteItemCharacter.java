package youlin.xinhua.com.youlin.widget.vote;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.bean.VoteItemBean;

/**
 * <pre>
 * desc   : 人物投票
 * author : 罗顺翔
 * time   : 2017-09-05 17:31
 * version: 1.0
 * </pre>
 */

public class VoteItemCharacter extends AbsVoteItem {

  @BindView(R.id.img_index)  ImageView imgIndex;
  @BindView(R.id.text_index) TextView  textIndex;
  @BindView(R.id.img_avatar) ImageView imgAvatar;
  @BindView(R.id.text_title) TextView  textTitle;
  @BindView(R.id.text_count) TextView  textCount;
  @BindView(R.id.btn_vote)   TextView  btnVote;

  int mIndex;

  public VoteItemCharacter(Context context, VoteItemBean.Question.Option option, int state,
      int index) {
    super(context, option, state);
    mIndex = index;
    initView();
  }

  @OnClick({ R.id.item_container, R.id.btn_vote }) public void click(View view) {
    switch (view.getId()) {
      case R.id.item_container: {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onItemClick(mOption);
        }
      }
      break;
      case R.id.btn_vote: {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onVoteClick(mOption);
        }
      }
      break;
    }
  }

  @Override public int getLayoutId() {
    return R.layout.item_vote_character;
  }

  @Override protected void initView() {
    updateView();
  }

  public void notifyDataChanged(VoteItemBean.Question.Option option, int state, int index) {
    mOption = option;
    mVoteState = state;
    mIndex = index;
    updateView();
  }

  @Override protected void updateView() {

    textTitle.setText(mOption.getOptionContent());
    // Todo 头像

    if (mVoteState != 2) {
      textIndex.setVisibility(VISIBLE);
      imgIndex.setVisibility(GONE);
      textIndex.setText(String.valueOf(mIndex));

      if (mOption.getIsVote() == 0) { // 未投票
        btnVote.setEnabled(true);
        btnVote.setBackgroundResource(R.drawable.shape_blue_fillet_15);
        textCount.setText(getResources().getString(R.string.num_of_ticket, 0));
      } else { // 已投票
        btnVote.setEnabled(false);
        btnVote.setBackgroundResource(R.drawable.shape_gray_deep_fillet_15);
        textCount.setText(
            getResources().getString(R.string.num_of_ticket, mOption.getAnswerTotal()));
      }
    } else { // 已结束
      if (mIndex <= 3) {
        textIndex.setVisibility(GONE);
        imgIndex.setVisibility(VISIBLE);
        if (mIndex == 1) {
          imgIndex.setImageResource(R.drawable.club_no1);
        } else if (mIndex == 2) {
          imgIndex.setImageResource(R.drawable.club_no2);
        } else {
          imgIndex.setImageResource(R.drawable.club_no3);
        }
      } else {
        textIndex.setVisibility(VISIBLE);
        textIndex.setText(String.valueOf(mIndex));
        imgIndex.setVisibility(GONE);
      }
      textCount.setText(getResources().getString(R.string.num_of_ticket, mOption.getAnswerTotal()));
      btnVote.setEnabled(false);
      btnVote.setBackgroundResource(R.drawable.shape_gray_deep_fillet_15);
    }
  }
}
