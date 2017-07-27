package youlin.xinhua.com.youlin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import youlin.xinhua.com.youlin.MApp;

/**
 * Created by xuemei on 2015/12/11.
 */

/**
 * SharedPreferences封装类
 */
public class SPUtils {
  /**
   * 保存在手机里面的文件名
   */
  public static final String FILE_NAME = "share_data";

  /**
   * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
   */
  public static void put(Context context, String key, Object object) {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
    SharedPreferences.Editor editor = sp.edit();
    if (object instanceof String) {
      editor.putString(key, (String) object);
    } else if (object instanceof Integer) {
      editor.putInt(key, (Integer) object);
    } else if (object instanceof Boolean) {
      editor.putBoolean(key, (Boolean) object);
    } else if (object instanceof Float) {
      editor.putFloat(key, (Float) object);
    } else if (object instanceof Long) {
      editor.putLong(key, (Long) object);
    } else {
      editor.putString(key, object.toString());
    }
    SharePreferencesCompat.apply(editor);
  }

  /**
   * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
   */
  public static void put(String key, Object object) {
    SharedPreferences sp = getSharedPreference(FILE_NAME);
    SharedPreferences.Editor editor = sp.edit();
    if (object instanceof String) {
      editor.putString(key, (String) object);
    } else if (object instanceof Integer) {
      editor.putInt(key, (Integer) object);
    } else if (object instanceof Boolean) {
      editor.putBoolean(key, (Boolean) object);
    } else if (object instanceof Float) {
      editor.putFloat(key, (Float) object);
    } else if (object instanceof Long) {
      editor.putLong(key, (Long) object);
    } else {
      editor.putString(key, object.toString());
    }
    SharePreferencesCompat.apply(editor);
  }

  /**
   * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相应的方法获取值
   */
  public static Object get(Context context, String key, Object defaultObject) {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
    if (defaultObject instanceof String) {
      return sp.getString(key, (String) defaultObject);
    } else if (defaultObject instanceof Integer) {
      return sp.getInt(key, (Integer) defaultObject);
    } else if (defaultObject instanceof Float) {
      return sp.getFloat(key, (Float) defaultObject);
    } else if (defaultObject instanceof Long) {
      return sp.getLong(key, (Long) defaultObject);
    } else if (defaultObject instanceof Boolean) {
      return sp.getBoolean(key, (Boolean) defaultObject);
    }
    return null;
  }

  /**
   * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相应的方法获取值
   */
  public static Object get(String key, Object defaultObject) {
    SharedPreferences sp = getSharedPreference(FILE_NAME);
    if (defaultObject instanceof String) {
      return sp.getString(key, (String) defaultObject);
    } else if (defaultObject instanceof Integer) {
      return sp.getInt(key, (Integer) defaultObject);
    } else if (defaultObject instanceof Float) {
      return sp.getFloat(key, (Float) defaultObject);
    } else if (defaultObject instanceof Long) {
      return sp.getLong(key, (Long) defaultObject);
    } else if (defaultObject instanceof Boolean) {
      return sp.getBoolean(key, (Boolean) defaultObject);
    }
    return null;
  }

  /**
   * 移除某个key值已经对应的值
   */
  public static void remove(Context context, String key) {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
    SharedPreferences.Editor editor = sp.edit();
    editor.remove(key);
    SharePreferencesCompat.apply(editor);
  }

  /**
   * 清除所有数据
   */
  public static void clear(Context context) {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
    SharedPreferences.Editor editor = sp.edit();
    editor.clear();
    SharePreferencesCompat.apply(editor);
  }

  /**
   * 查询某个key是否已经存在
   */
  public static boolean contains(Context context, String key) {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
    return sp.contains(key);
  }

  /**
   * 返回所有的键值对
   */
  public static Map<String, ?> getAll(Context context) {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
    return sp.getAll();
  }

  /**
   * 获取SharedPreferences实例对象
   */
  private static SharedPreferences getSharedPreference(String fileName) {
    return MApp.get().getSharedPreferences(fileName, Context.MODE_APPEND);
  }

  /**
   * 创建一个解决SharePreferencesCompat.apply
   */
  private static class SharePreferencesCompat {
    private static final Method sApplayMethod = findApplyMethod();

    /**
     * 反射查找apply的方法
     */
    @SuppressWarnings({ "unchecked", "rawtypes" }) private static Method findApplyMethod() {
      Class clz = SharedPreferences.Editor.class;
      try {
        return clz.getMethod("apply");
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      }
      return null;
    }

    /**
     * 如果找到则使用apply执行，否则使用commit
     */
    public static void apply(SharedPreferences.Editor editor) {
      if (sApplayMethod != null) {
        try {
          sApplayMethod.invoke(editor);
          return;
        } catch (IllegalAccessException e) {
          // e.printStackTrace();
        } catch (InvocationTargetException e) {
          // e.printStackTrace();
        } catch (IllegalArgumentException e) {
          // e.printStackTrace();
        }
        editor.commit();
      }
    }
  }
}
