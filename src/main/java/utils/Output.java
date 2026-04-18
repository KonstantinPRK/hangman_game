package utils;

public class Output {
    DictionaryLoader dictionaryLoader = new DictionaryLoader();

    public void print(Object... o){
        for(Object obj : o){
            System.out.println(obj);
        }
    }

    public void somethingWrong(){
        print("Что-то пошло не так");
    }

    public String drawHangman(int level) {
        return null;
    }
}
