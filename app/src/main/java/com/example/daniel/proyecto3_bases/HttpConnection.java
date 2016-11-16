package com.example.daniel.proyecto3_bases;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by ferllini13 on 16/11/2016.
 */

public class HttpConnection {
    String urls ="http://webserviceepatec.azurewebsites.net/EPATEC.asmx/Parsear?frase=";


    public String request(String msj) {

        try {

            URL url = new URL(urls+msj);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);

            System.out.println(msj);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

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
            String response2 = response.substring(68, response.length()-9);
            System.out.println(response2);
            return response2;

        }catch (Exception e) {
            return null;
        }
    }
}