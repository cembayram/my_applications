package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SinavAyarlari extends AppCompatActivity {

    Button SinavAyarKaydet;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String SinavSuresi = "sinavSuresiKey";
    public static final String SoruPuani = "sinavSoruPuaniKey";
    public static final String SinavZorlukDuzeyi = "sinavZorlukDuzeyiKey";
    SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinav_ayarlari);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        Spinner mySpinner1 = findViewById(R.id.spinnerSinavSuresi);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SinavAyarlari.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.SinavSuresi));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter);


        Spinner mySpinner2 = findViewById(R.id.spinnerSoruPuani);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(SinavAyarlari.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.SoruPuani));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);


        Spinner mySpinner3 = findViewById(R.id.spinnerSinavZorlukDuzeyi);
        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(SinavAyarlari.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.SinavZorlukDuzeyi));
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner3.setAdapter(myAdapter3);


        SinavAyarKaydet=(Button) findViewById(R.id.buttonSinavAyarKaydet);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        SinavAyarKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sinavSuresi =mySpinner1.getSelectedItem().toString();
                String soruPuani=mySpinner2.getSelectedItem().toString();
                String sinavZorlukDuzeyi=mySpinner3.getSelectedItem().toString();

                SharedPreferences.Editor editor =sharedpreferences.edit();

                editor.putString(SinavSuresi,sinavSuresi);
                editor.putString(SoruPuani,soruPuani);
                editor.putString(SinavZorlukDuzeyi,sinavZorlukDuzeyi);
                editor.commit();
                Toast.makeText(SinavAyarlari.this,"Sınav Ayarlarınız Kaydedildi",Toast.LENGTH_LONG).show();


            }
        });

    }

}