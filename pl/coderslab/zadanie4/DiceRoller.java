package pl.coderslab.zadanie4;

import java.util.Random;
import java.util.Scanner;

public class DiceRoller {
    public static void main(String[] args) {
        diceResult();
    }

    public static String userInfo() {
        Scanner scan = new Scanner(System.in);
        String info = "";

        System.out.println("Podaj jaki rzut kością mam zasymulować");
        info = scan.nextLine().toUpperCase();
        return info;
    }

    /**
     * poniższa metoda zbiera informacje pobrane od użytkownika w formacie:
     * xDy+z
     * gdzie:
     *
     * y – rodzaj kostek, których należy użyć (np. D6, D10),
     * x – liczba rzutów kośćmi (jeśli rzucamy raz, ten parametr jest pomijalny),
     * z – (opcjonalnie) liczba, którą należy dodać (lub odjąć) do wyniku rzutów.
     * i zamienia je na tablicę liczb gdzie y - indeks[0], x - indeks[1], z - indeks[2];
     */

    public static int[] diceInfo() {
        String userAnswer = userInfo();
        String[] diceArray = userAnswer.split("D"); // diceArray[0] - y
        String counter = diceArray[1].toString();
        String[] diceArray2 = null;

        if (counter.contains("+")) {
            diceArray2 = counter.split("[+]"); //  diceArray2[0] - x
            // diceArray2[1] - z
        } else if (counter.contains("-")) {
            diceArray2 = counter.split("[-]");
            String temp = "-" + diceArray2[1];
            diceArray2[1] = temp;
        } else {                                         //  gdy z = 0
            diceArray2 = new String[2];
            diceArray2[0] = diceArray[1];
            diceArray2[1] = "";
        }

        int[] diceParameters = new int[3];

        try {
            if (!diceArray[0].isEmpty()) {                        // jeżeli użytkownik wpisał ilość rzutów to przypisz tę ilość do indeksu 0
                diceParameters[0] = Integer.parseInt(diceArray[0]);
            }

            diceParameters[1] = Integer.parseInt(diceArray2[0]);  // przypisz rodzaj kości do indeksu 1

            if (!diceArray2[1].isEmpty()) {                         // jeżeli użytkownik kazał dodać wartość do rzutu to przypisz do indeksu 2
                diceParameters[2] = Integer.parseInt(diceArray2[1]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wpisałeś zły format kości");
        }
        return diceParameters;
    }

    /**
     * poniższa metoda sprawdza czy rodzaj kości jest obsługiwany przez symulator rzutów kości
     * obsługiwane kości to: D3, D4, D6, D8, D10, D12, D20, D100
     */
    public static int[] diceCheck() {
        int[] diceToCheck = diceInfo();

        while (diceToCheck[1] != 3 && diceToCheck[1] != 4 && diceToCheck[1] != 6 && diceToCheck[1] != 8
                && diceToCheck[1] != 10 && diceToCheck[1] != 12 && diceToCheck[1] != 20 && diceToCheck[1] != 100) {
            System.out.println("Nie ma takiej kości");
            diceToCheck = diceInfo();
        }
        return diceToCheck;
    }


    /**
     * poniższa metoda sumuluje rzuty kością i w przypadku wykonania więcej niż 1 rzutu podaje informację o sumie wyników
     */
    public static void diceResult() {
        int[] diceParameters = diceCheck();
        int sumOfResults = 0;
        Random rand = new Random();
        int diceDots = diceParameters[1];
        int diceRandom = 0;
        if (diceParameters[2] != 0) {                                // sumulacja rzutów z modyfikacją wyniku
            diceRandom = rand.nextInt(diceDots) + 1;
            System.out.println("Wyrzuciłeś " + diceRandom);
            sumOfResults = diceRandom;
            if (diceParameters[0]==0) {                              // jeżeli rzut wykonuje się 1 raz to wyświetl
                System.out.println("Wynik po modyfikacji " + diceParameters[2] + " równa się " + sumOfResults);
            }

            if (diceParameters[0] != 0) {                         // wykonuje dodatkowe rzuty
                for (int i = 1; i < diceParameters[0]; i++) {
                    diceRandom = rand.nextInt(diceDots) + 1;
                    System.out.println("Wyrzuciłeś " + diceRandom);
                    sumOfResults = sumOfResults + +diceRandom + diceParameters[2];
                    if (i==diceParameters[0]-1) {               // po ostatnim rzucie wyświetl wiadomość
                        System.out.println("Suma wyników po modyfikacji " + diceParameters[2] + " równa się " + sumOfResults);
                    }
                }
            }

        } else {                                            // symulacja rzutów bez modyfikacji wyniku
            diceRandom = rand.nextInt(diceDots) + 1;
            System.out.println("Wyrzuciłeś " + diceRandom);
            sumOfResults = diceRandom;

            if (diceParameters[0] != 0) {                     // wykonuje dodatkowe rzuty
                for (int i = 1; i < diceParameters[0]; i++) {
                    diceRandom = rand.nextInt(diceDots) + 1;
                    System.out.println("Wyrzuciłeś " + diceRandom);
                    sumOfResults = sumOfResults + +diceRandom;

                    if (i==diceParameters[0]-1){             // po ostatnim rzucie wyświetl wiadomość
                        System.out.println("Suma wyników równa się " + sumOfResults);
                    }
                }
            }
        }

    }
}