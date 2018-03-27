package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import butterknife.BindView;
import com.flyco.animation.FadeEnter.FadeEnter;
import com.flyco.animation.FadeExit.FadeExit;
import youlin.xinhua.com.youlin.base.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.ToastUtils;
import youlin.xinhua.com.youlin.widget.MessageToolbar;
import youlin.xinhua.com.youlin.widget.extra.CustomBubblePopup;
import youlin.xinhua.com.youlin.widget.extra.GongYiBubblePopup;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2017/11/29
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class MessageToolbarActivity extends BaseActivity {

  @BindView(R.id.message_tool_bar) MessageToolbar messageToolbar;

  public static void start(Context context) {
    Intent starter = new Intent(context, MessageToolbarActivity.class);
    context.startActivity(starter);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_message_toolbar;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    messageToolbar.setTextTitle("公益组织");
    messageToolbar.setImgBackOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        finish();
      }
    });

    messageToolbar.setRightOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        // show popup
        CustomBubblePopup customBubblePopup = new CustomBubblePopup(v.getContext());
        customBubblePopup.setCanceledOnTouchOutside(false);

        View img = messageToolbar.findViewById(R.id.toolbar_image_right);

        GongYiBubblePopup popup =
            new GongYiBubblePopup(v.getContext(), GongYiBubblePopup.TYPE_JOINED_HAS_MESSAGE);
        popup.gravity(Gravity.BOTTOM)
            .anchorView(img)
            .triangleWidth(15)
            .triangleHeight(7)
            .cornerRadius(10)
            .bubbleColor(android.R.color.white)
            .showAnim(new FadeEnter())
            .dismissAnim(new FadeExit())
            .setOnItemClickListener(new GongYiBubblePopup.OnItemClickListener() {
              @Override public void onMessageClick(GongYiBubblePopup popup) {
                ToastUtils.show("onMessageClick");
                popup.cancel();
              }

              @Override public void onJuBaoClick(GongYiBubblePopup popup) {
                ToastUtils.show("onJuBaoClick");
                popup.cancel();
              }

              @Override public void onExitClick(GongYiBubblePopup popup) {
                ToastUtils.show("onExitClick");
                popup.cancel();
              }
            })
            .show();
      }
    });
  }
}
