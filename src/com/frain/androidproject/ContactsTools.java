package com.frain.androidproject;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.text.TextUtils;
import android.util.Log;

/**
 * 用于联系人获取，添加，删除，修改，排序等操作。 
 * */
public class ContactsTools {
	
	    final static String TAG = "ContactsTools";
	    final static String PhoneAccountName = "Phone";
	    
	    // 读取联系人信息
	    public static List<ContactData> getPhoneContacts(Context context)
	    {
	       List<ContactData> list = new ArrayList<ContactData>();
	        
	    	Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
	    	          null,
	    	          null,
	    	          null, null);
	        while (cursor.moveToNext())
	        {
	            int indexId = cursor.getColumnIndex(ContactsContract.Contacts._ID);
	        	String contactId = cursor.getString(indexId);
	        	int indexDisplayName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
	            String name = cursor.getString(indexDisplayName);
	            
	            Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
	                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
	            while (phones.moveToNext())
	            {
	                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	                ContactData data = new ContactData();
	                data.id = contactId;
	                data.name = name;
	                data.number = phoneNumber;   
	                Log.i(TAG, name);
	                list.add(data);
	            }
	            phones.close();
	        }
	        cursor.close();
	        return list;
	    }
	            
	  
	    
	    public static boolean insertPhoneContact(Context context, ContactData contact){
	        /**
	         * 首先向RawContacts.CONTENT_URI执行一个空值插入，目的是获取系统返回的rawContactId
	         * 这时后面插入data表的依据，只有执行空值插入，才能使插入的联系人在通讯录里面可见
	         */
	            Uri rcUri;
	            ContentValues values = new ContentValues();
	            
//	            ContentResolver resolver = context.getContentResolver();
	            //首先向RawContacts.CONTENT_URI执行一个空值插入，目的是获取系统返回的rawContactId 
	            values.put(RawContacts.ACCOUNT_NAME, PhoneAccountName);
	            values.put(RawContacts.ACCOUNT_TYPE, "null");
	            values.put(ContactsContract.Contacts.DISPLAY_NAME, contact.getContactName());
	            Uri rawContactUri =context.getContentResolver().insert(RawContacts.CONTENT_URI, values);
	            long rawContactId = ContentUris.parseId(rawContactUri);
	            contact.setId(rawContactId+"");
	            
	            //往data表入姓名数据
	            values.clear();
	            values.put(Data.RAW_CONTACT_ID,rawContactId); 
	            values.put(Data.MIMETYPE,StructuredName.CONTENT_ITEM_TYPE);//内容类型
	            values.put(StructuredName.GIVEN_NAME,contact.getContactName());
	            rcUri = context.getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI,values);
	            
	            if(rcUri != null){
	              //往data表入电话数据
	                values.clear();
	                values.put(Data.RAW_CONTACT_ID,rawContactId);
	                values.put(Data.MIMETYPE,Phone.CONTENT_ITEM_TYPE);
	                values.put(Phone.NUMBER,contact.getNumber());
	                values.put(Phone.TYPE,Phone.TYPE_WORK);
	                rcUri = context.getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI,values);

	            }
	            
	            return (rcUri != null);
	    }
	    
	    public static boolean updatePhoneContact(Context context, ContactData contact){
	      
	            ContentValues values = new ContentValues();
	            
	            values.clear();
	            values.put(ContactsContract.Contacts.DISPLAY_NAME, contact.getContactName());
	            int rc1 = context.getContentResolver().update(RawContacts.CONTENT_URI,values,
	                    ContactsContract.Contacts._ID + "=?", new String[]{contact.getId()});
	            
	            values.clear();
	            values.put(Data.MIMETYPE,Phone.CONTENT_ITEM_TYPE);
	            values.put(Phone.NUMBER,contact.getNumber());
	            int rc2 = context.getContentResolver().update(android.provider.ContactsContract.Data.CONTENT_URI,values,
	                    Data.RAW_CONTACT_ID + "=?", new String[]{contact.getId()+""});
	            return (rc1>0 || rc2 >0) ? true : false;
	    }

	    /**根据contactId删除联系人数据*/
	    public static int deletePhoneContact(Context context,String name, String contactId)
	    {  
	        ContentResolver resolver = context.getContentResolver();
	        int rc1 = 0, rc2 = 0;
	        //删除data表中数据
	        String where = ContactsContract.Data.CONTACT_ID + " =?";
	        String[] whereparams = new String[] { contactId };
	        rc1 = resolver.delete(ContactsContract.Data.CONTENT_URI, where, whereparams);
	        
	        //删除rawContact表中数据
	        where = ContactsContract.RawContacts.CONTACT_ID + " =?";
	        whereparams = new String[] { contactId };
	        rc2 = resolver.delete(ContactsContract.RawContacts.CONTENT_URI, where, whereparams);
	        
	        return (rc1>0 && rc2>0) ? (rc1+rc2) : 0;
	    }  
	    
}