package com.example.tasklist.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.tasklist.R;
import com.example.tasklist.controller.fragment.TaskListFragment;

public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        setTitle("TaskList");

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.task_list_container);
        if (fragment == null) {
            TaskListFragment taskListFragment = new TaskListFragment();
            fragmentManager.beginTransaction().add(R.id.task_list_container, taskListFragment).commit();
        }
    }
}