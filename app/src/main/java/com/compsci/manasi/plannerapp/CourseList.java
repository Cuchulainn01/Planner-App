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

// TODO: Add "m_" or "m" prefix to ALL members?? none
// TODO: Add TaskList class, along with fragment_coursedetail.xml
// TODO: Enable fragment_coursedetail.xml in activity_main.xml

public class CourseList extends Fragment {
    private RecyclerView m_rvCourseList;
    private MainActivity m_mainActivity;
    private CourseListAdapter m_claAdapter;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String m_Param1;
    private String m_Param2;

    public CourseList() {
        // Required empty public constructor
    }

    public static CourseList newInstance(String param1, String param2) {
        CourseList fragment = new CourseList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        m_mainActivity = (MainActivity)context;
        m_claAdapter = new CourseListAdapter(m_mainActivity.getArrCourseList(), m_mainActivity);
        m_mainActivity.setCLAdapter(m_claAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            m_Param1 = getArguments().getString(ARG_PARAM1);
            m_Param2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_courselist, container, false);
        RecyclerView rv = vw.findViewById(R.id.id_rvCourseList);
        // Bind the CourseListAdapter to the RecyclerView
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setAdapter(m_claAdapter);
        m_claAdapter.notifyDataSetChanged();
        rv.setClickable(true);

        DividerItemDecoration did = new DividerItemDecoration(rv.getContext(), llm.getOrientation());
        rv.addItemDecoration(did);

        return vw;
    }

    public CourseListAdapter getAdapter() {
        return m_claAdapter;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
