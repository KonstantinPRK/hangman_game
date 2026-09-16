package utils;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Обеспечивает взаимодействие игры с пользователем через потоки ввода и вывода.
 * Класс выводит сообщения и изображение виселицы, считывает пользовательские данные
 * и предоставляет доступ к загруженному словарю.
 */
public class Terminal {
    /** Загрузчик тематических словарей. */
    private final DictionaryLoader loader;

    /** Поток для вывода сообщений пользователю. */
    PrintStream printer;
    /** Сканер для чтения пользовательского ввода. */
    Scanner scan;

    /**
     * Создаёт объект для работы с терминалом.
     *
     * @param out поток для вывода сообщений
     * @param scanner сканер для чтения пользовательского ввода
     * @param wordLoader загрузчик тематических словарей
     */
    public Terminal(PrintStream out, Scanner scanner, DictionaryLoader wordLoader){
        this.printer = out;
        this.scan = scanner;
        this.loader = wordLoader;
    }


    /**
     * Выводит переданный объект с переводом строки.
     *
     * @param obj объект для вывода
     */
    public void print(Object obj){
       printer.println(obj);
    }


    /**
     * Выводит список всех доступных тем словаря.
     */
    public void printTopicsList() {
        for(String topic : loader.getTopicsList()){
            print(topic);
        }
    }


    /**
     * Возвращает название темы по её номеру, повторно запрашивая номер при неверном значении.
     *
     * @param topicNumber выбранный пользователем номер темы
     * @return название существующей темы
     */
    public String getTopicName(int topicNumber) {
        String variant = "Выберите число от 1 до 24 включительно";

        while((topicNumber < 1) || (topicNumber > 24)){
            print("Выбрана несуществующая тема. " + variant);
            topicNumber = getUserInt();
        }

        return loader.getTopicName(topicNumber);
    }


    /**
     * Считывает первый символ введённой пользователем строки в нижнем регистре.
     * При ошибке ввода повторяет запрос.
     *
     * @return первый корректно считанный символ
     */
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


    /**
     * Считывает целое число, повторяя запрос при вводе значения другого типа.
     *
     * @return корректно считанное целое число
     */
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


    /**
     * Возвращает слова, относящиеся к указанной теме.
     *
     * @param topic название темы
     * @return массив слов выбранной темы
     */
    public String[] getTopicWords(String topic) {
        return loader.getWords(topic);
    }


    /**
     * Выводит состояние виселицы, соответствующее текущему уровню ошибок.
     * Для неизвестного уровня выводит сообщение {@code Error}.
     *
     * @param level текущий уровень ошибок от 1 до 10
     */
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
