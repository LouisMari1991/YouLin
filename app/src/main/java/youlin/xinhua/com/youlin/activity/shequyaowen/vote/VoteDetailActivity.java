package youlin.xinhua.com.youlin.activity.shequyaowen.vote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.BindView;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.widget.statusbar.StatusBarHelper;
import youlin.xinhua.com.youlin.widget.vote.VoteItemCharacter;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-05 09:13
 * version: 1.0
 * </pre>
 */

public class VoteDetailActivity extends BaseActivity {

  @BindView(R.id.fl_container) FrameLayout mFLContainer;

  public static void launch(Context context) {
    Intent intent = new Intent(context, VoteDetailActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_vote_detail;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    StatusBarHelper.setColor(this, ContextCompat.getColor(this, R.color.white), 0);

    LinearLayout ll = new LinearLayout(this);
    ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT));
    ll.setOrientation(LinearLayout.VERTICAL);
    LayoutInflater inflater = LayoutInflater.from(this);
    for (int i = 0; i < 10; i++) {
      //VoteAtlasItem item = new VoteAtlasItem(this);
      //View item = inflater.inflate(R.layout.item_vote_character, ll, false);
      VoteItemCharacter item = new VoteItemCharacter(this);
      //VoteItemNormal item = new VoteItemNormal(this);
      ll.addView(item);
    }
    mFLContainer.addView(ll);
  }
}
