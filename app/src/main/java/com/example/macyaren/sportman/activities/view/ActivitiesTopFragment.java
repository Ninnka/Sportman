package com.example.macyaren.sportman.activities.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.macyaren.sportman.R;

/**
 * Created by hennzr on 2016/3/2.
 */
public class ActivitiesTopFragment extends Fragment {

	public int RESID;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		ImageView imageView = (ImageView) inflater.inflate(R.layout.banner_fragment_1, container, false);
		imageView.setImageResource(RESID);
		imageView.setClickable(true);
		return imageView;
	}

	public void setRESID(int RESID) {
		this.RESID = RESID;
	}
}
