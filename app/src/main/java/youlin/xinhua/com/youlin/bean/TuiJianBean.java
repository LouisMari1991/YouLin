package youlin.xinhua.com.youlin.bean;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     desc   : TO-DO
 *     author : 罗顺翔
 *     time   : 2017/5/9 0009 0936
 *     version: 1.0
 * </pre>
 */

public class TuiJianBean {

  private List<Banner>    mBannerList;
  private List<Recommend> mRecommendList;
  private List<Channel>   mChannelList;
  private Media           mVideo;
  private Media           mPicture;

  public List<Banner> getBannerList() {
    return mBannerList;
  }

  public void setBannerList(List<Banner> bannerList) {
    mBannerList = bannerList;
  }

  public List<Recommend> getRecommendList() {
    return mRecommendList;
  }

  public void setRecommendList(List<Recommend> recommendList) {
    mRecommendList = recommendList;
  }

  public List<Channel> getChannelList() {
    return mChannelList;
  }

  public void setChannelList(List<Channel> channelList) {
    mChannelList = channelList;
  }

  public Media getVideo() {
    return mVideo;
  }

  public void setVideo(Media video) {
    mVideo = video;
  }

  public Media getPicture() {
    return mPicture;
  }

  public void setPicture(Media picture) {
    mPicture = picture;
  }

  @Override public String toString() {
    return "TuiJianBean{"
        + "mBannerList="
        + mBannerList
        + ", mRecommendList="
        + mRecommendList
        + ", mChannelList="
        + mChannelList
        + ", mVideo="
        + mVideo
        + ", mPicture="
        + mPicture
        + '}';
  }

  /**
   * 轮播图
   */
  public static class Banner implements Serializable {
    private String uuid;
    private String title;
    private String describe;
    private String url;
    private String detailUrl;
    private String itemId;

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

    public String getDescribe() {
      return describe;
    }

    public void setDescribe(String describe) {
      this.describe = describe;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getDetailUrl() {
      return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
      this.detailUrl = detailUrl;
    }

    public String getItemId() {
      return itemId;
    }

    public void setItemId(String itemId) {
      this.itemId = itemId;
    }

    @Override public String toString() {
      return "Banner{"
          + "uuid='"
          + uuid
          + '\''
          + ", title='"
          + title
          + '\''
          + ", describe='"
          + describe
          + '\''
          + ", url='"
          + url
          + '\''
          + ", detailUrl='"
          + detailUrl
          + '\''
          + ", itemId='"
          + itemId
          + '\''
          + '}';
    }
  }

  /**
   * 首页推荐
   */
  public static class Recommend {
    private String uuid;
    private String title;
    private String creator;
    private String abstracts; // 文章摘要
    private String source;
    private String itemId;//所属频道
    private String weight;
    private int    type;
    private String htmlUrl;
    String time;
    String thumbnailurl;
    String itemName;
    private List<UrlDetails> urlDetailsList;

    public String getThumbnailurl() {
      return thumbnailurl;
    }

    public void setThumbnailurl(String thumbnailurl) {
      this.thumbnailurl = thumbnailurl;
    }

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

    public String getCreator() {
      return creator;
    }

    public void setCreator(String creator) {
      this.creator = creator;
    }

    public String getAbstracts() {
      return abstracts;
    }

    public void setAbstracts(String abstracts) {
      this.abstracts = abstracts;
    }

    public String getSource() {
      return source;
    }

    public void setSource(String source) {
      this.source = source;
    }

    public String getItemId() {
      return itemId;
    }

    public void setItemId(String itemId) {
      this.itemId = itemId;
    }

    public String getWeight() {
      return weight;
    }

    public void setWeight(String weight) {
      this.weight = weight;
    }

    public int getType() {
      return type;
    }

    public void setType(int type) {
      this.type = type;
    }

    public String getTime() {
      return time;
    }

    public void setTime(String time) {
      this.time = time;
    }

    public String getHtmlUrl() {
      return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
      this.htmlUrl = htmlUrl;
    }

    public List<UrlDetails> getUrlDetailsList() {
      return urlDetailsList;
    }

    public String getItemName() {
      return itemName;
    }

    public void setItemName(String itemName) {
      this.itemName = itemName;
    }

    public void setUrlDetailsList(List<UrlDetails> urlDetailsList) {
      this.urlDetailsList = urlDetailsList;
    }

    @Override public String toString() {
      return "Recommend{"
          + "uuid='"
          + uuid
          + '\''
          + ", title='"
          + title
          + '\''
          + ", creator='"
          + creator
          + '\''
          + ", abstracts='"
          + abstracts
          + '\''
          + ", source='"
          + source
          + '\''
          + ", itemId='"
          + itemId
          + '\''
          + ", weight='"
          + weight
          + '\''
          + ", type="
          + type
          + ", htmlUrl='"
          + htmlUrl
          + '\''
          + ", time='"
          + time
          + '\''
          + ", thumbnailurl='"
          + thumbnailurl
          + '\''
          + ", itemName='"
          + itemName
          + '\''
          + ", urlDetailsList="
          + urlDetailsList
          + '}';
    }
  }

  /**
   * 频道推荐
   */
  public static class Channel {
    private String          itemId;
    private String          itemName;
    private List<Recommend> itemList;

    public String getItemId() {
      return itemId;
    }

    public void setItemId(String itemId) {
      this.itemId = itemId;
    }

    public String getItemName() {
      return itemName;
    }

    public void setItemName(String itemName) {
      this.itemName = itemName;
    }

    public List<Recommend> getItemList() {
      return itemList;
    }

    public void setItemList(List<Recommend> itemList) {
      this.itemList = itemList;
    }

    @Override public String toString() {
      return "Channel{"
          + "itemId='"
          + itemId
          + '\''
          + ", itemName='"
          + itemName
          + '\''
          + ", itemList="
          + itemList
          + '}';
    }
  }

  public static class Media {
    private String          uuid;
    private String          itemName;
    private List<MediaItem> itemList;

    public String getUuid() {
      return uuid;
    }

    public void setUuid(String uuid) {
      this.uuid = uuid;
    }

    public String getItemName() {
      return itemName;
    }

    public void setItemName(String itemName) {
      this.itemName = itemName;
    }

    public List<MediaItem> getItemList() {
      return itemList;
    }

    public void setItemList(List<MediaItem> itemList) {
      this.itemList = itemList;
    }

    @Override public String toString() {
      return "Media{"
          + "uuid='"
          + uuid
          + '\''
          + ", itemName='"
          + itemName
          + '\''
          + ", itemList="
          + itemList
          + '}';
    }

    public static class MediaItem {
      private String           uuid;
      private String           title;
      private int              type;
      private String           creator;
      private String           thumbnailUrl;
      private String           source;
      private String           itemId;
      private String           weight;
      private String           time;
      private String           outurl;
      private List<UrlDetails> urlDetailsList;

      public String getOuturl() {
        return outurl;
      }

      public void setOuturl(String outurl) {
        this.outurl = outurl;
      }

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

      public int getType() {
        return type;
      }

      public void setType(int type) {
        this.type = type;
      }

      public String getCreator() {
        return creator;
      }

      public void setCreator(String creator) {
        this.creator = creator;
      }

      public String getThumbnailUrl() {
        return thumbnailUrl;
      }

      public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
      }

      public String getSource() {
        return source;
      }

      public void setSource(String source) {
        this.source = source;
      }

      public String getItemId() {
        return itemId;
      }

      public void setItemId(String itemId) {
        this.itemId = itemId;
      }

      public String getWeight() {
        return weight;
      }

      public void setWeight(String weight) {
        this.weight = weight;
      }

      public String getTime() {
        return time;
      }

      public void setTime(String time) {
        this.time = time;
      }

      public List<UrlDetails> getUrlDetailsList() {
        return urlDetailsList;
      }

      public void setUrlDetailsList(List<UrlDetails> urlDetailsList) {
        this.urlDetailsList = urlDetailsList;
      }

      @Override public String toString() {
        return "MediaItem{"
            + "uuid='"
            + uuid
            + '\''
            + ", title='"
            + title
            + '\''
            + ", type="
            + type
            + ", creator='"
            + creator
            + '\''
            + ", thumbnailUrl='"
            + thumbnailUrl
            + '\''
            + ", source='"
            + source
            + '\''
            + ", itemId='"
            + itemId
            + '\''
            + ", weight='"
            + weight
            + '\''
            + ", time='"
            + time
            + '\''
            + ", outurl='"
            + outurl
            + '\''
            + ", urlDetailsList="
            + urlDetailsList
            + '}';
      }
    }
  }

  public static class UrlDetails {
    private String uuid;
    private String url;
    private String sort;
    private String type;
    private String time;

    public String getTime() {
      return time;
    }

    public void setTime(String time) {
      this.time = time;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getUuid() {
      return uuid;
    }

    public void setUuid(String uuid) {
      this.uuid = uuid;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getSort() {
      return sort;
    }

    public void setSort(String sort) {
      this.sort = sort;
    }

    @Override public String toString() {
      return "UrlDetails{"
          + "uuid='"
          + uuid
          + '\''
          + ", url='"
          + url
          + '\''
          + ", sort="
          + sort
          + '}';
    }
  }
}
