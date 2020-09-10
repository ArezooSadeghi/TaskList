package com.example.tasklist.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tasklist.R;
import com.example.tasklist.controller.activity.TaskListActivity;
import com.example.tasklist.repository.TaskRepository;

public class StartPageFragment extends Fragment {

    private EditText mEditTextUsername, mEditTextNumberOfTask;
    private Button mButtonBuild;

    public static final String EXTRA_USERNAME = "com.example.tasklist.controller.fragment.EXTRA_USERNAME";
    public static final String EXTRA_NUMBER_OF_TASK = "com.example.tasklist.controller.fragment.EXTRA_NUMBER_OF_TASK";

    public StartPageFragment() {
    }

    public static StartPageFragment newInstance() {
        Bundle args = new Bundle();
        StartPageFragment startPageFragment = new StartPageFragment();
        startPageFragment.setArguments(args);
        return startPageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fagment_start_page, container, false);
        findViews(view);
        setListeners();
        return view;
    }

    private void setListeners() {
        mButtonBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numberOfTask = Integer.parseInt(mEditTextNumberOfTask.getText().toString());
                Intent intent = TaskListActivity.newIntent(getActivity(), mEditTextUsername.getText().toString(), numberOfTask);
                startActivity(intent);
            }
        });
    }

    private void findViews(View view) {
        mEditTextUsername = view.findViewById(R.id.txt_username);
        mEditTextNumberOfTask = view.findViewById(R.id.txt_numberoftask);
        mButtonBuild = view.findViewById(R.id.btn_build);
    }
}