package com.meadowsapps.pkmn.ui;

import com.meadowsapps.pkmn.data.Stat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Created by Dylan on 9/15/16.
 */
public class EvEditor extends GridPane {

    private Label label;
    private Slider slider;
    private Spinner<Integer> spinner;

    public EvEditor(Stat stat) {
        setHgap(10);
        setVgap(10);

        {
            ColumnConstraints labelColumn = new ColumnConstraints();
            labelColumn.setPercentWidth(15);
            labelColumn.setHgrow(Priority.NEVER);
            getColumnConstraints().add(labelColumn);

            label = new Label(stat.name() + ":");
            label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            label.setAlignment(Pos.CENTER_RIGHT);
            add(label, 0, 0);
        }

        {
            ColumnConstraints sliderColumn = new ColumnConstraints();
            sliderColumn.setPercentWidth(50);
            sliderColumn.setHgrow(Priority.ALWAYS);
            getColumnConstraints().add(sliderColumn);

            slider = new Slider(0, 255, 0);
            slider.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            slider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if (spinner.getValue() != slider.getValue()) {
                        spinner.getValueFactory().setValue((int) slider.getValue());
                    }
                }
            });
            slider.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            add(slider, 1, 0, 3, 1);
        }

        {
            ColumnConstraints spinnerColumn = new ColumnConstraints();
            spinnerColumn.setPercentWidth(10);
            spinnerColumn.setHgrow(Priority.NEVER);
            getColumnConstraints().add(spinnerColumn);

            spinner = new Spinner<Integer>(0, 255, 0);
            spinner.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    if (slider.getValue() != spinner.getValue()) {
                        slider.setValue(spinner.getValue());
                    }
                }
            });
            spinner.setEditable(true);
            add(spinner, 4, 0);
        }
    }

    public void addChangeListener(ChangeListener listener) {
        slider.valueProperty().addListener(listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        slider.valueProperty().removeListener(listener);
    }

    public int getValue() {
        return (int) slider.getValue();
    }

    public void setValue(int value) {
        slider.setValue(value);
        spinner.getValueFactory().setValue(value);
    }
}
