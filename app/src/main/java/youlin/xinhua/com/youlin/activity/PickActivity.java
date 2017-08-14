package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.ToastUtils;

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

  @OnClick({ R.id.single_picker }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.single_picker: { // 单项选择
        showSingleOption();
      }
      break;
      default:
        throw new UnsupportedOperationException(String.valueOf(view.getId()));
    }
  }

  private void showSingleOption() {
    String[] strArray = { "100%", "90%", "80%", "70%", "60%", "50%" };
    //CustomSinglePicker customSinglePicker = new CustomSinglePicker(this, strArray);
    //customSinglePicker.setTitleText("签到人数比例");
    //customSinglePicker.show();
    OptionPicker optionPicker = new OptionPicker(this, strArray);
    optionPicker.setDividerRatio(WheelView.DividerConfig.FILL);
    optionPicker.setOffset(2);
    optionPicker.setTextColor(getResources().getColor(R.color.black_text));
    optionPicker.setTextSize(18);
    optionPicker.setDividerColor(ActivityCompat.getColor(this, R.color.color_gray_light));
    optionPicker.setTopLineColor(ActivityCompat.getColor(this, R.color.color_gray_light));
    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
      @Override public void onOptionPicked(int index, String item) {
        ToastUtils.showToast(index + " , " + item);
      }
    });
    optionPicker.show();
  }
}
