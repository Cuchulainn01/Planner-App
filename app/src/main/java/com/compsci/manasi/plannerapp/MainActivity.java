package com.compsci.manasi.plannerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;

//TODO: Task stuff may have to go within CourseActivity

public class MainActivity extends AppCompatActivity
    implements CourseListAdapter.OnCourseItemSelectedListener {

    //TODO: write to userData file
    private static final String FILE_NAME_USER_DATA = "userData.json";

    private UserData m_ud;

    private CourseListAdapter m_clAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_ud = new UserData();
        loadLists(); // JSON Reader
        setContentView(R.layout.activity_main);

        if (m_clAdapter != null) {
            m_clAdapter.setArrCourses(this.m_ud.getCourseList());
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        loadLists();
//        if(m_clAdapter != null) {
//            m_clAdapter.setArrCourses(this.arrCourseList);
//        }
//    }

//    private Intent m_intentTaskList;
//    private Intent m_intentTaskDetail;

    public ArrayList<Course> getArrCourseList() {
        return m_ud.getCourseList();
    }

    public void setCLAdapter(CourseListAdapter clAdapter) {
        this.m_clAdapter = clAdapter;
    }


    // TODO: File/JSON logic
    // TODO: create "userData.json" if it doesn't already exist, do nothing if blank
    // Start @ readJsonStream, don't forget try/catch
    protected void loadLists() {
        if (null == m_ud.getCourseList()) {
            m_ud.setCourseList( new ArrayList<Course>());
        } else {
            m_ud.getCourseList().clear();
        }

        try {
            Gson gson = new Gson();
            Reader reader = new InputStreamReader(openFileInput(FILE_NAME_USER_DATA));
            readCourses(reader);
            reader.close();
        } catch (IOException ex) {
            try {
                FileOutputStream outputStream = openFileOutput(FILE_NAME_USER_DATA, Context.MODE_PRIVATE); // TODO: check if this creates file in right spot and only if doesn't exist
                outputStream.close();
            } catch (IOException ioEx) {

            }
        }

        // TODO: Remove this call when JSON Reader/Writer is working, DON'T NEED ANYMORE
        loadDummyData();
    }


    private void readCourses(Reader reader) {
        m_ud.setCourseList(new ArrayList<Course>());
        Gson gson = new Gson();
        UserData ud = null;

        try {
            ud = (UserData) gson.fromJson(reader, UserData.class);
        } catch (Exception e) {
        }
        if ( null != ud ) {
            this.m_ud = ud;
        }
    }

    //TODO: prepopulate with believable data
    private void loadDummyData() {
        int i, j;

        for (i=0; i < 5; ++i) {
            Course c = new Course("Course " + i);
            ArrayList<Task> tasks = new ArrayList<Task>();
            for (j=0; j < 3; ++j) {
                tasks.add(new Task("Task " + 3*i+j, "12/5/10", "testing", false));
            }
            c.setTasks(tasks);
            this.m_ud.getCourseList().add(c);
        }
    }

    private void saveUserData() {
        OutputStreamWriter writer = null;
        Gson gson = new Gson();
        String strJson;
        try {
            writer = new OutputStreamWriter(openFileOutput(FILE_NAME_USER_DATA, MODE_PRIVATE));
            strJson = gson.toJson(m_ud);
            writer.write(strJson);
            writer.close();
        } catch (Exception e) {

        }
    }

    private static final int REQUEST_COURSE_NAME = 0;
    // starts activity to create new course when "+" button is clicked
    public void createNewCourse(View view) {
        Intent intent = new Intent(this, CreateNewCourseActivity.class);
        startActivityForResult(intent, REQUEST_COURSE_NAME);
    }

    //TODO: figure out why this isn't getting called
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_COURSE_NAME && resultCode == Activity.RESULT_OK) {
            m_ud.getCourseList().add((Course) data.getExtras().getParcelable("course"));
            if (m_clAdapter != null) {
                m_clAdapter.setArrCourses(this.m_ud.getCourseList());
                saveUserData();
            }
        }
        if(requestCode == REQUEST_UPDATED_COURSE && resultCode == Activity.RESULT_OK) {
            Course course = (Course) data.getExtras().getParcelable("course");
            m_ud.getCourseList().set(lastClickedPos, course);
            saveUserData();
            m_clAdapter.notifyDataSetChanged();
        }
    }

    private Intent m_intentTaskList;
    public static final String COURSE_KEY = "Course";
    private static final int REQUEST_UPDATED_COURSE = 1;

    public Course lastClicked;
    public int lastClickedPos;

    @Override
    public void onCourseItemSelected(int pos, Course course) {
        if ( m_intentTaskList == null ) {
            m_intentTaskList = new Intent(this, TaskListActivity.class);
        }

        lastClicked = course;
        lastClickedPos = pos;

        m_intentTaskList.putExtra(MainActivity.COURSE_KEY, course);
        startActivityForResult(m_intentTaskList, REQUEST_UPDATED_COURSE);

    }

}