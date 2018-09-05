package com.example.captprice.packagesandcontacts;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactInfo();
        AppInfo();

    }

    private void ContactInfo() {
        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);


        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                int hasphone = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String Id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                if(hasphone>0) {

                    Cursor cursor2 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{Id}, null);
                    while (cursor2.moveToNext()) {
                     String phone = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.e("name ", name);
                        Log.e("ID", phone);
                    }
                    cursor2.close();
                }
            }
        }
//
//
        cursor.close();

    }

    private void AppInfo() {


        List<PackageInfo>packagelist = getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packagelist.size();i++)
        {
            PackageInfo packageInfo =packagelist.get(i);
            String packageName = packageInfo.packageName;
          Log.e("Package_name",packageName);
        }

    }


}
