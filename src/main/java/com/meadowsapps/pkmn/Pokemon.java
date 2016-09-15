package com.meadowsapps.pkmn;

/**
 * Created by Dylan on 9/13/16.
 */
public class Pokemon {

    private String name;

    private Nature nature;

    private int level;

    private String form;

    private int[] evs = new int[6];

    private int[] ivs = new int[6];

    public Pokemon() {
        this.level = 50;
        this.nature = Nature.Adamant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getEv(Stat stat) {
        return evs[stat.ordinal()];
    }

    public int[] getEvs() {
        return evs;
    }

    void setEvs(int[] evs) {
        this.evs = evs;
    }

    public void setEv(Stat stat, int ev) {
        evs[stat.ordinal()] = ev;
    }

    public int getIv(Stat stat) {
        return ivs[stat.ordinal()];
    }

    public int[] getIvs() {
        return ivs;
    }

    void setIvs(int[] ivs) {
        this.ivs = ivs;
    }

    public void setIv(Stat stat, int iv) {
        ivs[stat.ordinal()] = iv;
    }
}
