package item.lhd.com.itemdrag.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import item.lhd.com.itemdrag.R;

public class NewsFragment  extends BaseFragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		String content = getArguments().getString("content");
		View view = inflater.inflate(R.layout.fragment3, null);
		TextView tv= (TextView) view.findViewById(R.id.tv);
		tv.setText(content);
//		b.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//					Intent inent=new Intent(getActivity(), ChannelActivity.class);
//				startActivity(inent);
//
//			}
//		});
		return view;
	}

	@Override
	public View getContentView() {
		return null;
	}
}