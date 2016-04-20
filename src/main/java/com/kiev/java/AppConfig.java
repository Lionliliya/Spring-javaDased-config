package com.kiev.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    public Bootstrap bootstrap(ExecutorFactory executorFactory, TaskProvider<Integer> integerTaskProvider) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setTaskProvider(integerTaskProvider);
        bootstrap.setExecutorFactory(executorFactory);
        return bootstrap;
    }

    @Bean
    public TaskProvider<Integer> taskProvider() {
        IntegerTaskProvided integerTaskProvided = new IntegerTaskProvided();
        integerTaskProvided.init();
        return integerTaskProvided;
    }

    @Bean
    @Scope("prototype")
    public ExecutorImpl<Integer> executor() {
        return new ExecutorImpl<>();
    }

    @Bean
    public ExecutorFactory executorFactory() {
        return new ExecutorFactory() {
            @Override
            public Executor<Integer> getIntegerExecutor() {
                return executor();
            }
        };
    }
}
