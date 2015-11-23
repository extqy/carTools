package br.com.cartools;

import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.cartools.dao.CarDAO;
import br.com.cartools.models.Car;

public class UpdateCarActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.update_car);
		
		CarDAO carDAO = new CarDAO(this);
		Car car = carDAO.getCar();
		
		if (car != null) {
			
			Log.v("teste", String.valueOf((long)car.getOilDate()));
						
			Date oilDate= new Date(((long)car.getOilDate()) * 1000L);
			Date tireDate= new Date(((long)car.getTireDate()) * 1000L);
			
			EditText brandEdit = (EditText) findViewById(R.id.brand);
			EditText modelEdit = (EditText) findViewById(R.id.model);
			EditText yearEdit = (EditText) findViewById(R.id.year);
			DatePicker oilEdit = (DatePicker) findViewById(R.id.oil);
			DatePicker tireEdit = (DatePicker) findViewById(R.id.tire);
			
			brandEdit.setText(car.getBrand());
			modelEdit.setText(car.getModel());
			yearEdit.setText(String.valueOf(car.getYear()));
			oilEdit.updateDate(oilDate.getYear() + 1900, oilDate.getMonth(), oilDate.getDate());
			tireEdit.updateDate(tireDate.getYear() + 1900, tireDate.getMonth(), tireDate.getDate());
		}
		
		Button submit = (Button) findViewById(R.id.submit);
		submit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				CarDAO carDAO = new CarDAO(UpdateCarActivity.this);
				Car car = carDAO.getCar();
				
				if (null == car) {
					car = carDAO.createCar();
				}
				
				EditText brandEdit = (EditText) UpdateCarActivity.this.findViewById(R.id.brand);
				EditText modelEdit = (EditText) UpdateCarActivity.this.findViewById(R.id.model);
				EditText yearEdit = (EditText) UpdateCarActivity.this.findViewById(R.id.year);
				DatePicker oilEdit = (DatePicker) UpdateCarActivity.this.findViewById(R.id.oil);
				DatePicker tireEdit = (DatePicker) UpdateCarActivity.this.findViewById(R.id.tire);
				
				Calendar oilCalendar = new GregorianCalendar(oilEdit.getYear(), oilEdit.getMonth(), oilEdit.getDayOfMonth());
				Calendar tireCalendar = new GregorianCalendar(tireEdit.getYear(), tireEdit.getMonth(), tireEdit.getDayOfMonth());
				
				car.setBrand(brandEdit.getText().toString());
				car.setModel(modelEdit.getText().toString());
				car.setYear((int)Integer.valueOf(yearEdit.getText().toString()));
				car.setOilDate((int)(oilCalendar.getTimeInMillis() / 1000L));
				car.setTireDate((int)(tireCalendar.getTimeInMillis() / 1000L));

				carDAO.save(car);
				carDAO.close();
				
				Toast.makeText(UpdateCarActivity.this, "Seus dados foram alterados", Toast.LENGTH_SHORT);
				
				finish();
			}
		});
		
		
	}
}
