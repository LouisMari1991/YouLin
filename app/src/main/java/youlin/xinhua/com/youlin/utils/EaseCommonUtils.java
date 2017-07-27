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
package youlin.xinhua.com.youlin.utils;

import android.text.TextUtils;
import com.hyphenate.util.HanziToPinyin;
import java.util.ArrayList;
import youlin.xinhua.com.youlin.bean.UserInfo;

public class EaseCommonUtils {
  private static final String TAG = "CommonUtils";

  /**
   * set initial letter of according user's nickname( username if no nickname)
   *
   * @param user
   */
  public static void setUserInitialLetter(UserInfo user) {
    final String DefaultLetter = "#";
    String letter = DefaultLetter;

    final class GetInitialLetter {
      String getLetter(String name) {
        if (TextUtils.isEmpty(name)) {
          return DefaultLetter;
        }
        char char0 = name.toLowerCase().charAt(0);
        if (Character.isDigit(char0)) {
          return DefaultLetter;
        }
        ArrayList<HanziToPinyin.Token> l = HanziToPinyin.getInstance().get(name.substring(0, 1));
        if (l != null && l.size() > 0 && l.get(0).target.length() > 0) {
          HanziToPinyin.Token token = l.get(0);
          String letter = token.target.substring(0, 1).toUpperCase();
          char c = letter.charAt(0);
          if (c < 'A' || c > 'Z') {
            return DefaultLetter;
          }
          return letter;
        }
        return DefaultLetter;
      }
    }

    if (!TextUtils.isEmpty(user.getNickname())) {
      letter = new GetInitialLetter().getLetter(user.getNickname());
      user.setInitialLetter(letter);
      return;
    }
    if (letter.equals(DefaultLetter) && !TextUtils.isEmpty(user.getMobilePhone())) {
      letter = new GetInitialLetter().getLetter(user.getMobilePhone());
    }
    LogUtils.i(" letter : " + letter);
    user.setInitialLetter(letter);
  }
}
