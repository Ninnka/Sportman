package com.example.macyaren.sportman.message;

import com.example.macyaren.sportman.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hennzr on 2016/3/27.
 */
public class MessageLeftListFollowRecyclerViewData {

	public SimpleDateFormat df = new SimpleDateFormat("MM-dd hh:mm:ss");
	public String[] DATES = {};
	public String[] TITLES = {};
	public int[] TOP_POSTS = {};
	public String[] SUB_TITLE_FIRSTS = {};
	public String[] SUB_TITLE_SECONDS = {};
	public int[] SUB_POST_FIRSTS = {};
	public int[] SUB_POST_SECONDS = {};

	public MessageLeftListFollowRecyclerViewData() {
		Date[] ds = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 0, 12, 12, 30, 50);
		ds[0] = calendar.getTime();
		calendar.set(2016, 0, 13, 17, 23, 12);
		ds[1] = calendar.getTime();
		DATES = new String[]{df.format(ds[0]), df.format(ds[1])};
		TITLES = new String[]{"HOT:阿迪达斯最新款体育套装", "HOT:阿迪达斯最新款体育套装"};
		TOP_POSTS = new int[]{R.drawable.message_left_list_follow_post_top,
				R.drawable.message_left_list_follow_post_top};
		SUB_TITLE_FIRSTS = new String[]{"阿迪王与阿迪达斯私下和解", "阿迪王与阿迪达斯私下和解"};
		SUB_TITLE_SECONDS = new String[]{"阿迪达斯经典跑鞋五折销售", "阿迪达斯经典跑鞋五折销售"};
		SUB_POST_FIRSTS = new int[]{R.drawable.message_left_list_follow_post_bottom1,
				R.drawable.message_left_list_follow_post_bottom1};
		SUB_POST_SECONDS = new int[]{R.drawable.message_left_list_follow_post_bottom2,
				R.drawable.message_left_list_follow_post_bottom2};
	}
}
