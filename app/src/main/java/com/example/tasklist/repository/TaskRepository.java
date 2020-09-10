package com.example.tasklist.repository;

import com.example.tasklist.model.State;
import com.example.tasklist.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository implements IRepository {
    private static TaskRepository sInstance;
    private List<Task> mTasks;

    private TaskRepository(String userName, int numberOfTask) {
        mTasks = new ArrayList<>();
        for (int i = 0; i < numberOfTask; i++) {
            Task task = new Task();
            task.setName(userName);
            task.setState(State.getRandomTaskState());
            mTasks.add(task);
        }
    }

    public static TaskRepository getInstance(String userName, int numberOfTask) {
        if (sInstance == null) {
            sInstance = new TaskRepository(userName, numberOfTask);
        }
        return sInstance;
    }

    public List<Task> getTasks() {
        return mTasks;
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
