package com.example.macyaren.sportman.message;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.macyaren.sportman.R;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by hennzr on 2016/3/27.
 */
public class MessageLeftListFollowNewsRecyclerViewAdapter extends RecyclerView.Adapter<MessageLeftListFollowNewsRecyclerViewHolder> {

	public WeakReference<MessageLeftListFollowNews> messageLeftListFollowNewsWeakReference;
	public List<MessageLeftListFollowNewsRecyclerViewInfo> list;
	public LayoutInflater inflater;
	public MessageLeftListFollowNewsRecyclerViewInfo messageLeftListFollowNewsRecyclerViewInfo;

	public MessageLeftListFollowNewsRecyclerViewAdapter(MessageLeftListFollowNews
																messageLeftListFollowNews) {
		messageLeftListFollowNewsWeakReference = new WeakReference<>(messageLeftListFollowNews);
		inflater = LayoutInflater.from(messageLeftListFollowNewsWeakReference.get());
	}

	public void setList(List<MessageLeftListFollowNewsRecyclerViewInfo> list) {
		this.list = list;
	}

	@Override
	public MessageLeftListFollowNewsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new
				MessageLeftListFollowNewsRecyclerViewHolder(inflater.inflate(R.layout
				.message_left_list_follow_news_item, parent, false));
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
