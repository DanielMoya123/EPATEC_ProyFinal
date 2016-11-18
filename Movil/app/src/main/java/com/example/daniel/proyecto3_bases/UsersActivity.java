package com.example.daniel.proyecto3_bases;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SQLiteHandler SQLite = new SQLiteHandler(this);


       /* Category categoria = new Category("hola","prueba");
        try {
            SQLite.addCategory(categoria);
        } catch (Exception e) {
            e.printStackTrace();

        }*/

/*
        try {
            SQLite.UpSyncronize();
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } */

        List<Category> listUsers;
        ArrayList listUsersFinal = new ArrayList();

        listUsers = SQLite.getCategories();
        System.out.println("length es: "+listUsers.size());
        for (int i=0; i<listUsers.size(); i++) {
            listUsersFinal.add(listUsers.get(i)._description);
        }

        ListView listCart = (ListView)findViewById(R.id.allUsers);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listUsersFinal);
        listCart.setAdapter(adapter);

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listCart.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listCart);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listCart.getLayoutParams();
        params.height = totalHeight + (listCart.getDividerHeight() * (adapter.getCount() - 1));
        listCart.setLayoutParams(params);
        listCart.requestLayout();





        Spinner dropdown = (Spinner)findViewById(R.id.spinnerClient);
        String[] clients = new String[]{"prueba1", "prueba2", "prueba3"};
        ArrayAdapter<String> adapterClient = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, clients);
        dropdown.setAdapter(adapterClient);

        Spinner dropdown2 = (Spinner)findViewById(R.id.spinnerOffice);
        String[] office = new String[]{"San Jose", "Cartago", "Alajuela", "Heredia", "Guanacaste", "Puntarenas", "Limón"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, office);
        dropdown2.setAdapter(adapter2);


    }

}
