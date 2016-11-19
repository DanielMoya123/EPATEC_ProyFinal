package com.example.daniel.proyecto3_bases;

/**
 * Created by ferllini13 on 19/11/2016.
 */

import org.json.JSONArray;
import org.json.JSONException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpConnection2 {
    private static HttpConnection2 http;
    static String urls = "http://192.168.43.210:8080/Construtec.asmx/Parsear?frase=";
    private final OkHttpClient client = new OkHttpClient();

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static HttpConnection2 getHttp() {
        if (http != null) {
            return http;
        } else {
            return http = new HttpConnection2();
        }
    }

    public JSONArray getAns(String url) throws JSONException {
        JSONArray jsonArray = null;
        url = urls + url;
        String serverans = "";
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                serverans = response.body().string();

                System.out.println("respuesta del servidor"+serverans);

            } else {
                serverans = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String response2 = serverans.substring(76, serverans.length() - 9);
            System.out.println("ESTA ES BICHOS!!!  " + response2);
            jsonArray = new JSONArray(response2);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return jsonArray;
    }
}
