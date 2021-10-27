package com.company;

import com.company.scenes.DrawScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public final static int WINDOW_WIDTH = 1280;
    public final static int WINDOW_HEIGHT = 720;

    @Override
    public void start(Stage stage) {
        DrawScene drawScene = new DrawScene(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
