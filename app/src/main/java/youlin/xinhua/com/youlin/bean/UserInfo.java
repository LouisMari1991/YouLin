package youlin.xinhua.com.youlin.bean;

import java.io.Serializable;
import youlin.xinhua.com.youlin.widget.ISuspensionInterface;

/**
 * <pre>
 *     desc   : 总后台用户信息
 *     author : 罗顺翔
 *     time   : 2017-06-22 18:53
 *     version: 1.0
 * </pre>
 */
public class UserInfo implements Serializable, ISuspensionInterface {
  /**
   * uuid : 3fb8002fd6d64ca199f91e02c4123951
   * status : 0
   * createTime : 1498706947000
   * updateTime : 1498706947000
   * memo :
   * mobilePhone : 15914080970
   * password :
   * email :
   * nickname : 1
   * truename :
   * sex :
   * age : 0
   * avatar :
   * identifier :
   * idImgFront :
   * idImgBack :
   * fixPhone :
   * lastLoginTime : 1499142882490
   * label :
   */
  private Long id;

  private String uuid;
  private int    status;
  private long   createTime;
  private long   updateTime;
  private String memo;       //  个人描述",(可选
  private String mobilePhone;
  private String password;
  private String email;
  private String nickname;// "昵称",(可选)
  private String truename;//  "真实姓名",(可选)
  private int    sex;
  private int    age;
  private String avatar;
  private String identifier; // 用户身份证号",(可选）
  private String idImgFront; //   "身份证正面照（可选）"，
  private String idImgBack;// "身份证反面照（可选）"
  private String fixPhone; //  固定电话（可选）"
  private long   lastLoginTime;
  private String label;
  private String initialLetter; // 昵称首字母

  public UserInfo(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public UserInfo(Long id, String uuid, int status, long createTime, long updateTime, String memo,
      String mobilePhone, String password, String email, String nickname, String truename, int sex,
      int age, String avatar, String identifier, String idImgFront, String idImgBack,
      String fixPhone, String label) {
    this.id = id;
    this.uuid = uuid;
    this.status = status;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.memo = memo;
    this.mobilePhone = mobilePhone;
    this.password = password;
    this.email = email;
    this.nickname = nickname;
    this.truename = truename;
    this.sex = sex;
    this.age = age;
    this.avatar = avatar;
    this.identifier = identifier;
    this.idImgFront = idImgFront;
    this.idImgBack = idImgBack;
    this.fixPhone = fixPhone;
    this.label = label;
  }

  public UserInfo() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  public long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(long updateTime) {
    this.updateTime = updateTime;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNickname() {
    return this.nickname == null ? this.getMobilePhone() : this.nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getTruename() {
    return truename;
  }

  public void setTruename(String truename) {
    this.truename = truename;
  }

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getIdImgFront() {
    return idImgFront;
  }

  public void setIdImgFront(String idImgFront) {
    this.idImgFront = idImgFront;
  }

  public String getIdImgBack() {
    return idImgBack;
  }

  public void setIdImgBack(String idImgBack) {
    this.idImgBack = idImgBack;
  }

  public String getFixPhone() {
    return fixPhone;
  }

  public void setFixPhone(String fixPhone) {
    this.fixPhone = fixPhone;
  }

  public long getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(long lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getInitialLetter() {
    return initialLetter;
  }

  public void setInitialLetter(String initialLetter) {
    this.initialLetter = initialLetter;
  }

  //private String uuid;
  //private String mobilePhone;
  //private String email;
  //private String nickname;// "昵称",(可选)
  //private String truename; //  "真实姓名",(可选)
  //private String sex;//   0(man),1(woman)
  //private String avartar;//  "用户头像图片名称",(可选)
  //private String identifier;//  用户身份证号",(可选）
  //private String idImgFront;//   "身份证正面照（可选）"，
  //private String idImgBack;// "身份证反面照（可选）"
  //private String fixPhone;//  固定电话（可选）"
  //private String memo;//  个人描述",(可选
  //
  //public String getUuid() {
  //  return uuid;
  //}
  //
  //public void setUuid(String uuid) {
  //  this.uuid = uuid;
  //}
  //
  //public String getMobilePhone() {
  //  return mobilePhone;
  //}
  //
  //public void setMobilePhone(String mobilePhone) {
  //  this.mobilePhone = mobilePhone;
  //}
  //
  //public String getEmail() {
  //  return email;
  //}
  //
  //public void setEmail(String email) {
  //  this.email = email;
  //}
  //
  //public String getNickname() {
  //  return nickname;
  //}
  //
  //public void setNickname(String nickname) {
  //  this.nickname = nickname;
  //}
  //
  //public String getTruename() {
  //  return truename;
  //}
  //
  //public void setTruename(String truename) {
  //  this.truename = truename;
  //}
  //
  //public String getSex() {
  //  return sex;
  //}
  //
  //public void setSex(String sex) {
  //  this.sex = sex;
  //}
  //
  //public String getAvartar() {
  //  return avartar;
  //}
  //
  //public void setAvartar(String avartar) {
  //  this.avartar = avartar;
  //}
  //
  //public String getIdentifier() {
  //  return identifier;
  //}
  //
  //public void setIdentifier(String identifier) {
  //  this.identifier = identifier;
  //}
  //
  //public String getIdImgFront() {
  //  return idImgFront;
  //}
  //
  //public void setIdImgFront(String idImgFront) {
  //  this.idImgFront = idImgFront;
  //}
  //
  //public String getIdImgBack() {
  //  return idImgBack;
  //}
  //
  //public void setIdImgBack(String idImgBack) {
  //  this.idImgBack = idImgBack;
  //}
  //
  //public String getFixPhone() {
  //  return fixPhone;
  //}
  //
  //public void setFixPhone(String fixPhone) {
  //  this.fixPhone = fixPhone;
  //}
  //
  //public String getMemo() {
  //  return memo;
  //}
  //
  //public void setMemo(String memo) {
  //  this.memo = memo;
  //}
  //
  //@Override public String toString() {
  //  return "UserInfo{"
  //      + "uuid='"
  //      + uuid
  //      + '\''
  //      + ", mobilePhone='"
  //      + mobilePhone
  //      + '\''
  //      + ", email='"
  //      + email
  //      + '\''
  //      + ", nickname='"
  //      + nickname
  //      + '\''
  //      + ", truename='"
  //      + truename
  //      + '\''
  //      + ", sex='"
  //      + sex
  //      + '\''
  //      + ", avartar='"
  //      + avartar
  //      + '\''
  //      + ", identifier='"
  //      + identifier
  //      + '\''
  //      + ", idImgFront='"
  //      + idImgFront
  //      + '\''
  //      + ", idImgBack='"
  //      + idImgBack
  //      + '\''
  //      + ", fixPhone='"
  //      + fixPhone
  //      + '\''
  //      + ", memo='"
  //      + memo
  //      + '\''
  //      + '}';
  //}
}
