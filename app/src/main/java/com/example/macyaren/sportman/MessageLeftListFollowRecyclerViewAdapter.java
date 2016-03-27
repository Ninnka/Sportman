package com.example.macyaren.sportman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hennzr on 2016/3/27.
 */
public class MessageLeftListFollowRecyclerViewAdapter extends RecyclerView.Adapter<MessageLeftListFollowRecyclerViewHolder> {

	public Context context;
	public List<MessageLeftListFollowRecyclerViewInfo> list;
	public LayoutInflater inflater;

	public MessageLeftListFollowRecyclerViewAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void setList(List<MessageLeftListFollowRecyclerViewInfo> list) {
		this.list = list;
	}

	@Override
	public MessageLeftListFollowRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		MessageLeftListFollowRecyclerViewHolder recyclerViewHolder = new
				MessageLeftListFollowRecyclerViewHolder(inflater.inflate(R.layout
				.message_left_list_follow_item, parent, false));
		return recyclerViewHolder;
	}

	@Override
	public void onBindViewHolder(MessageLeftListFollowRecyclerViewHolder holder, int position) {
		holder.date_tv.setText(list.get(position).date);
	}

	@Override
	public int getItemCount() {
		return list.size();
	}
}
