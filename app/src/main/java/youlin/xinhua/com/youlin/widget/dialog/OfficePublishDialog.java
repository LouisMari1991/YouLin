package youlin.xinhua.com.youlin.widget.dialog;

import android.content.Context;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/04/20
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class OfficePublishDialog extends BottomBaseDialog<OfficePublishDialog> {

  @BindView(R.id.ll_container) View view;

  public OfficePublishDialog(Context context) {
    super(context);
  }

  @Override public View onCreateView() {
    View inflate = View.inflate(mContext, R.layout.dialog_office_publish, null);
    ButterKnife.bind(this, inflate);
    return inflate;
  }

  @Override public void setUiBeforShow() {
    //mLlTop.getBackground().mutate().setAlpha(10);
    //mLlTop.setAlpha(0.1f);
    view.getBackground().mutate().setAlpha(100);
  }
}
