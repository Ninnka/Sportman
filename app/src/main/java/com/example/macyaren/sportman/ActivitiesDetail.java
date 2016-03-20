package com.example.macyaren.sportman;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hennzr on 2016/3/19.
 */
public class ActivitiesDetail extends Activity implements ObservableScrollView.Callbacks {

	public Intent intent;
	public ImageView headerView;
	public ObservableScrollView scrollView;
	public Toolbar toolbar;
	public LinearLayout container_signup;
	public ListView commentsListView;
	public TextView commentsMore;
	public List<String> list;
	public ActivitiesDetailCommentsListAdapter activitiesDetailCommentsListAdapter;
	public float distance_to_top;
	public float distance_to_toolbar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activities_detail);

		distance_to_top = Utility.dip2px(this, 240);
		distance_to_toolbar = Utility.dip2px(this, 190);

		toolbar = (Toolbar) findViewById(R.id.activities_detail_toolbar);
		headerView = (ImageView) findViewById(R.id.activities_detail_headerView);
		scrollView = (ObservableScrollView) findViewById(R.id
				.activities_detail_observableScrollView);
		scrollView.setmCallbacks(this);
		scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				onScrollchanged(scrollView.getScrollY());
			}
		});
		container_signup = (LinearLayout) findViewById(R.id
				.activities_detail_content_center_container_signup);
		commentsListView = (ListView) findViewById(R.id
				.activities_detail_content_center_commentslist);
		activitiesDetailCommentsListAdapter = new ActivitiesDetailCommentsListAdapter(this);
		list = new ArrayList<String>();
		for(int i = 0;i<10;i++){
			list.add("item "+i);
		}
		activitiesDetailCommentsListAdapter.setList(list);
		commentsListView.setAdapter(activitiesDetailCommentsListAdapter);
		Utility.setListViewHeightBasedOnChildren(commentsListView);


		commentsMore = (TextView) findViewById(R.id.activities_detail_content_center_commentsmore);

		commentsMore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i=0;i<10;i++){
					list.add("item "+i);
				}
				activitiesDetailCommentsListAdapter.notifyDataSetChanged();
				Utility.setListViewHeightBasedOnChildren(commentsListView);
			}
		});
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	public void onScrollchanged(int t) {
		float translation = 0;
		float alphaFactor = 0;
		int alphaReal = 0;
			if (t < distance_to_toolbar) {
				translation = Math.max(t, distance_to_top);
				if(t>=0){
					alphaFactor = t / distance_to_toolbar;
				}
			} else {
				translation = t + Utility.dip2px(this, 50);
				alphaFactor = 1;
			}
		container_signup.setTranslationY(translation);

		alphaReal = (int) Math.ceil(255 * alphaFactor);
		Log.i("ZRH", "alphaReal: " + alphaReal);
		toolbar.setBackgroundColor(Color.argb(alphaReal, 254, 254, 254));

		int scaleSpeed = (int) (t * 0.5);
		headerView.scrollTo(0, scaleSpeed);
	}

	@Override
	public void onTouchUp() {

	}

	@Override
	public void onTouchDown() {

	}
}
