package com.example.macyaren.sportman.message;

import com.example.macyaren.sportman.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hennzr on 2016/3/28.
 */
public class MessageLeftListNewsRecyclerViewData {

	public SimpleDateFormat df = new SimpleDateFormat("MM-dd hh:mm:ss");
	public String[] DATES = {};
	public String[] TITLES = {};
	public int[] TOP_POSTS = {};
	public String[] SUB_TITLE_FIRSTS = {};
	public String[] SUB_TITLE_SECONDS = {};
	public int[] SUB_POST_FIRSTS = {};
	public int[] SUB_POST_SECONDS = {};

	public MessageLeftListNewsRecyclerViewData() {
		Date[] ds = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 0, 12, 12, 30, 50);
		ds[0] = calendar.getTime();
		calendar.set(2016, 0, 13, 17, 23, 12);
		ds[1] = calendar.getTime();
		DATES = new String[]{df.format(ds[0]), df.format(ds[1])};
		TITLES = new String[]{"足球:春茧足球场青春杯已开启报名！", "足球:春茧足球场青春杯已开启报名！"};
		TOP_POSTS = new int[]{R.drawable.message_left_list_news_post_top,
				R.drawable.message_left_list_news_post_top};
		SUB_TITLE_FIRSTS = new String[]{"大众健身暑期培训  抢先体验", "大众健身暑期培训  抢先体验"};
		SUB_TITLE_SECONDS = new String[]{"大众健身暑期培训  抢先体验", "大众健身暑期培训  抢先体验"};
		SUB_POST_FIRSTS = new int[]{R.drawable.message_left_list_news_post_bottom,
				R.drawable.message_left_list_news_post_bottom};
		SUB_POST_SECONDS = new int[]{R.drawable.message_left_list_news_post_bottom,
				R.drawable.message_left_list_news_post_bottom};
	}

}
