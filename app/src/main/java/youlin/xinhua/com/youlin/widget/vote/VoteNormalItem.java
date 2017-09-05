package youlin.xinhua.com.youlin.widget.vote;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.ToastUtils;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-05 10:48
 * version: 1.0
 * </pre>
 */

public class VoteNormalItem extends LinearLayout {

  @OnClick({ R.id.text_vote, R.id.item_container }) public void click(View v) {
    switch (v.getId()) {
      case R.id.text_vote: {
        ToastUtils.show("投票");
      }
      break;
      case R.id.item_container: {
        ToastUtils.show("详情");
      }
      break;
    }
  }

  public VoteNormalItem(Context context) {
    super(context);
    init();
  }

  private void init() {
    inflate(getContext(), R.layout.item_vote_normal, this);
    ButterKnife.bind(this);
  }
}
