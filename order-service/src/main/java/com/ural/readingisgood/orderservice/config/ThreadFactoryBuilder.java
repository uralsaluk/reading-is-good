package com.ural.readingisgood.orderservice.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryBuilder implements ThreadFactory
{
    private int          counter;
    private String       name;
    private List<String> stats;

    private Boolean daemon;

    public ThreadFactoryBuilder(String name,Boolean daemon)
    {
        counter = 1;
        this.name = name;
        stats = new ArrayList<String>();
        this.daemon=daemon;
    }

    @Override
    public Thread newThread(Runnable runnable)
    {
        Thread t = new Thread(runnable, name + "-Thread_" + counter);
        t.setDaemon(this.daemon);
        counter++;
        stats.add(String.format("Created thread %d with name %s on %s \n", t.getId(), t.getName(), new Date()));
        return t;
    }

    public String getStats()
    {
        StringBuffer buffer = new StringBuffer();
        Iterator<String> it = stats.iterator();
        while (it.hasNext())
        {
            buffer.append(it.next());
        }
        return buffer.toString();
    }
}