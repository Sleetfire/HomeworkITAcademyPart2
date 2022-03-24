package by.it.academy.MK_JD2_88_2.hw1.service.session_counter;

import by.it.academy.MK_JD2_88_2.hw1.service.session_counter.api.ISessionCounterService;

import java.util.concurrent.atomic.AtomicInteger;

public class SessionCounterService implements ISessionCounterService {

    private static final ISessionCounterService instance = new SessionCounterService();
    private AtomicInteger counter = new AtomicInteger(0);

    private SessionCounterService() {
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

    public static ISessionCounterService getInstance() {
        return instance;
    }

}
