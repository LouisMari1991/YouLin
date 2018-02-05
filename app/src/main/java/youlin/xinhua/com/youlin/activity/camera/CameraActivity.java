//package youlin.xinhua.com.youlin.activity.camera;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.hardware.Camera;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.DisplayMetrics;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.lang.reflect.Field;
//import youlin.xinhua.com.youlin.R;
//import youlin.xinhua.com.youlin.helper.CameraManager;
//import youlin.xinhua.com.youlin.utils.LogUtils;
//import youlin.xinhua.com.youlin.widget.PreviewBorderView;
//import youlin.xinhua.com.youlin.widget.dialog.TipDialog;
//
//public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {
//  private LinearLayout      mLinearLayout;
//  private PreviewBorderView mPreviewBorderView;
//  private SurfaceView       mSurfaceView;
//
//  private CameraManager cameraManager;
//  private boolean       hasSurface;
//  private Intent        mIntent;
//  private static final String DEFAULT_PATH = "/sdcard/";
//  private static final String DEFAULT_NAME = "default.jpg";
//  private static final String DEFAULT_TYPE = "default";
//  private String filePath;
//  private String fileName;
//  private String type;
//  private Button take, light;
//  private boolean toggleLight;
//  private boolean canUse;
//
//  TipDialog mTipDialog;
//
//  @Override protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_camera);
//    initIntent();
//    initLayoutParams();
//  }
//
//  private void initIntent() {
//    mIntent = getIntent();
//    filePath = mIntent.getStringExtra("path");
//    fileName = mIntent.getStringExtra("name");
//    type = mIntent.getStringExtra("type");
//    if (filePath == null) {
//      filePath = DEFAULT_PATH;
//    }
//    if (fileName == null) {
//      fileName = DEFAULT_NAME;
//    }
//    if (type == null) {
//      type = DEFAULT_TYPE;
//    }
//    LogUtils.e("TAG", filePath + "/" + fileName + "_" + type);
//  }
//
//  /**
//   * 重置surface宽高比例为3:4，不重置的话图形会拉伸变形
//   */
//  private void initLayoutParams() {
//    take = (Button) findViewById(R.id.take);
//    light = (Button) findViewById(R.id.light);
//    take.setOnClickListener(new View.OnClickListener() {
//      @Override public void onClick(View v) {
//        if (!canUse) {
//          showDialog();
//          return;
//        }
//        cameraManager.takePicture(null, null, myjpegCallback);
//      }
//    });
//    light.setOnClickListener(new View.OnClickListener() {
//      @Override public void onClick(View v) {
//
//        if (!canUse) {
//          showDialog();
//          return;
//        }
//
//        if (!toggleLight) {
//          toggleLight = true;
//          cameraManager.openLight();
//        } else {
//          toggleLight = false;
//          cameraManager.offLight();
//        }
//      }
//    });
//
//    //重置宽高，3:4
//    int widthPixels = getScreenWidth(this);
//    int heightPixels = getScreenHeight(this);
//    mLinearLayout = (LinearLayout) findViewById(R.id.linearlaout);
//    mPreviewBorderView = (PreviewBorderView) findViewById(R.id.borderview);
//    mSurfaceView = (SurfaceView) findViewById(R.id.surfaceview);
//
//    RelativeLayout.LayoutParams surfaceviewParams =
//        (RelativeLayout.LayoutParams) mSurfaceView.getLayoutParams();
//    surfaceviewParams.width = heightPixels * 4 / 3;
//    surfaceviewParams.height = heightPixels;
//    mSurfaceView.setLayoutParams(surfaceviewParams);
//
//    RelativeLayout.LayoutParams borderViewParams =
//        (RelativeLayout.LayoutParams) mPreviewBorderView.getLayoutParams();
//    borderViewParams.width = heightPixels * 4 / 3;
//    borderViewParams.height = heightPixels;
//    mPreviewBorderView.setLayoutParams(borderViewParams);
//
//    RelativeLayout.LayoutParams linearLayoutParams =
//        (RelativeLayout.LayoutParams) mLinearLayout.getLayoutParams();
//    linearLayoutParams.width = widthPixels - heightPixels * 4 / 3;
//    linearLayoutParams.height = heightPixels;
//    mLinearLayout.setLayoutParams(linearLayoutParams);
//
//    LogUtils.e("TAG", "Screen width:" + heightPixels * 4 / 3);
//    LogUtils.e("TAG", "Screen height:" + heightPixels);
//  }
//
//  @Override protected void onResume() {
//    super.onResume();
//    /**
//     * 初始化camera
//     */
//    SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
//    SurfaceHolder surfaceHolder = surfaceView.getHolder();
//    cameraManager = new CameraManager();
//
//    if (hasSurface) {
//      // activity在paused时但不会stopped,因此surface仍旧存在；
//      // surfaceCreated()不会调用，因此在这里初始化camera
//      initCamera(surfaceHolder);
//    } else {
//      // 重置callback，等待surfaceCreated()来初始化camera
//      surfaceHolder.addCallback(this);
//    }
//  }
//
//  @Override public void surfaceCreated(SurfaceHolder holder) {
//    if (!hasSurface) {
//      hasSurface = true;
//      initCamera(holder);
//    }
//  }
//
//  @Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//  }
//
//  @Override public void surfaceDestroyed(SurfaceHolder holder) {
//    hasSurface = false;
//  }
//
//  /**
//   * 初始camera
//   *
//   * @param surfaceHolder SurfaceHolder
//   */
//  private void initCamera(SurfaceHolder surfaceHolder) {
//    canUse = true;
//    if (surfaceHolder == null) {
//      throw new IllegalStateException("No SurfaceHolder provided");
//    }
//    if (cameraManager.isOpen()) {
//      return;
//    }
//    try {
//      // 打开Camera硬件设备
//      cameraManager.openDriver(surfaceHolder);
//      // 创建一个handler来打开预览，并抛出一个运行时异常
//      cameraManager.startPreview();
//    } catch (Exception ioe) {
//      LogUtils.e(ioe.toString());
//      canUse = false;
//      take.postDelayed(new Runnable() {
//        @Override public void run() {
//          showDialog();
//        }
//      }, 1000);
//      ioe.printStackTrace();
//    }
//  }
//
//  @Override protected void onPause() {
//    /**
//     * 停止camera，是否资源操作
//     */
//    cameraManager.stopPreview();
//    cameraManager.closeDriver();
//    if (!hasSurface) {
//      SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
//      SurfaceHolder surfaceHolder = surfaceView.getHolder();
//      surfaceHolder.removeCallback(this);
//    }
//    super.onPause();
//  }
//
//  /**
//   * 拍照回调
//   */
//  Camera.PictureCallback myjpegCallback = new Camera.PictureCallback() {
//    @Override public void onPictureTaken(final byte[] data, Camera camera) {
//      // 根据拍照所得的数据创建位图
//      final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//      int height = bitmap.getHeight();
//      int width = bitmap.getWidth();
//      final Bitmap bitmap1 =
//          Bitmap.createBitmap(bitmap, (width - height) / 2, height / 6, height, height * 2 / 3);
//      LogUtils.e("TAG", "width:" + width + " height:" + height);
//      LogUtils.e("TAG", "x:"
//          + (width - height) / 2
//          + " y:"
//          + height / 6
//          + " width:"
//          + height
//          + " height:"
//          + height * 2 / 3);
//      // 创建一个位于SD卡上的文件
//
//      File path = new File(filePath);
//      if (!path.exists()) {
//        path.mkdirs();
//      }
//      File file = new File(path, type + "_" + fileName);
//
//      FileOutputStream outStream = null;
//      try {
//        // 打开指定文件对应的输出流
//        outStream = new FileOutputStream(file);
//        // 把位图输出到指定文件中
//        bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
//        outStream.close();
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//
//      Intent intent = new Intent();
//      Bundle bundle = new Bundle();
//      bundle.putString("path", file.getAbsolutePath());
//      bundle.putString("type", type);
//      intent.putExtras(bundle);
//      setResult(RESULT_OK, intent);
//
//      CameraActivity.this.finish();
//    }
//  };
//
//  /**
//   * 获得屏幕宽度，单位px
//   *
//   * @param context 上下文
//   * @return 屏幕宽度
//   */
//  public int getScreenWidth(Context context) {
//    DisplayMetrics dm = context.getResources().getDisplayMetrics();
//    return dm.widthPixels;
//  }
//
//  /**
//   * 获得屏幕高度
//   *
//   * @param context 上下文
//   * @return 屏幕除去通知栏的高度
//   */
//  public int getScreenHeight(Context context) {
//    DisplayMetrics dm = context.getResources().getDisplayMetrics();
//    return dm.heightPixels - getStatusBarHeight(context);
//  }
//
//  /**
//   * 获取通知栏高度
//   *
//   * @param context 上下文
//   * @return 通知栏高度
//   */
//  public int getStatusBarHeight(Context context) {
//    int statusBarHeight = 0;
//    try {
//      Class<?> clazz = Class.forName("com.android.internal.R$dimen");
//      Object obj = clazz.newInstance();
//      Field field = clazz.getField("status_bar_height");
//      int temp = Integer.parseInt(field.get(obj).toString());
//      statusBarHeight = context.getResources().getDimensionPixelSize(temp);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return statusBarHeight;
//  }
//
//  /**
//   * 跳转到权限设置界面
//   */
//  private void getAppDetailSettingIntent() {
//
//    Intent intent = new Intent();
//    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    if (Build.VERSION.SDK_INT >= 9) {
//      intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
//      intent.setData(Uri.fromParts("package", getPackageName(), null));
//    } else if (Build.VERSION.SDK_INT <= 8) {
//      intent.setAction(Intent.ACTION_VIEW);
//      intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
//      intent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
//    }
//    startActivity(intent);
//  }
//
//  private void showDialog() {
//    if (mTipDialog == null) {
//      mTipDialog = new TipDialog(this, TipDialog.NORMAL_TYPE);
//    }
//    mTipDialog.setTitleText("权限提醒")
//        .setContentText("请前往权限设置中允许使用手机摄像头")
//        .setConfirmText("确定")
//        .setCancelText("取消")
//        .setConfirmClickListener(new TipDialog.OnSweetClickListener() {
//          @Override public void onClick(TipDialog tipDialog) {
//            getAppDetailSettingIntent();
//            tipDialog.dismiss();
//          }
//        })
//        .setCancelClickListener(new TipDialog.OnSweetClickListener() {
//          @Override public void onClick(TipDialog tipDialog) {
//            tipDialog.dismiss();
//          }
//        });
//    mTipDialog.show();
//  }
//}
