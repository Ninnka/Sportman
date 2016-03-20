package com.example.macyaren.sportman;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by hennzr on 2016/3/2.
 */
public class TopFragmentPagerAdapter extends FragmentPagerAdapter {

	public List<Fragment> fragmentList;


	public TopFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public void setFragmentList(List<Fragment> list) {
		this.fragmentList = list;
	}

	@Override
	public Fragment getItem(int position) {
		return fragmentList.get(position);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

}
