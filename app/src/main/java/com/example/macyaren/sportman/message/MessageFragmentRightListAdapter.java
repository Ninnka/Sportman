package com.example.macyaren.sportman.message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macyaren.sportman.R;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by hennzr on 2016/3/9 23:17
 * Package in com.example.macyaren.sportman
 * Project name is Sportman
 */
public class MessageFragmentRightListAdapter extends BaseExpandableListAdapter {

	List<String> listPY;
	List<List<MessageFragmentRightListInfo>> listInfos;
	MessageFragment messageFragment;
	WeakReference<MessageFragment> messageFragmentWeakReference;
	LayoutInflater inflater;

	public MessageFragmentRightListAdapter(MessageFragment messageFragment) {
		this.messageFragmentWeakReference = new WeakReference<MessageFragment>(messageFragment);
		this.messageFragment = messageFragmentWeakReference.get();
		inflater = LayoutInflater.from(messageFragment.getContext());
	}

	public void setListInfos(List<List<MessageFragmentRightListInfo>> listInfos) {
		this.listInfos = listInfos;
	}

	public void setListPY(List<String> listPY) {

		this.listPY = listPY;
	}

	@Override
	public int getGroupCount() {
		return listPY.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return listInfos.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return listPY.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return listInfos.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		MessageFragmentRightListHolderGroup messageFragmentRightListHolderGroup;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.message_fragment_listview_right_group, null);
			messageFragmentRightListHolderGroup = new MessageFragmentRightListHolderGroup();
			messageFragmentRightListHolderGroup.GROUPNAME = (TextView) convertView.findViewById(R.id
					.message_fragment_listview_right_group_groupname);
			convertView.setTag(messageFragmentRightListHolderGroup);
		} else {
			messageFragmentRightListHolderGroup = (MessageFragmentRightListHolderGroup) convertView.getTag();
		}
		String gn = listPY.get(groupPosition);
		messageFragmentRightListHolderGroup.GROUPNAME.setText(gn);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		MessageFragmentRightListHolderChild messageFragmentRightListHolderChild;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.message_fragment_listview_right_child, null);
			messageFragmentRightListHolderChild = new MessageFragmentRightListHolderChild();
			messageFragmentRightListHolderChild.CHILDPHOTO = (ImageView) convertView.findViewById(R.id
					.message_fragment_listview_right_child_photo);
			messageFragmentRightListHolderChild.CHILDNAME = (TextView) convertView.findViewById(R.id
					.message_fragment_listview_right_child_name);
			convertView.setTag(messageFragmentRightListHolderChild);
		}else {
			messageFragmentRightListHolderChild = (MessageFragmentRightListHolderChild) convertView.getTag();
		}
		List<MessageFragmentRightListInfo> list = listInfos.get(groupPosition);
		MessageFragmentRightListInfo messageFragmentRightListInfo = list.get(childPosition);
		messageFragmentRightListHolderChild.CHILDPHOTO.setImageResource
				(messageFragmentRightListInfo.photo);
		messageFragmentRightListHolderChild.CHILDNAME.setText(messageFragmentRightListInfo.uname);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
