package com.ligstd.homework;

import com.ligstd.homework.controllers.MainController;

import java.io.IOException;
/**
 * @author blackgreymon
 * Main Class
 */
public final class Main {
    /**
     * Main entry method
     * @param args Startup arguments
     * @throws IOException throws All Exceptions
     */
    public static void main(final String[]  args) throws IOException {
       MainController mainController =
                  new MainController(System.in, System.out);
        while (true) {
            try {
                mainController.AcquireInput();
            } catch (ArithmeticException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
