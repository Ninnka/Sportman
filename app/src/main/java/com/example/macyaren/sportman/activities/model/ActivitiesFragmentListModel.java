package com.example.macyaren.sportman.activities.model;

import com.example.macyaren.sportman.activities.interator.ActivitiesFragmentListModelInterator;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivitiesFragmentListData;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivitiesFragmentListInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hennzr on 2016/4/14 15:16
 * Package in com.example.macyaren.sportman.activities.model
 * Project name is Sportman
 */
public class ActivitiesFragmentListModel implements ActivitiesFragmentListModelInterator {

	/*
	* 用于计算过程的变量
	* */
	ActivitiesFragmentListInfo[] activitiesFragmentListInfos;
	ActivitiesFragmentListData activitiesFragmentListData;

	List<ActivitiesFragmentListInfo> activitiesFragmentListInfoList;

//	ActivitiesFragmentListAdapter activitiesFragmentListAdapter;

	public ActivitiesFragmentListModel() {
		initActivitiesFragmentList();
	}

	@Override
	public void getActivitiesFragmentList(ActivitiesFragmentListAdapter activitiesFragmentListAdapter) {
//		if(activitiesFragmentListAdapter == null){
//			activitiesFragmentListAdapter = ActivitiesFragmentListAdapter.getInstance(mainActivity);
//		}
		activitiesFragmentListAdapter.addList(activitiesFragmentListInfoList);
	}

	public void initActivitiesFragmentList() {

		/*
		* 创建activities中活动list的有关对象
		* 初始化活动列表数据
		* */
		activitiesFragmentListInfos = new ActivitiesFragmentListInfo[5];
		activitiesFragmentListData = new ActivitiesFragmentListData();
		activitiesFragmentListInfoList = new ArrayList<>();

		/*
		* 假设这里经过了一系列的逻辑判断以及网络请求
		* 得到了最终的数据
		* do something in network...
		* 初始化活动列表信息
		* */
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
}
