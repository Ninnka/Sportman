package com.example.macyaren.sportman;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by hennzr on 2016/4/12 13:06
 * Package in com.example.macyaren.sportman
 * Project name is Sportman
 */
public class ExampleApp extends Application {

	public static RefWatcher getRefWatcher(Context context){
		ExampleApp exampleApp = (ExampleApp) context.getApplicationContext();
		return exampleApp.refWatcher;
	}

	private RefWatcher refWatcher;

	@Override
	public void onCreate() {
		super.onCreate();
		refWatcher = LeakCanary.install(this);
	}
}
