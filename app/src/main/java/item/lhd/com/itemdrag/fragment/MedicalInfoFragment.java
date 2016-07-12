package item.lhd.com.itemdrag.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import item.lhd.com.itemdrag.R;
import item.lhd.com.itemdrag.adapter.ChannelAdapter;
import item.lhd.com.itemdrag.adapter.NewsFragmentPagerAdapter;
import item.lhd.com.itemdrag.bean.ChannelEntity;
import item.lhd.com.itemdrag.utils.ItemDragHelperCallback;
import item.lhd.com.itemdrag.utils.ResourceUtil;
import item.lhd.com.itemdrag.utils.ScreenSizeUtil;
import item.lhd.com.itemdrag.view.ColumnHorizontalScrollView;
import item.lhd.com.itemdrag.view.LazyViewPager;

public class MedicalInfoFragment extends BaseFragment implements ChannelAdapter.MyItemListener {
    private int mScreenWidth = 0;
    private int columnSelectIndex = 0;
    private ColumnHorizontalScrollView mNaviga_scroll;
    private ImageView iv;
    private LinearLayout ll_tab;
    private LinearLayout mNavigation;
    private RelativeLayout rl1;
    private LazyViewPager mViewpager;
    public static final List<ChannelEntity> items = new ArrayList<>();
    public static final List<ChannelEntity> otherItems = new ArrayList<>();
    private PopupWindow popWindow;
    private boolean flag = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScreenWidth = ScreenSizeUtil.getScreenWidth(getActivity());
        initData();
    }

    private void initData() {
        for (int i = 0; i < 18; i++) {
            ChannelEntity entity = new ChannelEntity();
            entity.setName("频道" + i);
            items.add(entity);
        }
        for (int i = 0; i < 20; i++) {
            ChannelEntity entity = new ChannelEntity();
            entity.setName("其他" + i);
            otherItems.add(entity);
        }
    }

    private void setNavigation() {
        int count = otherItems.size();
        int dp2Px = ScreenSizeUtil.Dp2Px(getActivity(), 20);
        int width = (ScreenSizeUtil.getScreenWidth(getActivity()) - 12 * 10 - 4
                - dp2Px - 19) / 5;
        mNavigation.removeAllViews();
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 12;
            params.rightMargin = 12;
            TextView localTextView = new TextView(getActivity());
            localTextView
                    .setBackgroundResource(R.drawable.selector_navigation_btn);
            localTextView.setGravity(Gravity.CENTER);
            localTextView.setPadding(5, 5, 5, 30);
            localTextView.setId(i);
            localTextView.setText(otherItems.get(i).getName());
            localTextView
                    .setTextColor(ResourceUtil
                            .getColorStateList(R.color.top_category_scroll_text_color_day));
            localTextView.setTextSize(16);
            if (columnSelectIndex == i) {
                localTextView.setSelected(true);
            }
            localTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mNavigation.getChildCount(); i++) {
                        View localView = mNavigation.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else {
                            localView.setSelected(true);
                            mViewpager.setCurrentItem(i / 2);
                        }
                    }
                }
            });
            mNavigation.addView(localTextView, params);
            if (i != count - 1) {
                ImageView imageView = new ImageView(getActivity());
                imageView.setImageDrawable(ResourceUtil
                        .getDrawable(R.drawable.nav_line));
                LinearLayout.LayoutParams split = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mNavigation.addView(imageView, split);
            }
        }
    }


    private void setViewPagerV() {
        mViewpager.setAdapter(new NewsFragmentPagerAdapter(getFragmentManager(), items));
        mViewpager.setOnPageChangeListener(new LazyViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                selectTab(arg0);
            }

        });
    }

    /**
     * 选择的Column里面的Tab
     */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion * 2;
        // for (int i = 0; i < mNavigation.getChildCount(); i++) {
        View checkView = mNavigation.getChildAt(tab_postion * 2);
        int k = checkView.getMeasuredWidth();
        int l = checkView.getLeft();
        int i2 = l + k / 2 - mScreenWidth / 2;
        // rg_nav_content.getParent()).smoothScrollTo(i2, 0);
        mNaviga_scroll.smoothScrollTo(i2, 0);
        // mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
        // mItemWidth , 0);
        // }
        // 判断是否选中
        for (int j = 0; j < mNavigation.getChildCount(); j++) {
            View checkView1 = mNavigation.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion * 2) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView1.setSelected(ischeck);
        }
    }


    private void initView(View view) {
        mNaviga_scroll = (ColumnHorizontalScrollView) view.findViewById(R.id.naviga_scroll);
        mNaviga_scroll = (ColumnHorizontalScrollView) view.findViewById(R.id.naviga_scroll);
        iv = (ImageView) view.findViewById(R.id.iv);
        mNavigation = (LinearLayout) view.findViewById(R.id.naviga_view);
        ll_tab = (LinearLayout) view.findViewById(R.id.ll_tab);
        rl1 = (RelativeLayout) view.findViewById(R.id.rl1);
        mViewpager = (LazyViewPager) view.findViewById(R.id.viewpager);
        iv.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      iv.setImageResource(R.drawable.el_list_up);
                                      mNaviga_scroll.setVisibility(View.GONE);
                                      showPopupWindow(fl_titlebar);
                                  }
                              }

        );
    }

    /**
     * 加载popupWindow
     *
     * @param parent
     */

    private void showPopupWindow(View parent) {
        if (popWindow == null) {
            LayoutInflater inflater = (LayoutInflater) getActivity()
                    .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.activity_demo, null);
            popWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, true);
            initPop(view);
        }
        popWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        popWindow.setFocusable(false);
        popWindow.setOutsideTouchable(false);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popWindow.showAsDropDown(parent, 0, 0);
    }

    private void initPop(View view) {
        RecyclerView mRecy = (RecyclerView) view.findViewById(R.id.recy);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        mRecy.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecy);

        final ChannelAdapter adapter = new ChannelAdapter(this,getActivity(), helper, MedicalInfoFragment.items, MedicalInfoFragment.otherItems);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRecy.setAdapter(adapter);

        adapter.setOnMyChannelItemClickListener(new ChannelAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getActivity(), MedicalInfoFragment.items.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View getContentView() {
        View view = View.inflate(mContext, R.layout.fragment1, null);
        initView(view);
        setViewPagerV();
        setNavigation();
        return view;
    }

    @Override
    public void setPopwindowShow() {
        iv.setImageResource(R.drawable.project_jt);
        mNaviga_scroll.setVisibility(View.VISIBLE);
        if (popWindow != null) {
            popWindow.dismiss();}
    }
}