package model;

public class WordConstructor {
    private int[] charFrequency;
    private boolean[] isCharExist;


    public void setNewWord(String userWord) {
        charFrequency = new int[33];
        isCharExist = new boolean[33];

        for(char letter : userWord.toCharArray()){
            int charIndex = letter - 'а'; //кириллическая "а"
            charFrequency[charIndex]++;
            isCharExist[charIndex] = true;
        }
    }

    public String getCurrentStateWord() {
        return null;
    }

    public boolean checkRespond(char letter) {

        return true;
    }

    public String drawHangman(int level) {
        String hangman = "";
        switch (level) {
            case 1:
                hangman = """
                +---+
                |   |
                    |
                    |
                    |
                    |
                    |
                =========
                """;
                break;
            case 2:
                hangman = """
                +---+
                |   |
                O   |
                    |
                    |
                    |
                    |
                =========
                """;
                break;
            case 3:
                hangman = """
                +---+
                |   |
                O   |
                |   |
                    |
                    |
                    |
                =========
                """;
                break;
            case 4:
                hangman = """
                +---+
                |   |
                O   |
               /|   |
                    |
                    |
                    |
                =========
                """;
                break;
            case 5:
                hangman = """
                +---+
                |   |
                O   |
               /|\\  |
                    |
                    |
                    |
                =========
                """;
                break;
            case 6:
                hangman = """
                +---+
                |   |
                O   |
               /|\\  |
               /    |
                    |
                    |
                =========
                """;
                break;
            case 7:
                hangman = """
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
                    |
                    |
                =========
                """;
                break;
            case 8:
                hangman = """
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
               /    |
                    |
                =========
                """;
                break;
            case 9:
                hangman = """
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
               / \\  |
                    |
                =========
                """;
                break;
            case 10:
                hangman = """
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
                break;
            default:
                hangman = "Error";
        }
        return hangman;
    }

}
