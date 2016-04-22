package com.example.macyaren.sportman.activities.presenter;

import com.example.macyaren.sportman.activities.interator.ActivitiesDetailModelInterator;
import com.example.macyaren.sportman.activities.interator.ActivitiesDetailViewInterator;
import com.example.macyaren.sportman.activities.model.ActivitiesDetailModel;

/**
 * Created by hennzr on 2016/4/14 16:39
 * Package in com.example.macyaren.sportman.activities.presenter
 * Project name is Sportman
 */
public class ActivitiesDetailPresenter {

	ActivitiesDetailViewInterator activitiesDetailViewInterator;
	ActivitiesDetailModelInterator activitiesDetailModelInterator;

	public ActivitiesDetailPresenter(ActivitiesDetailViewInterator activitiesDetailViewInterator) {
		this.activitiesDetailViewInterator = activitiesDetailViewInterator;
		this.activitiesDetailModelInterator = new ActivitiesDetailModel();
	}

	public void getActivities_detail_registration_more() {
		activitiesDetailViewInterator.setActivities_detail_registration_more
				(activitiesDetailModelInterator.getActivities_detail_registration_more());
	}

	public void getActivities_detail_process_more() {
		activitiesDetailViewInterator.setActivities_detail_process_more
				(activitiesDetailModelInterator.getActivities_detail_process_more());
	}

	public void getActivities_detail_comment_more(Boolean flag) {
		if (flag) {
			activitiesDetailModelInterator.getActivities_detail_comment_more();
		} else {
			activitiesDetailModelInterator.resetActivities_detail_comment();
		}
	}
}
