package com.frain.androidproject;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ContentProvideActivity extends Activity {
	ListView listView;
	Button insertBtn;
	Button refreshBtn;

	EditText userNameEdit;
	EditText userTelEdit;
	ContactAdapter contactAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_privoder);

		listView = (ListView) findViewById(R.id.contacts_listview);
		insertBtn = (Button) findViewById(R.id.get_list);
		refreshBtn = (Button) findViewById(R.id.refresh_data);
		userNameEdit = (EditText) findViewById(R.id.username);
		userTelEdit = (EditText) findViewById(R.id.usertel);

		insertBtn.setOnClickListener(clickListener);
		refreshBtn.setOnClickListener(clickListener);
		contactAdapter = new ContactAdapter(
				ContactsTools.getPhoneContacts(this), this);
		listView.setAdapter(contactAdapter);
	}


	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.get_list:
				ContactData contactData = new ContactData();
				contactData.setContactName(userNameEdit.getText().toString());
				contactData.setNumber(userTelEdit.getText().toString());
				ContactsTools.insertPhoneContact(ContentProvideActivity.this,
						contactData);

				break;
			case R.id.refresh_data:
				contactAdapter.setData(ContactsTools.getPhoneContacts(ContentProvideActivity.this));
				break;
			default:
				break;
			}
		}
	};

	@SuppressLint("ViewHolder")
	public class ContactAdapter extends BaseAdapter {
		List<ContactData> list;
		Context context;
		LayoutInflater layoutInflater;

		public ContactAdapter() {

		}

		public ContactAdapter(List<ContactData> list, Context context) {
			this.list = list;
			this.context = context;
			layoutInflater = LayoutInflater.from(context);
		}

		public void setData(List<ContactData> list) {
			this.list = list;
			this.notifyDataSetChanged();

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub
			view = layoutInflater.inflate(R.layout.listview_contact, null);
			TextView ContactId = (TextView) view.findViewById(R.id.contact_id);
			TextView ContactName = (TextView) view
					.findViewById(R.id.contact_name);
			TextView ContactTel = (TextView) view
					.findViewById(R.id.contact_tel);

			ContactData contactData = list.get(arg0);
			ContactId.setText(contactData.getId());
			ContactName.setText(contactData.getContactName());
			ContactTel.setText(contactData.getNumber());

			return view;
		}

	}

}
