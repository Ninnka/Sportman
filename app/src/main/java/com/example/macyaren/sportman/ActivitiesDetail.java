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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hennzr on 2016/3/19.
 */
public class ActivitiesDetail extends Activity implements ObservableScrollView.Callbacks,
		View.OnClickListener {

	public Intent intent;
	public ImageView headerView;
	public ObservableScrollView scrollView;
	public Toolbar toolbar;
	public LinearLayout container_signup;
	public ListView commentsListView;
	public TextView commentsMore;
	public TextView commentsCollapse;
	public TextView signupdetailMore;
	public TextView processMore;
	public TextView sample;
	public TextView process_sample;
	public LinearLayout process_container;
	public LinearLayout signupdetail_container;
	public TextView process_expand_tv;
	public TextView signupdetail_expanded_tv;
	public List<ActivitiesDetailCommentsListCommInfo> list;
	public ImageView detail_return;

	public View cachedView;

	public float distance_to_top;
	public float distance_to_toolbar;

	public boolean hasExpanded_signupdetail = false;
	public boolean hasExpanded_process = false;

	public final static int EXPAND_SUD = 0;
	public final static int COLLAPSE_SUD = 1;
	public final static int EXPAND_PRO = 2;
	public final static int COLLAPSE_PRO = 3;

	public ActivitiesDetailCommentsListAdapter activitiesDetailCommentsListAdapter;
	public ActivitiesDetailCommentsListCommData activitiesDetailCommentsListCommData;
	public ActivitiesDetailCommentsListCommInfo[] activitiesDetailCommentsListCommInfo;

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case EXPAND_SUD:
					if (cachedView.getTag(R.id.EXPAND_SUD) == null) {
						signupdetail_expanded_tv = new TextView(ActivitiesDetail.this);
						signupdetail_expanded_tv.setLayoutParams(sample.getLayoutParams());
						signupdetail_expanded_tv.setGravity(Gravity.CENTER | Gravity.LEFT);
						signupdetail_expanded_tv.setPadding(Utility.dip2px(ActivitiesDetail.this, 20), 0, 0, 0);
						signupdetail_expanded_tv.setTextSize(14);
						signupdetail_expanded_tv.setText("登记材料：身份证明，联系方式等");
						cachedView.setTag(R.id.EXPAND_SUD, signupdetail_expanded_tv);
					} else {
						signupdetail_expanded_tv = (TextView) cachedView.getTag(R.id.EXPAND_SUD);
					}
					signupdetail_container.addView(signupdetail_expanded_tv);
					signupdetail_container.removeView(signupdetailMore);
					signupdetailMore.setText("Collapse view <<");
					signupdetail_container.addView(signupdetailMore);
					hasExpanded_signupdetail = true;
					break;
				case COLLAPSE_SUD:
					signupdetail_container.removeView(signupdetailMore);
					signupdetailMore.setText("Click to view more >>");
					signupdetail_container.removeView(signupdetail_expanded_tv);
					signupdetail_container.addView(signupdetailMore);
					hasExpanded_signupdetail = false;
					break;
				case EXPAND_PRO:
					if (cachedView.getTag(R.id.EXPAND_PRO) == null) {
						process_expand_tv = new TextView(ActivitiesDetail.this);
						process_expand_tv.setLayoutParams(process_sample.getLayoutParams());
						process_expand_tv.setPadding(Utility.dip2px(ActivitiesDetail.this, 20), 0,
								0, 0);
						process_expand_tv.setGravity(Gravity.CENTER | Gravity.LEFT);
						process_expand_tv.setSingleLine(false);
//						process_expand_tv.setMaxLines(30);
//						process_expand_tv.setEllipsize(TextUtils.TruncateAt.END);
						process_expand_tv.setTextSize(14);
						process_expand_tv.setText("II. Race Course \n(I) Mini Marathon: \n" +
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
						cachedView.setTag(R.id.EXPAND_PRO);
					} else {
						process_expand_tv = (TextView) cachedView.getTag(R.id.EXPAND_PRO);
					}
					process_container.addView(process_expand_tv);
					process_container.removeView(processMore);
					processMore.setText("Collapse view <<");
					process_container.addView(processMore);
					hasExpanded_process = true;
					break;
				case COLLAPSE_PRO:
					process_container.removeView(processMore);
					processMore.setText("Click to view more >>");
					process_container.removeView(process_expand_tv);
					process_container.addView(processMore);
					hasExpanded_process = false;
					break;
			}
		}
	};

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
		container_signup = (LinearLayout) findViewById(R.id
				.activities_detail_content_center_container_signup);
		commentsListView = (ListView) findViewById(R.id
				.activities_detail_content_center_commentslist);
		activitiesDetailCommentsListAdapter = new ActivitiesDetailCommentsListAdapter(this);
		list = new ArrayList<ActivitiesDetailCommentsListCommInfo>();
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
//			list.add(activitiesDetailCommentsListCommInfo[i]);
		}
		activitiesDetailCommentsListAdapter.setList(list);
		commentsListView.setAdapter(activitiesDetailCommentsListAdapter);
		Utility.setListViewHeightBasedOnChildren(commentsListView);

		process_container = (LinearLayout) findViewById(R.id
				.activities_detail_content_center_process);
		signupdetail_container = (LinearLayout) findViewById(R.id
				.activities_detail_content_center_signupdetail);

		commentsMore = (TextView) findViewById(R.id
				.activities_detail_content_center_comments_clicktoviewmore);
		commentsMore.setOnClickListener(this);
		commentsCollapse = (TextView) findViewById(R.id
				.activities_detail_content_center_comments_cllapseview);
		commentsCollapse.setOnClickListener(this);
		signupdetailMore = (TextView) findViewById(R.id
				.activities_detail_content_center_signupdetail_clicktoviewmore);
		signupdetailMore.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.activities_detail_content_center_comments_clicktoviewmore:
				int size = list.size();
				if(size == 0){
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
				if(!commentsCollapse.getText().toString().equals("")){
					list.clear();
					activitiesDetailCommentsListAdapter.notifyDataSetChanged();
					Utility.setListViewHeightBasedOnChildren(commentsListView);
					commentsCollapse.setText("");
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
			case R.id.activities_detail_content_center_signupdetail_clicktoviewmore:
				Message message_signupdetail = new Message();
				if (hasExpanded_signupdetail) {
					message_signupdetail.what = COLLAPSE_SUD;
					handler.sendMessage(message_signupdetail);
				} else {
					message_signupdetail.what = EXPAND_SUD;
					handler.sendMessage(message_signupdetail);
				}
				break;
			case R.id.activity_detail_return:
				finish();
				break;
		}
	}
}
