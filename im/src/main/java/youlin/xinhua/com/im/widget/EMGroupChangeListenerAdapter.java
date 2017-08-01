package youlin.xinhua.com.im.widget;

import com.hyphenate.EMGroupChangeListener;
import com.hyphenate.chat.EMMucSharedFile;
import java.util.List;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-17 19:52
 * version: 1.0
 * </pre>
 */

public class EMGroupChangeListenerAdapter implements EMGroupChangeListener {

  @Override public void onInvitationReceived(String s, String s1, String s2, String s3) {
    
  }

  @Override public void onRequestToJoinReceived(String s, String s1, String s2, String s3) {

  }

  @Override public void onRequestToJoinAccepted(String s, String s1, String s2) {

  }

  @Override public void onRequestToJoinDeclined(String s, String s1, String s2, String s3) {

  }

  @Override public void onInvitationAccepted(String s, String s1, String s2) {

  }

  @Override public void onInvitationDeclined(String s, String s1, String s2) {

  }

  @Override public void onUserRemoved(String s, String s1) {

  }

  @Override public void onGroupDestroyed(String s, String s1) {

  }

  @Override public void onAutoAcceptInvitationFromGroup(String s, String s1, String s2) {

  }

  @Override public void onMuteListAdded(String s, List<String> list, long l) {

  }

  @Override public void onMuteListRemoved(String s, List<String> list) {

  }

  @Override public void onAdminAdded(String s, String s1) {

  }

  @Override public void onAdminRemoved(String s, String s1) {

  }

  @Override public void onOwnerChanged(String s, String s1, String s2) {

  }

  @Override public void onMemberJoined(String s, String s1) {

  }

  @Override public void onMemberExited(String s, String s1) {

  }

  @Override public void onAnnouncementChanged(String s, String s1) {

  }

  @Override public void onSharedFileAdded(String s, EMMucSharedFile emMucSharedFile) {

  }

  @Override public void onSharedFileDeleted(String s, String s1) {

  }
}
