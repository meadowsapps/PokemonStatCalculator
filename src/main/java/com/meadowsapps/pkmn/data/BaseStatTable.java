package com.meadowsapps.pkmn.data;

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
 * Created by dmeadows on 9/14/16.
 */
public class BaseStatTable {

    private HashMap<Integer, HashMap<String, Integer[]>> stats;

    private static BaseStatTable table = new BaseStatTable();

    private BaseStatTable() {
        try {
            InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("stats.txt");
            File tmp = File.createTempFile("base_stats", null);
            Files.copy(resourceStream, Paths.get(tmp.toURI()), StandardCopyOption.REPLACE_EXISTING);
            byte[] bytes = Files.readAllBytes(Paths.get(tmp.toURI()));
            String contents = new String(bytes);

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
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public Integer[] getBaseStats(int index, String form) {
        Integer[] rv = null;
        if (stats.containsKey(index)) {
            HashMap<String, Integer[]> forms = stats.get(index);
            rv = (forms.containsKey(form)) ? forms.get(form) : forms.get("");
        }
        return rv;
    }

    public static BaseStatTable getTable() {
        return table;
    }

}
