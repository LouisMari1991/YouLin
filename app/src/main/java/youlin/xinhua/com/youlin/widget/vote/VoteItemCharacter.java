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
 * time   : 2017-09-05 17:31
 * version: 1.0
 * </pre>
 */

public class VoteItemCharacter extends LinearLayout {

  @OnClick({ R.id.item_container, R.id.btn_vote }) public void click(View view) {
    switch (view.getId()) {
      case R.id.item_container: {
        ToastUtils.show("详情");
      }
      break;
      case R.id.btn_vote: {
        ToastUtils.show("投票");
      }
      break;
    }
  }

  public VoteItemCharacter(Context context) {
    super(context);
    init();
  }

  private void init() {
    inflate(getContext(), R.layout.item_vote_character, this);
    ButterKnife.bind(this);
  }
}
