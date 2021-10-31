package com.company.systems.subSystems;

import com.company.scenes.DrawScene;
import com.company.systems.MainSystem;

public class SearchSystem extends MainSystem {
    public SearchSystem(DrawScene drawScene) {
        super(drawScene);
    }
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
