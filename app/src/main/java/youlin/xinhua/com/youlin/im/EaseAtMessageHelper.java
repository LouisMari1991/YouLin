//package youlin.xinhua.com.youlin.im;
//
//import android.text.TextUtils;
//import com.hyphenate.chat.EMClient;
//import com.hyphenate.chat.EMMessage;
//import com.hyphenate.chat.EMMessage.ChatType;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import org.json.JSONArray;
//import youlin.xinhua.com.youlin.MApp;
//import youlin.xinhua.com.youlin.R;
//import youlin.xinhua.com.youlin.constant.EaseConstant;
//import youlin.xinhua.com.youlin.utils.EasePreferenceManager;
//
///**
// * 群聊中 @ 功能辅助类
// */
//public class EaseAtMessageHelper {
//
//  private        List<String>        toAtUserList  = new ArrayList<String>();
//  private        Set<String>         atMeGroupList = null;
//  private static EaseAtMessageHelper instance      = null;
//
//  public synchronized static EaseAtMessageHelper get() {
//    if (instance == null) {
//      instance = new EaseAtMessageHelper();
//    }
//    return instance;
//  }
//
//  private EaseAtMessageHelper() {
//    atMeGroupList = EasePreferenceManager.getInstance().getAtMeGroups();
//    if (atMeGroupList == null) atMeGroupList = new HashSet<>();
//  }
//
//  /**
//   * add user you want to @
//   */
//  public void addAtUser(String username) {
//    synchronized (toAtUserList) {
//      if (!toAtUserList.contains(username)) {
//        toAtUserList.add(username);
//      }
//    }
//  }
//
//  /**
//   * check if be mentioned(@) in the content
//   */
//  public boolean containsAtUsername(String content) {
//    if (TextUtils.isEmpty(content)) {
//      return false;
//    }
//    synchronized (toAtUserList) {
//      for (String username : toAtUserList) {
//        String nick = username;
//        //if (EaseUserUtils.getUserInfo(username) != null) {
//        //  EaseUser user = EaseUserUtils.getUserInfo(username);
//        //  if (user != null) {
//        //    nick = user.getNick();
//        //  }
//        //}
//        if (content.contains(nick)) {
//          return true;
//        }
//      }
//    }
//    return false;
//  }
//
//  public boolean containsAtAll(String content) {
//    String atAll = "@" + MApp.get().getString(R.string.all_members);
//    if (content.contains(atAll)) {
//      return true;
//    }
//    return false;
//  }
//
//  /**
//   * get the users be mentioned(@)
//   */
//  public List<String> getAtMessageUsernames(String content) {
//    if (TextUtils.isEmpty(content)) {
//      return null;
//    }
//    synchronized (toAtUserList) {
//      List<String> list = null;
//      for (String username : toAtUserList) {
//        String nick = username;
//        //if (EaseUserUtils.getUserInfo(username) != null) {
//        //  EaseUser user = EaseUserUtils.getUserInfo(username);
//        //  if (user != null) {
//        //    nick = user.getNick();
//        //  }
//        //}
//        if (content.contains(nick)) {
//          if (list == null) {
//            list = new ArrayList<>();
//          }
//          list.add(username);
//        }
//      }
//      return list;
//    }
//  }
//
//  /**
//   * parse the message, get and save group id if I was mentioned(@)
//   */
//  public void parseMessages(List<EMMessage> messages) {
//    int size = atMeGroupList.size();
//    EMMessage[] msgs = messages.toArray(new EMMessage[messages.size()]);
//    for (EMMessage msg : msgs) {
//      if (msg.getChatType() == ChatType.GroupChat) {
//        String groupId = msg.getTo();
//        try {
//          JSONArray jsonArray = msg.getJSONArrayAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG);
//          for (int i = 0; i < jsonArray.length(); i++) {
//            String username = jsonArray.getString(i);
//            if (EMClient.getInstance().getCurrentUser().equals(username)) {
//              if (!atMeGroupList.contains(groupId)) {
//                atMeGroupList.add(groupId);
//                break;
//              }
//            }
//          }
//        } catch (Exception e1) {
//          //Determine whether is @ all message
//          String usernameStr = msg.getStringAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG, null);
//          if (usernameStr != null) {
//            if (usernameStr.toUpperCase().equals(EaseConstant.MESSAGE_ATTR_VALUE_AT_MSG_ALL)) {
//              if (!atMeGroupList.contains(groupId)) {
//                atMeGroupList.add(groupId);
//              }
//            }
//          }
//        }
//
//        if (atMeGroupList.size() != size) {
//          EasePreferenceManager.getInstance().setAtMeGroups(atMeGroupList);
//        }
//      }
//    }
//  }
//
//  /**
//   * get groups which I was mentioned
//   * @return
//   */
//  public Set<String> getAtMeGroups(){
//      return atMeGroupList;
//  }
//
//  /**
//   * remove group from the list
//   */
//  public void removeAtMeGroup(String groupId) {
//    if (atMeGroupList.contains(groupId)) {
//      atMeGroupList.remove(groupId);
//      EasePreferenceManager.getInstance().setAtMeGroups(atMeGroupList);
//    }
//  }
//
//  /**
//   * check if the input groupId in atMeGroupList
//   */
//  public boolean hasAtMeMsg(String groupId) {
//    return atMeGroupList.contains(groupId);
//  }
//
//  public boolean isAtMeMsg(EMMessage message) {
//      try {
//        JSONArray jsonArray = message.getJSONArrayAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG);
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//          String username = jsonArray.getString(i);
//          if (username.equals(EMClient.getInstance().getCurrentUser())) {
//            return true;
//          }
//        }
//      } catch (Exception e) {
//        //perhaps is a @ all message
//        String atUsername = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG, null);
//        if (atUsername != null) {
//          if (atUsername.toUpperCase().equals(EaseConstant.MESSAGE_ATTR_VALUE_AT_MSG_ALL)) {
//            return true;
//          }
//        }
//        return false;
//      }
//    return false;
//  }
//
//  public JSONArray atListToJsonArray(List<String> atList) {
//    JSONArray jArray = new JSONArray();
//    int size = atList.size();
//    for (int i = 0; i < size; i++) {
//      String username = atList.get(i);
//      jArray.put(username);
//    }
//    return jArray;
//  }
//
//  public void cleanToAtUserList() {
//    synchronized (toAtUserList) {
//      toAtUserList.clear();
//    }
//  }
//}
