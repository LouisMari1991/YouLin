package sample.mari.com.filelib;

import android.net.Uri;

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

  //private Uri getContentUriByCategory(FileCategory cat) {
  //  Uri uri;
  //  String volumeName = "external";
  //  switch(cat) {
  //    case Theme:
  //    case Doc:
  //    case Zip:
  //    case Apk:
  //      uri = Files.getContentUri(volumeName);
  //      break;
  //    case Music:
  //      uri = Audio.Media.getContentUri(volumeName);
  //      break;
  //    case Video:
  //      uri = Video.Media.getContentUri(volumeName);
  //      break;
  //    case Picture:
  //      uri = Images.Media.getContentUri(volumeName);
  //      break;
  //    default:
  //      uri = null;
  //  }
  //  return uri;
  //}

}
