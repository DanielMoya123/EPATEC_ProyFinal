package com.example.daniel.proyecto3_bases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.json.*;


/**
 * Created by ferllini13 on 14/11/2016.
 */

public class SQLiteHandler extends SQLiteOpenHelper {
    private static SQLiteHandler SQLiteHandler;
    private HttpConnection GET;

    public SQLiteHandler(Context ctx){
        super(ctx,"DB",null,1);
    }
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE GENERAL_USER\n" +
                "(\n" +
                "  _id TEXT UNIQUE NOT NULL,\n" +
                "  _name TEXT NOT NULL,\n" +
                "  _lastName1 TEXT ,\n" +
                "  _lastName2 TEXT ,\n" +
                "  _identityNumber TEXT NOT NULL,\n" +
                "  _password TEXT NOT NULL,\n" +
                "  _username TEXT NOT NULL,\n" +
                "  _cellPhone TEXT,\n" +
                "  _birthDate TEXT,\n" +
                "  _penalty numeric ,\n" +
                "  _office TEXT ,\n" +
                "  _residenceAddress TEXT,\n" +
                "  CONSTRAINT gupk PRIMARY KEY (_id),\n" +
                "  CONSTRAINT general_user_cell_phone_number_key UNIQUE (_cellPhone),\n" +
                "  CONSTRAINT general_user_identity_number_key UNIQUE (_identityNumber),\n" +
                "  CONSTRAINT general_user_username_key UNIQUE (_username)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE ROL\n" +
                "(\n" +
                "  _id TEXT UNIQUE NOT NULL,\n" +
                "  _description TEXT ,\n" +
                "  CONSTRAINT rpk PRIMARY KEY (_id)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE USER_ROL\n" +
                "(\n" +
                "  _user_id TEXT,\n" +
                "  rol_id TEXT,\n" +
                "  CONSTRAINT user_rol_rol_id_fkey FOREIGN KEY (rol_id)\n" +
                "      REFERENCES rol (_id),\n" +
                "  CONSTRAINT user_rol_user_id_fkey FOREIGN KEY (_user_id)\n" +
                "      REFERENCES general_user (_id),\n" +
                "  CONSTRAINT user_rol_pkey PRIMARY KEY (_user_id,rol_id)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "CREATE TABLE CATEGORY(\n" +
                "\t_id TEXT UNIQUE NOT NULL,\n" +
                "\t_description TEXT NOT NULL,\n" +
                "\tCONSTRAINT Cpk PRIMARY KEY (_id),\n" +
                ")\n" +
                "\n" +
                "CREATE TABLE PRODUCT\n" +
                "(\n" +
                "  _id TEXT UNIQUE NOT NULL,\n" +
                "  _description TEXT NOT NULL,\n" +
                "  _office TEXT,\n" +
                "  _nonTaxable bit,\n" +
                "  price INTEGER NOT NULL,\n" +
                "  _categoryId INTEGER NOT NULL,\n" +
                "  CONSTRAINT mpk PRIMARY KEY (_id),\n" +
                "  CONSTRAINT category_fk FOREIGN KEY (_categoryId)\n" +
                "      REFERENCES CATEGORY (_id)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "CREATE TABLE PROVIDER_PRODUCTS(\n" +
                "\t_providerId TEXT NOT NULL,\n" +
                "\t_productId TEXT NOT NULL,\n" +
                "\tCONSTRAINT providerFK FOREIGN KEY (_providerId)\n" +
                "\t  REFERENCES GENERAL_USER (_id),\n" +
                "    CONSTRAINT productFK FOREIGN KEY (_productId)\n" +
                "      REFERENCES PRODUCT (_id),\n" +
                "    CONSTRAINT provider_productsPK PRIMARY KEY (_providerId,_productId)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE WISH (\n" +
                "\t_id TEXT UNIQUE NOT NULL,\n" +
                "\t_office TEXT,\n" +
                "\t_clientId TEXT,\n" +
                "\t_sellerId TEXT,\n" +
                "\t_penalty NUMERIC,\n" +
                "\t_creationTime TEXT,\n" +
                "\tCONSTRAINT clientFK FOREIGN KEY (_clientId)\n" +
                "\t  REFERENCES GENERAL_USER (_id),\n" +
                "    CONSTRAINT sellerFK FOREIGN KEY (_sellerId)\n" +
                "      REFERENCES GENERAL_USER (_id)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE WISH_PRODUCTS (\n" +
                "\t_wishId TEXT NOT NULL,\n" +
                "\t_productId TEXT NOT NULL,\n" +
                "\tnumberOfProducts INTEGER,\n" +
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

    public void addUser(Users urs) {
        ContentValues values = new ContentValues();

        values.put("_id", urs._id);
        values.put("_name",urs._name );
        values.put("_lastName1", urs._lastName1);
        values.put("_lastName2",urs._lastName2 );
        values.put("_identityNumber", urs._identityNumber);
        values.put("_password", urs._password);
        values.put("_username", urs._username);
        values.put("_cellPhone", urs._cellPhone);
        values.put("_birthDate", urs._birthDate);
        values.put("penalty", urs.penalty);
        values.put("_office", urs._office);
        values.put("_residenceAddress", urs._residenceAddress);

        insertData(urs.table, values);
    }

    public void addProduct(Products pro) {
        ContentValues values = new ContentValues();

        values.put("_id",pro._id);
        values.put("_description",pro._description);
        values.put("_office",pro._office);
        values.put("_nonTaxable",pro._nontaxable);
        values.put("price",pro.price);
        values.put("_categoryId",pro._categoryId);
        values.put("_amount",pro._amount);

        insertData(pro.table, values);
    }

    public void addOrder(Orders ord) {
        ContentValues values = new ContentValues();
        values.put("_id",ord._id);
        values.put("_clientId",ord._clientId);
        values.put("_sellerId",ord._sellerId);
        values.put("penalty",ord.penalty);
        values.put("_creationTime",ord._creationTime);

        insertData("tabla", values);

    }

    public void addCategory(Category cate){
        ContentValues values = new ContentValues();

        values.put("_id", cate._id);
        values.put("_description",cate._description);
        insertData(cate.table, values);
    }

    public void UpSyncronize() throws JSONException {
        UpUsers();
        UpProducts();
        UpOrders();
        UpCategory();
        UpProviderPro();
        UpRol();
        UpUseRol();
        UpOrderPro();
    }



    public boolean UpUsers() throws JSONException {

        String query="listar/clientes";
        String ans=GET.request(query);
        JSONArray jsonArray = new JSONArray(ans);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject item=jsonArray.getJSONObject(i);
            addUser(new Users(item.getString("_id"),item.getString("_name"), item.getString("_lastName1"), item.getString("_lastName2"), item.getString("_cellPhone"), item.getString("_identityNumber"),item.getString( "_username"), item.getString( "_password"),item.getString( "_birthDate"),item.getString( "_office"), item.getString( "_residenceAddress"),item.getString( "penalty")));
        }
            return true;
    }

    public boolean UpProducts() throws JSONException {
        String query="listar/productos";
        String ans=GET.request(query);
        JSONArray jsonArray = new JSONArray(ans);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject item=jsonArray.getJSONObject(i);
            addProduct(new Products(item.getString("_id"),item.getBoolean("_nontaxable"), item.getString("_office"), item.getString("_description"), item.getString("_categoryId"), item.getInt("_amount"),item.getInt( "price")));
        }
        return true;
    }

    public boolean UpOrders() throws JSONException {
        String query="listar/pedidos";
        String ans=GET.request(query);
        JSONArray jsonArray = new JSONArray(ans);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject item=jsonArray.getJSONObject(i);
            addOrder(new Orders(item.getString("_id"),item.getString("_office"),item.getString("_clientId"),item.getString("_sellerId"),item.getBoolean("penalty"),item.getString("_creationTime")));
        }
        return true;

    }



    public boolean UpCategory() throws JSONException {
        String query="listar/categorias";
        String ans=GET.request(query);
        JSONArray jsonArray = new JSONArray(ans);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject item=jsonArray.getJSONObject(i);
            addCategory(new Category(item.getString("_id"),item.getString("_description")));
        }
        return true;

    }

    public boolean UpProviderPro() throws JSONException {
        String query="listar/PROVIDER_PRODUCTS";
        String ans=GET.request(query);
        JSONArray jsonArray = new JSONArray(ans);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject item=jsonArray.getJSONObject(i);
            addCategory(new Category(item.getString("_id"),item.getString("_description")));
        }
        return true;
    }

    public boolean UpRol() throws JSONException {
        String query="listar/ROL";
        String ans=GET.request(query);
        JSONArray jsonArray = new JSONArray(ans);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject item=jsonArray.getJSONObject(i);
            addCategory(new Category(item.getString("_id"),item.getString("_description")));
        }
        return true;
    }

    public boolean UpUseRol() throws JSONException {
        String query="listar/USER_ROL";
        String ans=GET.request(query);
        JSONArray jsonArray = new JSONArray(ans);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject item=jsonArray.getJSONObject(i);
            addCategory(new Category(item.getString("_id"),item.getString("_description")));
        }
        return true;
    }

    public boolean UpOrderPro() throws JSONException {
        String query="listar/WISH_PRODUCTS";
        String ans=GET.request(query);
        JSONArray jsonArray = new JSONArray(ans);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject item=jsonArray.getJSONObject(i);
            addCategory(new Category(item.getString("_id"),item.getString("_description")));
        }
        return true;
    }



    public void insertData (String table  ,ContentValues content) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(table, null, content);
    }
}
