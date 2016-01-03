package com.frain.androidproject;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter {
	List<CateInfo> data;
	Context context;
	LayoutInflater inflater;// 使用前提：对于一个没有被加载或者需要动态加载的界面

	// LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化；findViewById()是找xml布局文件下的具体widget控件(如Button、TextView等)。
	public MyBaseAdapter() {

	}

	public MyBaseAdapter(Context cont, List<CateInfo> data) {
		context = cont;
		this.data = data;
		inflater = LayoutInflater.from(this.context);
	}

	public void setAdapter(List<CateInfo> data) {
		this.data = data;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		CateInfo cateInfo = (CateInfo) getItem(position);
		if (convertView == null || convertView.getTag() == null) {
			convertView = inflater.inflate(R.layout.list_item, null);

			viewHolder = new ViewHolder();

			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.txt_address);

			viewHolder.linearlayout=(LinearLayout)convertView.findViewById(R.id.visble_linearlayout);
			viewHolder.ratingBar = (RatingBar) convertView
					.findViewById(R.id.rating_bar);
			viewHolder.checkBox = (CheckBox) convertView
					.findViewById(R.id.checkbox);

			convertView.setTag(viewHolder);

			viewHolder.checkBox.setTag(cateInfo);
			viewHolder.checkBox
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton arg0,
								boolean arg1) {
							// TODO Auto-generated method stub
							CateInfo info = (CateInfo) arg0.getTag();
							if (arg1) {
								if (info.getSales() > 0) {
									viewHolder.linearlayout.removeAllViews();
									for (int i = 0; i < info.getSales(); i++) {
										TextView textView = new TextView(
												context);
										textView.setText("优惠" + i);
										textView.setLayoutParams(new LinearLayout.LayoutParams(
												LinearLayout.LayoutParams.WRAP_CONTENT,
												LinearLayout.LayoutParams.WRAP_CONTENT));
										viewHolder.linearlayout
												.addView(textView);
									}

								}
								viewHolder.linearlayout
										.setVisibility(View.VISIBLE);
							} else {
								viewHolder.linearlayout
										.setVisibility(View.GONE);
							}
							info.setChecked(arg1);
						}
					});

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			viewHolder.checkBox.setTag(cateInfo);

		}

		viewHolder.textView.setText("距离："+cateInfo.getAddressLine()+"m");
		viewHolder.ratingBar.setRating((float)cateInfo.getStart());
		viewHolder.checkBox.setChecked(cateInfo.isChecked());
		if (cateInfo.isChecked()) {
			if (cateInfo.getSales() > 0) {
				viewHolder.linearlayout.removeAllViews();
				for (int i = 0; i < cateInfo.getSales(); i++) {
					TextView textView = new TextView(
							context);
					textView.setText("优惠" + i);
					textView.setLayoutParams(new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.WRAP_CONTENT,
							LinearLayout.LayoutParams.WRAP_CONTENT));
					viewHolder.linearlayout
							.addView(textView);
				}

			}
			viewHolder.linearlayout
					.setVisibility(View.VISIBLE);
		} else {
			viewHolder.linearlayout
					.setVisibility(View.GONE);
		}

		return convertView;
	}

	class ViewHolder {// 内部类
		TextView textView;
		LinearLayout linearlayout;
		RatingBar ratingBar;
		CheckBox checkBox;
	}

}
