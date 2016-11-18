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
import android.widget.Toast;

import org.json.JSONException;

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

        Category categoria = new Category("123","prueba");
        SQLite.addCategory(categoria);
        /*
        try {
            SQLite.UpSyncronize();
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/

        List listUsers;
        listUsers = SQLite.getCategories();

        ListView listCart = (ListView)findViewById(R.id.allUsers);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listUsers);
        listCart.setAdapter(adapter);


    }

}
