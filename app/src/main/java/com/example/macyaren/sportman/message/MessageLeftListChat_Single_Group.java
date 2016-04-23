package com.example.macyaren.sportman.message;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.macyaren.sportman.R;
import com.example.macyaren.sportman.customwidget.CustomToolbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hennzr on 2016/4/1 14:50
 * Project name is Sportman
 */
public class MessageLeftListChat_Single_Group extends AppCompatActivity implements CustomToolbar
		.customToolbarCallback {

	protected CustomToolbar chat_toolbar;
	protected RecyclerView chat_recyclerView;
	protected MessageLeftListChat_Single_GroupListRecyclerViewAdapter
			messageLeftListChat_single_groupListRecyclerViewAdapter;

	protected LinearLayout root;
	protected ImageView addother;
	protected EditText responseText;

	public final static int INTENT_FROM_CHAT = 2;

	MLLCSG_Handler mllcsg_handler = new MLLCSG_Handler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_left_list_chat_single_group);
		chat_toolbar = (CustomToolbar) findViewById(R.id.message_left_list_chat_single_group_toolbar);
		String uname = getIntent().getStringExtra("data").equals("") ? "" : getIntent()
				.getStringExtra("data");
		chat_toolbar.setCenter_title_attrs(uname);
		chat_toolbar.setRight_ti_image(R.drawable.friend_detail_normal);
		chat_toolbar.setCallback(this);
		chat_recyclerView = (RecyclerView) findViewById(R.id.message_left_list_chat_single_group_recyclerview);

		responseText = (EditText) findViewById(R.id.message_left_list_chat_single_group_edittext);
		root = (LinearLayout) findViewById(R.id.message_left_list_chat_single_group_root);
		addother = (ImageView) findViewById(R.id.message_left_list_chat_single_group_addother);
		addother.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				responseText.clearFocus();
				hideSoftInput(v.getWindowToken());
			}
		});

		new Thread(new Runnable() {
			@Override
			public void run() {
				List<MessageLeftListChat_Single_GroupListRecyclerViewInfo>
						listChat_single_groupListRecyclerViewInfos = new ArrayList<>();
				MessageLeftListChat_Single_GroupListRecyclerViewData
						messageLeftListChat_single_groupListRecyclerViewData = new
						MessageLeftListChat_Single_GroupListRecyclerViewData();
				MessageLeftListChat_Single_GroupListRecyclerViewInfo[]
						messageLeftListChat_single_groupListRecyclerViewInfos = new
						MessageLeftListChat_Single_GroupListRecyclerViewInfo
						[messageLeftListChat_single_groupListRecyclerViewData.DATA_LENGHT];
				for (int i = 0; i < messageLeftListChat_single_groupListRecyclerViewData.DATA_LENGHT; i++) {
					messageLeftListChat_single_groupListRecyclerViewInfos[i] = new
							MessageLeftListChat_Single_GroupListRecyclerViewInfo();
					messageLeftListChat_single_groupListRecyclerViewInfos[i].type =
							messageLeftListChat_single_groupListRecyclerViewData.TYPE[i];
					messageLeftListChat_single_groupListRecyclerViewInfos[i].username =
							messageLeftListChat_single_groupListRecyclerViewData.USERNAME[i];
					messageLeftListChat_single_groupListRecyclerViewInfos[i].profile =
							messageLeftListChat_single_groupListRecyclerViewData.PROFILE[i];
					messageLeftListChat_single_groupListRecyclerViewInfos[i].message =
							messageLeftListChat_single_groupListRecyclerViewData.MESSAGE[i];

					listChat_single_groupListRecyclerViewInfos.add
							(messageLeftListChat_single_groupListRecyclerViewInfos[i]);
				}
				messageLeftListChat_single_groupListRecyclerViewAdapter = new
						MessageLeftListChat_Single_GroupListRecyclerViewAdapter
						(MessageLeftListChat_Single_Group.this);
				messageLeftListChat_single_groupListRecyclerViewAdapter.setList
						(listChat_single_groupListRecyclerViewInfos);

				Message message = new Message();
				message.what = INTENT_FROM_CHAT;
				mllcsg_handler.sendMessage(message);
			}
		}).start();
	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {
		Snackbar.make(root, "custom action", Snackbar.LENGTH_SHORT).show();
	}

	static class MLLCSG_Handler extends Handler {

		WeakReference<MessageLeftListChat_Single_Group>
				messageLeftListChat_single_groupWeakReference;
		MessageLeftListChat_Single_Group messageLeftListChat_single_group;

		public MLLCSG_Handler(MessageLeftListChat_Single_Group messageLeftListChat_single_group) {
			this.messageLeftListChat_single_groupWeakReference = new
					WeakReference<>(messageLeftListChat_single_group);
			this.messageLeftListChat_single_group = messageLeftListChat_single_groupWeakReference
					.get();
		}

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == INTENT_FROM_CHAT) {
				messageLeftListChat_single_group.chat_recyclerView.setAdapter
						(messageLeftListChat_single_group
								.messageLeftListChat_single_groupListRecyclerViewAdapter);
				messageLeftListChat_single_group.chat_recyclerView.setLayoutManager(new
						LinearLayoutManager(messageLeftListChat_single_group));
			}
		}
	}

	private void hideSoftInput(IBinder token) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		boolean isOpen = imm.isActive();
		if (isOpen) {
			// imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//没有显示则显示
			imm.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

}
