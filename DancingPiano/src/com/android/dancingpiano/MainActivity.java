package com.android.dancingpiano;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	MediaPlayer dancingP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("Pickle", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		Log.e("Pickle", "onResume");
		dancingP = MediaPlayer.create(this, R.raw.dance);
		dancingP.start();
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.e("Pickle", "onPause");
		dancingP.stop();
		dancingP.release();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void openFB(View V) {
		String url = "https://www.facebook.com/lynda";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);

	}

}
