package com.example.tasklist.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.tasklist.R;
import com.example.tasklist.controller.fragment.TaskInformationFragment;

public class TaskInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskinformation);
        setTitle("TaskInformation");

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.task_information_container);
        if (fragment == null) {
            TaskInformationFragment taskListFragment = new TaskInformationFragment();
            fragmentManager.beginTransaction().add(R.id.task_information_container, taskListFragment).commit();
        }
    }
}