package com.bimco.chippet.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.bimco.chippet.ClipListFragment;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.Context;
import android.content.Intent;
import android.content.SyncContext;
import android.os.IBinder;
import android.util.Log;
import android.widget.ListView;

public class ClipboardWatchService extends Service {
	private Notification mNotification;
	public static ClipboardManager mClipboardManager;

	public static ArrayList<String> clips = new ArrayList<String>();;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		mNotification = new Notification(this);
		mClipboardManager = (ClipboardManager) this
				.getSystemService(Context.CLIPBOARD_SERVICE);

		mClipboardManager
				.addPrimaryClipChangedListener(new PrimaryClipChangedListener());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("Judy", "Service Reached");


		mNotification.show();
		if (intent == null) {
			return START_STICKY;
		}

		return START_STICKY;
	}

	private class PrimaryClipChangedListener implements
			OnPrimaryClipChangedListener {

		@Override
		public void onPrimaryClipChanged() {
			Log.e("Judy", "copyclip reached");
			ClipData.Item item = mClipboardManager.getPrimaryClip()
					.getItemAt(0);
			String s = item.getText().toString();
			ClipListFragment.allClips.add(s);
			for (int i = 0; i < ClipListFragment.allClips.size(); i++) {
				Log.e("Added clipes " + i, ClipListFragment.allClips.get(i));
			}
		}
	}

}
