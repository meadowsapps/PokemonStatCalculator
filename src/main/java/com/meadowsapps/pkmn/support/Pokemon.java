package com.meadowsapps.pkmn.support;

/**
 * Class: Pokemon
 *
 * @author: Dylan
 * Date: 2/12/15
 * Time: 1:32 PM
 * </p>
 * @modifications:
 */
public class Pokemon {
//        extends ValueChangeListener implements Serializable {

//    private File file;
//
//    private String dexNumber;
//    private String name;
//    private Nature nature;
//    private int level;
//    private String form;
//
//    private Integer[] evs = new Integer[6];
//    private Integer[] ivs = new Integer[6];
//
//    private Integer[] baseStats = new Integer[6];
//
//    private Integer[] stats = new Integer[6];
//
//    private EventListenerList listenerList = new EventListenerList();
//    private ValueChangeEvent nameChanged = new ValueChangeEvent(this, "name");
//    private ValueChangeEvent natureChanged = new ValueChangeEvent(this, "nature");
//    private ValueChangeEvent levelChanged = new ValueChangeEvent(this, "level");
//    private ValueChangeEvent formChanged = new ValueChangeEvent(this, "form");
//
//    private ValueChangeEvent evChanged = new ValueChangeEvent(this, "evs");
//    private ValueChangeEvent ivChanged = new ValueChangeEvent(this, "ivs");
//
//    private ValueChangeEvent baseStatChanged = new ValueChangeEvent(this, "base stats");
//
//    private ValueChangeEvent statsChanged = new ValueChangeEvent(this, "stats");
//
//    public Pokemon() {
//        this.dexNumber = "000";
//        this.name = "---";
//        this.level = 50;
//        this.nature = Nature.ADAMANT;
//        this.form = "---";
//
//        for (int i = PKMNUtils.HP; i <= PKMNUtils.SPEED; i++) {
//            baseStats[i] = 0;
//            evs[i] = 0;
//            ivs[i] = 0;
//            stats[i] = 0;
//        }
//
//        this.addValueChangeListener(this);
//    }
//
//    private void calculateStats() {
//        if (ivs != null && baseStats != null && evs != null && nature != null) {
//            stats[PKMNUtils.HP] = PKMNUtils.calculateHPStat(ivs[PKMNUtils.HP], baseStats[PKMNUtils.HP], evs[PKMNUtils.HP], level);
//            for (int i = PKMNUtils.ATTACK; i <= PKMNUtils.SPEED; i++) {
////                stats[i] = PKMNUtils.calculateStat(ivs[i], baseStats[i], evs[i], level, nature.getModifier(i));
//            }
//        }
//
//        fireChange(statsChanged);
//    }
//
//    private void loadBaseStats() {
//        String modifier = getModifier();
//        String name = PKMNUtils.validateName(Pokemon.this.name);
//
//        if (PKMNUtils.errorCheck(name, form) == true) {
//            if (PKMNUtils.passesModifierCheck(name, modifier)) {
//                setBaseStats(PKMNUtils.getBaseStats(name + modifier));
//            } else {
//                setBaseStats(PKMNUtils.getBaseStats(name));
//            }
//        }
//    }
//
//    @Override
//    public void valueChanged(ValueChangeEvent e) {
//        switch (e.getEventDescription()) {
//            case "name":
//                loadBaseStats();
//                break;
//            case "nature":
//                calculateStats();
//                break;
//            case "level":
//                calculateStats();
//                break;
//            case "form":
//                loadBaseStats();
//                break;
//            case "evs":
//                calculateStats();
//                break;
//            case "ivs":
//                calculateStats();
//                break;
//            case "base stats":
//                calculateStats();
//                break;
//            default:
//                break;
//        }
//    }
//
//    public void addValueChangeListener(ValueChangeListener listener) {
//        listenerList.add(ValueChangeListener.class, listener);
//    }
//
//    public void removeValueChangeListener(ValueChangeListener listener) {
//        listenerList.remove(ValueChangeListener.class, listener);
//    }
//
//    private void fireChange(ValueChangeEvent e) {
//        for (ValueChangeListener l : listenerList.getListeners(ValueChangeListener.class)) {
//            l.valueChanged(e);
//        }
//    }
//
//    public File getFile() {
//        return file;
//    }
//
//    public void setFile(File file) {
//        this.file = file;
//    }
//
//    public String getDexNumber() {
//        return dexNumber;
//    }
//
//    public void setDexNumber(int dexNumber) {
//        DecimalFormat format = new DecimalFormat("#000");
//        this.dexNumber = format.format(dexNumber);
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//        fireChange(nameChanged);
//    }
//
//    public Nature getNature() {
//        return nature;
//    }
//
//    public void setNature(Nature nature) {
//        this.nature = nature;
//        fireChange(natureChanged);
//    }
//
//    public void setNature(String nature) {
////        Nature tmp = Nature.getNature(nature);
////
////        if (tmp != null) {
////            setNature(Nature.getNature(nature));
////        } else {
////            throw new IllegalArgumentException("invalid nature");
////        }
//    }
//
//    public int getLevel() {
//        return level;
//    }
//
//    public void setLevel(int level) {
//        if (level <= 0) {
//            throw new IllegalArgumentException("level cannot be less than or equal to 0");
//        } else if (level > 100) {
//            throw new IllegalArgumentException("level cannot be greater than 100");
//        } else {
//            this.level = level;
//            fireChange(levelChanged);
//        }
//    }
//
//    public String getForm() {
//        return form;
//    }
//
//    public void setForm(String form) {
//        this.form = form;
//        fireChange(formChanged);
//    }
//
//    public Integer[] getEVs() {
//        return evs;
//    }
//
//    public int getEV(int stat) {
//        if (stat >= PKMNUtils.HP && stat <= PKMNUtils.SPEED) {
//            return evs[stat];
//        } else {
//            throw new IllegalArgumentException("invalid stat value");
//        }
//    }
//
//    public void setEVs(int[] evs) {
//        int index = 0;
//        for (Integer ev : evs) {
//            setEV(index, ev);
//            index++;
//        }
//    }
//
//    public void setEV(int stat, int ev) {
//        if (stat >= PKMNUtils.HP && stat <= PKMNUtils.SPEED) {
//            if (ev < 0) {
//                throw new IllegalArgumentException("EV cannot be less than 0");
//            } else if (ev > 255) {
//                throw new IllegalArgumentException("EV cannot be greater than 255");
//            } else {
//                evs[stat] = ev;
//                fireChange(evChanged);
//            }
//        } else {
//            throw new IllegalArgumentException("invalid stat value");
//        }
//    }
//
//    public Integer[] getIVs() {
//        return ivs;
//    }
//
//    public int getIV(int stat) {
//        if (stat >= PKMNUtils.HP && stat <= PKMNUtils.SPEED) {
//            return ivs[stat];
//        } else {
//            throw new IllegalArgumentException("invalid stat value");
//        }
//    }
//
//    public void setIVs(int[] ivs) {
//        int index = 0;
//        for (int iv : ivs) {
//            setIV(index, iv);
//            index++;
//        }
//    }
//
//    public void setIV(int stat, int iv) {
//        if (stat >= PKMNUtils.HP && stat <= PKMNUtils.SPEED) {
//            if (iv < 0) {
//                throw new IllegalArgumentException("IV cannot be less than 0");
//            } else if (iv > 31) {
//                throw new IllegalArgumentException("IV cannot be greater than 31");
//            } else {
//                ivs[stat] = iv;
//                fireChange(ivChanged);
//            }
//        } else {
//            throw new IllegalArgumentException("invalid stat value");
//        }
//    }
//
//    public Integer[] getBaseStats() {
//        return baseStats;
//    }
//
//    public int getBaseStat(int stat) {
//        if (stat >= PKMNUtils.HP && stat <= PKMNUtils.SPEED) {
//            return baseStats[stat];
//        } else {
//            throw new IllegalArgumentException("invalid stat value");
//        }
//    }
//
//    public void setBaseStats(final Integer[] baseStats) {
//        this.baseStats = baseStats;
//        fireChange(baseStatChanged);
//    }
//
//    public Integer[] getStats() {
//        return stats;
//    }
//
//    public int getStat(int stat) {
//        if (stat >= PKMNUtils.HP && stat <= PKMNUtils.SPEED) {
//            return stats[stat];
//        } else {
//            throw new IllegalArgumentException("invalid stat value");
//        }
//    }
//
//    public boolean hasListener(EventListener listener) {
//        for (Object l : listenerList.getListenerList()) {
//            if (l.equals(listener)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private String getModifier() {
//        switch (form) {
//            case "---":
//                return "";
//            case "Normal":
//                return "";
//            case "Male":
//                return "-m";
//            case "Female":
//                return "-f";
//            case "Mega":
//                return "-mega";
//            case "Mega (X)":
//                return "-megax";
//            case "Mega (Y)":
//                return "-megay";
//            default:
//                switch (form) {
//                    case "High Plains":
//                        return "-high_plains";
//                    case "Icy Snow":
//                        return "-icy_snow";
//                    case "Poké Ball":
//                        return "-poke_ball";
//                    default:
//                        return "-" + form.toLowerCase();
//                }
//        }
//    }
//
//    public String standardizedName() {
//        String name = PKMNUtils.validateName(this.name);
//        String modifier = getModifier();
//
//        String standard = name + modifier;
//
//        return standard;
//    }

}
