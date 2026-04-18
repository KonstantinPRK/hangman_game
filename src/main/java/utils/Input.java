package utils;

import java.util.Set;

public class Input {
    private final DictionaryLoader wordLoader;

    public Input(DictionaryLoader dictionaryLoader){
        this.wordLoader = dictionaryLoader;
    }

    public String[] getTopicsList() {
        return null;
    }

    public String getTopicFromUser() {
        //возвращает название списка по номеру, полученному от пользователя
        return null;
    }

    public char getUserRespond(){
        return 'd';
    }
}
