//package youlin.xinhua.com.youlin.widget.dialog;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.EditText;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import youlin.xinhua.com.youlin.R;
//
///**
// * <pre>
// * desc   : Todo
// * author : 罗顺翔
// * time   : 2017-08-25 14:10
// * version: 1.0
// * </pre>
// */
//
//public class InputRoomNumDialog extends Dialog {
//
//  @BindView(R.id.edit_0) EditText mEdit0;
//  @BindView(R.id.edit_1) EditText mEdit1;
//  @BindView(R.id.edit_2) EditText mEdit2;
//
//  @OnClick(R.id.btn_save) public void onclick(View view) {
//    if (mDialogBtnClickListener != null) {
//      String num0 = mEdit0.getText().toString().trim();
//      String num1 = mEdit1.getText().toString().trim();
//      String num2 = mEdit2.getText().toString().trim();
//      StringBuffer sb = new StringBuffer();
//      if (!TextUtils.isEmpty(num0)) sb.append(num0).append("#");
//      if (!TextUtils.isEmpty(num1)) sb.append(num1).append("#");
//      if (!TextUtils.isEmpty(num2)) sb.append(num2);
//      mDialogBtnClickListener.click(view, sb.toString());
//    }
//  }
//
//  public InputRoomNumDialog(@NonNull Context context) {
//    super(context, R.style.full_screen_dialog);
//    setCancelable(true);
//    setCanceledOnTouchOutside(true);
//  }
//
//  @Override protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.dialog_input_room_num);
//    ButterKnife.bind(this);
//  }
//
//  private DialogBtnClickListener mDialogBtnClickListener;
//
//  public void setDialogBtnClickListener(DialogBtnClickListener dialogBtnClickListener) {
//    mDialogBtnClickListener = dialogBtnClickListener;
//  }
//
//  public interface DialogBtnClickListener {
//    void click(View view, String str);
//  }
//}
