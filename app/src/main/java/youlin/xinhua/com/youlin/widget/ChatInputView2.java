package youlin.xinhua.com.youlin.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.widget.record.RecordControllerView;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-27 11:11
 * version: 1.0
 * </pre>
 */

public class ChatInputView2 extends LinearLayout implements View.OnClickListener {

  private InputMethodManager mImm;
  private Window             mWindow;
  private int mLastClickId = 0;

  private int mWidth;
  private int mHeight;
  public static int sMenuHeight = 800;

  @BindView(R.id.aurora_rcv_recordvoice_controller) RecordControllerView mRecordControllerView;
  @BindView(R.id.aurora_et_chat_input)              EditText             mChatInput;

  public ChatInputView2(Context context) {
    super(context);
    init(context);
  }

  public ChatInputView2(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public ChatInputView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    inflate(getContext(), R.layout.view_chat_input, this);
    ButterKnife.bind(this);

    mImm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    mWindow = ((Activity) context).getWindow();
    DisplayMetrics dm = getResources().getDisplayMetrics();
    mWidth = dm.widthPixels;
    mHeight = dm.heightPixels;
    mRecordControllerView.setWidth(mWidth);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {

    }
  }
}
