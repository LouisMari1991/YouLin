package youlin.xinhua.com.youlin.widget.html;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     desc   : TO-DO
 *     author : 罗顺翔
 *     time   : 2017-06-07 11:30
 *     version: 1.0
 * </pre>
 */

public class HtmlTextView extends TextView {

  private OnImageClickListener onImageClickListener;

  public HtmlTextView(Context context) {
    super(context);
  }

  public HtmlTextView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public HtmlTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  /**
   * 异步加载图片（依赖于Glide）
   */
  private Html.ImageGetter asyncImageGetter = new UrlImageGetter(this);

  /**
   * 设置富文本
   *
   * @param text 富文本
   */
  public void setRichText(String text) {
    Spanned spanned = Html.fromHtml(text, asyncImageGetter, null);
    SpannableStringBuilder spannableStringBuilder;
    if (spanned instanceof SpannableStringBuilder) {
      spannableStringBuilder = (SpannableStringBuilder) spanned;
    } else {
      spannableStringBuilder = new SpannableStringBuilder(spanned);
    }

    ImageSpan[] imageSpans =
        spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), ImageSpan.class);
    final ArrayList<String> imageUrls = new ArrayList<>();

    for (int i = 0, size = imageSpans.length; i < size; i++) {
      ImageSpan imageSpan = imageSpans[i];
      String imageUrl = imageSpan.getSource();
      int start = spannableStringBuilder.getSpanStart(imageSpan);
      int end = spannableStringBuilder.getSpanEnd(imageSpan);
      imageUrls.add(imageUrl);

      final int finalI = i;
      ClickableSpan clickableSpan = new ClickableSpan() {
        @Override public void onClick(View widget) {
          if (onImageClickListener != null) {
            onImageClickListener.imageClicked(imageUrls, finalI);
          }
          //ImageDetailActivity.launch(widget.getContext(), finalI, imageUrls);
        }
      };
      ClickableSpan[] clickableSpans =
          spannableStringBuilder.getSpans(start, end, ClickableSpan.class);
      if (clickableSpans != null && clickableSpans.length != 0) {
        for (ClickableSpan cs : clickableSpans) {
          spannableStringBuilder.removeSpan(cs);
        }
      }
      spannableStringBuilder.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    super.setText(spanned);
    setMovementMethod(LinkMovementMethod.getInstance());
  }

  public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
    this.onImageClickListener = onImageClickListener;
  }

  public static final class URLDrawable extends BitmapDrawable {
    private Drawable drawable;

    @SuppressWarnings("deprecation") public URLDrawable() {
    }

    @Override public void draw(Canvas canvas) {
      if (drawable != null) {
        drawable.draw(canvas);
      }
    }

    public void setDrawable(Drawable drawable) {
      this.drawable = drawable;
    }
  }

  public interface OnImageClickListener {
    /**
     * 图片被点击后的回调方法
     *
     * @param imageUrls 本篇富文本内容里的全部图片
     * @param position 点击处图片在imageUrls中的位置
     */
    void imageClicked(List<String> imageUrls, int position);
  }
}
