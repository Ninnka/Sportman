package com.example.macyaren.sportman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hennzr on 2016/3/6.
 */
public class Msg_fragment_mlist_left extends Fragment implements AdapterView.OnItemClickListener{

	ListView listView;
	List<MessageFragmentLeftListInfo> list;
	MessageFragmentLeftListAdapter messageFragmentLeftListAdapter;
	MessageFragmentLeftListInfo[] messageFragmentLeftListInfos = new MessageFragmentLeftListInfo[7];

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
			list.add(messageFragmentLeftListInfos[i]);
		}

//		messageFragmentLeftListInfo = new MessageFragmentLeftListInfo();
//		messageFragmentLeftListInfo.photo = MessageFragmentLeftListData.PHOTO[6];
//		messageFragmentLeftListInfo.uname = MessageFragmentLeftListData.NAME[6];
//		messageFragmentLeftListInfo.profile = MessageFragmentLeftListData.PROFILE[6];
//		messageFragmentLeftListInfo.date = MessageFragmentLeftListData.DATE[6];
//		list.add(messageFragmentLeftListInfo);

		messageFragmentLeftListAdapter = new MessageFragmentLeftListAdapter(getActivity());
		messageFragmentLeftListAdapter.setList(list);
		listView.setAdapter(messageFragmentLeftListAdapter);
		listView.setOnItemClickListener(this);
		return resView;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		int realPosition = position + 1;
		Toast.makeText(getContext(),"ensure message_fragment_listview's item" + realPosition +" can" +
						" be click",
				Toast.LENGTH_SHORT).show();
	}
}
