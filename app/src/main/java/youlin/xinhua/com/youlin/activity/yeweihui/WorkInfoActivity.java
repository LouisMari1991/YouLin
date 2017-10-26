package youlin.xinhua.com.youlin.activity.yeweihui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.widget.wheelpick.CommentDatePicker;

/**
 * <pre>
 * @author : 罗顺翔
 * desc   : Todo
 * time   : 2017-10-25 10:36
 * version: 1.0
 * </pre>
 */

public class WorkInfoActivity extends BaseActivity {

  public static void launch(Context context) {
    Intent intent = new Intent(context, WorkInfoActivity.class);
    context.startActivity(intent);
  }

  @BindView(R.id.text_start_time) TextView textStartTime;
  @BindView(R.id.text_end_time)   TextView textEndTime;
  @BindView(R.id.et_company_name) EditText etCompanyName;
  @BindView(R.id.et_post)         EditText etPost;

  @OnClick({ R.id.text_start_time, R.id.text_end_time }) public void click(View view) {
    switch (view.getId()) {
      case R.id.text_start_time:
        showDatePicker(0);
        break;
      case R.id.text_end_time:
        showDatePicker(1);
        break;
      default:
        break;
    }
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_yunshe_work_info;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  void showDatePicker(final int type) {
    CommentDatePicker picker = new CommentDatePicker(this, DateTimePicker.YEAR_MONTH);
    picker.setRangeStartForYear(30);
    picker.setRangeEndForYear(30);
    picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
      @Override public void onDatePicked(String year, String month) {
        if (type == 0) {
          textStartTime.setText(year + "-" + month);
        } else {
          textEndTime.setText(year + "-" + month);
        }
      }
    });
    picker.show();
  }
}
