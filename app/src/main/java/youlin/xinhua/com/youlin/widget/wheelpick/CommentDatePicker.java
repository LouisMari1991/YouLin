package youlin.xinhua.com.youlin.widget.wheelpick;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.widget.WheelView;
import java.util.Calendar;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * @author : 罗顺翔
 * desc   : Todo
 * time   : 2017-10-19 14:25
 * version: 1.0
 * </pre>
 */

public class CommentDatePicker extends DatePicker {
  public CommentDatePicker(Activity activity) {
    super(activity);
    init();
  }

  public CommentDatePicker(Activity activity, int mode) {
    super(activity, mode);
    init();
  }

  private void init() {
    setCanceledOnTouchOutside(true);
    setUseWeight(false);
    setLineSpaceMultiplier(3);
    setResetWhileWheel(false);
    setLabel("", "", "");
    setDividerRatio(WheelView.DividerConfig.FILL);
    setOffset(2);
    setTextColor(ActivityCompat.getColor(getContext(), R.color.black_text));
    setTextSize(30);
    setDividerColor(ActivityCompat.getColor(getContext(), R.color.color_gray_light));
    setTopLineColor(ActivityCompat.getColor(getContext(), R.color.color_gray_light));
  }

  public void setRangeStartForCurTime() {
    Calendar calendar = Calendar.getInstance();
    setRangeStart(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH));
  }

  public void setRangeEndForYear(int year) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.YEAR, year);
    setRangeEnd(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH));
  }
}
