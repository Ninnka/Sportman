package com.example.macyaren.sportman.activities.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.macyaren.sportman.R;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivityCitySelectionExpandableListHolderChild;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivityCitySelectionExpandableListHolderGroup;

import java.util.List;

/**
 * Created by hennzr on 2016/3/14.
 */
public class ActivityCitySelectionExpandableListAdapter extends BaseExpandableListAdapter {

	LayoutInflater inflater;
	Context context;
	List<String> listGroup;
	List<List<String>> listChild;
	public static int OTHER_SELECTION = 0;
	public static int NORMAL_SELECTION = 1;

	public void setListGroup(List<String> listGroup) {
		this.listGroup = listGroup;
	}

	public void setListChild(List<List<String>> listChild) {
		this.listChild = listChild;
	}

	public ActivityCitySelectionExpandableListAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);

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
					null);
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
					null);
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

//		if (groupPosition < 3) {
//			ActivityCitySelectionExpandableListHolderChild_Other
//					activityCitySelectionExpandableListHolderChild_other;
//			List<String> listTemp = listChild.get(groupPosition);
//			int gridrow = (int) Math.ceil(listTemp.size() / 4);
//			//				int gridcol = 4;
//			//			if (convertView == null) {
//			activityCitySelectionExpandableListHolderChild_other = new
//					ActivityCitySelectionExpandableListHolderChild_Other();
//			convertView = inflater.inflate(R.layout
//					.activity_city_selection_expandablelist_child_other, parent, false);
//			activityCitySelectionExpandableListHolderChild_other.gridLayout = (GridLayout)
//					convertView.findViewById(R.id
//							.activity_city_selection_expandablelist_child_other_container);
//			activityCitySelectionExpandableListHolderChild_other.gridLayout.setRowCount
//					(gridrow);
//			for (int i = 0; i < listTemp.size(); i++) {
//				TextView textView = new TextView(context);
//				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
//						(Utility.dip2px(context, 60), Utility.dip2px(context, 40));
//				layoutParams.setMargins(Utility.dip2px(context, 10), Utility.dip2px(context,
//						5), Utility.dip2px(context, 10), Utility.dip2px(context, 5));
//				textView.setLayoutParams(layoutParams);
//				textView.setText(listTemp.get(i));
//				textView.setGravity(Gravity.CENTER);
//				textView.setBackgroundColor(context.getResources().getColor(R.color
//						.md_white_1000));
//				textView.setPadding(Utility.dip2px(context, 6), Utility.dip2px(context,
//						3), Utility.dip2px(context, 6), Utility.dip2px(context, 3));
//				activityCitySelectionExpandableListHolderChild_other.gridLayout.addView
//						(textView);
//			}
//
//		} else {
//			ActivityCitySelectionExpandableListHolderChild
//					activityCitySelectionExpandableListHolderChild;
//			//			if (convertView == null) {
//			activityCitySelectionExpandableListHolderChild = new
//					ActivityCitySelectionExpandableListHolderChild();
//			convertView = inflater.inflate(R.layout
//					.activity_city_selection_expandablelist_child, null);
//			activityCitySelectionExpandableListHolderChild.childname = (TextView) convertView
//					.findViewById(R.id.activity_city_selection_expandablelist_child_childname);
//			String cn = listChild.get(groupPosition).get(childPosition);
//			activityCitySelectionExpandableListHolderChild.childname.setText(cn);
//		}
//		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
