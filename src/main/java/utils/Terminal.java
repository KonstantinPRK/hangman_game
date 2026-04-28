package utils;

import java.io.PrintStream;
import java.util.Scanner;

public class Terminal {
    PrintStream printer;
    Scanner scan;
    private final DictionaryLoader loader;

    public Terminal(PrintStream out, Scanner scanner, DictionaryLoader wordLoader){
        this.printer = out;
        this.scan = scanner;
        this.loader = wordLoader;
    }


    public void print(Object obj){
       printer.println(obj);
    }


    public void printTopicsList() {
        for(String topic : loader.getTopicsList()){
            print(topic);
        }
    }


    public String getTopicName(int topicNumber) {
        String variant = "Выберите число от 1 до 24 включительно";

        while((topicNumber < 1) || (topicNumber > 24)){
            print("Выбрана несуществующая тема. " + variant);
            topicNumber = getUserInt();
        }

        return loader.getTopicName(topicNumber);
    }


    public char getUserChar(){
        boolean success = false;
        char userChar = '_';

        while (!success) {
            try {
                userChar = scan.nextLine().toLowerCase().charAt(0);
                success = true;
            } catch (Exception e) {
                printer.println("Ошибка: первый символ не буква. Выберите букву: ");
            }
        }

        return userChar;
    }


    public int getUserInt() {
        boolean success = false;
        int userInt = -1;

        while (!success) {
            try {
                userInt = scan.nextInt();
                scan.nextLine();
                success = true;
            } catch (Exception e) {
                print("Ошибка: это не число. Введите ЦЕЛОЕ число: ");
                scan.nextLine();
            }
        }

        return userInt;
    }


    public String[] getTopicWords(String topic) {
        return loader.getWords(topic);
    }

    public void drawHangman(int level) {
        String hangman = switch (level) {
            case 1 -> """
                +---+
                |   |
                    |
                    |
                    |
                    |
                    |
                =========
                """;
            case 2 -> """
                +---+
                |   |
                O   |
                    |
                    |
                    |
                    |
                =========
                """;
            case 3 -> """
                +---+
                |   |
                O   |
                |   |
                    |
                    |
                    |
                =========
                """;
            case 4 -> """
                +---+
                |   |
                O   |
               /|   |
                    |
                    |
                    |
                =========
                """;
            case 5 -> """
                +---+
                |   |
                O   |
               /|\\  |
                    |
                    |
                    |
                =========
                """;
            case 6 -> """
                +---+
                |   |
                O   |
               /|\\  |
               /    |
                    |
                    |
                =========
                """;
            case 7 -> """
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
                    |
                    |
                =========
                """;
            case 8 -> """
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
               /    |
                    |
                =========
                """;
            case 9 -> """
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
               / \\  |
                    |
                =========
                """;
            case 10 -> """
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
               / \\  |
                XXX
                =========
                ВЫ ПРОИГРАЛИ!
                """;
            default -> "Error";
        };

        print(hangman);
    }
}
