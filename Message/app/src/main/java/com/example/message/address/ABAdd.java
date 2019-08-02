package com.example.message.address;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.message.R;
import com.example.message.SecondActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ABAdd extends Activity{
    private byte[] photo;
    private int contactId;
    private boolean newContact;
    private String contactFirstName, contactLastName, contactPhone, contactPhone2, contactEmail, contactEmail2, contactAddress;

    private Button addField;
    private ImageView addImage;
    private LinearLayout phone2Layout, mail2Layout, addressLayout;
    private EditText addFirstName, addLastName, addPhone, addMail, addMail2, addPhone2, addAddress;

    private static final int IMAGE_SELECTION = 1;
    private static final int IMAGE_CAPTURE_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addressbook_add_contact);

        addImage = (ImageView) findViewById(R.id.addImage);
        addImage.setImageResource(R.drawable.contact_high);
        addFirstName = (EditText) findViewById(R.id.addFirstName);
        addLastName = (EditText) findViewById(R.id.addLastName);
        addPhone = (EditText) findViewById(R.id.addPhone);
        addPhone2 = (EditText) findViewById(R.id.addPhone2);
        addMail = (EditText) findViewById(R.id.addMail);
        addMail2 = (EditText) findViewById(R.id.addMail2);
        addAddress = (EditText) findViewById(R.id.addAddress);
        phone2Layout = (LinearLayout) findViewById(R.id.phone2Layout);
        mail2Layout = (LinearLayout) findViewById(R.id.mail2Layout);
        addressLayout = (LinearLayout) findViewById(R.id.addressLayout);
        addField = (Button) findViewById(R.id.addField);

        Button back = (Button) findViewById(R.id.delete_button);
        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            contactId = bundle.getInt("contactId");
            fillContactData();
            setTitle("Edit contact");
            newContact = false;
        } else {
            setTitle("Add new contact");
            newContact = true;
        }
    }

    public void fillContactData(){
        String columns[] = new String[] {
                ABContract.FIRSTNAME,
                ABContract.LASTNAME,
                ABContract.PHONE,
                ABContract.PHONE_TWO,
                ABContract.EMAIL,
                ABContract.EMAIL_TWO,
                ABContract.ADDRESS,
                ABContract.IMAGE
        };

        Uri contactUri = ABContract.CONTACTS_URI;
        Cursor cursor = getContentResolver().query(contactUri, columns, ABContract.ID + "=" + contactId, null, null, null);
        while(cursor.moveToNext()) {
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
        Bitmap image= BitmapFactory.decodeStream(imageStream);
        addFirstName.setText(contactFirstName);
        addLastName.setText(contactLastName);
        addPhone.setText(contactPhone);
        addPhone2.setText(contactPhone2);
        addMail.setText(contactEmail);
        addMail2.setText(contactEmail2);
        addAddress.setText(contactAddress);
        addImage.setImageBitmap(image);
        try {
            imageStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        showView(contactPhone2, addPhone2, phone2Layout);
        showView(contactEmail2, addMail2, mail2Layout);
        showView(contactAddress, addAddress, addressLayout);
    }

    public void showView(String field, EditText view, LinearLayout layout){
        if(!field.isEmpty()) {
            layout.setVisibility(View.VISIBLE);
            view.setText(field);
        }
    }

    public void openPopupMenuAddField(View view) {
        PopupMenu popup = new PopupMenu(ABAdd.this, addField);
        popup.getMenuInflater().inflate(R.menu.address_popup_menu_add_field, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                // adds a second phone field
                if (id == R.id.addPhone2Field) {
                    Log.d("addressApp", "email field added");
                    addField("phone");
                    return true;
                }
                // adds a second email field
                if (id == R.id.addEmail2Field) {
                    Log.d("addressApp", "email field added");
                    addField("email");
                    return true;
                }
                if (id == R.id.addAddressField) {
                    Log.d("addressApp", "address field added");
                    addField("address");
                    return true;
                }
                return true;
            }
        });
        popup.show();
    }

    public void addField(String filed){
        switch(filed){
            case "phone":
                phone2Layout.setVisibility(View.VISIBLE);
                break;
            case "email":
                mail2Layout.setVisibility(View.VISIBLE);
                break;
            case "address":
                addressLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void saveToDb(View view) {

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        Bitmap contactImage = ((BitmapDrawable)addImage.getDrawable()).getBitmap();
        contactImage.compress(Bitmap.CompressFormat.PNG, 70, bao);
        photo = bao.toByteArray();
        try {
            bao.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String firstName = addFirstName.getText().toString();
        String lastName = addLastName.getText().toString();
        String phone = addPhone.getText().toString();
        String phone2 = addPhone2.getText().toString();
        String mail = addMail.getText().toString();
        String mail2 = addMail2.getText().toString();
        String address = addAddress.getText().toString();

        if (!ABContract.FIRSTNAME.isEmpty()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ABContract.FIRSTNAME, firstName);
            contentValues.put(ABContract.LASTNAME, lastName);
            contentValues.put(ABContract.PHONE, phone);
            contentValues.put(ABContract.PHONE_TWO, phone2);
            contentValues.put(ABContract.EMAIL, mail);
            contentValues.put(ABContract.EMAIL_TWO, mail2);
            contentValues.put(ABContract.ADDRESS, address);
            contentValues.put(ABContract.IMAGE, photo);

            if (newContact) {
                Uri uri = getContentResolver().insert(ABContract.CONTACTS_URI, contentValues);
                Intent intent = new Intent(this, SecondActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                int number = getContentResolver().update(ABContract.CONTACTS_URI, contentValues,ABContract.ID+"="+contactId, null);
                Bundle bundle = new Bundle();
                bundle.putInt("contactId", contactId);
                Intent intent = new Intent(ABAdd.this, ABView.class);
                intent.putExtras(bundle);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
        else {
            Toast toast = Toast.makeText(this, "First Name cannot be empty!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public static Bitmap DrawableToBitmap(Drawable drawable) {

        // 获取 drawable 长宽
        int width = drawable.getIntrinsicWidth();
        int heigh = drawable.getIntrinsicHeight();

        drawable.setBounds(0, 0, width, heigh);

        // 获取drawable的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 创建bitmap
        Bitmap bitmap = Bitmap.createBitmap(width, heigh, config);
        // 创建bitmap画布
        Canvas canvas = new Canvas(bitmap);
        // 将drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    public void openPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(ABAdd.this, addImage);
        popup.getMenuInflater().inflate(R.menu.address_popup_menu_image, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                // opens Camera for option 1
                if (id == R.id.takePhoto) {
                    openCamera();
                    return true;
                }
                // open default galleryApp for option 2
                if (id == R.id.choosePhoto) {
                    chooseFile();
                    return true;
                }
                return true;
            }
        });
        popup.show();
    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, IMAGE_CAPTURE_REQUEST_CODE);
    }

    public void chooseFile() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE_SELECTION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == IMAGE_CAPTURE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                addImage.setImageBitmap(photo);
            } else if (resultCode == RESULT_CANCELED) {

            } else {

                Toast toast = Toast.makeText(this, "Image capture failed!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if (requestCode == IMAGE_SELECTION) {
            if (resultCode == RESULT_OK) {

                Uri uri = data.getData();
                Bitmap image = null;
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addImage.setImageBitmap(image);
            }
            else if (resultCode == RESULT_CANCELED) {
            } else {
                Toast toast = Toast.makeText(this, "Image selection failed!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

}
