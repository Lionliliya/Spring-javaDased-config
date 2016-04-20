package com.kiev.java;

import java.util.ArrayList;
import java.util.List;


public class ExecutorImpl<T> implements Executor<T> {

    private static final Validator<Object> DEFAULT_VALIDATOR = value -> true;

    private List<TaskAndValidator<T>> taskList = new ArrayList<>();
    private List<T> validResult = new ArrayList<>();
    private List<T> invalidResult = new ArrayList<>();

    @Override
    public void addTask(Task<? extends T> task) {
        addTask(task, DEFAULT_VALIDATOR);
    }

    @Override
    public void addTask(Task<? extends T> task, Validator<? super T> validator) {
        taskList.add(new TaskAndValidator<>(task, validator));

    }

    @Override
    public void execute() {
        for (TaskAndValidator<T> taskAndValidator : taskList) {
            Task<? extends T> task = taskAndValidator.task;
            task.execute();
            if (taskAndValidator.validator.isValid(task.getResult())) {
                validResult.add(task.getResult());
            } else {
                invalidResult.add(task.getResult());
            }
        }

    }

    @Override
    public List<T> getValidResults() throws Exception {
        return validResult;
    }

    @Override
    public List<T> getInvalidResults() throws Exception {
        return invalidResult;
    }

    private static class TaskAndValidator<T> {

        private Task<? extends T> task;
        private Validator<? super T> validator;

        public TaskAndValidator(Task<? extends T> task, Validator<? super T> validator) {
            this.task = task;
            this.validator = validator;
        }

    }
}