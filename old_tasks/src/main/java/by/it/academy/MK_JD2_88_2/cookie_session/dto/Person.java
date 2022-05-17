package by.it.academy.MK_JD2_88_2.cookie_session.dto;

import java.util.Objects;

public class Person {

    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person() {

    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return this.age == person.age && Objects.equals(this.firstName, person.firstName) && Objects.equals(this.lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        int result = 31 * this.firstName.hashCode();
        result += 31 * this.lastName.hashCode();
        result += 31 * this.age;
        return result;
    }

    @Override
    public String toString() {
        return "Имя: " + this.firstName + ". Фамилия: " + this.lastName + ". Возраст: " + this.age;
    }
}
