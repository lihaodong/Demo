package item.lhd.com.itemdrag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import item.lhd.com.itemdrag.fragment.FindingFragment;
import item.lhd.com.itemdrag.fragment.MedicalInfoFragment;
import item.lhd.com.itemdrag.fragment.MyFragment;
import item.lhd.com.itemdrag.utils.ActivityManager;

/**
 * Created by Administrator on 2016/7/8.
 */
public class HomeActivity extends FragmentActivity {
    private long waitTime = 2000;
    private long touchTime = 0;
    private RadioGroup radioGroup;
    protected int index;
    private FrameLayout fl_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ActivityManager.getInstance().addActivity(this);
    }
    private void init() {
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedid) {
                switch (checkedid) {
                    case R.id.radioNews:
                        index=0;
                        break;
                    case R.id.radioLearn:
                        index=1;
                        break;
//				case R.id.radioFind:
//					index=2;
//					break;
                    case R.id.radioMy:
                        index=3;
                        break;
                }
                // 调用数据适配器中的getitem方法
                Fragment fragment = (Fragment) fspAdapter.instantiateItem(
                        fl_content, index);
                // 通过索引生成的fragment去替换制定的一个帧布局
                fspAdapter.setPrimaryItem(fl_content, 0, fragment);
                // 提交事务
                fspAdapter.finishUpdate(fl_content);
            }

        });
        radioGroup.check(R.id.radioNews);
    }
    FragmentStatePagerAdapter fspAdapter = new FragmentStatePagerAdapter(
            getSupportFragmentManager()) {
        public int getCount() {
            return 2;
        }

        public Fragment getItem(int index) {
            Fragment fragment = null;
            switch (index) {
                case 0:
                    fragment = new MedicalInfoFragment();
                    break;
                case 1:
                    fragment = new FindingFragment();
                    break;
//			case 2:
//				fragment = new FindingFragment();
//				break;
                case 3:
                    fragment = new MyFragment();
                    break;
            }
            return fragment;
        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && KeyEvent.KEYCODE_BACK == keyCode) {
                long currentTime = System.currentTimeMillis();
                if ((currentTime - touchTime) >= waitTime) {
                    Toast.makeText(HomeActivity.this, "再按一次退出",
                            Toast.LENGTH_SHORT).show();
                    touchTime = currentTime;
                } else {
                    ActivityManager.getInstance().exit();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
