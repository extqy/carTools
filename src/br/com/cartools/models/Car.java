package br.com.cartools.models;

public class Car {
	protected Integer id;
	protected String brand;
	protected String model;
	protected int year;
	protected int oilDate;
	protected int tireDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getOilDate() {
		return oilDate;
	}
	public void setOilDate(int oilDate) {
		this.oilDate = oilDate;
	}
	public int getTireDate() {
		return tireDate;
	}
	public void setTireDate(int tireDate) {
		this.tireDate = tireDate;
	}
	
	
	
}
