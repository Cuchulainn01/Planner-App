package com.compsci.manasi.plannerapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class TaskDetailActivity extends AppCompatActivity {

    // Called when Task is clicked from TaskList
    // sets text to be displayed for each Task
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent intent = getIntent();
        Task task = intent.getParcelableExtra(TaskListActivity.TASK_KEY);
        setTextForView(R.id.id_tvTDTaskName, task.m_strName);
        setTextForView(R.id.id_tvTDDueDate, task.m_strDueDate);
        setTextForView(R.id.id_tvTDNotes, task.m_strNotes);

    }

    // helper method to reduce repeated code
    private void setTextForView(int idTxtView, String text) {
        TextView tv = findViewById(idTxtView);
        tv.setText(text);
    }
}
