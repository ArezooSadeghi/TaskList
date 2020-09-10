package com.example.tasklist.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.tasklist.R;
import com.example.tasklist.controller.fragment.AddTaskFragment;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.start_page_container);
        if (fragment == null) {
            AddTaskFragment addTaskFragment = AddTaskFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.start_page_container, addTaskFragment)
                    .commit();
        }
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AddTaskActivity.class);
        return intent;
    }
}