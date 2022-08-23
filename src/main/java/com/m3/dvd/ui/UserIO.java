package com.m3.dvd.ui;

import java.time.LocalDate;

public interface UserIO {
    void print(String msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    String readString(String prompt);

    LocalDate readDate(String prompt);
}