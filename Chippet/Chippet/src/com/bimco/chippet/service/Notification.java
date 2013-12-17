package com.bimco.chippet.service;

import com.bimco.chippet.MainActivity;
import com.bimco.chippet.R;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class Notification {
	
	private Context mContext;
	public Notification(Context context) {
		mContext = context;
	}
	
	public void startClipBoardWatchService(){
		 mContext.startService(new Intent(mContext, ClipboardWatchService.class));
	}
	
	void show(){
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext)
			    .setSmallIcon(R.drawable.ic_notification)
			    .setContentTitle("My notification")
			    .setContentText("Hello World!")
			    .setOngoing(true);
		
		Intent resultIntent = new Intent(mContext, MainActivity.class);
	
		// Because clicking the notification opens a new ("special") activity, there's
		// no need to create an artificial back stack.
		PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		
		// Sets an ID for the notification
		int mNotificationId = 001;
		// Gets an instance of the NotificationManager service
		NotificationManager mNotifyMgr =(NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
		// Builds the notification and issues it.
		mNotifyMgr.notify(mNotificationId, mBuilder.build());
	}
}
