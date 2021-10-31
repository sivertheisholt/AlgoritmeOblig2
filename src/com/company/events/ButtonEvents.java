package com.company.events;

import com.company.scenes.DrawScene;
import javafx.scene.control.Button;

/**
 * ButtonEvents er ansvarlig for å initialisere knappene med hendelser
 */
public class ButtonEvents {
    private DrawScene scene;
    private boolean isInt;

    /**
     * Konstruktøren for ButtonEvents
     * @param buttons knappene
     * @param scene scenen som brukes
     * @param isInt om applikasjonen er startet med type int eller char
     */
    public ButtonEvents(Button[] buttons, DrawScene scene, boolean isInt) {
        this.scene = scene;
        this.isInt = isInt;
        for(Button button: buttons){
            initializeButtonEvent(button);
        }
    }

    /**
     * Initialiserer knappene
     * @param button
     */
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
            case "minste":
                button.setOnAction((event) -> minste());
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
            scene.randomDataChar();
        }
    }
    private void minste() {
        scene.minste();
    }
}
