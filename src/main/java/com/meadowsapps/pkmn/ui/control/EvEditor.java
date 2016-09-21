package com.meadowsapps.pkmn.ui.control;

import com.meadowsapps.pkmn.data.Stat;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.VPos;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

/**
 * Created by Dylan on 9/15/16.
 */
public class EvEditor extends GridPane implements Initializable {

    /**
     * The stat this editor is associated with
     */
    private Stat stat;

    /**
     * Slider control
     */
    private Slider slider;

    /**
     * Spinner control
     */
    private Spinner<Integer> spinner;

    /**
     * Creates a new EvEditor instance
     */
    public EvEditor(Stat stat) {
        this.stat = stat;
        initialize();
    }

    /**
     * Initializes the components contained in the component
     */
    @Override
    public void initialize() {
        setGridLinesVisible(false);
        setHgap(10);
        setVgap(10);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setValignment(VPos.CENTER);
        rowConstraints.setVgrow(Priority.ALWAYS);
        rowConstraints.setFillHeight(true);
        getRowConstraints().add(rowConstraints);

        // Spinner
        {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setHgrow(Priority.NEVER);
            constraints.setMinWidth(10);
            constraints.setPrefWidth(100);
            getColumnConstraints().add(constraints);

            spinner = new Spinner<>(0, 255, 0);
            spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (slider.getValue() != spinner.getValue()) {
                    slider.setValue(spinner.getValue());
                }
            });
            spinner.setEditable(true);
            add(spinner, 0, 0);
        }

        // Slider
        {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setHgrow(Priority.SOMETIMES);
            constraints.setMinWidth(10);
            constraints.setPrefWidth(100);
            getColumnConstraints().add(constraints);

            slider = new Slider(0, 255, 0);
            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (spinner.getValue() != slider.getValue()) {
                    spinner.getValueFactory().setValue((int) slider.getValue());
                }
            });
            slider.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            add(slider, 1, 0);
        }
    }

    /**
     * Registers the slider to the specified group
     *
     * @param group the group to register the slider to
     */
    public void setSliderGroup(SliderGroup group) {
        group.add(slider);
    }

    /**
     * Gets the value property from the slider
     *
     * @return the slider's value property
     */
    public DoubleProperty valueProperty() {
        return slider.valueProperty();
    }

    /**
     * Gets the value of the editor
     *
     * @return the editor's value
     */
    public int getValue() {
        return (int) slider.getValue();
    }

    /**
     * Sets the value of the editor
     *
     * @param value the new value
     */
    public void setValue(int value) {
        slider.setValue(value);
        spinner.getValueFactory().setValue(value);
    }

    /**
     * Gets the stat this editor is associated with
     *
     * @return the associated stat
     */
    public Stat getStat() {
        return stat;
    }
}
