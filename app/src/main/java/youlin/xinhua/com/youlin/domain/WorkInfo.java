package youlin.xinhua.com.youlin.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 * @author : 罗顺翔
 * desc   : 业委会工作履历
 * time   : 2017-10-26 17:59
 * version: 1.0
 * </pre>
 */

public class WorkInfo implements Parcelable {

  private String company;
  private String job;
  private String startTime;
  private String endTime;

  public WorkInfo() {
  }

  protected WorkInfo(Parcel in) {
    this.company = in.readString();
    this.job = in.readString();
    this.startTime = in.readString();
    this.endTime = in.readString();
  }

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

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.company);
    dest.writeString(this.job);
    dest.writeString(this.startTime);
    dest.writeString(this.endTime);
  }

  public static final Creator<WorkInfo> CREATOR = new Creator<WorkInfo>() {
    @Override public WorkInfo createFromParcel(Parcel source) {
      return new WorkInfo(source);
    }

    @Override public WorkInfo[] newArray(int size) {
      return new WorkInfo[size];
    }
  };
}
