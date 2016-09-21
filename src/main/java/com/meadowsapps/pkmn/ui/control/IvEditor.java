package com.meadowsapps.pkmn.ui.control;

import com.meadowsapps.pkmn.data.Stat;
import javafx.scene.control.Spinner;

/**
 * Created by dmeadows on 9/21/16.
 */
public class IvEditor extends Spinner<Integer> {

    private Stat stat;

    public IvEditor(Stat stat) {
        super(0, 31, 31);
        this.stat = stat;
    }

    public Stat getStat() {
        return stat;
    }
}
