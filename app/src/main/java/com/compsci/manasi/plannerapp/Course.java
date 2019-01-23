package com.compsci.manasi.plannerapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Course implements Parcelable {

    @SerializedName("CourseName")
    public String m_strName;
    @SerializedName("Tasks")
    public ArrayList<Task> m_arrTasks;

    public Course(String name, ArrayList<Task> tasks) {
        this.m_strName = name;
        this.m_arrTasks = tasks;
        this.m_arrTasks = new ArrayList<Task>();
    }

    public Course(String name) {
        this(name, null);
    }

    public void setTasks(ArrayList<Task> arrTasks) {
        this.m_arrTasks = arrTasks;
    }

    public String getName() {
        return this.m_strName;
    }

    public int getTaskCount() {
        return (null == this.m_arrTasks ? 0 : m_arrTasks.size());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.m_strName);
        dest.writeList(this.m_arrTasks);
    }

    private Course(Parcel in) {
        this.m_strName = in.readString();
        this.m_arrTasks = in.readArrayList(Course.class.getClassLoader());
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel source) {
            return new Course(source);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };
}
