package com.bimco.chippet;

import com.bimco.chippet.service.ClipboardWatchService;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private static final String TAG_LIST = "list";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		ExampleMode mode = new ExampleMode();
		startActionMode(mode);

		this.startService(new Intent(this, ClipboardWatchService.class));

		/**
		 * This will start the ClipListFragment, which will be used to display a
		 * list of data
		 */
		Fragment fragment = getFragmentManager().findFragmentByTag(TAG_LIST);
		if (fragment == null) {
			fragment = ClipListFragment.newInstance();
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.add(android.R.id.content, fragment, TAG_LIST);
			ft.commit();
		}

	}

	private class ExampleMode implements ActionMode.Callback {

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

			final CharSequence[] items = { "2", "4", "6", "8", "10", "20" };
			AlertDialog.Builder builder = new AlertDialog.Builder(
					MainActivity.this);
			builder.setTitle("Choose how many clips to display:");
			builder.setItems(items, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (which == 0)
						ClipListFragment.numClips = 2;
					else if (which == 1)
						ClipListFragment.numClips = 4;
					else if (which == 2)
						ClipListFragment.numClips = 6;

					else if (which == 3)
						ClipListFragment.numClips = 8;
					else if (which == 4)
						ClipListFragment.numClips = 10;

					else
						ClipListFragment.numClips = 20;

				}
			});

			AlertDialog alert = builder.create();
			alert.show();

			return true;
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			mode.getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {

		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}
	}
}
