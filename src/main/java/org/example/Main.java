package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String ANSI_ESCAPE = "\033[";
    private static final String COLOR_RED = "31m";
    private static final String COLOR_GREEN = "32m";
    private static final String RESET = "\033[0m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.print(
                    "Enter top limit int number (or 'q' to quit): ");
            userInput = sc.nextLine();

            long topLimit;
            try {
                if (userInput.equalsIgnoreCase("q") || userInput.equalsIgnoreCase("quit") ) {
                    break;
                }
                topLimit = Long.parseLong(userInput);
                if (topLimit < 1) {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException e) {
                System.out.println(ANSI_ESCAPE + COLOR_RED +
                                   "incorrect input! Must be positive integer!" +
                                   RESET
                );
                continue;

            }

            System.out.println("Below all perfect numbers below the limit: ");
            List<Long> perfectNumbers = getPerfectNumbersUpTo(topLimit);
            System.out.println(ANSI_ESCAPE + COLOR_GREEN + perfectNumbers + RESET);


        }

    }

    public static List<Long> getPerfectNumbersUpTo(Long limit)
            throws IllegalArgumentException {
        if (limit < 1) {
            throw new IllegalArgumentException();
        }

        List<Long> perfectNumbers = new ArrayList<>();
        long p = 2L;

        while (true) {
            try {
                long perfectNumber = getPerfectNumber(p);
                if (perfectNumber > limit) {
                    break;
                }
                perfectNumbers.add(perfectNumber);

            } catch (IllegalArgumentException ignored) {

            }
            p = nextPrime(p);
        }
        return perfectNumbers;
    }

    public static long getPerfectNumber(long p)
            throws IllegalArgumentException {
        if (p < 1) {
            throw new IllegalArgumentException("p must be positive integer!");
        }

        if (lucasLehmer(p)) {
            return (1L << (p - 1)) * getMersseneNumber(p);
        }

        throw new IllegalArgumentException(
                "Can't get perfect number, p didn't pass Lucas Lehmer test!");

    }

    public static boolean lucasLehmer(long p) throws IllegalArgumentException {
        if (p <= 1) {
            throw new IllegalArgumentException(
                    "p must be positive integer greater than 1!");
        }

        if (p == 2) {
            return true;
        }

        long M = getMersseneNumber(p);
        Long s = 4L;
        for (int i = 0; i < p - 2; i++) {
            s = (s * s - 2) % M;
        }
        return s == 0;
    }

    public static Long getMersseneNumber(long p)
            throws IllegalArgumentException {
        if (p < 1) {
            throw new IllegalArgumentException("p must be positive integer!");
        }

        return ((1L << p) - 1);
    }

    public static long nextPrime(long p) {
        long candidate = p+1;
        while (true) {
            if (isPrime(candidate)) {
                return candidate;
            }
            candidate += 1;
        }
    }

    public static boolean isPrime(long num) throws IllegalArgumentException {
        if (num <= 1) {
            throw new IllegalArgumentException(
                    "p must be positive integer higher than 1!");
        }

        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}