import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmeadows on 9/14/16.
 */
public class BaseStatEditor {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        File tmp = createAndCopy("temporary", "stats.txt");
        String contents = readFileAsString(tmp);

        HashMap<Integer, HashMap<String, Integer[]>> stats = new HashMap<Integer, HashMap<String, Integer[]>>();
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
        System.out.println(stop - start);
    }

    private static File createAndCopy(String name, String source) throws IOException {
        InputStream stream = BaseStatEditor.class.getClassLoader().getResourceAsStream(source);
        File tmp = File.createTempFile(name, null);
        Files.copy(stream, Paths.get(tmp.toURI()), StandardCopyOption.REPLACE_EXISTING);
        return tmp;
    }

    private static HashMap<Integer, HashMap<String, Integer[]>> processCsv(String contents) {
        // process lines
        String[] lines = contents.split("\r\n");
        HashMap<Integer, HashMap<String, Integer[]>> stats = new HashMap<Integer, HashMap<String, Integer[]>>();
        for (String line : lines) {
            String[] strings = line.split(",");
            int index = Integer.parseInt(strings[0]);
            if (!stats.containsKey(index)) {
                stats.put(index, new HashMap<String, Integer[]>());
            }
            String form = strings[1];
            Integer[] statArray = new Integer[6];
            for (int i = 0; i < statArray.length; i++) {
                statArray[i] = Integer.parseInt(strings[i + 2]);
            }
            stats.get(index).put(form, statArray);
        }
        return stats;
    }

    private static String getStatsMapAsString(HashMap<Integer, HashMap<String, Integer[]>> map) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 721; i++) {
            builder.append(i);
            builder.append(":");
            builder.append("{\n");
            HashMap<String, Integer[]> statMap = map.get(i);
            Set<String> keys = statMap.keySet();
            for (String key : keys) {
                builder.append("\t");
                builder.append(key);
                builder.append(":");
                builder.append(Arrays.toString(statMap.get(key)));
                builder.append(",\n");
            }
            int index = builder.lastIndexOf(",");
            builder.deleteCharAt(index);
            builder.append("},\n");
        }
        int index = builder.lastIndexOf(",");
        builder.deleteCharAt(index);
        return builder.toString();
    }

    private static String readFileAsString(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
        return new String(bytes);
    }

    private static void writeToFile(String name, String contents) throws IOException {
        URL resource = BaseStatEditor.class.getClassLoader().getResource("");
        File file = new File(resource.getPath() + File.separator + name);
        Files.write(Paths.get(file.toURI()), contents.getBytes(), StandardOpenOption.CREATE_NEW, StandardOpenOption.TRUNCATE_EXISTING);
    }


}
