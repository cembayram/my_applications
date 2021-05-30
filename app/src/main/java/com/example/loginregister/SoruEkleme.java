package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class SoruEkleme extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Button SoruKaydet;
    EditText correct_text_s,question_s,ChoiceA_s,ChoiceB_s,ChoiceC_s,ChoiceD_s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru_ekleme);

        databaseHelper = new DatabaseHelper(this);

        Spinner mySpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SoruEkleme.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        // references to buttons and other contorls on the layout
        SoruKaydet = findViewById(R.id.buttonSoruKaydet);
        correct_text_s = findViewById(R.id.correct_text);
        question_s = findViewById(R.id.question);
        ChoiceA_s = findViewById(R.id.ChoiceA);
        ChoiceB_s = findViewById(R.id.ChoiceB);
        ChoiceC_s = findViewById(R.id.ChoiceC);
        ChoiceD_s = findViewById(R.id.ChoiceD);


        SoruKaydet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String question = question_s.getText().toString();
                String correctText = correct_text_s.getText().toString();
                String choiceA = ChoiceA_s.getText().toString();
                String choiceB = ChoiceB_s.getText().toString();
                String choiceC =ChoiceC_s.getText().toString();
                String choiceD =ChoiceD_s.getText().toString();
                String difficulty=mySpinner.getSelectedItem().toString();

                if(question.equals("") || correctText.equals("") || choiceA.equals("") || choiceB.equals("")
                        || choiceC.equals("") || choiceD.equals("")){
                    Toast.makeText(getApplicationContext(), "Alanları doldurmak zorunlu", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean ekle = databaseHelper.questionAdd(correctText,question,choiceA,choiceB,choiceC,choiceD,difficulty);
                    if(ekle == true)
                    {
                        Toast.makeText(getApplicationContext(), "Soru kaydedildi", Toast.LENGTH_SHORT).show();
                        question_s.setText("");
                        correct_text_s.setText("");
                        ChoiceA_s.setText("");
                        ChoiceB_s.setText("");
                        ChoiceC_s.setText("");
                        ChoiceD_s.setText("");
                    }
                    else
                    {
                        Toast.makeText(SoruEkleme.this,"Soru kaydedilirken bir hata oldu",Toast.LENGTH_SHORT).show();
                    }
                }

            }

    });

}
}