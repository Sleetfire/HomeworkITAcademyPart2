package by.it.academy.MK_JD2_88_2.hw1.controllers.mains;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.ChoiceFactoryStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.HibernateAuditUserStorage;

import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.HibernateUserMessageAuditDecorator;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.HibernateUserStorage;

import java.time.LocalDate;
import java.util.List;

public class UserMain {
    public static void main(String[] args) {

//        IUserStorage storage = new ChoiceFactoryStorage().getUserStorage();
//        User user = User.Builder.createBuilder()
//                .setLogin("login1")
//                .setPassword("login")
//                .setName("boris")
//                .setBirthday(LocalDate.now())
//                .setRgDate(LocalDate.now())
//                .build();
//        storage.add(user);

//        List<User> users = storage.getAll();
//        for (User user1 : users) {
//            System.out.println(user1.toString());
//        }
//
       // System.out.println(storage.getCount());
//
//        System.out.println(storage.get("login"));
//
        //storage.delete("login");


    }

}
