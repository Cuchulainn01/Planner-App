package com.compsci.manasi.plannerapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Fragment that TaskList RecyclerView is displayed on
public class TaskList extends Fragment {

    private RecyclerView m_rvCTaskList;
    private TaskListActivity m_taskListActivity;
    private TaskListAdapter m_tlaAdapter;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String m_Param1;
    private String m_Param2;

    public TaskList() {
        // Required empty public constructor
    }

    public static TaskList newInstance(String param1, String param2) {
        TaskList fragment = new TaskList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        m_taskListActivity = (TaskListActivity) context;
        m_tlaAdapter = new TaskListAdapter(m_taskListActivity.getArrTaskList(), m_taskListActivity);
        m_taskListActivity.setTLAdapter(m_tlaAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            m_Param1 = getArguments().getString(ARG_PARAM1);
            m_Param2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // assigns TaskListAdapter to RecyclerView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_tasklist, container, false);
        RecyclerView rv = vw.findViewById(R.id.id_rvTaskList);
        // Bind the TaskListAdapter to the RecyclerView
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setAdapter(m_tlaAdapter);
        m_tlaAdapter.notifyDataSetChanged();
        rv.setClickable(true);

        DividerItemDecoration did = new DividerItemDecoration(rv.getContext(), llm.getOrientation());
        rv.addItemDecoration(did);

        return vw;
    }

    public TaskListAdapter getAdapter() {
        return m_tlaAdapter;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
