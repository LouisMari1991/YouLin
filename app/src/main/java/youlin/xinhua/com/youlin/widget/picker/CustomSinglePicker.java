package youlin.xinhua.com.youlin.widget.picker;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-08-14 11:15
 * version: 1.0
 * </pre>
 */

public class CustomSinglePicker extends OptionPicker {

  public CustomSinglePicker(Activity activity, String[] items) {
    super(activity, items);
    setSelectedIndex(items.length - 1);
    setDividerRatio(WheelView.DividerConfig.FILL);
  }

  TextView headLeftText;

  //@Nullable @Override protected View makeHeaderView() {
    //View view = LayoutInflater.from(activity).inflate(R.layout.picker_header, null);
    //headLeftText = (TextView) view.findViewById(R.id.text_head_left);
    //headLeftText.setText(titleText);
    //view.findViewById(R.id.text_head_right).setOnClickListener(new View.OnClickListener() {
    //  @Override public void onClick(View v) {
    //    dismiss();
    //  }
    //});
  //  return view;
  //}

  @Nullable @Override protected View makeFooterView() {
    View view = LayoutInflater.from(activity).inflate(R.layout.view_pick_single_foot, null);
    view.findViewById(R.id.btn_foot).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dismiss();
      }
    });
    return view;
  }
}
