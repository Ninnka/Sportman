package com.example.macyaren.sportman.activities.presenter;

import android.content.Context;

import com.example.macyaren.sportman.activities.interator.ActivitiesFragmentListModelInterator;
import com.example.macyaren.sportman.activities.interator.ActivitiesFragmentListViewInterator;
import com.example.macyaren.sportman.activities.model.ActivitiesFragmentListModel;

/**
 * Created by hennzr on 2016/4/14 15:42
 * Package in com.example.macyaren.sportman.activities.presenter
 * Project name is Sportman
 */
public class ActivitiesFragmentListPresenter {

	public ActivitiesFragmentListViewInterator activitiesFragmentListViewInterator;

	public ActivitiesFragmentListModelInterator activitiesFragmentListModelInterator;

	public ActivitiesFragmentListPresenter(ActivitiesFragmentListViewInterator activitiesFragmentListViewInterator) {
		this.activitiesFragmentListViewInterator = activitiesFragmentListViewInterator;
		this.activitiesFragmentListModelInterator = new ActivitiesFragmentListModel();
	}

	public void getActivitiesFragmentList(Context context){
		activitiesFragmentListModelInterator.getActivitiesFragmentList(context);
	}
}
