package youlin.xinhua.com.youlin;

import android.app.Application;
import com.iflytek.cloud.SpeechUtility;
import youlin.xinhua.com.youlin.constant.CacheConsts;
import youlin.xinhua.com.youlin.im.IMPlatform;
import youlin.xinhua.com.youlin.utils.FileUtil;
import youlin.xinhua.com.youlin.utils.LogUtils;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-13 16:23
 * version: 1.0
 * </pre>
 */

public class MApp extends Application {

  private static Application context;

  @Override public void onCreate() {
    super.onCreate();
    context = this;
    IMPlatform.get().init(this);
    initAppFolders();

    // 应用程序入口处调用，避免手机内存过小，杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
    // 如在Application中调用初始化，需要在Mainifest中注册该Applicaiton
    // 注意：此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，请增加参数：SpeechConstant.FORCE_LOGIN+"=true"
    // 参数间使用半角“,”分隔。
    // 设置你申请的应用appid,请勿在'='与appid之间添加空格及空转义符

    // 注意： appid 必须和下载的SDK保持一致，否则会出现10407错误
    SpeechUtility utility = SpeechUtility.createUtility(MApp.this, "appid=5a09469d");
    LogUtils.i("onCreate ,  SpeechUtility : " + utility);
  }

  public static Application get() {
    return context;
  }

  public void initAppFolders() {
    FileUtil.createFolder(CacheConsts.ExternalStorage.APP_DIR);
    FileUtil.createFolder(CacheConsts.ExternalStorage.DOCUMNETS_DIR);
    FileUtil.createFolder(CacheConsts.ExternalStorage.LOG_DIR);
    FileUtil.createFolder(CacheConsts.ExternalStorage.RESOURCE_DIR);
    FileUtil.createFolder(CacheConsts.ExternalStorage.LOCAL_DIR);
    FileUtil.createFolder(CacheConsts.ExternalStorage.TEMP_DIR);
    FileUtil.createFolder(CacheConsts.ExternalStorage.GLIDE_DIR);
    FileUtil.createFolder(CacheConsts.ExternalStorage.VOICE_DIR);
  }
}
