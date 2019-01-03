package com.compsci.manasi.plannerapp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

//TODO: Task stuff may have to go within CourseActivity

public class MainActivity extends AppCompatActivity {
//    implements CourseListAdapter.OnCourseItemSelectedListener,
//    implements TaskListAdapter.OnTaskItemSelectedListener {

    //TODO: write to userData file
    private static final String FILE_NAME_USER_DATA = "userData.json";

    //TODO: create course class
    //TODO: create task class
    private ArrayList<Course> arrCourseList;

    //TODO: create list adapters
    private CourseListAdapter m_clAdapter;
    private TaskListAdapter m_tlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayouts(); // figure out what ViewPager does first
        loadLists(); // JSON Reader
        //TODO: set arrayList for each adapter (look @ downloadsComplete)
    }

    private Intent m_intentCourseDetail;
    private Intent m_intentTaskDetail;

    public ArrayList<Course> getArrCourseList() {
        return arrCourseList;
    }

    public void setCLAdapter(CourseListAdapter m_clAdapter) {
        this.m_clAdapter = m_clAdapter;
    }

    public void setTLAdapter(TaskListAdapter m_tlAdapter) {
        this.m_tlAdapter = m_tlAdapter;
    }

    //TODO: figure out what ViewPager does
//    private ViewPager m_vp;
//
//    protected void initLayouts() {
//
//    }

    //TODO: File/JSON logic
    protected void loadLists() {
        // Start @ readJsonStream, don't forget try/catch
    }

    // starts activity to create new course when "+" button is clicked
    public void createNewCourse(View view) {
        Intent intent = new Intent(this, CreateNewCourseActivity.class);
        startActivity(intent);
    }

    //TODO: onCourseSelected (look @ onAgendaItemSelected)
    //TODO: onTaskSelected
}
