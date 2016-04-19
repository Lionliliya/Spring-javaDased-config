package com.kiev.java;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap {

    private TaskProvider<Integer> taskProvider;
    private ObjectFactory<Executor<Integer>> executorFactory;

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Bootstrap bootstrap = context.getBean("bootstrap", Bootstrap.class);
        bootstrap.execute();
        bootstrap.execute();
    }

    public void execute() throws Exception {
        Executor<Integer> executor = executorFactory.getObject();
        taskProvider.getAllTasks().forEach(executor::addTask);
        executor.execute();

        System.out.println("Valid results");
        executor.getValidResults().forEach(System.out::println);
        System.out.println("Valid results");
        executor.getInvalidResults().forEach(System.out :: println);
    }
    @Autowired
    public void setTaskProvider(TaskProvider<Integer> taskProvider) {
        this.taskProvider = taskProvider;
    }

    @Autowired
    public void setExecutorFactory(ObjectFactory<Executor<Integer>> executorFactory) {
        this.executorFactory = executorFactory;
    }
}
