package com.meadowsapps.pkmn;

import com.meadowsapps.pkmn.ui.InfoView;
import com.meadowsapps.pkmn.ui.StatView;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by dmeadows on 2/21/2015
 */
public class PokemonStatCalculator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();

        InfoView infoView = new InfoView();
        infoView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setConstraints(infoView, 0, 0, 1, 1,
                HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        pane.setTop(infoView);

        StatView statView = new StatView();
        statView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setConstraints(statView, 0, 1, 1, 1,
                HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        pane.setCenter(statView);

        primaryStage.setTitle("Pokemon Stat Calculator");
        primaryStage.setScene(new Scene(pane, pane.getPrefWidth(), pane.getHeight()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}