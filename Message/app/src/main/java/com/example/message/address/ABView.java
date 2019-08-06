package com.example.message.address;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.example.message.R;
import com.example.message.SecondActivity;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ABView extends Activity implements View.OnClickListener {
    byte[] photo;
    private int contactId;
    private String contactFirstName, contactLastName, contactPhone, contactPhone2, contactEmail, contactEmail2, contactAddress;

    private ImageView contactImage;
    private TextView nameView, phoneView, phoneView2, mailView, mailView2, addressView;
    private LinearLayout layoutPhone1, layoutPhone2, layoutMail1, layoutMail2, layoutAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_view);

        contactImage = (ImageView) findViewById(R.id.contactImage);
        contactImage.setImageResource(R.drawable.contact_high);
        nameView = (TextView) findViewById(R.id.firstNameView);
        phoneView = (TextView) findViewById(R.id.phoneNumberView);
        phoneView.setOnClickListener(this);
        phoneView2 = (TextView) findViewById(R.id.phoneNumberView2);
        phoneView2.setOnClickListener(this);
        mailView = (TextView) findViewById(R.id.emailAddressView);
        mailView.setOnClickListener(this);
        mailView2 = (TextView) findViewById(R.id.emailAddressView2);
        mailView2.setOnClickListener(this);
        addressView = (TextView) findViewById(R.id.addressView);
        layoutPhone1 = (LinearLayout) findViewById(R.id.layoutPhone1);
        layoutPhone2 = (LinearLayout) findViewById(R.id.layoutPhone2);
        layoutMail1 = (LinearLayout) findViewById(R.id.layoutMail1);
        layoutMail2 = (LinearLayout) findViewById(R.id.layoutMail2);
        layoutAddress = (LinearLayout) findViewById(R.id.layoutAddress);

        Bundle bundle = getIntent().getExtras();
        contactId = bundle.getInt("contactId");
        displayDetails();
    }

    public void displayDetails() {
        String columns[] = new String[]{
                ABContract.FIRSTNAME,
                ABContract.LASTNAME,
                ABContract.PHONE,
                ABContract.PHONE_TWO,
                ABContract.EMAIL,
                ABContract.EMAIL_TWO,
                ABContract.ADDRESS,
                ABContract.IMAGE
        };

        final Uri contactsUri = ABContract.CONTACTS_URI;
        Cursor cursor = getContentResolver().query(contactsUri, columns, ABContract.ID + "=" + contactId, null, null, null);
        while (cursor.moveToNext()) {
            contactFirstName = cursor.getString(0);
            contactLastName = cursor.getString(1);
            contactPhone = cursor.getString(2);
            contactPhone2 = cursor.getString(3);
            contactEmail = cursor.getString(4);
            contactEmail2 = cursor.getString(5);
            contactAddress = cursor.getString(6);
            photo = cursor.getBlob(7);
        }
        cursor.close();

        ByteArrayInputStream imageStream = new ByteArrayInputStream(photo);
        Bitmap image = BitmapFactory.decodeStream(imageStream);

        nameView.setText(contactFirstName + " " + contactLastName);
        showView(contactPhone, phoneView, layoutPhone1);
        showView(contactPhone2, phoneView2, layoutPhone2);
        showView(contactEmail, mailView, layoutMail1);
        showView(contactEmail2, mailView2, layoutMail2);
        showView(contactAddress, addressView, layoutAddress);
        contactImage.setImageBitmap(image);
        try {
            imageStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showView(String field, TextView view, LinearLayout layout) {
        if (!field.isEmpty()) {
            layout.setVisibility(View.VISIBLE);
            view.setText(field);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phoneNumberView:
                Uri number = Uri.parse("tel:" + phoneView.getText());
                Intent callIntent = new Intent(Intent.ACTION_CALL, number);
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ABView.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }else{
                    startActivity(callIntent);
                }
                break;
            case R.id.phoneNumberView2:
                Uri number2 = Uri.parse("tel:" + phoneView2.getText());
                Intent callIntent2 = new Intent(Intent.ACTION_CALL, number2);
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ABView.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }else{
                    startActivity(callIntent2);
                }
                break;
            case R.id.emailAddressView:
                Uri mail = Uri.parse("mailto:" + mailView.getText());
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO, mail);
                startActivity(Intent.createChooser(mailIntent, "Send Email"));
                break;
            case R.id.emailAddressView2:
                Uri mail2 = Uri.parse("mailto:" + mailView2.getText());
                Intent mailIntent2 = new Intent(Intent.ACTION_SENDTO, mail2);
                startActivity(Intent.createChooser(mailIntent2, "Send Email"));
                break;
            case R.id.addressView:
                Uri mapsUri = Uri.parse("geo:0,0?q=" + addressView.getText().toString());
                Intent mapsIntent = new Intent(Intent.ACTION_VIEW, mapsUri);
                mapsIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapsIntent);
                break;
        }
    }

    public void editDetails() {
        Bundle bundle = new Bundle();
        bundle.putInt("contactId",contactId);
        Intent intent = new Intent(this, ABAdd.class).putExtras(bundle);
        startActivity(intent);
    }

    public void deleteDetails() {

        int number = getContentResolver().delete(ABContract.CONTACTS_URI,"ABContract.ID = contactId", null);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public Dialog alertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this contact?");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteDetails();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) { }});

        return builder.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.address_menu_view_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.edit_contact) {
            this.editDetails();
            return true;
        }
        if (id == R.id.delete_contact) {
            this.alertDialog().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
