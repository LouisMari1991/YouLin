package youlin.xinhua.com.youlin;

import android.app.Application;
import android.content.Context;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.ext.group.TIMUserConfigGroupExt;
import com.tencent.imsdk.ext.message.TIMUserConfigMsgExt;
import com.tencent.imsdk.ext.sns.TIMUserConfigSnsExt;
import com.tencent.tim.consts.TIMConsts;
import com.tencent.tim.impl.TIMConnListenerImpl;
import com.tencent.tim.impl.TIMFriendshipProxyListenerImpl;
import com.tencent.tim.impl.TIMGroupAssistantListenerImpl;
import com.tencent.tim.impl.TIMMessageListenerImpl;
import com.tencent.tim.impl.TIMMessageRevokedListenerImpl;
import com.tencent.tim.impl.TIMRefreshListenerImpl;
import com.tencent.tim.impl.TIMUserStatusListenerImpl;
import youlin.xinhua.com.youlin.constant.CacheConsts;
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
    //IMPlatform.get().init(this);

    initTIM();

    initAppFolders();

    // 应用程序入口处调用，避免手机内存过小，杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
    // 如在Application中调用初始化，需要在Mainifest中注册该Applicaiton
    // 注意：此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，请增加参数：SpeechConstant.FORCE_LOGIN+"=true"
    // 参数间使用半角“,”分隔。
    // 设置你申请的应用appid,请勿在'='与appid之间添加空格及空转义符

    // 注意： appid 必须和下载的SDK保持一致，否则会出现10407错误
    //SpeechUtility utility = SpeechUtility.createUtility(MApp.this, "appid=5a09469d");
    //LogUtils.i("onCreate ,  SpeechUtility : " + utility);
  }

  private void initTIM() {
    Context context = getApplicationContext();

    //初始化IMSDK
    TIMSdkConfig config = new TIMSdkConfig(TIMConsts.SDK_APPID);
    config.enableLogPrint(true).setLogLevel(TIMLogLevel.DEBUG)
        // 设置是否开启bugly的crash上报功能， 必须在sdk初始化之前设置
        .enableCrashReport(false).setLogPath(CacheConsts.ExternalStorage.LOG_DIR);

    //初始化imsdk
    TIMManager.getInstance().init(context, config);
    //禁止服务器自动代替上报已读

    // 登录未超时
    //登录之前要初始化群和好友关系链缓存
    TIMUserConfig userConfig = new TIMUserConfig();
    userConfig.setUserStatusListener(new TIMUserStatusListenerImpl())
        .setConnectionListener(new TIMConnListenerImpl())
        .setRefreshListener(new TIMRefreshListenerImpl());

    userConfig = new TIMUserConfigSnsExt(userConfig)
        // 设置是否开启关系链本地储存
        .enableFriendshipStorage(true)
        .setFriendshipProxyListener(new TIMFriendshipProxyListenerImpl());

    userConfig = new TIMUserConfigGroupExt(userConfig).
        enableGroupStorage(true).setGroupAssistantListener(new TIMGroupAssistantListenerImpl());

    userConfig = new TIMUserConfigMsgExt(userConfig).enableStorage(true)
        .setMessageRevokedListener(new TIMMessageRevokedListenerImpl());

    TIMManager.getInstance().setUserConfig(userConfig);

    TIMManager.getInstance().addMessageListener(new TIMMessageListenerImpl());
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
