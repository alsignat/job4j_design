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

    @SuppressWarnings("checkstyle:EqualsHashCode")
    @Override
    public int hashCode() {
        return name.hashCode() * 5
                + childrenCount
                + birthday.hashCode() * 11;
    }

    public static void main(String[] args) {
        Calendar birthday = new GregorianCalendar(1980, Calendar.JULY, 31);
        User userOne = new User("Harry Potter", 2, birthday);
        User userTwo = new User("Harry Potter", 2, birthday);
        Map<User, Object> map = new HashMap<>(Map.of(userOne, new Object(), userTwo, new Object()));
        System.out.println(map);
        int indexOne = userOne.hashCode() & 15;
        int indexTwo = userTwo.hashCode() & 15;
        System.out.println("bucket index of (userOne, obj1): " + indexOne);
        System.out.println("bucket index of (userTwo, obj2): " + indexTwo);
    }

}
