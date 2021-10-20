package com.company.scenes;

import com.company.Main;
import com.company.entities.AVLTree;
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

import static java.lang.Math.random;

public class TestScene {

    public TestScene(Stage stage) {
        MainGui mainGui = new MainGui();

        Comparable numbers[] = {6, 3, 9, 1, 5, 7, 11, 15, 20, 30, 2, 4, 10, 14, 50, 60, 70, 80, 90, 100};

        AVLTree tree = new AVLTree(numbers);
        AVLView view = new AVLView(tree, Main.WINDOW_WIDTH);

        Scene scene = new Scene(mainGui, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

        stage.setScene(scene);
        stage.show();

        addTree(view, mainGui);
    }

    public void addTree(AVLView view, BorderPane borderPane) {
        view.drawTree();

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
}
