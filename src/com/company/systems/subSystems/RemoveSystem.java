package com.company.systems.subSystems;

import com.company.scenes.DrawScene;
import com.company.systems.MainSystem;

/**
 * Denne klassen validerer sletting i treet
 */
public class RemoveSystem extends MainSystem {
    public RemoveSystem(DrawScene drawScene) {
        super(drawScene);
    }

    /**
     * Sjekker om char node kan fjernes
     * @param input input fra bruker
     * @return true om den kan fjernes, false om ikke
     */
    public boolean removeNodeCheckChar(String input) {
        if(!checkIfInt(input)) {
            if(!sjekkForSymboler(input)) {
                showErrorMessage("Please enter a character!");
                return false;
            }
            if(!drawScene.getTree().search(input.charAt(0))) {
                showErrorMessage("Node doesnt exist!");
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sjekker om int node kan fjernes
     * @param input input fra bruker
     * @return true om den kan fjernes, false om ikke
     */
    public boolean removeNodeCheckInt(String input) {
        if(checkIfInt(input)) {
            if(!drawScene.getTree().search(Integer.parseInt(input))) {
                showErrorMessage("Node doesnt exist!");
                return false;
            }
            return true;
        } else {
            showErrorMessage("Please enter a number instead!");
            return false;
        }
    }
}
