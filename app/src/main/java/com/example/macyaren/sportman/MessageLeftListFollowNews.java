package com.example.macyaren.sportman;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hennzr on 2016/3/27.
 */
public class MessageLeftListFollowNews extends Activity implements View.OnClickListener {

	public MessageLeftListFollowNewsRecyclerViewAdapter
			messageLeftListFollowNewsRecyclerViewAdapter;

	public List<MessageLeftListFollowNewsRecyclerViewInfo> leftListNewsRecyclerViewInfos;

	public RecyclerView recyclerView;
	public ImageView follow_return;
	public TextView toolbar_title;

	public LinearLayoutManager linearLayoutManager;

	public final static int INTENT_FROM_FOLLOW = 0;
	public final static int INTENT_FROM_NEWS = 1;

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case INTENT_FROM_FOLLOW:
					toolbar_title.setText("Follow");
					recyclerView.setLayoutManager(linearLayoutManager);
					recyclerView.setAdapter(messageLeftListFollowNewsRecyclerViewAdapter);
					break;
				case INTENT_FROM_NEWS:
					toolbar_title.setText("News");
					recyclerView.setLayoutManager(linearLayoutManager);
					recyclerView.setAdapter(messageLeftListFollowNewsRecyclerViewAdapter);
					break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_left_list_follow_news);
		String intent_key = getIntent().getStringExtra("data");
		recyclerView = (RecyclerView) findViewById(R.id.message_left_list_follow_recyclerView);
		toolbar_title = (TextView) findViewById(R.id.message_left_list_follow_news_toolbar_title);
		linearLayoutManager = new LinearLayoutManager(this);
		switch (intent_key) {
			case Msg_fragment_mlist_left.INTENT_TO_FOLLOW_KEY:
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
								ArrayList<MessageLeftListFollowNewsRecyclerViewInfo>();
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
						handler.sendMessage(msg);
					}
				}).start();
				break;
			case Msg_fragment_mlist_left.INTENT_TO_NEWS_KEY:
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
								ArrayList<MessageLeftListFollowNewsRecyclerViewInfo>();
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
						handler.sendMessage(msg);
					}
				}).start();
				break;
		}


		follow_return = (ImageView) findViewById(R.id.message_left_list_follow_return);
		follow_return.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.message_left_list_follow_return:
				finish();
				break;
		}
	}
}
