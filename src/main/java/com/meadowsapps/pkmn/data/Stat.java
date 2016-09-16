package com.meadowsapps.pkmn.data;

import javafx.scene.paint.Color;

/**
 * Created by Dylan on 9/13/16.
 */
public enum Stat {
    HP(Color.valueOf("#FF0000")),
    Attack(Color.valueOf("#F08232")),
    Defense(Color.valueOf("#FAD232")),
    SpAttack(Color.valueOf("#6E90F0")),
    SpDefense(Color.valueOf("#78C850")),
    Speed(Color.valueOf("#FA5A8C"));

    private Color fill;

    Stat(Color fill) {
        this.fill = fill;
    }

    public Color getFill() {
        return fill;
    }
}
