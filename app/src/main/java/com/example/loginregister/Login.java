package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    Button btn_lregister, btn_llogin;
    EditText et_lusername, et_lpassword;
    Integer attempt;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        et_lusername = (EditText)findViewById(R.id.inputUsername);
        et_lpassword = (EditText)findViewById(R.id.inputPassword);
        btn_llogin = (Button)findViewById(R.id.btnLogin);
        btn_lregister = (Button)findViewById(R.id.btn_lregister);

        btn_lregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_lusername.getText().toString();
                String password = et_lpassword.getText().toString();
                attempt=0;

                Boolean checklogin = databaseHelper.CheckLogin(username, password);
                if(checklogin == true){
                    Toast.makeText(getApplicationContext(), "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, DashboardMenu.class);
                    startActivity(intent);

                   // Intent intent;
                    //intent = new Intent(v.getContext(), DashboardMenu.class);
                    //startActivity(intent);
                }
                else{
                    et_lusername.setText("");
                    et_lpassword.setText("");
                    attempt +=1;
                    Toast.makeText(getApplicationContext(), "Kullanıcı Adı veya şifre hatalı", Toast.LENGTH_SHORT).show();
                    if (attempt>=3){
                        Toast.makeText(Login.this,"3 defa hatalı giriş yapıldığı için uygulama sonlandırılıyor", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

}