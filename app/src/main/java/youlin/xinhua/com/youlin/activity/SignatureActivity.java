package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import butterknife.BindView;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.widget.DrawingView;

/**
 * Description:
 * Author：Mari on 2017-08-13 21:33
 * Contact：289168296@qq.com
 */
public class SignatureActivity extends BaseActivity {

  public static void launch(Context context) {
    Intent intent = new Intent(context, SignatureActivity.class);
    context.startActivity(intent);
  }

  @BindView(R.id.drawing_view) DrawingView mDrawingView;

  @OnClick(R.id.btn_save) public void click() {
    //BitmapUtils.saveBitmap("drawing", mDrawingView.getBitmap());
    //Bitmap bitmap = BitmapUtils.getBitmapFromView(mDrawingView);
    mDrawingView.clearCanvas();
    //
    //BitmapUtils.saveBitmap("drawing_01", bitmap);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_signture;
  }
}
