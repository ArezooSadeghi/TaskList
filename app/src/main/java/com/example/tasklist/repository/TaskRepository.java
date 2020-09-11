package com.example.tasklist.repository;

import com.example.tasklist.model.State;
import com.example.tasklist.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository implements IRepository {
    private static TaskRepository sInstance;
    private static String sUserName;
    private static int sNumberOfTask;
    private List<Task> mTasks;

    private TaskRepository() {
        mTasks = new ArrayList<>();
        for (int i = 0; i < sNumberOfTask; i++) {
            Task task = new Task();
            task.setName(sUserName);
            task.setState(State.getRandomTaskState());
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

    public static String getUserName() {
        return sUserName;
    }

    public static int getNumberOfTask() {
        return sNumberOfTask;
    }

    public static void setUserName(String userName) {
        sUserName = userName;
    }

    public static void setNumberOfTask(int numberOfTask) {
        sNumberOfTask = numberOfTask;
    }

    public void setTasks(List<Task> tasks) {
        mTasks = tasks;
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
        Task findTask = read(task.getId());
        findTask.setName(task.getName());
        findTask.setState(task.getState());
    }

    public void delete(Task task) {
        for (int i = 0; i < mTasks.size(); i++) {
            if (mTasks.get(i).getId().equals(task.getId())) {
                mTasks.remove(task);
                return;
            }
        }
    }
}
