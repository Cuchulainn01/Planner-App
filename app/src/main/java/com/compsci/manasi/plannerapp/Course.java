package com.compsci.manasi.plannerapp;

import java.util.ArrayList;

public class Course {

    public String name;
    public ArrayList<Task> tasks;

    public Course(String name, ArrayList<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    public Course(String name) {
        this(name, null);
    }
}
