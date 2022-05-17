package by.it.academy.MK_JD2_88_2.polls.dto;

import java.util.Objects;

public class ChoiceWithCounter<T> implements Comparable<ChoiceWithCounter<?>>{

    private T choice;
    private int countPoll;

    public ChoiceWithCounter(T choice, int countPoll) {
        this.choice = choice;
        this.countPoll = countPoll;
    }

    public T getChoice() {
        return choice;
    }

    public void setChoice(T choice) {
        this.choice = choice;
    }

    public int getCountPoll() {
        return countPoll;
    }

    public void setCountPoll(int countPoll) {
        this.countPoll = countPoll;
    }

    @Override
    public int compareTo(ChoiceWithCounter<?> o) {
        return this.countPoll - o.getCountPoll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoiceWithCounter<?> that = (ChoiceWithCounter<?>) o;
        return countPoll == that.countPoll && Objects.equals(choice, that.choice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(choice, countPoll);
    }

    @Override
    public String toString() {
        return "ChoiceWithCounter{" +
                "choice=" + choice +
                ", countPoll=" + countPoll +
                '}';
    }
}
