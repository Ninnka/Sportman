package com.example.macyaren.sportman;

import java.util.Random;

/**
 * Created by hennzr on 2016/3/2.
 */
public class ActivitiesFragmentListData {

	public int[] IMAGE_ID = {R.drawable.activities_fragment_list_1,
			R.drawable.activities_fragment_list_2, R.drawable.activities_fragment_list_3,
			R.drawable.activities_fragment_list_4, R.drawable.activities_fragment_list_5};

	public String[] NAME = {"广州马拉松", "白云山野战场畅玩", "滑轮逛街活动", "胜利乒乓球运动场", "荧光夜跑"};

	public String[] AREA = {"海珠区", "白云区", "海珠区", "白云区", "海珠区"};

	public String[] DISTANCE = {"1.3KM", "7.2KM", "37.2KM", "0.8KM", "26.4KM"};

	public String[] HOLD = {"广州体育委员会", "白云山野战场", "阿迪王专业体育用具", "胜利运动场", "阿迪王专业体育用具"};

	public int[] JOIN = new int[5];

	public String[] PRICE = {"¥ 99", "off 50%", "¥ 88", "¥ 66", "off 80%"};

	public String[] DATE = {"01-27", "至 03-25", "至 07-14", "07-15", "02-28"};

	public ActivitiesFragmentListData() {
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			JOIN[i] = (random.nextInt() * 3) * 40;
		}
	}
}
