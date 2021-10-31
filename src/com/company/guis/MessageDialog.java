package com.company.guis;

import javax.swing.*;

/**
 *  Denne klassen viser en melding om hvilken type brukeren vil ha
 */
public class MessageDialog extends JOptionPane{
    JFrame frame = new JFrame("Oblig");
    Object[] options = {
            "Character",
            "Number"
    };

    /**
     * Spørr om type
     * @return true om nummer, false om char
     */
    public boolean askType() {
        int input = JOptionPane.showOptionDialog(frame,
                "What type of format would you like to use?",
                "Tree type",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        return input >0 ? true : false;
    }
}
