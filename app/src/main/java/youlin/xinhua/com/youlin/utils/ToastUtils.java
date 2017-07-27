package youlin.xinhua.com.youlin.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import youlin.xinhua.com.youlin.MApp;

/**
 * Created by long on 2016/6/6.
 * 避免同样的信息多次触发重复弹出的问题
 */
public class ToastUtils {

    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    private ToastUtils() {
        throw new RuntimeException("ToastUtils cannot be initialized!");
    }

    public static void showToast(String s) {
        showAtLooper(s);
    }

    public static void showToast(int resId) {
        showToast(MApp.get().getString(resId));
    }

    /**
     * 调用线程没有Looper的给加上 Looper
     */
    private static void showAtLooper(final String text) {
        if (Looper.myLooper() != null) {
            show(text);
        } else {
            // run on main looper
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override public void run() {
                    show(text);
                }
            });
        }
    }
    public static void show(String s) {
        if (toast == null) {
            toast = Toast.makeText(MApp.get(), s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
            oneTime = twoTime;
        }
    }


}
