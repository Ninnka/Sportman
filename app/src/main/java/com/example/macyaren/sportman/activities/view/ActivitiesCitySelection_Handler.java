package com.example.macyaren.sportman.activities.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.macyaren.sportman.main.view.MainActivity;

import java.lang.ref.WeakReference;

/**
 * Created by hennzr on 2016/4/23 16:39
 * Project name is Sportman
 */
public class ActivitiesCitySelection_Handler extends Handler {

	WeakReference<ActivityCitySelection> activityCitySelectionWeakReference;
	ActivityCitySelection activityCitySelection;

	public ActivitiesCitySelection_Handler(ActivityCitySelection activityCitySelection) {
		activityCitySelectionWeakReference = new WeakReference<>
				(activityCitySelection);
		this.activityCitySelection = activityCitySelectionWeakReference.get();
	}

	@Override
	public void handleMessage(Message msg) {
		if (msg.what == ActivityCitySelection.LOADING_CITY_NAME) {
			//				activityCitySelection.activityCitySelectionExpandableListAdapter.setListGroup
			//						(activityCitySelection.listGroup);
			//				activityCitySelection.activityCitySelectionExpandableListAdapter.setListChild
			//						(activityCitySelection.listChild);
			//				activityCitySelection.expandableListView.setAdapter
			//						(activityCitySelection.activityCitySelectionExpandableListAdapter);
			for (int i = 0; i < activityCitySelection
					.activityCitySelectionExpandableListAdapter.getGroupCount(); i++) {
				activityCitySelection.expandableListView.expandGroup(i);
			}
			activityCitySelection.expandableListView.setVisibility(View.VISIBLE);
			activityCitySelection.expandableListView.setGroupIndicator(null);
			activityCitySelection.expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
				@Override
				public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
					return true;
				}
			});
			activityCitySelection.expandableListView.setOnChildClickListener(new ExpandableListView
					.OnChildClickListener() {
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
											int groupPosition, int childPosition, long id) {
					Intent intent = activityCitySelection.getIntent();
					intent.putExtra("city", activityCitySelection
							.activityCitySelectionExpandableListAdapter.listChild.get
							(groupPosition).get(childPosition));
					activityCitySelection.setResult(MainActivity.CITY_SELECTION_TRANCATIONCODE,
							intent);
					activityCitySelection.finish();
					return true;
				}
			});
		}
	}
}
