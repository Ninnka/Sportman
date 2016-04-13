package com.example.macyaren.sportman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hennzr on 2016/3/6
 * Project name is Sportman
 */
public class MessageFragmentLeft extends Fragment implements AdapterView.OnItemClickListener {

	protected MsgFragmentLeftListCallback msgFragmentLeftListCallback;
	ListView listView;
	List<MessageFragmentLeftListInfo> list;
	MessageFragmentLeftListAdapter messageFragmentLeftListAdapter;
	MessageFragmentLeftListInfo[] messageFragmentLeftListInfos = new MessageFragmentLeftListInfo[7];
	WeakReference<MessageFragment> messageFragmentWeakReference;
	MessageFragment messageFragment;

	public void setMessageFragment(MessageFragment messageFragment) {
		this.messageFragmentWeakReference = new WeakReference<MessageFragment>(messageFragment);
		this.messageFragment = messageFragmentWeakReference.get();
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		list = new ArrayList<>();
		for (int i = 0; i < messageFragmentLeftListInfos.length; i++) {
			messageFragmentLeftListInfos[i] = new MessageFragmentLeftListInfo();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		RefWatcher refWatcher = ExampleApp.getRefWatcher(getActivity());
		refWatcher.watch(this);
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

		messageFragmentLeftListAdapter = new MessageFragmentLeftListAdapter(messageFragment);
		messageFragmentLeftListAdapter.setList(list);
		listView.setAdapter(messageFragmentLeftListAdapter);
		listView.setOnItemClickListener(this);

		return resView;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (msgFragmentLeftListCallback != null) {
			MessageFragmentLeftListInfo messageFragmentLeftListInfo = (MessageFragmentLeftListInfo)
					listView.getAdapter().getItem(position);
			msgFragmentLeftListCallback.listItemClick(messageFragmentLeftListInfo);
		}

	}

	public void setMsgFragmentLeftListCallback(MsgFragmentLeftListCallback msgFragmentLeftListCallback) {
		this.msgFragmentLeftListCallback = msgFragmentLeftListCallback;
	}

	public interface MsgFragmentLeftListCallback {
		void listItemClick(MessageFragmentLeftListInfo messageFragmentLeftListInfo);
	}
}
