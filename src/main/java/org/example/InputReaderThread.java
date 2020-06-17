package org.example;

import java.util.Scanner;

public class InputReaderThread implements Runnable {
    @Override
    public void run() {
        System.out.println("You can exit the network at any time by typing something and pressing ENTER.");

        Scanner in = new Scanner(System.in);
        String input = null;

        do {
            input = in.next();
        } while (input == null);
    }
}
