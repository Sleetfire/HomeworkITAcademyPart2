package by.it.academy.MK_JD2_88_2.session_counter.service.api;

import java.util.concurrent.atomic.AtomicInteger;

public interface ISessionCounter {

    void doIncrementCounter();

    void doDecrementCounter();

    AtomicInteger getCounter();

}
