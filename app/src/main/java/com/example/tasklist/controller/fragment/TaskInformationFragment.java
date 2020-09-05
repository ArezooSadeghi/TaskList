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

public class TaskInformationFragment extends Fragment {

    private EditText mEditTextUsername, mEditTextNumberOfTask;

    private Button mButtonBuild;

    public static final String EXTRA_USERNAME = "com.example.tasklist.controller.fragment.EXTRA_USERNAME";
    public static final String EXTRA_NUMBER_OF_TASK = "com.example.tasklist.controller.fragment.EXTRA_NUMBER_OF_TASK";

    public TaskInformationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_information, container, false);
        findViews(view);

        setListeners();

        return view;
    }

    private void setListeners() {
        mButtonBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskRepository.setmName(mEditTextUsername.getText().toString());
                TaskRepository.setmNumber(Integer.parseInt(mEditTextNumberOfTask.getText().toString()));
                Intent intent = new Intent(getActivity(), TaskListActivity.class);
                intent.putExtra(EXTRA_USERNAME, mEditTextUsername.getText().toString());
                intent.putExtra(EXTRA_NUMBER_OF_TASK, mEditTextNumberOfTask.getText().toString());
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