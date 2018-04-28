package sample.mari.com.filelib;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IntDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.Iterator;

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

    final static int NAME = 0x018;
    final static int SIZE = 0x019;
    final static int DATE = 0x020;
    final static int TYPE = 0x021;

    Context mContext;

    public FileCategoryHelper(Context context) {
        this.mContext = mContext;
    }

    @Retention(RetentionPolicy.SOURCE)
    @Target(PARAMETER)
    @IntDef({ALL, Music, Video, Picture, Doc, Zip, Apk, Other})
    public @interface FileCategory {

    }

    @Retention(RetentionPolicy.SOURCE)
    @Target(PARAMETER)
    @IntDef({NAME, SIZE, DATE, TYPE})
    public @interface SortMethod {

    }

    public Cursor query(@FileCategory int fc, @SortMethod int sort) {
        Uri uri = getContentUriByCategory(fc);
        String selection = buildSelectionByCategory(fc);
        String sortOrder = buildSortOrder(sort);

        if (uri == null) {
            return null;
        }

        String[] columns = new String[]{
                MediaStore.Files.FileColumns._ID, MediaStore.Files.FileColumns.DATA, MediaStore.Files.FileColumns.SIZE, MediaStore.Files.FileColumns.DATE_MODIFIED
        };

        return mContext.getContentResolver().query(uri, columns, selection, null, sortOrder);
    }

    private Uri getContentUriByCategory(@FileCategory int category) {
        Uri uri;
        String volumeName = "external";
        switch (category) {
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

    private String buildSelectionByCategory(@FileCategory int category) {
        String selection;
        switch (category) {
            case Doc:
                selection = buildDocSelection();
                break;
            case Zip:
                selection = "(" + MediaStore.Files.FileColumns.MIME_TYPE + " == '" + sZipFileMimeType + "')";
                break;
            case Apk:
                selection = MediaStore.Files.FileColumns.DATA + " LIKE '%.apk'";
                break;
            default:
                selection = null;
        }
        return selection;
    }

    private String buildSortOrder(@SortMethod int sort) {
        String sortOrder = null;
        switch (sort) {
            case NAME:
                sortOrder = MediaStore.Files.FileColumns.TITLE + " asc";
                break;
            case SIZE:
                sortOrder = MediaStore.Files.FileColumns.SIZE + " asc";
                break;
            case DATE:
                sortOrder = MediaStore.Files.FileColumns.DATE_MODIFIED + " desc";
                break;
            case TYPE:
                sortOrder = MediaStore.Files.FileColumns.MIME_TYPE + " asc, " + MediaStore.Files.FileColumns.TITLE + " asc";
                break;
        }
        return sortOrder;
    }

    private String buildDocSelection() {
        StringBuilder selection = new StringBuilder();
        Iterator<String> iter = sDocMimeTypesSet.iterator();
        while (iter.hasNext()) {
            selection.append("(" + MediaStore.Files.FileColumns.MIME_TYPE + "=='" + iter.next() + "') OR ");
        }
        return selection.substring(0, selection.lastIndexOf(")") + 1);
    }

    public static String sZipFileMimeType = "application/zip";

    public static HashSet<String> sDocMimeTypesSet = new HashSet<String>() {
        {
            add("text/plain");
            add("text/plain");
            add("application/pdf");
            add("application/msword");
            add("application/vnd.ms-excel");
            add("application/vnd.ms-excel");
        }
    };

}
