package com.example.kurs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MyDbManager
{
    private final Context context;
    private final MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context)
    {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }
    public void openDb()
    {
        db = myDbHelper.getWritableDatabase();
    }
    public void insertToDb(String full_name, String car_model, String number, String vin, String status )
    {
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.FULL_NAME, full_name);
        cv.put(MyConstants.CAR_MODEL, car_model);
        cv.put(MyConstants.NUMBER, number);
        cv.put(MyConstants.VIN, vin);
        cv.put(MyConstants.STATUS, status);
         long res = db.insert(MyConstants.TABLE_NAME, null, cv);
         if (res == -1){
             Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
         }
         else {
             Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
         }
    }
    public Cursor readAllDAta(){
        String query = "SELECT * FROM "+MyConstants.TABLE_NAME;
        openDb();

        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        cursor.close();
        return cursor;

    }

}
