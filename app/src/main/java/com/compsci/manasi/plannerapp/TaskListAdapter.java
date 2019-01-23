package com.compsci.manasi.plannerapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

// adapts List of Tasks to display in RecyclerView
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TLViewHolder> {

    private ArrayList<Task> m_arrTasks;
    private Context m_context;
    private OnTaskItemSelectedListener m_Listener;

    // implemented by TaskListActivity
    public interface OnTaskItemSelectedListener {
        void onTaskItemSelected(Task task);
    }

    public TaskListAdapter(ArrayList<Task> tasks, Context context) {
        this.m_arrTasks = tasks;
        this.m_context = context;
        if(context instanceof OnTaskItemSelectedListener) {
            this.m_Listener = (OnTaskItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement OnAgendaItemSelectedListener");
        }
    }

    public void setArrTasks(ArrayList<Task> arrTasks) {
        this.m_arrTasks = arrTasks;
        notifyDataSetChanged();
    }

    // sets layout for each row of TaskList
    @NonNull
    @Override
    public TLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View vw = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklist_row, parent, false);
        return new TLViewHolder(vw);
    }

    // sets text for each row of TaskList, and info passed with the row
    @Override
    public void onBindViewHolder(@NonNull TLViewHolder tlViewHolder, int pos) {
        Task task = m_arrTasks.get(pos);
        tlViewHolder.tvTaskName.setText(task.m_strName);
        tlViewHolder.tvDueDate.setText(task.m_strDueDate);
        tlViewHolder.bind(task, m_Listener);
    }

    @Override
    public int getItemCount() { return (m_arrTasks == null ? 0 : m_arrTasks.size());}

    static class TLViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTaskName;
        public TextView tvDueDate;

        public TLViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.id_tvTaskName);
            tvDueDate = itemView.findViewById(R.id.id_tvDueDate);
        }

        public void bind(final Task task, final TaskListAdapter.OnTaskItemSelectedListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onTaskItemSelected(task);
                }
            });
        }

    }
}
