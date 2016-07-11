package item.lhd.com.itemdrag;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import item.lhd.com.itemdrag.bean.ChannelEntity;

public class AppApplication extends Application {
	private static AppApplication mAppApplication;
	public static final List<ChannelEntity> items = new ArrayList<>();
	public static final List<ChannelEntity> otherItems = new ArrayList<>();
	@Override
	public void onCreate() {
		super.onCreate();
		mAppApplication = this;
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
	/** 获取Application */
	public static AppApplication getApp() {
		return mAppApplication;
	}
}
