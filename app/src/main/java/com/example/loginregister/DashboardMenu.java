package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;


public class DashboardMenu extends AppCompatActivity {
    Button buttonSinavAyarlari,buttonSinavOlustur,buttonSoruListele,buttonSoruEkle,buttonSinavYolla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_menu);

        buttonSinavAyarlari = (Button)findViewById(R.id.buttonSinavAyarlari);
        buttonSinavOlustur = (Button)findViewById(R.id.buttonSinavOlustur);
        buttonSoruListele = (Button)findViewById(R.id.buttonSoruListele);
        buttonSoruEkle = (Button)findViewById(R.id.buttonSoruEkle);
        buttonSinavYolla = findViewById(R.id.buttonSinavYolla);

        buttonSoruEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardMenu.this, SoruEkleme.class);
                startActivity(intent);
            }
        });
        buttonSoruListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DashboardMenu.this, SoruListeleme.class);
                startActivity(intent);
            }
        });
        buttonSinavOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardMenu.this,SinavOlustur.class);
                startActivity(intent);
            }
        });
        buttonSinavAyarlari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardMenu.this,SinavAyarlari.class);
                startActivity(intent);
            }
        });
        buttonSinavYolla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardMenu.this, MailGonderme.class);
                startActivity(intent);
            }
        });
    }
}