package youlin.xinhua.com.youlin.constant;

import android.os.Environment;

/**
 * <pre>
 *     desc   : 缓存相关常量
 *     author : 罗顺翔
 *     time   : 2017-05-31 09:57
 *     version: 1.0
 * </pre>
 */

public interface CacheConsts {

  interface Assets {
    String NEWS_CHANNEL   = "NewsChannel";
    String CHINESE_CITIES = "ChineseCities";
  }

  interface UserConsts {
    String TOKEN     = "token";
    String USER_INFO = "user_info";
    String ORGS      = "orgs";
    String USERDATA  = "user_data";
    String PHONE_NUM = "phone_num";
    String AVATAR    = "avatar";
    String NICK_NAME = "nick_name";
  }

  interface ExternalStorage {
    String ZCHX_DIR      = Environment.getExternalStorageDirectory().getAbsolutePath();
    String APP_FOLDER    = "youlin";
    String APP_DIR       = ZCHX_DIR + "/" + APP_FOLDER;
    String DOCUMNETS_DIR = APP_DIR + "/Documents";// 用户文档目录
    String LOG_DIR       = APP_DIR + "/Logcat";
    String RESOURCE_DIR  = APP_DIR + "/Resource";// 公共资源
    String LOCAL_DIR     = APP_DIR + "/Local";// 单次运行的持久数据
    String TEMP_DIR      = APP_DIR + "/Temp";// 临时数据
    String GLIDE_DIR     = APP_DIR + "/GlideCache";// 图片缓存
    String VOICE_DIR     = APP_DIR + "/voice";// 语音文件
  }
}
