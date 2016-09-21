package com.meadowsapps.pkmn;

import com.meadowsapps.pkmn.ui.StatCalculatorView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by dmeadows on 2/21/2015
 */
public class PokemonStatCalculator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxml = getClass().getClassLoader().getResource("fxml/StatCalculatorView.fxml");
        FXMLLoader loader = new FXMLLoader(fxml);
        AnchorPane view = (AnchorPane) loader.load();
        StatCalculatorView controller = (StatCalculatorView) loader.getController();

        Scene scene = new Scene(view);
        URL style = getClass().getClassLoader().getResource("style/default.css");
        scene.getStylesheets().add(style.toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pokemon Stat Calculator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}