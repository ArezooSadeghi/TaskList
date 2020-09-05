package com.example.tasklist.repository;

import com.example.tasklist.controller.fragment.TaskListFragment;
import com.example.tasklist.model.State;
import com.example.tasklist.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository sInstance;
    private List<Task> mTasks;
    private static String mName;
    private static int mNumber;

    public static String getmName() {
        return mName;
    }

    public static void setmName(String mName) {
        TaskRepository.mName = mName;
    }

    public static int getmNumber() {
        return mNumber;
    }

    public static void setmNumber(int mNumber) {
        TaskRepository.mNumber = mNumber;
    }

    private TaskRepository() {
        mTasks = new ArrayList<>();
        for (int i = 0; i < getmNumber(); i++) {
            Task task = new Task();
            task.setName(getmName());
            State state = State.getRandomTaskState();
            if (state == State.DONE) {
                task.setDone(true);
            } else if (state == State.DOING) {
                task.setDoing(true);
            } else {
                task.setTodo(true);
            }
            task.setState(state);
            mTasks.add(task);
        }
    }

    public static TaskRepository getInstance() {
        if (sInstance == null) {
            sInstance = new TaskRepository();
        }
        return sInstance;
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public void setTasks(List<Task> tasks) {
        mTasks = tasks;
    }

    public static void setUsername(String name) {


    }

    public void create(Task task) {
        mTasks.add(task);
    }

    public Task read(UUID id) {
        for (Task task : mTasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void update(Task task) {
        //todo
    }

    public void delete(Task task) {
        mTasks.remove(task);
    }

}
