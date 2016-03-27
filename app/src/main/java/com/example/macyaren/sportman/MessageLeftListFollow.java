package com.example.macyaren.sportman;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hennzr on 2016/3/27.
 */
public class MessageLeftListFollow extends Activity implements View.OnClickListener{

	public MessageLeftListFollowRecyclerViewAdapter messageLeftListFollowRecyclerViewAdapter;
	public MessageLeftListFollowRecyclerViewData messageLeftListFollowRecyclerViewData;
	public MessageLeftListFollowRecyclerViewInfo[] messageLeftListFollowRecyclerViewInfo = new
			MessageLeftListFollowRecyclerViewInfo[2];
	public List<MessageLeftListFollowRecyclerViewInfo> leftListFollowRecyclerViewInfos;
	public RecyclerView recyclerView;
	public ImageView follow_return;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_left_list_follow);
		recyclerView = (RecyclerView) findViewById(R.id.message_left_list_follow_recyclerView);
		messageLeftListFollowRecyclerViewData = new MessageLeftListFollowRecyclerViewData();
		leftListFollowRecyclerViewInfos = new ArrayList<MessageLeftListFollowRecyclerViewInfo>();
		for (int i = 0; i < 2; i++) {
			messageLeftListFollowRecyclerViewInfo[i] = new MessageLeftListFollowRecyclerViewInfo();
			messageLeftListFollowRecyclerViewInfo[i].date = messageLeftListFollowRecyclerViewData
					.DATES[i];
			leftListFollowRecyclerViewInfos.add(messageLeftListFollowRecyclerViewInfo[i]);
		}
		messageLeftListFollowRecyclerViewAdapter = new MessageLeftListFollowRecyclerViewAdapter
				(this);
		messageLeftListFollowRecyclerViewAdapter.setList(leftListFollowRecyclerViewInfos);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(messageLeftListFollowRecyclerViewAdapter);
		follow_return = (ImageView) findViewById(R.id.message_left_list_follow_return);
		follow_return.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.message_left_list_follow_return:
				finish();
				break;
		}
	}
}
