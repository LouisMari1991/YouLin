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
    this(context, attrs, 0);
  }

  public VoteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    setOrientation(LinearLayout.VERTICAL);
  }

  public void setVoteItemBean(VoteItemBean voteItemBean) {
    mVoteItemBean = voteItemBean;
    mVoteState = Integer.valueOf(voteItemBean.getCarryState());
    mVoteType = voteItemBean.getVoteType();
    mQuestion = voteItemBean.getQuestionList().get(0);
    mOptionList = mQuestion.getOptionList();
    initView();
  }

  public void updateVoteItemBean(VoteItemBean voteItemBean) {
    mVoteItemBean = voteItemBean;
    mVoteState = Integer.valueOf(voteItemBean.getCarryState());
    mVoteType = voteItemBean.getVoteType();
    mQuestion = voteItemBean.getQuestionList().get(0);
    mOptionList = mQuestion.getOptionList();
    updateView();
  }

  private void updateView() {
    int childCount = getChildCount();
    int startIndex = 0;
    int size = mOptionList.size();
    int extra = size % 2;
    for (int i = 0; i < childCount; i++) {
      AbsVoteItem item = (AbsVoteItem) getChildAt(i);
      if (item instanceof VoteItemNormal) {
        ((VoteItemNormal) item).notifyDataChanged(mOptionList.get(i), mVoteState,
            mVoteItemBean.getAnswersTotal());
      } else if (item instanceof VoteItemCharacter) { // 人物
        ((VoteItemCharacter) item).notifyDataChanged(mOptionList.get(i), mVoteState, i + 1);
      } else if (item instanceof VoteItemAtlas) { // 图集

        if (extra == 0) {
          VoteItemBean.Question.Option[] options =
              getItemBeanArray(mOptionList.get(startIndex), mOptionList.get(startIndex + 1));
          startIndex += 2;
          ((VoteItemAtlas) item).notifyDataChanged(mVoteState, options);
        } else {
          if (startIndex == size - 1) {
            VoteItemBean.Question.Option[] options = getItemBeanArray(mOptionList.get(startIndex));
            ((VoteItemAtlas) item).notifyDataChanged(mVoteState, options);
          } else {
            VoteItemBean.Question.Option[] options =
                getItemBeanArray(mOptionList.get(startIndex), mOptionList.get(startIndex + 1));
            startIndex += 2;
            ((VoteItemAtlas) item).notifyDataChanged(mVoteState, options);
          }
        }
      }
    }
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
        item =
            new VoteItemNormal(getContext(), option, mVoteState, mVoteItemBean.getAnswersTotal());
      } else if (mVoteType == 2) {
        item = new VoteItemCharacter(getContext(), option, mVoteState, i + 1);
      }
      item.setOnItemClickListener(new AbsVoteItem.OnItemClickListener() {
        @Override public void onItemClick(VoteItemBean.Question.Option option) {
          // 详情
          ToastUtils.showToast("initNormal , onItemClick");
          if (mOnVoteViewClickListener != null) {

          }
        }

        @Override public void onVoteClick(VoteItemBean.Question.Option option) {
          // 投票
          ToastUtils.showToast("initNormal , onVoteClick");
          if (mOnVoteViewClickListener != null) {
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
      VoteItemAtlas item = new VoteItemAtlas(getContext(), mVoteState,
          getItemBeanArray(mOptionList.get(startIndex), mOptionList.get(startIndex + 1)));
      startIndex += 2;
      item.setOnItemClickListener(new AbsVoteItem.OnItemClickListener() {
        @Override public void onItemClick(VoteItemBean.Question.Option option) {
          // 详情
          ToastUtils.showToast("getVoteItemAtlas , onItemClick");
          if (mOnVoteViewClickListener != null) {
          }
        }

        @Override public void onVoteClick(VoteItemBean.Question.Option option) {
          // 投票
          ToastUtils.showToast("getVoteItemAtlas , onVoteClick");
          if (mOnVoteViewClickListener != null) {
          }
        }
      });
      addView(item);
    }
    if (extra != 0) {
      VoteItemAtlas item = new VoteItemAtlas(getContext(), mVoteState,
          getItemBeanArray(mOptionList.get(mOptionList.size() - 1)));
      item.setOnItemClickListener(new AbsVoteItem.OnItemClickListener() {
        @Override public void onItemClick(VoteItemBean.Question.Option option) {
          // 详情
          ToastUtils.showToast("getVoteItemAtlas , onItemClick");
          if (mOnVoteViewClickListener != null) {

          }
        }

        @Override public void onVoteClick(VoteItemBean.Question.Option option) {
          // 投票
          ToastUtils.showToast("getVoteItemAtlas , onVoteClick");
          if (mOnVoteViewClickListener != null) {

          }
        }
      });
      addView(item);
    }
  }

  private VoteItemBean.Question.Option[] getItemBeanArray(VoteItemBean.Question.Option... optins) {
    return optins;
  }

  public void setOnVoteViewClickListener(OnVoteViewClickListener onVoteViewClickListener) {
    mOnVoteViewClickListener = onVoteViewClickListener;
  }

  public interface OnVoteViewClickListener {
    void onItemClick(VoteItemBean.Question.Option option);

    void onVoteClick(VoteItemBean.Question.Option option);
  }
}
