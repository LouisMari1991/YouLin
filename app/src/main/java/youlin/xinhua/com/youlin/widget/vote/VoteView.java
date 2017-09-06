package youlin.xinhua.com.youlin.widget.vote;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import java.util.List;
import youlin.xinhua.com.youlin.bean.VoteItemBean;
import youlin.xinhua.com.youlin.utils.ToastUtils;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-06 09:10
 * version: 1.0
 * </pre>
 */

public class VoteView extends LinearLayout {

  int mVoteType; // 投票类型
  int mVoteState; // 投票状态

  VoteItemBean mVoteItemBean;

  VoteItemBean.Question              mQuestion;
  List<VoteItemBean.Question.Option> mOptionList;

  private OnVoteViewClickListener mOnVoteViewClickListener;

  public VoteView(Context context) {
    this(context, null);
  }

  public VoteView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs, 0);
  }

  public VoteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    setOrientation(VERTICAL);
  }

  public void setVoteItemBean(VoteItemBean voteItemBean) {
    mVoteItemBean = voteItemBean;
    initData();
  }

  private void initData() {
    mVoteState = Integer.valueOf(mVoteItemBean.getCarryState());
    mVoteType = mVoteItemBean.getVoteType();
    mQuestion = mVoteItemBean.getQuestionList().get(0);
    mOptionList = mQuestion.getOptionList();
    initView();
  }

  private void initView() {
    switch (mVoteType) {
      case 1: // 普通投票
      case 2: // 人物投票
        initNormal();
        break;
      case 3: // 图集投票
        initAtlas();
        break;
      default:
        break;
    }
  }

  private void initNormal() {
    for (int i = 0; i < mOptionList.size(); i++) {
      VoteItemBean.Question.Option option = mOptionList.get(i);
      AbsVoteItem item = null;
      if (mVoteType == 1) {
        item = new VoteItemNormal(getContext(), option, mVoteState, mVoteItemBean.getAnswerTotal());
      } else if (mVoteType == 2) {
        item = new VoteItemCharacter(getContext(), option, mVoteState, i + 1);
      }
      item.setOnItemClickListener(new AbsVoteItem.OnItemClickListener() {
        @Override public void onItemClick(VoteItemBean.Question.Option option) {
          // 详情
          if (mOnVoteViewClickListener != null) {
            ToastUtils.showToast("initNormal , onItemClick");
          }
        }

        @Override public void onVoteClick(VoteItemBean.Question.Option option) {
          // 投票
          if (mOnVoteViewClickListener != null) {
            ToastUtils.showToast("initNormal , onVoteClick");
          }
        }
      });
      addView(item);
    }
  }

  private void initAtlas() {
    int size = mOptionList.size();
    int even = size / 2;
    int extra = size % 2;
    int startIndex = 0;
    for (int i = 0; i < even; i++) {
      addView(getVoteItemAtlas(getContext(), mVoteState, mOptionList.get(startIndex),
          mOptionList.get(startIndex + 1)));
      startIndex += 2;
    }
    if (extra != 0) {
      addView(getVoteItemAtlas(getContext(), mVoteState, mOptionList.get(mOptionList.size() - 1)));
    }
  }

  private AbsVoteItem getVoteItemAtlas(Context context, int state,
      VoteItemBean.Question.Option... options) {
    AbsVoteItem item = new VoteItemAtlas(context, state, options);
    item.setOnItemClickListener(new AbsVoteItem.OnItemClickListener() {
      @Override public void onItemClick(VoteItemBean.Question.Option option) {
        // 详情
        if (mOnVoteViewClickListener != null) {
          ToastUtils.showToast("getVoteItemAtlas , onItemClick");
        }
      }

      @Override public void onVoteClick(VoteItemBean.Question.Option option) {
        // 投票
        if (mOnVoteViewClickListener != null) {
          ToastUtils.showToast("getVoteItemAtlas , onVoteClick");
        }
      }
    });
    return item;
  }

  public void setOnVoteViewClickListener(OnVoteViewClickListener onVoteViewClickListener) {
    mOnVoteViewClickListener = onVoteViewClickListener;
  }

  public interface OnVoteViewClickListener {
    void onItemClick();

    void onVoteClick();
  }
}
