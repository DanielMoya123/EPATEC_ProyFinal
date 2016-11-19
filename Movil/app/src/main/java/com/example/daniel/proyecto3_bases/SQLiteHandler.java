package com.example.daniel.proyecto3_bases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferllini13 on 14/11/2016.
 */

public class SQLiteHandler extends SQLiteOpenHelper {
    private static SQLiteHandler SQLiteHandler;
    private HttpConnection GET;
    //SQLiteDatabase db;


    public SQLiteHandler(Context ctx){
        super(ctx,"DB",null,1);
        GET = new HttpConnection();
        //db = getWritableDatabase();
        //onCreate(db);
    }


    public void onCreate(SQLiteDatabase db) {
        System.out.println("Entra al create");
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
        //String query = "CREATE TABLE CATEGORY (_id TEXT UNIQUE PRIMARY KEY, _description TEXT );";
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

    public void addRol(String id, String descrip){
        ContentValues values = new ContentValues();

        values.put("_id", id);
        values.put("_description",descrip);
        insertData("ROL", values);

    }

    public void addProvidersPro(String prov,String rol){
        ContentValues values = new ContentValues();

        values.put("_providerId", prov);
        values.put("_productId",rol);
        insertData("PROVIDER_PRODUCTS", values);
    }

    public void addUseRol(String usr,String rol){
        ContentValues values = new ContentValues();

        values.put("_user_id", usr);
        values.put("rol_id",rol);
        insertData("USER_ROL", values);
    }


    public void addOrderPro(String wish,String pro,int cant){
        ContentValues values = new ContentValues();
        values.put("_wishId", wish);
        values.put("_productId",pro);
        values.put("numberOfProducts",cant);
        insertData("WISH_PRODUCTS", values);

    }

    public void UpSyncronize() throws JSONException, InterruptedException {
        UpUsers();
        /*UpProducts();
        UpOrders();
        UpCategory();
        UpProviderPro();
        UpRol();
        UpUseRol();
        UpOrderPro();*/
    }



    public boolean UpUsers() throws JSONException, InterruptedException {

        String query = "listar/clientes";
        HttpConnection http=new HttpConnection();

        JSONArray jsonArray= HttpConnection.getAns(query);

        for(int i=0;i<jsonArray.length();i++){
            JSONObject item=jsonArray.getJSONObject(i);
            addUser(new Users(item.getString("_id"),item.getString("_name"), item.getString("_lastName1"), item.getString("_lastName2"), item.getString("_cellPhone"), item.getString("_identityNumber"),item.getString( "_username"), item.getString( "_password"),item.getString( "_birthDate"),item.getString( "_office"), item.getString( "_residenceAddress"),item.getString( "penalty")));
        }
            return true;
    }
/*
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
            addProvidersPro(item.getString("_providerId"),item.getString("_productId"));
        }
        return true;
    }

    public boolean UpRol() throws JSONException {
        String query="listar/ROL";
        String ans=GET.request(query);
        JSONArray jsonArray = new JSONArray(ans);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject item=jsonArray.getJSONObject(i);
            addRol(item.getString("_id"),item.getString("_description"));
        }
        return true;
    }

    public boolean UpUseRol() throws JSONException {
        String query="listar/USER_ROL";
        String ans=GET.request(query);
        JSONArray jsonArray = new JSONArray(ans);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject item=jsonArray.getJSONObject(i);
            addUseRol(item.getString("_user_id"),item.getString("rol_id"));
        }
        return true;
    }

    public boolean UpOrderPro() throws JSONException {
        String query="listar/WISH_PRODUCTS";
        String ans=GET.request(query);
        JSONArray jsonArray = new JSONArray(ans);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject item=jsonArray.getJSONObject(i);
            addOrderPro(item.getString("_wishId"),item.getString("_productId"),item.getInt("numberOfProducts"));
        }
        return true;
    }*/

    public List<Users> getUsers(){
        List<Users> usrl = new ArrayList<Users>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT * FROM GENERAL_USER";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {

            do {
                Users usr = new Users(cursor.getString(cursor.getColumnIndex("_id")),
                        cursor.getString(cursor.getColumnIndex("_name")),
                        cursor.getString(cursor.getColumnIndex("_lastName1")),
                        cursor.getString(cursor.getColumnIndex("_lastName2")),
                        cursor.getString(cursor.getColumnIndex("_cellPhone")),
                        cursor.getString(cursor.getColumnIndex("_identityNumber")),
                        cursor.getString(cursor.getColumnIndex("_username")),
                        cursor.getString(cursor.getColumnIndex("_password")),
                        cursor.getString(cursor.getColumnIndex("_birthDate")),
                        cursor.getString(cursor.getColumnIndex("_office")),
                        cursor.getString(cursor.getColumnIndex("_residenceAddress")),
                        cursor.getString(cursor.getColumnIndex("penalty")));

                usrl.add(usr);
            } while (cursor.moveToNext());
        }

        db.close();
        return usrl;
    }

    public List<Products> getProducts(){
        List<Products> prod = new ArrayList<Products>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT * FROM PRODUCT";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Products pro = new Products(cursor.getString(cursor.getColumnIndex("_id")),
                        false,
                        cursor.getString(cursor.getColumnIndex("_office")),
                        cursor.getString(cursor.getColumnIndex("_description")),
                        cursor.getString(cursor.getColumnIndex("_categoryId")),
                        cursor.getInt(cursor.getColumnIndex("_amount")),
                        cursor.getInt(cursor.getColumnIndex("price")));

                prod.add(pro);
            } while (cursor.moveToNext());
        }

        db.close();
        return prod;
    }


    public List<Category> getCategories() {
        List<Category> cate = new ArrayList<Category>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM CATEGORY";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Category cat = new Category(cursor.getString(cursor.getColumnIndex("_id")),
                        cursor.getString(cursor.getColumnIndex("_description")));

                cate.add(cat);
            } while (cursor.moveToNext());
        }
            db.close();
        System.out.println("G,yitjrhgrwhklhjgthrgsjhk."+cate.toString());
            return cate;

    }

    public void delUser(String userID){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "delete from USER_ROL\n" +
                "\t\twhere _user_id = "+ userID+"\n" +
                "\n" +
                "\t\tdelete from GENERAL_USER\n" +
                "\t\twhere " + userID +" = _id";
        db.rawQuery(query, null);
    }

    public void delCategory(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "update PRODUCT\n" +
                "set _categoryId = '0'\n" +
                "where _id in (Select _id from PRODUCT\n" +
                "\twhere _categoryId =" + id +")\n" +
                "\n" +
                "delete from CATEGORY\n" +
                "where _id ="+ id;
        db.rawQuery(query, null);
    }

    public void delProduct(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "delete from PROVIDER_PRODUCTS\n" +
                "\t\twhere _productId ="+ id+"\n" +
                "\n" +
                "\t\tdelete from WISH_PRODUCTS\n" +
                "\t\twhere _productId = "+id+"\n" +
                "\n" +
                "\t\tdelete from PRODUCT\n" +
                "\t\twhere _id =" +id;
        db.rawQuery(query, null);
    }
    public void delOrder(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "update PRODUCT\n" +
                "set _amount = _amount + (select WP.numberOfProducts from WISH_PRODUCTS as WP where WP._wishId ="+ id+")\n" +
                "where _id in (select WP._productId from WISH_PRODUCTS as WP where WP._wishId ="+ id+")\n" +
                "\n" +
                "\n" +
                "delete from WISH_PRODUCTS\n" +
                "where _wishId ="+ id+"\n" +
                "delete from WISH\n" +
                "where _id ="+ id;
        db.rawQuery(query, null);
    }

    public void delProvider(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "delete from PROVIDER_PRODUCTS\n" +
                "where _productId in (select _productId\n" +
                "\tfrom PROVIDER_PRODUCTS\n" +
                "\twhere _providerId ="+id+")\n" +
                "\n" +
                "delete from PRODUCT\n" +
                "where _id in (select _productId\n" +
                "\tfrom PROVIDER_PRODUCTS\n" +
                "\twhere _providerId ="+ id+")\n" +
                "\n" +
                "delete from USER_ROL\n" +
                "where _user_id ="+ id+"\n" +
                "\n" +
                "delete from GENERAL_USER\n" +
                "where _id ="+id;
        db.rawQuery(query, null);
    }



    public void syncronize(){

    }


    public void insertData (String table  ,ContentValues content) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("Content: " + content.toString());
        db.insert(table, null, content);
    }
}