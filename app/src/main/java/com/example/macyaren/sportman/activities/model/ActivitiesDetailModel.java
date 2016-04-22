package com.example.macyaren.sportman.activities.model;

import com.example.macyaren.sportman.activities.interator.ActivitiesDetailModelInterator;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivitiesDetailCommentsListCommData;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivitiesDetailCommentsListCommInfo;
import com.example.macyaren.sportman.activities.view.ActivitiesDetail;

/**
 * Created by hennzr on 2016/4/14 16:12
 * Package in com.example.macyaren.sportman.activities.model
 * Project name is Sportman
 */
public class ActivitiesDetailModel implements ActivitiesDetailModelInterator {

	/*
	* 用于计算的变量
	* */
	public ActivitiesDetailCommentsListCommData activitiesDetailCommentsListCommData;
	public ActivitiesDetailCommentsListCommInfo[] activitiesDetailCommentsListCommInfo;

	/*
	* 需要返回的变量
	* */
	String activities_detail_registration_more = "";
	String activities_detail_process_more = "";
//	List<ActivitiesDetailCommentsListCommInfo> list;

	public ActivitiesDetailModel() {
//		list = new ArrayList<>();
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
	}

	@Override
	public String getActivities_detail_registration_more() {

		/*
		* 假设这里经过一系列的逻辑判断以及网络请求
		* do something in network...
		* 从服务器返回了最终的结果
		* */

		activities_detail_registration_more = "登记材料：身份证明，联系方式等";
		return activities_detail_registration_more;
	}

	@Override
	public String getActivities_detail_process_more() {

		/*
		* 假设这里经过一系列的逻辑判断以及网络请求
		* do something in network...
		* 从服务器返回了最终的结果
		* */

		activities_detail_process_more = "II. Race Course \n(I) Mini Marathon: \n" +
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
				" Linjiang Ave. → Huacheng Square (Finishing Point)";
		return activities_detail_process_more;
	}

	@Override
	public void getActivities_detail_comment_more() {

		/*
		* 假设这里经过了一系列的逻辑判断以及网络请求
		* do something in network...
		* 得到了最终的结果
		* 以下模拟获得数据的过程
		* */

		for (int i = 0; i < 2; i++) {
			ActivitiesDetail.list.add(activitiesDetailCommentsListCommInfo[i]);
		}
	}

	@Override
	public void resetActivities_detail_comment() {

		/*
		* 数据清除操作
		* 无需访问网络
		* */
		ActivitiesDetail.list.clear();
	}
}
