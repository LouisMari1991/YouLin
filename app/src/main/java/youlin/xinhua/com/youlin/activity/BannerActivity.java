package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import butterknife.BindView;
import com.xinhua.recycler.adapter.BaseQuickAdapter;
import com.xinhua.recycler.adapter.BaseViewHolder;
import com.xinhua.recycler.helper.RecyclerViewHelper;
import java.util.ArrayList;
import java.util.List;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.bean.TuiJianBean;
import youlin.xinhua.com.youlin.widget.CarouselView;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-14 19:05
 * version: 1.0
 * </pre>
 */

public class BannerActivity extends BaseActivity {

  public static final void launch(Context context) {
    Intent intent = new Intent(context, BannerActivity.class);
    context.startActivity(intent);
  }

  @BindView(R.id.rv_yunxun_list) RecyclerView mRecyclerView;

  CarouselView mCarouselView;

  @Override protected int attachLayoutRes() {
    return R.layout.activity_banner;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    TuiJianBean.Banner banner0 = new TuiJianBean.Banner();
    banner0.setTitle(" 念落灼灼桃花十里");
    banner0.setUrl("http://otiwf3ulm.bkt.clouddn.com/1301a18845a986101aa7bbbbca4ff8c0.png");
    TuiJianBean.Banner banner1 = new TuiJianBean.Banner();
    banner1.setTitle("童年，那老街的苦楝树");
    banner1.setUrl("http://otiwf3ulm.bkt.clouddn.com/d9153249393eb1c106781ed1c6866207.png");
    TuiJianBean.Banner banner2 = new TuiJianBean.Banner();
    banner2.setTitle("相遇文字，相遇你");
    banner2.setUrl("http://otiwf3ulm.bkt.clouddn.com/ghost.jpg");
    TuiJianBean.Banner banner3 = new TuiJianBean.Banner();
    banner3.setTitle("不殆时间，不负自己");
    banner3.setUrl("http://otiwf3ulm.bkt.clouddn.com/f23542b9250bc0ecdfc67f7b0465b928.png");
    TuiJianBean.Banner banner4 = new TuiJianBean.Banner();
    banner4.setTitle("在岁月里，做一个懂得的人");
    banner4.setUrl("http://otiwf3ulm.bkt.clouddn.com/5a6cdc92a75ab4e2a02971170c6a9ab4.png");

    BaseQuickAdapter adapter = new BaseQuickAdapter(this) {
      @Override protected int attachLayoutRes() {
        return R.layout.item_contact;
      }

      @Override protected void convert(BaseViewHolder baseViewHolder, Object o) {

      }
    };

    List<TuiJianBean.Banner> bannerList = new ArrayList<>();
    bannerList.add(banner0);
    bannerList.add(banner1);
    bannerList.add(banner2);
    bannerList.add(banner3);
    bannerList.add(banner4);

    RecyclerViewHelper.initRecyclerViewV(this, mRecyclerView, adapter);

    View view = LayoutInflater.from(this).inflate(R.layout.layout_head_newslistad, null);
    mCarouselView = (CarouselView) view.findViewById(R.id.slider_ads);
    mCarouselView.setScrollInterval(4000);
    mCarouselView.setImageList(bannerList);
    adapter.addHeaderView(view);
  }
}
