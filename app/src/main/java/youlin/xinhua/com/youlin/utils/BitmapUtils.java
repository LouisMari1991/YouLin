package youlin.xinhua.com.youlin.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-08-25 16:17
 * version: 1.0
 * </pre>
 */

public class BitmapUtils {

  public static final boolean saveBitmap(String name, Bitmap bmp) {
    File file = new File(Environment.getExternalStorageDirectory() + "/" + name + ".png");
    try {
      file.createNewFile();
      FileOutputStream fos = new FileOutputStream(file);
      bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
      fos.flush();
      fos.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static Bitmap getBitmapFromView(View v) {
    Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.RGB_565);
    Canvas c = new Canvas(b);
    v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
    // Draw background
    Drawable bgDrawable = v.getBackground();
    if (bgDrawable != null)
      bgDrawable.draw(c);
    else
      c.drawColor(Color.WHITE);
    // Draw view to canvas
    v.draw(c);
    return b;
  }

}
