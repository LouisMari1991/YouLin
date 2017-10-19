package youlin.xinhua.com.youlin.widget.wheelpick;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;
import java.util.List;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * @author : 罗顺翔
 * desc   : Todo
 * time   : 2017-10-19 14:12
 * version: 1.0
 * </pre>
 */

public class CommentOptionPicker extends OptionPicker {

  public CommentOptionPicker(Activity activity, String[] items) {
    super(activity, items);
    init();
  }

  public CommentOptionPicker(Activity activity, List<String> items) {
    super(activity, items);
    init();
  }

  private void init() {
    setDividerRatio(WheelView.DividerConfig.FILL);
    setOffset(2);
    setTextColor(ActivityCompat.getColor(getContext(), R.color.black_text));
    setTextSize(18);
    setDividerColor(ActivityCompat.getColor(getContext(), R.color.color_gray_light));
    setTopLineColor(ActivityCompat.getColor(getContext(), R.color.color_gray_light));
  }
}
