package com.kiev.java;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskProvider<T> {

    List<Task<T>> getAllTasks();
}
