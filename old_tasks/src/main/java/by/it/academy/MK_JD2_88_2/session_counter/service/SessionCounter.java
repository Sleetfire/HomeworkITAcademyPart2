package by.it.academy.MK_JD2_88_2.session_counter.service;

import by.it.academy.MK_JD2_88_2.session_counter.service.api.ISessionCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class SessionCounter implements ISessionCounter {

    private static ISessionCounter instance = new SessionCounter();
    private AtomicInteger counter = new AtomicInteger(0);

    private SessionCounter() {
    }

    @Override
    public AtomicInteger getCounter() {
        return this.counter;
    }

    @Override
    public void doIncrementCounter() {
       this.counter.incrementAndGet();
    }

    @Override
    public void doDecrementCounter() {
        this.counter.decrementAndGet();
    }

    public static ISessionCounter getInstance() {
        return instance;
    }

}
