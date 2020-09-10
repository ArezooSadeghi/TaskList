package com.example.tasklist.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.tasklist.R;
import com.example.tasklist.model.State;
import com.example.tasklist.model.Task;
import com.example.tasklist.repository.IRepository;
import com.example.tasklist.repository.TaskRepository;

import java.util.List;

public class AddTaskFragment extends Fragment {

    private EditText mEditTextNameOfTask;
    private CheckBox mCheckBoxDone, mCheckBoxDoing, mCheckBoxTodo;
    private Task mTask = new Task();
    private IRepository mTaskRepository;

    public AddTaskFragment() {
    }

    public static AddTaskFragment newInstance() {
        AddTaskFragment addTaskFragment = new AddTaskFragment();
        Bundle args = new Bundle();
        addTaskFragment.setArguments(args);
        return addTaskFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        findViews(view);
        setListeners();
        return view;
    }

    private void setListeners() {
        mEditTextNameOfTask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTask.setName(mEditTextNameOfTask.getText().toString());
            }
        });

        mCheckBoxDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mTask.setState(State.DONE);
            }
        });

        mCheckBoxDoing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mTask.setState(State.DOING);
            }
        });

        mCheckBoxTodo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mTask.setState(State.TODO);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mTaskRepository = TaskRepository.getInstance();
        mTaskRepository.create(mTask);
    }

    private void findViews(View view) {
        mEditTextNameOfTask = view.findViewById(R.id.txt_nameoftaskadded);
        mCheckBoxDone = view.findViewById(R.id.chbox_done);
        mCheckBoxDoing = view.findViewById(R.id.chbox_doing);
        mCheckBoxTodo = view.findViewById(R.id.chbox_todo);
    }
}