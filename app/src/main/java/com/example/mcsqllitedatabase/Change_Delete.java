package com.example.mcsqllitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Change_Delete extends AppCompatActivity {

    Button saveBtn, deleteBtn;
    EditText nameText, cgpaText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_delete);
        saveBtn = findViewById(R.id.saveBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        nameText = findViewById(R.id.studentNameText);
        cgpaText = findViewById(R.id.cgpaTextNumber);
        nameText.setText(getIntent().getStringExtra("name").toString());
        cgpaText.setText(getIntent().getStringExtra("cgpa").toString());
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(getApplicationContext());
                StudentModel std = db.getStudent(Integer.parseInt(getIntent().getStringExtra("index")));
                std.setName(nameText.getText().toString());
                float cgpa = Float.parseFloat(cgpaText.getText().toString());
                if(cgpa>=0 && cgpa <=4)
                {
                    std.setCgpa(cgpa);
                    db.updateStudent(std);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid CGPA", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(getApplicationContext());
                StudentModel std = db.getStudent(Integer.parseInt(getIntent().getStringExtra("index")));
                db.deleteStudent(std);
                Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}