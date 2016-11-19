package com.example.daniel.proyecto3_bases;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CartActivity extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<String>();
    private EditText mSellerId;
    private EditText mClientId;
    private String office2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        List<Products> listUsers;
        ArrayList listUsersFinal = new ArrayList();



        SQLiteHandler SQLite = new SQLiteHandler(this);

        listUsers = SQLite.getProducts();
        System.out.println("length es: "+listUsers.size());
        for (int i=0; i<listUsers.size(); i++) {
            listUsersFinal.add(listUsers.get(i)._description);
        }

        ListView listCart = (ListView)findViewById(R.id.listviewCart);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listUsersFinal);
        listCart.setAdapter(adapter);


        ListView lv = (ListView) findViewById(R.id.listviewCart);
        generateListContent();
        lv.setAdapter(new MyListAdaper(this, R.layout.content_cart, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
            }
        });

        Spinner dropdown2 = (Spinner) findViewById(R.id.spinnerOffice2);
        String[] office = new String[]{"San Jose", "Cartago", "Alajuela", "Heredia", "Guanacaste", "Puntarenas", "Limón"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, office);
        dropdown2.setAdapter(adapter2);

        office2 = dropdown2.getSelectedItem().toString();

        mSellerId = (EditText) findViewById(R.id.seller_id);
        mClientId = (EditText) findViewById(R.id.client_id);

        Button mSignInButton = (Button) findViewById(R.id.buy_products);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createOrder(office2);
            }
        });

    }

    private void createOrder(String office2) {
        String seller_id = mSellerId.getText().toString();
        String client_id = mClientId.getText().toString();

        Random rand = new Random();
        int n = rand.nextInt(10000);
        String id = Integer.toString(n);

        Orders orden = new Orders(id,office2,client_id,seller_id,false,"10-10-10");

    }

    private void generateListContent() {
        List<Products> listUsers;
        SQLiteHandler SQLite = new SQLiteHandler(this);

        listUsers = SQLite.getProducts();
        System.out.println("length es: "+listUsers.size());
        for (int i=0; i<listUsers.size(); i++) {
            data.add(listUsers.get(i)._description);
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btn);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
                }
            });
            mainViewholder.title.setText(getItem(position));

            return convertView;
        }
    }
    public class ViewHolder {

        ImageView thumbnail;
        TextView title;
        Button button;
    }

}
