package com.example.mcsqllitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity  {

    ListView listView;
    Button showBtn, addBtn;
    TextView nameText, cgpaText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = findViewById(R.id.nameText);
        cgpaText = findViewById(R.id.cgpaText);
        listView = findViewById(R.id.listview);
        showBtn = findViewById(R.id.showBtn);
        addBtn = findViewById(R.id.addBtn);
        updateListView();

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateListView();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = nameText.getText().toString();
                    if(name.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Name Should not be empty", Toast.LENGTH_SHORT).show();
                    }
                    float cgpa = Float.parseFloat(cgpaText.getText().toString());
                    if(cgpa<0 && cgpa>4){
                        Toast.makeText(getApplicationContext(), "Invalid CGPA", Toast.LENGTH_SHORT).show();
                    }
                    StudentModel std = new StudentModel(name,cgpa);
                    DBHelper dbHelper = new DBHelper(MainActivity.this);
                    dbHelper.addStudent(std);
                    updateListView();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    private void updateListView()
    {
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        List<StudentModel> list = dbHelper.getAllStudents();
        ArrayAdapter arrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
    }
}