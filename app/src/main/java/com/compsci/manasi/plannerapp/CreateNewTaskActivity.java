package com.compsci.manasi.plannerapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

// called when "+" button is clicked from Tasks screen
public class CreateNewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_task);
    }

    // called when "Enter" button is clicked
    // creates a Task from user input, returns task to TaskListActivity
    public void createNewTask(View view) {
        Intent data = getIntent();
        EditText name = findViewById(R.id.EnterTaskName);
        EditText date = findViewById(R.id.EnterDueDate);
        EditText note = findViewById(R.id.EnterNotes);
        String taskName = name.getText().toString();
        String dueDate  = date.getText().toString();
        String notes  = note.getText().toString();
        data.putExtra("task", new Task(taskName, dueDate, notes, false));
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
