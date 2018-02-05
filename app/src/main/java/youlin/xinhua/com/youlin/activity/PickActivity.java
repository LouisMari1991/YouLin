//package youlin.xinhua.com.youlin.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.View;
//import android.widget.TextView;
//import butterknife.BindView;
//import butterknife.OnClick;
//import cn.qqtheme.framework.entity.City;
//import cn.qqtheme.framework.entity.County;
//import cn.qqtheme.framework.entity.Province;
//import cn.qqtheme.framework.picker.AddressPicker;
//import cn.qqtheme.framework.picker.OptionPicker;
//import java.util.ArrayList;
//import java.util.List;
//import rx.Observable;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;
//import youlin.xinhua.com.youlin.BaseActivity;
//import youlin.xinhua.com.youlin.R;
//import youlin.xinhua.com.youlin.utils.AssetsHelper;
//import youlin.xinhua.com.youlin.utils.GsonHelper;
//import youlin.xinhua.com.youlin.utils.LogUtils;
//import youlin.xinhua.com.youlin.utils.StringUtils;
//import youlin.xinhua.com.youlin.utils.ToastUtils;
//import youlin.xinhua.com.youlin.widget.wheelpick.CommentAddressPicker;
//import youlin.xinhua.com.youlin.widget.wheelpick.CommentOptionPicker;
//
//import static youlin.xinhua.com.youlin.utils.ToastUtils.showToast;
//
///**
// * <pre>
// * desc   : Todo
// * author : 罗顺翔
// * time   : 2017-08-14 10:49
// * version: 1.0
// * </pre>
// */
//
//public class PickActivity extends BaseActivity {
//
//  public static void launch(Context context) {
//    Intent intent = new Intent(context, PickActivity.class);
//    context.startActivity(intent);
//  }
//
//  @BindView(R.id.text_content) TextView textContent;
//
//  @Override protected int attachLayoutRes() {
//    return R.layout.activity_picker;
//  }
//
//  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//  }
//
//  @OnClick({ R.id.single_picker, R.id.year_picker, R.id.area_picker, R.id.btn_changed })
//  public void onClick(View view) {
//    switch (view.getId()) {
//      case R.id.single_picker:
//        // 单项选择
//        showSingleOption();
//        break;
//      case R.id.year_picker:
//        showYearPick();
//        break;
//      case R.id.area_picker:
//        showArea();
//        break;
//      case R.id.btn_changed:
//        textContent.setText(
//            StringUtils.getLightText(this, "欠费100元", "100", R.color.black_text, R.color.red_btn_bg_color, true));
//        break;
//      default:
//        throw new UnsupportedOperationException(String.valueOf(view.getId()));
//    }
//  }
//
//  private void showArea() {
//
//    Observable.just(AssetsHelper.readData(this, "city.json"))
//        .flatMap(new Func1<String, Observable<List<Province>>>() {
//          @Override public Observable<List<Province>> call(String s) {
//            List<Province> provinceList = GsonHelper.convertEntities(s, Province.class);
//            return Observable.just(provinceList);
//          }
//        })
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new Subscriber<List<Province>>() {
//          @Override public void onCompleted() {
//
//          }
//
//          @Override public void onError(Throwable e) {
//
//          }
//
//          @Override public void onNext(List<Province> provinces) {
//            LogUtils.i(provinces);
//            ArrayList<Province> list = new ArrayList<Province>(provinces);
//            AddressPicker picker = new AddressPicker(PickActivity.this, list);
//            //picker.setHideProvince(hideProvince);
//            //picker.setHideCounty(hideCounty);
//            //if (hideCounty) {
//            //  picker.setColumnWeight(1 / 3.0f, 2 / 3.0f);//将屏幕分为3份，省级和地级的比例为1:2
//            //} else {
//            //  picker.setColumnWeight(2 / 8.0f, 3 / 8.0f, 3 / 8.0f);//省级、地级和县级的比例为2:3:3
//            //}
//            //picker.setSelectedItem(selectedProvince, selectedCity, selectedCounty);
//            //picker.setOnAddressPickListener(callback);
//            picker.show();
//          }
//        });
//  }
//
//  private void showYearPick() {
//
//    //final CommentDatePicker picker = new CommentDatePicker(this, DateTimePicker.YEAR_MONTH);
//    //picker.setRangeStartForCurTime();
//    //picker.setRangeEndForYear(20);
//    //
//    //picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
//    //  @Override public void onDatePicked(String year, String month, String day) {
//    //    showToast(year + "-" + month + "-" + day);
//    //  }
//    //});
//    //picker.setOnWheelListener(new DatePicker.OnWheelListener() {
//    //  @Override public void onYearWheeled(int index, String year) {
//    //    picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
//    //  }
//    //
//    //  @Override public void onMonthWheeled(int index, String month) {
//    //    picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
//    //  }
//    //
//    //  @Override public void onDayWheeled(int index, String day) {
//    //    picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
//    //  }
//    //});
//    //picker.show();
//
//    CommentAddressPicker picker = new CommentAddressPicker(this);
//    picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
//      @Override public void onAddressPicked(Province province, City city, County county) {
//        ToastUtils.show(province.getName() + " , " + city.getName());
//      }
//    });
//    picker.show();
//  }
//
//  private void showSingleOption() {
//    String[] strArray = { "100%", "90%", "80%", "70%", "60%", "50%" };
//    CommentOptionPicker commentOptionPicker = new CommentOptionPicker(this, strArray);
//    commentOptionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
//      @Override public void onOptionPicked(int index, String item) {
//        showToast(index + " , " + item);
//      }
//    });
//    commentOptionPicker.show();
//  }
//}
