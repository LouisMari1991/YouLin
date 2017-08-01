package youlin.xinhua.com.youlin.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;
import youlin.xinhua.com.youlin.MApp;

public class EasePreferenceManager {
  private SharedPreferences.Editor editor;
  private SharedPreferences        mSharedPreferences;
  private static final String KEY_AT_GROUPS               = "AT_GROUPS";
  private static final String KEY_FRIENT_UNREAD_MSG_COUNT = "KEY_FRIENT_UNREAD_MSG_COUNT";
  private static final String KEY_GROUP_UNREAD_MSG_COUNT  = "KEY_GROUP_UNREAD_MSG_COUNT";

  @SuppressLint("CommitPrefEdits") private EasePreferenceManager() {
    mSharedPreferences =
        MApp.get().getSharedPreferences("EM_SP_AT_MESSAGE", Context.MODE_PRIVATE);
    editor = mSharedPreferences.edit();
  }

  private static EasePreferenceManager instance;

  public synchronized static EasePreferenceManager getInstance() {
    if (instance == null) {
      instance = new EasePreferenceManager();
    }
    return instance;
  }

  public void setAtMeGroups(Set<String> groups) {
    editor.remove(KEY_AT_GROUPS);
    editor.putStringSet(KEY_AT_GROUPS, groups);
    editor.apply();
  }

  public Set<String> getAtMeGroups() {
    return mSharedPreferences.getStringSet(KEY_AT_GROUPS, null);
  }

  /**
   * 新增未读联系人变化通知数量
   */
  public void addUnreadFriendMsgCount() {
    int count = mSharedPreferences.getInt(KEY_FRIENT_UNREAD_MSG_COUNT, 0);
    count++;
    editor.putInt(KEY_FRIENT_UNREAD_MSG_COUNT, count);
    editor.apply();
  }

  /**
   * 重置未读联系人变化通知数量
   */
  public void resetUnreadFriendMsgCount() {
    editor.remove(KEY_FRIENT_UNREAD_MSG_COUNT);
    editor.apply();
  }

  /**
   * 获取未读联系人变化通知数量
   */
  public int getUnreadFriendMsgCount() {
    return mSharedPreferences.getInt(KEY_FRIENT_UNREAD_MSG_COUNT, 0);
  }

  /**
   * 新增未读群组变化通知数量
   */
  public void addUnreadGroupMsgCount() {
    int count = mSharedPreferences.getInt(KEY_GROUP_UNREAD_MSG_COUNT, 0);
    count++;
    LogUtils.i("增加一条未读群消息 , count : " + count);
    editor.putInt(KEY_GROUP_UNREAD_MSG_COUNT, count);
    editor.apply();
  }

  /**
   * 重置未读群组变化通知数量
   */
  public void resetUnreadGroupMsgCount() {
    editor.remove(KEY_GROUP_UNREAD_MSG_COUNT);
    editor.apply();
  }

  /**
   * 获取未读群组变化通知数量
   */
  public int getUnreadGroupMsgCount() {
    return mSharedPreferences.getInt(KEY_GROUP_UNREAD_MSG_COUNT, 0);
  }
}
