package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import youlin.xinhua.com.youlin.base.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.ToastUtils;
import youlin.xinhua.com.youlin.widget.photoview.EasePhotoView;

import static youlin.xinhua.com.youlin.constant.EaseConstant.EXTRA_IMG_URL;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-08-02 16:15
 * version: 1.0
 * </pre>
 */

public class ImageDetailActivity extends BaseActivity {

  public static void launch(Context context, String imageUrl) {
    Intent intent = new Intent(context, ImageDetailActivity.class);
    intent.putExtra(EXTRA_IMG_URL, imageUrl);
    context.startActivity(intent);
  }

  @BindView(R.id.image)         EasePhotoView mPhotoView;
  @BindView(R.id.pb_load_local) ProgressBar   mProgressBar;

  @Override protected int attachLayoutRes() {
    return R.layout.activity_img_detail;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mProgressBar.setVisibility(View.VISIBLE);
    mProgressBar.setClickable(false);
    String imgUrl = getIntent().getStringExtra(EXTRA_IMG_URL);
    Glide.with(this)
        .load(imgUrl)
        .crossFade(700)
        .listener(new RequestListener<String, GlideDrawable>() {
          @Override
          public boolean onException(Exception e, String model, Target<GlideDrawable> target,
              boolean isFirstResource) {
            ToastUtils.showToast("图片加载失败!");
            mProgressBar.setVisibility(View.GONE);
            return false;
          }

          @Override public boolean onResourceReady(GlideDrawable resource, String model,
              Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            mProgressBar.setVisibility(View.GONE);

            /**这里应该是加载成功后图片的高**/
            int height = mPhotoView.getHeight();
            int wHeight = getWindowManager().getDefaultDisplay().getHeight();
            if (height > wHeight) {
              mPhotoView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
              mPhotoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
            return false;
          }
        })
        .into(mPhotoView);
  }
}
