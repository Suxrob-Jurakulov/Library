package com.company;

import java.util.Scanner;


public class Main {
    static void recur(int d) {
        if (d <= 100) {
            System.out.println(d);
            recur(++d);
            System.out.println(d);
        }
    }
    public static void main(String[] args) {

        recur(99);
    }
}