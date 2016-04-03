package com.example.macyaren.sportman;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by hennzr on 2016/3/5.
 */
public class ObservableScrollView extends ScrollView {

	private Callbacks mCallbacks;

	public ObservableScrollView(Context context) {
		super(context);
	}

	public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ObservableScrollView(Context context, AttributeSet attrs) {

		super(context, attrs);
	}

	public void setmCallbacks(Callbacks mCallbacks) {
		this.mCallbacks = mCallbacks;
	}

	public interface Callbacks {
		public void onScrollchanged(int t);

		public void onTouchUp();

		public void onTouchDown();
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (mCallbacks != null) {
//			Log.i("ZRH", "onScrollChanged in ObservableScrollView");
			mCallbacks.onScrollchanged(t);
		}
	}

	@Override
	protected int computeVerticalScrollRange() {
		return super.computeVerticalScrollRange();
	}
}
