package by.it.academy.MK_JD2_88_2.session_counter.controllers.web.listeners;

import by.it.academy.MK_JD2_88_2.session_counter.service.SessionCounter;
import by.it.academy.MK_JD2_88_2.session_counter.service.api.ISessionCounter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CreateDestroySessionListener implements HttpSessionListener {

    private ISessionCounter counter = SessionCounter.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        this.counter.doIncrementCounter();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        this.counter.doDecrementCounter();
    }
}
