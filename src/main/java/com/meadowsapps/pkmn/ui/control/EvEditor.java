package com.meadowsapps.pkmn.ui.control;

import com.meadowsapps.pkmn.ui.Component;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Created by Dylan on 9/15/16.
 */
public class EvEditor extends Component {

    /**
     * Slider control
     */
    private Slider slider;

    /**
     * Spinner control
     */
    private Spinner<Integer> spinner;

    /**
     * Initializes the components contained in the component
     *
     * @return the component layout
     */
    @Override
    public Node initComponents() {
        GridPane layoutPane = new GridPane();
        layoutPane.setGridLinesVisible(false);
        layoutPane.setHgap(10);
        layoutPane.setVgap(10);

        // Spinner
        {
            ColumnConstraints spinnerColumn = new ColumnConstraints();
            spinnerColumn.setPercentWidth(30);
            layoutPane.getColumnConstraints().add(spinnerColumn);

            spinner = new Spinner<>(0, 255, 0);
            spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (slider.getValue() != spinner.getValue()) {
                    slider.setValue(spinner.getValue());
                }
            });
            spinner.setEditable(true);
            layoutPane.add(spinner, 0, 0);
        }

        // Slider
        {
            ColumnConstraints sliderColumn = new ColumnConstraints();
            sliderColumn.setPercentWidth(70);
            sliderColumn.setHgrow(Priority.ALWAYS);
            layoutPane.getColumnConstraints().add(sliderColumn);

            slider = new Slider(0, 255, 0);
            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (spinner.getValue() != slider.getValue()) {
                    spinner.getValueFactory().setValue((int) slider.getValue());
                }
            });
            slider.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPane.add(slider, 1, 0);
        }
        return layoutPane;
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
     * Adds the specified <code>ChangeListener</code> to the slider and spinner controls
     *
     * @param listener the listener to add to the controls
     */
    public void addChangeListener(ChangeListener listener) {
        slider.valueProperty().addListener(listener);
    }

    /**
     * Removes the specified <code>ChangeListener</code> from the slider and spinner controls
     *
     * @param listener the listener to remove from the controls
     */
    public void removeChangeListener(ChangeListener listener) {
        slider.valueProperty().removeListener(listener);
    }

    public void bind(IntegerProperty property) {
        slider.valueProperty().bindBidirectional(property);
    }

    public void unbind(IntegerProperty property) {
        slider.valueProperty().unbindBidirectional(property);
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
}
