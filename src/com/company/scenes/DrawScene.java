package com.company.scenes;

import com.company.Main;
import com.company.entities.AVLTree;
import com.company.events.ButtonEvents;
import com.company.guis.AVLView;
import com.company.guis.MainGui;
import com.company.guis.MessageDialog;
import com.company.systems.subSystems.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import static java.lang.Math.random;

/**
 * Denne klassen er hoved vinduet til treet
 */
public class DrawScene {
    private MainGui mainGui;
    private AVLTree tree;
    private AVLView view;
    private Scene scene;
    private boolean isNumber;
    private AddSystem innsettingSystem;
    private AksessSystem aksessSystem;
    private RandomSystem randomSystem;
    private RemoveSystem sletteSystem;
    private SearchSystem searchSystem;

    /**
     * Konstruktør for hoved scenen
     * @param stage
     */
    public DrawScene(Stage stage){
        //Create the main gui
        mainGui = new MainGui();
        innsettingSystem = new AddSystem(this);
        aksessSystem = new AksessSystem(this);
        randomSystem = new RandomSystem(this);
        sletteSystem = new RemoveSystem(this);
        searchSystem = new SearchSystem(this);

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
        int size = tree.getSize();

        if(isNumber) {
            if(innsettingSystem.checkInsertNumber(input, size)) {
                if(!tree.search(Integer.parseInt(input)))
                    addNumber(Integer.parseInt(input));
            }
        } else {
            String inputBig = input.toUpperCase();
            if(innsettingSystem.checkInsertChar(inputBig, size)) {
                addChar(inputBig.charAt(0));
            }
        }
    }
    /**
     * Søker etter en node
     */
    public void searchNode() {
        String input = mainGui.getInput().getText();
        searchSystem.searchNode(input.toUpperCase());
        mainGui.getInput().setText("");
    }

    /**
     * Fjerner en node og tegner treet på nytt
     */
    public void removeNode() {
        String input = mainGui.getInput().getText();
        if(isNumber) {
            if(sletteSystem.removeNodeCheck(input.toUpperCase())){
                tree.remove(Integer.parseInt(input));
            }
        } else {
            if(sletteSystem.removeNodeCheck(input.toUpperCase())); {
                tree.remove(input.charAt(0));
            }
        }
        view.getChildren().clear();
        view.createTree();
        mainGui.getInput().setText("");
    }

    /**
     * Lager 10 random nodes for char og tegner treet på nytt
     */
    public void randomDataChar() {
        tree.clear();
        tree.insertMultiple(randomSystem.randomDataChar());
        addTree(view, mainGui);
    }

    /**
     * Lager 10 random nodes for int og tegner treet på nytt
     * @param min minste nummer
     * @param max største nummer
     */
    public void randomDataInt(int min, int max) {
        tree.clear();
        tree.insertMultiple(randomSystem.randomDataInt(min, max));
        addTree(view, mainGui);
    }

    /**
     * Finner x minste tall i treet
     * Vi har 2 metoder her, ettersom vi har litt usikre på hva du ville med o(logn) her
     * Data fra treet er allerede sortert som gjør at x = index + 1
     */
    public void minste() {
        String input = mainGui.getInput().getText();
        ArrayList<Integer> array = new ArrayList<>();
        int[] intArray = new int[tree.getSize()];

        tree.forEach(item -> array.add((int) item));

        for(int i = 0; i < array.size(); i++) {
            intArray[i] = array.get(i);
        }

        //Ferdig sortert
        aksessSystem.noSort(array, input);

        //Shuffel og sorter
        aksessSystem.mergeSort(intArray, 0, array.size() - 1);
    }

    public AVLTree getTree() {
        return tree;
    }

    /**
     * Legger til ny int
     * @param number
     */
    private void addNumber(int number) {
            tree.insert(number);
            updateNode();
    }

    /**
     * Legger til ny chart
     * @param input
     */
    private void addChar(char input) {
        tree.insert(input);
        updateNode();
    }

    /**
     * Oppdaterer treet med nye node
     */
    private void updateNode() {
        view.getChildren().clear();
        view.createTree();
        mainGui.getInput().setText("");
    }
}
