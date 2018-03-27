package youlin.xinhua.com.youlin.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FileUtil {

  private static final String[][] MIME_MapTable = {
      // {后缀名， MIME类型}
      { ".doc", "application/msword" },
      { ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
      { ".xls", "application/vnd.ms-excel" },
      { ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
      { ".pps", "application/vnd.ms-powerpoint" }, { ".ppt", "application/vnd.ms-powerpoint" },
      { ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation" },
      // {".wps", "application/vnd.ms-works"},
      { ".wps", "application/kswps" }, { ".et", "application/kset" },
      { ".dps", "application/ksdps" }, { "", "*/*" }
  };

  public static void createFolder(String folderName) {
    File folder = new File(folderName);
    if (!folder.exists()) {
      if (!folder.isDirectory()) {
        folder.mkdirs();
      }
    }
  }

  public static void deleteDir(String folderName) {
    File dir = new File(folderName);
    if (dir.exists()) {
      clearDir(dir);
      dir.delete();
    }
  }

  public static void clearDir(File dir) {
    if (dir.exists()) {
      File[] files = dir.listFiles();
      for (File file : files) {
        if (file.isDirectory()) {
          clearDir(file);
        }
        file.delete();
      }
    }
  }

  /**
   * 复制单个文件
   *
   * @param oldPath String 原文件路径 如：c:/fqf.txt
   * @param newPath String 复制后路径 如：f:/fqf.txt
   * @return boolean
   */
  public static void copyFile(String oldPath, String newPath) {
    try {
      int byteread = 0;
      File oldfile = new File(oldPath);
      if (oldfile.exists()) { // 文件存在时
        InputStream inStream = new FileInputStream(oldPath); // 读入原文件
        FileOutputStream fs = new FileOutputStream(newPath);
        byte[] buffer = new byte[1024 * 10];
        while ((byteread = inStream.read(buffer)) != -1) {
          fs.write(buffer, 0, byteread);
        }
        fs.flush();
        fs.close();
        inStream.close();
      }
    } catch (Exception e) {
      System.out.println("复制单个文件操作出错");
      e.printStackTrace();
    }
  }

  /**
   * 复制整个文件夹内容
   *
   * @param oldPath String 原文件路径 如：c:/fqf
   * @param newPath String 复制后路径 如：f:/fqf/ff
   * @return boolean
   */
  public static void copyFolder(String oldPath, String newPath) {

    try {
      (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
      File a = new File(oldPath);
      String[] file = a.list();
      File temp = null;
      for (int i = 0; i < file.length; i++) {
        if (oldPath.endsWith(File.separator)) {
          temp = new File(oldPath + file[i]);
        } else {
          temp = new File(oldPath + File.separator + file[i]);
        }

        if (temp.isFile()) {
          FileInputStream input = new FileInputStream(temp);
          FileOutputStream output =
              new FileOutputStream(String.valueOf(newPath + "/" + (temp.getName())));
          byte[] b = new byte[1024 * 5];
          int len;
          while ((len = input.read(b)) != -1) {
            output.write(b, 0, len);
          }
          output.flush();
          output.close();
          input.close();
        }
        if (temp.isDirectory()) {// 如果是子文件夹
          copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
        }
      }
    } catch (Exception e) {
      System.out.println("复制整个文件夹内容操作出错");
      e.printStackTrace();
    }
  }

  public static boolean isFile(String path) {
    if (path == null) {
      return false;
    }
    File file = new File(path);
    if (file.exists() && file.isFile()) {
      return true;
    }
    return false;
  }

  // Filtering special characters
  public static boolean stringFilter(String str) throws PatternSyntaxException {
    // All special characters
    String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(str);
    if (str.length() > m.replaceAll("").trim().length()) {
      return false;
    }
    return true;
  }

  /**
   * 获取服务器文件的名称
   */
  public static String getFileName(String serverPath) {
    return serverPath.substring(serverPath.lastIndexOf("/") + 1);
  }

  /**
   * @Description: TODO(字符串转化成输入流)
   * @param: @param sInputString
   * @param: @return
   */
  public static InputStream getStringStream(String sInputString) {

    if (sInputString != null && !sInputString.trim().equals("")) {

      try {

        ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());

        return tInputStringStream;
      } catch (Exception ex) {

        ex.printStackTrace();
      }
    }
    return null;
  }

  private static List<String> getSDCardPaths() {
    List<String> uPaths = new ArrayList<String>();
    StringBuffer sb = new StringBuffer();
    String cmd = "cat /proc/mounts";
    Runtime run = Runtime.getRuntime();
    try {
      Process p = run.exec(cmd);
      BufferedInputStream in = new BufferedInputStream(p.getInputStream());
      BufferedReader inBr = new BufferedReader(new InputStreamReader(in));

      String lineStr;
      while ((lineStr = inBr.readLine()) != null) {
        String[] temp = TextUtils.split(lineStr, " ");
        String result = temp[1];
        File file = new File(result);

        if (!temp[0].equalsIgnoreCase("tmpfs")
            && !result.contains("\\\\")
            && !result.equals("/")
            && !result.contains("/smb")
            && !result.contains("legacy")
            && !result.contains("skydir")
            && !result.contains("sys")
            && !result.contains("system")
            && !result.contains("dev")
            && !result.contains("shell")
            && !result.contains("data")
            && !result.contains("cache")
            && !result.contains("obb")
            && !result.contains("proc")
            && !result.contains("acct")
            && !result.contains("-")
            && !result.contains("tv")
            && !result.contains("tmp")
            && !result.contains("_")
            && file.isDirectory()
            && file.canRead()) {
          uPaths.add(result);
          sb.append("这是一个外部存储：   ");
        }
        if (p.waitFor() != 0 && p.exitValue() == 1) {
          Log.e("getSDCardPath", "命令执行失败!");
        }

        sb.append(result + "     read: " + file.canRead() + "   write:" + file.canWrite() + "\r\n");
      }
      String info = sb.toString();
      inBr.close();
      in.close();
    } catch (Exception e) {
      Log.e("getSDCardPath1", "getsdcardpath error.........", e);
      uPaths.add(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    optimize(uPaths);
    for (Iterator iterator = uPaths.iterator(); iterator.hasNext(); ) {
      String string = (String) iterator.next();
      //			LogUtil.e("CommonUtil:getSDCardPath", "清除过后  "+string);
    }
    return uPaths;
  }

  /**
   * 获取所有的U盘、内置sd卡  、外置sd卡 路径
   */
  public static List<String> getSdCardAndUPanPaths() {

    //		List<String> paths = new ArrayList<String>();

    String root = Environment.getRootDirectory().getAbsoluteFile()
        + File.separator
        + "etc"
        + File.separator
        + "vold.fstab";
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(root)));
      String line;
      List<String> tmpPaths = new ArrayList<String>();
      while ((line = br.readLine()) != null) {
        String[] strs = TextUtils.split(line, " ");
        if (line.startsWith("#") || line.length() == 0 || strs.length < 2) {
          continue;
        }
        String str = strs[2];
        if (str != null) { //这个就是路径
          tmpPaths.add(str); //sd卡 外置sd卡  U盘的路径
        }
      }
      //筛选哪些已经挂载过
      return mount(tmpPaths);
    } catch (Exception e) {
      e.printStackTrace();
      return getSDCardPaths();
    }
  }

  /**
   * 获取已经挂载的路径
   *
   * @param tmpPaths 从这个集合里筛选
   */
  private static List<String> mount(List<String> tmpPaths) {
    List<String> paths = new ArrayList<String>();
    String cmd = "cat /proc/mounts";
    Runtime run = Runtime.getRuntime();
    try {
      Process p = run.exec(cmd);
      BufferedInputStream in = new BufferedInputStream(p.getInputStream());
      BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
      String lineStr;
      while ((lineStr = inBr.readLine()) != null) {
        String[] temp = TextUtils.split(lineStr, " ");
        String result = temp[1];
        if (tmpPaths.contains(result)) {
          paths.add(result);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return paths;
  }

  /**
   * 根据文件后缀名获得对应的MIME类型。
   */
  public static String getMIMEType(File file) {

    String type = "*/*";
    String fName = file.getName();
    // 获取后缀名前的分隔符"."在fName中的位置。
    int dotIndex = fName.lastIndexOf(".");
    if (dotIndex < 0) {
      return type;
    }
    /* 获取文件的后缀名 */
    String end = fName.substring(dotIndex, fName.length()).toLowerCase();
    if (end == "") return type;
    // 在MIME和文件类型的匹配表中找到对应的MIME类型。
    for (int i = 0; i < MIME_MapTable.length; i++) { // MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
      if (end.equals(MIME_MapTable[i][0])) type = MIME_MapTable[i][1];
    }
    return type;
  }

  private static void optimize(List<String> sdcaredPaths) {
    int index = 0;
    if (sdcaredPaths.size() <= 0) {
      return;
    }
    while (true) {
      if (index >= sdcaredPaths.size() - 1) {
        String lastItem = sdcaredPaths.get(sdcaredPaths.size() - 1);
        for (int i = sdcaredPaths.size() - 2; i >= 0; i--) {
          if (sdcaredPaths.get(i).contains(lastItem)) {
            sdcaredPaths.remove(i);
          }
        }
        return;
      }
      String containsItem = sdcaredPaths.get(index);
      for (int i = index + 1; i < sdcaredPaths.size(); i++) {
        if (sdcaredPaths.get(i).contains(containsItem)) {
          sdcaredPaths.remove(i);
          i--;
        }
      }
      index++;
    }
  }

  /**
   * the traditional io way
   *
   * @throws IOException
   */
  public static byte[] toByteArray(String filename) throws IOException {

    File f = new File(filename);
    if (!f.exists()) {
      throw new FileNotFoundException(filename);
    }

    ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
    BufferedInputStream in = null;
    try {
      in = new BufferedInputStream(new FileInputStream(f));
      int buf_size = 1024;
      byte[] buffer = new byte[buf_size];
      int len = 0;
      while (-1 != (len = in.read(buffer, 0, buf_size))) {
        bos.write(buffer, 0, len);
      }
      return bos.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    } finally {
      try {
        if (in != null) {
          in.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      bos.close();
    }
  }

  /**
   * 把字节数组写到文件中
   */
  public static void byteArraySaveToFile(String path, byte[] datas) {
    Log.e("www", "byteArraySaveToFile(String path, byte[] datas)");
    ByteArrayInputStream input = new ByteArrayInputStream(datas);
    FileOutputStream out = null;
    try {
      out = new FileOutputStream(path);

      int lent = -1;
      byte[] buff = new byte[1024];

      while ((lent = input.read(buff)) != -1) {
        out.write(buff);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {

      try {
        input.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static String getExtensionName(String filename) {
    if ((filename != null) && (filename.length() > 0)) {
      int dot = filename.lastIndexOf('.');
      if ((dot > -1) && (dot < (filename.length() - 1))) {
        return filename.substring(dot + 1);
      }
    }
    return filename;
  }

  public static boolean createFile(File file) throws IOException {
    if (!file.exists()) {
      makeDir(file.getParentFile());
    }
    return file.createNewFile();
  }

  public static void makeDir(File dir) {
    if (!dir.getParentFile().exists()) {
      makeDir(dir.getParentFile());
    }
    dir.mkdirs();
  }

  /**
   * 获取文件夹下所有文件
   */
  public static List<File> getFileAll(String path) {
    File file = new File(path);
    File[] files = file.listFiles();
    List<File> fileList = new ArrayList<>();
    for (File file1 : files) {
      fileList.add(file1);
    }
    return fileList;
  }

  /**
   * 文件是否存在
   *
   * @param filePath 文件路径
   * @return File exists return <code>true</code> , else <code>false</code>
   */
  public static boolean fileExists(String filePath) {
    if (TextUtils.isEmpty(filePath)) {
      return false;
    }
    File file = new File(filePath);
    if (file.exists() && file.length() > 0) {
      return true;
    }
    return false;
  }
}
