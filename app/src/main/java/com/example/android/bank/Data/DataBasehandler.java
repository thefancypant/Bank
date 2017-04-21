package com.example.android.bank.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;



public class DataBasehandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BankDataBase";
    private static final String TABLE_CONTACTS = "Customers";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_DOB= "dateofbirth";
//    private SQLiteDatabase db;
//    private String s;
    private ArrayList<UserData> arlist;


    public DataBasehandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
       // SQLiteDatabase db= new SQLiteDatabase(context);
    }


    //Creates a table in the DB
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Customers(id INTEGER PRIMARY KEY, name TEXT,amount INTEGER, dateofbirth TEXT);");
        //sqLiteDatabase.close();
        }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Customers;");
        //db.close();

        }
    //Adding data to the DB
    public void addContact(UserData ud) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,ud.getName());
        values.put(KEY_AMOUNT,ud.getAmount());
        values.put(KEY_DOB,ud.getDob());
        db.insert(TABLE_CONTACTS,null,values);

        Log.i("values entered",ud.getName()+" "+ud.getAmount()+" "+ud.getDob()+" ");

       db.close();

    }

    public ArrayList<UserData> getContact( )  {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor res = db.rawQuery("SELECT * FROM Customers;", null);

            arlist = new ArrayList<UserData>();
            UserData ud;
            int i=res.getCount();
            Log.i("number in cursor ",Integer.toString(i));

            if (res !=null && res.moveToFirst())
            {
                do
                {
                    String name = res.getString(1);
                    int amount = res.getInt(2);
                    String dob = res.getString(3);

                    ud = new UserData(name,amount,dob);
                    arlist.add(ud);

                    String total =name +" "+Integer.toString(amount)+" "+dob;
                    Log.i("Total",total);
                }while (res.moveToNext());

            }
            res.close();
            int j = arlist.size();
            Log.i("number in arlist 2 ",Integer.toString(j));

        }
        catch (Exception e)
            {
                Log.e("Error reading bd",e.getMessage() );

            }


        return  arlist;

    }



}

