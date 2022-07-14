package com.example.addisu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final ArrayList<Contact> contactList = new ArrayList<>();

    public DatabaseHandler(Context context){

        super(context, Constants.DATABASE_NAME, null,

                Constants.DATABASE_VERSION);
    }

    @Override

    public void onCreate(SQLiteDatabase db) { String Create_TABLE = "CREATE TABLE " +

            Constants.TABLE_NAME + "("

            + Constants.KEY_ID + " INTEGER PRIMARY KEY, " + Constants.FIRST_NAME

            + " TEXT, " + Constants.LAST_NAME + " TEXT, " + Constants.PHONE_NUMBER
            + " TEXT, " + Constants.EMAIL_ADDRESS + " TEXT);";
        db.execSQL(Create_TABLE);

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        onCreate(db);

    }

    public int getTotalItems(){

        int totalItems = 0;

        String query = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase dba = this.getReadableDatabase();

        Cursor cursor = dba.rawQuery(query, null);

        totalItems = cursor.getCount();

        dba.close();

        return	totalItems;
    }

    public void deleteContact(int id){
        SQLiteDatabase dba = this.getWritableDatabase();

        dba.delete(Constants.TABLE_NAME, Constants.KEY_ID + " = ?",

        new String[]{String.valueOf(id)}); dba.close();

    }

    public void addContact(Contact contact){ SQLiteDatabase dba = this.getWritableDatabase(); ContentValues values = new ContentValues(); values.put(Constants.FIRST_NAME, contact.getfName()); values.put(Constants.LAST_NAME, contact.getlName()); values.put(Constants.PHONE_NUMBER, contact.getPhone()); values.put(Constants.EMAIL_ADDRESS, contact.getEmail());

        dba.insert(Constants.TABLE_NAME, null, values);

        dba.close();
    }

    public ArrayList<Contact> getContacts(){ contactList.clear();

        SQLiteDatabase dba = this.getReadableDatabase(); Cursor cursor = dba.query(Constants.TABLE_NAME, new String[]{Constants.KEY_ID,
                        Constants.FIRST_NAME,

                        Constants.LAST_NAME, Constants.PHONE_NUMBER, Constants.EMAIL_ADDRESS},
                null, null, null, null, Constants.KEY_ID + " DESC ");

        if(cursor.moveToFirst()){
            do {
                Contact contact = new Contact();

                contact.setfName(cursor.getString(cursor.getColumnIndex(Constants.FIRST_NAME)));

                contact.setlName(cursor.getString(cursor.getColumnIndex(Constants.LAST_NAME)));

                contact.setPhone(cursor.getString(cursor.getColumnIndex(Constants.PHONE_NUMBER)));

                contact.setEmail(cursor.getString(cursor.getColumnIndex(Constants.EMAIL_ADDRESS)));

                contact.setContactId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));

                contactList.add(contact);
            }while(cursor.moveToNext());
        }

        return contactList;
    }

}
