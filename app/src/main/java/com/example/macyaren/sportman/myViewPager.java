package com.example.macyaren.sportman;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by hennzr on 2016/3/2.
 */
public class myViewPager extends android.support.v4.view.ViewPager {
	public myViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		return true;
//	}


//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		boolean ret = super.dispatchTouchEvent(ev);
//		if(ret){
//			requestDisallowInterceptTouchEvent(true);
//		}
//		return ret;
//	}

//	@Override
//	public boolean onTouchEvent(MotionEvent ev) {
//		switch (ev.getAction()){
//			case MotionEvent.ACTION_DOWN:
//				requestDisallowInterceptTouchEvent(true);
//				break;
//			case MotionEvent.ACTION_MOVE:
//				requestDisallowInterceptTouchEvent(true);
//				break;
//		}
//		return false;
//	}


}
