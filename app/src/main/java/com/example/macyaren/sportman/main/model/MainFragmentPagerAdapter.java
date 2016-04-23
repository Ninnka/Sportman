package com.example.macyaren.sportman.main.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by hennzr on 2016/2/29.
 */
public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {

	public List<Fragment> fragmentList;

	public MainFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;
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
