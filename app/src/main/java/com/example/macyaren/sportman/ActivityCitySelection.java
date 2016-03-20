package com.example.macyaren.sportman;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hennzr on 2016/3/14.
 */
public class ActivityCitySelection extends Activity implements View.OnClickListener {

	Toolbar toolbar;
	ImageView imageView_return;
	ExpandableListView expandableListView;
	EditText editText_search;
	LinearLayout cities_selection_navigation;
	TextView navigation_tv;
	TextView navigation_indicator;
	EditText cities_selection_searchtext;
	Intent intent_return;
	List<String> listGroup;
	List<List<String>> listChild;
	Map<String, List<String>> listMap;
	List<String> listGroupTemp;
	ActivityCitySelectionExpandableListAdapter activityCitySelectionExpandableListAdapter;
	Handler handler;
	PingYinTool pingYinTool;
	String[] navigation_alpha;
	float navigation_container_height;
	float navigation_tv_height;
	public final static int LOADING_CITY_NAME = 233;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cities_selection);
		expandableListView = (ExpandableListView) findViewById(R.id
				.activity_cities_selection_expandablelist);
		toolbar = (Toolbar) findViewById(R.id.activity_cities_selection_toolbar);
		setActionBar(toolbar);
		expandableListView.setFocusable(true);
		imageView_return = (ImageView) findViewById(R.id.activity_return);
		imageView_return.setOnClickListener(this);
		editText_search = (EditText) findViewById(R.id.activity_cities_selection_searchtext);

		activityCitySelectionExpandableListAdapter = new
				ActivityCitySelectionExpandableListAdapter(this);

		listGroup = new ArrayList<String>();
		listChild = new ArrayList<List<String>>();
		listMap = new HashMap<String, List<String>>();
		listGroupTemp = new ArrayList<String>();
		listGroup.add("#");
		listGroup.add("$");
		listGroup.add("*");
		List<String> list_loca = new ArrayList<String>();
		for (int i = 0; i < ActivityCitySelectionExpandableListData.CITY_LOCA.length; i++) {
			list_loca.add(ActivityCitySelectionExpandableListData.CITY_LOCA[i]);
		}
		listChild.add(list_loca);

		List<String> list_history = new ArrayList<String>();
		for (int i = 0; i < ActivityCitySelectionExpandableListData.CITY_HISTORY.length; i++) {
			list_history.add(ActivityCitySelectionExpandableListData.CITY_HISTORY[i]);
		}
		listChild.add(list_history);

		List<String> list_hot = new ArrayList<String>();
		for (int i = 0; i < ActivityCitySelectionExpandableListData.CITY_HOT.length; i++) {
			list_hot.add(ActivityCitySelectionExpandableListData.CITY_HOT[i]);
		}
		listChild.add(list_hot);

		/*创建拼音工具类实例*/
		pingYinTool = new PingYinTool();

		/*
		* 创建city_selection的右侧导航栏
		* */
		cities_selection_navigation = (LinearLayout) findViewById(R.id
				.activity_cities_selection_navigation);
		navigation_alpha = new String[]{"#", "$", "*", "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
				"T", "U", "V", "W", "X", "Y", "Z"};
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
				.LayoutParams.MATCH_PARENT, 0, 1);
		for (int i = 0; i < navigation_alpha.length; i++) {
			Log.i("ZRH", "创建navigation_tv");
			navigation_tv = new TextView(this);
			navigation_tv.setText(navigation_alpha[i]);
			//noinspection deprecation
			navigation_tv.setTextColor(getResources().getColor(R.color.md_grey_700));
			navigation_tv.setGravity(Gravity.CENTER_HORIZONTAL);
			navigation_tv.setLayoutParams(layoutParams);
			cities_selection_navigation.addView(navigation_tv);
		}

		/*
		* 右侧导航栏的手势action事件*/
		ViewTreeObserver viewTreeObserver_city_selection_navigation = cities_selection_navigation
				.getViewTreeObserver();
		viewTreeObserver_city_selection_navigation.addOnGlobalLayoutListener(new ViewTreeObserver
				.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				navigation_container_height = cities_selection_navigation.getHeight();
				navigation_tv_height = navigation_container_height / 29;
			}
		});
		navigation_indicator = (TextView) findViewById(R.id
				.activity_cities_selection_expandablelist_navigation_indicator);
		cities_selection_navigation.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				float currentPointY = event.getY();
				int navigation_tv_position = (int) Math.floor
						(currentPointY / navigation_tv_height);
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						cities_selection_navigation.setBackgroundColor(getResources().getColor(R
								.color.navigation_press));
						if (navigation_tv_position >= 0 && navigation_tv_position <= 29) {
							String clickStr = navigation_alpha[navigation_tv_position];
							navigation_indicator.setText(clickStr);
							navigation_indicator.setVisibility(View.VISIBLE);
							for (int i = 0; i < listGroup.size(); i++) {
								if (clickStr.equals(listGroup.get(i))) {
									expandableListView.setSelectedGroup(i);
								}
							}
						}
						return true;
					case MotionEvent.ACTION_MOVE:
						if (navigation_tv_position >= 0 && navigation_tv_position <= 29) {
							String clickStr = navigation_alpha[navigation_tv_position];
							navigation_indicator.setText(clickStr);
							for (int i = 0; i < listGroup.size(); i++) {
								if (clickStr.equals(listGroup.get(i))) {
									expandableListView.setSelectedGroup(i);
								}
							}
						}
						return true;
					case MotionEvent.ACTION_UP:
						cities_selection_navigation.setBackgroundColor(getResources().getColor(R
								.color.navigatiom_release));
						navigation_indicator.setVisibility(View.INVISIBLE);
						return false;
				}
				return false;
			}
		});

		/*
		* 建立handler实例处理信息
		* */
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == LOADING_CITY_NAME) {
					activityCitySelectionExpandableListAdapter.setListGroup(listGroup);
					activityCitySelectionExpandableListAdapter.setListChild(listChild);
					expandableListView.setAdapter(activityCitySelectionExpandableListAdapter);
					for (int i = 0; i < activityCitySelectionExpandableListAdapter.getGroupCount(); i++) {
						expandableListView.expandGroup(i);
					}
					expandableListView.setGroupIndicator(null);
					expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
						@Override
						public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
							return true;
						}
					});
					expandableListView.setOnChildClickListener(new ExpandableListView
							.OnChildClickListener() {
						@Override
						public boolean onChildClick(ExpandableListView parent, View v,
													int groupPosition, int childPosition, long id) {
							Toast.makeText(ActivityCitySelection.this, "ChildItem" + childPosition +
											" was clicked",
									Toast.LENGTH_SHORT).show();
							return true;
						}
					});
				}
			}
		};

		/*
		* 新开线程处理城市名
		* */
		new Thread(new Runnable() {
			@Override
			public void run() {
				XmlResourceParser city_parse = getResources().getXml(R.xml.cities);
				try {
					while (city_parse.getEventType() != XmlResourceParser.END_DOCUMENT) {
						if (city_parse.getEventType() == XmlResourceParser.START_TAG) {
							String tag_name = city_parse.getName();
							if (tag_name.equals("City")) {
								String city_name = city_parse.getAttributeValue(null, "CityName");
								String city_name_pinyin = pingYinTool.toPinYin(city_name)
										.toUpperCase();
								String city_name_pinyin_first = String.valueOf(city_name_pinyin
										.charAt(0));
								if (listMap.get(city_name_pinyin_first) != null) {
									listMap.get(city_name_pinyin_first).add(city_name);
								} else {
									List<String> list_city_name = new ArrayList<String>();
									list_city_name.add(city_name);
									listGroupTemp.add(city_name_pinyin_first);
									listMap.put(city_name_pinyin_first, list_city_name);
								}
							}
						}
						city_parse.next();
					}
				} catch (XmlPullParserException | BadHanyuPinyinOutputFormatCombination |
						IOException e) {
					e.printStackTrace();
				}

				Collections.sort(listGroupTemp);
				Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
				for (int i = 0; i < listGroupTemp.size(); i++) {
					List<String> listTemp = listMap.get(listGroupTemp.get(i));
					if (listTemp != null) {
						Collections.sort(listTemp, cmp);
						listChild.add(listTemp);
					}
				}
				listGroup.addAll(listGroupTemp);
				Log.i("ZRH", "listGroup的长度：" + listGroup.size());
				Log.i("ZRH", "listChild的长度：" + listChild.size());

				Message loadcityname = new Message();
				loadcityname.what = LOADING_CITY_NAME;
				handler.sendMessage(loadcityname);
			}
		}).start();
	}


	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.activity_return:
				finish();
				break;
		}
	}
}