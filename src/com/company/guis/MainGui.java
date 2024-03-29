package com.company.guis;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * MainGui er hoved guiet i applikasjonen
 */
public class MainGui extends BorderPane{
    private Button[] buttons;
    private TextField input;

    /**
     * Konstruktøren for MainGui
     */
    public MainGui() {
        HBox hBox = new HBox(10);
        Button btn1 = new Button("Legg til");
        btn1.setId("add");
        Button btn2 = new Button("Slett");
        btn2.setId("remove");
        Button btn3 = new Button("Søk");
        btn3.setId("search");
        Button btn4 = new Button("Random");
        btn4.setId("random");
        Button btn5 = new Button("Minste");
        btn5.setId("minste");

        input = new TextField();
        Label lbl1 = new Label("Input: ");
        btn1.setPrefSize(80, 35);
        btn2.setPrefSize(80, 35);
        btn3.setPrefSize(80, 35);
        btn4.setPrefSize(80, 35);
        btn5.setPrefSize(80, 35);
        hBox.getChildren().addAll(lbl1, input, btn1, btn2, btn3, btn4, btn5);
        hBox.setPadding(new Insets(15));
        setBottom(hBox);
        Button[] buttons = {btn1, btn2, btn3, btn4, btn5};
        this.buttons = buttons;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public TextField getInput() {
        return input;
    }
}
