package com.example.tasklist.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tasklist.R;
import com.example.tasklist.controller.fragment.TaskListFragment;

public class TaskListActivity extends AppCompatActivity {

    public static final String EXTRA_USER_NAME = "com.example.tasklist.username";
    public static final String EXTRA_NUMBER_OF_TASK = "com.example.tasklist.numberoftask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        setTitle("Tasks");

        String userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        int numberOfTask = getIntent().getIntExtra(EXTRA_NUMBER_OF_TASK, 0);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.task_list_container);
        if (fragment == null) {
            TaskListFragment taskListFragment = TaskListFragment
                    .newInstance(userName, numberOfTask);
            fragmentManager.beginTransaction().add(R.id.task_list_container, taskListFragment)
                    .commit();
        }
    }

    public static Intent newIntent(Context context, String userName, int numberOfTask) {
        Intent intent = new Intent(context, TaskListActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_NUMBER_OF_TASK, numberOfTask);
        return intent;
    }
}
