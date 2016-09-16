package com.meadowsapps.pkmn;

import com.meadowsapps.pkmn.ui.StatGraph;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by dmeadows on 2/21/2015
 */
public class PokemonStatCalculator extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox layout = new VBox();
        final StatGraph graph = new StatGraph();
        layout.getChildren().add(graph);
        Slider slider = new Slider(0, 255, 0);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                graph.setValue(newValue.intValue());
            }
        });
        layout.getChildren().add(slider);

        primaryStage.setTitle("Pokemon Stat Calculator");
        primaryStage.setScene(new Scene(layout, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}