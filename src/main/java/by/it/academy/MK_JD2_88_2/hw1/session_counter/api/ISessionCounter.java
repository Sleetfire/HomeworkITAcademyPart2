package by.it.academy.MK_JD2_88_2.hw1.session_counter.api;

public interface ISessionCounter {

    /**
     * Increments session's counter
     */
    void doIncrementCounter();

    /**
     * Decrements session's counter
     */
    void doDecrementCounter();

    /**
     * Returns the number of the session
     * @return number of session
     */
    int getCounter();
}
