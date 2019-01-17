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

public class CourseView extends Fragment {
    private RecyclerView m_rvEvents;
    private MainActivity m_mainActivity;
    private CourseListAdapter m_claAdapter;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CourseView() {
        // Required empty public constructor
    }

    public static CourseView newInstance(String param1, String param2) {
        CourseView fragment = new CourseView();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_course_view, container, false);
        RecyclerView rv = (RecyclerView) vw.findViewById(R.id.id_rvCourses);
        // Bind the AgendaListAdapter to the RecyclerView
        rv.setAdapter(m_claAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
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
