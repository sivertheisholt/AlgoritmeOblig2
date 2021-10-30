package com.company.scenes;

import com.company.Main;
import com.company.entities.AVLTree;
import com.company.entities.Tree;
import com.company.entities.TreeNode;
import com.company.events.ButtonEvents;
import com.company.guis.AVLView;
import com.company.guis.MainGui;
import com.company.guis.MessageDialog;
import com.company.systems.AksessSystem;
import com.company.systems.InnsettingSystem;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.random;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class DrawScene {
    private MainGui mainGui;
    private AVLTree tree;
    private AVLView view;
    private Scene scene;
    private boolean isNumber;
    private InnsettingSystem innsettingSystem;
    private AksessSystem aksessSystem;

    public AVLTree getTree() {
        return tree;
    }

    public DrawScene(Stage stage){
        //Create the main gui
        mainGui = new MainGui();
        innsettingSystem = new InnsettingSystem(this);
        aksessSystem = new AksessSystem();

        //Create tree
        tree = new AVLTree();
        view = new AVLView(tree, Main.WINDOW_WIDTH);

        //Create scene
        scene = new Scene(mainGui, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

        //Ask for type
        MessageDialog dialog = new MessageDialog();
        isNumber = dialog.askType();

        //Initialize buttons
        ButtonEvents buttons = new ButtonEvents(mainGui.getButtons(), this, isNumber);

        //Stage
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Tegner tree fra bunn av
     * @param view AVLView
     * @param borderPane borderpane som treet skal legges inn i
     */
    public void addTree(AVLView view, BorderPane borderPane) {
        view.getChildren().clear();
        //Create tree
        view.createTree();

        //Create animation (For fun)
        Timeline timeline = new Timeline();
        for (Node panes: view.getChildren()) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(panes.translateXProperty(), random() * -1000),
                            new KeyValue(panes.translateYProperty(), random() * 1200)),
                    new KeyFrame(new Duration(5000), // set end position at 40s
                            new KeyValue(panes.translateXProperty(), 0),
                            new KeyValue(panes.translateYProperty(), 0)
                    )
            );
        }
        timeline.play();
        borderPane.setCenter(view);
    }

    /**
     * Legger til en ny node og tegner treet på nytt
     */
    public void addNode() {
        String input = mainGui.getInput().getText();
        if(isNumber && checkIfInt(input)) {
            addNumber(input);
            return;
        } else if(!isNumber && !checkIfInt(input) && input.length() == 1) {
            addChar(input.toUpperCase().charAt(0));
            return;
        }
        showErrorMessage("Wrong format! Please enter a " + (isNumber ? "number" : "string"));
    }

    public void addNumber(String input) {
        if (innsettingSystem.sjekkOmNummerEksisterer(Integer.parseInt(input), "Number is already in the tree!")){
            tree.insert(input);
            updateNode();
        };
    }



    public void addChar(char input) {
        if(innsettingSystem.sjekkOmCharEksisterer(input,"Character is already in the tree!")) {
            tree.insert(input);
            updateNode();
        }
    }
    public void updateNode() {
        view.getChildren().clear();
        view.createTree();
        mainGui.getInput().setText("");
    }

    /**
     * Søker etter en node
     */
    public void searchNode() {
        if(tree.search(Integer.parseInt(mainGui.getInput().getText())))
            showInfoMessage("Value is in tree");
        else
            showInfoMessage("Value is NOT in tree");
        mainGui.getInput().setText("");
    }

    /**
     * Fjerner en node og tegner treet på nytt
     */
    public void removeNode() {
        tree.remove(Integer.parseInt(mainGui.getInput().getText()));
        view.getChildren().clear();
        view.createTree();
        mainGui.getInput().setText("");
    }

    /**
     * Lager 10 random nodes og tegner treet på nytt
     * @param min minste nummer
     * @param max største nummer
     */
    public void randomDataInt(int min, int max) {
        HashMap<Integer, Integer> numbers = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            if(numbers.containsKey(randomNum))
                continue;
            numbers.put(randomNum, randomNum);
        }
        tree.clear();
        tree.insertMultiple(numbers.values().toArray(new Comparable[0]));
        addTree(view, mainGui);
    }
    public void randomDataString() {
        HashMap<Character, Character> numbers = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(65, 90);
            if(numbers.containsKey((int) randomNum))
                continue;
            numbers.put((char) randomNum, (char) randomNum);
        }
        tree.clear();
        tree.insertMultiple(numbers.values().toArray(new Comparable[0]));
        addTree(view, mainGui);
    }

    /**
     * Viser info dialog
     * @param message melding som dialogen skal inneholde
     */
    private void showInfoMessage(String message) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, message);
    }

    /**
     * Viser error dialog
     * @param message melding som dialogen skal inneholde
     */
    public void showErrorMessage(String message) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, message, "Error", ERROR_MESSAGE);
    }

    /**
     * Sjekker om input er string eller nummer
     * @return true om det er int, false annet
     */
    private boolean checkIfInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }catch(NumberFormatException e) {
            return false;
        }
    }

    public void minste() {

        tree.getNodes();
        tree.forEach(item -> {
            System.out.println(item);
        });




        //int test = aksessSystem.minsteTall(tree.getNodes(), Integer.parseInt(mainGui.getInput().getText()));
        //System.out.println(test);
    }
}
