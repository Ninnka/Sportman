package com.example.macyaren.sportman;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by hennzr on 2016/4/7 20:28
 * Package in com.example.macyaren.sportman
 * Project name is Sportman
 */
public class VerticalScrollNavigation extends LinearLayout {

	protected TextView navigation_tv;
	protected WeakReference<Activity> activityWeakReference;
	protected VerticalScrollNavigationCallback verticalScrollNavigationCallback;
	protected int navigation_container_height = 0;
	protected int navigation_container_top = 0;

	public interface VerticalScrollNavigationCallback {
		void scrollNavigation(float positionY, int navigation_container_height, int eventAction);
	}

	public void setVerticalScrollNavigationCallback(VerticalScrollNavigationCallback verticalScrollNavigationCallback) {
		this.verticalScrollNavigationCallback = verticalScrollNavigationCallback;
	}

	public VerticalScrollNavigation(Context context) {
		super(context);
	}

	public VerticalScrollNavigation(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public VerticalScrollNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public VerticalScrollNavigation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	protected void setNavigationAttribute(String[] navigationAttribute, Activity activity) {
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
				.LayoutParams.MATCH_PARENT, 0, 1);
		activityWeakReference = new WeakReference<>(activity);

		for (int i = 0; i < navigationAttribute.length; i++) {
			navigation_tv = new TextView(activityWeakReference.get());
			navigation_tv.setText(navigationAttribute[i]);
			//noinspection deprecation
			navigation_tv.setTextColor(getResources().getColor(R.color.md_grey_700));
			navigation_tv.setGravity(Gravity.CENTER);
			navigation_tv.setLayoutParams(layoutParams);
			addView(navigation_tv);
		}

		getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				navigation_container_height = getHeight();
				navigation_container_top = getTop();
				if (navigation_container_height != 0) {
					getViewTreeObserver().removeOnGlobalLayoutListener(this);
//					Log.i("ZRH", "navigation_container_height: " + navigation_container_height);
//					Log.i("ZRH", "remove globalTree in verticalScrollNavigation");
				}
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (verticalScrollNavigationCallback != null) {
			verticalScrollNavigationCallback.scrollNavigation(event.getY() -
					navigation_container_top, navigation_container_height, event.getAction());
		} else {
			Log.i("ZRH", "verticalScrollNavigationCallback is null");
		}
		return true;
	}
}
