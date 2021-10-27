package com.company.events;

import com.company.scenes.DrawScene;
import javafx.scene.control.Button;

public class ButtonEvents {
    DrawScene scene;
    boolean isInt;

    public ButtonEvents(Button[] buttons, DrawScene scene, boolean isInt) {
        this.scene = scene;
        this.isInt = isInt;
        for(Button button: buttons){
            initializeButtonEvent(button);
        }
    }

    private void initializeButtonEvent(Button button) {
        switch(button.getId()) {
            case "add":
                button.setOnAction((event) -> add());
                break;
            case "remove":
                button.setOnAction((event) -> remove());
                break;
            case "search":
                button.setOnAction((event) -> search());
                break;
            case "random":
                button.setOnAction((event) -> random());
                break;
            default:
                System.out.println("Cant find button ID");
        }
    }
    private void add() {
        scene.addNode();
    }
    private void remove() {
        scene.removeNode();
    }
    private void search() {
        scene.searchNode();
    }
    private void random() {
        if(isInt) {
            scene.randomDataInt(0, 100);
        } else {
            scene.randomDataString();
        }
    }
}
