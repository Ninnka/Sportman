package com.example.macyaren.sportman.message;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macyaren.sportman.R;

/**
 * Created by hennzr on 2016/3/27.
 */
public class MessageLeftListFollowNewsRecyclerViewHolder extends RecyclerView.ViewHolder {

	public TextView date_tv;
	public ImageView post_img;
	public TextView title_tv;
	public TextView sub_title_first_tv;
	public TextView sub_title_second_tv;
	public ImageView sub_post_first_img;
	public ImageView sub_post_second_img;

	public MessageLeftListFollowNewsRecyclerViewHolder(View itemView) {
		super(itemView);
		date_tv = (TextView) itemView.findViewById(R.id.message_left_list_follow_date);
		post_img = (ImageView) itemView.findViewById(R.id.message_left_list_follow_post);
		title_tv = (TextView) itemView.findViewById(R.id.message_left_list_follow_title);
		sub_title_first_tv = (TextView) itemView.findViewById(R.id
				.message_left_list_follow_subtitle_1);
		sub_title_second_tv = (TextView) itemView.findViewById(R.id
				.message_left_list_follow_subtitle_2);
		sub_post_first_img = (ImageView) itemView.findViewById(R.id
				.message_left_list_follow_subpost_1);
		sub_post_second_img = (ImageView) itemView.findViewById(R.id
				.message_left_list_follow_subpost_2);
	}
}
