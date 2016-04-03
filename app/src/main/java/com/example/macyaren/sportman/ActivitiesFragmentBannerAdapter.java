package com.example.macyaren.sportman;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by hennzr on 2016/3/8.
 */
public class ActivitiesFragmentBannerAdapter extends PagerAdapter {

	List<View> list;

	public ActivitiesFragmentBannerAdapter() {
	}

	public void setList(List<View> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

	@Override
	public Object instantiateItem(final ViewGroup container, final int position) {
		/*
		* 为每个页面设置监听
		* */
		View view = list.get(position);
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//				v.getParent().requestDisallowInterceptTouchEvent(true);
				Log.i("ZRH", "item" + position + " was clicked");
				Toast.makeText(v.getContext().getApplicationContext(), "Item" + position + " was clicked",
						Toast.LENGTH_SHORT).show();
			}
		});
		view.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getActionMasked()) {
					//					case MotionEvent.ACTION_DOWN:
					//						v.getParent().requestDisallowInterceptTouchEvent(true);
					//						return true;
					case MotionEvent.ACTION_MOVE:
						return true;
				}
				return false;
			}
		});

		container.addView(list.get(position));
		return list.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list.get(position));
	}
}
