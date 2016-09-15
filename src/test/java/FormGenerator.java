import com.meadowsapps.pkmn.DataTable;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmeadows on 9/15/16.
 */
public class FormGenerator {

    private static HashMap<String, String> FORMS = new HashMap<String, String>();

    static {
        FORMS.put("m", "Male");
        FORMS.put("f", "Female");
        FORMS.put("megax", "Mega (X)");
        FORMS.put("megay", "Mega (Y)");
        FORMS.put("exclamation", "!");
        FORMS.put("question", "?");
        FORMS.put("icy_snow", "Icy Snow");
        FORMS.put("high_plains", "High Plains");
    }

    public static void main(String[] args) throws Exception {
        URL resource = FormGenerator.class.getClassLoader().getResource("forms.txt");
        byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
        String contents = new String(bytes);

        HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        class FormSorter implements Comparator<String> {

            private HashMap<String, Integer> weight = new HashMap<String, Integer>();

            public FormSorter() {
                weight.put("Male", 1);
                weight.put("Female", 2);
                weight.put("Mega", 3);
                weight.put("Mega (X)", 4);
                weight.put("Mega (Y)", 5);
            }

            @Override
            public int compare(String o1, String o2) {
                if (isCustomSort(o1) && isCustomSort(o2)) {
                    int weight1 = weight.get(o1);
                    int weight2 = weight.get(o2);
                    if (weight1 - weight2 < 0) {
                        return -1;
                    } else if (weight1 - weight2 > 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    return o1.compareTo(o2);
                }
            }

            public boolean isCustomSort(String s) {
                return s.equals("Male") || s.equals("Female") || s.contains("Mega");
            }
        }
        Pattern pattern = Pattern.compile("\\d+:\\{([^\\*])+?\\},*");
        Matcher matcher = pattern.matcher(contents);
        while (matcher.find()) {
            String entry = contents.substring(matcher.start(), matcher.end());
            String[] kv = entry.split(":");
            int key = Integer.parseInt(kv[0]);

            List<String> forms = new ArrayList<String>();
            Pattern formPattern = Pattern.compile("(\\w|\\?|\\!)+(\\s\\(\\w\\))*");
            Matcher formMatcher = formPattern.matcher(kv[1]);
            while (formMatcher.find()) {
                forms.add(kv[1].substring(formMatcher.start(), formMatcher.end()));
            }
            forms.sort(new FormSorter());
            map.put(key, forms);
        }

        StringBuilder builder = new StringBuilder();
        List<Integer> keys = new ArrayList<Integer>(map.keySet());
        Collections.sort(keys);

        for (Integer key : keys) {
            builder.append(key);
            builder.append(":{\n");
            List<String> formsList = map.get(key);
            for (String form : formsList) {
                builder.append("\t");
                builder.append(form);
                builder.append(",\n");
            }
            int index = builder.lastIndexOf(",");
            builder.deleteCharAt(index);
            builder.append("},\n");
        }
        File formsFile = new File("/home/dmeadows/Desktop/forms.txt");
        Files.write(Paths.get(formsFile.toURI()), builder.toString().getBytes(), StandardOpenOption.WRITE);
    }

    private static void generateForms() throws Exception {
        HashMap<Integer, List<String>> forms = new HashMap<Integer, List<String>>();
        File models = new File("/home/dmeadows/Workspace/IDEs/IntelliJ/PokemonStatCalculator/src/main/resources/images/models");
        for (File model : models.listFiles()) {
            String imageName = model.getName().split("\\.")[0];
            if (!imageName.contains("porygon") && !imageName.contains("ho-oh")) {
                String[] names = imageName.split("-");
                String pkmnName = names[0].substring(0, 1).toUpperCase() + names[0].substring(1).toLowerCase();
                String form = (names.length > 1) ? names[1] : null;
                int dexNumber = DataTable.getPokemonTable().getDexNumber(pkmnName);
                if (dexNumber > 0 && form != null) {
                    if (!forms.containsKey(dexNumber)) {
                        forms.put(dexNumber, new ArrayList<String>());
                    }
                    if (FORMS.containsKey(form) && dexNumber != 201) {
                        form = FORMS.get(form);
                    } else {
                        form = form.substring(0, 1).toUpperCase() + names[1].substring(1).toLowerCase();
                    }
                    forms.get(dexNumber).add(form);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        List<Integer> keys = new ArrayList<Integer>(forms.keySet());
        Collections.sort(keys);

        for (Integer key : keys) {
            builder.append(key);
            builder.append(":{\n");
            List<String> formsList = forms.get(key);
            for (String form : formsList) {
                builder.append("\t");
                builder.append(form);
                builder.append(",\n");
            }
            int index = builder.lastIndexOf(",");
            builder.deleteCharAt(index);
            builder.append("},\n");
        }
        File formsFile = new File("/home/dmeadows/Desktop/forms.txt");
        Files.write(Paths.get(formsFile.toURI()), builder.toString().getBytes(), StandardOpenOption.WRITE);
    }
}
