package com.example.macyaren.sportman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hennzr on 2016/3/9.
 */
public class Msg_fragment_mlist_right extends Fragment implements View.OnClickListener {

	ExpandableListView expandableListView;
	MessageFragmentRightListAdapter messageFragmentRightListAdapter;
	List<String> listPYTemp;
	List<String> listPY;
	List<List<MessageFragmentRightListInfo>> listInfos;
	//	MessageFragmentRightListData messageFragmentRightListData;
	PingYinTool pingYinTool;
	String[] rightListData_eng;
	Map<String, List<String>> map;
	LinearLayout linearLayout_container;
	int container_height;
	float navigation_parent_height;
	float navigation_tv_height;
	int list_group_height;
	int list_child_height;
	TextView navigation_tv;
	String[] navigation_alpha;
	LinearLayout navigation_parent;
	int curPY = 0;
	LinearLayout right_top_newfriend;
	LinearLayout right_top_groupchat;
	LinearLayout right_top_star;
	TextView counter;
	TextView navigation_indicator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		map = new HashMap<String, List<String>>();
		//		messageFragmentRightListData = new MessageFragmentRightListData();
		pingYinTool = new PingYinTool();
		rightListData_eng = new String[MessageFragmentRightListData.UNAME.length];
		list_group_height = Utility.dip2px(getContext(), 18);
		list_child_height = Utility.dip2px(getContext(), 45);
		navigation_alpha = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O",
				"P", "Q", "R", "S",
				"T", "U", "V", "W", "X", "Y", "Z", "#"};
	}

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View resView = inflater.inflate(R.layout.message_fragment_mlist_right, container, false);
		right_top_newfriend = (LinearLayout) resView.findViewById(R.id
				.message_fragment_mlist_right_top_newFriend);
		right_top_groupchat = (LinearLayout) resView.findViewById(R.id
				.message_fragment_mlist_right_top_groupChat);
		right_top_star = (LinearLayout) resView.findViewById(R.id
				.message_fragment_mlist_right_top_myStar);
		right_top_newfriend.setOnClickListener(this);
		right_top_groupchat.setOnClickListener(this);
		right_top_star.setOnClickListener(this);

		expandableListView = (ExpandableListView) resView.findViewById(R.id
				.message_fragment_mlist_right_expandablelist);
		listPY = new ArrayList<String>();
		listInfos = new ArrayList<List<MessageFragmentRightListInfo>>();
		listPYTemp = new ArrayList<String>();

		try {
			/*
		* 准备listPY
		* */
			for (int i = 0; i < MessageFragmentRightListData.UNAME.length; i++) {
				//				Log.i("ZRH", "第一种循环开始");
				try {
					rightListData_eng[i] = pingYinTool.toPinYin(MessageFragmentRightListData
							.UNAME[i]);
					char c = rightListData_eng[i].charAt(0);
					String cc = String.valueOf(c);
					if (((int) c >= 65 & (int) c <= 90) || ((int) c >= 97 & (int) c <= 122)) {
						if (map.get(cc) == null) {
							List<String> newlist = new ArrayList<String>();
							newlist.add(rightListData_eng[i]);
							map.put(cc.toUpperCase(), newlist);
							listPYTemp.add(cc.toUpperCase());
						} else {
							map.get(cc.toUpperCase()).add(rightListData_eng[i]);
						}
					} else {
						if (map.get("OTHERS") == null) {
							List<String> newList = new ArrayList<String>();
							newList.add(rightListData_eng[i]);
							map.put("OTHERS", newList);
						} else {
							map.get("OTHERS").add(rightListData_eng[i]);
						}
					}
				} catch (BadHanyuPinyinOutputFormatCombination bhypy) {
					Log.i("ZRH FOR HANYUPINYIN", bhypy.getMessage());
				}
			}
			String[] temp = (String[]) listPYTemp.toArray(new String[listPYTemp.size()]);
			Arrays.sort(temp);

			/*
			* 由数组转化而成的list不再具有动态增加元素的效果
			* 巨坑，切记
			* */
			//			listPY = Arrays.asList(temp);
			for (int i = 0; i < temp.length; i++) {
				listPY.add(temp[i]);
			}

			Log.i("ZRH", "listPY is preparing");
			Log.i("ZRH", "-----------------------------------------------------");

		/*
		* 准备listInfos
		* */
			for (int j = 0; j < listPY.size(); j++) {
				//				Log.i("ZRH", "第二种循环开始");
				if (map.get(listPY.get(j)) != null) {
					List<MessageFragmentRightListInfo> listInfo = new ArrayList<MessageFragmentRightListInfo>();

					List<String> tranList = map.get(listPY.get(j));
					String[] tranTemp = tranList.toArray(new String[tranList.size()]);
					Arrays.sort(tranTemp);
					for (int i = 0; i < tranTemp.length; i++) {
						String tempStr = tranTemp[i];
						for (int k = 0; k < rightListData_eng.length; k++) {
							if (tempStr == rightListData_eng[k]) {
								MessageFragmentRightListInfo messageFragmentRightListInfo = new
										MessageFragmentRightListInfo();
								messageFragmentRightListInfo.uname = MessageFragmentRightListData
										.UNAME[k];
								messageFragmentRightListInfo.photo = MessageFragmentRightListData
										.PHOTO[k];
								listInfo.add(messageFragmentRightListInfo);
								Log.i("ZRH", "添加成功");
								break;
							}
						}
					}
					listInfos.add(listInfo);
					//					Log.i("ZRH", "当前listInfos的长度： " + listInfos.size());
				}
			}
		} catch (Exception e) {
			Log.i("ZRH", e.getMessage());
		}
		try {
			if (map.get("OTHERS") == null) {
				Log.i("ZRH", "无#项");
			} else {
				listPY.add("#");
				List<MessageFragmentRightListInfo> otherNameList = new
						ArrayList<MessageFragmentRightListInfo>();
				List<String> tempList = map.get("OTHERS");
				for (int i = 0; i < tempList.size(); i++) {
					for (int j = 0; j < rightListData_eng.length; j++) {
						if (tempList.get(i) == rightListData_eng[j]) {
							MessageFragmentRightListInfo messageFragmentRightListInfo = new
									MessageFragmentRightListInfo();
							messageFragmentRightListInfo.photo = MessageFragmentRightListData
									.PHOTO[j];
							messageFragmentRightListInfo.uname = MessageFragmentRightListData
									.UNAME[j];
							otherNameList.add(messageFragmentRightListInfo);
							break;
						}
					}
				}
				listInfos.add(otherNameList);
			}
		} catch (Exception ee) {
			Log.i("ZRH", ee.getMessage());
		}

		Log.i("ZRH", "----------------------------------------------------");

		/*
		* 设置MessageFragmentRightListAdapter
		* */
		messageFragmentRightListAdapter = new MessageFragmentRightListAdapter(getContext());
		messageFragmentRightListAdapter.setListPY(listPY);
		messageFragmentRightListAdapter.setListInfos(listInfos);

		/*
		*设置ExpandableListView的Adapter
		* */
		expandableListView.setAdapter(messageFragmentRightListAdapter);
		for (int i = 0; i < messageFragmentRightListAdapter.getGroupCount(); i++) {
			expandableListView.expandGroup(i);
		}
		/*
		* ExpandableListView的部分布局显示更改
		* 重写ExpandableListView的group、child点击事件
		* */
		expandableListView.setGroupIndicator(null);
		expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				return true;
			}
		});
		expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				Toast.makeText(getContext(), "ChildItem" + childPosition + " was clicked",
						Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		/*
		* 禁止ExpandableListView获得焦点
		* 解决在切换页面时ExpandableListView定位到获得焦点的Item上的问题
		* */
		expandableListView.setFocusable(false);
		Utility.setListViewHeightBasedOnChildren(expandableListView);
		final ScrollView scrollView = (ScrollView) resView.findViewById(R.id
				.message_fragment_mlist_right_scrollview);
		scrollView.setFocusable(true);
		scrollView.scrollTo(0, 0);

		/*
		* 设置linearLayout_container全局视图树监听
		* 获取container的高度
		* */
		linearLayout_container = (LinearLayout) resView.findViewById(R.id
				.message_fragment_mlist_right_top_container);
		ViewTreeObserver viewTreeObserver_linearLayout_container = linearLayout_container
				.getViewTreeObserver();
		viewTreeObserver_linearLayout_container.addOnGlobalLayoutListener(new ViewTreeObserver
				.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				container_height = linearLayout_container.getBottom();
			}
		});

		/*
		* 获取navigation的indicator
		* */
		navigation_indicator = (TextView) resView.findViewById(R.id
				.message_fragment_mlist_right_expandablelist_navigation_indicator);

		/*
		* 动态创建右边的导航栏
		* 根据当前已有的分组动态添加点击事件
		* 设置navigation_parent全局视图树监听
		* 获取navigation_parent的高度
		* */
		navigation_parent = (LinearLayout) resView.findViewById(R.id
				.message_fragment_mlist_right_expandablelist_navigation_parent);
		ViewTreeObserver viewTreeObserver_navigation_parent = navigation_parent
				.getViewTreeObserver();
		viewTreeObserver_navigation_parent.addOnGlobalLayoutListener(new ViewTreeObserver
				.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				navigation_parent_height = navigation_parent.getHeight();
				navigation_tv_height = navigation_parent_height / 27;
				Log.i("ZRH", "navigation_parent_height: " + navigation_parent_height);
				Log.i("ZRH", "navigation_tv_height: " + navigation_tv_height);
			}
		});
		navigation_parent.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				float currentPointY = event.getY();
				int navigation_tv_position = (int) Math.floor
						(currentPointY / navigation_tv_height);
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						navigation_parent.setBackgroundColor(getResources().getColor(R.color
								.navigation_press));
						if(navigation_tv_position>=0 && navigation_tv_position <= 26){
							String clickStr = navigation_alpha[navigation_tv_position];
							navigation_indicator.setText(clickStr);
							navigation_indicator.setVisibility(View.VISIBLE);
							for (int k = 0; k < listPY.size(); k++) {
								if (clickStr.equals(listPY.get(k))) {
									int childDis = 0;
									int groupDis = k * (list_group_height + 12);
									for (int j = 0; j < k; j++) {
										int curChildDis = messageFragmentRightListAdapter
												.getChildrenCount(j) * (list_child_height
												+ 12);
										childDis += curChildDis;
									}
									int realDis = childDis + groupDis + container_height;
									scrollView.smoothScrollTo(0, realDis);
									break;
								}
							}
						}
						return true;
					case MotionEvent.ACTION_MOVE:
						if(navigation_tv_position>=0 && navigation_tv_position <= 26){
							String clickStr = navigation_alpha[navigation_tv_position];
							navigation_indicator.setText(clickStr);
							for (int k = 0; k < listPY.size(); k++) {
								if (clickStr.equals(listPY.get(k))) {
									int childDis = 0;
									int groupDis = k * (list_group_height + 12);
									for (int j = 0; j < k; j++) {
										int curChildDis = messageFragmentRightListAdapter
												.getChildrenCount(j) * (list_child_height
												+ 12);
										childDis += curChildDis;
									}
									int realDis = childDis + groupDis + container_height;
									scrollView.smoothScrollTo(0, realDis);
									break;
								}
							}
						}
						return true;
					case MotionEvent.ACTION_UP:
						navigation_parent.setBackgroundColor(getResources().getColor(R.color
								.navigatiom_release));
						navigation_indicator.setText("");
						navigation_indicator.setVisibility(View.INVISIBLE);
						return false;
				}
				return false;
			}
		});
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
				.LayoutParams.MATCH_PARENT, 0, 1);
		for (int i = 0; i < navigation_alpha.length; i++) {
			navigation_tv = new TextView(getContext());
			navigation_tv.setText(navigation_alpha[i]);
			//noinspection deprecation
			navigation_tv.setTextColor(getResources().getColor(R.color.md_grey_700));
			navigation_tv.setGravity(Gravity.CENTER_HORIZONTAL);
			navigation_tv.setLayoutParams(layoutParams);
//			for (curPY = 0; curPY < listPY.size(); curPY++) {
//				/*
//				* 虽然不知道为什么，这个if里面的“==”必须用equals()来代替
//				* 用“==”进不去判断，A跟A肯定是相同的
//				* 但就是进不去
//				* 其实IDE给了warning：最好用”equals“来代替“==”
//				* */
// 				if (navigation_alpha[i].equals(listPY.get(curPY))) {
//					/*
//					* 添加onTouchListener
//					* */
//					navigation_tv.setPressed(true);
//					navigation_tv.setOnTouchListener(new View.OnTouchListener() {
//						@Override
//						public boolean onTouch(View v, MotionEvent event) {
//							switch (event.getAction()) {
//								case MotionEvent.ACTION_DOWN:
//									TextView clickTextView = (TextView) v;
//									String clickStr = (String) clickTextView.getText();
//									for (int k = 0; k < listPY.size(); k++) {
//										if (clickStr.equals(listPY.get(k))) {
//											int childDis = 0;
//											int groupDis = k * (list_group_height + 12);
//											for (int j = 0; j < k; j++) {
//												int curChildDis = messageFragmentRightListAdapter
//														.getChildrenCount(j) * (list_child_height
//														+ 12);
//												childDis += curChildDis;
//											}
//											int realDis = childDis + groupDis + container_height;
//											scrollView.smoothScrollTo(0, realDis);
//											break;
//										}
//									}
//									break;
//							}
//							return false;
//						}
//					});
//					break;
//				}
//			}
			navigation_parent.addView(navigation_tv);
		}

		/*
		* 测试用
		* */
		//		navigation_tv = new TextView(getContext());
		//		navigation_tv.setText("A");
		//		navigation_tv.setTextColor(getResources().getColor(R.color.md_grey_700));
		//		navigation_tv.setLayoutParams(layoutParams);
		//		navigation_tv.setOnClickListener(new View.OnClickListener() {
		//			@Override
		//			public void onClick(View v) {
		//				Toast.makeText(getContext(), "can be clicked", Toast.LENGTH_SHORT).show();
		//			}
		//		});
		//		navigation_parent.addView(navigation_tv);
		/*
		* 事前准备
		* 测试导航栏用
		* */
		//		TextView textView = (TextView) resView.findViewById(R.id
		//				.message_fragment_mlist_right_expandablelist_navigation_A);
		//		textView.setOnClickListener(new View.OnClickListener() {
		//			@Override
		//			public void onClick(View v) {
		//				//				expandableListView.setFocusable(true);
		//				//				expandableListView.setSelectedGroup(0);
		//				int distance = container_height;
		//				//				scrollView.setScrollY(distance);
		//				scrollView.smoothScrollTo(0, distance);
		//				Toast.makeText(getContext(), "ExpandableListView's Item-event: ",
		//						Toast.LENGTH_SHORT).show();
		//			}
		//		});

		/*
		* 部分核心数据测试日志
		* */
		//		Log.i("ZRH", "list_group_height: " + list_group_height);
		//		Log.i("ZRH", "list_child_height: " + list_child_height);
		//		Log.i("ZRH", "container_height_translate" + Utility.dip2px(getContext(), 145));

		/*
		* 设置总人数显示
		* */
		counter = (TextView) resView.findViewById(R.id.message_fragment_mlist_right_counter);
		counter.setText(MessageFragmentRightListData.UNAME.length + "位联系人");

		return resView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.message_fragment_mlist_right_top_newFriend:
				Toast.makeText(getContext(), "add new friend was clicked",
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.message_fragment_mlist_right_top_groupChat:
				Toast.makeText(getContext(), "initiate a group_char was clicked",
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.message_fragment_mlist_right_top_myStar:
				Toast.makeText(getContext(), "my star was clicked", Toast.LENGTH_SHORT).show();
				break;
		}
	}
}
