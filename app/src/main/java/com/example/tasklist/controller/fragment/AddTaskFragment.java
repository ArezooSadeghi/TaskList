package com.example.tasklist.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.tasklist.R;
import com.example.tasklist.model.State;
import com.example.tasklist.model.Task;
import com.example.tasklist.repository.IRepository;
import com.example.tasklist.repository.TaskRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AddTaskFragment extends Fragment {

    private EditText mEditTextNameOfTask;
    private CheckBox mCheckBoxDone, mCheckBoxDoing, mCheckBoxTodo;
    private FloatingActionButton mFloatingActionButtonTik;
    private Task mTask;
    private IRepository mTaskRepository;
    private List<Task> mTasks;

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
        mFloatingActionButtonTik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTask = new Task();
                mTask.setName(mEditTextNameOfTask.getText().toString());

                if (mCheckBoxDone.isChecked())
                    mTask.setState(State.DONE);
                else if (mCheckBoxDoing.isChecked())
                    mTask.setState(State.DOING);
                else
                    mTask.setState(State.TODO);

                mTaskRepository = TaskRepository.getInstance();
                mTasks = mTaskRepository.getTasks();
                mTaskRepository.create(mTask);
            }
        });
    }

    private void findViews(View view) {
        mEditTextNameOfTask = view.findViewById(R.id.txt_nameoftaskadded);
        mCheckBoxDone = view.findViewById(R.id.chbox_done);
        mCheckBoxDoing = view.findViewById(R.id.chbox_doing);
        mCheckBoxTodo = view.findViewById(R.id.chbox_todo);
        mFloatingActionButtonTik = view.findViewById(R.id.fabtik);
    }
}