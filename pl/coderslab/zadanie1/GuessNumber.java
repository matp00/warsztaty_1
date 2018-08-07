package pl.coderslab.zadanie1;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {


    public static int getNumber(String message, String errorMessage) {
        Scanner scan = new Scanner(System.in);
        System.out.println(message);
        while (!scan.hasNextInt()) {
            System.out.println(errorMessage);
            System.out.println(message);
            scan.next();
        }
        return scan.nextInt();
    }

    public static int randNumber(int range) {
        Random random = new Random();
        return random.nextInt(range + 1);
    }

    public static void main(String[] args) {
        //System.out.println(getNumber("Zgadnij liczbę", "To nie jest liczba"));

        int computerNumber = randNumber(100);
        System.out.println(computerNumber);
        int userNumber = 0;

        do {
            userNumber = getNumber("Zgadnij liczbę", "To nie jest liczba");
            if (userNumber > computerNumber) {
                System.out.println("Za dużo!");
            } else if (userNumber < computerNumber) {
                System.out.println("Za mało!");
            }
        } while (userNumber!=computerNumber);
        System.out.println("Zgadłeś!");
    }

}



