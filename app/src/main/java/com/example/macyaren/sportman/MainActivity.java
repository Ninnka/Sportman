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
 * Created by hennzr on 2016/2/28.
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
	int[] toolbar_loca = new int[2];
	int[] filter_loca = new int[2];
	LinearLayout filter;
	LinearLayout placeholder;
	ObservableScrollView observableScrollView;
	RelativeLayout filter_sport;
	RelativeLayout filter_time;
	RelativeLayout filter_distance;
	RelativeLayout filter_price;
	LinearLayout activities_fragment_tc;
	LinearLayout message_fragment_tc;
	LinearLayout me_fragment_tc;
	LayoutInflater inflater;

	LinearLayout activities_fragment_toolbar_content_location;
	ImageView activities_fragment_toolbar_content_search;

	ImageView message_fragment_toolbar_content_friendgroup;
	ImageView message_fragment_toolbar_content_add;

	ImageView me_fragment_toolbar_content_setting;

	Intent intent;

	public final static String INTENT_FOR_ACTIVITY_CITY_SELECTION = "com.macya.intent.action" +
			".CITY_SELECTION";

	//	int filter_top;
	//	int toolbar_bottom;
	//	int real_toolbar_bottom;


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
		activities_fragment_tc = (LinearLayout) inflater.inflate(R.layout
				.activities_fragment_toolbar_content, null);
		message_fragment_tc = (LinearLayout) inflater.inflate(R.layout
				.message_fragment_toolbar_content, null);
		me_fragment_tc = (LinearLayout) inflater.inflate(R.layout.me_fragment_toolbar_content, null);
		toolbar.addView(activities_fragment_tc);


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

		/*
		* 为活动页上的toolbar添加点击事件
		* */
		activities_fragment_toolbar_content_location = (LinearLayout) findViewById(R.id
				.activities_fragment_toolbar_content_location);
		activities_fragment_toolbar_content_search = (ImageView) findViewById(R.id
				.activities_fragment_toolbar_content_search);
		activities_fragment_toolbar_content_location.setOnClickListener(this);
		activities_fragment_toolbar_content_search.setOnClickListener(this);

		message_fragment_toolbar_content_friendgroup = (ImageView) message_fragment_tc
				.findViewById(R.id
						.message_fragment_toolbar_content_friendgroup);
		message_fragment_toolbar_content_add = (ImageView) message_fragment_tc
				.findViewById(R.id
						.message_fragment_toolbar_content_add);
		message_fragment_toolbar_content_friendgroup.setOnClickListener(this);
		message_fragment_toolbar_content_add.setOnClickListener(this);

		me_fragment_toolbar_content_setting = (ImageView) me_fragment_tc.findViewById(R.id
				.me_fragment_toolbar_content_setting);
		me_fragment_toolbar_content_setting.setOnClickListener(this);

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
			observableScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new
					ViewTreeObserver.OnGlobalLayoutListener() {
				@Override
				public void onGlobalLayout() {
					onScrollchanged(observableScrollView.getScrollY());
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

			/*
			* 上滑悬停效果
			* 设置scrollView的onTouchListener
			* 循环获取scrollY直到停下
			* 对比filter_top与toolbar_bottom
			* 相等时将filter添加到toolbar下面
			* 运用了handler处理
			* 可惜效果不太好
			* 迅速滑动的时候handler处理有延时，filter没办法准确加载在toolbar下面
			* */
			//			filter = (LinearLayout) findViewById(R.id.activities_fragment_filter);
			//			filter_parent = (LinearLayout) findViewById(R.id.activities_fragment_filter_parent);
			//			main_filter_parent = (LinearLayout) findViewById(R.id.main_filter_parent);
			//			toolBar_top = (Toolbar) findViewById(R.id.toolbar);
			//
			//			filter_parent.getLocationOnScreen(filter_loca);
			//			toolbar_bottom = toolBar_top.getBottom();
			//			toolbar.getLocationOnScreen(toolbar_loca);
			//
			//			Log.i("ZRH", "filter_top:" + filter_loca[1]);
			//			real_toolbar_bottom = toolbar_bottom + toolbar_loca[1];
			//			Log.i("ZRH", "real_toolbar_bottom:" + real_toolbar_bottom);
			//
			//
			//			final ScrollView scrollView = (ScrollView) findViewById(R.id.activities_fragment_scrollView);
			//
			//			scrollView.setOnTouchListener(new View.OnTouchListener() {
			//				private int lastY = 0;
			//				private int touchEventId = -9983761;
			//				private int scrollY;
			//
			//				private Handler handler = new Handler() {
			//					public void handleMessage(Message msg) {
			//						super.handleMessage(msg);
			//						try{
			//							if (msg.what == touchEventId) {
			//
			//								scrollY = scrollView.getScrollY();
			//								if (lastY != scrollY) {
			//									lastY = scrollY;
			//									Log.i("ZRH FOR scrollView Listener",
			//											"in handler 执行 sendMessageDelayed");
			//									handler.sendMessageDelayed(handler.obtainMessage(touchEventId,
			//											scrollView), 5);
			//								}
			//								if (lastY == scrollY) {
			//									filter_parent.getLocationOnScreen(filter_loca);
			//									Log.i("ZRH", "filter_top:" + filter_loca[1]);
			//									Log.i("ZRH", "real_toolbar_bottom:" + real_toolbar_bottom);
			//
			//									if (filter_loca[1] <= real_toolbar_bottom) {
			//										Log.i("ZRH FOR scrollView Listener",
			//												"filter_top <= toolbar_bottom");
			//										if (filter.getParent() == filter_parent) {
			//											filter_parent.removeView(filter);
			//											main_filter_parent.addView(filter);
			//										}
			//									} else {
			//										Log.i("ZRH FOR scrollView Listener",
			//												"filter_top > toolbar_bottom");
			//										if (filter.getParent() == main_filter_parent) {
			//											main_filter_parent.removeView(filter);
			//											filter_parent.addView(filter);
			//										}
			//									}
			//									Log.i("ZRH FOR scrollView Listener",
			//											"执行removeMessages");
			//									handler.removeMessages(touchEventId);
			//								}
			//							}
			//						}catch (Exception e){
			//							Log.i("ZRH FOR scrollView Listener",e.getMessage());
			//						}
			//					}
			//				};
			//
			//				@Override
			//				public boolean onTouch(View v, MotionEvent event) {
			//					switch (event.getAction()) {
			//						case MotionEvent.ACTION_MOVE:
			//							Log.i("ZRH FOR scrollView Listener",
			//									"in action_move 执行 sendMessageDelayed");
			//							handler.sendMessageDelayed(handler.obtainMessage(touchEventId, v), 5);
			//							break;
			//						case MotionEvent.ACTION_UP:
			//							Log.i("ZRH FOR scrollView Listener",
			//									"in action_up 执行 sendMessageDelayed");
			//							handler.sendMessageDelayed(handler.obtainMessage(touchEventId, v), 5);
			//							break;
			//					}
			//					return false;
			//				}
			//			});

			//			if (filter_top <= toolbar_bottom) {
			//				if (filter.getParent() == filter_parent) {
			//					filter_parent.removeView(filter);
			//					main_filter_parent.addView(filter);
			//				}
			//			} else {
			//				if (filter.getParent() == main_filter_parent) {
			//					main_filter_parent.removeView(filter);
			//					filter_parent.addView(filter);
			//				}
			//			}

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
				toolbar.removeAllViews();
				toolbar.addView(activities_fragment_tc);
				break;
			case 1:
				currentImageView = (ImageView) findViewById(R.id.tab_image_2);
				currentImageView.setImageResource(R.drawable.sms2);
				prevImageView = (ImageView) findViewById(R.id.tab_image_1);
				prevImageView.setImageResource(R.drawable.wrestling1);
				prevImageView = (ImageView) findViewById(R.id.tab_image_3);
				prevImageView.setImageResource(R.drawable.user_male1);
				toolbar.removeAllViews();
				toolbar.addView(message_fragment_tc);
				break;
			case 2:
				currentImageView = (ImageView) findViewById(R.id.tab_image_3);
				currentImageView.setImageResource(R.drawable.user_male2);
				prevImageView = (ImageView) findViewById(R.id.tab_image_2);
				prevImageView.setImageResource(R.drawable.sms1);
				prevImageView = (ImageView) findViewById(R.id.tab_image_1);
				prevImageView.setImageResource(R.drawable.wrestling1);
				toolbar.removeAllViews();
				toolbar.addView(me_fragment_tc);
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
				Toast.makeText(this, "filter_sport was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.activities_fragment_filter_time:
				Toast.makeText(this, "filter_time was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.activities_fragment_filter_distance:
				Toast.makeText(this, "filter_distance was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.activities_fragment_filter_price:
				Toast.makeText(this, "filter_price was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.activities_fragment_toolbar_content_location:
				//				Toast.makeText(this, "toolbar_location was clicked",Toast.LENGTH_SHORT).show();
				intent = new Intent();
				intent.setAction(MainActivity.INTENT_FOR_ACTIVITY_CITY_SELECTION);
				startActivity(intent);
				break;
			case R.id.activities_fragment_toolbar_content_search:
				Toast.makeText(this, "toolbar_search was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.message_fragment_toolbar_content_friendgroup:
				Toast.makeText(this, "toolbar_friendgroup was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.message_fragment_toolbar_content_add:
				Toast.makeText(this, "toolbar_add was clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.me_fragment_toolbar_content_setting:
				Toast.makeText(this, "toolbar_setting was clicked", Toast.LENGTH_SHORT).show();
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
}
