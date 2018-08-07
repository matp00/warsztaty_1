package pl.coderslab.zadanie3;

import java.util.Scanner;

public class ComputerGuess {

    public static void main(String[] args) {
        System.out.println("Pomyśl liczbę od 1 do 1000, a komputer ją zgadnie");
        computerGuess();

    }

    public static String getAsnwer() {   // metoda sprawdzająca czy odpowiedź podana przez użytkownika jest z wymaganego zakresu
        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz odpowiedź z zestawu: \nza mało \nza dużo \nzgadłeś");
        String answer = scan.nextLine();

        while (!answer.equalsIgnoreCase("za mało")
                && !answer.equalsIgnoreCase("za dużo")
                && !answer.equalsIgnoreCase("zgadłeś")) {
            System.out.println("Błędna odpowiedź");
            answer = scan.nextLine();
        }
        return answer;
    }

    public static void computerGuess() {
        int max = 1001;
        int min = 1;
        String answer = "";

        int guess = ((max - min) / 2) + min;
        System.out.println("Zgaduję " + guess);
        answer = getAsnwer();
        int i = 9;
        System.out.println("Mam jeszcze " + i + " szans");

        while (i > 0) {  // pętla wykonuje się max 9 razy
            if (answer.equalsIgnoreCase("za mało")) {
                i--;
                min = guess;
                guess = ((max - min) / 2) + min;
                System.out.println("Zgaduję " + guess);
                answer = getAsnwer();
            } else if (answer.equalsIgnoreCase("za dużo")) {
                i--;
                max = guess;
                guess = ((max - min) / 2) + min;
                System.out.println("Zgaduję " + guess);
                answer = getAsnwer();
            } else {
                System.out.println("Wygrałem! Twoja liczba to " + guess);
            }
        }
    }

}