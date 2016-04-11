package com.example.macyaren.sportman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hennzr on 2016/2/28 16:25
 * Package in com.example.macyaren.sportman
 * Project name is Sportman
 */
public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener,
		View.OnClickListener, ObservableScrollView.Callbacks {
	/*
	* 实现ViewPager.OnPageChangeListener接口，控制page的跳转
	* 实现View.OnClickListener接口，控制点击事件
	* */

	MainFragmentPagerAdapter fragmentPagerAdapter;
	Toolbar toolbar;
	ViewPager viewPager;
	public List<Fragment> fragmentList;
	Fragment activitiesfragment;
	Fragment msgfragment;
	Fragment mefragment;
	ImageView currentImageView = null;
	ImageView prevImageView = null;
	LinearLayout tab_liner_1;
	LinearLayout tab_liner_2;
	LinearLayout tab_liner_3;
	LinearLayout filter;
	LinearLayout placeholder;
	ObservableScrollView observableScrollView;
	RelativeLayout filter_sport;
	RelativeLayout filter_time;
	RelativeLayout filter_distance;
	RelativeLayout filter_price;
	LayoutInflater inflater;

	LinearLayout activities_fragment_toolbar_content_location;
	ImageView activities_fragment_toolbar_content_search;

	ImageView message_fragment_toolbar_content_friendgroup;
	ImageView message_fragment_toolbar_content_add;

	ImageView me_fragment_toolbar_content_setting;

	Intent intent;

	public final static String INTENT_FOR_ACTIVITY_CITY_SELECTION = "com.macya.intent.action" +
			".CITY_SELECTION";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		inflater = LayoutInflater.from(this);
		/*
		* 全局crash监控
		* */
		//		CrashHandler crashHandler = CrashHandler.getInstance();
		//		crashHandler.init(getApplicationContext());

		/*
		* 在ActionBar的位置替换成ToolBar
		* 进行一些ToolBar的简单设置
		* */
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("");
		//		toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
		setActionBar(toolbar);

		//		toolbar = (Toolbar) inflater.inflate(R.layout.activities_fragment_toolbar_content, toolbar,
		//				true);
		addActivitiesToolBarContent();

		/*
		* 准备主页上的三个页面
		* */
		viewPager = (ViewPager) findViewById(R.id.mainViewPager);
		fragmentList = new ArrayList<Fragment>();
		if (activitiesfragment == null) {
			activitiesfragment = new ActivitiesFragment();
		}
		if (msgfragment == null) {
			msgfragment = new MessageFragment();
		}
		if (mefragment == null) {
			mefragment = new MeFragment();
		}
		fragmentList.add(activitiesfragment);
		fragmentList.add(msgfragment);
		fragmentList.add(mefragment);
		fragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(fragmentPagerAdapter);
		viewPager.setOffscreenPageLimit(fragmentList.size());
		viewPager.addOnPageChangeListener(this);

		/*
		* 主页底部导航栏添加跳转事件
		* */
		tab_liner_1 = (LinearLayout) findViewById(R.id.tab_1);
		tab_liner_1.setOnClickListener(this);
		tab_liner_2 = (LinearLayout) findViewById(R.id.tab_2);
		tab_liner_2.setOnClickListener(this);
		tab_liner_3 = (LinearLayout) findViewById(R.id.tab_3);
		tab_liner_3.setOnClickListener(this);


	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			/*
			* 为全局监控重绘filter做准备
			* */
			placeholder = (LinearLayout) findViewById(R.id.sticky_filter_placeholder);
			filter = (LinearLayout) findViewById(R.id.activities_fragment_filter);
			observableScrollView = (ObservableScrollView) findViewById(R.id
					.activities_fragment_scrollView);
			observableScrollView.setmCallbacks(this);

			/*
			* 当布局绘制完全的时候我们才可以得到view.getTop()
			* 所以我在onWindowFocusChanged中
			* 设置全局布局监听器
			* */
			observableScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
				@Override
				public void onGlobalLayout() {
					onScrollchanged(observableScrollView.getScrollY());
					observableScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				}
			});

			/*
			* 添加筛选器的点击事件
			* */
			filter_sport = (RelativeLayout) findViewById(R.id.activities_fragment_filter_sport);
			filter_sport.setOnClickListener(this);
			filter_time = (RelativeLayout) findViewById(R.id.activities_fragment_filter_time);
			filter_time.setOnClickListener(this);
			filter_distance = (RelativeLayout) findViewById(R.id
					.activities_fragment_filter_distance);
			filter_distance.setOnClickListener(this);
			filter_price = (RelativeLayout) findViewById(R.id.activities_fragment_filter_price);
			filter_price.setOnClickListener(this);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/*创建菜单*/
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		* 菜单的选项单击事件
		* */
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		/*
		* viewPager页面滚动时的事件
		* */
	}

	@Override
	public void onPageSelected(int position) {
		/*
		* 页面切换事件
		* */
		switch (position) {
			case 0:
				currentImageView = (ImageView) findViewById(R.id.tab_image_1);
				currentImageView.setImageResource(R.drawable.wrestling2);
				prevImageView = (ImageView) findViewById(R.id.tab_image_2);
				prevImageView.setImageResource(R.drawable.sms1);
				prevImageView = (ImageView) findViewById(R.id.tab_image_3);
				prevImageView.setImageResource(R.drawable.user_male1);
				addActivitiesToolBarContent();
				break;
			case 1:
				currentImageView = (ImageView) findViewById(R.id.tab_image_2);
				currentImageView.setImageResource(R.drawable.sms2);
				prevImageView = (ImageView) findViewById(R.id.tab_image_1);
				prevImageView.setImageResource(R.drawable.wrestling1);
				prevImageView = (ImageView) findViewById(R.id.tab_image_3);
				prevImageView.setImageResource(R.drawable.user_male1);
				addMessageToolBarContent();
				break;
			case 2:
				currentImageView = (ImageView) findViewById(R.id.tab_image_3);
				currentImageView.setImageResource(R.drawable.user_male2);
				prevImageView = (ImageView) findViewById(R.id.tab_image_2);
				prevImageView.setImageResource(R.drawable.sms1);
				prevImageView = (ImageView) findViewById(R.id.tab_image_1);
				prevImageView.setImageResource(R.drawable.wrestling1);
				addMeToolBarContent();
				break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		/*
		* viewPager页面切换的状态变化时的事件
		* */
	}

	@Override
	public void onClick(View v) {
		/*
		* 主页上有关的点击事件
		* 包括：
		* 1、主页的viewPager切换
		* 2、活动主页筛选器的点击事件
		* 3、活动页toolbar的地址定位
		* 4、活动页toolb的搜索
		*
		*
		*
		*
		*
		*
		* ...
		* */
		switch (v.getId()) {
			case R.id.tab_1:
				viewPager.setCurrentItem(0, true);
				break;
			case R.id.tab_2:
				viewPager.setCurrentItem(1, true);
				break;
			case R.id.tab_3:
				viewPager.setCurrentItem(2, true);
				break;
			case R.id.activities_fragment_filter_sport:
				Toast.makeText(getApplicationContext(), "filter_sport was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.activities_fragment_filter_time:
				Toast.makeText(getApplicationContext(), "filter_time was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.activities_fragment_filter_distance:
				Toast.makeText(getApplicationContext(), "filter_distance was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.activities_fragment_filter_price:
				Toast.makeText(getApplicationContext(), "filter_price was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.activities_fragment_toolbar_content_location:
				//				Toast.makeText(this, "toolbar_location was clicked",Toast.LENGTH_SHORT).show();
				intent = new Intent();
				intent.setAction(MainActivity.INTENT_FOR_ACTIVITY_CITY_SELECTION);
				startActivity(intent);
				break;
			case R.id.activities_fragment_toolbar_content_search:
				Toast.makeText(getApplicationContext(), "toolbar_search was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.message_fragment_toolbar_content_friendgroup:
				Toast.makeText(getApplicationContext(), "toolbar_friendgroup was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.message_fragment_toolbar_content_add:
				Toast.makeText(getApplicationContext(), "toolbar_add was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.me_fragment_toolbar_content_setting:
				Toast.makeText(getApplicationContext(), "toolbar_setting was clicked", Toast.LENGTH_SHORT).show();
				break;
		}
	}

	@Override
	public void onScrollchanged(int t) {
		int translation = Math.max(t, placeholder.getTop());
		//		Log.i("ZRH", "observableScrollView getScrollY: " + observableScrollView
		//				.getScrollY());
		//		Log.i("ZRH", "placeHolder getTop: " + placeholder.getTop());
		filter.setTranslationY(translation);
	}

	@Override
	public void onTouchUp() {

	}

	@Override
	public void onTouchDown() {

	}

	protected void addActivitiesToolBarContent() {
		toolbar.removeAllViews();
		toolbar = (Toolbar) inflater.inflate(R.layout.activities_fragment_toolbar_content, toolbar,
				true);
		activities_fragment_toolbar_content_location = (LinearLayout) toolbar.findViewById(R.id
				.activities_fragment_toolbar_content_location);
		activities_fragment_toolbar_content_search = (ImageView) toolbar.findViewById(R.id
				.activities_fragment_toolbar_content_search);
		activities_fragment_toolbar_content_location.setOnClickListener(this);
		activities_fragment_toolbar_content_search.setOnClickListener(this);
	}

	protected void addMessageToolBarContent() {
		toolbar.removeAllViews();
		toolbar = (Toolbar) inflater.inflate(R.layout.message_fragment_toolbar_content, toolbar,
				true);
		message_fragment_toolbar_content_friendgroup = (ImageView) toolbar.findViewById(R.id
				.message_fragment_toolbar_content_friendgroup);
		message_fragment_toolbar_content_add = (ImageView) toolbar.findViewById(R.id
				.message_fragment_toolbar_content_add);
		message_fragment_toolbar_content_friendgroup.setOnClickListener(this);
		message_fragment_toolbar_content_add.setOnClickListener(this);
	}

	protected void addMeToolBarContent() {
		toolbar.removeAllViews();
		toolbar = (Toolbar) inflater.inflate(R.layout.me_fragment_toolbar_content, toolbar,
				true);
		me_fragment_toolbar_content_setting = (ImageView) toolbar.findViewById(R.id
				.me_fragment_toolbar_content_setting);
		me_fragment_toolbar_content_setting.setOnClickListener(this);
	}
}
