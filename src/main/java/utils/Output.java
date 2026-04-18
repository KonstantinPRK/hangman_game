package utils;

public class Output {
    DictionaryLoader dictionaryLoader = new DictionaryLoader();

    public void print(Object obj){
        System.out.println(obj);
    }

    public void somethingWrong(){
        print("Что-то пошло не так");
    }

    public void showHangmanLevel(int i) {
    }
}
