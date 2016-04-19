package com.kiev.java;

public interface Task<T> {

    void execute();

    T getResult();
}
