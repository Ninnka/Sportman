package com.example.macyaren.sportman.message;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macyaren.sportman.R;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by hennzr on 2016/4/9 10:57
 * Package in com.example.macyaren.sportman
 * Project name is Sportman
 */
public class MessageLeftListChat_Single_GroupListRecyclerViewAdapter extends RecyclerView
		.Adapter<RecyclerView.ViewHolder> {

	protected LayoutInflater inflater;
	protected WeakReference<MessageLeftListChat_Single_Group>
			messageLeftListChat_single_groupWeakReference;
	protected List<MessageLeftListChat_Single_GroupListRecyclerViewInfo> list;

	protected enum ITEM_TYPE {
		ITEM_SYSTEM_DATE,
		ITEM_ME,
		ITEM_OTHER
	}

	public MessageLeftListChat_Single_GroupListRecyclerViewAdapter
			(MessageLeftListChat_Single_Group messageLeftListChat_single_group) {
		this.messageLeftListChat_single_groupWeakReference = new
				WeakReference<>(messageLeftListChat_single_group);
		inflater = LayoutInflater.from(messageLeftListChat_single_groupWeakReference.get());
	}

	public void setList(List<MessageLeftListChat_Single_GroupListRecyclerViewInfo> list) {
		this.list = list;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == ITEM_TYPE.ITEM_SYSTEM_DATE.ordinal()) {
			return new MessageLeftListChat_Single_GroupListRecyclerViewHolder_SystemDate(inflater
					.inflate(R.layout
							.message_left_list_chat_single_group_item_systemdate, parent, false));
		}else if (viewType == ITEM_TYPE.ITEM_ME.ordinal()) {
			return new MessageLeftListChat_Single_GroupListRecyclerViewHolder_Me(inflater.inflate(R
					.layout.message_left_list_chat_single_group_item_me, parent, false));
		}else if (viewType == ITEM_TYPE.ITEM_OTHER.ordinal()) {
			return new MessageLeftListChat_Single_GroupListRecyclerViewHolder_Other(inflater.inflate(R
					.layout.message_left_list_chat_single_group_item_other, parent, false));
		}
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		MessageLeftListChat_Single_GroupListRecyclerViewInfo
				messageLeftListChat_single_groupListRecyclerViewInfo = list.get(position);
		if (holder instanceof MessageLeftListChat_Single_GroupListRecyclerViewHolder_Me) {
//			Log.i("ZRH", "onBindViewHolder: holder instanceof ViewHolder_Me");
			((MessageLeftListChat_Single_GroupListRecyclerViewHolder_Me) holder).message.setText
					(messageLeftListChat_single_groupListRecyclerViewInfo.message);
			((MessageLeftListChat_Single_GroupListRecyclerViewHolder_Me) holder).profile
					.setImageResource(messageLeftListChat_single_groupListRecyclerViewInfo.profile);
		} else if (holder instanceof MessageLeftListChat_Single_GroupListRecyclerViewHolder_Other) {
//			Log.i("ZRH", "onBindViewHolder: holder instanceof ViewHolder_Other");
			((MessageLeftListChat_Single_GroupListRecyclerViewHolder_Other) holder).message
					.setText(messageLeftListChat_single_groupListRecyclerViewInfo.message);
			((MessageLeftListChat_Single_GroupListRecyclerViewHolder_Other) holder).profile
					.setImageResource(messageLeftListChat_single_groupListRecyclerViewInfo.profile);
		} else if (holder instanceof MessageLeftListChat_Single_GroupListRecyclerViewHolder_SystemDate) {
//			Log.i("ZRH", "onBindViewHolder: holder instanceof ViewHolder_SystemDate");
			((MessageLeftListChat_Single_GroupListRecyclerViewHolder_SystemDate) holder).date
					.setText(messageLeftListChat_single_groupListRecyclerViewInfo.message);
		}
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	@Override
	public int getItemViewType(int position) {
		MessageLeftListChat_Single_GroupListRecyclerViewInfo
				messageLeftListChat_single_groupListRecyclerViewInfo = list.get(position);
		String item_type = messageLeftListChat_single_groupListRecyclerViewInfo.type;
		int return_type = -1;
		switch (item_type) {
			case "date":
				return_type = ITEM_TYPE.ITEM_SYSTEM_DATE.ordinal();
				break;
			case "chat_me":
				return_type = ITEM_TYPE.ITEM_ME.ordinal();
				break;
			case "chat_other":
				return_type = ITEM_TYPE.ITEM_OTHER.ordinal();
				break;
			default:
				Log.i("ZRH", "聊天信息中无此类型");
				break;
		}
		return return_type;
	}

	static class MessageLeftListChat_Single_GroupListRecyclerViewHolder_Me extends RecyclerView
			.ViewHolder {

		public ImageView profile;
		public TextView message;

		public MessageLeftListChat_Single_GroupListRecyclerViewHolder_Me(View itemView) {
			super(itemView);
			message = (TextView) itemView.findViewById(R.id
					.message_left_list_chat_single_group_item_me_message_tv);
			profile = (ImageView) itemView.findViewById(R.id
					.message_left_list_chat_single_group_item_me_profile);
		}

	}

	static class MessageLeftListChat_Single_GroupListRecyclerViewHolder_Other extends RecyclerView
			.ViewHolder {

		public ImageView profile;
		public TextView message;

		public MessageLeftListChat_Single_GroupListRecyclerViewHolder_Other(View itemView) {
			super(itemView);
			message = (TextView) itemView.findViewById(R.id
					.message_left_list_chat_single_group_item_other_message_tv);
			profile = (ImageView) itemView.findViewById(R.id
					.message_left_list_chat_single_group_item_other_profile);
		}

	}

	static class MessageLeftListChat_Single_GroupListRecyclerViewHolder_SystemDate extends
			RecyclerView.ViewHolder {

		public TextView date;

		public MessageLeftListChat_Single_GroupListRecyclerViewHolder_SystemDate(View itemView) {
			super(itemView);
			date = (TextView) itemView.findViewById(R.id
					.message_left_list_chat_single_group_item_systemdate_tv);
		}

	}

}
