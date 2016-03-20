package com.example.macyaren.sportman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by hennzr on 2016/3/20.
 */
public class ActivitiesDetailCommentsListAdapter extends BaseAdapter {

	LayoutInflater inflater;
	Context context;
	List<String> list;


	public ActivitiesDetailCommentsListAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ActivitiesDetailCommentsListHolder activitiesDetailCommentsListHolder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.listitem,parent,false);
			activitiesDetailCommentsListHolder = new ActivitiesDetailCommentsListHolder();
			activitiesDetailCommentsListHolder.comments = (android.widget.TextView) convertView
					.findViewById(R.id.comments);
			convertView.setTag(activitiesDetailCommentsListHolder);
		}else {
			activitiesDetailCommentsListHolder = (ActivitiesDetailCommentsListHolder) convertView
					.getTag();
		}
		String comment = list.get(position);
		activitiesDetailCommentsListHolder.comments.setText(comment);
		return convertView;
	}
}
