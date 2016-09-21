package com.meadowsapps.pkmn.ui.control;

import com.meadowsapps.pkmn.data.Stat;
import javafx.scene.control.Label;

/**
 * Created by dmeadows on 9/21/16.
 */
public class StatLabel extends Label {

    private Stat stat;

    public StatLabel(Stat stat) {
        this.stat = stat;
    }

    public int getValue() {
        return Integer.parseInt(getText());
    }

    public void setValue(int value) {
        setText(Integer.toString(value));
    }

    public Stat getStat() {
        return stat;
    }
}
