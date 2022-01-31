package ru.job4j.generics;

public class Animal {

    private int age;
    private int weight;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "age=" + age
                + ", weight=" + weight
                + '}';
    }
}
