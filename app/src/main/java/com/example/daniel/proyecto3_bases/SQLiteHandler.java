package com.example.daniel.proyecto3_bases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by ferllini13 on 14/11/2016.
 */

public class SQLiteHandler extends SQLiteOpenHelper {
    private static SQLiteHandler SQLiteHandler;

    public SQLiteHandler(Context ctx){
        super(ctx,"DB",null,1);
    }
    public void onCreate(SQLiteDatabase db) {
        String query0="CREATE TABLE Usuario";
        db.execSQL(query0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public static SQLiteHandler getDB(Context context) {
        if (SQLiteHandler == null){
            SQLiteHandler = new SQLiteHandler(context);
        }
        return SQLiteHandler;
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();

        values.put("Nombre", "");

        insertData("tabla", values);
    }

    public void addProduct() {
        ContentValues values = new ContentValues();

        values.put("Nombre", "");

        insertData("tabla", values);
    }



    public void addOrder() {
        ContentValues values = new ContentValues();

        values.put("Nombre","");

        insertData("tabla", values);

    }

    public void addCategory(){
        ContentValues values = new ContentValues();

        values.put("Nombre","");

        insertData("tabla", values);
    }


    public void insertData (String table  ,ContentValues content) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(table, null, content);
    }
}
