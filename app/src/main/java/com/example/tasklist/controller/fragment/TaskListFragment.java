package com.example.tasklist.controller.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.tasklist.R;
import com.example.tasklist.controller.activity.AddTaskActivity;
import com.example.tasklist.model.State;
import com.example.tasklist.model.Task;
import com.example.tasklist.repository.IRepository;
import com.example.tasklist.repository.TaskRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.IllegalFormatCodePointException;
import java.util.List;

public class TaskListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private IRepository mTaskRepository;
    private TaskAdapter mTaskAdapter;
    private FloatingActionButton mActionButtonAddNewTask;
    public static final String ARGS_USER_NAME = "username";
    public static final String ARGS_NUMBER_OF_TASK = "numberoftask";

    public TaskListFragment() {
    }

    public static TaskListFragment newInstance(String userName, int numberOfTask) {
        Bundle args = new Bundle();
        args.putString(ARGS_USER_NAME, userName);
        args.putInt(ARGS_NUMBER_OF_TASK, numberOfTask);
        TaskListFragment taskListFragment = new TaskListFragment();
        taskListFragment.setArguments(args);
        return taskListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userName = getArguments().getString(ARGS_USER_NAME);
        int numberOfTask = getArguments().getInt(ARGS_NUMBER_OF_TASK);
        TaskRepository.setUserName(userName);
        TaskRepository.setNumberOfTask(numberOfTask);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        findViews(view);
        initViews();
        setListeners();
        return view;
    }

    private void setListeners() {
        mActionButtonAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddTaskActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        mTaskRepository = TaskRepository.getInstance();
        List<Task> tasks = mTaskRepository.getTasks();
        if (mTaskAdapter == null) {
            mTaskAdapter = new TaskAdapter(tasks);
            mRecyclerView.setAdapter(mTaskAdapter);
        } else {
            mTaskAdapter.notifyDataSetChanged();
        }
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.tasklist_recyclerview);
        mActionButtonAddNewTask = view.findViewById(R.id.fabadd);
    }

    public class TaskHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewTaskName;
        private CheckBox mCheckBoxDone, mCheckBoxDoing, mCheckBoxTodo;
        private Task mTask;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTaskName = itemView.findViewById(R.id.txt_row_taskname);
            mCheckBoxDone = itemView.findViewById(R.id.checkbox_done);
            mCheckBoxDoing = itemView.findViewById(R.id.checkbox_doing);
            mCheckBoxTodo = itemView.findViewById(R.id.checkbox_todo);
        }

        public void bindTask(Task task) {
            mTask = task;
            mTextViewTaskName.setText(task.getName());
            State state = mTask.getState();
            switch (state) {
                case DONE:
                    mCheckBoxDone.setChecked(true);
                    break;
                case DOING:
                    mCheckBoxDoing.setChecked(true);
                    break;
                default:
                    mCheckBoxTodo.setChecked(true);
                    break;
            }
        }
    }

    public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> mTasks;

        public TaskAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        public List<Task> getTasks() {
            return mTasks;
        }

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }


        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.row_task_list, parent, false);
            TaskHolder taskHolder = new TaskHolder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = mTasks.get(position);
            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#FF69B4"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#FFC0CB"));
            }
            holder.bindTask(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }


}