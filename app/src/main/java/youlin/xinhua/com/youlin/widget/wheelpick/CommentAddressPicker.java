package youlin.xinhua.com.youlin.widget.wheelpick;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.widget.WheelView;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.AssetsHelper;
import youlin.xinhua.com.youlin.utils.GsonHelper;
import youlin.xinhua.com.youlin.utils.ListUtils;

/**
 * <pre>
 * @author : 罗顺翔
 * desc   : Todo
 * time   : 2017-10-26 10:46
 * version: 1.0
 * </pre>
 */

public class CommentAddressPicker {

  ArrayList<Province> mProvinces;

  AddressPicker mPicker;

  Activity activity;

  boolean isHideCountry = true;

  AddressPicker.OnAddressPickListener mOnAddressPickListener;

  public CommentAddressPicker(Activity activity) {
    this.activity = activity;
  }

  public void show() {
    if (ListUtils.isEmpty(mProvinces)) {
      Observable.just(AssetsHelper.readData(activity, "city.json"))
          .flatMap(new Func1<String, Observable<List<Province>>>() {
            @Override public Observable<List<Province>> call(String s) {
              List<Province> provinceList = GsonHelper.convertEntities(s, Province.class);
              return Observable.just(provinceList);
            }
          })
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Subscriber<List<Province>>() {
            @Override public void onCompleted() {

            }

            @Override public void onError(Throwable e) {

            }

            @Override public void onNext(List<Province> provinces) {
              mProvinces = new ArrayList<>(provinces);
              showPicker();
            }
          });
    } else {
      showPicker();
    }
  }

  public void setOnAddressPickListener(AddressPicker.OnAddressPickListener addressPickListener) {
    mOnAddressPickListener = addressPickListener;
  }

  public void setHideCounty(boolean isHideCountry) {
    this.isHideCountry = isHideCountry;
  }

  private void showPicker() {
    mPicker = new AddressPicker(activity, mProvinces);
    mPicker.setCanceledOnTouchOutside(true);
    mPicker.setLineSpaceMultiplier(3);
    mPicker.setLabel("", "", "");
    mPicker.setDividerRatio(WheelView.DividerConfig.FILL);
    mPicker.setOffset(2);
    mPicker.setTextColor(ActivityCompat.getColor(activity, R.color.black_text));
    mPicker.setDividerColor(ActivityCompat.getColor(activity, R.color.color_gray_light));
    mPicker.setTopLineColor(ActivityCompat.getColor(activity, R.color.color_gray_light));
    mPicker.setHideCounty(isHideCountry);
    if (mOnAddressPickListener != null) {
      mPicker.setOnAddressPickListener(mOnAddressPickListener);
    }
    mPicker.show();
  }
}
