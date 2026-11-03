package com.leonardo.taskmanager.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("TaskManager");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}