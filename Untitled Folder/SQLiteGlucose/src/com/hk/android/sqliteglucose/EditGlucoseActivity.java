package com.hk.android.sqliteglucose;

import java.util.List;

import com.hk.android.sqliteglucose.model.Glucose;
import com.hk.android.sqliteglucose.model.GlucoseManager;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class EditGlucoseActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_glucose);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new EditGlucoseFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_glucose, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class EditGlucoseFragment extends ListFragment {

		private GlucoseManager glucoseManager;

		public EditGlucoseFragment() {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.fragment_edit_glucose,
					container, false);

			glucoseManager = new GlucoseManager(getActivity());
			glucoseManager.open();

			List<Glucose> values = glucoseManager.getAllGLucoses();

			ArrayAdapter<Glucose> adapter = new ArrayAdapter<Glucose>(
					getActivity(), android.R.layout.simple_list_item_1, values);
			setListAdapter(adapter);

			Button btnAdd = (Button) v.findViewById(R.id.btn_add_glucose);
			btnAdd.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					ArrayAdapter<Glucose> adapter = (ArrayAdapter<Glucose>) getListAdapter();

					EditText text = (EditText) v.findViewById(R.id.editText1);
					Glucose value = glucoseManager.addGlucose(text.getText()
							.toString());

					adapter.add(value);
				}

			});

			return v;
		}
	}

}
