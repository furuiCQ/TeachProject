package com.frain.androidproject;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

@SuppressLint("NewApi")
public class FriendFragment extends Fragment {
	
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
		 ViewGroup container,  Bundle savedInstanceState) {
	// TODO Auto-generated method stub
		LinearLayout linearLayout=new LinearLayout(getActivity());
		linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		linearLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
		View view=inflater.inflate(R.layout.fragment_view, linearLayout,true);
		TextView textView=(TextView)view.findViewById(R.id.textview);
		textView.setText("我是好友页面");
		textView.setOnClickListener(clickListener);
		return view;
		
	}
	OnClickListener clickListener=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			  MyExmapleFragment fThree = new MyExmapleFragment();  
		        FragmentManager fm = getFragmentManager();  
		        FragmentTransaction tx = fm.beginTransaction();  
		       tx.hide(FriendFragment.this); 
		       tx.add(R.id.fragment_linearlayout , fThree, "THREE");  
//		      tx.replace(R.id.id_content, fThree, "THREE");  
		        tx.addToBackStack(null);  
		        tx.commit();  
		}
	};
	
}	
