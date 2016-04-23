package com.example.macyaren.sportman.activities.model;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macyaren.sportman.R;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivitiesFragmentListHolder;
import com.example.macyaren.sportman.activities.model.dataHelper.ActivitiesFragmentListInfo;
import com.example.macyaren.sportman.main.view.MainActivity;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by hennzr on 2016/4/18 0:44
 * Project name is Sportman
 */
public class ActivitiesFragmentListAdapter extends BaseAdapter {

	public LayoutInflater inflater;
	public List<ActivitiesFragmentListInfo> list;

	public static ActivitiesFragmentListAdapter activitiesFragmentListAdapter = null;

	public WeakReference<MainActivity> mainActivityWeakReference;
	public MainActivity mainActivity;

	//	public Context context;

	public ActivitiesFragmentListAdapter(MainActivity mainActivity) {
		this.mainActivityWeakReference = new WeakReference<>(mainActivity);
		this.mainActivity = mainActivityWeakReference.get();
	}

	//	public ActivitiesFragmentListAdapter(Context context) {
	//		this.context = context;
	//	}

	synchronized public static ActivitiesFragmentListAdapter getInstance(MainActivity mainActivity) {
		if (activitiesFragmentListAdapter == null) {
			activitiesFragmentListAdapter = new ActivitiesFragmentListAdapter(mainActivity);
//			Log.i("ZRH", "ActivitiesFragmentListAdapter getInstance");
		}
		return activitiesFragmentListAdapter;
	}

	public void setList(List<ActivitiesFragmentListInfo> list) {
		this.list = list;
	}

	public void addList(List<ActivitiesFragmentListInfo> listInfos) {
		this.list.addAll(listInfos);
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ActivitiesFragmentListHolder holder = new ActivitiesFragmentListHolder();
		inflater = LayoutInflater.from(mainActivity);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.activities_fragment_listview, parent, false);
			holder.photo = (ImageView) convertView.findViewById(R.id.activities_fragment_list_photo);
			holder.name = (TextView) convertView.findViewById(R.id.activity_name);
			holder.hold = (TextView) convertView.findViewById(R.id.activity_hold);
			holder.area = (TextView) convertView.findViewById(R.id.activity_area);
			holder.distance = (TextView) convertView.findViewById(R.id
					.activity_distance);
			holder.join = (TextView) convertView.findViewById(R.id.activity_join);
			holder.price = (TextView) convertView.findViewById(R.id.activity_price);
			holder.date = (TextView) convertView.findViewById(R.id.activity_date);
			convertView.setTag(holder);
		} else {
			holder = (ActivitiesFragmentListHolder) convertView.getTag();
		}
		ActivitiesFragmentListInfo afInfo = list.get(position);
		holder.photo.setImageResource(afInfo.photo);
		holder.name.setText(afInfo.name);
		holder.hold.setText(afInfo.hold);
		holder.area.setText(afInfo.area);
		holder.distance.setText(afInfo.distance);
		holder.join.setText("30");
		holder.join.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
		holder.join.setGravity(Gravity.CENTER);
		holder.join.setTextColor(mainActivity.getResources().getColor(R.color.md_white_1000));
		holder.price.setText(afInfo.price);
		holder.date.setText(afInfo.date);
		return convertView;
	}

}
