package com.example.macyaren.sportman;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by hennzr on 2016/3/22.
 */
public class ActivitiesDetailRegistrationComplete extends Activity implements View
		.OnClickListener, ObservableScrollView.Callbacks {

	public ImageView registration_complete_back;
	public ImageView registration_complete_closenotification;
	public TextView registration_complete_notificationtext;
	public LinearLayout registration_complete_floatnotification;
	public ObservableScrollView observableScrollView;
	public LinearLayout scrollView_container;
	public LinearLayout toolbar_container;

	public int TOOL_SCROLL;
	public double FINALLY_SCROLLY = 0;
	public double CURR_SCROLLY = 0;
	public static Boolean TOOL_HAS_HIDE = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activities_detail_registration_complete);

		TOOL_SCROLL = Utility.dip2px(this, 50);

		registration_complete_back = (ImageView) findViewById(R.id
				.activity_detail_registration_complete_return);
		registration_complete_back.setOnClickListener(this);
		registration_complete_floatnotification = (LinearLayout) findViewById(R.id
				.activities_detail_registration_complete_floatnotification);
		registration_complete_notificationtext = (TextView) findViewById(R.id
				.activities_detail_registration_complete_notificationtext);
		registration_complete_closenotification = (ImageView) findViewById(R.id
				.activities_detail_registration_complete_closenotification);
		registration_complete_closenotification.setOnClickListener(this);

		observableScrollView = (ObservableScrollView) findViewById(R.id
				.activities_detail_registration_complete_observablescrollview);
		observableScrollView.setmCallbacks(this);
		toolbar_container = (LinearLayout) findViewById(R.id
				.activities_detail_registration_complete_toolbar_container);
		scrollView_container = (LinearLayout) findViewById(R.id
				.activities_detail_registration_complete_observablescrollview_container);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.activity_detail_registration_complete_return:
				finish();
				break;
			case R.id.activities_detail_registration_complete_closenotification:
				registration_complete_notificationtext.setText("");
				registration_complete_floatnotification.setVisibility(View.INVISIBLE);
				break;
		}
	}

	@Override
	public void onScrollchanged(int t) {
//		CURR_SCROLLY = t;
//		if (TOOL_HAS_HIDE) {
//			if (CURR_SCROLLY < FINALLY_SCROLLY) {
//				toolbar_container.scrollTo(0, -TOOL_SCROLL);
//				TOOL_HAS_HIDE = false;
//				scrollView_container.scrollTo(0, -TOOL_SCROLL);
//			}
//		} else {
//			if (CURR_SCROLLY > FINALLY_SCROLLY) {
//				toolbar_container.scrollTo(0, TOOL_SCROLL);
//				TOOL_HAS_HIDE = true;
//				scrollView_container.scrollTo(0, TOOL_SCROLL);
//			}
//		}
//		FINALLY_SCROLLY = CURR_SCROLLY;
	}

	@Override
	public void onTouchUp() {

	}

	@Override
	public void onTouchDown() {

	}
}
