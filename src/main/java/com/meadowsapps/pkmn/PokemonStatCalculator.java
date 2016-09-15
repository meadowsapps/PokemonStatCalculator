package com.meadowsapps.pkmn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by dmeadows on 2/21/2015
 */
public class PokemonStatCalculator extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        InfoView view = new InfoView();
        primaryStage.setTitle("Pokemon Stat Calculator");
        primaryStage.setScene(new Scene(view, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}