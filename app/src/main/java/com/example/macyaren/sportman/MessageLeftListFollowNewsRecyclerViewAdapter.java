package com.example.macyaren.sportman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hennzr on 2016/3/27.
 */
public class MessageLeftListFollowNewsRecyclerViewAdapter extends RecyclerView.Adapter<MessageLeftListFollowNewsRecyclerViewHolder> {

	public Context context;
	public List<MessageLeftListFollowNewsRecyclerViewInfo> list;
	public LayoutInflater inflater;
	public MessageLeftListFollowNewsRecyclerViewInfo messageLeftListFollowNewsRecyclerViewInfo;

	public MessageLeftListFollowNewsRecyclerViewAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void setList(List<MessageLeftListFollowNewsRecyclerViewInfo> list) {
		this.list = list;
	}

	@Override
	public MessageLeftListFollowNewsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		MessageLeftListFollowNewsRecyclerViewHolder recyclerViewHolder = new
				MessageLeftListFollowNewsRecyclerViewHolder(inflater.inflate(R.layout
				.message_left_list_follow_news_item, parent, false));
		return recyclerViewHolder;
	}

	@Override
	public void onBindViewHolder(MessageLeftListFollowNewsRecyclerViewHolder holder, int position) {
		messageLeftListFollowNewsRecyclerViewInfo = list.get(position);
		holder.date_tv.setText(messageLeftListFollowNewsRecyclerViewInfo.date);
		holder.title_tv.setText(messageLeftListFollowNewsRecyclerViewInfo.title);
		holder.post_img.setImageResource(messageLeftListFollowNewsRecyclerViewInfo.top_post);
		holder.sub_title_first_tv.setText(messageLeftListFollowNewsRecyclerViewInfo.sub_title_first);
		holder.sub_title_second_tv.setText(messageLeftListFollowNewsRecyclerViewInfo.sub_title_second);
		holder.sub_post_first_img.setImageResource(messageLeftListFollowNewsRecyclerViewInfo.sub_post_first);
		holder.sub_post_second_img.setImageResource(messageLeftListFollowNewsRecyclerViewInfo.sub_post_second);
	}

	@Override
	public int getItemCount() {
		return list.size();
	}
}
