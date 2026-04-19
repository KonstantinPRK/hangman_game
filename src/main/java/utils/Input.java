package utils;

public class Input {
    private final DictionaryLoader wordLoader;

    public Input(DictionaryLoader dictionaryLoader){
        this.wordLoader = dictionaryLoader;
    }

    public String[] getTopicsList() {
        return null;
    }

    public String getTopicName(int topicNumber) {
        //возвращает название списка по номеру, полученному от пользователя
        return null;
    }

    //приводим любую букву к меньшему регистру
    public char getUserChar(){
        return 'd';
    }

    public int getUserInt() {
        return 1;
    }

    public String[] getTopicWords() {
        return null;
    }
}
