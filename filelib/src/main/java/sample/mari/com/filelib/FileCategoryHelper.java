package sample.mari.com.filelib;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/04/28
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class FileCategoryHelper {

  public static final int COLUMN_ID = 0;

  public static final int COLUMN_PATH = 1;

  public static final int COLUMN_SIZE = 2;

  public static final int COLUMN_DATE = 3;

  final public static int ALL = 0x010;
  final public static int Music = 0x011;
  final public static int Video = 0x012;
  final public static int Picture = 0x013;
  final public static int Doc = 0x014;
  final public static int Zip = 0x015;
  final public static int Apk = 0x016;
  final public static int Other = 0x017;

  @Retention(RetentionPolicy.SOURCE) @Target(PARAMETER)
  @IntDef({ ALL, Music, Video, Picture, Doc, Zip, Apk, Other }) public @interface FileCategory {

  }

  private Uri getContentUriByCategory(@FileCategory int fileCategory) {
    Uri uri;
    String volumeName = "external";
    switch (fileCategory) {
      case Doc:
      case Zip:
      case Apk:
        uri = MediaStore.Files.getContentUri(volumeName);
        break;
      case Music:
        uri = MediaStore.Audio.Media.getContentUri(volumeName);
        break;
      case Video:
        uri = MediaStore.Video.Media.getContentUri(volumeName);
        break;
      case Picture:
        uri = MediaStore.Images.Media.getContentUri(volumeName);
        break;
      default:
        uri = null;
    }
    return uri;
  }
}
