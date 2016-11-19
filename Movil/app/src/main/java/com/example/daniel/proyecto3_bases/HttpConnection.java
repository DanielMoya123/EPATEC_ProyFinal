package com.example.daniel.proyecto3_bases;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by ferllini13 on 16/11/2016.
 */

public class HttpConnection extends AsyncTask<String, String, String> {
    private static String response2;
    String urls ="http://webserviceepatec.azurewebsites.net/EPATEC.asmx/Parsear?frase=";

    public static String getAns(){
        return response2;
    }
    @Override
    protected String doInBackground(String... msj) {
        String realOutput="";
        try {

            URL url = new URL(urls+msj[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);

            //System.out.println(msj);

            int responseCode = con.getResponseCode();
            String responseMessage = con.getResponseMessage();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            System.out.printf("Response message: "+responseMessage);
            InputStream input = con.getErrorStream();
            System.out.println("error: "+input.toString());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
            String response3 = response.substring(68, response.length()-9);
            System.out.println(response3);


        }catch (Exception e) {
            System.out.println(e.toString());
            response2=null;
        }
        return response2;
    }

    @Override
    protected void onPostExecute(String s) {
        response2=s;
    }
}