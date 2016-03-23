package com.example.macyaren.sportman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by hennzr on 2016/3/22.
 */
public class ActivitiesDetailRegistrationInfomation extends Activity implements View
		.OnClickListener {

	public Spinner item_spinner;
	public ImageView registration_information_back;
	public TextView registration_information_submit;
	public Intent intent_to_complete;

	public final static String INTENT_TO_COMPLETE = "com.macya.intent.action" +
			".ACTIVITIES_DETAIL_REGISTRATION_COMPLETE";
	public Boolean IS_ITEM_SPINNER_FIRST = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activities_detail_registration_information);
		item_spinner = (Spinner) findViewById(R.id
				.activities_detail_registration_information_itemspinner);
		registration_information_back = (ImageView) findViewById(R.id
				.activity_detail_registration_information_return);
		registration_information_back.setOnClickListener(this);
		registration_information_submit = (TextView) findViewById(R.id
				.activities_detail_registration_information_submit);
		registration_information_submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.activity_detail_registration_information_return:
				finish();
				break;
			case R.id.activities_detail_registration_information_submit:
				intent_to_complete = new Intent();
				intent_to_complete.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				intent_to_complete.setAction(INTENT_TO_COMPLETE);
				startActivity(intent_to_complete);
				break;
		}
	}
}
