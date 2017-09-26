package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.bean.HuoDongItemBean;
import youlin.xinhua.com.youlin.utils.ImageLoader;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-21 17:56
 * version: 1.0
 * </pre>
 */

public class AvatarNameContainer extends LinearLayout {

  private int count = -1;
  private LayoutInflater mLayoutInflater;

  public AvatarNameContainer(Context context) {
    this(context, null);
  }

  public AvatarNameContainer(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public AvatarNameContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setOrientation(HORIZONTAL);
    mLayoutInflater = LayoutInflater.from(getContext());
    count = calculateSpanCount();
  }

  public void initView(List<HuoDongItemBean.Applicant> applicantList) {
    if (applicantList.size() > count) {
      applicantList.subList(0, count);
    }
    for (int i = 0; i < applicantList.size(); i++) {
      HuoDongItemBean.Applicant applicant = applicantList.get(i);
      View view = mLayoutInflater.inflate(R.layout.item_yunshe_act_detail_grid, this, false);
      ImageView avatar = (ImageView) view.findViewById(R.id.img_view);
      TextView name = (TextView) view.findViewById(R.id.text_view);
      ImageLoader.displayAvatar(avatar, applicant.getApplicantMemo());
      name.setText(applicant.getApplicantName());
      addView(view);
    }
  }

  private int calculateSpanCount() {
    int avatarSize = getResources().getDimensionPixelSize(R.dimen.avatar_size);
    int numColumns = getWidth() / avatarSize;
    return numColumns;
  }
}
