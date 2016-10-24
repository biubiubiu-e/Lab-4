package com.ligstd.homework;

import com.ligstd.homework.controllers.MainController;

import java.io.IOException;
/**
 *
 * @author
 *
 */
public final class Main {
    /**
     *
     */
    private Main() { }
    /**
     *
     * @param args ??
     * @throws IOException throws exception
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
