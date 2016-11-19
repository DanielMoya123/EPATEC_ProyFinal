package com.example.daniel.proyecto3_bases;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by ferllini13 on 16/11/2016.
 */

public class HttpConnection{
     static String urls ="http://172.19.13.120:8080/Construtec.asmx/Parsear?frase=";

    public static JSONArray getAns(String msj){
        String realOutput="";
        HttpURLConnection con=null;
        BufferedReader reader=null;

        try {

            URL url = new URL(urls+msj);
           con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.connect();
            con.setConnectTimeout(10000);



            int responseCode = con.getResponseCode();
            String responseMessage = con.getResponseMessage();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            //InputStream input = con.getErrorStream();


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response= new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            //print result
            System.out.println(response.toString());
            String response2 = response.substring(74, response.length()-9);
            System.out.println("ESTA ES BICHOS!!!  " + response2);
            JSONArray jsonArray = new JSONArray(response2);
            return jsonArray;

        }catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}