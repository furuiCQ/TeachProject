package com.frain.androidproject;

import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

public class DialogActivity extends Activity {
	Notification notification;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);

		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// manager.

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
	//
		setIconEnable(menu, true);

		menu.add(0, 0, 1, "姬鑫").setIcon(R.drawable.ic_launcher);

		menu.add(0, 1, 2, "老大").setIcon(R.drawable.ic_launcher);

		menu.add(0, 2, 3, "老二").setIcon(R.drawable.ic_launcher);

		menu.add(0, 3, 4, "老三").setIcon(R.drawable.ic_launcher);

		return super.onCreateOptionsMenu(menu);

	}

	private void setIconEnable(Menu menu, boolean enable)

	{

		try

		{

			// 未知的类

			Class<?> clazz = Class
					.forName("com.android.internal.view.menu.MenuBuilder");

			Method m = clazz.getDeclaredMethod("setOptionalIconsVisible",
					boolean.class);

			m.setAccessible(true);

			// MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)

			m.invoke(menu, enable);

		} catch (Exception e)

		{

			e.printStackTrace();

		}

	}

}
