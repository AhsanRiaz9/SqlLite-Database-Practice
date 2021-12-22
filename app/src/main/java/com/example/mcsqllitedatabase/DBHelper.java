package com.example.mcsqllitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String STUDENT_TABLE = "Student";
    public static final String STUDENT_ID = "StudentID";
    public static final String STUDENT_NAME = "StudentName";
    public static final String STUDENT_CGPA = "StudentCGPA";

    public DBHelper(@Nullable Context context) {
        super(context, "studentDB.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createDB = "CREATE TABLE " + STUDENT_TABLE + "(" + STUDENT_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " Text, " + STUDENT_CGPA + " real )";
        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(db);
    }

    public void addStudent(StudentModel std)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STUDENT_NAME, std.getName());
        cv.put(STUDENT_CGPA,std.getCgpa());
        db.insert(STUDENT_TABLE,null, cv);
        db.close();
    }

    public ArrayList<StudentModel>getAllStudents()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + STUDENT_TABLE,null);
        ArrayList<StudentModel> list = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do {
                list.add(new StudentModel(cursor.getString(1),cursor.getFloat(2)));
            }
            while(cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public void updateStudent(StudentModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STUDENT_NAME, model.getName());
        cv.put(STUDENT_CGPA, model.getCgpa());
        db.update(STUDENT_TABLE, cv, STUDENT_ID+"= ?", new String[]{Integer.toString(model.getId())});
    }

    public StudentModel getStudent(int pos) {
        StudentModel std;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + STUDENT_TABLE,null);
        if(cursor.moveToNext())
        {
            int count = 0;
            do{
                if(count==pos)
                {
                    std = new StudentModel(cursor.getString(1),cursor.getFloat(2));
                    std.setId(cursor.getInt(0));
                    return std;
                }
            }
            while(cursor.moveToNext());
        }
        std = new StudentModel(cursor.getString(1), cursor.getFloat(2));
        std.setId(-1);
        return std;
    }

    public void deleteStudent(StudentModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STUDENT_TABLE, STUDENT_ID+"= ?", new String[]{Integer.toString(model.getId())});
    }
}
