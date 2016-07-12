package item.lhd.com.itemdrag.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import item.lhd.com.itemdrag.R;

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    private TextView tv_title;
    private TextView tv_right;

    private LinearLayout ll_fragment_content;
    protected View fl_titlebar;
    private RelativeLayout rl_loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_base, null);
        initView(view);
        return view;
    }

    // 初始化View
    private void initView(View view) {
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        ll_fragment_content = (LinearLayout) view
                .findViewById(R.id.ll_fragment_content);
        fl_titlebar = view.findViewById(R.id.fl_fragment_titlebar);

        View child = getContentView();
        // 导入包最好和父类一样
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, -1);
        // 加载子类的页面
        if (child != null) {
            ll_fragment_content.addView(child, params);
        }

    }

    /**
     * 加载子类的布局文件
     *
     * @return
     */
    public abstract View getContentView();

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
        super.setMenuVisibility(menuVisible);
    }
}
