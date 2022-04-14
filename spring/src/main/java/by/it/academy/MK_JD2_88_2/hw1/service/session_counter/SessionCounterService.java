package by.it.academy.MK_JD2_88_2.hw1.service.session_counter;

import by.it.academy.MK_JD2_88_2.hw1.service.session_counter.api.ISessionCounterService;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SessionCounterService implements ISessionCounterService {

    private AtomicInteger counter = new AtomicInteger(0);

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

}
