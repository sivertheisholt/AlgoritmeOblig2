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
     * Sjekker om
     * @param input
     * @return
     */
    public boolean removeNodeCheck(String input) {
        if(!checkIfInt(input)) {
            if(!sjekkForSymboler(input)) {
                showErrorMessage("Please enter a character!");
                return false;
            }
            return true;
        } else {
            return true;
        }
    }
}
