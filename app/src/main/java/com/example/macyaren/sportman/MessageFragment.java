package com.example.macyaren.sportman;

import android.content.Intent;
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
 * Created by hennzr on 2016/2/29 14:20
 * Package in com.example.macyaren.sportman
 * Project name is Sportman
 */
public class MessageFragment extends Fragment implements View.OnClickListener,
		Msg_fragment_mlist_left.MsgFragmentLeftListCallback {

	TextView msgf_tt_left_tv;
	TextView msgf_tt_left_line;
	TextView msgf_tt_right_tv;
	TextView msgf_tt_right_line;
	RelativeLayout msgf_tt_left;
	RelativeLayout msgf_tt_right;
	FragmentManager fragmentManager;
	Msg_fragment_mlist_left msg_fragment_mlist_left;
	Msg_fragment_mlist_right msg_fragment_mlist_right;
	Intent intent;

	public final static String INTENT_TO_FOLLOW_KEY = "intent_from_follow";
	public final static String INTENT_TO_NEWS_KEY = "intent_from_news";
	public final static String INTENT_TO_FOLLOW_NEWS = "com.macya.intent.action" +
			".Message_Left_List_Follow_News";

	public final static String INTENT_TO_CHAT_SINGLE_GROUP = "com.macya.intent.action" +
			".MessageLeftListChat_Single_Group";

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
		msg_fragment_mlist_left.setMsgFragmentLeftListCallback(this);
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

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void listItemClick(MessageFragmentLeftListInfo messageFragmentLeftListInfo) {
		String intent_for = messageFragmentLeftListInfo.type;
		switch (intent_for) {
			case "chat":
			case "groupchar":
				intent = new Intent();
				intent.setAction(INTENT_TO_CHAT_SINGLE_GROUP);
				startActivity(intent);
				break;
			case "news":
				intent = new Intent();
				intent.putExtra("data", INTENT_TO_NEWS_KEY);
				intent.setAction(INTENT_TO_FOLLOW_NEWS);
				startActivity(intent);
				break;
			case "follow":
				intent = new Intent();
				intent.putExtra("data", INTENT_TO_FOLLOW_KEY);
				intent.setAction(INTENT_TO_FOLLOW_NEWS);
				startActivity(intent);
				break;
		}
	}
}
