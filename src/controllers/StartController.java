package controllers;

import actors.singleton.ActorContext;
import main.StartProgram;

import java.util.LinkedList;

public class StartController {
    public StartController(){}

    public void start(){
        StartProgram.intro();
        StartProgram.startProgram();

    }

    public String actorsContext(){

        LinkedList<String> actorsContext = new LinkedList<>();
        ActorContext.getActorList().forEach((k, v) -> actorsContext.add(k));
        if(actorsContext.isEmpty()) return "No actors in Actor Context";
        else return actorsContext.toString();
    }

    public void exit(){
        System.exit(0);
    }
}
