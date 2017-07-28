package youlin.xinhua.com.youlin;

import android.app.Application;
import youlin.xinhua.com.youlin.constant.CacheConsts;
import youlin.xinhua.com.youlin.im.IMPlatform;
import youlin.xinhua.com.youlin.utils.FileUtil;

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
