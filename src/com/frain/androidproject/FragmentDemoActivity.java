package com.frain.androidproject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class FragmentDemoActivity extends Activity{
	
	MyExmapleFragment exmapleFragment=new MyExmapleFragment();//实例化一个自定义的fragment的对象
	FriendFragment friendFragment=new FriendFragment();
	@SuppressLint("NewApi")
	FragmentManager fragmentManager=getFragmentManager();//
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction(); 

		fragmentTransaction.add(R.id.fragment_group, exmapleFragment);
		fragmentTransaction.add(R.id.fragment_group, friendFragment);
		//fragmentTransaction.addToBackStack(String) 回退栈

		//fragmentTransaction.hide(exmapleFragment);
		Log.i("R.id.linear_layout",""+ R.id.linear_layout);
		
		fragmentTransaction.commit();
		
		
		initRadioGroup();
		
	}
	public void initRadioGroup(){
		RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radio_group);
		radioGroup.setOnCheckedChangeListener(changeListener);
	}
	@SuppressLint("NewApi")
	OnCheckedChangeListener changeListener=new OnCheckedChangeListener() {
		
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction(); 

			switch (arg0.getCheckedRadioButtonId()) {
			case R.id.main_btn:
				//fragmentTransaction.replace(R.id.fragment_group, friendFragment);

				fragmentTransaction.hide(exmapleFragment);
				fragmentTransaction.show(friendFragment);
				fragmentTransaction.commit();
				Log.i("ssssss", "隐藏");
				break;
			case R.id.friend_btn:

				fragmentTransaction.hide(friendFragment);
				fragmentTransaction.show(exmapleFragment);
				fragmentTransaction.commit();

				Log.i("ssssss", "显示");

				break;
			case R.id.circle_btn:
				
				  
				break;
			default:
				break;
			}
		}
	};

}
