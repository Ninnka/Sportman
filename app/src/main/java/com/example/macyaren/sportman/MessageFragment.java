package com.example.macyaren.sportman;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by hennzr on 2016/2/29.
 */
public class MessageFragment extends Fragment implements View.OnClickListener {

	TextView msgf_tt_left_tv;
	TextView msgf_tt_left_line;
	TextView msgf_tt_right_tv;
	TextView msgf_tt_right_line;
	RelativeLayout msgf_tt_left;
	RelativeLayout msgf_tt_right;
	FragmentManager fragmentManager;
	Msg_fragment_mlist_left msg_fragment_mlist_left;
	Msg_fragment_mlist_right msg_fragment_mlist_right;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View resView = inflater.inflate(R.layout.message_fragment, container, false);
		msgf_tt_left_line = (TextView) resView.findViewById(R.id.msg_fragment_top_tab_left_line);
		msgf_tt_left_tv = (TextView) resView.findViewById(R.id.msg_fragment_top_tab_left_text);
		msgf_tt_right_line = (TextView) resView.findViewById(R.id.msg_fragment_top_tab_right_line);
		msgf_tt_right_tv = (TextView) resView.findViewById(R.id.msg_fragment_top_tab_right_text);
		msgf_tt_left = (RelativeLayout) resView.findViewById(R.id.msg_fragment_top_tab_left);
		msgf_tt_right = (RelativeLayout) resView.findViewById(R.id.msg_fragment_top_tab_right);
		msgf_tt_left.setOnClickListener(this);
		msgf_tt_right.setOnClickListener(this);
		fragmentManager = getChildFragmentManager();
		msg_fragment_mlist_left = new Msg_fragment_mlist_left();
		msg_fragment_mlist_right = new Msg_fragment_mlist_right();
		fragmentManager.beginTransaction().replace(R.id.msg_fragment_mlist,
				msg_fragment_mlist_left).commit();
		return resView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.msg_fragment_top_tab_left:
				msgf_tt_left_tv.setTextColor(getResources().getColor(R.color.md_orange_600));
				msgf_tt_left_line.setVisibility(View.VISIBLE);
				msgf_tt_right_tv.setTextColor(getResources().getColor(R.color.md_grey_600));
				msgf_tt_right_line.setVisibility(View.INVISIBLE);
				fragmentManager.beginTransaction().replace(R.id.msg_fragment_mlist,
						msg_fragment_mlist_left).commit();
				break;
			case R.id.msg_fragment_top_tab_right:
				msgf_tt_right_tv.setTextColor(getResources().getColor(R.color.md_orange_600));
				msgf_tt_right_line.setVisibility(View.VISIBLE);
				msgf_tt_left_tv.setTextColor(getResources().getColor(R.color.md_grey_600));
				msgf_tt_left_line.setVisibility(View.INVISIBLE);
				fragmentManager.beginTransaction().replace(R.id.msg_fragment_mlist,
						msg_fragment_mlist_right).commit();
				break;
		}
	}
}
