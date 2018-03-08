/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xinhua.tim.util;

import android.content.Context;
import android.net.Uri;
import android.text.Spannable;
import android.text.Spannable.Factory;
import android.text.style.ImageSpan;
import com.xinhua.tim.model.EaseDefaultEmojiconDatas;
import com.xinhua.tim.model.EaseEmojicon;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EaseSmileUtils {

  private static final Factory spannableFactory = Factory.getInstance();

  private static final Map<Pattern, Object> emoticons = new HashMap<>();

  static {
    EaseEmojicon[] emojicons = EaseDefaultEmojiconDatas.getData();
    for (EaseEmojicon emojicon : emojicons) {
      addPattern(emojicon.getEmojiText(), emojicon.getIcon());
    }
    //EaseEmojiconInfoProvider emojiconInfoProvider = EaseUI.getInstance().getEmojiconInfoProvider();
    //if(emojiconInfoProvider != null && emojiconInfoProvider.getTextEmojiconMapping() != null){
    //    for(Entry<String, Object> entry : emojiconInfoProvider.getTextEmojiconMapping().entrySet()){
    //        addPattern(entry.getKey(), entry.getValue());
    //    }
    //}

  }

  /**
   * add text and icon to the map
   *
   * @param emojiText-- text of emoji
   * @param icon -- resource id or local path
   */
  public static void addPattern(String emojiText, Object icon) {
    emoticons.put(Pattern.compile(Pattern.quote(emojiText)), icon);
  }

  /**
   * replace existing spannable with smiles
   */
  public static boolean addSmiles(Context context, Spannable spannable) {
    boolean hasChanges = false;
    for (Entry<Pattern, Object> entry : emoticons.entrySet()) {
      Matcher matcher = entry.getKey().matcher(spannable);
      while (matcher.find()) {
        boolean set = true;
        for (ImageSpan span : spannable.getSpans(matcher.start(), matcher.end(), ImageSpan.class))
          if (spannable.getSpanStart(span) >= matcher.start()
              && spannable.getSpanEnd(span) <= matcher.end()) {
            spannable.removeSpan(span);
          } else {
            set = false;
            break;
          }
        if (set) {
          hasChanges = true;
          Object value = entry.getValue();
          if (value instanceof String && !((String) value).startsWith("http")) {
            File file = new File((String) value);
            if (!file.exists() || file.isDirectory()) {
              return false;
            }
            spannable.setSpan(new ImageSpan(context, Uri.fromFile(file)), matcher.start(),
                matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
          } else {
            spannable.setSpan(new ImageSpan(context, (Integer) value), matcher.start(),
                matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
          }
        }
      }
    }

    return hasChanges;
  }

  public static Spannable getSiiledText(Context context, CharSequence text, String rexgString,
      int resId) {
    Spannable spannable = spannableFactory.newSpannable(text);
    Pattern pattern = Pattern.compile(rexgString);
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      spannable.setSpan(new ImageSpan(context, resId), matcher.start(), matcher.end(),
          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    return spannable;
  }

  public static Spannable getSmiledText(Context context, String text) {
    Spannable spannable = spannableFactory.newSpannable(text);
    addSmiles(context, spannable);
    return spannable;
  }

  public static boolean containsKey(String key) {
    boolean b = false;
    for (Entry<Pattern, Object> entry : emoticons.entrySet()) {
      Matcher matcher = entry.getKey().matcher(key);
      if (matcher.find()) {
        b = true;
        break;
      }
    }

    return b;
  }

  public static int getSmilesSize() {
    return emoticons.size();
  }
}
