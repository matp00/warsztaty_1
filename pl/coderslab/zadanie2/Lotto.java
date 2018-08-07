package pl.coderslab.zadanie2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lotto {

    public static int[] lottoResult() {
        Random rand = new Random();
        int[] lottoResult = new int[6];
        int[] tabLotto = new int[49];

        for (int i = 0; i < tabLotto.length; i++) { // wypełnianie tablicy od 1 do 49
            tabLotto[i] = i + 1;
        }

        for (int i = 49; i > (49 - 6); i--) {  // losowanie kolejnych 6 indeksów z tablicy z zakresu od 0 do i
            int index = rand.nextInt(i); // przypisywanie loswej liczby z zakresu i do zmiennej index
            lottoResult[49 - i] = tabLotto[index]; // przypisanie wylosowanej liczby to tablicy wyników
            tabLotto[index] = tabLotto[i - 1]; // nadpisanie wylosowanego elementu wartością z końca tablicy
        }
        return lottoResult;
    }


    public static int getNumber() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj wartosc");

        int number = 0;
        do {                                            // sprawdzenie czy wpisana wartość jest liczbą
            while (!scan.hasNextInt()) {
                System.out.println("To nie jest liczba"); // jeśli nie to zwróć komentarz
                scan.next();
            }
            number = scan.nextInt();                       // jeśli tak, to przypisz ją do zmiennej number
        } while (!(number >= 1 && number <= 49));          // sprawdzenie czy liczba mieści się w zakresie 1-49
        return number;
    }

    public static boolean isElementInArray(int[] arr, int element) { //metoda sprawdzająca czy element znajduje się już w tablicy
        for (int number : arr) {                                    //dla każdego indeksu tablicy sprawdź czy jego wartość równa się elementowi
            if (number == element)
                return true;
        }

        return false;
    }

    public static int[] getUserNumbers() {      //metoda pobierająca liczby od użytkownika i przypisująca je do tablicy wyników
        int[] result = new int[6];
        int index = 0;

        while (index < 6) {
            int userNumber = getNumber();   // pobranie liczby przy pomocy funkcji pomocniczej getNumber
            if (!(isElementInArray(result, userNumber))) { // sprawdzenie czy wpisana liczba jest już w tablicy
                result[index] = userNumber;
                index++;
            }

        }
        return result;
    }

    public static int getScore(int[] lottoNumbers, int[] userNumbers) { //funkcja sprawdzająca ilość trafionych liczb
        int score = 0;

        for (int lottoNumber : lottoNumbers) {
            if (isElementInArray(userNumbers, lottoNumber)) {
                score++;
            }
        }
        return score;

    }


    public static void main(String[] args) {

        int[] lottoNumbers = lottoResult();
        int[] userNumbers = getUserNumbers();
        System.out.println(Arrays.toString(userNumbers));

        int score = getScore(lottoNumbers, userNumbers);

        System.out.println("Wylosowane liczby to:" + Arrays.toString(lottoNumbers));

        if (score >= 3) {
            System.out.println("Twój wynik to " + score);
        } else
            System.out.println("Nic nie wygrałeś");

    }

}