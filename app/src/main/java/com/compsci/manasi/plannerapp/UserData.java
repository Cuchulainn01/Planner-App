package com.compsci.manasi.plannerapp;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("Courses")
    private ArrayList<Course> m_arrCourseList;

    public UserData(ArrayList<Course> courses) {
        this.m_arrCourseList = courses;
    }
    public UserData() { this.m_arrCourseList = new ArrayList<Course>(); }

    public ArrayList<Course> getCourseList() {
        return m_arrCourseList;
    }

    public void setCourseList(ArrayList<Course> m_arrCourseList) {
        this.m_arrCourseList = m_arrCourseList;
    }
}
