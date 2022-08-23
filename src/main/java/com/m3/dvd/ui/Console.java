package com.m3.dvd.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Console implements UserIO{
    final private Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Input was not a number please write a number. \n Example: 1.0");
            }
        }
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double result;
        do {
            result = readDouble(prompt);
        } while (result < min || result > max);
        return result;
    }

    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                String stringValue = this.readString(prompt);
                num = Integer.parseInt(stringValue);
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("Input was not an integer please write a whole number. \n Example: 1");
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int result;
        do {
        result = readInt(prompt);
        } while (result < min || result > max);
        return result;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public LocalDate readDate(String prompt){
        while(true){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(readString(prompt), formatter);
                return date;
            } catch (Exception e) {
                System.out.println("Provided date could not be parsed properly.");
            }
        }
    }
}
