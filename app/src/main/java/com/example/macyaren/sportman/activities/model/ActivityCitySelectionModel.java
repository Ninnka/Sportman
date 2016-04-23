package com.example.macyaren.sportman.activities.model;

import android.content.res.XmlResourceParser;
import android.os.Handler;
import android.os.Message;

import com.example.macyaren.sportman.R;
import com.example.macyaren.sportman.activities.interator.ActivityCitySelectionModelInterator;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivityCitySelectionExpandableListData;
import com.example.macyaren.sportman.activities.view.ActivitiesCitySelection_Handler;
import com.example.macyaren.sportman.activities.view.ActivityCitySelection;
import com.example.macyaren.sportman.helper.PingYinTool;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hennzr on 2016/4/14 23:36
 * Project name is Sportman
 */
public class ActivityCitySelectionModel implements ActivityCitySelectionModelInterator {

	public List<String> listGroup;
	public List<List<String>> listChild;
	public Map<String, List<String>> listMap;
	public List<String> listGroupTemp;
	public PingYinTool pingYinTool;

	public ActivitiesCitySelection_Handler activitiesCitySelection_Handler;

	public static Boolean status = false;

	public CityHandler CityHandler = new CityHandler(this);

	public WeakReference<ActivityCitySelection> activityCitySelectionWeakReference;
	public ActivityCitySelection activityCitySelection;

	public ActivityCitySelectionExpandableListAdapter activityCitySelectionExpandableListAdapter;

	public ActivityCitySelectionModel(final ActivityCitySelection activityCitySelection) {

		if (activityCitySelectionExpandableListAdapter == null) {
			activityCitySelectionExpandableListAdapter =
					ActivityCitySelectionExpandableListAdapter.getInstance(activityCitySelection);
		}

		/*
		* 实例化弱引用
		* */
		this.activityCitySelectionWeakReference = new WeakReference<>(activityCitySelection);
		this.activityCitySelection = activityCitySelectionWeakReference.get();

		/*
		* 实例化handler
		* */
		activitiesCitySelection_Handler = new ActivitiesCitySelection_Handler
				(this.activityCitySelection);

		/*创建拼音工具类实例*/
		pingYinTool = new PingYinTool();

	}

	@Override
	public void setActivityCitySelectionExpandableList() {
		/*
		* 新开线程处理城市名
		* */
		new Thread(new Runnable() {
			@Override
			public void run() {
				listGroup = new ArrayList<>();
				listChild = new ArrayList<>();
				listMap = new HashMap<>();
				listGroupTemp = new ArrayList<>();
				listGroup.add("#");
				listGroup.add("$");
				listGroup.add("*");
				List<String> list_loca = new ArrayList<>();
				for (int i = 0; i < ActivityCitySelectionExpandableListData.CITY_LOCA.length; i++) {
					list_loca.add(ActivityCitySelectionExpandableListData.CITY_LOCA[i]);
				}
				listChild.add(list_loca);

				List<String> list_history = new ArrayList<>();
				for (int i = 0; i < ActivityCitySelectionExpandableListData.CITY_HISTORY.length; i++) {
					list_history.add(ActivityCitySelectionExpandableListData.CITY_HISTORY[i]);
				}
				listChild.add(list_history);

				List<String> list_hot = new ArrayList<>();
				for (int i = 0; i < ActivityCitySelectionExpandableListData.CITY_HOT.length; i++) {
					list_hot.add(ActivityCitySelectionExpandableListData.CITY_HOT[i]);
				}
				listChild.add(list_hot);

				XmlResourceParser city_parse = activityCitySelection.getResources().getXml(R
						.xml
						.cities);
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
						//						Log.i("ZRH","listChild size : "+listChild.size());
					}
				}
				listGroup.addAll(listGroupTemp);
				//				Log.i("ZRH", "listGroup size : " + listGroup.size());
				status = true;
				CityHandler.sendEmptyMessage(0x123);
			}
		}).start();

	}

	static class CityHandler extends Handler {

		public WeakReference<ActivityCitySelectionModel> activityCitySelectionModelWeakReference;
		public ActivityCitySelectionModel activityCitySelectionModel;

		public CityHandler(ActivityCitySelectionModel activityCitySelectionModel) {
			this.activityCitySelectionModelWeakReference = new WeakReference<>
					(activityCitySelectionModel);
			this.activityCitySelectionModel = activityCitySelectionModelWeakReference.get();
		}

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x123) {
				activityCitySelectionModel.activityCitySelectionExpandableListAdapter
						.setListChild(activityCitySelectionModel.listChild);
				activityCitySelectionModel.activityCitySelectionExpandableListAdapter
						.setListGroup(activityCitySelectionModel.listGroup);
				activityCitySelectionModel.activityCitySelectionExpandableListAdapter
						.notifyDataSetChanged();
				activityCitySelectionModel.activitiesCitySelection_Handler.sendEmptyMessage
						(ActivityCitySelection.LOADING_CITY_NAME);
			}
		}
	}

}
