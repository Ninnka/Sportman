package com.example.macyaren.sportman.activities.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macyaren.sportman.R;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivitiesDetailCommentsListCommInfo;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivitiesDetailCommentsListHolder;
import com.example.macyaren.sportman.activities.view.ActivitiesDetail;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

/**
 * Created by hennzr on 2016/3/20 22:29
 * Project name is Sportman
 */
public class ActivitiesDetailCommentsListAdapter extends BaseAdapter {

	List<ActivitiesDetailCommentsListCommInfo> list;
	LayoutInflater inflater;
	ActivitiesDetail activitiesDetail;
	WeakReference<ActivitiesDetail> activitiesDetailWeakReference;

//	public static ActivitiesDetailCommentsListAdapter activitiesDetailCommentsListAdapter = null;

	public ActivitiesDetailCommentsListAdapter(ActivitiesDetail activitiesDetail) {
		activitiesDetailWeakReference = new WeakReference<>(activitiesDetail);
		this.activitiesDetail = activitiesDetailWeakReference.get();
	}

//	public static ActivitiesDetailCommentsListAdapter getInstance(ActivitiesDetail activitiesDetail) {
//		if(activitiesDetailCommentsListAdapter == null){
//			activitiesDetailCommentsListAdapter = new ActivitiesDetailCommentsListAdapter
//					(activitiesDetail);
//		}
//		return activitiesDetailCommentsListAdapter;
//	}

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

	public void addList(ActivitiesDetailCommentsListCommInfo activitiesDetailCommentsListCommInfo){
		this.list.add(activitiesDetailCommentsListCommInfo);
	}

	public void clearList(){
		this.list.clear();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		inflater = LayoutInflater.from(activitiesDetail);
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
