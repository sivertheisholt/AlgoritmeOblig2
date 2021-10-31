package com.company.systems.subSystems;

import com.company.scenes.DrawScene;
import com.company.systems.MainSystem;

/**
 * Denne klassen håndterer validering av å legge til ny node
 */
public class AddSystem extends MainSystem {
    public AddSystem(DrawScene drawScene) {
        super(drawScene);
    }

    /**
     * Sjekker input på adde nummer
     * @param input input fra bruker
     * @param size størrelse på treet
     * @return true om alt er ok, false om input er feil
     */
    public boolean checkInsertNumber(String input, int size) {
        if(!checkIfInt(input)) {
            showErrorMessage("Please enter a number instead!");
            return false;
        }
        int number = Integer.parseInt(input);
        return sjekkMax(size) &&
                sjekkNummer(number) &&
                !sjekkOmNummerEksisterer(number);
    }

    /**
     * Sjekker input på adde char
     * @param input input fra bruker
     * @param size størrelse på treet
     * @return true om alt er ok, false om input er feil
     */
    public boolean checkInsertChar(String input, int size) {
        if(checkIfInt(input) && !sjekkForSymboler(input)) {
            showErrorMessage("Please enter a char instead!");
            return false;
        }
        char c = input.charAt(0);
        return sjekkMax(size) &&
                !sjekkOmCharEksisterer(c);
    }

    /**
     * Sjekker om max nodes er nådd
     * @param size
     * @return
     */
    private boolean sjekkMax(int size) {
        if(size > 30){
            showErrorMessage("Max nodes added");
            return false;
        }
        return true;
    }
    /**
     * Sjekker om nummer er lik eller mindre en 999.
     * @param number int
     * @return true hvis den er mindre eller lik 999 eller false hvis ikke.
     */
    private boolean sjekkNummer(int number){
        if(number >= 999) {
            showErrorMessage("Number is too big");
            return false;
        }
        return true;
    }

    /**
     * Sjekker om nummer eksisterer
     * @param nummer nummer som skal sjekkes
     * @return True hvis den eksisterer, false hvis den ikke eksisterer
     */
    private boolean sjekkOmNummerEksisterer(int nummer) {
        if (drawScene.getTree().search(nummer)) {
            showErrorMessage("Number already exists in the tree!");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sjekker om char eksisterer
     * @param input
     * @return
     */
    private boolean sjekkOmCharEksisterer(char input){
        if(drawScene.getTree().search(input)) {
            showErrorMessage("Char already exists in the tree!");
            return true;
        } else {
            return false;
        }
    }
}
