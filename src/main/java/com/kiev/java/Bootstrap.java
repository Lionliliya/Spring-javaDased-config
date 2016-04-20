package com.kiev.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Bootstrap {

    private TaskProvider<Integer> taskProvider;
    private ExecutorFactory executorFactory;

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Bootstrap bootstrap = context.getBean("bootstrap", Bootstrap.class);
        bootstrap.execute();
        bootstrap.execute();
    }

    public void execute() throws Exception {
        Executor<Integer> executor = executorFactory.getIntegerExecutor();
        taskProvider.getAllTasks().forEach(executor::addTask);
        executor.execute();

        System.out.println("Valid results");
        executor.getValidResults().forEach(System.out::println);
        System.out.println("Valid results");
        executor.getInvalidResults().forEach(System.out :: println);
    }

    public void setTaskProvider(TaskProvider<Integer> taskProvider) {
        this.taskProvider = taskProvider;
    }


    public void setExecutorFactory(ExecutorFactory executorFactory) {
        this.executorFactory = executorFactory;
    }
}
