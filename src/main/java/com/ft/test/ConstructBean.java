package com.ft.test;

public class ConstructBean {
    private Food fd;
    private String name;

    public ConstructBean() {
    }

    public ConstructBean(Food fd1, String s1) {
        fd = fd1;
        name = s1;
    }
    public Food getFd() {
        return fd;
    }

    public void setFd(Food fd) {
        this.fd = fd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name+", "+fd.toString();
    }
}
