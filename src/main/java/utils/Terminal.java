package utils;

import java.io.PrintStream;
import java.util.Scanner;

public class Terminal {
    PrintStream printer;
    Scanner scan;
    private final DictionaryLoader wordLoader;

    public Terminal(PrintStream out, Scanner scanner, DictionaryLoader wordLoader){
        this.printer = out;
        this.scan = scanner;
        this.wordLoader = wordLoader;
    }

    public void print(Object obj){
       printer.println(obj);
    }


    public String[] getTopicsList() {
        return null;
    }


    public String getTopicName(int topicNumber) {
        //возвращает название списка по номеру, полученному от пользователя
        return null;
    }


    //приводим любую букву к меньшему регистру, проверяем на символ, пока не получим символ запрашиваем снова с предупреждением
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
                success = true;
            } catch (Exception e) {
                print("Ошибка: это не число. Введите ЦЕЛОЕ число: ");
            }
        }

        return userInt;
    }


    public String[] getTopicWords() {
        return null;
    }
}
