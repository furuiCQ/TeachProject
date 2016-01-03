package com.frain.androidproject;

import java.io.File;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class IntentDemoActivity extends Activity {
	TextView searchTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		searchTextView = (TextView) findViewById(R.id.txt_search);
		searchTextView.setOnClickListener(clickListener);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			// forAction();
			forData();
		}
	};

	public void forCompnent() {
		// Component属性明确指定Intent的目标组件的类名称。（属于直接Intent）
		// 如果 component这个属性有指定的话，将直接使用它指定的组件。
		// 指定了这个属性以后，Intent的其它所有属性都是可选的。
		// creat a new intent;
		Intent intent = new Intent();
		// creat a new component
		ComponentName componentName = new ComponentName(
				IntentDemoActivity.this, IntentDemoActivity.class);
		intent.setComponent(componentName);
		startActivity(intent);
		// when we user component into intent,it's belong to showIntent
		// other type belong implicitIntent

		// simple mode
		Intent intent1 = new Intent();
		intent1.setClass(IntentDemoActivity.this, IntentDemoActivity.class);
		startActivity(intent1);

		// normal mode
		Intent intent2 = new Intent(IntentDemoActivity.this,
				IntentDemoActivity.class);
		startActivity(intent2);
	}

	public void forAction() {
		// Action
		// 用来表现意图的行动
		// 当日常生活中，描述一个意愿或愿望的时候，总是有一个动词在其中。比如：我想“做”三个俯卧撑；我要“写” 一封情书，等等。
		// 在Intent中，Action就是描述做、写等动作的，当你指明了一个Action，
		// 执行者就会依照这个动作的指示，接受相关输入，表现对应行为，产生符合的输出。在Intent类中，定义了一批量的动作，
		// 比如ACTION_VIEW，ACTION_PICK等， 基本涵盖了常用动作。加的动作越多，越精确。
		// Action 是一个用户定义的字符串，用于描述一个 Android 应用程序组件，
		// 一个 Intent Filter 可以包含多个 Action。在 AndroidManifest.xml 的Activity 定义时，
		// 可以在其 <intent-filter >节点指定一个 Action列表用于标识 Activity 所能接受的“动作”。

		// Intent intent3 = new Intent();
		// /* 开启Pictures画面Type设定为image */
		// intent3.setType("image/*");
		// /* 使用Intent.ACTION_GET_CONTENT这个Action */
		// intent3.setAction(Intent.ACTION_GET_CONTENT);
		// /* 取得相片后返回本画面 */
		// startActivityForResult(intent3, 1);
		//

		// 启动另一个Activity，（通过action属性进行查找）
		Intent intent = new Intent();
		// 设置动作（实际action属性就是一个字符串标记而已）
		intent.setAction("com.example.intent01.MY_ACTION"); // 方法：Intent
															// android.content.Intent.setAction(String
															// action)
		intent.addCategory("com.example.intent01.MY_CATEGORY");
		startActivity(intent);

	}

	public void forData() {
		// data（数据）：表示与动作要操纵的数据
		// Data属性是Android要访问的数据，和action和Category声明方式相同，也是在<intent-filter>中。
		// 多个组件匹配成功显示优先级高的； 相同显示列表。
		// Data是用一个uri对象来表示的，uri代表数据的地址，属于一种标识符。
		// 通常情况下，我们使用action+data属性的组合来描述一个意图：做什么
		// 使用隐式Intent，我们不仅可以启动自己程序内的活动，还可以启动其他程序的活动，
		// 这使得Android多个应用程序之间的功能共享成为了可能。比如应用程序中需要展示一个网页，没有必要自己去实现一个浏览器（事实上也不太可能），
		// 而是只需要条用系统的浏览器来打开这个网页就行了。
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		Uri data = Uri.parse("http://www.baidu.com");
		intent.setData(data);
		startActivity(intent);

		// simple mode
		Intent intent1 = new Intent(Intent.ACTION_VIEW);
		intent1.setData(Uri.parse("http://www.baidu.com"));
		startActivity(intent1);

		// youxianji
		// android:priority="-1"
	}

	public void forType() {
		// 如果Intent对象中既包含Uri又包含Type，那么，在<intent-filter>中也必须二者都包含才能通过测试。
		// Type属性用于明确指定Data属性的数据类型或MIME类型，但是通常来说，当Intent不指定Data属性时，Type属性才会起作用，
		// 否则Android系统将会根据Data属性值来分析数据的类型，所以无需指定Type属性。
		// data和type属性一般只需要一个，通过setData方法会把type属性设置为null，相反设置setType方法会把data设置为null，
		// 如果想要两个属性同时设置，要使用Intent.setDataAndType()方法。

		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		Uri data = Uri.parse("music uri");
		// 设置data+type属性
		intent.setDataAndType(data, "audio/mp3"); // 方法：Intent
													// android.content.Intent.setDataAndType(Uri
													// data, String type)
		startActivity(intent);
	}

	// 打开指定网页
	public void browsePageClick(View view) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://www.baidu.com/"));
		startActivity(intent);

	}

	// 打开拨号面板
	public void openDialPageClick(View view) {
		Intent intent = new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:10086"));
		startActivity(intent);
	}

	// 直接拨打指定号码
	public void dialPhoneClick(View view) {
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:10086"));
		startActivity(intent);
	}

	// 打开发短信的界面:action+type
	public void openMsgPageClick(View view) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setType("vnd.android-dir/mms-sms");
		intent.putExtra("sms_body", "具体短信内容"); // "sms_body"为固定内容
		startActivity(intent);
	}

	// 打开发短信的界面(指定电话号码):action+data
	public void sendMsgClick(View view) {
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.setData(Uri.parse("smsto:13539921099"));
		intent.putExtra("sms_body", "具体短信内容"); // "sms_body"为固定内容
		startActivity(intent);
	}

	// 播放指定路径音乐
	public void playMusicClick(View view) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri uri = Uri.parse("file:///storage/sdcard0/music1.mp3"); // 路径也可以写成："/storage/sdcard0/music1.mp3"
		intent.setDataAndType(uri, "audio/mp3"); // 方法：Intent
													// android.content.Intent.setDataAndType(Uri
													// data, String type)
		startActivity(intent);
	}

	// 卸载某个应用程序，根据包名来识别
	public void uninstallClick(View view) {
		Intent intent = new Intent(Intent.ACTION_DELETE);
		Uri data = Uri.parse("package:com.example.smyh006intent01");
		intent.setData(data);
		startActivity(intent);
	}

	// 安装某个应用程序，根据apk的文件名来识别
	public void installClick(View view) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri data = Uri
				.fromFile(new File("/storage/sdcard0/AndroidTest/xxx.apk")); // 路径不能写成："file:///storage/sdcard0/···"
		intent.setDataAndType(data, "application/vnd.android.package-archive"); // Type的字符串为固定内容
		startActivity(intent);
	}

}
