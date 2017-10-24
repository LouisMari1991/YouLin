package youlin.xinhua.com.youlin.activity.yeweihui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.BindView;
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

  @Override protected int attachLayoutRes() {
    return R.layout.activity_yunshe_add_candidate_info;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mCandidateInfoContainer.setCandidateInfoContainerClickListener(
        new CandidateInfoContainer.CandidateInfoContainerClickListener() {
          @Override public void onEditButtonClick() {

          }

          @Override public void onDeleteButtonClick() {

          }

          @Override public void onAddButtonClick() {
            ToastUtils.show("onAddButtonClick");
          }
        });
  }
}
