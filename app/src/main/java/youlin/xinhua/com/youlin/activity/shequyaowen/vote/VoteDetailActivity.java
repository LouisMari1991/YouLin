package youlin.xinhua.com.youlin.activity.shequyaowen.vote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import butterknife.BindView;
import java.util.ArrayList;
import java.util.List;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.bean.VoteItemBean;
import youlin.xinhua.com.youlin.widget.statusbar.StatusBarHelper;
import youlin.xinhua.com.youlin.widget.vote.VoteView;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-05 09:13
 * version: 1.0
 * </pre>
 */

public class VoteDetailActivity extends BaseActivity {

  @BindView(R.id.vote_view) VoteView mVoteView;

  public static void launch(Context context) {
    Intent intent = new Intent(context, VoteDetailActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_vote_detail;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    StatusBarHelper.setColor(this, ContextCompat.getColor(this, R.color.white), 0);

    final VoteItemBean voteItemBean = new VoteItemBean();
    voteItemBean.setVoteType(2);
    voteItemBean.setAnswersTotal(50);
    voteItemBean.setCarryState("1");
    voteItemBean.setContent("super man");
    voteItemBean.setStartTime("2017-09-04 14:23:05");
    voteItemBean.setEndTime("2017-09-28 14:22:54");

    List<VoteItemBean.Question> questionList = new ArrayList<>();
    VoteItemBean.Question question = new VoteItemBean.Question();
    List<VoteItemBean.Question.Option> optionList = new ArrayList<>();

    VoteItemBean.Question.Option option_0 = new VoteItemBean.Question.Option();
    option_0.setOptionContent("李晨");
    option_0.setAnswerTotal(10);
    option_0.setIsVote(0);
    VoteItemBean.Question.Option option_1 = new VoteItemBean.Question.Option();
    option_1.setOptionContent("小槐槐");
    option_1.setAnswerTotal(20);
    option_1.setIsVote(0);
    VoteItemBean.Question.Option option_2 = new VoteItemBean.Question.Option();
    option_2.setOptionContent("其他");
    option_2.setAnswerTotal(5);
    option_2.setIsVote(0);

    optionList.add(option_0);
    optionList.add(option_1);
    optionList.add(option_2);

    question.setOptionList(optionList);

    questionList.add(question);

    voteItemBean.setQuestionList(questionList);

    mVoteView.setVoteItemBean(voteItemBean);

    mVoteView.setOnVoteViewClickListener(new VoteView.OnVoteViewClickListener() {
      @Override public void onItemClick(VoteItemBean.Question.Option option) {

      }

      @Override public void onVoteClick(VoteItemBean.Question.Option option) {

      }
    });

    //mVoteView.postDelayed(new Runnable() {
    //  @Override public void run() {
    //    voteItemBean.setCarryState("2");
    //    mVoteView.updateVoteItemBean(voteItemBean);
    //  }
    //}, 2000);
  }
}
