package com.example.macyaren.sportman;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hennzr on 2016/3/1.
 */
public class ActivitiesFragmentListAdapter extends BaseAdapter {

	public LayoutInflater inflater;
	public Context context;
	public List<ActivitiesFragmentListInfo> list;

	public ActivitiesFragmentListAdapter(Context context) {
		this.context = context;
	}

	public void setList(List<ActivitiesFragmentListInfo> list) {
		this.list = list;
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
		inflater = LayoutInflater.from(context);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.activities_fragment_listview, null);
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
		holder.join.setTextColor(context.getResources().getColor(R.color.md_white_1000));
		holder.price.setText(afInfo.price);
		holder.date.setText(afInfo.date);
		return convertView;
	}
}
