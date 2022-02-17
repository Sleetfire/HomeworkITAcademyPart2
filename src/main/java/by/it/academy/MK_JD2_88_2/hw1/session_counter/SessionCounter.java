package by.it.academy.MK_JD2_88_2.hw1.session_counter;

import by.it.academy.MK_JD2_88_2.hw1.session_counter.api.ISessionCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class SessionCounter implements ISessionCounter {

    private static final ISessionCounter instance = new SessionCounter();
    private AtomicInteger counter = new AtomicInteger(0);

    private SessionCounter() {
    }

    @Override
    public int getCounter() {
        return this.counter.get();
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
