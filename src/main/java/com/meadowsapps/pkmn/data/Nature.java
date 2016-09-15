package com.meadowsapps.pkmn.data;

/**
 * Created by dmeadows on 2/12/15
 */
public enum Nature {
    Adamant,
    Bashful,
    Bold,
    Brave,
    Calm,
    Careful,
    Docile,
    Gentle,
    Hardy,
    Hasty,
    Impish,
    Jolly,
    Lax,
    Lonely,
    Mild,
    Modest,
    Naive,
    Naughty,
    Quiet,
    Quirky,
    Rash,
    Relaxed,
    Sassy,
    Serious,
    Timid;

    public Double[] getModifiers() {
        return DataTable.getNatureTable().getModifier(this);
    }

}
