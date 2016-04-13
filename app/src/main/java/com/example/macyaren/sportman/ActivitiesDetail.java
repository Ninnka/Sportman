package com.example.macyaren.sportman;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hennzr on 2016/3/19 11:22
 * Package in com.example.macyaren.sportman
 * Project name is Sportman
 */
public class ActivitiesDetail extends Activity implements ObservableScrollView.Callbacks,
		View.OnClickListener {

	public ImageView headerView;
	public ObservableScrollView scrollView;
	public Toolbar toolbar;
	public LinearLayout container_registration;
	public ListView commentsListView;
	public TextView commentsMore;
	public TextView commentsCollapse;
	public TextView registrationdetailMore;
	public TextView processMore;
	public TextView sample;
	public TextView process_sample;
	public LinearLayout process_container;
	public LinearLayout registrationdetail_container;
	public TextView process_expand_tv;
	public TextView registrationdetail_expanded_tv;
	public List<ActivitiesDetailCommentsListCommInfo> list;
	public ImageView detail_return;
	public TextView registration_button_tv;

	public View cachedView;

	public float distance_to_top;
	public float distance_to_toolbar;

	public boolean hasExpanded_registrationdetail = false;
	public boolean hasExpanded_process = false;

	public final static int EXPAND_REGIS = 0;
	public final static int COLLAPSE_REGIS = 1;
	public final static int EXPAND_PRO = 2;
	public final static int COLLAPSE_PRO = 3;

	public final static String INTENT_TO_NEXT = "com.macya.intent.action.ACTIVITIES_DETAIL_REGISTRATION_INSTRUCTION";

	public ActivitiesDetailCommentsListAdapter activitiesDetailCommentsListAdapter;
	public ActivitiesDetailCommentsListCommData activitiesDetailCommentsListCommData;
	public ActivitiesDetailCommentsListCommInfo[] activitiesDetailCommentsListCommInfo;

	public Intent intent_to_next;

	AD_Handler handler = new AD_Handler(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activities_detail);

		cachedView = new View(this);

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
		container_registration = (LinearLayout) findViewById(R.id
				.activities_detail_content_center_container_registration);
		commentsListView = (ListView) findViewById(R.id
				.activities_detail_content_center_commentslist);
		activitiesDetailCommentsListAdapter = new ActivitiesDetailCommentsListAdapter(this);
		list = new ArrayList<>();
		activitiesDetailCommentsListCommInfo = new ActivitiesDetailCommentsListCommInfo[2];
		activitiesDetailCommentsListCommData = new ActivitiesDetailCommentsListCommData();
		for (int i = 0; i < 2; i++) {
			activitiesDetailCommentsListCommInfo[i] = new ActivitiesDetailCommentsListCommInfo();
			activitiesDetailCommentsListCommInfo[i].photo = activitiesDetailCommentsListCommData
					.PHOTOS[i];
			activitiesDetailCommentsListCommInfo[i].uname = activitiesDetailCommentsListCommData
					.UNAMES[i];
			activitiesDetailCommentsListCommInfo[i].comment =
					activitiesDetailCommentsListCommData.COMMENTS[i];
			activitiesDetailCommentsListCommInfo[i].praise = activitiesDetailCommentsListCommData
					.PRAISES[i];
			activitiesDetailCommentsListCommInfo[i].date = activitiesDetailCommentsListCommData
					.DATES[i];
		}
		activitiesDetailCommentsListAdapter.setList(list);
		commentsListView.setAdapter(activitiesDetailCommentsListAdapter);
		Utility.setListViewHeightBasedOnChildren(commentsListView);

		process_container = (LinearLayout) findViewById(R.id
				.activities_detail_content_center_process);
		registrationdetail_container = (LinearLayout) findViewById(R.id
				.activities_detail_content_center_registrationdetail);

		registration_button_tv = (TextView) findViewById(R.id
				.activities_detail_content_center_button_registration);
		registration_button_tv.setOnClickListener(this);

		commentsMore = (TextView) findViewById(R.id
				.activities_detail_content_center_comments_clicktoviewmore);
		commentsMore.setOnClickListener(this);
		commentsCollapse = (TextView) findViewById(R.id
				.activities_detail_content_center_comments_cllapseview);
		commentsCollapse.setOnClickListener(this);
		registrationdetailMore = (TextView) findViewById(R.id
				.activities_detail_content_center_registrationdetail_clicktoviewmore);
		registrationdetailMore.setOnClickListener(this);
		processMore = (TextView) findViewById(R.id
				.activities_detail_content_center_process_clicktoviewmore);
		processMore.setOnClickListener(this);

		detail_return = (ImageView) findViewById(R.id.activity_detail_return);
		detail_return.setOnClickListener(this);

		sample = (TextView) findViewById(R.id.detail_sample);
		process_sample = (TextView) findViewById(R.id
				.activities_detail_content_center_process_sample_tv);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		headerView = null;
//		scrollView = null;
//		toolbar = null;
//		container_registration = null;
//		commentsListView = null;
//		commentsCollapse = null;
//		commentsMore = null;
//		processMore = null;
//		registrationdetailMore = null;
//		sample = null;
//		process_sample = null;
//		process_container = null;
//		registrationdetail_container = null;
//		process_expand_tv = null;
//		registrationdetail_expanded_tv = null;
//		list = null;
//		detail_return = null;
//		registration_button_tv = null;
//		cachedView = null;
//		activitiesDetailCommentsListAdapter = null;
//		activitiesDetailCommentsListCommData = null;
//		activitiesDetailCommentsListCommInfo = null;
//		handler = null;
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
			if (t >= 0) {
				alphaFactor = t / distance_to_toolbar;
			}
		} else {
			translation = t + Utility.dip2px(this, 50);
			alphaFactor = 1;
		}
		container_registration.setTranslationY(translation);

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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.activities_detail_content_center_comments_clicktoviewmore:
				int size = list.size();
				if (size == 0) {
					commentsCollapse.setText("Collapse view <<");
				}
				if (size < 10) {
					for (int i = 0; i < 2; i++) {
						list.add(activitiesDetailCommentsListCommInfo[i]);
					}
					activitiesDetailCommentsListAdapter.notifyDataSetChanged();
					Utility.setListViewHeightBasedOnChildren(commentsListView);
				} else {
					commentsMore.setText("最后一页");
				}
				break;
			case R.id.activities_detail_content_center_comments_cllapseview:
				if (!commentsCollapse.getText().toString().equals("")) {
					list.clear();
					activitiesDetailCommentsListAdapter.notifyDataSetChanged();
					Utility.setListViewHeightBasedOnChildren(commentsListView);
					commentsCollapse.setText("");
					if (commentsMore.getText().toString().equals("最后一页")) {
						commentsMore.setText("Click to view more >>");
					}
				}
				break;
			case R.id.activities_detail_content_center_process_clicktoviewmore:
				Message message_process = new Message();
				if (hasExpanded_process) {
					message_process.what = COLLAPSE_PRO;
					handler.sendMessage(message_process);
				} else {
					message_process.what = EXPAND_PRO;
					handler.sendMessage(message_process);
				}
				break;
			case R.id.activities_detail_content_center_registrationdetail_clicktoviewmore:
				Message message_registrationdetail = new Message();
				if (hasExpanded_registrationdetail) {
					message_registrationdetail.what = COLLAPSE_REGIS;
					handler.sendMessage(message_registrationdetail);
				} else {
					message_registrationdetail.what = EXPAND_REGIS;
					handler.sendMessage(message_registrationdetail);
				}
				break;
			case R.id.activity_detail_return:
				finish();
				break;
			case R.id.activities_detail_content_center_button_registration:
				intent_to_next = new Intent();
				intent_to_next.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				intent_to_next.setAction(INTENT_TO_NEXT);
				startActivity(intent_to_next);
				break;
		}
	}

	static class AD_Handler extends Handler {

		ActivitiesDetail activitiesDetail;
		WeakReference<ActivitiesDetail> activitiesDetailWeakReference;

		public AD_Handler(ActivitiesDetail activitiesDetail) {
			this.activitiesDetailWeakReference = new WeakReference<ActivitiesDetail>
					(activitiesDetail);
			this.activitiesDetail = activitiesDetailWeakReference.get();
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case EXPAND_REGIS:
					if (activitiesDetail.cachedView.getTag(R.id.EXPAND_REGIS) == null) {
						activitiesDetail.registrationdetail_expanded_tv = new TextView(activitiesDetail);
						activitiesDetail.registrationdetail_expanded_tv.setLayoutParams
								(activitiesDetail.sample.getLayoutParams());
						activitiesDetail.registrationdetail_expanded_tv.setGravity(Gravity.CENTER | Gravity.LEFT);
						activitiesDetail.registrationdetail_expanded_tv.setPadding(Utility.dip2px(activitiesDetail, 20), 0, 0, 0);
						activitiesDetail.registrationdetail_expanded_tv.setTextSize(14);
						activitiesDetail.registrationdetail_expanded_tv.setText("登记材料：身份证明，联系方式等");
						activitiesDetail.cachedView.setTag(R.id.EXPAND_REGIS, activitiesDetail.registrationdetail_expanded_tv);
					} else {
						activitiesDetail.registrationdetail_expanded_tv = (TextView) activitiesDetail.cachedView.getTag(R.id
								.EXPAND_REGIS);
					}
					activitiesDetail.registrationdetail_container.addView(activitiesDetail.registrationdetail_expanded_tv);
					activitiesDetail.registrationdetail_container.removeView(activitiesDetail.registrationdetailMore);
					activitiesDetail.registrationdetailMore.setText("Collapse view <<");
					activitiesDetail.registrationdetail_container.addView(activitiesDetail.registrationdetailMore);
					activitiesDetail.hasExpanded_registrationdetail = true;
					break;
				case COLLAPSE_REGIS:
					activitiesDetail.registrationdetail_container.removeView(activitiesDetail.registrationdetailMore);
					activitiesDetail.registrationdetailMore.setText("Click to view more >>");
					activitiesDetail.registrationdetail_container.removeView(activitiesDetail.registrationdetail_expanded_tv);
					activitiesDetail.registrationdetail_container.addView(activitiesDetail.registrationdetailMore);
					activitiesDetail.hasExpanded_registrationdetail = false;
					break;
				case EXPAND_PRO:
					if (activitiesDetail.cachedView.getTag(R.id.EXPAND_PRO) == null) {
						activitiesDetail.process_expand_tv = new TextView(activitiesDetail);
						activitiesDetail.process_expand_tv.setLayoutParams(activitiesDetail.process_sample.getLayoutParams());
						activitiesDetail.process_expand_tv.setPadding(Utility.dip2px(activitiesDetail, 20), 0,
								0, 0);
						activitiesDetail.process_expand_tv.setGravity(Gravity.CENTER | Gravity.LEFT);
						activitiesDetail.process_expand_tv.setSingleLine(false);
						//						process_expand_tv.setMaxLines(30);
						//						process_expand_tv.setEllipsize(TextUtils.TruncateAt.END);
						activitiesDetail.process_expand_tv.setTextSize(14);
						activitiesDetail.process_expand_tv.setText("II. Race Course \n(I) Mini Marathon: \n" +
								"Huacheng Square (Starting Point) → Linjiang Ave (Eastwards) →" +
								" Tunnel →Linjiang Ave (Westwards) → Guangzhou Middle Ave.→ Tianhe " +
								"North Rd. → North Gate, Tianhe Sports Center → Ring Road of " +
								"Tianhe Stadium →South Gate Square, Tianhe Sports Center " +
								"(Finishing Point) \n(II) Half Marathon \n" +
								"Huacheng Square (Starting point) → Linjiang Ave (Eastwards) →" +
								" U turn at Chebei South Rd. → Linjiang Ave. (Westwards) →" +
								" Liede Ave → U turn at the top of Huacheng Ave Tunnel. →" +
								" Liede Bridge →Yuejiang Rd. (Eastwards) → Intersection of " +
								"Yuejiang Rd. and Huizhan Middle Rd. → Yuejiang Rd " +
								"(Reserve Direction) → U turn at Yuejiang Rd (under Pazhou Bridge)" +
								" → Yuejiang Middle Rd (North to the Poly International Plaza, " +
								"Finishing Point) \n(III) Marathon: \n" +
								"Huacheng Square (Starting point) → Linjiang Ave (Eastwards) →" +
								" U turn at Chebei South Rd. → Linjiang Ave. (Westwards) →" +
								" Liede Ave → U turn at the top of Huacheng Ave Tunnel. " +
								"→ Liede Bridge →Yuejiang Rd. (Eastwards) → U turn at the " +
								"intersection of Yuejiang Rd. and Huizhan Middle Rd. → " +
								"Yuejiang Rd (Westwards) → Binjiang East Rd. → U turn at " +
								"Binjiang Rd. → Binjiang Rd → Yiyuan Rd. → Yizhou Rd. → Binjiang " +
								"Rd (Westwards) → Hongde Rd. (Southwards) → People Bridge → " +
								"Yanjiang Rd. (Eastwards) → Datong Rd. (Eastwards) → Tanyue Street" +
								" → Qingbo Rd. → Hai Xin Sha → No. 1 Bridge of Hai Xin Sha →" +
								" Linjiang Ave. → Huacheng Square (Finishing Point)");
						activitiesDetail.cachedView.setTag(R.id.EXPAND_PRO);
					} else {
						activitiesDetail.process_expand_tv = (TextView) activitiesDetail.cachedView.getTag(R.id.EXPAND_PRO);
					}
					activitiesDetail.process_container.addView(activitiesDetail.process_expand_tv);
					activitiesDetail.process_container.removeView(activitiesDetail.processMore);
					activitiesDetail.processMore.setText("Collapse view <<");
					activitiesDetail.process_container.addView(activitiesDetail.processMore);
					activitiesDetail.hasExpanded_process = true;
					break;
				case COLLAPSE_PRO:
					activitiesDetail.process_container.removeView(activitiesDetail.processMore);
					activitiesDetail.processMore.setText("Click to view more >>");
					activitiesDetail.process_container.removeView(activitiesDetail.process_expand_tv);
					activitiesDetail.process_container.addView(activitiesDetail.processMore);
					activitiesDetail.hasExpanded_process = false;
					break;
			}
		}
	}
}
