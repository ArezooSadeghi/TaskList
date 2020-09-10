package com.example.tasklist.model;

import java.util.UUID;

public class Task {
    private String mName;
    private State mState;
    private UUID mId;

    public Task() {
        mId = UUID.randomUUID();
    }

    public String getName() {
        return mName;
    }

    public State getState() {
        return mState;
    }

    public UUID getId() {
        return mId;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setState(State state) {
        mState = state;
    }
}
