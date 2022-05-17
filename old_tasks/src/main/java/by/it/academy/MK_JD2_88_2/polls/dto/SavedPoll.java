package by.it.academy.MK_JD2_88_2.polls.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SavedPoll implements Comparable<SavedPoll>, Serializable {

    private final LocalDateTime time;
    private final Poll pool;

    public SavedPoll(LocalDateTime time, Poll pool) {
        this.time = time;
        this.pool = pool;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Poll getPool() {
        return pool;
    }

    @Override
    public int compareTo(SavedPoll o) {
        return this.time.compareTo(o.time);
    }
}
