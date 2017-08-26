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
import youlin.xinhua.com.youlin.widget.dialog.InputRoomNumDialog;

/**
 * <pre>
 * desc   : 完善信息
 * author : 罗顺翔
 * time   : 2017-08-22 20:13
 * version: 1.0
 * </pre>
 */

public class PerfectInformationActivity extends BaseActivity {

  public static void launch(Context context) {
    Intent intent = new Intent(context, PerfectInformationActivity.class);
    context.startActivity(intent);
  }

  private InputRoomNumDialog mInputRoomNumDialog;

  @BindView(R.id.text_fangjianNum) TextView textFJNum;// 房间号

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
  }

  @OnClick({ R.id.btn_confirm, R.id.text_fangjianNum }) public void onclick(View view) {
    switch (view.getId()) {
      case R.id.text_fangjianNum: {
        showInputDialog();
      }
      break;
      case R.id.btn_confirm: {

      }
      break;
      default:
        break;
    }
  }

  private void showInputDialog() {
    if (mInputRoomNumDialog == null) {
      mInputRoomNumDialog = new InputRoomNumDialog(this);
      mInputRoomNumDialog.setDialogBtnClickListener(
          new InputRoomNumDialog.DialogBtnClickListener() {
            @Override public void click(View view, String str) {
              textFJNum.setText(str);
              mInputRoomNumDialog.dismiss();
            }
          });
    }
    if (mInputRoomNumDialog.isShowing()) {
      mInputRoomNumDialog.dismiss();
    }
    mInputRoomNumDialog.show();
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_committee_perfect_information;
  }
}
