package com.meadowsapps.pkmn;

import com.meadowsapps.pkmn.ui.StatCalculatorView;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
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
    public void start(final Stage primaryStage) throws Exception {
        URL fxml = getClass().getClassLoader().getResource("fxml/StatCalculatorView.fxml");
        FXMLLoader loader = new FXMLLoader(fxml);
        AnchorPane view = (AnchorPane) loader.load();
        StatCalculatorView controller = (StatCalculatorView) loader.getController();

        Scene scene = new Scene(view);
        URL style = getClass().getClassLoader().getResource("style/default.css");
        scene.getStylesheets().add(style.toExternalForm());

        primaryStage.setScene(scene);

        double width = 1120;
        double height = 775;
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setMinWidth(width);
        primaryStage.setMaxHeight(height);

        primaryStage.setTitle("Pokemon Stat Calculator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}