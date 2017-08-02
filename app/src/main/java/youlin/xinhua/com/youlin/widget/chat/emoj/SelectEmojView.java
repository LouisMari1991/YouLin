package youlin.xinhua.com.youlin.widget.chat.emoj;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-28 10:51
 * version: 1.0
 * </pre>
 */

public class SelectEmojView extends RelativeLayout {

  private GridView mGridView;
  private EmojAdapter mEmojAdapter = new EmojAdapter(getContext());

  public SelectEmojView(@NonNull Context context) {
    super(context);
    init(context);
  }

  public SelectEmojView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public SelectEmojView(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    inflate(context, R.layout.layout_chatinput_emoj, this);
    this.addOnLayoutChangeListener(new OnLayoutChangeListener() {
      @Override
      public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft,
          int oldTop, int oldRight, int oldBottom) {
        v.removeOnLayoutChangeListener(this);
        setUpGridView();
      }
    });
  }

  private void setUpGridView() {
    mGridView = (GridView) findViewById(R.id.aurora_gridview_emoj);
    mGridView.setVerticalScrollBarEnabled(false);
    mGridView.setFastScrollEnabled(false);
    //mEmojAdapter = new EmojAdapter(getContext());
    mGridView.setAdapter(mEmojAdapter);
    mGridView.setNumColumns(calculateSpanCount());
  }

  private int calculateSpanCount() {
    int avatarSize = getResources().getDimensionPixelSize(R.dimen.emi_icon_size);
    int avatarPadding = getResources().getDimensionPixelSize(R.dimen.spacing);
    return mGridView.getWidth() / (avatarSize + avatarPadding);
  }

  public void setEmojMenuClickListener(EmojMenuClickListener emojMenuClickListener) {
    //mEmojMenuClickListener = emojMenuClickListener;
    mEmojAdapter.setEmojMenuClickListener(emojMenuClickListener);
  }

  public interface EmojMenuClickListener {
    void onExpressionClick(CharSequence emojiText);
  }
}
