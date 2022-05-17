package by.it.academy.MK_JD2_88_2.hw1.session_counter.api;

import java.util.concurrent.atomic.AtomicInteger;

public interface ISessionCounter {

    void doIncrementCounter();

    void doDecrementCounter();

    int getCounter();

}
