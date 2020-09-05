package com.example.tasklist.model;

public enum State {
    DONE, DOING, TODO;

    public static State getRandomTaskState() {
        return values()[(int) (Math.random() * values().length)];
    }
}
