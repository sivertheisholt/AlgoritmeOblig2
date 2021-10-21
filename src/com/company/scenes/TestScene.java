package com.company.scenes;

import com.company.Main;
import com.company.entities.AVLTree;
import com.company.entities.TreeNode;
import com.company.events.ButtonEvents;
import com.company.guis.AVLView;
import com.company.guis.MainGui;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.random;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class TestScene {
    private MainGui mainGui;
    private AVLTree tree;
    private AVLView view;
    private Scene scene;
    private boolean number = true;

    public TestScene(Stage stage){
        //Create the main gui
        mainGui = new MainGui();

        //Test data
        Comparable[] numbers = {6, 3, 9, 1, 5, 7, 11, 15, 20, 30, 2, 4, 10, 14, 50, 60, 70, 80, 90, 100};

        //Create tree
        tree = new AVLTree();
        view = new AVLView(tree, Main.WINDOW_WIDTH);

        //Create scene
        scene = new Scene(mainGui, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

        //Initialize buttons
        ButtonEvents buttons = new ButtonEvents(mainGui.getButtons(), this);

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
        if(checkIfInt()) {

        }
        tree.insert(Integer.parseInt(mainGui.getInput().getText()));
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
    public void randomData(int min, int max) {
        Comparable[] numbers = new Comparable[10];
        for(int i = 0; i < 10; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            numbers[i] = randomNum;
        }
        tree.clear();
        tree.insertMultiple(numbers);
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
    private void showErrorMessage(String message) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, message, "Error", ERROR_MESSAGE);
    }

    /**
     * Sjekker om input er string eller nummer
     * @return true om det er int, false annet
     */
    private boolean checkIfInt() {
        try {
            Integer.parseInt(mainGui.getInput().getText());
            return true;
        }catch(NumberFormatException e) {
            return false;
        }
    }
}
