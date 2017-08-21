package youlin.xinhua.com.youlin.activity.yeweihui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;
import youlin.xinhua.com.flowlayout.FlowLayout;
import youlin.xinhua.com.flowlayout.TagAdapter;
import youlin.xinhua.com.flowlayout.TagFlowLayout;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.ToastUtils;
import youlin.xinhua.com.youlin.widget.dialog.AddJobDialog;

/**
 * <pre>
 * desc   : 填写资料
 * author : 罗顺翔
 * time   : 2017-08-21 15:45
 * version: 1.0
 * </pre>
 */

public class InputInformationActivity extends BaseActivity {

  public static void launch(Context context) {
    Intent intent = new Intent(context, InputInformationActivity.class);
    context.startActivity(intent);
  }

  @BindView(R.id.flow_layout) TagFlowLayout mTagFlowLayout;
  @BindView(R.id.img_arror)   ImageView     imgArror;
  @BindView(R.id.text_zijian) TextView      textZiJian;

  AddJobDialog mAddJobDialog;

  TagAdapter<String> mAdapter;

  String[] flowAttrs = { "添加岗位", "主任", "副主任", "当值书记", "主任", "副主任", "当值书记", "+ 添加" };

  @Override protected int attachLayoutRes() {
    return R.layout.activity_committee_input_information;
  }

  @OnClick({ R.id.ll_zijian, R.id.btn_confirm }) public void onclick(View view) {
    switch (view.getId()) {
      case R.id.ll_zijian: { // 自荐比例
        showSingleOption();
      }
      break;
      case R.id.btn_confirm: { // 确认新建群聊

      }
      break;
    }
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initDialog();
    initFlowLayout();
  }

  private void initDialog() {
    mAddJobDialog = new AddJobDialog(this);
    mAddJobDialog.setDialogClickListener(new AddJobDialog.DialogClickListener() {
      @Override public void onDialogItemClick(String string) {
        mAdapter.addNextItem(string);
        mAddJobDialog.dismiss();
      }

      @Override public void onDialogSaveClick(String jobStr) {
        if (TextUtils.isEmpty(jobStr)) {
          ToastUtils.showToast("岗位名称不能为空");
          return;
        }
        mAddJobDialog.restore();
        mAddJobDialog.dismiss();
        mAdapter.addNextItem(jobStr);
      }

      @Override public void onCustomItemClick() {

      }
    });
  }

  private void showSingleOption() {
    String[] strArray = { "100%", "90%", "80%", "70%", "60%", "50%" };
    OptionPicker optionPicker = new OptionPicker(this, strArray);
    optionPicker.setDividerRatio(WheelView.DividerConfig.FILL);
    optionPicker.setOffset(2);
    optionPicker.setTextColor(getResources().getColor(R.color.black_text));
    optionPicker.setTextSize(18);
    optionPicker.setDividerColor(ActivityCompat.getColor(this, R.color.color_gray_light));
    optionPicker.setTopLineColor(ActivityCompat.getColor(this, R.color.color_gray_light));
    optionPicker.setSelectedIndex(strArray.length - 1);
    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
      @Override public void onOptionPicked(int index, String item) {
        imgArror.setVisibility(View.GONE);
        textZiJian.setText(item);
      }
    });
    optionPicker.show();
  }

  private void initFlowLayout() {
    final LayoutInflater mInflater = LayoutInflater.from(this);
    mAdapter = new TagAdapter<String>(flowAttrs) {
      @Override public View getView(FlowLayout parent, final int position, String s) {

        if (position == 0) {
          View itemView = mInflater.inflate(R.layout.item_flow_layout_first, mTagFlowLayout, false);
          return itemView;
        }

        View itemView = mInflater.inflate(R.layout.item_flow_layout, mTagFlowLayout, false);
        TextView tv = (TextView) itemView.findViewById(R.id.text);
        View del = itemView.findViewById(R.id.fl);
        if (position == mAdapter.getCount() - 1) {
          del.setVisibility(View.INVISIBLE);
        }
        del.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View view) {
            mAdapter.removeItem(position);
          }
        });
        tv.setText(s);
        return itemView;
      }
    };
    mTagFlowLayout.setAdapter(mAdapter);
    mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
      @Override public boolean onTagClick(View view, int position, FlowLayout parent) {
        if (position == mAdapter.getCount() - 1) {
          showAddJobDialog();
        }
        return true;
      }
    });
  }

  void showAddJobDialog() {
    if (mAddJobDialog.isShowing()) {
      mAddJobDialog.dismiss();
    }
    mAddJobDialog.show();
  }
}
