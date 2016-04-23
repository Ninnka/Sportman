package com.example.macyaren.sportman.activities.presenter;

import com.example.macyaren.sportman.activities.interator.ActivityCitySelectionModelInterator;
import com.example.macyaren.sportman.activities.interator.ActivityCitySelectionViewInterator;
import com.example.macyaren.sportman.activities.model.ActivityCitySelectionModel;
import com.example.macyaren.sportman.activities.view.ActivityCitySelection;

/**
 * Created by hennzr on 2016/4/23 14:12
 * Project name is Sportman
 */
public class ActivityCitySelectionPresenter {

	public ActivityCitySelectionViewInterator activityCitySelectionViewInterator;
	public ActivityCitySelectionModelInterator activityCitySelectionModelInterator;

	public ActivityCitySelectionPresenter(ActivityCitySelection activityCitySelection,
										  ActivityCitySelectionViewInterator
												   activityCitySelectionViewInterator) {
		this.activityCitySelectionViewInterator = activityCitySelectionViewInterator;
		activityCitySelectionModelInterator = new ActivityCitySelectionModel(activityCitySelection);
	}

	public void setActivityCitySelectionExpandableList(){
		activityCitySelectionModelInterator.setActivityCitySelectionExpandableList();
	}

}
