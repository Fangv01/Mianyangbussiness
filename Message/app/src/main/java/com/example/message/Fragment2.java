package com.example.message;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.message.address.ABAdd;
import com.example.message.address.ABContract;
import com.example.message.address.ABCustomerAdapter;
import com.example.message.address.ABView;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private View view;
    private Button Add;
    private int contactId;
    private byte[] image;
    private String firstName, lastName;
    private ABCustomerAdapter<String> customArrayAdapter;

    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.activity_address,container,false);
        showAllContacts();
        return view;
    }
    public void showAllContacts(){
        Add=view.findViewById(R.id.buttonAddContact);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ABAdd.class);
                startActivity(intent);
            }
        });

        String columns[] = new String[] {
                ABContract.ID,
                ABContract.LASTNAME,
                ABContract.FIRSTNAME,
                ABContract.IMAGE
        };

        final Uri contactsUri = ABContract.CONTACTS_URI;

        Cursor cursor = getActivity().getContentResolver().query(contactsUri, columns, null, null, null, null);
        final ArrayList id = new ArrayList();
        final ArrayList names = new ArrayList();
        final ArrayList images = new ArrayList();

        while(cursor.moveToNext()) {
            contactId = cursor.getInt(0);
            firstName = cursor.getString(1);
            lastName = cursor.getString(2);
            image = cursor.getBlob(3);
            id.add(contactId);
            names.add(lastName + " " + firstName);
            images.add(image);
        }
        cursor.close();

        customArrayAdapter = new ABCustomerAdapter<String>(getActivity(), names, images);
        listView = view.findViewById(R.id.contactsListView);
        listView.setAdapter(customArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int contactId = (int) id.get(i);
                Bundle bundle = new Bundle();
                bundle.putInt("contactId",contactId);

                Intent intent = new Intent(getActivity().getApplicationContext(), ABView.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    public void onResume() {
        super.onResume();
        this.showAllContacts();
    }

}
