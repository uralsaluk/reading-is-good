package com.ural.readingisgood.orderservice.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class SpringAsyncModifier implements AsyncConfigurer {


    private DelegatedThreadPoolExecutor delegatedThreadPoolExecutor;

    @Autowired
    public SpringAsyncModifier(DelegatedThreadPoolExecutor delegatedThreadPoolExecutor) {
        this.delegatedThreadPoolExecutor = delegatedThreadPoolExecutor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return delegatedThreadPoolExecutor;
    }

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster =
                new SimpleApplicationEventMulticaster();

        eventMulticaster.setTaskExecutor(delegatedThreadPoolExecutor);
        return eventMulticaster;
    }


}
