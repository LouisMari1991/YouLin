package youlin.xinhua.com.youlin.widget.html;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import youlin.xinhua.com.youlin.MApp;
import youlin.xinhua.com.youlin.utils.LogUtils;

/**
 * <pre>
 *     desc   : TO-DO
 *     author : 罗顺翔
 *     time   : 2017-06-07 11:36
 *     version: 1.0
 * </pre>
 */

public class UrlImageGetter implements Html.ImageGetter {

  private TextView mTextView;

  public UrlImageGetter(TextView textView) {
    mTextView = textView;
  }

  @Override public Drawable getDrawable(String source) {
    LogUtils.i("source : " + source);
    final HtmlTextView.URLDrawable urlDrawable = new HtmlTextView.URLDrawable();

    Glide.with(MApp.get().getApplicationContext())
        .load(source)
        .asBitmap()
        .into(new SimpleTarget<Bitmap>() {
          @Override public void onResourceReady(Bitmap resource,
              GlideAnimation<? super Bitmap> glideAnimation) {
            //image.setImageBitmap(resource);
            LogUtils.i(" resource : "
                + resource
                + " , "
                + resource.getWidth()
                + " , "
                + resource.getHeight());
            int tvWidth = mTextView.getMeasuredWidth();
            int width = tvWidth;
            int height = tvWidth * resource.getHeight() / resource.getWidth();
            BitmapDrawable drawable = new BitmapDrawable(resource);
            drawable.setBounds(0, 0, width, height);
            urlDrawable.setBounds(0, 0, width, height);
            urlDrawable.setDrawable(drawable);
            mTextView.setText(mTextView.getText());
          }
        });

    return urlDrawable;
  }
}
