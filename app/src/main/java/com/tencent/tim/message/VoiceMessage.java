package com.tencent.tim.message;

import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMSoundElem;
import com.xinhua.tim.util.MediaUtil;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/02/23
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class VoiceMessage extends Message {

  public VoiceMessage(TIMMessage message) {
    super(message);
  }

  /**
   * 语音消息构造方法
   *
   * @param duration 时长
   * @param filePath 语音数据地址
   */
  public VoiceMessage(long duration, String filePath) {
    message = new TIMMessage();
    TIMSoundElem elem = new TIMSoundElem();
    elem.setPath(filePath);
    elem.setDuration(duration);  //填写语音时长
    message.addElement(elem);
  }

  @Override public String getSummary() {
    return "[语音]";
  }

  @Override protected void onBubbleClick() {

  }

  @Override protected void onSetUpView() {
    ImageView voiceImageView = helper.getView(R.id.iv_voice);
    TextView voiceLengthView = helper.getView(R.id.tv_length);
    AnimationDrawable voiceAnimation;

    message.getElement(0).getType();

    TIMSoundElem elem = (TIMSoundElem) message.getElement(0);

    if (MediaUtil.getInstance().isPlaying() && TextUtils.equals(elem.getUuid(),
        MediaUtil.getInstance().getPlayId())) {

    } else {

      if (isSelf()) {
        voiceImageView.setImageResource(R.drawable.voice_from_icon);
      } else {
        voiceImageView.setImageResource(R.drawable.voice_to_icon);
      }
    }
    showStatus();
  }

  @Override public int getItemType() {
    if (isSelf()) {
      return TYPE_VOICE_SEND;
    } else {
      return TYPE_VOICE_RECEIVE;
    }
  }
}
