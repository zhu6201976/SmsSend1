package com.example.administrator.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * ContactsActivity
 * Created by My on 2017/12/10.
 */

public class ContactsActivity extends AppCompatActivity {
    private ArrayList<Contact> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ListView lv = (ListView) findViewById(R.id.lv);
        mList = new ArrayList<>();
        Contact contact;
        for (int i = 0; i < 20; i++) {
            contact = new Contact();
            contact.name = "zhangsan" + i;
            if (i < 10) {
                contact.phone = "1388888888" + i;
            } else {
                contact.phone = "138888888" + i;
            }
            mList.add(contact);
        }
        lv.setAdapter(new ContactsAdapter());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact1 = mList.get(position);
                Intent intent = getIntent();
                intent.putExtra("phone", contact1.phone);
                setResult(10, intent);
                finish();
            }
        });

    }

    private class ContactsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Contact getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.listview_contacts_item, null);
            }
            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            Contact contact = getItem(position);
            tv_name.setText(contact.name);
            tv_phone.setText(contact.phone);
            return convertView;
        }
    }
}
