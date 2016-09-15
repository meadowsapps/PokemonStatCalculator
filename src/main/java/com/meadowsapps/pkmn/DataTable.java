package com.meadowsapps.pkmn;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dylan on 9/14/16.
 */
public abstract class DataTable {

    private static PokemonTable pokemonTable = new PokemonTable();

    private static BaseStatTable baseStatTable = new BaseStatTable();

    private static NatureTable natureTable = new NatureTable();

    protected DataTable(String path) {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
            File file = File.createTempFile("pkmn", null);
            Files.copy(stream, Paths.get(file.toURI()), StandardCopyOption.REPLACE_EXISTING);
            byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
            String contents = new String(bytes);
            process(contents);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    protected abstract void process(String contents);

    public static PokemonTable getPokemonTable() {
        return pokemonTable;
    }

    public static BaseStatTable getBaseStatTable() {
        return baseStatTable;
    }

    public static NatureTable getNatureTable() {
        return natureTable;
    }

    public static class PokemonTable extends DataTable {

        private String[] pokemon;

        public static final int NUMBER_OF_POKEMON = 721;

        private PokemonTable() {
            super("pokemon.txt");
        }

        @Override
        protected void process(String contents) {
            long start = System.currentTimeMillis();
            pokemon = new String[NUMBER_OF_POKEMON];
            int counter = 0;
            String[] lines = contents.split("\n");
            for (String line : lines) {
                pokemon[counter] = line.trim();
                counter++;
            }
            long stop = System.currentTimeMillis();
            System.out.println("Pokemon Table: " + (stop - start));
        }

        public String[] getPokemon() {
            return pokemon;
        }

        public String getPokemon(int dexNumber) {
            return pokemon[dexNumber - 1];
        }
    }

    public static class BaseStatTable extends DataTable {

        private HashMap<Integer, HashMap<String, Integer[]>> stats;

        private BaseStatTable() {
            super("stats.txt");
        }

        @Override
        protected void process(String contents) {
            long start = System.currentTimeMillis();
            stats = new HashMap<Integer, HashMap<String, Integer[]>>();
            Pattern pattern = Pattern.compile("\\d+:\\{([^\\*]+?)\\},*");
            Matcher matcher = pattern.matcher(contents);
            while (matcher.find()) {
                String entry = contents.substring(matcher.start(), matcher.end());
                int key = Integer.parseInt(entry.substring(0, entry.indexOf(':')));
                if (!stats.containsKey(key)) {
                    stats.put(key, new HashMap<String, Integer[]>());
                }
                Pattern formPattern = Pattern.compile("(.*):\\[(.*)+?\\]");
                Matcher formMatcher = formPattern.matcher(entry);
                while (formMatcher.find()) {
                    String form = entry.substring(formMatcher.start(), formMatcher.end()).trim();
                    String[] strings = form.split(":");

                    String formKey = strings[0];
                    Integer[] statArray = new Integer[6];
                    String[] statStringArray = strings[1].substring(1, strings[1].length() - 1).split(",");
                    for (int i = 0; i < statArray.length; i++) {
                        statArray[i] = Integer.parseInt(statStringArray[i].trim());
                    }
                    stats.get(key).put(formKey, statArray);
                }
            }
            long stop = System.currentTimeMillis();
            System.out.println("Base Stat Table: " + (stop - start));
        }

        public Integer[] getBaseStats(int index, String form) {
            Integer[] rv = null;
            if (stats.containsKey(index)) {
                HashMap<String, Integer[]> forms = stats.get(index);
                rv = (forms.containsKey(form)) ? forms.get(form) : forms.get("");
            }
            return rv;
        }
    }

    public static class NatureTable extends DataTable {

        private HashMap<String, Double[]> natures;

        private NatureTable() {
            super("natures.txt");
        }

        @Override
        protected void process(String contents) {
            long start = System.currentTimeMillis();
            natures = new HashMap<String, Double[]>();
            String[] lines = contents.split("\n");
            for (String line : lines) {
                line = line.trim();
                String[] kv = line.split(":");
                String key = kv[0];
                Double[] modifiers = new Double[5];

                int counter = 0;
                Pattern pattern = Pattern.compile("\\d+\\.*\\d+");
                Matcher matcher = pattern.matcher(kv[1]);
                while (matcher.find()) {
                    String value = kv[1].substring(matcher.start(), matcher.end());
                    modifiers[counter] = Double.parseDouble(value);
                    counter++;
                }
                natures.put(key, modifiers);
            }
            long stop = System.currentTimeMillis();
            System.out.println("Nature Table: " + (stop - start));
        }

        public Double[] getModifier(Nature nature) {
            return natures.get(nature.name());
        }
    }

}
