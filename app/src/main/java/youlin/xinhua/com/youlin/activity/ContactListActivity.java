//package youlin.xinhua.com.youlin.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import butterknife.BindView;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.UUID;
//import youlin.xinhua.com.youlin.base.BaseActivity;
//import youlin.xinhua.com.youlin.R;
//import youlin.xinhua.com.youlin.adapter.ContactAdapter;
//import youlin.xinhua.com.youlin.bean.UserInfo;
//import youlin.xinhua.com.youlin.utils.EaseCommonUtils;
//import youlin.xinhua.com.youlin.utils.LogUtils;
//import youlin.xinhua.com.youlin.widget.SuspensionDecoration;
//
///**
// * <pre>
// * desc   : Todo
// * author : 罗顺翔
// * time   : 2017-07-20 10:23
// * version: 1.0
// * </pre>
// */
//
//public class ContactListActivity extends BaseActivity {
//
//  public static void lunch(Context context) {
//    Intent intent = new Intent(context, ContactListActivity.class);
//    context.startActivity(intent);
//  }
//
//  private SuspensionDecoration mSuspensionDecoration;
//
//  @BindView(R.id.rv_list) RecyclerView mRecyclerView;
//  //@BindView(R.id.indexBar) IndexBar mIndexBar;
//
//  @Override protected int attachLayoutRes() {
//    return R.layout.activity_contact_list;
//  }
//
//  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//
//    List<UserInfo> list = new ArrayList<>();
//    for (int i = 0; i < 20; i++) {
//      UserInfo userInfo = new UserInfo(UUID.randomUUID().toString());
//      //userInfo.setNickname(UUID.randomUUID().toString());
//      EaseCommonUtils.setUserInitialLetter(userInfo);
//      LogUtils.i(" userInfo , str : " + userInfo.getInitialLetter());
//      list.add(userInfo);
//    }
//    // sorting
//    Collections.sort(list, new Comparator<UserInfo>() {
//
//      @Override
//      public int compare(UserInfo lhs, UserInfo rhs) {
//        if(lhs.getInitialLetter().equals(rhs.getInitialLetter())){
//          return lhs.getNickname().compareTo(rhs.getNickname());
//        }else{
//          if("#".equals(lhs.getInitialLetter())){
//            return 1;
//          }else if("#".equals(rhs.getInitialLetter())){
//            return -1;
//          }
//          return lhs.getInitialLetter().compareTo(rhs.getInitialLetter());
//        }
//      }
//    });
//
//    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//
//    mSuspensionDecoration = new SuspensionDecoration(this, list);
//    //mRecyclerView.setLayoutManager(linearLayoutManager);
//    ContactAdapter adapter = new ContactAdapter(this, list);
//    //mRecyclerView.setAdapter(adapter);
//    //mRecyclerView.addItemDecoration(mSuspensionDecoration);
//    ////adapter.notifyDataSetChanged();
//    //mIndexBar//.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
//    //    //.setNeedRealIndex(true)//设置需要真实的索引
//    //    .setmLayoutManager(linearLayoutManager)//设置RecyclerView的LayoutManager
//    //    .setmSourceDatas(list)//设置数据
//    //    .invalidate();
//
//  }
//
//
//
//}
