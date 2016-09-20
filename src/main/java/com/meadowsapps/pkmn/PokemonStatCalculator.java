package com.meadowsapps.pkmn;

import com.meadowsapps.pkmn.data.Pokemon;
import com.meadowsapps.pkmn.ui.InfoView;
import com.meadowsapps.pkmn.ui.InfoViewController;
import com.meadowsapps.pkmn.ui.StatCalculatorView;
import com.meadowsapps.pkmn.ui.StatView;
import com.meadowsapps.pkmn.ui.control.ModelViewer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by dmeadows on 2/21/2015
 */
public class PokemonStatCalculator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        StatCalculatorView view = new StatCalculatorView();
//        final Pokemon pokemon = new Pokemon();
//        view.setPokemon(pokemon);
        primaryStage.setTitle("Pokemon Stat Calculator");
        Parent root = new InfoViewController();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}