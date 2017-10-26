package youlin.xinhua.com.youlin.bean;

import java.io.Serializable;

/**
 * <pre>
 * @author : 罗顺翔
 * desc   : Todo
 * time   : 2017-10-26 17:57
 * version: 1.0
 * </pre>
 */

public class WorkInfo implements Serializable {

  private String company;
  private String job;
  private String startTime;
  private String endTime;

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
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

  @Override public String toString() {
    return "WorkInfo{"
        + "company='"
        + company
        + '\''
        + ", job='"
        + job
        + '\''
        + ", startTime='"
        + startTime
        + '\''
        + ", endTime='"
        + endTime
        + '\''
        + '}';
  }
}
