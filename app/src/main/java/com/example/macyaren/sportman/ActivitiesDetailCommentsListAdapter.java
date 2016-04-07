package com.example.macyaren.sportman;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

/**
 * Created by hennzr on 2016/3/20.
 */
public class ActivitiesDetailCommentsListAdapter extends BaseAdapter {

	LayoutInflater inflater;
	ActivitiesDetail activitiesDetail;
	List<ActivitiesDetailCommentsListCommInfo> list;
	WeakReference<ActivitiesDetail> activitiesDetailWeakReference;


	public ActivitiesDetailCommentsListAdapter(ActivitiesDetail activitiesDetail) {
//		this.context = context;
		activitiesDetailWeakReference = new WeakReference<ActivitiesDetail>(activitiesDetail);
		this.activitiesDetail = activitiesDetailWeakReference.get();
		inflater = LayoutInflater.from(activitiesDetail);
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

	public void setList(List<ActivitiesDetailCommentsListCommInfo> list) {
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ActivitiesDetailCommentsListHolder activitiesDetailCommentsListHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.activities_detail_comments_list, parent, false);
			activitiesDetailCommentsListHolder = new ActivitiesDetailCommentsListHolder();
			activitiesDetailCommentsListHolder.photo = (ImageView) convertView
					.findViewById(R.id.activities_detail_comments_list_photo);
			activitiesDetailCommentsListHolder.uname = (TextView) convertView
					.findViewById(R.id.activities_detail_comments_list_name);
			activitiesDetailCommentsListHolder.comment = (TextView) convertView.findViewById(R.id
					.activities_detail_comments_list_comment);
			activitiesDetailCommentsListHolder.praise = (TextView) convertView.findViewById(R.id
					.activities_detail_comments_list_praise);
			activitiesDetailCommentsListHolder.good = (ImageView) convertView.findViewById(R.id
					.activities_detail_comments_list_good);
			activitiesDetailCommentsListHolder.date = (TextView) convertView.findViewById(R.id
					.activities_detail_comments_list_date);
			convertView.setTag(activitiesDetailCommentsListHolder);
		} else {
			activitiesDetailCommentsListHolder = (ActivitiesDetailCommentsListHolder) convertView
					.getTag();
		}
		ActivitiesDetailCommentsListCommInfo activitiesDetailCommentsListCommInfo = list.get
				(position);
		activitiesDetailCommentsListHolder.photo.setImageResource
				(activitiesDetailCommentsListCommInfo.photo);
		activitiesDetailCommentsListHolder.uname.setText(activitiesDetailCommentsListCommInfo
				.uname);
		activitiesDetailCommentsListHolder.comment.setText(activitiesDetailCommentsListCommInfo
				.comment);
		activitiesDetailCommentsListHolder.praise.setText(String.valueOf
				(activitiesDetailCommentsListCommInfo.praise));
		String name = activitiesDetailCommentsListCommInfo.uname;
		if(Objects.equals(name, "天上的日")){
			activitiesDetailCommentsListHolder.good.setImageResource(R.drawable.praise_small);
		}
		activitiesDetailCommentsListHolder.date.setText(activitiesDetailCommentsListCommInfo.date);
		return convertView;
	}
}
