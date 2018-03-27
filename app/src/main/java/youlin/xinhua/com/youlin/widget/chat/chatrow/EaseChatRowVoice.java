//package youlin.xinhua.com.youlin.widget.chat.chatrow;
//
//import android.content.Context;
//import android.graphics.drawable.AnimationDrawable;
//import android.view.View;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import com.hyphenate.chat.EMFileMessageBody;
//import com.hyphenate.chat.EMMessage;
//import com.hyphenate.chat.EMVoiceMessageBody;
//import youlin.xinhua.com.youlin.R;
//import youlin.xinhua.com.youlin.utils.LogUtils;
//
//public class EaseChatRowVoice extends EaseChatRowFile {
//
//  private ImageView voiceImageView;
//  private TextView  voiceLengthView;
//
//  public EaseChatRowVoice(Context context, EMMessage message, int position, BaseAdapter adapter) {
//    super(context, message, position, adapter);
//  }
//
//  @Override protected void onInflateView() {
//    inflater.inflate(
//        message.direct() == EMMessage.Direct.RECEIVE ? R.layout.item_chat_received_voice
//            : R.layout.item_chat_send_voice, this);
//  }
//
//  @Override protected void onFindViewById() {
//    voiceImageView = ((ImageView) findViewById(R.id.iv_voice));
//    voiceLengthView = (TextView) findViewById(R.id.tv_length);
//  }
//
//  @Override protected void onSetUpView() {
//    EMVoiceMessageBody voiceBody = (EMVoiceMessageBody) message.getBody();
//    int len = voiceBody.getLength();
//    if (len > 0) {
//      voiceLengthView.setText(voiceBody.getLength() + "\"");
//      voiceLengthView.setVisibility(View.VISIBLE);
//    } else {
//      voiceLengthView.setVisibility(View.INVISIBLE);
//    }
//    if (EaseChatRowVoicePlayClickListener.playMsgId != null
//        && EaseChatRowVoicePlayClickListener.playMsgId.equals(message.getMsgId())
//        && EaseChatRowVoicePlayClickListener.isPlaying) {
//      AnimationDrawable voiceAnimation;
//      if (message.direct() == EMMessage.Direct.RECEIVE) {
//        voiceImageView.setImageResource(R.drawable.voice_from_icon);
//      } else {
//        voiceImageView.setImageResource(R.drawable.voice_to_icon);
//      }
//      voiceAnimation = (AnimationDrawable) voiceImageView.getDrawable();
//      voiceAnimation.start();
//    } else {
//      if (message.direct() == EMMessage.Direct.RECEIVE) {
//        voiceImageView.setImageResource(R.drawable.ease_chatfrom_voice_playing);
//      } else {
//        voiceImageView.setImageResource(R.drawable.ease_chatto_voice_playing);
//      }
//    }
//
//    if (message.direct() == EMMessage.Direct.RECEIVE) {
//      //if (message.isListened()) {
//      //  // hide the unread icon
//      //  readStatusView.setVisibility(View.INVISIBLE);
//      //} else {
//      //  readStatusView.setVisibility(View.VISIBLE);
//      //}
//      LogUtils.d("it is receive msg");
//      if (voiceBody.downloadStatus() == EMFileMessageBody.EMDownloadStatus.DOWNLOADING
//          || voiceBody.downloadStatus() == EMFileMessageBody.EMDownloadStatus.PENDING) {
//        progressBar.setVisibility(View.VISIBLE);
//        setMessageReceiveCallback();
//      } else {
//        progressBar.setVisibility(View.INVISIBLE);
//      }
//      return;
//    }
//
//    // until here, handle sending voice message
//    handleSendMessage();
//  }
//
//  @Override protected void onUpdateView() {
//    super.onUpdateView();
//  }
//
//  @Override protected void onBubbleClick() {
//    new EaseChatRowVoicePlayClickListener(message, voiceImageView, adapter, activity).onClick(
//        bubbleLayout);
//  }
//
//  @Override protected void onDetachedFromWindow() {
//    super.onDetachedFromWindow();
//    if (EaseChatRowVoicePlayClickListener.currentPlayListener != null
//        && EaseChatRowVoicePlayClickListener.isPlaying) {
//      EaseChatRowVoicePlayClickListener.currentPlayListener.stopPlayVoice();
//    }
//  }
//}
