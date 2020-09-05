package com.example.tasklist.model;

import java.util.UUID;

public class Task {
    private String mName;
    private State mState;
    private boolean mIsDone;
    private boolean mIsDoing;
    private boolean mIsTodo;
    private UUID mId;

    public Task() {
        mId = UUID.randomUUID();
    }

    public Task(String name, State state) {
        mName = name;
        mState = state;
        mId = UUID.randomUUID();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        mState = state;
    }

    public UUID getId() {
        return mId;
    }

    public boolean isDone() {
        return mIsDone;
    }

    public void setDone(boolean done) {
        mIsDone = done;
    }

    public boolean isDoing() {
        return mIsDoing;
    }

    public void setDoing(boolean doing) {
        mIsDoing = doing;
    }

    public boolean isTodo() {
        return mIsTodo;
    }

    public void setTodo(boolean todo) {
        mIsTodo = todo;
    }
}
