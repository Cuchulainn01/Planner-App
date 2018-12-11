package com.compsci.manasi.plannerapp;

import java.time.LocalDateTime;

public class Task {

    public String name;
    public LocalDateTime dueDate;
    public LocalDateTime notificationTime; // may need specific format for push notifications
    public String notes;
    public boolean isComplete;

    //TODO: set parameters as null when creating object in JSON reader, easier than making all possible constructors
    public Task(String name, LocalDateTime dueDate, LocalDateTime notificationTime, String notes, boolean isComplete) {
        this.name = name;
        this.dueDate = dueDate;
        this.notificationTime = notificationTime;
        this.notes = notes;
        this.isComplete = isComplete;
    }

}
