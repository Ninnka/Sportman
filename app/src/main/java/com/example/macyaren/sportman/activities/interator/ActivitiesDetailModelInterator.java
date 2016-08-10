package com.example.macyaren.sportman.activities.interator;

import com.example.macyaren.sportman.activities.model.ActivitiesDetailCommentsListAdapter;

/**
 * Created by hennzr on 2016/4/14 16:12
 * Project name is Sportman
 */
public interface ActivitiesDetailModelInterator {

	String getActivities_detail_registration_more();

	String getActivities_detail_process_more();

	void getActivities_detail_comment_more(ActivitiesDetailCommentsListAdapter activitiesDetailCommentsListAdapter);

	void resetActivities_detail_comment(ActivitiesDetailCommentsListAdapter activitiesDetailCommentsListAdapter);

}
