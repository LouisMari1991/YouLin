package youlin.xinhua.com.youlin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * desc:日期计算工具类
 * author:TanXueMei
 * time:2017/5/20
 * version:1.0
 * </pre>
 */

public class DateUtil {

  public static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
    @Override protected SimpleDateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd");
    }
  };

  public static ThreadLocal<SimpleDateFormat> dateFormater3 = new ThreadLocal<SimpleDateFormat>() {
    @Override protected SimpleDateFormat initialValue() {
      return new SimpleDateFormat("MM月dd日 HH点mm分");
    }
  };

  public static ThreadLocal<SimpleDateFormat> dateFormater4 = new ThreadLocal<SimpleDateFormat>() {
    @Override protected SimpleDateFormat initialValue() {
      return new SimpleDateFormat("MM月dd日 HH点");
    }
  };

  public static ThreadLocal<SimpleDateFormat> dateFormater5 = new ThreadLocal<SimpleDateFormat>() {
    @Override protected SimpleDateFormat initialValue() {
      return new SimpleDateFormat("HH:mm:ss");
    }
  };

  public static String normalTimeFormat(String sdate) {
    Date time = toDate(sdate);
    if (time == null) {
      return "";
    }
    String ftime;
    if (time.getMinutes() == 0) {
      ftime = dateFormater4.get().format(time);
    } else {
      ftime = dateFormater3.get().format(time);
    }
    return ftime;
  }

  public static String friendlyTimeFormat(String sdate) {
    Date time = toDate(sdate);
    if (time == null) {
      return "";
    }
    String ftime = "";
    Calendar cal = Calendar.getInstance();

    //判断是否是同一天
    String curDate = dateFormater2.get().format(cal.getTime());
    String paramDate = dateFormater2.get().format(time);
    if (curDate.equals(paramDate)) {
      int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
      if (hour == 0) {
        ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
      } else {
        ftime = hour + "小时前";
      }
      return ftime;
    }

    long lt = time.getTime() / 86400000;
    long ct = cal.getTimeInMillis() / 86400000;
    int days = (int) (ct - lt);
    if (days == 0) {
      int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
      if (hour == 0) {
        ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
      } else {
        ftime = hour + "小时前";
      }
    } else if (days == 1) {
      ftime = "昨天";
    } else if (days == 2) {
      ftime = "前天";
    }
    // else if(days > 2 && days <= 10){
    // ftime = days+"天前";
    // }
    else if (days > 2 && days <= 30) {
      ftime = days + "天前";
    } else if (days > 30 && days <= 60) {
      ftime = "1个月前";
    } else if (days > 60 && days <= 90) {
      ftime = "2个月前";
    } else if (days > 90 && days <= 120) {
      ftime = "3个月前";
    } else if (days > 120 && days <= 150) {
      ftime = "4个月前";
    } else if (days > 150 && days <= 180) {
      ftime = "5个月前";
    } else if (days > 180 && days <= 210) {
      ftime = "6个月前";
    } else if (days > 210 && days <= 240) {
      ftime = "7个月前";
    } else if (days > 240 && days <= 270) {
      ftime = "8个月前";
    } else if (days > 270 && days <= 300) {
      ftime = "9个月前";
    } else if (days > 300 && days <= 330) {
      ftime = "10个月前";
    } else if (days > 330 && days <= 360) {
      ftime = "11个月前";
    } else if (days > 360 && days <= 720) {
      ftime = "一年前";
    } else if (days > 720 && days <= 1080) {
      ftime = "两年前";
    } else if (days > 1080) {
      ftime = dateFormater2.get().format(time);
    }
    return ftime;
  }

  /**
   * 当前时间10分钟之内,返回: "刚刚"
   */
  public static String chatTimeFormat(long sdate) {
    Date time = new Date(sdate);
    if (time == null) {
      return "";
    }
    String ftime = "";
    Calendar cal = Calendar.getInstance();

    //判断是否是同一天
    String curDate = dateFormater2.get().format(cal.getTime());
    String paramDate = dateFormater2.get().format(time);
    if (curDate.equals(paramDate)) {
      int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
      if (hour == 0) {

        long temp = (cal.getTimeInMillis() - time.getTime()) / 60000;
        if (temp <= 10) {
          return "刚刚";
        } else {
          ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
        }
      } else {
        ftime = hour + "小时前";
      }
      return ftime;
    }

    long lt = time.getTime() / 86400000;
    long ct = cal.getTimeInMillis() / 86400000;
    int days = (int) (ct - lt);
    if (days == 0) {
      int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
      if (hour == 0) {
        ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
      } else {
        ftime = hour + "小时前";
      }
    } else if (days == 1) {
      ftime = "昨天";
    } else if (days == 2) {
      ftime = "前天";
    }
    // else if(days > 2 && days <= 10){
    // ftime = days+"天前";
    // }
    else if (days > 2 && days <= 30) {
      ftime = days + "天前";
    } else if (days > 30 && days <= 60) {
      ftime = "1个月前";
    } else if (days > 60 && days <= 90) {
      ftime = "2个月前";
    } else if (days > 90 && days <= 120) {
      ftime = "3个月前";
    } else if (days > 120 && days <= 150) {
      ftime = "4个月前";
    } else if (days > 150 && days <= 180) {
      ftime = "5个月前";
    } else if (days > 180 && days <= 210) {
      ftime = "6个月前";
    } else if (days > 210 && days <= 240) {
      ftime = "7个月前";
    } else if (days > 240 && days <= 270) {
      ftime = "8个月前";
    } else if (days > 270 && days <= 300) {
      ftime = "9个月前";
    } else if (days > 300 && days <= 330) {
      ftime = "10个月前";
    } else if (days > 330 && days <= 360) {
      ftime = "11个月前";
    } else if (days > 360 && days <= 720) {
      ftime = "一年前";
    } else if (days > 720 && days <= 1080) {
      ftime = "两年前";
    } else if (days > 1080) {
      ftime = dateFormater2.get().format(time);
    }
    return ftime;
  }

  /**
   * 倒计时
   */
  public static String formatTimeDown(long sdate) {
    Date time = new Date(sdate);
    if (time == null) {
      return null;
    }
    StringBuffer sb = new StringBuffer();
    long day = time.getTime() / 86400000;
    if (day > 0) {
      sb.append(day).append("天").append(" ");
    }
    sb.append(dateFormater5.get().format(time));
    return sb.toString();
  }

  /**
   *      * 将字符串转位日期类型
   *      *
   *      * @param sdate
   *      * @return
   *      
   */
  public static Date toDate(String sdate) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return sdf.parse(sdate);
    } catch (ParseException e) {
      return null;
    }
  }
}
