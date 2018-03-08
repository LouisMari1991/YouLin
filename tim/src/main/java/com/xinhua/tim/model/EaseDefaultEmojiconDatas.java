package com.xinhua.tim.model;

import com.xinhua.tim.R;

public class EaseDefaultEmojiconDatas {

  private static int[] icons = new int[] {
      R.drawable.ecf, R.drawable.ecv, R.drawable.ecb, R.drawable.ecy, R.drawable.ebu,
      R.drawable.ebr, R.drawable.ecc, R.drawable.eft, R.drawable.ecr, R.drawable.ebs,
      R.drawable.ech, R.drawable.ecg, R.drawable.ebh, R.drawable.ebg, R.drawable.ecp,
      R.drawable.deg, R.drawable.ecd, R.drawable.ecj, R.drawable.ebv, R.drawable.ece,
      R.drawable.ebl, R.drawable.eca, R.drawable.ecn, R.drawable.eco, R.drawable.eeo,
      R.drawable.eep, R.drawable.eci, R.drawable.ebj, R.drawable.eer, R.drawable.edi,
      R.drawable.ebq, R.drawable.eeq, R.drawable.ecq, R.drawable.ebt, R.drawable.ede,
      R.drawable.eew, R.drawable.eex, R.drawable.dga, R.drawable.ebp, R.drawable.ebo,
  };

  private static String[] emojis = new String[] {
      "[ecf]", "[ecv]", "[ecb]", "[ecy]", "[ebu]",

      "[ebr]", "[ecc]", "[eft]", "[ecr]", "[ebs]",

      "[ech]", "[ecg]", "[ebh]", "[ebg]", "[ecp]",

      "[deg]", "[ecd]", "[ecj]", "[ebv]", "[ece]",

      "[ebl]", "[eca]", "[ecn]", "[eco]", "[eeo]",

      "[eep]", "[eci]", "[ebj]", "[eer]", "[edi]",

      "[ebq]", "[eeq]", "[ecq]", "[ebt]", "[ede]",

      "[eew]", "[eex]", "[dga]", "[ebp]", "[ebo]",
  };

  private static final EaseEmojicon[] DATA = createData();

  private static EaseEmojicon[] createData() {
    EaseEmojicon[] datas = new EaseEmojicon[icons.length];
    for (int i = 0; i < icons.length; i++) {
      datas[i] = new EaseEmojicon(icons[i], emojis[i], EaseEmojicon.Type.NORMAL);
    }
    return datas;
  }

  public static EaseEmojicon[] getData() {
    return DATA;
  }
}
