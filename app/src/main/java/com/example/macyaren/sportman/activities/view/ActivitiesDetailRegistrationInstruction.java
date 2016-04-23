package com.example.macyaren.sportman.activities.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macyaren.sportman.R;

/**
 * Created by hennzr on 2016/3/22.
 */
public class ActivitiesDetailRegistrationInstruction extends AppCompatActivity implements View.OnClickListener {

	public Intent intent_to_information;
	public CheckBox accept_button;
	public ImageView registration_instruction_back;
	public TextView next_to_information;
	public static final String INTENT_TO_INFORMATION = "com.macya.intent.action" +
			".ACTIVITIES_DETAIL_REGISTRATION_INFORMATION";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activities_detail_registration_instruction);
		accept_button = (CheckBox) findViewById(R.id
				.activities_detail_registration_instruction_radioButton);
		registration_instruction_back = (ImageView) findViewById(R.id
				.activity_detail_registration_instruction_return);
		accept_button.setOnClickListener(this);
		registration_instruction_back.setOnClickListener(this);
		next_to_information = (TextView) findViewById(R.id
				.activity_detail_registration_instruction_next);
		next_to_information.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.activity_detail_registration_instruction_return:
				finish();
				break;
			case R.id.activity_detail_registration_instruction_next:
				if (accept_button.isChecked()) {
					intent_to_information = new Intent();
					intent_to_information.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
					intent_to_information.setAction(INTENT_TO_INFORMATION);
					startActivity(intent_to_information);
				} else {
					Toast.makeText(getApplicationContext(), "please accept protocol", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.activities_detail_registration_instruction_radioButton:
				if (accept_button.isChecked()) {
					next_to_information.setTextColor(Color.argb(255, 251, 128, 65));
				} else {
					next_to_information.setTextColor(Color.argb(255, 189, 189, 189));
				}
		}
	}
}
