package com.company.guis;

import javax.swing.*;

public class MessageDialog extends JOptionPane{
    JFrame frame = new JFrame("Oblig");
    Object[] options = {
            "Character",
            "Number"
    };

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
