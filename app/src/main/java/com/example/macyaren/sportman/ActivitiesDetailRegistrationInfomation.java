package com.example.macyaren.sportman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by hennzr on 2016/3/22.
 */
public class ActivitiesDetailRegistrationInfomation extends Activity implements View
		.OnClickListener {

	public Spinner item_spinner;
	public ImageView registration_information_back;
	public TextView registration_information_submit;
	public TextView registration_information_applytoken;
	public EditText registration_information_texttoken;
	public EditText registration_information_textphonenumber;
	public EditText registration_information_textidentity;
	public Intent intent_to_complete;
	public Random random;

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
		registration_information_applytoken = (TextView) findViewById(R.id
				.activities_detail_registration_information_applytoken);
		registration_information_applytoken.setOnClickListener(this);
		registration_information_texttoken = (EditText) findViewById(R.id
				.activities_detail_registration_information_texttoken);
		registration_information_textphonenumber = (EditText) findViewById(R.id
				.activities_detail_registration_information_textphonenumber);
		registration_information_textidentity = (EditText) findViewById(R.id
				.activities_detail_registration_information_textidentity);

		random = new Random();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.activity_detail_registration_information_return:
				finish();
				break;
			case R.id.activities_detail_registration_information_submit:
				int strlen_iden = registration_information_textidentity.getText().toString()
						.equals("") ? 0 : registration_information_textidentity.getText()
						.toString().length();
				if (strlen_iden == 18) {
					int strlen_pn = registration_information_textphonenumber.getText().toString()
							.equals("") ? 0 : registration_information_textphonenumber.getText()
							.toString().length();
					if (strlen_pn == 11) {
						int strlen_vertifyCode = registration_information_texttoken.getText()
								.toString().equals("") ? 0 : registration_information_texttoken
								.getText().toString().length();
						if (strlen_vertifyCode == 4) {
							intent_to_complete = new Intent();
							intent_to_complete.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
							intent_to_complete.setAction(INTENT_TO_COMPLETE);
							startActivity(intent_to_complete);
						} else {
							Toast.makeText(this, "验证码格式错误", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(this, "电话号码格式错误", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(this, "身份证号码格式错误", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.activities_detail_registration_information_applytoken:
				StringBuilder vertify = new StringBuilder("");
				for (int i = 0; i < 4; i++) {
					int rd = random.nextInt(10);
					vertify.append(String.valueOf(rd));
				}
				registration_information_texttoken.setText(vertify);
				break;
		}
	}
}
