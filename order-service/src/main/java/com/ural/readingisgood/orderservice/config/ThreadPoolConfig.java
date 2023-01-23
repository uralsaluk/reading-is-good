package com.ural.readingisgood.orderservice.config;


import com.ural.readingisgood.orderservice.util.ContextUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolderStrategy;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@ConfigurationProperties(
        prefix = "order-service.application.threadpool"
)
@Data
public class ThreadPoolConfig {

    private String name;
    private Integer corePoolSize;
    private Integer maximumPoolSize;
    private Integer keepAliveTime;
    private Long maxWaitTime;
    private Integer queueSize;
    private Boolean daemonThreads = Boolean.FALSE;
    private SecurityContextHolderStrategy securityContext;

    @Autowired
    public ThreadPoolConfig() {

        this.securityContext = ContextUtil.getContextStrategy();
    }

    @Bean
    public DelegatedThreadPoolExecutor getThreadPoolExecutor() {
        ThreadFactory threadFactory = (new ThreadFactoryBuilder(this.name, this.daemonThreads));

        RejectedExecutionHandler rejectedExecutionHandler = getRejectedExecutionHandler();

        return new DelegatedThreadPoolExecutor(this.corePoolSize,
                this.maximumPoolSize,
                this.keepAliveTime,
                this.queueSize,
                threadFactory,
                rejectedExecutionHandler,
                this.maxWaitTime,
                this.securityContext);


    }


    private RejectedExecutionHandler getRejectedExecutionHandler() {

        return new ThreadPoolExecutor.AbortPolicy();

    }

}




