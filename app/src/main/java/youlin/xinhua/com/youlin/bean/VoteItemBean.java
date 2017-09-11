package youlin.xinhua.com.youlin.bean;

import java.util.List;

/**
 * <pre>
 *     desc   : 投票
 *     author : 罗顺翔
 *     time   : 2017-06-05 15:24
 *     version: 1.0
 * </pre>
 *
 * 查询投票列表：返回字段加入voteType
 * 查询投票：返回字段给出voteType，hasPicNote字段
 * voteType	投票类型	int	投票类型：1:普通投票 2:人物投票 3:图集投票
 * hasPicNote	是否有图文	int	1:有 2:没有
 */

public class VoteItemBean {

  private String         uuid;
  private String         title; // 投票主题
  private String         organization; // 所属机构
  private String         content;//内容简介
  private String         startTime;//开始时间
  private String         endTime;//结束时间
  private String         auth; // 投票权限
  private String         carryState; //0:未开始；1:进行中；2:已结束;
  private String         picUrl;
  private int            answersTotal;
  private List<Question> questionList; //问题列表
  private int            isAnswer; // 是否回答问题， 0:未回答,1:已回答
  private int            voteType;// 投票类型
  private List<Answer>   resultList; // 个人回答的答案集合
  private String         relativeTime; // 详情页的相对时间

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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

  public String getAuth() {
    return auth;
  }

  public void setAuth(String auth) {
    this.auth = auth;
  }

  public String getCarryState() {
    return carryState;
  }

  public void setCarryState(String carryState) {
    this.carryState = carryState;
  }

  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  public int getAnswersTotal() {
    return answersTotal;
  }

  public void setAnswersTotal(int answersTotal) {
    this.answersTotal = answersTotal;
  }

  public List<Question> getQuestionList() {
    return questionList;
  }

  public void setQuestionList(List<Question> questionList) {
    this.questionList = questionList;
  }

  public int getIsAnswer() {
    return isAnswer;
  }

  public void setIsAnswer(int isAnswer) {
    this.isAnswer = isAnswer;
  }

  public int getVoteType() {
    return voteType;
  }

  public void setVoteType(int voteType) {
    this.voteType = voteType;
  }

  public List<Answer> getResultList() {
    return resultList;
  }

  public void setResultList(List<Answer> resultList) {
    this.resultList = resultList;
  }

  public String getRelativeTime() {
    return relativeTime;
  }

  public void setRelativeTime(String relativeTime) {
    this.relativeTime = relativeTime;
  }

  @Override public String toString() {
    return "VoteItemBean{"
        + "uuid='"
        + uuid
        + '\''
        + ", title='"
        + title
        + '\''
        + ", organization='"
        + organization
        + '\''
        + ", content='"
        + content
        + '\''
        + ", startTime='"
        + startTime
        + '\''
        + ", endTime='"
        + endTime
        + '\''
        + ", auth='"
        + auth
        + '\''
        + ", carryState='"
        + carryState
        + '\''
        + ", picUrl='"
        + picUrl
        + '\''
        + ", answerTotal="
        + answersTotal
        + ", questionList="
        + questionList
        + ", isAnswer="
        + isAnswer
        + ", voteType="
        + voteType
        + ", resultList="
        + resultList
        + ", relativeTime='"
        + relativeTime
        + '\''
        + '}';
  }

  public static class Question {

    private String uuid;

    private String questionName; // 问题名字

    private String quesOrderNum; //问题序号

    private int questionType; //问题类型,0：单选，1:多选

    private List<Option> optionList;  // 选项列表

    public String getUuid() {
      return uuid;
    }

    public void setUuid(String uuid) {
      this.uuid = uuid;
    }

    public String getQuestionName() {
      return questionName;
    }

    public void setQuestionName(String questionName) {
      this.questionName = questionName;
    }

    public String getQuesOrderNum() {
      return quesOrderNum;
    }

    public void setQuesOrderNum(String quesOrderNum) {
      this.quesOrderNum = quesOrderNum;
    }

    public int getQuestionType() {
      return questionType;
    }

    public void setQuestionType(int questionType) {
      this.questionType = questionType;
    }

    public List<Option> getOptionList() {
      return optionList;
    }

    public void setOptionList(List<Option> optionList) {
      this.optionList = optionList;
    }

    @Override public String toString() {
      return "Question{"
          + "uuid='"
          + uuid
          + '\''
          + ", questionName='"
          + questionName
          + '\''
          + ", quesOrderNum='"
          + quesOrderNum
          + '\''
          + ", questionType="
          + questionType
          + ", optionList="
          + optionList
          + '}';
    }

    public static class Option {
      private String uuid;
      private String optionContent; // 选项内容
      private String picSite; // 选项图片
      private int    answerTotal;
      private int    hasPicNote;// 是否有图文, 1:有,2:没有
      private int    isVote; // 是否已经投票， 0: 没有, 1: 有

      public String getUuid() {
        return uuid;
      }

      public void setUuid(String uuid) {
        this.uuid = uuid;
      }

      public String getOptionContent() {
        return optionContent;
      }

      public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
      }

      public String getPicSite() {
        return picSite;
      }

      public void setPicSite(String picSite) {
        this.picSite = picSite;
      }

      public int getAnswerTotal() {
        return answerTotal;
      }

      public void setAnswerTotal(int answerTotal) {
        this.answerTotal = answerTotal;
      }

      public int getHasPicNote() {
        return hasPicNote;
      }

      public void setHasPicNote(int hasPicNote) {
        this.hasPicNote = hasPicNote;
      }

      public int getIsVote() {
        return isVote;
      }

      public void setIsVote(int isVote) {
        this.isVote = isVote;
      }
    }
  }

  public static class Answer {
    private String uuid;
    private String optionId;

    public String getUuid() {
      return uuid;
    }

    public void setUuid(String uuid) {
      this.uuid = uuid;
    }

    public String getOptionId() {
      return optionId;
    }

    public void setOptionId(String optionId) {
      this.optionId = optionId;
    }

    @Override public String toString() {
      return "Answer{" + "uuid='" + uuid + '\'' + ", optionId='" + optionId + '\'' + '}';
    }
  }
}
