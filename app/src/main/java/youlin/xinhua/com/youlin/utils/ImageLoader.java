package youlin.xinhua.com.youlin.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import java.io.File;
import java.util.concurrent.ExecutionException;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 *     author : ${xuemei}
 *     e-mail : 1840494174@qq.com
 *     time   : 2017/04/19
 *     desc   : 图片加载帮助类
 *       不加dontAnimate(),有的机型会出现图片变形的情况，
 *       先记下找到更好的方法再处理
 *     version: 1.0
 * </pre>
 */

public class ImageLoader {

  private ImageLoader() {
    throw new RuntimeException("ImageLoader cannot be initialized!");
  }

  /**
   * 显示list中的item图片
   *
   * @param imageSize 1.大图片(720*386) 2.中等图片(360*240) 3.小图片(230*150)
   */
  public static void displayFadeImage(int imageSize, String imageUrl, ImageView imageView,
      RequestListener<String, GlideDrawable> listener) {
    Glide.with(imageView.getContext())
        .load(imageUrl)
        .placeholder(getYunXunDefaultPic(imageSize))
        .error(getYunXunDefaultPic(imageSize))
        .crossFade(1500)
        .listener(listener)
        .into(imageView);
  }

  /**
   * 加载头像, default:  R.drawable.ic_default_avatar
   *
   * @param avatarView 头像
   * @param avatarUrl url
   */
  public static void displayAvatar(ImageView avatarView, String avatarUrl) {
    Glide.with(avatarView.getContext())
        .load(avatarUrl)
        .crossFade(500)
        .error(R.drawable.ic_default_avatar)
        .transform(new GlideCircleTransform(avatarView.getContext()))
        .into(avatarView);
  }

  /**
   * 加载头像, default:  R.drawable.ic_default_avatar
   *
   * @param avatarView 头像
   * @param avatarUrl url
   */
  public static void displayAvatar(ImageView avatarView, byte[] avatarUrl) {
    Glide.with(avatarView.getContext())
        .load(avatarUrl)
        .crossFade(500)
        .error(R.drawable.ic_default_avatar)
        .transform(new GlideCircleTransform(avatarView.getContext()))
        .into(avatarView);
  }

  /**
   * 加载圆角图
   */
  public static void displayCircle(ImageView imageView, String imageUrl) {
    Glide.with(imageView.getContext())
        .load(imageUrl)
        .crossFade(500)
        .error(R.drawable.ic_default_avatar)
        .transform(new GlideCircleTransform(imageView.getContext()))
        .into(imageView);
  }

  /**
   * 显示list中的item图片
   *
   * @param imageSize 1.大图片(720*386) 2.中等图片(360*240) 3.小图片(230*150)
   */
  public static void displayFadeImage(int imageSize, String imageUrl, ImageView imageView) {
    Glide.with(imageView.getContext())
        .load(imageUrl)
        .placeholder(getYunXunDefaultPic(imageSize))
        .error(getYunXunDefaultPic(imageSize))
        .crossFade(1500)
        .into(imageView);
  }

  public static void displayFadeImage(String imageUrl, ImageView imageView,
      @DrawableRes int defaultDrawable) {
    Glide.with(imageView.getContext())
        .load(imageUrl)
        .placeholder(defaultDrawable)
        .error(defaultDrawable)
        .crossFade(500)
        .into(imageView);
  }

  /**
   * 加载聊天Item中的图片
   */
  public static void displayChatRowPicture(String picUrl, ImageView imageView) {
    //Glide.with(imageView.getContext())
    //    .load(picUrl)
    //    .placeholder(R.drawable.aurora_picture_not_found)
    //    .error(R.drawable.aurora_picture_not_found)
    //    .dontAnimate()
    //    .into(imageView);

    // You can use other image load libraries.
    Glide.with(imageView.getContext())
        .load(picUrl)
        .fitCenter()
        .placeholder(R.drawable.aurora_picture_not_found)
        .override(400, Target.SIZE_ORIGINAL)
        .into(imageView);
  }

  public static void loadFit(Context context, String url, ImageView imageView, int defaultResId) {
    if (NetUtil.isNetworkAvailable(context)) {
      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
      Glide.with(context)
          .load(url)
          .fitCenter()
          .dontAnimate()
          .placeholder(defaultResId)
          .into(imageView);
    } else {
      imageView.setImageResource(defaultResId);
    }
  }

  public static void loadCenterCrop(Context context, String url, ImageView imageView,
      int defaultResId) {
    if (NetUtil.isNetworkAvailable(context)) {
      Glide.with(context)
          .load(url)
          .centerCrop()
          .dontAnimate()
          .placeholder(defaultResId)
          .error(defaultResId)
          .into(imageView);
    } else {
      imageView.setImageResource(defaultResId);
    }
  }

  public static void loadFitCenter(Context context, String url, ImageView imageView,
      int defaultResId) {
    if (NetUtil.isNetworkAvailable(context)) {
      Glide.with(context)
          .load(url)
          .fitCenter()
          .dontAnimate()
          .placeholder(defaultResId)
          .into(imageView);
    } else {
      imageView.setImageResource(defaultResId);
    }
  }

  public static void loadHeadPortrait(Context context, byte[] bitmap, ImageView imageView,
      int defaultResId) {
    if (NetUtil.isNetworkAvailable(context)) {
      Glide.with(context).load(bitmap).transform(new GlideCircleTransform(context)).into(imageView);
    } else {
      imageView.setImageResource(defaultResId);
    }
  }

  /**
   * 带图片监听的
   */
  public static void loadFitCenter(Context context, String url, ImageView imageView,
      RequestListener listener) {
    Glide.with(context).load(url).fitCenter().dontAnimate().listener(listener).into(imageView);
  }

  public static void loadCenterCrop(Context context, String url, ImageView imageView,
      RequestListener listener) {
    Glide.with(context).load(url).centerCrop().dontAnimate().listener(listener).into(imageView);
  }

  /**
   * 设置图片大小处理
   */
  public static void loadFitOverride(Context context, String url, ImageView imageView,
      int defaultResId, int width, int height) {
    if (NetUtil.isNetworkAvailable(context)) {
      Glide.with(context)
          .load(url)
          .fitCenter()
          .dontAnimate()
          .override(width, height)
          .placeholder(defaultResId)
          .into(imageView);
    } else {
      imageView.setImageResource(defaultResId);
    }
  }

  /**
   * 计算图片分辨率
   */
  public static String calePhotoSize(Context context, String url)
      throws ExecutionException, InterruptedException {
    File file = Glide.with(context)
        .load(url)
        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        .get();
    // First decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    return options.outWidth + "*" + options.outHeight;
  }

  public static void loadImage(int type, String url, final ImageView imageView) {

    //Glide.with(imageView.getContext())
    //    .load(url)
    //    .placeholder(getYunXunDefaultPic(2))
    //    .error(getYunXunDefaultPic(2))
    //    .override(80,80)
    //    .into(imageView);

    Glide.with(imageView.getContext())
        .load(url)
        .asBitmap()
        .placeholder(getYunXunDefaultPic(type))
        .error(getYunXunDefaultPic(type))
        .into(new BitmapImageViewTarget(imageView) {
          @Override protected void setResource(Bitmap resource) {
            super.setResource(resource);
            int width = resource.getWidth();
            int height = resource.getHeight();
            //获取imageView的宽
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            int imageViewWidth = imageView.getWidth();
            int imageViewHeight = imageView.getHeight();
            //float scaleWidth = ((float) imageViewWidth) / width;
            //float scaleHeight = ((float) imageViewHeight) / height;
            //Matrix matrix = new Matrix();
            //// 缩放图片动作
            //matrix.postScale(scaleWidth, scaleHeight);
            //Bitmap bitmap = Bitmap.createBitmap(resource, 0, 0, (int) width,
            //    (int) height, matrix, true);
            //params.width = bitmap.getWidth();
            //params.height = bitmap.getHeight();
            float sy = 1;
            if (width < height) {
              sy = (float) (imageViewWidth * 0.1) / (float) (width * 0.1);
              imageViewHeight = (int) (height * sy);
              params.height = imageViewHeight;
            } else {
              sy = (float) (imageViewHeight * 0.1) / (float) (height * 0.1);
              imageViewWidth = (int) (width * sy);
              params.width = imageViewWidth;
            }

            imageView.setLayoutParams(params);
          }
        });
  }

  //public static void loadImageCir(int type, String url, final ImageView imageView) {
  //
  //  Glide.with(imageView.getContext())
  //      .load(url)
  //      .asBitmap()
  //      .placeholder(getYunXunDefaultPic(type))
  //      .error(getYunXunDefaultPic(type))
  //      .transform(new GlideRoundTransform(imageView.getContext(),20))
  //      .into(imageView);
  //}

  public static int getYunXunDefaultPic(int imageSize) {
    switch (imageSize) {
      case 1:
        return R.mipmap.default_big_one;
      case 2:
      case 3:
        return R.mipmap.default_two_to_three;
    }
    return R.mipmap.default_two_to_three;
  }
}
