package com.vsushko;

public class LoggerService {

    public void logToOut(String s) {
        System.out.println(s);
    }

    public void logToErr(String s) {
        System.err.println(s);
    }

    public void log(String s) {
        System.out.println(s);
    }
}
