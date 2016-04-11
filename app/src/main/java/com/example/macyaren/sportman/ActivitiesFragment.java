package com.example.macyaren.sportman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hennzr on 2016/2/29.
 */
public class ActivitiesFragment extends Fragment implements View.OnClickListener {

	ViewPager mViewPager;
	ListView listViewForScrollView;

	List<Fragment> fragmentList = new ArrayList<>();
	List<View> imageList = new ArrayList<>();

	TopFragmentPagerAdapter topFragmentPagerAdapter;
	ActivitiesFragmentBannerAdapter activitiesFragmentBannerAdapter;

	ActivitiesFragmentListInfo[] activitiesFragmentListInfos;
	ActivitiesFragmentListData activitiesFragmentListData;
	List<ActivitiesFragmentListInfo> activitiesFragmentListInfoList;
	ActivitiesFragmentListAdapter activitiesFragmentListAdapter;

	LinearLayout left_liner;
	LinearLayout right_liner;

	LinearLayout banner_switcher_container;
	TextView banner_switcher_left;
	TextView banner_switcher_center;
	TextView banner_switcher_right;

	LinearLayout.LayoutParams current_switcher_layoutParams;
	LinearLayout.LayoutParams other_switcher_layoutParams;

	Intent intent_in_list;

	int margin_other;
	int margin_top_bottom;

	public int final_position = 1;
	public final static String action_to_activity_detail = "com.macya.intent.action" +
			".ACTIVITIES_DETAIL";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		* 初始化活动列表数据
		* */
		activitiesFragmentListInfos = new ActivitiesFragmentListInfo[5];
		activitiesFragmentListData = new ActivitiesFragmentListData();
		activitiesFragmentListInfoList = new ArrayList<ActivitiesFragmentListInfo>();

		margin_top_bottom = Utility.dip2px(getContext(), 2);
		margin_other = Utility.dip2px(getContext(), 5);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ScrollView resView = (ScrollView) inflater.inflate(R.layout.activities_fragment,
				container, false);

		/*
		* 创建嵌套在ViewPager的ScrollView中的广告栏ViewPager，
		* */
		mViewPager = (ViewPager) resView.findViewById(R.id.activities_fragment_viewPager);
		try {
			mViewPager.removeAllViews();

			ActivitiesTopFragment[] activitiesTopFragment = new ActivitiesTopFragment[3];
			activitiesTopFragment[0] = new ActivitiesTopFragment();
			activitiesTopFragment[0].setRESID(R.drawable.activities_post1);
			fragmentList.add(activitiesTopFragment[0]);
			activitiesTopFragment[1] = new ActivitiesTopFragment();
			activitiesTopFragment[1].setRESID(R.drawable.activities_post2);
			fragmentList.add(activitiesTopFragment[1]);
			activitiesTopFragment[2] = new ActivitiesTopFragment();
			activitiesTopFragment[2].setRESID(R.drawable.activities_post3);
			fragmentList.add(activitiesTopFragment[2]);

			topFragmentPagerAdapter = new TopFragmentPagerAdapter(getChildFragmentManager());
			topFragmentPagerAdapter.setFragmentList(fragmentList);

			ImageView post_13 = (ImageView) inflater.inflate(R.layout.banner_fragment_13, container, false);
			ImageView post_113 = (ImageView) post_13.findViewById(R.id.banner_fragment_post_13);
			post_113.setImageResource(R.drawable.activities_post3);
			imageList.add(post_13);

			ImageView post_1 = (ImageView) inflater.inflate(R.layout.banner_fragment_1, container,
					false);
			ImageView post_11 = (ImageView) post_1.findViewById(R.id.banner_fragment_post_1);
			post_11.setImageResource(R.drawable.activities_post1);
			imageList.add(post_1);

			ImageView post_2 = (ImageView) inflater.inflate(R.layout.banner_fragment_2, container,
					false);
			ImageView post_22 = (ImageView) post_2.findViewById(R.id.banner_fragment_post_2);
			post_22.setImageResource(R.drawable.activities_post2);
			imageList.add(post_2);

			ImageView post_3 = (ImageView) inflater.inflate(R.layout.banner_fragment_3, container,
					false);
			ImageView post_33 = (ImageView) post_3.findViewById(R.id.banner_fragment_post_3);
			post_33.setImageResource(R.drawable.activities_post3);
			imageList.add(post_3);

			ImageView post_31 = (ImageView) inflater.inflate(R.layout.banner_fragment_31,
					container, false);
			ImageView post_331 = (ImageView) post_31.findViewById(R.id.banner_fragment_post_31);
			post_331.setImageResource(R.drawable.activities_post1);
			imageList.add(post_31);

			activitiesFragmentBannerAdapter = new ActivitiesFragmentBannerAdapter();
			activitiesFragmentBannerAdapter.setList(imageList);

			mViewPager.setAdapter(activitiesFragmentBannerAdapter);

			mViewPager.setOnPageChangeListener(new myOnPageChangeListener());
			mViewPager.setCurrentItem(1, false);


		} catch (Exception ee) {
			ee.printStackTrace();
		}

		/*
		* 获取banner的indicator
		* */
		banner_switcher_container = (LinearLayout) resView.findViewById(R.id
				.banner_switcher_container);
		banner_switcher_left = (TextView) resView.findViewById(R.id.banner_switcher_left);
		banner_switcher_center = (TextView) resView.findViewById(R.id.banner_switcher_center);
		banner_switcher_right = (TextView) resView.findViewById(R.id.banner_switcher_right);

		/*
		* 准备banner_switcher的layoutParams
		* */
		current_switcher_layoutParams = new LinearLayout.LayoutParams(Utility
				.dip2px(getContext(), 16), Utility
				.dip2px(getContext(), 8));
		other_switcher_layoutParams = new LinearLayout.LayoutParams(Utility
				.dip2px(getContext(), 8), Utility
				.dip2px(getContext(), 8));


		/*
		* 活动主页宣传liner部分的设置点击事件
		* */
		left_liner = (LinearLayout) resView.findViewById(R.id.left_liner);
		left_liner.setOnClickListener(this);
		right_liner = (LinearLayout) resView.findViewById(R.id.right_liner);
		right_liner.setOnClickListener(this);

		/*
		* 创建活动的listview，嵌套在ViewPager的ScrollView中
		* */
		listViewForScrollView = (ListView) resView.findViewById(R.id.activities_fragment_list);
		activitiesFragmentListAdapter = new ActivitiesFragmentListAdapter(getActivity());

		initActivitiesFragmentList();
		activitiesFragmentListAdapter.setList(activitiesFragmentListInfoList);
		listViewForScrollView.setAdapter(activitiesFragmentListAdapter);
		Utility.setListViewHeightBasedOnChildren(listViewForScrollView);
		listViewForScrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getContext().getApplicationContext(), "listItem" + position + " was clicked",
						Toast.LENGTH_SHORT).show();
				intent_in_list = new Intent();
				intent_in_list.setAction(action_to_activity_detail);
				startActivity(intent_in_list);
			}
		});
		resView.smoothScrollTo(0, 0);

		listViewForScrollView.setFocusable(false);
		resView.setFocusable(true);
		return resView;
	}

	public void initActivitiesFragmentList() {
		/*初始化活动列表信息*/
		for (int i = 0; i < 5; i++) {
			activitiesFragmentListInfos[i] = new ActivitiesFragmentListInfo();
			activitiesFragmentListInfos[i].photo = activitiesFragmentListData.IMAGE_ID[i];
			activitiesFragmentListInfos[i].name = activitiesFragmentListData.NAME[i];
			activitiesFragmentListInfos[i].hold = activitiesFragmentListData.HOLD[i];
			activitiesFragmentListInfos[i].area = activitiesFragmentListData.AREA[i];
			activitiesFragmentListInfos[i].distance = activitiesFragmentListData.DISTANCE[i];
			activitiesFragmentListInfos[i].join = activitiesFragmentListData.JOIN[i];
			activitiesFragmentListInfos[i].price = activitiesFragmentListData.PRICE[i];
			activitiesFragmentListInfos[i].date = activitiesFragmentListData.DATE[i];
			activitiesFragmentListInfoList.add(activitiesFragmentListInfos[i]);
		}
	}

	/*
	* 1、活动主页宣传liner部分的点击事件
	* */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.left_liner:
				Toast.makeText(getContext().getApplicationContext(), "left_liner was clicked", Toast.LENGTH_SHORT)
						.show();
				break;
			case R.id.right_liner:
				Toast.makeText(getContext().getApplicationContext(), "right_liner was clicked", Toast.LENGTH_SHORT)
						.show();
				break;
		}
	}


	/*实现banner上的ViewPager监听接口*/
	class myOnPageChangeListener implements ViewPager.OnPageChangeListener {

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

		}

		@Override
		public void onPageSelected(int position) {
			//			Toast.makeText(getContext(),"banner current post position is "+position,
			//					Toast.LENGTH_SHORT).show();

			switch (position) {
				case 0:
					other_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_left.setLayoutParams(other_switcher_layoutParams);
					banner_switcher_left.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_out));

					other_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_center.setLayoutParams(other_switcher_layoutParams);
					banner_switcher_center.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_out));

					current_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_right.setLayoutParams(current_switcher_layoutParams);
					banner_switcher_right.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_in));
					final_position = 0;
					break;
				case 1:
					current_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_left.setLayoutParams(current_switcher_layoutParams);
					banner_switcher_left.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_in));

					other_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_center.setLayoutParams(other_switcher_layoutParams);
					banner_switcher_center.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_out));

					other_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_right.setLayoutParams(other_switcher_layoutParams);
					banner_switcher_right.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_out));
					final_position = 1;
					break;
				case 2:
					other_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_left.setLayoutParams(other_switcher_layoutParams);
					banner_switcher_left.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_out));

					current_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_center.setLayoutParams(current_switcher_layoutParams);
					banner_switcher_center.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_in));

					other_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_right.setLayoutParams(other_switcher_layoutParams);
					banner_switcher_right.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_out));
					final_position = 2;
					break;
				case 3:
					other_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_left.setLayoutParams(other_switcher_layoutParams);
					banner_switcher_left.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_out));

					other_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_center.setLayoutParams(other_switcher_layoutParams);
					banner_switcher_center.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_out));

					current_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_right.setLayoutParams(current_switcher_layoutParams);
					banner_switcher_right.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_in));
					final_position = 3;
					break;
				case 4:
					current_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_left.setLayoutParams(current_switcher_layoutParams);
					banner_switcher_left.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_in));

					other_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_center.setLayoutParams(other_switcher_layoutParams);
					banner_switcher_center.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_out));

					other_switcher_layoutParams.setMargins(margin_other, margin_top_bottom, 0,
							margin_top_bottom);
					banner_switcher_right.setLayoutParams(other_switcher_layoutParams);
					banner_switcher_right.setBackground(getResources().getDrawable(R.drawable
							.banner_switcher_out));
					final_position = 4;
					break;
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			if (imageList.size() > 1) {
				if (state == ViewPager.SCROLL_STATE_DRAGGING) {
					banner_switcher_container.setBackground(getResources().getDrawable(R.drawable
							.banner_container_in));
				}
				if (state == ViewPager.SCROLL_STATE_IDLE) {
					banner_switcher_container.setBackground(getResources().getDrawable(R.drawable
							.banner_container_out));
				}
				if (final_position == 0) {
					if (state == ViewPager.SCROLL_STATE_IDLE) {
						mViewPager.setCurrentItem(3, false);
					}
				}
				if (final_position == 4) {
					if (state == ViewPager.SCROLL_STATE_IDLE) {
						mViewPager.setCurrentItem(1, false);
					}
				}
			}
		}
	}
}
