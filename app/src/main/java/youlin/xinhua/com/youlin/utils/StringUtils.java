package youlin.xinhua.com.youlin.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-12 12:03
 * version: 1.0
 * </pre>
 */

public class StringUtils {

  public static CharSequence getLightText(String src, String find) {
    if (!TextUtils.isEmpty(src) && !TextUtils.isEmpty(find) && src.contains(find)) {
      SpannableString spannableString = new SpannableString(src);
      int startIndex = src.indexOf(find);
      int endIndex = startIndex + find.length();
      spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, startIndex,
          Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), startIndex, endIndex,
          Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      if (endIndex != src.length()) {

        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), endIndex, src.length(),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
      return spannableString;
    }
    return src;
  }


  public static CharSequence getLightText(Context context, String src, String find, int normalColor,
      int lightColor) {
    if (!TextUtils.isEmpty(src) && !TextUtils.isEmpty(find) && src.contains(find)) {
      SpannableString spannableString = new SpannableString(src);
      int startIndex = src.indexOf(find);
      int endIndex = startIndex + find.length();
      spannableString.setSpan(
          new ForegroundColorSpan(ActivityCompat.getColor(context, normalColor)), 0, startIndex,
          Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      spannableString.setSpan(new ForegroundColorSpan(ActivityCompat.getColor(context, lightColor)),
          startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      if (endIndex != src.length()) {
        spannableString.setSpan(
            new ForegroundColorSpan(ActivityCompat.getColor(context, normalColor)), endIndex,
            src.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
      return spannableString;
    }
    return src;
  }
}
