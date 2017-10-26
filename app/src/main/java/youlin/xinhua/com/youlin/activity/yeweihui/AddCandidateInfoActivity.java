package youlin.xinhua.com.youlin.activity.yeweihui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.ToastUtils;
import youlin.xinhua.com.youlin.widget.wheelpick.CommentAddressPicker;
import youlin.xinhua.com.youlin.widget.wheelpick.CommentDatePicker;
import youlin.xinhua.com.youlin.widget.wheelpick.CommentOptionPicker;
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
        showCityPicker(text_origin);
      }
      break;
      case R.id.text_politics: {
        // 政治面貌
        showOptionPicker(text_politics, getResources().getStringArray(R.array.political_visage));
      }
      break;
      case R.id.text_nation: {
        // 民族
        showOptionPicker(text_nation, getResources().getStringArray(R.array.nation));
      }
      break;
      case R.id.text_political: {
        // 出生地
        showCityPicker(text_political);
      }
      break;
      case R.id.text_education: {
        // 学历
        showOptionPicker(text_education, getResources().getStringArray(R.array.education));
      }
      break;
      case R.id.text_hours: {
        // 参加工作时间
        showDatePicker();
      }
      break;
      case R.id.text_property: {
        // 物业缴纳情况
        showOptionPicker(text_property, getResources().getStringArray(R.array.payment_status));
      }
      break;
      default:
        break;
    }
  }

  private void showOptionPicker(final TextView textView, String[] strArray) {
    CommentOptionPicker picker = new CommentOptionPicker(this, strArray);
    picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
      @Override public void onOptionPicked(int index, String item) {
        textView.setText(item);
      }
    });
    picker.show();
  }

  private void showCityPicker(final TextView textView) {
    CommentAddressPicker addressPicker = new CommentAddressPicker(this);
    addressPicker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
      @Override public void onAddressPicked(Province province, City city, County county) {
        textView.setText(province.getName() + '-' + city.getName());
      }
    });
    addressPicker.show();
  }

  private void showDatePicker() {
    CommentDatePicker picker = new CommentDatePicker(this, DateTimePicker.YEAR_MONTH);
    picker.setRangeStartForYear(50);
    picker.setRangeEndForCurTime();
    picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
      @Override public void onDatePicked(String year, String month) {
        text_hours.setText(year + '-' + month);
      }
    });
    picker.show();
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
  }
}
