package youlin.xinhua.com.youlin.utils;

import android.content.Context;
import android.support.annotation.FloatRange;
import android.support.v4.app.ActivityCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

/**
 * Description:
 * Author：Mari on 2017-11-01 11:37
 * Contact：289168296@qq.com
 */
public class StringUtils {

  /**
   * 高亮话题
   * eg: #活动# 活动开始了哦
   *
   *
   * AbsoluteSizeSpan(int size) ：设置字体大小，参数是绝对数值，相当于Word中的字体大小。
   *
   * 　　RelativeSizeSpan(float proportion) ：设置字体大小，参数是相对于默认字体大小的倍数。
   *
   * 　　ScaleXSpan(float proportion)：缩放字体，与上面的类似，默认为1,设置后就是原来的乘以proportion，大于1时放大(zoon
   * in)，小于时缩小(zoom
   * out)。
   *
   * 　　BackgroundColorSpan(int color)：背景着色，参数是颜色数值，可以直接使用android.graphics.Color里面定义的常量，或是用Color.rgb(int,
   * int, int)。
   *
   * 　　ForegroundColorSpan(int color)：前景着色，也就是字的着色，参数与背景着色一致。
   *
   * 　　TypefaceSpan(String family)：字体，参数是字体的名字比如“sans", "sans-serif"等。
   *
   * 　　StyleSpan(Typeface style) ：字体风格，比如粗体，斜体，参数是android.graphics.Typeface里面定义的常量，如Typeface.BOLD，Typeface.ITALIC等等。
   *
   * 　　StrikethroughSpan：如果设置了此风格，会有一条线从中间穿过所有的字，就像被划掉一样。
   */
  public static CharSequence getLightText(Context context, String src, String find, int normalColor, int lightColor) {
    return getLightText(context, src, find, normalColor, lightColor, false);
  }

  public static CharSequence getLightText(Context context, String src, String find, int normalColor, int lightColor,
      boolean scaleFind) {
    return getLightText(context, src, find, normalColor, lightColor, scaleFind, 1.5f);
  }

  public static CharSequence getLightText(Context context, String src, String find, int normalColor, int lightColor,
      boolean scaleFind, @FloatRange(from = 1.0, to = 2.0) float scaleProportion) {
    if (!TextUtils.isEmpty(src) && !TextUtils.isEmpty(find) && src.contains(find)) {
      SpannableString spannableString = new SpannableString(src);
      int startIndex = src.indexOf(find);
      int endIndex = startIndex + find.length();
      spannableString.setSpan(new ForegroundColorSpan(ActivityCompat.getColor(context, normalColor)), 0, startIndex,
          Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      spannableString.setSpan(new ForegroundColorSpan(ActivityCompat.getColor(context, lightColor)), startIndex,
          endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      if (scaleFind) {
        spannableString.setSpan(new RelativeSizeSpan(scaleProportion), startIndex, endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
      if (endIndex != src.length()) {
        spannableString.setSpan(new ForegroundColorSpan(ActivityCompat.getColor(context, normalColor)), endIndex,
            src.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
      return spannableString;
    }
    return src;
  }
}
