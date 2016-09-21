package com.meadowsapps.pkmn.ui.control;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dylan on 9/16/16.
 */
public class SliderGroup implements ChangeListener<Number> {

    private int total;

    private List<Slider> sliders;

    public SliderGroup(int total) {
        this.total = total;
        sliders = new ArrayList<Slider>();
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        if (observable instanceof ReadOnlyProperty) {
            Slider source = (Slider) ((ReadOnlyProperty) observable).getBean();
            int sum = getSum();
            if (newValue.intValue() > oldValue.intValue() && sum > total) {
                int difference = total - sum;
                source.setValue(newValue.intValue() + difference);
            }
        }
    }

    public void add(Slider slider) {
        if (!sliders.contains(slider)) {
            slider.valueProperty().addListener(this);
            sliders.add(slider);
        }
    }

    public void remove(Slider slider) {
        if (sliders.contains(slider)) {
            slider.valueProperty().removeListener(this);
            sliders.remove(slider);
        }
    }

    public int getTotal() {
        return total;
    }

    public int getSum() {
        int sum = 0;
        for (Slider s : sliders) {
            sum += s.getValue();
        }
        return sum;
    }

}
