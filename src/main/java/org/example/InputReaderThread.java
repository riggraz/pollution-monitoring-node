package org.example;

import java.util.Scanner;

public class InputReaderThread implements Runnable {
    @Override
    public void run() {
        System.out.println("You can exit the network at any time by typing 0");

        Scanner in = new Scanner(System.in);
        int input = 0;

        do {
            input = in.nextInt();
        } while (input != 0);
    }
}
