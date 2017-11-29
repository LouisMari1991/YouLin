package youlin.xinhua.com.youlin.widget.extra;

import android.content.Context;
import android.support.annotation.IntDef;
import android.view.View;
import com.flyco.animation.FadeEnter.FadeEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.dialog.widget.popup.base.BaseBubblePopup;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2017/11/29
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class GongYiBubblePopup extends BaseBubblePopup<GongYiBubblePopup> {

  public static final int TYPE_NO_JOIN_NO_MESSAGE = 0;
  public static final int TYPE_NO_JOIN_HAS_MESSAGE = 1;
  public static final int TYPE_JOINED_NO_MESSAGE = 3;
  public static final int TYPE_JOINED_HAS_MESSAGE = 4;

  int status;

  View rl_xiaoxi;
  View rl_jubao;
  View rl_tuichu;
  View view_tip;

  OnItemClickListener onItemClickListener;

  public GongYiBubblePopup setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
    return this;
  }

  public GongYiBubblePopup(Context context, @GongYiBubblePopupStatus int status) {
    super(context);
    this.status = status;
  }

  @Override public View onCreateBubbleView() {
    View inflate = View.inflate(mContext, R.layout.popup_gongyi_message, null);
    rl_xiaoxi = inflate.findViewById(R.id.rl_xiaoxi);
    rl_jubao = inflate.findViewById(R.id.rl_jubao);
    rl_tuichu = inflate.findViewById(R.id.rl_tuichu);
    view_tip = inflate.findViewById(R.id.view_tip);
    return inflate;
  }

  @Override public void setUiBeforShow() {
    super.setUiBeforShow();



    switch (status) {
      case TYPE_NO_JOIN_NO_MESSAGE:
        view_tip.setVisibility(View.GONE);
        rl_tuichu.setVisibility(View.GONE);
        break;
      case TYPE_NO_JOIN_HAS_MESSAGE:
        view_tip.setVisibility(View.VISIBLE);
        rl_tuichu.setVisibility(View.GONE);
        break;
      case TYPE_JOINED_NO_MESSAGE:
        view_tip.setVisibility(View.GONE);
        rl_tuichu.setVisibility(View.VISIBLE);
        break;
      case TYPE_JOINED_HAS_MESSAGE:
        view_tip.setVisibility(View.VISIBLE);
        rl_tuichu.setVisibility(View.VISIBLE);
        break;
    }

    rl_xiaoxi.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (onItemClickListener != null) {
          onItemClickListener.onMessageClick(GongYiBubblePopup.this);
        }
      }
    });
    rl_jubao.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onItemClickListener.onJuBaoClick(GongYiBubblePopup.this);
      }
    });
    rl_tuichu.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onItemClickListener.onExitClick(GongYiBubblePopup.this);
      }
    });
  }

  @IntDef({
      TYPE_NO_JOIN_NO_MESSAGE, TYPE_NO_JOIN_HAS_MESSAGE, TYPE_JOINED_NO_MESSAGE,
      TYPE_JOINED_HAS_MESSAGE
  }) public @interface GongYiBubblePopupStatus {
  }

  public interface OnItemClickListener {

    void onMessageClick(GongYiBubblePopup popup);

    void onJuBaoClick(GongYiBubblePopup popup);

    void onExitClick(GongYiBubblePopup popup);
  }
}
