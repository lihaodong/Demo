package item.lhd.com.itemdrag.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import item.lhd.com.itemdrag.R;
import item.lhd.com.itemdrag.adapter.NewsFragmentPagerAdapter;
import item.lhd.com.itemdrag.bean.ChannelEntity;
import item.lhd.com.itemdrag.utils.ResourceUtil;
import item.lhd.com.itemdrag.utils.ScreenSizeUtil;
import item.lhd.com.itemdrag.view.ColumnHorizontalScrollView;
import item.lhd.com.itemdrag.view.LazyViewPager;

public class MedicalInfoFragment extends BaseFragment {
    private int mScreenWidth = 0;
    private int columnSelectIndex = 0;
    private ColumnHorizontalScrollView mNaviga_scroll;
    private LinearLayout mNavigation;
    private LazyViewPager mViewpager;
    public static final List<ChannelEntity> items = new ArrayList<>();
    public static final List<ChannelEntity> otherItems = new ArrayList<>();
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
        mViewpager.setAdapter(new NewsFragmentPagerAdapter(getFragmentManager(),items));
        mViewpager.setOnPageChangeListener(new LazyViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                selectTab(arg0);
            }

        });
    }
    /**
     * 选择的Column里面的Tab
     * */
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
        mNaviga_scroll = (ColumnHorizontalScrollView)view.findViewById(R.id.naviga_scroll);
        mNavigation = (LinearLayout) view.findViewById(R.id.naviga_view);
        mViewpager = (LazyViewPager) view.findViewById(R.id.viewpager);
    }

        @Override
        public View getContentView() {
            View view = View.inflate(mContext, R.layout.fragment1, null);
            initView(view);
            setViewPagerV();
            setNavigation();
            return view;
        }
}