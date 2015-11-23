package br.com.cartools;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.TextView;
import br.com.cartools.dao.CarDAO;
import br.com.cartools.models.Car;

public class ResumeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//SQLiteDatabase db = db.openDatabase("data/data/br.com.cartools/databases/cartools", null, SQLiteDatabase.OPEN_READWRITE)execSQL("DROP TABLE IF EXISTS car");
		//db.close();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.resume);
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		CarDAO carDAO = new CarDAO(this);
		Car car = carDAO.getCar();
		
		if (car != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Date oilDate= new Date(((long)car.getOilDate()) * 1000L);
			Date tireDate= new Date(((long)car.getTireDate()) * 1000L);
			
			TextView brandView = (TextView) findViewById(R.id.brand);
			TextView modelView = (TextView) findViewById(R.id.model);
			TextView yearView = (TextView) findViewById(R.id.year);
			TextView oilView = (TextView) findViewById(R.id.oil);
			TextView tireView = (TextView) findViewById(R.id.tire);
			
			brandView.setText(car.getBrand());
			modelView.setText(car.getModel());
			yearView.setText(String.valueOf(car.getYear()));
			oilView.setText(sdf.format(oilDate));
			tireView.setText(sdf.format(tireDate));
			
		}
	}
	
	@SuppressLint("NewApi")
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		MenuItem menuUpdate = menu.add("Atualizar Dados");
		menuUpdate.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		menuUpdate.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				Intent goToUpdateCar = new Intent(ResumeActivity.this, UpdateCarActivity.class);
				startActivity(goToUpdateCar);
				
				return false;
			}
			
		});
		
		MenuItem menuAbout = menu.add("Sobre");
		menuAbout.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		menuAbout.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				Intent goToAbout = new Intent(ResumeActivity.this, AboutActivity.class);
				startActivity(goToAbout);
				
				return false;
			}
			
		});
		
		
		return super.onCreateOptionsMenu(menu);
	}
}
