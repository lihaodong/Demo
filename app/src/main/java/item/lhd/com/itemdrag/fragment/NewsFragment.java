package item.lhd.com.itemdrag.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import item.lhd.com.itemdrag.R;
import item.lhd.com.itemdrag.channel.ChannelActivity;

public class NewsFragment  extends BaseFragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment3, null);
		Button b= (Button) view.findViewById(R.id.button);
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
					Intent inent=new Intent(getActivity(), ChannelActivity.class);
				startActivity(inent);

			}
		});
		return view;
	}

	@Override
	public View getContentView() {
		return null;
	}
}