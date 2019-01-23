package com.compsci.manasi.plannerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

// Overall screen the TaskList fragment is displayed on
// Started by MainActivity when a Course item is clicked
public class TaskListActivity extends AppCompatActivity
        implements TaskListAdapter.OnTaskItemSelectedListener{

    //private ArrayList<Task> m_arrTaskList;
    private TaskListAdapter m_tlAdapter;
    public Course m_currentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        Intent intent = getIntent();
        m_currentCourse = intent.getParcelableExtra(MainActivity.COURSE_KEY);
        // this.m_arrTaskList = m_currentCourse.m_arrTasks;

        if (m_tlAdapter != null) {
            m_tlAdapter.setArrTasks(this.m_currentCourse.m_arrTasks);
        }
    }

    public ArrayList<Task> getArrTaskList() {
        return (null == this.m_currentCourse ? null : this.m_currentCourse.m_arrTasks);
    }

    public void setTLAdapter(TaskListAdapter tlAdapter) {
        this.m_tlAdapter = tlAdapter;
    }

    private static final int REQUEST_TASK_DETAILS = 1;

    // starts activity to create new task when "+" button is clicked
    public void createNewTask(View view) {
        Intent intent = new Intent(this, CreateNewTaskActivity.class);
        startActivityForResult(intent, REQUEST_TASK_DETAILS);
    }

    // called when Task is returned from CreateNewTaskActivity
    // adds task to currentCourse
    // returns currentCourse to MainActivity to update userData
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TASK_DETAILS && resultCode == Activity.RESULT_OK) {
            m_currentCourse.m_arrTasks.add((Task) data.getExtras().getParcelable("task"));
            //m_arrTaskList = m_currentCourse.m_arrTasks;
            if (m_tlAdapter != null) {
                m_tlAdapter.setArrTasks(this.m_currentCourse.m_arrTasks);
            }
            Intent intent = getIntent();
            data.putExtra("course", m_currentCourse);
            setResult(Activity.RESULT_OK, data);
            finish();
        }
    }

    private Intent m_intentTaskDetail;
    public static final String TASK_KEY = "Task";
    //Called when Task item is selected from TaskList
    // Starts activity to display TaskDetails
    @Override
    public void onTaskItemSelected(Task task) {
        if ( m_intentTaskDetail == null ) {
            m_intentTaskDetail = new Intent(this, TaskDetailActivity.class);
        }

        m_intentTaskDetail.putExtra(TaskListActivity.TASK_KEY, task);
        startActivity(m_intentTaskDetail);

    }
    // Old code, may be useful later
//    @Override
//    protected void onStop() {
//        Intent data = getIntent();
//        data.putExtra("taskList", m_currentCourse.m_arrTasks);
//        setResult(Activity.RESULT_OK, data);
//        super.onStop();
//    }
}
