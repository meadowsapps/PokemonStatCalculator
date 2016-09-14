package com.meadowsapps.pkmn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by dmeadows on 2/12/15
 */
public enum Nature {
    ADAMANT,
    BASHFUL,
    BOLD,
    BRAVE,
    CALM,
    CAREFUL,
    DOCILE,
    GENTLE,
    HARDY,
    HASTY,
    IMPISH,
    JOLLY,
    LAX,
    LONELY,
    MILD,
    MODEST,
    NAIVE,
    NAUGHTY,
    QUIET,
    QUIRKY,
    RASH,
    RELAXED,
    SASSY,
    SERIOUS,
    TIMID;

    private static HashMap<Nature, Double[]> modifiers = new HashMap<Nature, Double[]>();

    static {
        InputStream stream = Nature.class.getClassLoader().getResourceAsStream("nature_modifiers.txt");
        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);

        int counter = 0;
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                Double[] modifiers = new Double[6];

                int stat = 0;
                String[] mods = line.split(", ");
                for (String mod : mods) {
                    modifiers[stat] = Double.parseDouble(mod);
                    stat++;
                }
                Nature.modifiers.put(values()[counter], modifiers);
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private String name;

    Nature() {
        name = StringUtils.toCamelCase(name());
    }

    @Override
    public String toString() {
        return name;
    }

    public Double[] getModifiers() {
        return modifiers.get(this);
    }

}
