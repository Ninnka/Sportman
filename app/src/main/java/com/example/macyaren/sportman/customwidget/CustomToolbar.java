package com.example.macyaren.sportman.customwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.macyaren.sportman.R;
import com.example.macyaren.sportman.helper.Utility;

/**
 * Created by hennzr on 2016/4/2 17:05
 * Package in com.example.macyaren.sportman
 * Project name is Sportman
 */
public class CustomToolbar extends Toolbar {

	public customToolbarCallback callback;

	public TextView left_back;
	public TextView center_title;
	public TextView right_ti;
	public RelativeLayout relativeLayout;

	public int center_title_color;
	public int center_title_size;
	public Drawable left_imgResources;

	public interface customToolbarCallback {

		void leftClick();

		void rightClick();

	}

	public CustomToolbar(Context context) {
		super(context);
	}

	public CustomToolbar(final Context context, AttributeSet attrs) {
		super(context, attrs);

		initComponent(context);
		obtainParameters(context, attrs);

		relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams
				.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

		RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(Utility.dip2px
				(context, 30), Utility.dip2px(context, 30));
		leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT | RelativeLayout.CENTER_VERTICAL,
				RelativeLayout.TRUE);
		left_back.setBackground(left_imgResources);
		relativeLayout.addView(left_back, leftParams);

		RelativeLayout.LayoutParams centerParams = new RelativeLayout.LayoutParams(RelativeLayout
				.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		centerParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		center_title.setTextSize(Utility.px2sp(context, center_title_size));
		center_title.setTextColor(center_title_color);
		center_title.setSingleLine(true);
		center_title.setEllipsize(TextUtils.TruncateAt.END);
		relativeLayout.addView(center_title, centerParams);

		RelativeLayout.LayoutParams rightParams = new RelativeLayout.LayoutParams(Utility.dip2px
				(context, 30), Utility.dip2px(context, 30));
		rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		rightParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		rightParams.setMarginEnd(Utility.dip2px(context, 8));
		right_ti.setGravity(Gravity.CENTER);
		relativeLayout.addView(right_ti, rightParams);

		left_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (callback != null) {
					callback.leftClick();
				} else {
					Toast.makeText(context.getApplicationContext(), "this activity have not implement " +
							"customToolbarCallback", Toast.LENGTH_SHORT).show();

				}
			}
		});

		right_ti.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (callback != null) {
					callback.rightClick();
				} else {
					Toast.makeText(context, "this activity have not implement " +
							"customToolbarCallback", Toast.LENGTH_SHORT).show();
				}
			}
		});

		addView(relativeLayout);

	}

	public CustomToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	protected void initComponent(Context context) {
		left_back = new TextView(context);
		center_title = new TextView(context);
		right_ti = new TextView(context);
		relativeLayout = new RelativeLayout(context);
	}

	protected void obtainParameters(Context context, AttributeSet attrs) {
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar);
		center_title_color = typedArray.getColor(R.styleable
				.CustomToolbar_toolbar_center_textColor, 0);
		center_title_size = (int) typedArray.getDimension(R.styleable
				.CustomToolbar_toolbar_center_textSize, Utility.sp2px(context, 20));
		left_imgResources = typedArray.getDrawable(R.styleable
				.CustomToolbar_toolbar_left_imgResources);
		typedArray.recycle();
	}

	public void setCallback(customToolbarCallback callback) {
		this.callback = callback;
	}

	/*
	* @params string
	* 				the text of centet title
	* 				if string = null,it would not be setted into center title
	* */
	public void setCenter_title_attrs(String string) {
		if (string != null) {
			center_title.setText(string);
		}
	}

	/*
	* @params string
	* 				the text of center title
	* 				if string = null,it would not be setted into center title
	* @params size
	* 				ths textsize of center title
	* 				if size = -1,it would not be setted into center title
	* */
	public void setCenter_title_attrs(String string, float size) {
		if (string != null) {
			center_title.setText(string);
		}
		if (size != -1) {
			center_title.setTextSize(size);
		}
	}

	/*
	* @params string
	* 				the text of center title
	* 				if string = null,it would not be setted into center title
	* @params size
	* 				ths textsize of center title
	* 				if size = -1,it would not be setted into center title
	* @params color
	* 				ths textcolor of center title
	* */
	public void setCenter_title_attrs(String string, float size, int color) {
		if (string != null) {
			center_title.setText(string);
		}
		if (size != -1) {
			center_title.setTextSize(size);
		}
		center_title.setTextColor(color);
	}

	/*
	* @params drawableID
	* 					use the value to set the background of left back
	* */
	public void setLeft_back_image(int drawableID) {
		left_back.setBackgroundResource(drawableID);
	}

	/*
	* @params string
	* 				the text of centet title
	* 				if string = null,it would not be setted into right textview
	* */
	public void setRight_ti_attrs(String string) {
		if (string != null) {
			right_ti.setText(string);
		}
	}

	/*
	* @params string
	* 				the text of center title
	* 				if string = null,it would not be setted intoright textview
	* @params size
	* 				ths textsize of center title
	* 				if size = -1,it would not be setted into right textview
	* */
	public void setRight_ti_attrs(String string, float size) {
		if (string != null) {
			right_ti.setText(string);
		}
		if (size != -1) {
			right_ti.setTextSize(size);
		}
	}

	/*
	* @params string
	* 				the text of center title
	* 				if string = null,it would not be setted intoright textview
	* @params size
	* 				ths textsize of center title
	* 				if size = -1,it would not be setted into right textview
	* @params color
	* 				ths textcolor of right textview
	* */
	public void setRight_ti_attrs(String string, float size, int color) {
		if (string != null) {
			right_ti.setText(string);
		}
		if (size != -1) {
			right_ti.setTextSize(size);
		}
		right_ti.setTextColor(color);
	}

	/*
	* @params drawableID
	* 					use the value to set the background of right textview
	* */
	public void setRight_ti_image(int drawableID) {
		right_ti.setBackgroundResource(drawableID);
	}

}
