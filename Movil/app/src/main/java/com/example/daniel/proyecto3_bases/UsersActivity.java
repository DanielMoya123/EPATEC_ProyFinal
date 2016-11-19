package com.example.daniel.proyecto3_bases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<String>();
    private EditText mUserName;
    private EditText mUserLast1;
    private EditText mUserLast2;
    private EditText mUserAddress;
    private EditText mUserPhone;
    private EditText mUserIdentity;
    private EditText mUserBirth;
    private EditText mUserPassword;
    private String office2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       /* SQLiteHandler SQLite = new SQLiteHandler(this);

        List<Category> listUsers;
        ArrayList listUsersFinal = new ArrayList();

        listUsers = SQLite.getCategories();
        System.out.println("length es: "+listUsers.size());
        for (int i=0; i<listUsers.size(); i++) {
            listUsersFinal.add(listUsers.get(i)._description);*/
       /* }

        ListView listCart = (ListView)findViewById(R.id.allUsers);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listUsersFinal);
        listCart.setAdapter(adapter); */
/*
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
        listCart.requestLayout();*/


        Spinner dropdown = (Spinner) findViewById(R.id.spinnerClient);
        String[] clients = new String[]{"prueba1", "prueba2", "prueba3"};
        ArrayAdapter<String> adapterClient = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, clients);
        dropdown.setAdapter(adapterClient);

        Spinner dropdown2 = (Spinner) findViewById(R.id.spinnerOffice);
        String[] office = new String[]{"San Jose", "Cartago", "Alajuela", "Heredia", "Guanacaste", "Puntarenas", "Limón"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, office);
        dropdown2.setAdapter(adapter2);

        office2 = dropdown2.getSelectedItem().toString();

        mUserName = (EditText) findViewById(R.id.name);
        mUserLast1 = (EditText) findViewById(R.id.last1);
        mUserLast2 = (EditText) findViewById(R.id.last2);
        mUserAddress = (EditText) findViewById(R.id.address);
        mUserPhone = (EditText) findViewById(R.id.phone);
        mUserIdentity = (EditText) findViewById(R.id.identity);
        mUserBirth = (EditText) findViewById(R.id.birth);
        mUserPassword = (EditText) findViewById(R.id.password2);

        Button mSignInButton = (Button) findViewById(R.id.add_user_button);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser(office2);
            }
        });

/*
        ListView lv = (ListView) findViewById(R.id.listviewUsers);
        generateListContent();
        lv.setAdapter(new MyListAdaper(this, R.layout.content_users, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
            }
        });
*/

    }

    private void createUser(String office){
        String userName = mUserName.getText().toString();
        String userLast1 = mUserLast1.getText().toString();
        String userLast2 = mUserLast2.getText().toString();
        String userAddress = mUserAddress.getText().toString();
        String userPhone = mUserPhone.getText().toString();
        String userIdentity = mUserIdentity.getText().toString();
        String userBirth = mUserBirth.getText().toString();
        String userPassword = mUserPassword.getText().toString();

        Users usuario = new Users("12345673123",userName,userLast1,userLast2,userPhone,userIdentity,"usuario",userPassword,userBirth,office2,userAddress,"0");
        SQLiteHandler handler = new SQLiteHandler(this);
        handler.addUser(usuario);

    }

/*
    private void generateListContent() {

        SQLiteHandler SQLite = new SQLiteHandler(this);

        List<Users> listUsers;
        listUsers = SQLite.getUsers();

        for(int i = 0; i < listUsers.size(); i++) {
            data.add(listUsers.get(i)._name);
        }
    }

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
            UsersActivity.ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                UsersActivity.ViewHolder viewHolder = new UsersActivity.ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_textUser);
                viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btnUser);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (UsersActivity.ViewHolder) convertView.getTag();
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

        TextView title;
        Button button;
    }*/




}
