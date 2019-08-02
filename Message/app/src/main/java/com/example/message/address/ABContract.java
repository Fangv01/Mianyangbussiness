package com.example.message.address;

import android.net.Uri;

public class ABContract {

    public static final String AUTHORITY = "com.example.ab.ABMyProvider";

    public static final Uri CONTACTS_URI = Uri.parse("content://"+AUTHORITY+"/table1");

    public static final String FIRSTNAME = "firstName";

    public static final String ID = "_id";

    public static final String LASTNAME = "lastName";

    public static final String PHONE = "phone";

    public static final String PHONE_TWO = "phoneTwo";

    public static final String EMAIL = "email";

    public static final String EMAIL_TWO = "emailTwo";

    public static final String ADDRESS = "address";

    public static final String IMAGE = "image";
}
