package com.example.macyaren.sportman;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;


/**
 * Created by hennzr on 2016/3/6 23:12
 * Package in com.example.macyaren.sportman
 * Project name is Sportman
 */
public class MessageFragmentLeftListAdapter extends BaseAdapter {

	List<MessageFragmentLeftListInfo> list;
	WeakReference<MessageFragment> messageFragmentWeakReference;
	MessageFragment messageFragment;
	LayoutInflater inflater;

	public MessageFragmentLeftListAdapter(MessageFragment messageFragment) {
		this.messageFragmentWeakReference = new WeakReference<MessageFragment>(messageFragment);
		this.messageFragment = messageFragmentWeakReference.get();
		inflater = LayoutInflater.from(messageFragment.getContext());
	}

	public void setList(List<MessageFragmentLeftListInfo> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MessageFragmentLeftListHolder holder;
		if (convertView == null) {
			holder = new MessageFragmentLeftListHolder();
			convertView = inflater.inflate(R.layout.message_fragment_listview_left, null);
			holder.photo = (ImageView) convertView.findViewById(R.id.message_fragment_listview_photo);
			holder.uname = (TextView) convertView.findViewById(R.id.message_fragment_listview_name);
			holder.profile = (TextView) convertView.findViewById(R.id
					.message_fragment_listview_profile);
			holder.date = (TextView) convertView.findViewById(R.id.message_fragment_listview_date);
			convertView.setTag(holder);
		} else {
			holder = (MessageFragmentLeftListHolder) convertView.getTag();
		}
		MessageFragmentLeftListInfo messageFragmentLeftListInfo = list.get(position);
		//		Log.i("ZRH FOR msg_fragment_left_list", messageFragmentLeftListInfo.photo +
		//				"  " + messageFragmentLeftListInfo.uname);
		holder.photo.setImageResource(messageFragmentLeftListInfo.photo);
		holder.uname.setText(messageFragmentLeftListInfo.uname);
		holder.profile.setText(messageFragmentLeftListInfo.profile);
		holder.date.setText(messageFragmentLeftListInfo.date);
		return convertView;
	}
}
