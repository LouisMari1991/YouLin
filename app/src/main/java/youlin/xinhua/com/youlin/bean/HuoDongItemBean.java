package youlin.xinhua.com.youlin.bean;

import java.util.List;

/**
 * <pre>
 *     desc   : TO-DO
 *     author : 罗顺翔
 *     time   : 2017-06-05 11:24
 *     version: 1.0
 * </pre>
 */

public class HuoDongItemBean {

  private String          uuid;
  private String          actName;//活动名称
  private String          actStatus; // 活动状态 0：普通 1：推荐 2：删除
  private String          memo;//活动摘要
  private String          actContent; //活动内容
  private String          applicationStart;//报名开始时间
  private String          applicationEnd;//报名结束时间
  private String          startTime;// 开始时间
  private String          endTime; //结束时间
  private String          actChargeType; //消费类型 0:免费 1:收费
  private String          peopleLimit;  // 人数限制
  private String          actPlace;// 活动地点
  private String          telephone; // 联系方式
  private String          actAuth; // 参与权限
  //指活动进行状态，根据开始结束时间判断 0:未开始；1:进行中；2:已结束 3.已参与
  private String          carryState;
  private int             actTotal; // 参与人总数
  private String          firstPic; // 封面图
  private int             isApplicant; // 活动是否报名，0:未报名 1:已报名
  private List<Applicant> applicantList;// 活动参与者列表

  private String relativeTime; // 详情页的相对时间

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getActName() {
    return actName;
  }

  public void setActName(String actName) {
    this.actName = actName;
  }

  public String getActStatus() {
    return actStatus;
  }

  public void setActStatus(String actStatus) {
    this.actStatus = actStatus;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getActContent() {
    return actContent;
  }

  public void setActContent(String actContent) {
    this.actContent = actContent;
  }

  public String getApplicationStart() {
    return applicationStart;
  }

  public void setApplicationStart(String applicationStart) {
    this.applicationStart = applicationStart;
  }

  public String getApplicationEnd() {
    return applicationEnd;
  }

  public void setApplicationEnd(String applicationEnd) {
    this.applicationEnd = applicationEnd;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getActChargeType() {
    return actChargeType;
  }

  public void setActChargeType(String actChargeType) {
    this.actChargeType = actChargeType;
  }

  public String getPeopleLimit() {
    return peopleLimit;
  }

  public void setPeopleLimit(String peopleLimit) {
    this.peopleLimit = peopleLimit;
  }

  public String getActPlace() {
    return actPlace;
  }

  public void setActPlace(String actPlace) {
    this.actPlace = actPlace;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getActAuth() {
    return actAuth;
  }

  public void setActAuth(String actAuth) {
    this.actAuth = actAuth;
  }

  public String getCarryState() {
    return carryState;
  }

  public void setCarryState(String carryState) {
    this.carryState = carryState;
  }

  public int getActTotal() {
    return actTotal;
  }

  public void setActTotal(int actTotal) {
    this.actTotal = actTotal;
  }

  public String getFirstPic() {
    return firstPic;
  }

  public void setFirstPic(String firstPic) {
    this.firstPic = firstPic;
  }

  public int getIsApplicant() {
    return isApplicant;
  }

  public void setIsApplicant(int isApplicant) {
    this.isApplicant = isApplicant;
  }

  public List<Applicant> getApplicantList() {
    return applicantList;
  }

  public void setApplicantList(List<Applicant> applicantList) {
    this.applicantList = applicantList;
  }

  public String getRelativeTime() {
    return relativeTime;
  }

  public void setRelativeTime(String relativeTime) {
    this.relativeTime = relativeTime;
  }

  @Override public String toString() {
    return "HuoDongItemBean{"
        + "uuid='"
        + uuid
        + '\''
        + ", actName='"
        + actName
        + '\''
        + ", actStatus='"
        + actStatus
        + '\''
        + ", memo='"
        + memo
        + '\''
        + ", actContent='"
        + actContent
        + '\''
        + ", applicationStart='"
        + applicationStart
        + '\''
        + ", applicationEnd='"
        + applicationEnd
        + '\''
        + ", startTime='"
        + startTime
        + '\''
        + ", endTime='"
        + endTime
        + '\''
        + ", actChargeType='"
        + actChargeType
        + '\''
        + ", peopleLimit='"
        + peopleLimit
        + '\''
        + ", actPlace='"
        + actPlace
        + '\''
        + ", telephone='"
        + telephone
        + '\''
        + ", actAuth="
        + actAuth
        + ", carryState='"
        + carryState
        + '\''
        + ", actTotal='"
        + actTotal
        + '\''
        + ", firstPic='"
        + firstPic
        + '\''
        + ", isApplicant="
        + isApplicant
        + ", applicantList="
        + applicantList
        + '}';
  }

  public static class Applicant {
    private String uuid;
    private String applicantName; //参与者姓名
    private String applicantMemo; // 参与者头像

    public String getUuid() {
      return uuid;
    }

    public void setUuid(String uuid) {
      this.uuid = uuid;
    }

    public String getApplicantName() {
      return applicantName;
    }

    public void setApplicantName(String applicantName) {
      this.applicantName = applicantName;
    }

    public String getApplicantMemo() {
      return applicantMemo;
    }

    public void setApplicantMemo(String applicantMemo) {
      this.applicantMemo = applicantMemo;
    }

    @Override public String toString() {
      return "Applicant{"
          + "applicantName='"
          + applicantName
          + '\''
          + ", applicantMemo='"
          + applicantMemo
          + '\''
          + '}';
    }
  }
}
