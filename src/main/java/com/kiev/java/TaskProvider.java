package com.kiev.java;


import java.util.List;


public interface TaskProvider<T> {

    List<Task<T>> getAllTasks();
}
