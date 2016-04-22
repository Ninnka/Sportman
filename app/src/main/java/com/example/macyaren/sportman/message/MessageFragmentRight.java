package com.example.macyaren.sportman.message;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import com.example.macyaren.sportman.R;
import com.example.macyaren.sportman.customwidget.VerticalScrollNavigation;
import com.example.macyaren.sportman.helper.PingYinTool;
import com.example.macyaren.sportman.helper.Utility;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hennzr on 2016/3/9 16:42
 * Package in com.example.macyaren.sportman
 * Project name is Sportman
 */
public class MessageFragmentRight extends Fragment implements View.OnClickListener,
		VerticalScrollNavigation.VerticalScrollNavigationCallback {

	ScrollView scrollView;
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
//	float navigation_parent_height;
	float navigation_tv_height = 0;
	int list_group_height;
	int list_child_height;
	//	TextView navigation_tv;
	public String[] navigation_alpha = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J",
			"K",
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
	VerticalScrollNavigation navigation_parent;
//	int curPY = 0;
	LinearLayout right_top_newfriend;
	LinearLayout right_top_groupchat;
	LinearLayout right_top_star;
	TextView counter;
	TextView navigation_indicator;
	ViewTreeObserver viewTreeObserver_linearLayout_container;
//	ViewTreeObserver viewTreeObserver_navigation_parent;

	MSGFLR_Handler handler = new MSGFLR_Handler(this);

	MessageFragment messageFragment;
	WeakReference<MessageFragment> messageFragmentWeakReference;

	protected MsgFragmentRightListCallback msgFragmentRightListCallback;

	public void setMessageFragment(MessageFragment messageFragment) {
		this.messageFragmentWeakReference = new WeakReference<MessageFragment>(messageFragment);
		this.messageFragment = messageFragmentWeakReference.get();
	}

	public void setMsgFragmentRightListCallback(MsgFragmentRightListCallback msgFragmentRightListCallback) {
		this.msgFragmentRightListCallback = msgFragmentRightListCallback;
	}

	@Override
	public void scrollNavigation(float positionY, int navigation_container_height, int eventAction) {
		if (navigation_tv_height == 0) {
			navigation_tv_height = navigation_container_height / navigation_alpha.length;
		}
		int navigation_tv_position = (int) Math.floor
				(positionY / navigation_tv_height);
		switch (eventAction) {
			case MotionEvent.ACTION_DOWN:
				navigation_parent.setBackgroundColor(getResources().getColor(R.color
						.navigation_press));
				if (navigation_tv_position >= 0 && navigation_tv_position <= 26) {
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
				break;
			case MotionEvent.ACTION_MOVE:
				if (navigation_tv_position >= 0 && navigation_tv_position <= 26) {
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
				break;
			case MotionEvent.ACTION_UP:
				navigation_parent.setBackgroundColor(getResources().getColor(R.color
						.navigatiom_release));
				navigation_indicator.setText("");
				navigation_indicator.setVisibility(View.INVISIBLE);
				break;
		}
	}

	public interface MsgFragmentRightListCallback {

		void rightFragmentItemClick();

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
//		RefWatcher refWatcher = ExampleApp.getRefWatcher(getActivity());
//		refWatcher.watch(this);
	}

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
							 @Nullable Bundle savedInstanceState) {

		map = new HashMap<String, List<String>>();
		pingYinTool = new PingYinTool();
		rightListData_eng = new String[MessageFragmentRightListData.UNAME.length];
		list_group_height = Utility.dip2px(getContext(), 18);
		list_child_height = Utility.dip2px(getContext(), 45);

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
		listPY = new ArrayList<>();
		listInfos = new ArrayList<>();
		listPYTemp = new ArrayList<>();

		new Thread(new Runnable() {
			@Override
			public void run() {
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
					String[] temp = listPYTemp.toArray(new String[listPYTemp.size()]);
					Arrays.sort(temp);

					/*
					* 由数组转化而成的list不再具有动态增加元素的效果
					* 巨坑，切记
					* */
					//			listPY = Arrays.asList(temp);
					for (int i = 0; i < temp.length; i++) {
						listPY.add(temp[i]);
					}

//					Log.i("ZRH", "listPY is preparing");
//					Log.i("ZRH", "-----------------------------------------------------");

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
//										Log.i("ZRH", "添加成功");
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

//				Log.i("ZRH", "----------------------------------------------------");

				Message msg = new Message();
				msg.what = 0;
				handler.sendMessage(msg);
			}
		}).start();

		/*
		* 解决在切换页面时ExpandableListView定位到获得焦点的Item上的问题
		* */
		scrollView = (ScrollView) resView.findViewById(R.id
				.message_fragment_mlist_right_scrollview);
		scrollView.setFocusable(true);
		scrollView.scrollTo(0, 0);

		/*
		* 设置linearLayout_container全局视图树监听
		* 获取container的高度
		* */
		linearLayout_container = (LinearLayout) resView.findViewById(R.id
				.message_fragment_mlist_right_top_container);
		viewTreeObserver_linearLayout_container = linearLayout_container
				.getViewTreeObserver();
		viewTreeObserver_linearLayout_container.addOnGlobalLayoutListener(new ViewTreeObserver
				.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				container_height = linearLayout_container.getBottom();
				linearLayout_container.getViewTreeObserver().removeOnGlobalLayoutListener(this);
			}
		});

		/*
		* 获取navigation的indicator
		* */
		navigation_indicator = (TextView) resView.findViewById(R.id
				.message_fragment_mlist_right_expandablelist_navigation_indicator);

		/*
		* 获取navigation的container
		* */
		navigation_parent = (VerticalScrollNavigation) resView.findViewById(R.id
				.message_fragment_mlist_right_expandablelist_navigation_parent);
		navigation_parent.setVerticalScrollNavigationCallback(this);


		//		navigation_parent.setVerticalScrollNavigationCallback();

		//		viewTreeObserver_navigation_parent = navigation_parent
		//				.getViewTreeObserver();
		//		viewTreeObserver_navigation_parent.addOnGlobalLayoutListener(new ViewTreeObserver
		//				.OnGlobalLayoutListener() {
		//			@Override
		//			public void onGlobalLayout() {
		//				navigation_parent_height = navigation_parent.getHeight();
		//				navigation_tv_height = navigation_parent_height / 27;
		//				navigation_parent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
		//				//				Log.i("ZRH", "navigation_parent_height: " + navigation_parent_height);
		//				//				Log.i("ZRH", "navigation_tv_height: " + navigation_tv_height);
		//			}
		//		});

		//		navigation_parent.setOnTouchListener(new View.OnTouchListener() {
		//			@Override
		//			public boolean onTouch(View v, MotionEvent event) {
		//				float currentPointY = event.getY();
		//				int navigation_tv_position = (int) Math.floor
		//						(currentPointY / navigation_tv_height);
		//				switch (event.getAction()) {
		//					case MotionEvent.ACTION_DOWN:
		//						navigation_parent.setBackgroundColor(getResources().getColor(R.color
		//								.navigation_press));
		//						if (navigation_tv_position >= 0 && navigation_tv_position <= 26) {
		//							String clickStr = navigation_alpha[navigation_tv_position];
		//							navigation_indicator.setText(clickStr);
		//							navigation_indicator.setVisibility(View.VISIBLE);
		//							for (int k = 0; k < listPY.size(); k++) {
		//								if (clickStr.equals(listPY.get(k))) {
		//									int childDis = 0;
		//									int groupDis = k * (list_group_height + 12);
		//									for (int j = 0; j < k; j++) {
		//										int curChildDis = messageFragmentRightListAdapter
		//												.getChildrenCount(j) * (list_child_height
		//												+ 12);
		//										childDis += curChildDis;
		//									}
		//									int realDis = childDis + groupDis + container_height;
		//									scrollView.smoothScrollTo(0, realDis);
		//									break;
		//								}
		//							}
		//						}
		//						return true;
		//					case MotionEvent.ACTION_MOVE:
		//						if (navigation_tv_position >= 0 && navigation_tv_position <= 26) {
		//							String clickStr = navigation_alpha[navigation_tv_position];
		//							navigation_indicator.setText(clickStr);
		//							for (int k = 0; k < listPY.size(); k++) {
		//								if (clickStr.equals(listPY.get(k))) {
		//									int childDis = 0;
		//									int groupDis = k * (list_group_height + 12);
		//									for (int j = 0; j < k; j++) {
		//										int curChildDis = messageFragmentRightListAdapter
		//												.getChildrenCount(j) * (list_child_height
		//												+ 12);
		//										childDis += curChildDis;
		//									}
		//									int realDis = childDis + groupDis + container_height;
		//									scrollView.smoothScrollTo(0, realDis);
		//									break;
		//								}
		//							}
		//						}
		//						return true;
		//					case MotionEvent.ACTION_UP:
		//						navigation_parent.setBackgroundColor(getResources().getColor(R.color
		//								.navigatiom_release));
		//						navigation_indicator.setText("");
		//						navigation_indicator.setVisibility(View.INVISIBLE);
		//						return false;
		//				}
		//				return false;
		//			}
		//		});

		//		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
		//				.LayoutParams.MATCH_PARENT, 0, 1);
		//		for (int i = 0; i < navigation_alpha.length; i++) {
		//			navigation_tv = new TextView(getContext());
		//			navigation_tv.setText(navigation_alpha[i]);
		//			//noinspection deprecation
		//			navigation_tv.setTextColor(getResources().getColor(R.color.md_grey_700));
		//			navigation_tv.setGravity(Gravity.CENTER_HORIZONTAL);
		//			navigation_tv.setLayoutParams(layoutParams);
		//			navigation_parent.addView(navigation_tv);
		//		}

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
				Toast.makeText(getContext().getApplicationContext(), "add new friend was clicked",
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.message_fragment_mlist_right_top_groupChat:
				Toast.makeText(getContext().getApplicationContext(), "initiate a group_char was clicked",
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.message_fragment_mlist_right_top_myStar:
				Toast.makeText(getContext().getApplicationContext(), "my star was clicked", Toast.LENGTH_SHORT).show();
				break;
		}
	}

	static class MSGFLR_Handler extends Handler {

		MessageFragmentRight messageFragmentRight;
		WeakReference<MessageFragmentRight> messageFragmentListRightWeakReference;

		public MSGFLR_Handler(MessageFragmentRight messageFragmentRight) {
			this.messageFragmentListRightWeakReference = new
					WeakReference<>(messageFragmentRight);
			this.messageFragmentRight = messageFragmentListRightWeakReference.get();
		}

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				/*
				* 设置MessageFragmentRightListAdapter
				* */
				messageFragmentRight.messageFragmentRightListAdapter = new
						MessageFragmentRightListAdapter(messageFragmentRight.messageFragment);
				messageFragmentRight.messageFragmentRightListAdapter.setListPY
						(messageFragmentRight.listPY);
				messageFragmentRight.messageFragmentRightListAdapter.setListInfos
						(messageFragmentRight.listInfos);

				/*
				* 设置ExpandableListView的Adapter
				* 展开所有groupItem
				* */
				messageFragmentRight.expandableListView.setAdapter(messageFragmentRight
						.messageFragmentRightListAdapter);
				for (int i = 0; i < messageFragmentRight.messageFragmentRightListAdapter
						.getGroupCount(); i++) {
					messageFragmentRight.expandableListView.expandGroup(i);
				}

				/*
				* ExpandableListView的部分布局显示更改
				* 重写ExpandableListView的group、child点击事件
				* */
				messageFragmentRight.expandableListView.setGroupIndicator(null);
				messageFragmentRight.expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
					@Override
					public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
						return true;
					}
				});
				messageFragmentRight.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
					@Override
					public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
						Toast.makeText(messageFragmentRight.getContext().getApplicationContext(), "ChildItem" +
										childPosition +
										" was " +
										"clicked",
								Toast.LENGTH_SHORT).show();
						return true;
					}
				});
				/*
				* 禁止ExpandableListView获得焦点
				* */
				messageFragmentRight.expandableListView.setFocusable(false);
				Utility.setListViewHeightBasedOnChildren(messageFragmentRight.expandableListView);

				messageFragmentRight.navigation_parent.setNavigationAttribute
						(messageFragmentRight.navigation_alpha, messageFragmentRight
								.messageFragment.getActivity());
			}
		}
	}
}
