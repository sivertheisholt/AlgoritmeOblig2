package com.company.scenes;

import com.company.Main;
import com.company.entities.AVLTree;
import com.company.entities.BST;
import com.company.entities.TreeNode;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestScene {

    public TestScene(Stage stage) {
        BorderPane mainPane = new BorderPane();
        Pane pane = new Pane();
        HBox hBox = new HBox(10);
        Button btn1 = new Button("Legg til");
        Button btn2 = new Button("Slett");
        Button btn3 = new Button("Søk");
        Button btn4 = new Button("Random");
        TextField input = new TextField();
        Label lbl1 = new Label("Input: ");
        btn1.setPrefSize(80, 35);
        btn2.setPrefSize(80, 35);
        btn3.setPrefSize(80, 35);
        btn4.setPrefSize(80, 35);
        hBox.getChildren().addAll(lbl1, input, btn1, btn2, btn3, btn4);
        hBox.setPadding(new Insets(15));
        mainPane.setCenter(pane);
        mainPane.setBottom(hBox);
        Scene scene = new Scene(mainPane, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

        Comparable numbers[] = {6, 3, 9, 1, 5, 7, 11};

        AVLTree tree = new AVLTree(numbers);

        stage.setScene(scene);
        stage.show();
    }
}
