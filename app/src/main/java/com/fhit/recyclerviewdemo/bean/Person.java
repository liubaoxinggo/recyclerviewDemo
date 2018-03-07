package com.fhit.recyclerviewdemo.bean;

/**
 * Created by liubaoxing on 2018/3/7 10:55<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
