package com.meadowsapps.pkmn;

import com.meadowsapps.pkmn.ui.StatView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by dmeadows on 2/21/2015
 */
public class PokemonStatCalculator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StatView view = new StatView();

        primaryStage.setTitle("Pokemon Stat Calculator");
        primaryStage.setScene(new Scene(view, view.getPrefWidth(), 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}