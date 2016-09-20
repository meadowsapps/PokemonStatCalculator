package com.meadowsapps.pkmn.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    public static final String NAME = "Name";
    public static final String NATURE = "Nature";
    public static final String LEVEL = "Level";
    public static final String FORM = "Form";
    public static final String EV = "EV:";
    public static final String IV = "IV:";

    public Pokemon() {
        String name = DataTable.getPokemonTable().getPokemon(1);
        this.name = new SimpleStringProperty(null, NAME, name);
        nature = new SimpleStringProperty(null, NATURE, Nature.Adamant.name());
        level = new SimpleIntegerProperty(null, LEVEL, 50);
        form = new SimpleStringProperty(null, FORM, "");
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
