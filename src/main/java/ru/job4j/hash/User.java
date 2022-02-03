package ru.job4j.hash;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

}
