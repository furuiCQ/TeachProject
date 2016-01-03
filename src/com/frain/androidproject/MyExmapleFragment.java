package com.frain.androidproject;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

@SuppressLint("NewApi")
public class MyExmapleFragment extends Fragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
		}
		@Override
		public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		// TODO Auto-generated method stub
			LinearLayout linearLayout=new LinearLayout(getActivity());
			linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			linearLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
			View view=inflater.inflate(R.layout.fragment_view, linearLayout,true);
//			resource：需要加载布局文件的id，意思是需要将这个布局文件中加载到Activity中来操作。
//
//			root：需要附加到resource资源文件的根控件，什么意思呢，就是inflate()会返回一个View对象，
//			如果第三个参数attachToRoot为true，就将这个root作为根对象返回，
//			否则仅仅将这个root对象的LayoutParams属性附加到resource对象的根布局对象上，也就是布局文件resource的最外层的View上，
//			比如是一个LinearLayout或者其它的Layout对象。
//
//			attachToRoot：是否将root附加到布局文件的根视图上
			//Log.i("container.getId();",""+ container.getId());
			return view;
			
		}
}	
