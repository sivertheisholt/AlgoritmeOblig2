package com.company.systems;

import com.company.scenes.DrawScene;

import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class MainSystem {
    protected DrawScene drawScene;

    protected MainSystem(DrawScene drawScene) {
        this.drawScene = drawScene;
    }

    /**
     * Sjekker om stringen inneholder kun alfabet bokstaver
     * @param s String
     * @return true hvis det ikke er tall eller symboler, false hvis det inneholder tall eller symboler.
     */
    protected boolean sjekkForSymboler(String s){
        return s.matches("^[-a-zA-Z]+");
    }

    /**
     * Sjekker om input er string eller nummer
     * @return true om det er int, false annet
     */
    protected boolean checkIfInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Viser info dialog
     * @param message melding som dialogen skal inneholde
     */
    protected void showInfoMessage(String message) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, message);
    }

    /**
     * Viser error dialog
     * @param message melding som dialogen skal inneholde
     */
    protected void showErrorMessage(String message) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, message, "Error", ERROR_MESSAGE);
    }
}
