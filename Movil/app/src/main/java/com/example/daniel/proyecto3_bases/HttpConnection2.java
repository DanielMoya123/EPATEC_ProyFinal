package com.example.daniel.proyecto3_bases;

/**
 * Created by ferllini13 on 18/11/2016.
 */

import android.util.Log;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpConnection2 {
    private static HttpConnection2 singleton;
    //TODO get this information from user
    public static String serviceIp = "172.19.13.120";
    public static String port = "8080";
    private final OkHttpClient client = new OkHttpClient();

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    /**
     * Construtor
     */
    private HttpConnection2(){}

    /**
     * Returns a sigleton instance of this class
     * @return singleton instance of this class
     */
    public static HttpConnection2 getConnection(){
        if(singleton != null){
            return singleton;
        }
        else{
            return singleton = new HttpConnection2();
        }
    }

    /**
     * Makes an GET request based on an url, returns the WebService Responce
     * @param url  where request is going to be made
     * @return String with the response
     */
    public String sendGet(String url){
        url = "http://172.19.13.120:8080/Construtec.asmx/ListarClientes";
        System.out.println(url);
        Log.i("http", url);
        String respuesta = "";
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            if(response.code() == 200){//200 es el código de ok
                respuesta =  response.body().string();
            } else{
                respuesta = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Response  "+ respuesta);
        Log.i("Response", respuesta);
        return respuesta;
    }
}
