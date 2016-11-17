package com.example.daniel.proyecto3_bases;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textViewJairo =(TextView)findViewById(R.id.textViewJairo);
        textViewJairo.setClickable(true);
        textViewJairo.setMovementMethod(LinkMovementMethod.getInstance());
        String textJairo = "<a href='https://www.facebook.com/jairo.mendez.7?fref=ts'> Jairo Méndez Martínez </a>";
        textViewJairo.setText(Html.fromHtml(textJairo));

        TextView textViewFran =(TextView)findViewById(R.id.textViewFran);
        textViewFran.setClickable(true);
        textViewFran.setMovementMethod(LinkMovementMethod.getInstance());
        String textFran = "<a href='https://www.facebook.com/francisco.alvaradoferllini'> Francisco Alvarado Ferllini </a>";
        textViewFran.setText(Html.fromHtml(textFran));

        TextView textViewJason =(TextView)findViewById(R.id.textViewJason);
        textViewJason.setClickable(true);
        textViewJason.setMovementMethod(LinkMovementMethod.getInstance());
        String textJason = "<a href='https://www.facebook.com/jason.leiton.31?fref=ts'> Jason Leitón Jiménez </a>";
        textViewJason.setText(Html.fromHtml(textJason));


        TextView textViewDaniel =(TextView)findViewById(R.id.textViewDaniel);
        textViewDaniel.setClickable(true);
        textViewDaniel.setMovementMethod(LinkMovementMethod.getInstance());
        String textDaniel = "<a href='https://www.facebook.com/daniel.moya.79?fref=ts'> Daniel Moya Sánchez </a>";
        textViewDaniel.setText(Html.fromHtml(textDaniel));

    }

}
