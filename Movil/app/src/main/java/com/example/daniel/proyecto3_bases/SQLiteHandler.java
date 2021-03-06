package com.example.daniel.proyecto3_bases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import org.json.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ferllini13 on 14/11/2016.
 */

public class SQLiteHandler extends SQLiteOpenHelper {
    HttpConnection2 http = HttpConnection2.getHttp();
    List<Pair<String,Users>> UpUser = new ArrayList<Pair<String,Users>>();
    List<Pair<String,Category>> UpCate = new ArrayList<Pair<String,Category>>();
    List<Pair<String,Products>> UpProduct = new ArrayList<Pair<String,Products>>();
    List<Pair<String,Orders>> UpOrder = new ArrayList<Pair<String,Orders>>();

    private static SQLiteHandler SQLiteHandler;
    private HttpConnection GET;
    SQLiteDatabase db;


    public SQLiteHandler(Context ctx){
        super(ctx,"DB",null,1);
        GET = new HttpConnection();
        //db = getWritableDatabase();
        //onCreate(db);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Entra al create");
        System.out.println("General User  " + GeneralUserString.CREATE_GENERAL_USER);
        //db.execSQL(GeneralUserString.CREATE_GENERAL_USER);
        System.out.println("Rol  " + RolString.CREATE_ROL);
        db.execSQL(RolString.CREATE_ROL);
        System.out.println("User Rol  " + UserRolString.CREATE_USER_ROL);
        db.execSQL(UserRolString.CREATE_USER_ROL);
        System.out.println("Category  " + CategoryString.CREATE_CATEGORY);
        db.execSQL(CategoryString.CREATE_CATEGORY);
        System.out.println("Product  " + ProductString.CREATE_PRODUCT);
        db.execSQL(ProductString.CREATE_PRODUCT);
        System.out.println("Provider Product  " + ProviderProductString.CREATE_USER_ROL);
        db.execSQL(ProviderProductString.CREATE_USER_ROL);
        System.out.println("Wish   " + WishString.CREATE_WISH);
        db.execSQL(WishString.CREATE_WISH);
        System.out.println("Wish Product  " + WishProductString.CREATE_USER_ROL);
        db.execSQL(WishProductString.CREATE_USER_ROL);
    }

    public void createDB(){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            System.out.println("General User  " + GeneralUserString.CREATE_GENERAL_USER);
            db.execSQL(GeneralUserString.CREATE_GENERAL_USER);
            System.out.println("Rol  " + RolString.CREATE_ROL);
            db.execSQL(RolString.CREATE_ROL);
            System.out.println("User Rol  " + UserRolString.CREATE_USER_ROL);
            db.execSQL(UserRolString.CREATE_USER_ROL);
            System.out.println("Category  " + CategoryString.CREATE_CATEGORY);
            db.execSQL(CategoryString.CREATE_CATEGORY);
            System.out.println("Product  " + ProductString.CREATE_PRODUCT);
            db.execSQL(ProductString.CREATE_PRODUCT);
            System.out.println("Provider Product  " + ProviderProductString.CREATE_USER_ROL);
            db.execSQL(ProviderProductString.CREATE_USER_ROL);
            System.out.println("Wish   " + WishString.CREATE_WISH);
            db.execSQL(WishString.CREATE_WISH);
            System.out.println("Wish Product  " + WishProductString.CREATE_USER_ROL);
            db.execSQL(WishProductString.CREATE_USER_ROL);

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }


    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public static SQLiteHandler getDB(Context context) {

        if (SQLiteHandler == null){
            SQLiteHandler = new SQLiteHandler(context);
        }
        return SQLiteHandler;

    }

    public void addUser(Users urs,boolean flag) {

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



        if (flag){
            addUseRol(urs._id,urs.rol);
            Pair<String, Users> p=new Pair("add",urs);

        UpUser.add(p);}

    }

    public void addProduct(Products pro,boolean flag) {
        ContentValues values = new ContentValues();

        values.put("_id",pro._id);
        values.put("_description",pro._description);
        values.put("_office",pro._office);
        values.put("_nonTaxable",pro._nonTaxable);
        values.put("price",pro.price);
        values.put("_categoryId",pro._categoryId);
        values.put("_amount",pro._amount);

        insertData(pro.table, values);
        if (flag){
        Pair<String, Products> p=new Pair("add",pro);

        UpProduct.add(p);}

    }

    public void addOrder(Orders ord,boolean flag) {
        ContentValues values = new ContentValues();
        values.put("_id",ord._id);
        values.put("_clientId",ord._clientId);
        values.put("_sellerId",ord._sellerId);
        values.put("penalty",ord.penalty);
        values.put("_office",ord._office);
        values.put("_creationTime",ord._creationTime);

        insertData(ord.table, values);
        for (int i=0; i<ord.pro.size();i++){
            addOrderPro(ord._id,ord.pro.get(i).first,(int)ord.pro.get(i).second);

        }
        if (flag){
        Pair<String, Orders> p=new Pair("add",ord);

        UpOrder.add(p);}
    }
    public void Drop(){

        String query0="drop table WISH_PRODUCTS";
        String query1="drop table WISH";
        String query2="drop table PROVIDER_PRODUCTS";
        String query3="drop table PRODUCT";
        String query4="drop table CATEGORY";
        String query5="drop table USER_ROL";
        String query6="drop table GENERAL_USER ";
        String query7="drop table ROL";


        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query0);
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
        db.execSQL(query7);

    }

    public void addCategory(Category cate,boolean flag){
        ContentValues values = new ContentValues();

        values.put("_id", cate._id);
        values.put("_description",cate._description);
        insertData(cate.table, values);
        if (flag){
        Pair<String, Category> p=new Pair("add",cate);

        UpCate.add(p);}
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
        UpProducts();
        UpOrders();
        UpCategory();
        UpProviderPro();
        UpRol();
        UpUseRol();
        UpOrderPro();
    }


    public void ReSyncronize(){
        sendUsers();
        sendProducts();
        sendCategoties();
        sendOrders();

    }
    private boolean ifConect(){
        boolean status = false;
        try {
            URL u = new URL("https://www.google.com/");
            HttpsURLConnection con = (HttpsURLConnection) u.openConnection();
            con.connect();
            status = true;
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return status;
    }

    public void sendUsers(){
        for (int x=0;x<2;x++) {
            if (UpUser.size() != 0) {
                SQLiteDatabase db = this.getReadableDatabase();
                for (int i = 0; i < UpUser.size(); i++) {
                    String query;
                    if (UpCate.get(i).first == "del"){
                        query="eliminar/cliente/"+UpCate.get(i).second._id;
                    }else
                    query = "crear/cliente/"+UpUser.get(i).second._id+"/"+UpUser.get(i).second._name+"/"+UpUser.get(i).second._lastName1+"/"+UpUser.get(i).second._lastName2+"/"+UpUser.get(i).second._cellPhone+"/"+UpUser.get(i).second._identityNumber+"/"+UpUser.get(i).second._residenceAddress+"/"+UpUser.get(i).second._birthDate+"/"+UpUser.get(i).second.rol+"/"+UpUser.get(i).second._password+"/"+UpUser.get(i).second._username;

                    try {
                        JSONArray jsonArray = HttpConnection.getAns(query);
                    } catch (Exception e) {
                        UpUser.remove(UpUser.get(i));
                    }
                }
            }
        }
    }

    public void sendProducts(){
        for (int x=0;x<2;x++) {
            if (UpProduct.size() != 0 && ifConect()) {
                SQLiteDatabase db = this.getReadableDatabase();
                for (int i = 0; i < UpProduct.size(); i++) {
                    String query;
                    if (UpProduct.get(i).first == "del"){
                        query="eliminar/producto/"+UpProduct.get(i).second._id;
                    }else
                        query = "crear/producto/"+UpProduct.get(i).second._categoryId+"/"+UpProduct.get(i).second._providerId+"/"+UpProduct.get(i).second._id+"/"+UpProduct.get(i).second._providerId+"/"+UpProduct.get(i).second._amount+"/"+UpProduct.get(i).second._office+"/"+UpProduct.get(i).second._description;
                    try {
                        JSONArray jsonArray = HttpConnection.getAns(query);
                    } catch (Exception e) {
                        UpProduct.remove(UpProduct.get(i));
                    }
                }
            }
        }
    }

    public void sendCategoties(){
        for (int x=0;x<2;x++) {
            if (UpCate.size() != 0 && ifConect()) {
                SQLiteDatabase db = this.getReadableDatabase();
                for (int i = 0; i < UpCate.size(); i++) {
                    String query;
                    if (UpCate.get(i).first== "del"){
                        query="eliminar/categoria/"+UpCate.get(i).second._id;
                    }else
                    query = "crear/categoria/"+UpCate.get(i).second._id+"/"+UpCate.get(i).second._description;

                    try {
                        JSONArray jsonArray = HttpConnection.getAns(query);
                    } catch (Exception e) {
                        UpUser.remove(UpUser.get(i));

                    }
                }
            }
        }
    }

    public void sendOrders() {
        for (int x = 0; x < 2; x++) {
            if (UpOrder.size() != 0 && ifConect()) {
                SQLiteDatabase db = this.getReadableDatabase();
                for (int i = 0; i < UpOrder.size(); i++) {
                    String query;
                    if (UpOrder.get(i).first == "del") {
                        query = "eliminar/pedido/" + UpOrder.get(i).second._id;
                    } else {
                        query = "crear/pedido/" + UpOrder.get(i).second._clientId + "/" + UpOrder.get(i).second._id + "/" + UpOrder.get(i).second._office + "/" + UpOrder.get(i).second._creationTime + "/" + UpOrder.get(i).second.penalty;
                        String query2;
                        for (int c = 0; c < UpOrder.get(i).second.pro.size(); c++) {
                            query2 = "actualizarproductopedido/" + UpOrder.get(i).second.pro.get(c).first + "/" + UpOrder.get(i).second._id + "/" + UpOrder.get(i).second.pro.get(c).second;
                            JSONArray jsonArray2 = HttpConnection.getAns(query2);
                        }

                        try {
                            JSONArray jsonArray = HttpConnection.getAns(query);

                        } catch (Exception e) {
                            UpOrder.remove(UpOrder.get(i));
                        }
                    }
                }
            }
        }
    }

    public void DownSyncronize(){
        ReSyncronize();
    }




    public boolean UpUsers() throws JSONException, InterruptedException {

        String query = "listar/clientes";
        //JSONArray jsonArray= HttpConnection.getAns(query);

        JSONArray jsonArray=http.getAns(query);

        if (jsonArray!=null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                Users usr=new Users(item.getString("_id"), item.getString("_name"), item.getString("_lastName1"), item.getString("_lastName2"), item.getString("_cellPhone"), item.getString("_identityNumber"), item.getString("_username"), item.getString("_password"), item.getString("_birthDate"), item.getString("_office"), item.getString("_residenceAddress"), item.getString("penalty"));
                addUser(usr,false);
            }

        }else {
            System.out.println("esta vara salio false");
            return false;
        }
        return true;
        }


    public boolean UpProducts() throws JSONException {
        String query="listar/productos";
        JSONArray jsonArray= http.getAns(query);

        if (jsonArray!=null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                addProduct(new Products(item.getString("_id"), item.getBoolean("_nonTaxable"), item.getString("_office"), item.getString("_description"), item.getString("_categoryId"), item.getInt("_amount"), item.getInt("price")),false);
            }
            return true;

        }else return false;
    }

    public boolean UpOrders() throws JSONException {
        String query="listar/pedidos";
        JSONArray jsonArray= http.getAns(query);

        if (jsonArray!=null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                addOrder(new Orders(item.getString("_id"), item.getString("_office"), item.getString("_clientId"), item.getString("_sellerId"), item.getBoolean("penalty"), item.getString("_creationTime")),false);
            }
            return true;
        }else return false;

    }

    public boolean UpCategory() throws JSONException {
        String query="listar/categorias";
        JSONArray jsonArray= http.getAns(query);

        if (jsonArray!=null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                addCategory(new Category(item.getString("_id"), item.getString("_description")),false);
            }
            return true;
        }else return false;

    }

    public boolean UpProviderPro() throws JSONException {
        String query="listar/PROVIDER_PRODUCTS";
        JSONArray jsonArray= http.getAns(query);

        if (jsonArray!=null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                addProvidersPro(item.getString("_providerId"), item.getString("_productId"));
            }
            return true;
        }else return false;
    }

    public boolean UpRol() throws JSONException {
        String query="listar/ROL";

        JSONArray jsonArray= http.getAns(query);
        if (jsonArray!=null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                addRol(item.getString("_id"), item.getString("_description"));
            }
            return true;
        }else return false;
    }

    public boolean UpUseRol() throws JSONException {
        String query="listar/USER_ROL";
        JSONArray jsonArray= http.getAns(query);;
        if (jsonArray!=null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                addUseRol(item.getString("_user_id"), item.getString("rol_id"));
            }
            return true;
        }else return false;
    }

    public boolean UpOrderPro() throws JSONException {
        String query="listar/WISH_PRODUCTS";
        JSONArray jsonArray= http.getAns(query);
        if (jsonArray!=null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                addOrderPro(item.getString("_wishId"), item.getString("_productId"), item.getInt("numberOfProducts"));
            }
            return true;
        }else return false;

    }

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


    public void insertData (String table  ,ContentValues content) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("Content: " + content.toString());
        try{
        db.insert(table, null, content);
    }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}