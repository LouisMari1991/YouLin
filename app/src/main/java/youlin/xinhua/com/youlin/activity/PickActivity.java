package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.widget.wheelpick.CommentDatePicker;
import youlin.xinhua.com.youlin.widget.wheelpick.CommentOptionPicker;

import static youlin.xinhua.com.youlin.utils.ToastUtils.showToast;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-08-14 10:49
 * version: 1.0
 * </pre>
 */

public class PickActivity extends BaseActivity {

  public static void launch(Context context) {
    Intent intent = new Intent(context, PickActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_picker;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @OnClick({ R.id.single_picker, R.id.year_picker }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.single_picker:
        // 单项选择
        showSingleOption();
        break;
      case R.id.year_picker:
        showYearPick();
        break;
      default:
        throw new UnsupportedOperationException(String.valueOf(view.getId()));
    }
  }

  private void showYearPick() {
    //final DatePicker picker = new DatePicker(this);
    //picker.setCanceledOnTouchOutside(true);
    //picker.setUseWeight(false);
    //picker.setLineSpaceMultiplier(3);
    //picker.setResetWhileWheel(false);
    //picker.setLabel("", "", "");
    //picker.setDividerRatio(WheelView.DividerConfig.FILL);
    //picker.setOffset(2);
    //picker.setTextColor(getResources().getColor(R.color.black_text));
    //picker.setTextSize(30);
    //picker.setDividerColor(ActivityCompat.getColor(this, R.color.color_gray_light));
    //picker.setTopLineColor(ActivityCompat.getColor(this, R.color.color_gray_light));

    final CommentDatePicker picker = new CommentDatePicker(this);

    picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
      @Override public void onDatePicked(String year, String month, String day) {
        showToast(year + "-" + month + "-" + day);
      }
    });
    picker.setOnWheelListener(new DatePicker.OnWheelListener() {
      @Override public void onYearWheeled(int index, String year) {
        picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
      }

      @Override public void onMonthWheeled(int index, String month) {
        picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
      }

      @Override public void onDayWheeled(int index, String day) {
        picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
      }
    });
    picker.show();
  }

  private void showSingleOption() {
    String[] strArray = { "100%", "90%", "80%", "70%", "60%", "50%" };
    //OptionPicker optionPicker = new OptionPicker(this, strArray);
    //optionPicker.setDividerRatio(WheelView.DividerConfig.FILL);
    //optionPicker.setOffset(2);
    //optionPicker.setTextColor(getResources().getColor(R.color.black_text));
    //optionPicker.setTextSize(18);
    //optionPicker.setDividerColor(ActivityCompat.getColor(this, R.color.color_gray_light));
    //optionPicker.setTopLineColor(ActivityCompat.getColor(this, R.color.color_gray_light));
    CommentOptionPicker commentOptionPicker = new CommentOptionPicker(this, strArray);
    commentOptionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
      @Override public void onOptionPicked(int index, String item) {
        showToast(index + " , " + item);
      }
    });
    commentOptionPicker.show();
  }
}
