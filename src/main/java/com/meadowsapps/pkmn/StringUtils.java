package com.meadowsapps.pkmn;

/**
 * Created by Dylan on 9/13/16.
 */
public class StringUtils {

    public static String toCamelCase(String s) {
        StringBuilder builder = new StringBuilder();
        String[] words = s.split("\\s+");
        for (String word : words) {
            builder.append(word.substring(0, 1).toUpperCase());
            builder.append(word.substring(1).toLowerCase());
            builder.append(" ");
        }
        return builder.toString().trim();
    }

}
