package youlin.xinhua.com.youlin.helper;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import java.io.IOException;
import java.util.List;
import youlin.xinhua.com.youlin.utils.LogUtils;

/**
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-09-05
 * Time: 10:56
 */
public class CameraManager {
  private static final String TAG = CameraManager.class.getName();
  private Camera            camera;
  private Camera.Parameters parameters;
  private AutoFocusManager  autoFocusManager;
  private int requestedCameraId = -1;

  private boolean initialized;
  private boolean previewing;

  /**
   * 打开摄像头
   *
   * @param cameraId 摄像头id
   * @return Camera
   */
  public Camera open(int cameraId) {
    int numCameras = Camera.getNumberOfCameras();
    if (numCameras == 0) {
      Log.e(TAG, "No cameras!");
      return null;
    }
    boolean explicitRequest = cameraId >= 0;
    if (!explicitRequest) {
      // Select a camera if no explicit camera requested
      int index = 0;
      while (index < numCameras) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(index, cameraInfo);
        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
          break;
        }
        index++;
      }
      cameraId = index;
    }
    Camera camera;
    if (cameraId < numCameras) {
      Log.e(TAG, "Opening camera #" + cameraId);
      camera = Camera.open(cameraId);
    } else {
      if (explicitRequest) {
        Log.e(TAG, "Requested camera does not exist: " + cameraId);
        camera = null;
      } else {
        Log.e(TAG, "No camera facing back; returning camera #0");
        camera = Camera.open(0);
      }
    }
    return camera;
  }

  /**
   * 打开camera
   *
   * @param holder SurfaceHolder
   * @throws IOException IOException
   */
  public synchronized void openDriver(SurfaceHolder holder) throws IOException {
    Log.e(TAG, "openDriver");
    Camera theCamera = camera;
    if (theCamera == null) {
      theCamera = open(requestedCameraId);
      if (theCamera == null) {
        throw new IOException();
      }
      camera = theCamera;
    }
    theCamera.setPreviewDisplay(holder);

    if (!initialized) {
      initialized = true;
      parameters = camera.getParameters();
      List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();

      int w = 480;
      int h = 320;
      for (Camera.Size size : previewSizes) {
        Log.e("TAG", "previewSizes width:" + size.width);
        Log.e("TAG", "previewSizes height:" + size.height);
        if (size.width - w <= 100) {
          w = size.width;
          h = size.height;
          break;
        }
      }
      // 如果sizeList只有一个我们也没有必要做什么了，因为就他一个别无选择
      //if (previewSizes.size() > 1) {
      //    Iterator<Camera.Size> itor = previewSizes.iterator();
      //    while (itor.hasNext()) {
      //        Camera.Size cur = itor.next();
      //        if (cur.width >= w
      //            && cur.height >= h) {
      //            w = cur.width;
      //            h = cur.height;
      //            break;
      //        }
      //    }
      //}

      parameters.setPreviewSize(w, h); //获得摄像区域的大小
      //parameters.setPreviewFrameRate(3);//每秒3帧  每秒从摄像头里面获得3个画面
      parameters.setPictureFormat(ImageFormat.JPEG);//设置照片输出的格式
      parameters.set("jpeg-quality", 85);//设置照片质量
      //parameters.setPictureSize(430, 320);//设置拍出来的屏幕大小
      //
      theCamera.setParameters(parameters);//把上面的设置 赋给摄像头
      //theCamera.setPreviewDisplay(mySurfaceView.getHolder());//把摄像头获得画面显示在SurfaceView控件里面
      theCamera.startPreview();//开始预览

      //parameters.setPreviewSize(w, h);
      //parameters.setPictureFormat(ImageFormat.JPEG);
      //parameters.setJpegQuality(100);
      ////parameters.setPictureSize(430, 320);
      //theCamera.setParameters(parameters);
    }
  }

  /**
   * camera是否打开
   *
   * @return camera是否打开
   */
  public synchronized boolean isOpen() {
    return camera != null;
  }

  /**
   * 关闭camera
   */
  public synchronized void closeDriver() {
    Log.e(TAG, "closeDriver");
    if (camera != null) {
      camera.release();
      camera = null;
    }
  }

  /**
   * 开始预览
   */
  public synchronized void startPreview() {
    Log.e(TAG, "startPreview");
    Camera theCamera = camera;
    if (theCamera != null && !previewing) {
      theCamera.startPreview();
      previewing = true;
      autoFocusManager = new AutoFocusManager(camera);
    }
  }

  /**
   * 关闭预览
   */
  public synchronized void stopPreview() {
    Log.e(TAG, "stopPreview");
    if (autoFocusManager != null) {
      autoFocusManager.stop();
      autoFocusManager = null;
    }
    if (camera != null && previewing) {
      camera.stopPreview();
      previewing = false;
    }
  }

  /**
   * 打开闪光灯
   */
  public synchronized void openLight() {
    LogUtils.e(TAG, "openLight");
    if (camera != null) {
      parameters = camera.getParameters();
      parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
      camera.setParameters(parameters);
    }
  }

  /**
   * 关闭闪光灯
   */
  public synchronized void offLight() {
    LogUtils.e(TAG, "offLight");
    if (camera != null) {
      parameters = camera.getParameters();
      parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
      camera.setParameters(parameters);
    }
  }

  /**
   * 拍照
   *
   * @param shutter ShutterCallback
   * @param raw PictureCallback
   * @param jpeg PictureCallback
   */
  public synchronized void takePicture(final Camera.ShutterCallback shutter,
      final Camera.PictureCallback raw, final Camera.PictureCallback jpeg) {
    camera.takePicture(shutter, raw, jpeg);
  }
}
