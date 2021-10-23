package com.company.utilities;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt(String prompt, int min, int max) {
        int output;

        do {
            System.out.println(prompt);

            String input = scanner.nextLine();

            try {
                output = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Invalid entry, try again\n");

                output = min - 1;

            }
        } while (output <= min && output >= max);

        return output;
    }

    public static String getString(String prompt, boolean isRequired) {
        String output = "";

        System.out.println(prompt);

        output = scanner.nextLine();

        if (output.equals("") && isRequired) {
            System.out.println("Input is required to proceed");
            getString(prompt, true);
        }

        return output;
    }

}
