package com.hk.android.sqliteglucose.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.hk.android.sqliteglucose.db.GlucoseDBOpenHelper;

public class GlucoseManager {
	private GlucoseDBOpenHelper dbHelper;
	private String[] GLUCOSE_TABLE_COLUMNS = { GlucoseDBOpenHelper.GLUCOSE_ID,
			GlucoseDBOpenHelper.GLUCOSE_VALUE };
	private SQLiteDatabase database;

	public GlucoseManager(Context context) {
		dbHelper = new GlucoseDBOpenHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Glucose addGlucose(String value) {
		ContentValues cv = new ContentValues();

		cv.put(GlucoseDBOpenHelper.GLUCOSE_VALUE, value);

		long glucoseId = database.insert(GlucoseDBOpenHelper.GLUCOSE, null, cv);

		Cursor cursor = database.query(GlucoseDBOpenHelper.GLUCOSE,
				GLUCOSE_TABLE_COLUMNS, GlucoseDBOpenHelper.GLUCOSE_ID, null,
				null, null, null);
		cursor.moveToFirst();

		Glucose newValue = parseGlucose(cursor);
		cursor.close();
		return newValue;

	}
	
	public void deleteGlucose(Glucose glucose){
		long id = glucose.getId();
		System.out.println("Glucose deleted with id: " + id);
		database.delete(GlucoseDBOpenHelper.GLUCOSE, GlucoseDBOpenHelper.GLUCOSE_ID + " = " + id , null);
	}
	
	public List<Glucose> getAllGLucoses(){
		List<Glucose> glucoses = new ArrayList<Glucose>();
		
		Cursor cursor = database.query(GlucoseDBOpenHelper.GLUCOSE, GLUCOSE_TABLE_COLUMNS, null, null, null, null, null);
		
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()){
			Glucose glucose = parseGlucose(cursor);
			glucoses.add(glucose);
			cursor.moveToNext();
		}
		
		cursor.close();
		return glucoses;
		
	}
	
	

	private Glucose parseGlucose(Cursor cursor) {
		Glucose glucose = new Glucose();
		glucose.setId(cursor.getInt(0));
		glucose.setGlucose_value(cursor.getString(1));
		return glucose;
	}

}
