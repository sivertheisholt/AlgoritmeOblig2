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
    public void searchNode(String input) {
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
        } else {
            int number = Integer.parseInt(input);
            if(drawScene.getTree().search(number)) {
                showInfoMessage("Number is in the tree!");
                return;
            }
            showInfoMessage("Number is NOT in the tree!");
        }
    }
}
