package com.tencent.tim.message;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMUserProfile;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.adapter.MessageViewHolder;
import youlin.xinhua.com.youlin.listener.ChatItemClickListener;
import youlin.xinhua.com.youlin.utils.DateUtil;
import youlin.xinhua.com.youlin.utils.LogUtils;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/02/23
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public abstract class Message implements MultiItemEntity {

  public static final int TYPE_TEXT_SEND = 1;
  public static final int TYPE_TEXT_RECEIVE = 2;
  public static final int TYPE_IMG_SEND = 3;
  public static final int TYPE_IMG_RECEIVE = 4;
  public static final int TYPE_VOICE_SEND = 5;
  public static final int TYPE_VOICE_RECEIVE = 6;
  public static final int TYPE_GROUP_TIP = 7;
  public static final int TYPE_GROUP_INVITE_SEND = 8;
  public static final int TYPE_GROUP_INVITE_RECEIVER = 9;
  public static final int TYPE_MEETING_INVITE_SEND = 10;
  public static final int TYPE_MEETING_INVITE_RECEIVER = 11;
  public static final int TYPE_EVENT = 12;

  protected TIMMessage message;

  protected Context context;

  protected MessageViewHolder helper;

  protected ChatItemClickListener clickListener;

  public Message() {
  }

  public Message(TIMMessage message) {
    this.message = message;
  }

  private boolean hasTime;

  /**
   * 消息描述信息
   */
  private String desc;

  public TIMMessage getMessage() {
    return message;
  }

  /**
   * 判断是否是自己发的
   */
  public boolean isSelf() {
    return message.isSelf();
  }

  /**
   * 获取消息摘要
   */
  public abstract String getSummary();

  /**
   * 获取发送者
   */
  public String getSender() {
    if (message.isSelf()) {
      return "我";
    } else {
      TIMUserProfile senderProfile = message.getSenderProfile();
      return senderProfile.getNickName();
    }
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public boolean isHasTime() {
    return hasTime;
  }

  public void setHasTime(boolean hasTime) {
    this.hasTime = hasTime;
  }

  /**
   * 是否需要显示时间设置
   *
   * @param message 上一条消息
   */
  public void setHasTime(TIMMessage message) {
    if (message == null) {
      hasTime = true;
      return;
    }
    LogUtils.i(this.message.timestamp() + " , " + message.timestamp());
    hasTime = this.message.timestamp() - message.timestamp() > 300;
  }

  public void setUpBaseView(Context context, MessageViewHolder helper,
      ChatItemClickListener listener) {

    this.context = context;
    this.helper = helper;
    this.clickListener = listener;

    TextView timestamp = helper.getView(R.id.timestamp);

    if (timestamp != null) {
      if (isHasTime()) {
        timestamp.setVisibility(View.VISIBLE);
        timestamp.setText(DateUtil.getChatTimeStr(message.timestamp()));
      } else {
        timestamp.setVisibility(View.GONE);
      }
    }

    setClickListener();
    onSetUpView();
  }

  private void setClickListener() {

    if (clickListener == null) {
      return;
    }

    View bubble = helper.getView(R.id.bubble);
    View msg_status = helper.getView(R.id.msg_status);
    View img_avatar = helper.getView(R.id.avatar);

    if (bubble != null) {
      bubble.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          if (!clickListener.onBubbleClick(Message.this)) {
            // if listener return false, we call default handling
            onBubbleClick();
          }
        }
      });
      bubble.setOnLongClickListener(new View.OnLongClickListener() {
        @Override public boolean onLongClick(View v) {
          clickListener.onBubbleLongClick(Message.this);
          return true;
        }
      });
    }

    if (msg_status != null) {
      msg_status.setOnClickListener(new View.OnClickListener() {

        @Override public void onClick(View v) {
          clickListener.onResendClick(Message.this);
        }
      });
    }

    if (img_avatar != null) {
      img_avatar.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          clickListener.onUserAvatarClick(getSender());
        }
      });
      img_avatar.setOnLongClickListener(new View.OnLongClickListener() {
        @Override public boolean onLongClick(View v) {
          clickListener.onUserAvatarLongClick(getSender());
          return true;
        }
      });
    }
  }

  protected abstract void onBubbleClick();

  protected abstract void onSetUpView();

  /**
   * 显示消息状态
   */
  public void showStatus() {

    View msg_status = helper.getView(R.id.msg_status);
    View progress_bar = helper.getView(R.id.progress_bar);

    switch (message.status()) {
      case Sending:
        if (msg_status != null) {
          msg_status.setVisibility(View.INVISIBLE);
        }
        if (progress_bar != null) {
          progress_bar.setVisibility(View.VISIBLE);
        }
        break;
      case SendSucc:
        if (msg_status != null) {
          msg_status.setVisibility(View.INVISIBLE);
        }
        if (progress_bar != null) {
          progress_bar.setVisibility(View.INVISIBLE);
        }
        break;
      case SendFail:
        if (msg_status != null) {
          msg_status.setVisibility(View.VISIBLE);
        }
        if (progress_bar != null) {
          progress_bar.setVisibility(View.INVISIBLE);
        }
        break;
    }
  }
}
