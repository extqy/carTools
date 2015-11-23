package br.com.cartools.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.cartools.models.Car;

public class CarDAO extends SQLiteOpenHelper {
	
	final static protected String DATABASE = "cartool";
	final static protected String TABLE = "car";
	final static protected int VERSION = 1;
			
	public CarDAO(Context ctx) {
		// TODO Auto-generated constructor stub
		super(ctx, DATABASE, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		
		String sql = "CREATE TABLE " + TABLE +   
					 "(id INTEGER PRIMARY KEY, " +
					 "brand TEXT, " +
					 "model TEXT, " +
					 "year INTEGER, " +
					 "oil_date INTEGER, " +
					 "tire_date INTEGER);";
		
		database.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int old_version, int new_version) {
		// TODO Auto-generated method stub
		
	}
	
	public Car getCar() {
		
		Car car = null;
		Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE + " LIMIT 1", null);
		
		if (cursor.moveToNext()) {
			car = new Car();
			car.setId(cursor.getInt(cursor.getColumnIndex("id")));
			car.setBrand(cursor.getString(cursor.getColumnIndex("brand")));
			car.setModel(cursor.getString(cursor.getColumnIndex("model")));
			car.setYear(cursor.getInt(cursor.getColumnIndex("year")));
			car.setOilDate(cursor.getInt(cursor.getColumnIndex("oil_date")));
			car.setTireDate(cursor.getInt(cursor.getColumnIndex("tire_date")));
		}
		
		return car;
	}
	
	public Car createCar() {
		Car car = new Car();
		ContentValues values = new ContentValues();
		values.put("brand", "dasadsds");
		car.setId((int)getWritableDatabase().insert(TABLE, null, values));
		
		return car;
	}
	
	public void save(Car car) {
		
		ContentValues values = new ContentValues();
		values.put("brand", car.getBrand());
		values.put("model", car.getModel());
		values.put("year", car.getYear());
		values.put("oil_date", car.getOilDate());
		values.put("tire_date", car.getTireDate());
		
		String[] whereArgs = {car.getId().toString()};
		
		getWritableDatabase().update(TABLE, values, "id=?", whereArgs);
	}
}
