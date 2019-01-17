package com.compsci.manasi.plannerapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//TODO: Task stuff may have to go within CourseActivity

public class MainActivity extends AppCompatActivity {
//    implements CourseListAdapter.OnCourseItemSelectedListener,
//    implements TaskListAdapter.OnTaskItemSelectedListener {

    //TODO: write to userData file
    private static final String FILE_NAME_USER_DATA = "userData.json";

    private ArrayList<Course> arrCourseList;

    //TODO: create list adapters
    private CourseListAdapter m_clAdapter;
//    private TaskListAdapter m_tlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initLayouts(); // figure out what ViewPager does first
        loadLists(); // JSON Reader
        if(m_clAdapter != null) {
            m_clAdapter.setArrCourses(this.arrCourseList);
        }
        //TODO: set arrayList for each adapter (look @ downloadsComplete)
    }

    private Intent m_intentCourseDetail;
//    private Intent m_intentTaskDetail;

    public ArrayList<Course> getArrCourseList() {
        return arrCourseList;
    }

    public void setCLAdapter(CourseListAdapter m_clAdapter) {
        this.m_clAdapter = m_clAdapter;
    }

//    public void setTLAdapter(TaskListAdapter m_tlAdapter) {
//        this.m_tlAdapter = m_tlAdapter;
//    }

    //TODO: figure out what ViewPager does
//    private ViewPager m_vp;
//
//    protected void initLayouts() {
//
//    }

    //TODO: File/JSON logic
    // TODO: create "userData.json" if it doesn't already exist, do nothing if blank
    // Start @ readJsonStream, don't forget try/catch
    protected void loadLists() {
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(openFileInput(FILE_NAME_USER_DATA)));
            readCourses(reader);
            reader.close();
        } catch(IOException ex) {
            FileOutputStream outputStream = openFileOutput(FILE_NAME_USER_DATA, Context.MODE_PRIVATE); // TODO: check if this creates file in right spot and only if doesn't exist
            outputStream.close();
            this.arrCourseList = new ArrayList<Course>();
        }
    }

    private void readCourses(JsonReader reader) {
        this.arrCourseList = new ArrayList<Course>();

        try {
            while(reader.hasNext()) {
                reader.beginObject();
                String name = reader.nextName();
                arrCourseList.add(new Course(name));
                reader.endObject();
            }
        } catch (Exception e) {

        }
    }

    // starts activity to create new course when "+" button is clicked
    public void createNewCourse(View view) {
        Intent intent = new Intent(this, CreateNewCourseActivity.class);
        startActivity(intent);
    }

    //TODO: onCourseSelected (look @ onAgendaItemSelected)
    //TODO: onTaskSelected
}
