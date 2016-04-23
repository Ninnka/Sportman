package com.example.macyaren.sportman.activities.model;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.macyaren.sportman.R;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivityCitySelectionExpandableListHolderChild;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivityCitySelectionExpandableListHolderGroup;
import com.example.macyaren.sportman.activities.view.ActivityCitySelection;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by hennzr on 2016/3/14 14:19
 * Project name is Sportman
 */
public class ActivityCitySelectionExpandableListAdapter extends BaseExpandableListAdapter {

	LayoutInflater inflater;
	//	Context context;
	public List<String> listGroup;
	public List<List<String>> listChild;
	//	public static int OTHER_SELECTION = 0;
	//	public static int NORMAL_SELECTION = 1;

	public static ActivityCitySelectionExpandableListAdapter
			activityCitySelectionExpandableListAdapter = null;

	public WeakReference<ActivityCitySelection> activityCitySelectionWeakReference;
	public ActivityCitySelection activityCitySelection;

	public void setListGroup(List<String> listGroup) {
		this.listGroup = listGroup;
	}

	public void setListChild(List<List<String>> listChild) {
		this.listChild = listChild;
	}

	public void changeListGroup(List<String> listGroup) {
		Log.i("ZRH","传进来的listGroup长度："+ listGroup.size());
		this.listGroup.clear();
		for (int i = 0; i < listGroup.size(); i++) {
			this.listGroup.add(listGroup.get(i));
//			Log.i("ZRH", "listgroup item" + i + " : " + this.listGroup.get(i));
		}
	}

	public void changeListChild(List<List<String>> listChild) {
		Log.i("ZRH","传进来的listChild长度："+ listChild.size());
		this.listChild.clear();
		for (int i = 0; i < listChild.size(); i++) {
			this.listChild.add(listChild.get(i));
//			Log.i("ZRH", "listchild item" + i + " : " + this.listChild.get(i));
		}
	}

	public ActivityCitySelectionExpandableListAdapter(ActivityCitySelection activityCitySelection) {
		this.activityCitySelectionWeakReference = new WeakReference<>(activityCitySelection);
		this.activityCitySelection = activityCitySelectionWeakReference.get();
		inflater = LayoutInflater.from(this.activityCitySelection);
	}

	public static ActivityCitySelectionExpandableListAdapter getInstance(ActivityCitySelection activityCitySelection) {
		if (activityCitySelectionExpandableListAdapter == null) {
			activityCitySelectionExpandableListAdapter = new
					ActivityCitySelectionExpandableListAdapter(activityCitySelection);
		}
		return activityCitySelectionExpandableListAdapter;
	}

	@Override
	public int getGroupCount() {
		return listGroup.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return listChild.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return listGroup.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return listChild.get(childPosition);
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
		ActivityCitySelectionExpandableListHolderGroup
				activityCitySelectionExpandableListHolderGroup;
		if (convertView == null) {
			activityCitySelectionExpandableListHolderGroup = new
					ActivityCitySelectionExpandableListHolderGroup();
			convertView = inflater.inflate(R.layout.activity_city_selection_expandablelist_group,
					parent, false);
			activityCitySelectionExpandableListHolderGroup.groupName = (TextView) convertView.findViewById(R
					.id.activity_city_selection_expandablelist_group_groupname);
			convertView.setTag(activityCitySelectionExpandableListHolderGroup);
		} else {
			activityCitySelectionExpandableListHolderGroup =
					(ActivityCitySelectionExpandableListHolderGroup) convertView.getTag();
		}
		String gn = listGroup.get(groupPosition);
		switch (gn) {
			case "#":
				gn = "定位城市";
				break;
			case "$":
				gn = "历史城市";
				break;
			case "*":
				gn = "热门城市";
				break;
		}
		activityCitySelectionExpandableListHolderGroup.groupName.setText(gn);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
							 View convertView, ViewGroup parent) {
		ActivityCitySelectionExpandableListHolderChild
				activityCitySelectionExpandableListHolderChild;
		if (convertView == null) {
			activityCitySelectionExpandableListHolderChild = new
					ActivityCitySelectionExpandableListHolderChild();
			convertView = inflater.inflate(R.layout.activity_city_selection_expandablelist_child,
					parent, false);
			activityCitySelectionExpandableListHolderChild.childname = (TextView) convertView.findViewById(R
					.id.activity_city_selection_expandablelist_child_childname);
			convertView.setTag(activityCitySelectionExpandableListHolderChild);
		} else {
			activityCitySelectionExpandableListHolderChild =
					(ActivityCitySelectionExpandableListHolderChild) convertView.getTag();
		}
		String cn = listChild.get(groupPosition).get(childPosition);
		activityCitySelectionExpandableListHolderChild.childname.setText(cn);

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
