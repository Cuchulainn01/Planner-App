package com.compsci.manasi.plannerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateNewCourseActivity extends AppCompatActivity {

    private static final String FILE_NAME_USER_DATA = "userData.json";
    public static final String EXTRA_NAME = "com.compsci.manasi.plannerapp.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_course);
    }

    // calls method to add course to JSON model
    // returns to MainActivity
    public void createNewCourse(View view) {
        // TODO: add code to write Course to the JSON file
        // TODO: change so course name isn't returned but just added to JSON
        Intent intent = new Intent(this, MainActivity.class);
        EditText courseName = (EditText) findViewById(R.id.EnterName);
        String name = courseName.getText().toString();
        intent.putExtra(EXTRA_NAME, name);
        startActivity(intent);
    }
}
