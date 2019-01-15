package com.compsci.manasi.plannerapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

//JSON file imports
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class CreateNewCourseActivity extends AppCompatActivity {

    private static final String FILE_NAME_USER_DATA = "userData.json";
//    public static final String EXTRA_NAME = "com.compsci.manasi.plannerapp.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_course);
    }

    // calls method to add course to JSON model
    // returns to MainActivity
    public void createNewCourse(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText enterName = findViewById(R.id.EnterName);
        String courseName = enterName.getText().toString();
        writeCourseToUserData(courseName);
//        intent.putExtra(EXTRA_NAME, name);
        startActivity(intent);
    }

    @SuppressWarnings("unchecked")
    private void writeCourseToUserData(String courseName) {
        JSONObject obj = new JSONObject();
        obj.put("Name", courseName);
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(FILE_NAME_USER_DATA, Context.MODE_APPEND);
            outputStream.write(obj.toJSONString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
