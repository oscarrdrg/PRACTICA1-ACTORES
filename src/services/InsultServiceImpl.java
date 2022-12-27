package services;

import interfaces.InsultService;

import java.util.LinkedList;

public class InsultServiceImpl implements InsultService {

    LinkedList<String> insultList = new LinkedList<>();

    public InsultServiceImpl() {
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
