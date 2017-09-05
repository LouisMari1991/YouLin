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
 * time   : 2017-09-05 15:37
 * version: 1.0
 * </pre>
 */

public class VoteItemAtlas extends LinearLayout {

  @OnClick({
      R.id.fl_container_0, R.id.fl_container_1, R.id.btn_vote_0, R.id.btn_vote_1
  }) public void click(View view) {
    switch (view.getId()) {
      case R.id.fl_container_0: {
        ToastUtils.show("fl_container_0");
      }
      break;
      case R.id.fl_container_1: {
        ToastUtils.show("fl_container_1");
      }
      break;
      case R.id.btn_vote_0: {
        ToastUtils.show("btn_vote_0");
      }
      break;
      case R.id.btn_vote_1: {
        ToastUtils.show("btn_vote_1");
      }
      break;
      default:
        break;
    }
  }

  public VoteItemAtlas(Context context) {
    super(context);
    init();
  }

  private void init() {
    inflate(getContext(), R.layout.item_vote_atlas, this);
    ButterKnife.bind(this);
  }
}
