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
        String query="CREATE TABLE GENERAL_USER\n" +
                "(\n" +
                "  _id int UNIQUE NOT NULL,\n" +
                "  _name character varying NOT NULL,\n" +
                "  _lastName1 character varying,\n" +
                "  _lastName2 character varying,\n" +
                "  _identityNumber character varying NOT NULL,\n" +
                "  _password character varying NOT NULL,\n" +
                "  _username character varying NOT NULL,\n" +
                "  _cellPhone character varying,\n" +
                "  _birthDate character varying,\n" +
                "  _penalty bit,\n" +
                "  _office character varying,\n" +
                "  _residenceAddress character varying,\n" +
                "  CONSTRAINT gupk PRIMARY KEY (_id),\n" +
                "  CONSTRAINT general_user_cell_phone_number_key UNIQUE (_cellPhone),\n" +
                "  CONSTRAINT general_user_identity_number_key UNIQUE (_identityNumber),\n" +
                "  CONSTRAINT general_user_username_key UNIQUE (_username)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE ROL\n" +
                "(\n" +
                "  _id int UNIQUE NOT NULL,\n" +
                "  _description character varying,\n" +
                "  CONSTRAINT rpk PRIMARY KEY (_id)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE USER_ROL\n" +
                "(\n" +
                "  _user_id int,\n" +
                "  rol_id int,\n" +
                "  CONSTRAINT user_rol_rol_id_fkey FOREIGN KEY (rol_id)\n" +
                "      REFERENCES rol (_id),\n" +
                "  CONSTRAINT user_rol_user_id_fkey FOREIGN KEY (_user_id)\n" +
                "      REFERENCES general_user (_id),\n" +
                "  CONSTRAINT user_rol_pkey PRIMARY KEY (_user_id,rol_id)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "CREATE TABLE CATEGORY(\n" +
                "\t_id int UNIQUE NOT NULL,\n" +
                "\t_description character varying NOT NULL,\n" +
                "\tCONSTRAINT Cpk PRIMARY KEY (_id),\n" +
                ")\n" +
                "\n" +
                "CREATE TABLE PRODUCT\n" +
                "(\n" +
                "  _id int UNIQUE NOT NULL,\n" +
                "  _description character varying NOT NULL,\n" +
                "  _office character varying,\n" +
                "  _nonTaxable bit,\n" +
                "  price int NOT NULL,\n" +
                "  _categoryId int NOT NULL,\n" +
                "  CONSTRAINT mpk PRIMARY KEY (_id),\n" +
                "  CONSTRAINT category_fk FOREIGN KEY (_categoryId)\n" +
                "      REFERENCES CATEGORY (_id)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "CREATE TABLE PROVIDER_PRODUCTS(\n" +
                "\t_providerId int NOT NULL,\n" +
                "\t_productId int NOT NULL,\n" +
                "\tCONSTRAINT providerFK FOREIGN KEY (_providerId)\n" +
                "\t  REFERENCES GENERAL_USER (_id),\n" +
                "    CONSTRAINT productFK FOREIGN KEY (_productId)\n" +
                "      REFERENCES PRODUCT (_id),\n" +
                "    CONSTRAINT provider_productsPK PRIMARY KEY (_providerId,_productId)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE WISH (\n" +
                "\t_id int UNIQUE NOT NULL,\n" +
                "\t_office character varying,\n" +
                "\t_clientId int,\n" +
                "\t_sellerId int,\n" +
                "\t_penalty bit,\n" +
                "\t_creationTime character varying,\n" +
                "\tCONSTRAINT clientFK FOREIGN KEY (_clientId)\n" +
                "\t  REFERENCES GENERAL_USER (_id),\n" +
                "    CONSTRAINT sellerFK FOREIGN KEY (_sellerId)\n" +
                "      REFERENCES GENERAL_USER (_id)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE WISH_PRODUCTS (\n" +
                "\t_wishId int NOT NULL,\n" +
                "\t_productId int NOT NULL,\n" +
                "\tnumberOfProducts int,\n" +
                "\tCONSTRAINT wishFK FOREIGN KEY (_wishId)\n" +
                "\t  REFERENCES WISH (_id),\n" +
                "    CONSTRAINT productWFK FOREIGN KEY (_productId)\n" +
                "      REFERENCES PRODUCT (_id),\n" +
                "\tCONSTRAINT wish_productPK PRIMARY KEY (_wishId,_productId)\n" +
                ");";
        db.execSQL(query);
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

    public void addUser() {
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
