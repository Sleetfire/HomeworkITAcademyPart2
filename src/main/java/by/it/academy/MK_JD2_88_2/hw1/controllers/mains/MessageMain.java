package by.it.academy.MK_JD2_88_2.hw1.controllers.mains;

import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.HibernateMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.IHibernateMessageStorage;

public class MessageMain {
    public static void main(String[] args) {

        IHibernateMessageStorage storage = HibernateMessageStorage.getInstance();

        storage.getBySenderLogin("1").forEach(System.out::println);


    }
}
