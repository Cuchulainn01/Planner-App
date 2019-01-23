package com.compsci.manasi.plannerapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

// implements Parcelable so Task objects can be passed between activities
public class Task implements Parcelable {

    // Serializes member variables for storing as JSON object
    @SerializedName("TaskName")
    public String m_strName;
    @SerializedName("TaskDueDate")
    public String m_strDueDate;
//    public LocalDateTime notificationTime; // to be be implemented for push notifications
    @SerializedName("TaskNotes")
    public String m_strNotes;
    @SerializedName("isComplete")
    public boolean m_boolIsComplete;


    public Task(String name, String dueDate, String notes, boolean isComplete) {
        this.m_strName = name;
        this.m_strDueDate = dueDate;
//        this.notificationTime = notificationTime;
        this.m_strNotes = notes;
        this.m_boolIsComplete = isComplete;
    }

    // Parcelable Interface methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.m_strName);
        dest.writeString(this.m_strDueDate);
        dest.writeString(this.m_strNotes);
        dest.writeByte((byte) (this.m_boolIsComplete ? 1 : 0));
    }

    public Task(Parcel in) {
        this.m_strName = in.readString();
        this.m_strDueDate = in.readString();
        this.m_strNotes = in.readString();
        this.m_boolIsComplete = in.readByte() != 0;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

}
