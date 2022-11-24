package services;

import java.util.LinkedList;

public class InsultService {

    LinkedList<String> insultList = new LinkedList<>();

    public InsultService() {
        loadList();
    }

    private void loadList() {
        insultList.add("Idiot");
        insultList.add("Jerk");
        insultList.add("Bastard");
    }

    public void addInsult(String insult){
        insultList.add(insult);
    }
    public LinkedList<String> getAllInsults(){
        return insultList;
    }

    public String getInsult() {
        int index = (int) (Math.random() * insultList.size());
        return insultList.get(index);
    }


}
