package com.example.macyaren.sportman.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.macyaren.sportman.R;

/**
 * Created by hennzr on 2016/2/29.
 */
public class MeFragment extends Fragment {

	TextView change;
	LinearLayout linearLayoutcontianer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View resView = inflater.inflate(R.layout.me_fragment, container, false);
		linearLayoutcontianer = (LinearLayout) resView.findViewById(R.id.me_fragment_info_container);
		linearLayoutcontianer = (LinearLayout) inflater.inflate(R.layout
				.me_fragment_info_default, linearLayoutcontianer, true);
		change = (TextView) resView.findViewById(R.id.me_fragment_change);
		change.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				linearLayoutcontianer.removeAllViews();
				linearLayoutcontianer = (LinearLayout) inflater.inflate(R.layout.me_fragment_info_haslogin,
						linearLayoutcontianer, true);
			}
		});


		return resView;
	}
}
