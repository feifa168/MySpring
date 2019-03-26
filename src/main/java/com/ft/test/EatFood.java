package com.ft.test;

public class EatFood {
    private Food fd;

    public EatFood() {}
    public EatFood(Food fd) {
        setFd(fd);
    }
    public Food getFd() {
        return fd;
    }
    public void setFd(Food fd) {
        this.fd = fd;
    }
}
