package com.compsci.manasi.plannerapp;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_course);
    }

    // Called when enter button is clicked
    // Creates the Course based on name given by user, returns Course object to MainActivity
    public void createNewCourse(View view) {
        Intent data = getIntent();
        EditText enterName = findViewById(R.id.EnterName);
        String courseName = enterName.getText().toString();
        data.putExtra("course", new Course(courseName));
        setResult(Activity.RESULT_OK, data);
        finish();
    }

}
