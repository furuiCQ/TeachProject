package com.frain.androidproject;

public class CateInfo implements Comparable<CateInfo>{
	public Integer addressLine;
	public float start;
	public int sales;
	public boolean checked=false;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}


	public Integer getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(Integer addressLine) {
		this.addressLine = addressLine;
	}

	public float getStart() {
		return start;
	}

	public void setStart(float start) {
		this.start = start;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}
	


	@Override
	public int compareTo(CateInfo arg0) {
		// TODO Auto-generated method stub
		return this.getAddressLine().compareTo(arg0.getAddressLine());
	}

}
