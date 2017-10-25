package youlin.xinhua.com.youlin.activity.yeweihui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.ToastUtils;
import youlin.xinhua.com.youlin.widget.yunshe.ownercommittee.CandidateInfoContainer;

/**
 * <pre>
 * @author : 罗顺翔
 * desc   : 候选人自荐、候选人推荐
 * time   : 2017-10-24 10:41
 * version: 1.0
 * </pre>
 */

public class AddCandidateInfoActivity extends BaseActivity {

  public static void launch(Context context) {
    Intent intent = new Intent(context, AddCandidateInfoActivity.class);
    context.startActivity(intent);
  }

  @BindView(R.id.candidate_info_container) CandidateInfoContainer mCandidateInfoContainer;

  @BindView(R.id.text_job)       TextView text_job;
  @BindView(R.id.text_origin)    TextView text_origin;
  @BindView(R.id.text_politics)  TextView text_politics;
  @BindView(R.id.text_nation)    TextView text_nation;
  @BindView(R.id.text_political) TextView text_political;
  @BindView(R.id.text_education) TextView text_education;
  @BindView(R.id.text_hours)     TextView text_hours;
  @BindView(R.id.text_property)  TextView text_property;

  @OnClick({
      R.id.text_job, R.id.text_origin, R.id.text_politics, R.id.text_nation, R.id.text_political,
      R.id.text_education, R.id.text_hours, R.id.text_property
  }) public void click(View view) {
    switch (view.getId()) {
      case R.id.text_job: {
        // 职位
      }
      break;
      case R.id.text_origin: {
        // 籍贯
      }
      break;
      case R.id.text_politics: {
        // 政治面貌
      }
      break;
      case R.id.text_nation: {
        // 民族
      }
      break;
      case R.id.text_political: {
        // 出生地
      }
      break;
      case R.id.text_education: {
        // 学历
      }
      break;
      case R.id.text_hours: {
        // 参加工作时间
      }
      break;
      case R.id.text_property: {
        // 物业缴纳情况
      }
      break;
      default:
        break;
    }
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_yunshe_add_candidate_info;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mCandidateInfoContainer.setCandidateInfoContainerClickListener(
        new CandidateInfoContainer.CandidateInfoContainerClickListener() {
          @Override public void onEditButtonClick() {
            ToastUtils.show("onEditButtonClick");
          }

          @Override public void onDeleteButtonClick() {
            ToastUtils.show("onDeleteButtonClick");
          }

          @Override public void onAddButtonClick() {
            WorkInfoActivity.launch(AddCandidateInfoActivity.this);
          }
        });
    mCandidateInfoContainer.addWork();
    mCandidateInfoContainer.addWork();
    mCandidateInfoContainer.addWork();
    mCandidateInfoContainer.addWork();
  }
}
