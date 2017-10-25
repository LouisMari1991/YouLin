package youlin.xinhua.com.youlin.widget.yunshe.ownercommittee;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.MeasureUtils;

/**
 * <pre>
 * @author : 罗顺翔
 * desc   : Todo
 * time   : 2017-10-24 16:08
 * version: 1.0
 * </pre>
 */

public class CandidateInfoContainer extends LinearLayout {

  int idIndex = 0;

  LayoutInflater inflater;

  CandidateInfoContainerClickListener mCandidateInfoContainerClickListener;

  SparseArray<View> mViewSparseArray = new SparseArray<>();

  public CandidateInfoContainer(Context context) {
    this(context, null);
  }

  public CandidateInfoContainer(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CandidateInfoContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    inflater = LayoutInflater.from(context);

    setOrientation(VERTICAL);

    RelativeLayout addContainer = new RelativeLayout(context);
    addContainer.setBackgroundColor(ActivityCompat.getColor(context, R.color.white));

    ViewGroup.LayoutParams params =
        new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, MeasureUtils.dp2px(44));
    addContainer.setLayoutParams(params);

    LinearLayout childLL = new LinearLayout(context);
    RelativeLayout.LayoutParams childLp =
        new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    childLp.addRule(RelativeLayout.CENTER_IN_PARENT);
    childLL.setLayoutParams(childLp);

    ImageView imageView = new ImageView(context);
    LinearLayout.LayoutParams imgLp =
        new LinearLayout.LayoutParams(MeasureUtils.dp2px(16), MeasureUtils.dp2px(16));
    imgLp.gravity = Gravity.CENTER_VERTICAL;
    imageView.setLayoutParams(imgLp);
    imageView.setImageResource(R.drawable.ic_add_members);

    Space space = new Space(context);
    ViewGroup.LayoutParams spaceLp =
        new ViewGroup.LayoutParams(MeasureUtils.dp2px(5), ViewGroup.LayoutParams.WRAP_CONTENT);
    space.setLayoutParams(spaceLp);

    TextView textView = new TextView(context);
    LinearLayout.LayoutParams textLp =
        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    textLp.gravity = Gravity.CENTER_VERTICAL;
    textView.setLayoutParams(textLp);
    textView.setTextColor(ActivityCompat.getColor(context, R.color.color_blue_text));
    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
    textView.setText("工作履历");

    childLL.addView(imageView);
    childLL.addView(space);
    childLL.addView(textView);

    addContainer.addView(childLL);

    addView(addContainer);

    childLL.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        if (mCandidateInfoContainerClickListener != null) {
          mCandidateInfoContainerClickListener.onAddButtonClick();
        }
      }
    });
  }

  public void addWork() {
    final View view = inflater.inflate(R.layout.item_yunshe_candidate_info, null);
    view.setId(idIndex);
    mViewSparseArray.append(idIndex, view);
    idIndex++;
    View edit = view.findViewById(R.id.fl_edit);
    View delete = view.findViewById(R.id.fl_delete);
    edit.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        if (mCandidateInfoContainerClickListener != null) {
          mCandidateInfoContainerClickListener.onEditButtonClick();
        }
      }
    });
    delete.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        removeView(view);
        if (mCandidateInfoContainerClickListener != null) {
          mCandidateInfoContainerClickListener.onDeleteButtonClick();
        }
      }
    });
    addView(view, 0);
  }

  public void setCandidateInfoContainerClickListener(
      CandidateInfoContainerClickListener candidateInfoContainerClickListener) {
    mCandidateInfoContainerClickListener = candidateInfoContainerClickListener;
  }

  public interface CandidateInfoContainerClickListener {

    void onEditButtonClick();

    void onDeleteButtonClick();

    void onAddButtonClick();
  }
}
