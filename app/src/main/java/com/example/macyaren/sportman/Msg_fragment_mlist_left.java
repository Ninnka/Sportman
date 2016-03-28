package com.example.macyaren.sportman;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hennzr on 2016/3/6.
 */
public class Msg_fragment_mlist_left extends Fragment implements AdapterView.OnItemClickListener {

	ListView listView;
	List<MessageFragmentLeftListInfo> list;
	MessageFragmentLeftListAdapter messageFragmentLeftListAdapter;
	MessageFragmentLeftListInfo[] messageFragmentLeftListInfos = new MessageFragmentLeftListInfo[7];
	Intent intent;

	public final static String INTENT_TO_FOLLOW_KEY = "intent_from_follow";
	public final static String INTENT_TO_NEWS_KEY = "intent_from_news";
	public final static String INTENT_TO_FOLLOW_NEWS = "com.macya.intent.action" +
			".Message_Left_List_Follow_News";

	//	MessageFragmentLeftListInfo messageFragmentLeftListInfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		list = new ArrayList<MessageFragmentLeftListInfo>();
		for (int i = 0; i < messageFragmentLeftListInfos.length; i++) {
			messageFragmentLeftListInfos[i] = new MessageFragmentLeftListInfo();
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View resView = inflater.inflate(R.layout.message_fragment_mlist_left, container, false);
		listView = (ListView) resView.findViewById(R.id.message_fragment_mlist_left);
		for (int i = 0; i < messageFragmentLeftListInfos.length; i++) {
			messageFragmentLeftListInfos[i].photo = MessageFragmentLeftListData.PHOTO[i];
			messageFragmentLeftListInfos[i].uname = MessageFragmentLeftListData.NAME[i];
			messageFragmentLeftListInfos[i].profile = MessageFragmentLeftListData.PROFILE[i];
			messageFragmentLeftListInfos[i].date = MessageFragmentLeftListData.DATE[i];
			messageFragmentLeftListInfos[i].type = MessageFragmentLeftListData.TYPE[i];
			list.add(messageFragmentLeftListInfos[i]);
		}

		messageFragmentLeftListAdapter = new MessageFragmentLeftListAdapter(getActivity());
		messageFragmentLeftListAdapter.setList(list);
		listView.setAdapter(messageFragmentLeftListAdapter);
		listView.setOnItemClickListener(this);


		return resView;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//		int realPosition = position + 1;
		//		Toast.makeText(getContext(), "ensure message_fragment_listview's item" + realPosition + " can" +
		//				" be click", Toast.LENGTH_SHORT).show();
		MessageFragmentLeftListInfo messageFragmentLeftListInfo = (MessageFragmentLeftListInfo)
				listView.getAdapter().getItem(position);
		String intent_for = messageFragmentLeftListInfo.type;
		switch (intent_for) {
			case "chat":
				break;
			case "groupchar":
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
