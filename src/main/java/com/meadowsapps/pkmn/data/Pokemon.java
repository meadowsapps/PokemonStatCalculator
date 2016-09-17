package com.meadowsapps.pkmn.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Created by Dylan on 9/13/16.
 */
public class Pokemon {

    private StringProperty name;
    private StringProperty nature;
    private IntegerProperty level;
    private StringProperty form;
    private IntegerProperty[] evs;
    private IntegerProperty[] ivs;

    public Pokemon() {
        name = new SimpleStringProperty("");
        nature = new SimpleStringProperty(Nature.Adamant.name());
        level = new SimpleIntegerProperty(50);
        form = new SimpleStringProperty("");
        evs = new IntegerProperty[6];
        ivs = new IntegerProperty[6];
        for (Stat stat : Stat.values()) {
            evs[stat.ordinal()] = new SimpleIntegerProperty(0);
            ivs[stat.ordinal()] = new SimpleIntegerProperty(31);
        }
    }

    public StringProperty getName() {
        return name;
    }

    public StringProperty getNature() {
        return nature;
    }

    public IntegerProperty getLevel() {
        return level;
    }

    public StringProperty getForm() {
        return form;
    }

    public IntegerProperty getEv(Stat stat) {
        return evs[stat.ordinal()];
    }

    public IntegerProperty getIv(Stat stat) {
        return ivs[stat.ordinal()];
    }
}
