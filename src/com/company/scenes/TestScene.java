package com.company.scenes;

import com.company.Main;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestScene {

    public TestScene(Stage stage) {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }
}
