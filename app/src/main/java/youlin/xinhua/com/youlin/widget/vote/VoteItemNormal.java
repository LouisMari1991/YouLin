package youlin.xinhua.com.youlin.widget.vote;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import java.text.NumberFormat;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.bean.VoteItemBean;
import youlin.xinhua.com.youlin.utils.MeasureUtils;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-05 10:48
 * version: 1.0
 * </pre>
 */

public class VoteItemNormal extends AbsVoteItem {

  @BindView(R.id.text_title)    TextView     textTitle;
  @BindView(R.id.text_vote)     TextView     textVote;
  @BindView(R.id.text_number)   TextView     textNum;
  @BindView(R.id.view_progress) View         viewProgress;
  @BindView(R.id.ll_progress)   LinearLayout llProgress;

  int measureWidth;
  int minWidth;

  int mGlobalAnswerTotal;

  ValueAnimator mValueAnimator;

  public VoteItemNormal(Context context, VoteItemBean.Question.Option option, int state,
      int globalAnswerTotal) {
    super(context, option, state);
    mGlobalAnswerTotal = globalAnswerTotal;
    initView();
  }

  @OnClick({ R.id.text_vote, R.id.item_container }) public void click(View v) {
    switch (v.getId()) {
      case R.id.text_vote: {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onVoteClick(mOption);
        }
      }
      break;
      case R.id.item_container: {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onItemClick(mOption);
        }
      }
      break;
      default:
        break;
    }
  }

  @Override public int getLayoutId() {
    return R.layout.item_vote_normal;
  }

  @Override protected void initView() {
    minWidth = MeasureUtils.dp2px(15);
    measureWidth = MeasureUtils.getScreenWidth(getContext()) - MeasureUtils.dp2px(40);
    llProgress.addOnLayoutChangeListener(new OnLayoutChangeListener() {
      @Override
      public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft,
          int oldTop, int oldRight, int oldBottom) {
        v.removeOnLayoutChangeListener(this);
        updateView();
      }
    });
  }

  @Override protected void updateView() {
    textTitle.setText(mOption.getOptionContent());
    if (mVoteState == 0) { // 未开始
      textNum.setText(getNumOf(0));
    } else if (mVoteState == 1) { // 进行中
      if (mOption.getIsVote() == 0) { // 未投票
        textNum.setText(getNumOf(0));
      } else { // 已经投票
        textNum.setText(getNumOf(mOption.getAnswerTotal()));
        startValueAnimator();
      }
    } else if (mVoteState == 2) { // 已结束
      textVote.setEnabled(false);
      textNum.setText(getNumOf(mOption.getAnswerTotal()));
      startValueAnimator();
    }
  }

  public void notifyDataChanged(VoteItemBean.Question.Option option, int state,
      int globalAnswerTotal) {
    mOption = option;
    mVoteState = state;
    mGlobalAnswerTotal = globalAnswerTotal;
    updateView();
  }

  // 开始动画
  private void startValueAnimator() {
    if (mOption.getAnswerTotal() == 0) {
      return;
    }
    int answerTotal = mOption.getAnswerTotal();
    // 创建一个数值格式化对象
    NumberFormat numberFormat = NumberFormat.getInstance();
    numberFormat.setMaximumFractionDigits(2);

    String result = numberFormat.format((float) answerTotal / (float) mGlobalAnswerTotal);

    float resultF = Float.valueOf(result);
    int valueAnimatorTotal = (int) (resultF * measureWidth);

    mValueAnimator = ValueAnimator.ofInt(0, valueAnimatorTotal);
    mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        int value = (int) animation.getAnimatedValue();
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) viewProgress.getLayoutParams();
        lp.width = Math.max(minWidth, value);
        viewProgress.setLayoutParams(lp);
      }
    });
    mValueAnimator.setDuration(800);
    mValueAnimator.start();
  }

  private String getNumOf(int num) {
    return getResources().getString(R.string.num_of_people, num);
  }
}
