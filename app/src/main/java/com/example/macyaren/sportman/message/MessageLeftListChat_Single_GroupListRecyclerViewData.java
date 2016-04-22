package com.example.macyaren.sportman.message;

import com.example.macyaren.sportman.R;

/**
 * Created by hennzr on 2016/4/8 15:47
 * Package in com.example.macyaren.sportman
 * Project name is Sportman
 */
public class MessageLeftListChat_Single_GroupListRecyclerViewData {

	public int DATA_LENGHT;

	public String[] TYPE = {"date", "chat_me", "chat_other", "chat_me", "chat_other", "chat_me",
			"chat_other", "chat_me", "chat_other", "chat_me", "chat_other", "chat_me",
			"chat_other", "chat_me", "chat_other",};
	public String[] USERNAME = {"system", "Tiffany", "马爹", "Tiffany", "马爹", "Tiffany",
			"马爹", "Tiffany", "马爹", "Tiffany", "马爹", "Tiffany", "马爹", "Tiffany", "马爹"};
	public int[] PROFILE = {-1, R.drawable.message_left_list_chat_profile_me, R
			.drawable.message_left_list_chat_profile_other_madie, R.drawable.message_left_list_chat_profile_me, R
			.drawable.message_left_list_chat_profile_other_madie, R.drawable.message_left_list_chat_profile_me, R
			.drawable.message_left_list_chat_profile_other_madie, R.drawable.message_left_list_chat_profile_me, R
			.drawable.message_left_list_chat_profile_other_madie, R.drawable.message_left_list_chat_profile_me, R
			.drawable.message_left_list_chat_profile_other_madie, R.drawable.message_left_list_chat_profile_me, R
			.drawable.message_left_list_chat_profile_other_madie, R.drawable.message_left_list_chat_profile_me, R
			.drawable.message_left_list_chat_profile_other_madie};
	public String[] MESSAGE = {"01-25 18:06", "爸爸", "唉~", "太阳出来月亮回家了吗？", "对了",
			"星星出来太阳去哪里了？", "在天上", "我怎么找也找不到他？", "他回家了", "那要怎么找", "回家了怎么找", "对啊，怎么找", "都回家了怎么找",
			"我就问你怎么找啊，你还反问我怎么找，你到底懂不懂啊", "卧槽,放学别走"};

	public MessageLeftListChat_Single_GroupListRecyclerViewData() {
		this.DATA_LENGHT = MESSAGE.length;
	}
}
