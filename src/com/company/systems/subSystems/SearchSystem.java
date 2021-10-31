package com.company.systems.subSystems;

import com.company.scenes.DrawScene;
import com.company.systems.MainSystem;

/**
 * Denne klassen håndtere validering av søking i treet
 */
public class SearchSystem extends MainSystem {
    public SearchSystem(DrawScene drawScene) {
        super(drawScene);
    }

    /**
     * Sjekker om en node eksisterer eller ikke
     * @param input Input fra brukeren
     */
    public void searchNodeChar(String input) {
        if(!checkIfInt(input)) {
            if(!sjekkForSymboler(input)) {
                showErrorMessage("Please enter a character!");
                return;
            }
            char c = input.charAt(0);
            if(drawScene.getTree().search(c)) {
                showInfoMessage("Char is in the tree!");
                return;
            }
            showInfoMessage("Char is NOT in the tree!");
        }
    }
    public void searchNodeInt(String input) {
        if(checkIfInt(input)) {
           if(drawScene.getTree().search(Integer.parseInt(input))) {
               showInfoMessage("Number is in the tree");
           } else {
               showInfoMessage("Number is NOT in the tree");
           }
        }
    }
}
