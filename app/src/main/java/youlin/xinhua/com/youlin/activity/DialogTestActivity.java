//package youlin.xinhua.com.youlin.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.View;
//import butterknife.OnClick;
//import youlin.xinhua.com.youlin.BaseActivity;
//import youlin.xinhua.com.youlin.R;
//import youlin.xinhua.com.youlin.widget.dialog.LoadingProgressDialog;
//import youlin.xinhua.com.youlin.widget.dialog.TipDialog;
//
///**
// * <pre>
// * desc   : Todo
// * author : 罗顺翔
// * time   : 2017-09-07 16:11
// * version: 1.0
// * </pre>
// */
//
//public class DialogTestActivity extends BaseActivity {
//
//  public static void launch(Context context) {
//    Intent intent = new Intent(context, DialogTestActivity.class);
//    context.startActivity(intent);
//  }
//
//  @Override protected int attachLayoutRes() {
//    return R.layout.activity_dialog_test;
//  }
//
//  @OnClick({ R.id.btn_tip_dialog, R.id.btn_loading_dialog }) public void click(View view) {
//    switch (view.getId()) {
//      case R.id.btn_tip_dialog:
//        TipDialog tipDialog = new TipDialog(this);
//        tipDialog.setTitleText("哈哈");
//        tipDialog.setContentText("加载中").showCancelButton(false);
//        tipDialog.show();
//        break;
//      case R.id.btn_loading_dialog:
//        //LoadingDialog loadingDialog = new LoadingDialog(this);
//        //loadingDialog.show();
//        LoadingProgressDialog dialog = new LoadingProgressDialog(this);
//        dialog.show();
//        break;
//      default:
//        break;
//    }
//  }
//}
