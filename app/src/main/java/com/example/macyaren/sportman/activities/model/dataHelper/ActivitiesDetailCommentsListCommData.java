package com.example.macyaren.sportman.activities.model.dataHelper;

import com.example.macyaren.sportman.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hennzr on 2016/3/21.
 */
public class ActivitiesDetailCommentsListCommData {

	public SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public int[] PHOTOS = new int[]{R.drawable.activity_detail_content_comments_profile_1,
			R.drawable.activity_detail_content_comments_profile_2};

	public String[] UNAMES = new String[]{"广州凉风", "天上的日"};

	public String[] COMMENTS = new
			String[]{"第一次参加广深马拉松的时候，还是刚出世两个月不到，一手提着纸尿片，一手摆动保持平衡，就这样跑呀跑，" +
			"直到筋疲力尽，我才跑完行程的一半，灰心丧气的我放慢了脚步，打算在下一个休息站放弃，殊不知，中转站居" +
			"然站着一个漂亮长腿大姐姐，哎呀我去，真是好，嘿嘿嘿，受到鼓舞的我决定继续行程，最后完成了整一条路线，" +
			"现在想一想，这就是青春啊。",
			"我就在天上静静的看你们装B。"};

	public long[] PRAISES = new long[]{65535, 12580};

	public String[] DATES;

	public ActivitiesDetailCommentsListCommData() {

		Date[] ds = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 1, 12, 12, 30, 50);
		ds[0] = calendar.getTime();
		calendar.set(2016, 1, 13, 17, 23, 12);
		ds[1] = calendar.getTime();
		DATES = new String[]{df.format(ds[0]), df.format(ds[1])};
	}
}
