package com.ft.test;

public class Food {
    private String name;
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return name+","+color;
    }
    public void show() {
        System.out.println(name + ", " + color);
    }
}
