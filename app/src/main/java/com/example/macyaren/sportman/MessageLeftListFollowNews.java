package com.example.macyaren.sportman;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by hennzr on 2016/3/27
 * Project name is Sportman
 */
public class MessageLeftListFollowNews extends Activity implements View.OnClickListener,
		CustomToolbar.customToolbarCallback{

	protected MessageLeftListFollowNewsRecyclerViewAdapter
			messageLeftListFollowNewsRecyclerViewAdapter;

	protected RecyclerView recyclerView;
	public CustomToolbar customToolbar;

	protected LinearLayoutManager linearLayoutManager;

	protected final static int INTENT_FROM_FOLLOW = 0;
	protected final static int INTENT_FROM_NEWS = 1;

	protected MLL_Handler mll_handler = new MLL_Handler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_left_list_follow_news);
		String intent_key = getIntent().getStringExtra("data") == null ? "" : getIntent()
				.getStringExtra("data");
		recyclerView = (RecyclerView) findViewById(R.id.message_left_list_follow_recyclerView);
		customToolbar = (CustomToolbar) findViewById(R.id.message_left_list_follow_news_toolbar);
		customToolbar.setCallback(this);
		linearLayoutManager = new LinearLayoutManager(this);
		if (!Objects.equals(intent_key, "")) {
			switch (intent_key) {
				case MessageFragment.INTENT_TO_FOLLOW_KEY:
					new Thread(new Runnable() {
						@Override
						public void run() {
							MessageLeftListFollowRecyclerViewData messageLeftListFollowRecyclerViewData = new
									MessageLeftListFollowRecyclerViewData();
							MessageLeftListFollowNewsRecyclerViewInfo[]
									messageLeftListFollowRecyclerViewInfo
									= new MessageLeftListFollowNewsRecyclerViewInfo[2];
							List<MessageLeftListFollowNewsRecyclerViewInfo>
									leftListFollowRecyclerViewInfos = new
									ArrayList<>();
							for (int i = 0; i < 2; i++) {
								messageLeftListFollowRecyclerViewInfo[i] = new
										MessageLeftListFollowNewsRecyclerViewInfo();
								messageLeftListFollowRecyclerViewInfo[i].date =
										messageLeftListFollowRecyclerViewData.DATES[i];
								messageLeftListFollowRecyclerViewInfo[i].title =
										messageLeftListFollowRecyclerViewData.TITLES[i];
								messageLeftListFollowRecyclerViewInfo[i].top_post =
										messageLeftListFollowRecyclerViewData.TOP_POSTS[i];
								messageLeftListFollowRecyclerViewInfo[i].sub_title_first =
										messageLeftListFollowRecyclerViewData.SUB_TITLE_FIRSTS[i];
								messageLeftListFollowRecyclerViewInfo[i].sub_title_second =
										messageLeftListFollowRecyclerViewData.SUB_TITLE_SECONDS[i];
								messageLeftListFollowRecyclerViewInfo[i].sub_post_first =
										messageLeftListFollowRecyclerViewData.SUB_POST_FIRSTS[i];
								messageLeftListFollowRecyclerViewInfo[i].sub_post_second =
										messageLeftListFollowRecyclerViewData.SUB_POST_SECONDS[i];

								leftListFollowRecyclerViewInfos.add(messageLeftListFollowRecyclerViewInfo[i]);
							}
							messageLeftListFollowNewsRecyclerViewAdapter = new
									MessageLeftListFollowNewsRecyclerViewAdapter
									(MessageLeftListFollowNews.this);
							messageLeftListFollowNewsRecyclerViewAdapter.setList
									(leftListFollowRecyclerViewInfos);

							Message msg = new Message();
							msg.what = INTENT_FROM_FOLLOW;
							mll_handler.sendMessage(msg);
						}
					}).start();
					break;
				case MessageFragment.INTENT_TO_NEWS_KEY:
					new Thread(new Runnable() {
						@Override
						public void run() {
							MessageLeftListNewsRecyclerViewData messageLeftListNewsRecyclerViewData =
									new MessageLeftListNewsRecyclerViewData();
							MessageLeftListFollowNewsRecyclerViewInfo[]
									messageLeftListNewsRecyclerViewInfo = new
									MessageLeftListFollowNewsRecyclerViewInfo[2];
							List<MessageLeftListFollowNewsRecyclerViewInfo>
									leftListNewsRecyclerViewInfos = new
									ArrayList<>();
							for (int i = 0; i < 2; i++) {
								messageLeftListNewsRecyclerViewInfo[i] = new
										MessageLeftListFollowNewsRecyclerViewInfo();
								messageLeftListNewsRecyclerViewInfo[i].date =
										messageLeftListNewsRecyclerViewData.DATES[i];
								messageLeftListNewsRecyclerViewInfo[i].title =
										messageLeftListNewsRecyclerViewData.TITLES[i];
								messageLeftListNewsRecyclerViewInfo[i].top_post =
										messageLeftListNewsRecyclerViewData.TOP_POSTS[i];
								messageLeftListNewsRecyclerViewInfo[i].sub_title_first =
										messageLeftListNewsRecyclerViewData.SUB_TITLE_FIRSTS[i];
								messageLeftListNewsRecyclerViewInfo[i].sub_title_second =
										messageLeftListNewsRecyclerViewData.SUB_TITLE_SECONDS[i];
								messageLeftListNewsRecyclerViewInfo[i].sub_post_first =
										messageLeftListNewsRecyclerViewData.SUB_POST_FIRSTS[i];
								messageLeftListNewsRecyclerViewInfo[i].sub_post_second =
										messageLeftListNewsRecyclerViewData.SUB_POST_SECONDS[i];

								leftListNewsRecyclerViewInfos.add
										(messageLeftListNewsRecyclerViewInfo[i]);
							}

							messageLeftListFollowNewsRecyclerViewAdapter = new
									MessageLeftListFollowNewsRecyclerViewAdapter
									(MessageLeftListFollowNews.this);
							messageLeftListFollowNewsRecyclerViewAdapter.setList
									(leftListNewsRecyclerViewInfos);

							Message msg = new Message();
							msg.what = INTENT_FROM_NEWS;
							mll_handler.sendMessage(msg);
						}
					}).start();
					break;
			}
		}
	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {

	}

	static class MLL_Handler extends Handler {

		WeakReference<MessageLeftListFollowNews> messageLeftListFollowNewsWeakReference;
		MessageLeftListFollowNews messageLeftListFollowNews;

		public MLL_Handler(MessageLeftListFollowNews messageLeftListFollowNews) {
			this.messageLeftListFollowNewsWeakReference = new
					WeakReference<>(messageLeftListFollowNews);
		}

		@Override
		public void handleMessage(Message msg) {
			messageLeftListFollowNews = messageLeftListFollowNewsWeakReference.get();
			switch (msg.what) {
				case INTENT_FROM_FOLLOW:
					messageLeftListFollowNews.customToolbar.setCenter_title_attrs("Follows");
					messageLeftListFollowNews.recyclerView.setLayoutManager
							(messageLeftListFollowNews.linearLayoutManager);
					messageLeftListFollowNews.recyclerView.setAdapter(messageLeftListFollowNews
							.messageLeftListFollowNewsRecyclerViewAdapter);
					break;
				case INTENT_FROM_NEWS:
					messageLeftListFollowNews.customToolbar.setCenter_title_attrs("News");
					messageLeftListFollowNews.recyclerView.setLayoutManager
							(messageLeftListFollowNews.linearLayoutManager);
					messageLeftListFollowNews.recyclerView.setAdapter(messageLeftListFollowNews
							.messageLeftListFollowNewsRecyclerViewAdapter);
					break;
			}
		}
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mll_handler = null;
	}
}
