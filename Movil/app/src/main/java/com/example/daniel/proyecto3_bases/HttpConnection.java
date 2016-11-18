package com.example.daniel.proyecto3_bases;
import java.io.BufferedReader;
import java.io.InputStream;
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
            con.connect();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);

            System.out.println("MENSAJE es: " + msj);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            InputStreamReader input=new InputStreamReader(con.getInputStream());
            System.out.println("input");
            BufferedReader in = new BufferedReader(input);

            String inputLine;

            StringBuffer response = new StringBuffer();
            System.out.println("Intermedio");
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            con.disconnect();

            //print result
            System.out.println("Response es: " + response.toString());
            String response2 = response.substring(68, response.length()-9);
            System.out.println("Esta es la response filtrada: " + response2);
            return response2;

        }catch (Exception e) {
            return null;
        }
    }
}