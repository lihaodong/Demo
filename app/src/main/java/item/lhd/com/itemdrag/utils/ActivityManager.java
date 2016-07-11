package item.lhd.com.itemdrag.utils;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

public class ActivityManager extends Application {
	private List<Activity> mList = new LinkedList<Activity>();
	private List<Activity> szdwList = new LinkedList<Activity>();
	private List<Activity> editMobileList = new LinkedList<Activity>();
	private List<Activity> checkMobileList = new LinkedList<Activity>();
	private List<Activity> sqxfList = new LinkedList<Activity>();
	private static ActivityManager instance;

	private ActivityManager() {
	}

	public synchronized static ActivityManager getInstance() {
		if (null == instance) {
			instance = new ActivityManager();
		}
		return instance;
	}
	public void addSZDWActivity(Activity activity) {
		szdwList.add(activity);
	}
	public void addSQXFActivity(Activity activity) {
		sqxfList.add(activity);
	}
	public void addActivity(Activity activity) {
		mList.add(activity);
	}
	public void addEditMobileActivity(Activity activity) {
		editMobileList.add(activity);
	}
	public void addCheckMobileActivity(Activity activity) {
		checkMobileList.add(activity);
	}
	public void szdwExit() {
		try {
			for (Activity activity : szdwList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sqxfExit() {
		try {
			for (Activity activity : sqxfList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkExit() {
		try {
			for (Activity activity : checkMobileList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void EditMobileExit() {
		try {
			for (Activity activity : editMobileList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void exit() {
		try {
			for (Activity activity : mList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		System.gc();
	}

}