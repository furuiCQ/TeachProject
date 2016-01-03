package com.frain.androidproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView listview;
	MyBaseAdapter adapter;

	AutoCompleteTextView autoCompleteTextView;

	TextView textBack;
	TextView textSearch;

	Spinner spinnerAll;
	String[] allData = new String[] { "综合排序", "销量最高", "速度最快", "评分最高", "起送价最低" };
	ArrayAdapter<String> allAdapter;

	Spinner spinnerAddress;
	String[] addressData = new String[] { "由近到远", "由远到近" };

	ArrayAdapter<String> addressAdapter;

	Spinner spinnerSales;
	String[] salesData = new String[] { "优惠最多", "折扣最多" };
	ArrayAdapter<String> salesAdapter;

	Spinner spinnerAssess;
	String[] assessData = new String[] { "一星", "二星", "三星", "四星", "五星", };
	ArrayAdapter<String> assessAdapter;

	List<CateInfo> listviewData = new ArrayList<CateInfo>();
	List<CateInfo> addList=new ArrayList<CateInfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		spinnerAll = (Spinner) findViewById(R.id.spinner_all);
//		spinnerAddress = (Spinner) findViewById(R.id.spinner_address);
//		spinnerSales = (Spinner) findViewById(R.id.spinner_sales);
//		spinnerAssess = (Spinner) findViewById(R.id.spinner_assess);
//
//		autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto_complete_textview);
//		listview = (ListView) findViewById(R.id.listview);
		textBack = (TextView) findViewById(R.id.txt_back);
		textSearch = (TextView) findViewById(R.id.txt_search);

		allAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, allData);
		addressAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, addressData);
		salesAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, salesData);
		assessAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, assessData);

		getData();

		spinnerAll.setAdapter(allAdapter);
		spinnerAddress.setAdapter(addressAdapter);
		spinnerSales.setAdapter(salesAdapter);
		spinnerAssess.setAdapter(assessAdapter);

		adapter = new MyBaseAdapter(this, listviewData);

		listview.setAdapter(adapter);
		spinnerAddress.setOnItemSelectedListener(clickListener);
		spinnerAddress.setSelection(0,true);

	}
	OnItemSelectedListener clickListener=new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			//adapter.setAdapter(getSalesData());
			if(arg2>0){
				
				Collections.sort(addList);
				adapter.setAdapter(addList);

			}else{
				adapter.setAdapter(listviewData);
			}
			Toast.makeText(MainActivity.this, "=======", Toast.LENGTH_LONG).show();
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}

		
	};

	public void getData() {
		for (int i = 0; i < 5; i++) {
			CateInfo cateInfo = new CateInfo();
			cateInfo.setAddressLine((int) (Math.random() * 1000));
			cateInfo.setStart((float) Math.random() * 10);
			cateInfo.setSales((int) (Math.random() * 10));
			listviewData.add(cateInfo);
			addList.add(cateInfo);
		}
	}



	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.txt_back:

				break;
			case R.id.txt_search:

				break;
			default:
				break;
			}
			// adapter.setAdapter(data);
		}
	};

	// public void getData(){
	// data.add("我是List中的一个Item");
	// data.add("我是List中的一个Item");
	// data.add("我是List中的一个Item");
	// data.add("我是List中的一个Item");
	// data.add("我是List中的一个Item");
	// data.add("我是List中的一个Item");
	// data.add("我是List中的一个Item");
	// data.add("我是List中的一个Item");
	// data.add("我是List中的一个Item");
	// data.add("我是List中的一个Item");
	//
	// }
}
