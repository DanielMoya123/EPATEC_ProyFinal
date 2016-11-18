package com.example.daniel.proyecto3_bases;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SQLiteHandler SQLite = new SQLiteHandler(this);

        List<Category> listUsers;
        ArrayList listUsersFinal = new ArrayList();

        listUsers = SQLite.getCategories();
        System.out.println("length es: "+listUsers.size());
        for (int i=0; i<listUsers.size(); i++) {
            listUsersFinal.add(listUsers.get(i)._description);
        }

        ListView listCart = (ListView)findViewById(R.id.allProducts);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listUsersFinal);
        listCart.setAdapter(adapter);

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listCart.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listCart);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }
        //totalHeight -= 25*adapter.getCount();

        ViewGroup.LayoutParams params = listCart.getLayoutParams();
        params.height = totalHeight + (listCart.getDividerHeight() * (adapter.getCount() - 1));
        listCart.setLayoutParams(params);
        listCart.requestLayout();


    }

    public void onRadioButtonClicked(View view) {
        
    }

}
