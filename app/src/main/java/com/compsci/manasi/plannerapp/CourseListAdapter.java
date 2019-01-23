package com.compsci.manasi.plannerapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CLViewHolder> {

    private ArrayList<Course> m_arrCourses;
    private Context m_context;
    private OnCourseItemSelectedListener m_Listener;

    public interface OnCourseItemSelectedListener {
        void onCourseItemSelected(int pos, Course course);
    }

    public CourseListAdapter(ArrayList<Course> courses, Context context) {
        this.m_arrCourses = courses;
        this.m_context = context;
        if (context instanceof OnCourseItemSelectedListener) {
            this.m_Listener = (OnCourseItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnCourseItemSelectedListener");
        }
    }

    public void setArrCourses(ArrayList<Course> arrCourses) {
        this.m_arrCourses = arrCourses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View vw = LayoutInflater.from(parent.getContext()).inflate(R.layout.courselist_row, parent, false);
        return new CLViewHolder(vw);
    }

    @Override
    public void onBindViewHolder(@NonNull CLViewHolder clViewHolder, int pos) {
        Course course = m_arrCourses.get(pos);
        String strText = String.format("%s (%d tasks)",course.getName(), course.getTaskCount());
        clViewHolder.tvCourseName.setText(strText);
        clViewHolder.bind(pos, course, m_Listener);
    }

    @Override
    public int getItemCount() {
        return (m_arrCourses == null ? 0 : m_arrCourses.size());
    }

    static class CLViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCourseName;

        public CLViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseName = itemView.findViewById(R.id.id_tvCourseName);
        }

        public void bind(final int pos, final Course course, final OnCourseItemSelectedListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onCourseItemSelected(pos, course);
                }
            });
        }
    }
}
