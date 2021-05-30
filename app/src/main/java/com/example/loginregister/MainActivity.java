package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText et_username, et_password, et_cpassword, et_name, et_surname, et_tel, et_email;
    Button btn_register, btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        et_name  = (EditText)findViewById((R.id.inputName));
        et_surname = (EditText)findViewById(R.id.inputSurname);
        et_tel = (EditText)findViewById(R.id.inputTel);
        et_email = (EditText)findViewById(R.id.inputEmail);
        
        et_username = (EditText)findViewById(R.id.inputUsername);
        et_password = (EditText)findViewById(R.id.inputPassword);
        et_cpassword = (EditText)findViewById(R.id.inputConformPassword);

        btn_register = (Button)findViewById(R.id.btnRegister);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String name = et_name.getText().toString();
                String surname =et_surname.getText().toString();
                String tel =et_tel.getText().toString();
                String email =et_email.getText().toString();
                String confirm_password = et_cpassword.getText().toString();

                if(username.equals("") || password.equals("") || confirm_password.equals("")){
                    Toast.makeText(getApplicationContext(), "Alanları doldurmak zorunlu", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(confirm_password)){
                        Boolean checkusername = databaseHelper.CheckUsername(username);
                        if(checkusername == true){
                            Boolean insert = databaseHelper.Insert(username,password,name,surname,tel,email);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(), "Kayıt yapıldı", Toast.LENGTH_SHORT).show();
                                et_username.setText("");
                                et_password.setText("");
                                et_cpassword.setText("");
                                et_name.setText("");
                                et_surname.setText("");
                                et_tel.setText("");
                                et_email.setText("");
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Kullanıcı kayıtlı", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Şifrenizi doğru yazmadınız", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}