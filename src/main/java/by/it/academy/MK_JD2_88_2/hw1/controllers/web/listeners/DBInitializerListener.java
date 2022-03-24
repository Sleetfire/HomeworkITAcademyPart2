package by.it.academy.MK_JD2_88_2.hw1.controllers.web.listeners;

import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.MK_JD2_88_2.hw1.storage.sql.api.DBInitializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBInitializerListener implements ServletContextListener {

    private final HibernateDBInitializer hibernateDBInitializer = HibernateDBInitializer.getInstance();
    private final DBInitializer initializer = DBInitializer.getInstance();


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            this.hibernateDBInitializer.close();
            this.initializer.close();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка закрытия инициализатора hibernate", e);
        }
    }
}
