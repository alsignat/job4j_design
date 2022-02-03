package ru.job4j.hash;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private int childrenCount;
    private Calendar birthday;

    public User(String name, int childrenCount, Calendar birthday) {
        this.name = name;
        this.childrenCount = childrenCount;
        this.birthday = birthday;
    }

    public User() {
        this.name = "Unknown";
        this.childrenCount = -1;
        this.birthday = new GregorianCalendar(1900, Calendar.JANUARY, 1);
    }

    public String getName() {
        return name;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 5
                + childrenCount
                + birthday.hashCode() * 11;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User another = (User) o;

        return this.birthday.equals(another.getBirthday())
                && this.childrenCount == another.getChildrenCount()
                && this.name.equals(another.getName());
    }

    public static void main(String[] args) {
        Calendar birthday = new GregorianCalendar(1980, Calendar.JULY, 31);
        User userOne = new User("Harry Potter", 2, birthday);
        User userTwo = new User("Harry Potter", 2, birthday);
        Object ob = new Object();
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, ob);
        map.put(userTwo, ob);
        System.out.println(map);
    }

}
