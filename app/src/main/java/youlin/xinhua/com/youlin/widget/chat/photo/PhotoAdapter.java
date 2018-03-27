//package youlin.xinhua.com.youlin.widget.chat.photo;
//
//import android.animation.Animator;
//import android.animation.AnimatorSet;
//import android.animation.ObjectAnimator;
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import com.bumptech.glide.Glide;
//import java.util.ArrayList;
//import java.util.List;
//import youlin.xinhua.com.youlin.R;
//import youlin.xinhua.com.youlin.listener.OnFileSelectedListener;
//import youlin.xinhua.com.youlin.model.FileItem;
//import youlin.xinhua.com.youlin.widget.CheckableView;
//import youlin.xinhua.com.youlin.widget.chat.chatinput.ChatInputView;
//
//import static android.view.View.GONE;
//import static android.view.View.VISIBLE;
//
//public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
//
//  private Context mContext;
//
//  private List<FileItem> mMedias        = new ArrayList<>();
//  private List<FileItem> mSendFiles     = new ArrayList<>();
//  private List<Integer>  mSelectedItems = new ArrayList<>();
//
//  private OnFileSelectedListener mListener;
//
//  public PhotoAdapter(List<FileItem> list) {
//    if (list != null) {
//      mMedias = list;
//    }
//  }
//
//  public List<FileItem> getSelectedFiles() {
//    return mSendFiles;
//  }
//
//  public void setOnPhotoSelectedListener(OnFileSelectedListener listener) {
//    mListener = listener;
//  }
//
//  public void resetCheckedState() {
//    mSendFiles.clear();
//    mSelectedItems.clear();
//    notifyDataSetChanged();
//  }
//
//  @Override public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//    mContext = parent.getContext();
//    View layout =
//        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_select, parent, false);
//    return new PhotoViewHolder(layout);
//  }
//
//  @Override public void onBindViewHolder(final PhotoViewHolder holder, int position) {
//    if (holder.container.getHeight() != ChatInputView.sMenuHeight) {
//      FrameLayout.LayoutParams layoutParams =
//          new FrameLayout.LayoutParams(ChatInputView.sMenuHeight, ChatInputView.sMenuHeight);
//      holder.container.setLayoutParams(layoutParams);
//      holder.container.requestLayout();
//    }
//
//    FileItem item = mMedias.get(position);
//    Glide.with(mContext)
//        .load(item.getFilePath())
//        .placeholder(R.drawable.aurora_picture_not_found)
//        .into(holder.ivPhoto);
//
//    if (mSelectedItems.contains(position)) {    // Current photo is selected
//      holder.ivTick.setChecked(true);
//      holder.ivShadow.setVisibility(VISIBLE);
//      //addSelectedAnimation(holder.container);
//    } else if (holder.ivTick.getVisibility()
//        == VISIBLE) { // Selected before, now have not been selected
//      holder.ivTick.setChecked(false);
//      holder.ivShadow.setVisibility(GONE);
//      //addDeselectedAnimation(holder.container);
//    }
//
//    final FileItem.Type fileItem = item.getType();
//
//    holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
//      @Override public void onClick(View view) {
//        int p = holder.getAdapterPosition();
//
//        if (/*holder.ivTick.getVisibility() == GONE && */!mSelectedItems.contains(p)) {
//          holder.setIsRecyclable(false);
//
//          mSelectedItems.add(p);
//          mSendFiles.add(mMedias.get(p));
//
//          holder.ivTick.setChecked(true);
//          holder.ivShadow.setVisibility(VISIBLE);
//
//          //addSelectedAnimation(holder.container);
//
//          if (mListener != null) {
//            mListener.onFileSelected();
//          }
//        } else {
//          holder.setIsRecyclable(true);
//
//          mSelectedItems.remove(Integer.valueOf(p));
//          mSendFiles.remove(mMedias.get(p));
//
//          holder.ivTick.setChecked(false);
//          holder.ivShadow.setVisibility(GONE);
//
//          //addDeselectedAnimation(holder.container);
//
//          if (mListener != null) {
//            mListener.onFileDeselected();
//          }
//        }
//      }
//    });
//  }
//
//  @Override public int getItemCount() {
//    return mMedias.size();
//  }
//
//  @Override public int getItemViewType(int position) {
//    return mMedias.get(position).getType().getCode();
//  }
//
//  private void addDeselectedAnimation(View... views) {
//    List<Animator> valueAnimators = new ArrayList<>();
//    for (View v : views) {
//      ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f);
//      ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f);
//
//      valueAnimators.add(scaleX);
//      valueAnimators.add(scaleY);
//    }
//
//    AnimatorSet set = new AnimatorSet();
//    set.playTogether(valueAnimators);
//    set.setDuration(150);
//    set.start();
//  }
//
//  private void addSelectedAnimation(View... views) {
//    List<Animator> valueAnimators = new ArrayList<>();
//    for (View v : views) {
//      ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 0.90f);
//      ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 0.90f);
//
//      valueAnimators.add(scaleX);
//      valueAnimators.add(scaleY);
//    }
//
//    AnimatorSet set = new AnimatorSet();
//    set.playTogether(valueAnimators);
//    set.setDuration(150);
//    set.start();
//  }
//
//  static final class PhotoViewHolder extends RecyclerView.ViewHolder {
//    View          container;
//    ImageView     ivPhoto;
//    ImageView     ivShadow;
//    CheckableView ivTick;
//
//    PhotoViewHolder(View itemView) {
//      super(itemView);
//      container = itemView;
//      ivPhoto = (ImageView) itemView.findViewById(R.id.image_photoselect_photo);
//      ivShadow = (ImageView) itemView.findViewById(R.id.image_photoselect_shadow);
//      ivTick = (CheckableView) itemView.findViewById(R.id.image_photoselect_checkview);
//    }
//  }
//}
