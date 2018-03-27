package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import jp.wasabeef.glide.transformations.BlurTransformation;
import youlin.xinhua.com.youlin.base.BaseActivity;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2017/12/01
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class GlideTransformationActivity extends BaseActivity {

  @BindView(R.id.img_avatar) ImageView img_avatar;
  @BindView(R.id.img_profile_bg) ImageView img_profile_bg;
  @BindView(R.id.img_profile_bg_1) ImageView img_profile_bg_1;

  public static void start(Context context) {
    Intent starter = new Intent(context, GlideTransformationActivity.class);
    context.startActivity(starter);
  }

  //String IMAGE_URL_MEDIUM = "http://img2.imgtn.bdimg.com/it/u=4225722360,1367332860&fm=27&gp=0.jpg";
  String IMAGE_URL_MEDIUM = "https://img1.doubanio.com/mpic/s4477716.jpg";

  @Override protected int attachLayoutRes() {
    return R.layout.activity_glide_transformation;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Glide.with(this)
        .load(IMAGE_URL_MEDIUM)
        //                .placeholder(R.drawable.stackblur_default)
        .error(R.drawable.stackblur_default)
        .bitmapTransform(new BlurTransformation(this, 14, 1))// 设置高斯模糊
        .listener(new RequestListener<String, GlideDrawable>() {//监听加载状态
          @Override
          public boolean onException(Exception e, String model, Target<GlideDrawable> target,
              boolean isFirstResource) {
            return false;
          }

          @Override public boolean onResourceReady(GlideDrawable resource, String model,
              Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            //img_profile_bg.setImageAlpha(0);
            //img_profile_bg.setVisibility(View.VISIBLE);
            return false;
          }
        })
        .into(img_profile_bg);

    Glide.with(this)
        .load(IMAGE_URL_MEDIUM)
        .error(R.drawable.stackblur_default)
        .placeholder(R.drawable.stackblur_default)
        .crossFade(500)
        .bitmapTransform(new BlurTransformation(this, 14, 3))
        .into(img_profile_bg_1);
  }
}
