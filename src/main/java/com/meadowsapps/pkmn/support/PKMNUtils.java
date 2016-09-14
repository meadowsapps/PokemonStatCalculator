package com.meadowsapps.pkmn.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Class: Utils
 *
 * @author: Dylan
 * Date: 2/12/15
 * Time: 1:24 PM
 * </p>
 * @modifications:
 */
public class PKMNUtils {

    private static final HashMap<String, Integer[]> BASE_STATS = loadBaseStats();

    private static final TreeSet<String> POKEMON = loadPokemon();

    public static int calculateHPStat(int iv, int base, int ev, int level) {
        return (int) Math.floor((((iv + (2 * base) + (ev / 4) + 100) * level) / 100) + 10);
    }

    public static int calculateStat(int iv, int base, int ev, int level, double nature) {
        return (int) Math.floor(Math.floor((((iv + (2 * base) + (ev / 4)) * level) / 100) + 5) * nature);
    }

    /**
     * Returns array of forms if Pokemon has forms
     *
     * @param name
     * @return
     */
    public static ArrayList<String> getForms(String name) {
        ArrayList<String> forms = new ArrayList<String>();

        name = name.toLowerCase();

        switch (name) {
            case "abomasnow":
                forms.add("Male");
                forms.add("Female");
                forms.add("Mega");
                break;
            case "absol":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "aegislash":
                forms.add("Shield");
                forms.add("Blade");
                break;
            case "aerodactyl":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "aggron":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "aipom":
                forms.add("Male");
                forms.add("Female");
                break;
            case "alakazam":
                forms.add("Male");
                forms.add("Female");
                forms.add("Mega");
                break;
            case "altaria":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "ambipom":
                forms.add("Male");
                forms.add("Female");
                break;
            case "ampharos":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "audino":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "banette":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "basculin":
                forms.add("Red");
                forms.add("Blue");
                break;
            case "beautifly":
                forms.add("Male");
                forms.add("Female");
                break;
            case "beedrill":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "bibarel":
                forms.add("Male");
                forms.add("Female");
                break;
            case "bidoof":
                forms.add("Male");
                forms.add("Female");
                break;
            case "blastoise":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "blaziken":
                forms.add("Male");
                forms.add("Female");
                forms.add("Mega");
                break;
            case "buizel":
                forms.add("Male");
                forms.add("Female");
                break;
            case "burmy":
                forms.add("Plant");
                forms.add("Sandy");
                forms.add("Trash");
                break;
            case "butterfree":
                forms.add("Male");
                forms.add("Female");
                break;
            case "cacturne":
                forms.add("Male");
                forms.add("Female");
                break;
            case "camerupt":
                forms.add("Male");
                forms.add("Female");
                forms.add("Mega");
                break;
            case "castform":
                forms.add("Normal");
                forms.add("Rainy");
                forms.add("Snowy");
                forms.add("Sunny");
                break;
            case "charizard":
                forms.add("Normal");
                forms.add("Mega (X)");
                forms.add("Mega (Y)");
                break;
            case "cherrim":
                forms.add("Normal");
                forms.add("Sunshine");
                break;
            case "combee":
                forms.add("Male");
                forms.add("Female");
                break;
            case "combusken":
                forms.add("Male");
                forms.add("Female");
                break;
            case "croagunk":
                forms.add("Male");
                forms.add("Female");
                break;
            case "darmanitan":
                forms.add("Normal");
                forms.add("Zen");
                break;
            case "deerling":
                forms.add("Autumn");
                forms.add("Spring");
                forms.add("Summer");
                forms.add("Winter");
                break;
            case "deoxys":
                forms.add("Normal");
                forms.add("Attack");
                forms.add("Defense");
                forms.add("Speed");
                break;
            case "diancie":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "dodrio":
                forms.add("Male");
                forms.add("Female");
                break;
            case "doduo":
                forms.add("Male");
                forms.add("Female");
                break;
            case "donphan":
                forms.add("Male");
                forms.add("Female");
                break;
            case "dustox":
                forms.add("Male");
                forms.add("Female");
                break;
            case "finneon":
                forms.add("Male");
                forms.add("Female");
                break;
            case "flabebe":
                forms.add("Blue");
                forms.add("Orange");
                forms.add("Red");
                forms.add("White");
                forms.add("Yellow");
                break;
            case "floatzel":
                forms.add("Male");
                forms.add("Female");
                break;
            case "floette":
                forms.add("Blue");
                forms.add("Orange");
                forms.add("Red");
                forms.add("White");
                forms.add("Yellow");
                break;
            case "florges":
                forms.add("Blue");
                forms.add("Orange");
                forms.add("Red");
                forms.add("White");
                forms.add("Yellow");
                break;
            case "frillish":
                forms.add("Male");
                forms.add("Female");
                break;
            case "gabite":
                forms.add("Male");
                forms.add("Female");
                break;
            case "gallade":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "garchomp":
                forms.add("Male");
                forms.add("Female");
                forms.add("Mega");
                break;
            case "gastrodon":
                forms.add("East");
                forms.add("West");
                break;
            case "gengar":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "gible":
                forms.add("Male");
                forms.add("Female");
                break;
            case "girafarig":
                forms.add("Male");
                forms.add("Female");
                break;
            case "giratina":
                forms.add("Altered");
                forms.add("Origin");
                break;
            case "glalie":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "gligar":
                forms.add("Male");
                forms.add("Female");
                break;
            case "gloom":
                forms.add("Male");
                forms.add("Female");
                break;
            case "golbat":
                forms.add("Male");
                forms.add("Female");
                break;
            case "goldeen":
                forms.add("Male");
                forms.add("Female");
                break;
            case "gourgeist":
                forms.add("Small");
                forms.add("Average");
                forms.add("Large");
                forms.add("Super");
                break;
            case "groudon":
                forms.add("Normal");
                forms.add("Primal");
                break;
            case "gulpin":
                forms.add("Male");
                forms.add("Female");
                break;
            case "gyarados":
                forms.add("Male");
                forms.add("Female");
                forms.add("Mega");
                break;
            case "heracross":
                forms.add("Male");
                forms.add("Female");
                forms.add("Mega");
                break;
            case "hippopotas":
                forms.add("Male");
                forms.add("Female");
                break;
            case "hippowdon":
                forms.add("Male");
                forms.add("Female");
                break;
            case "hoopa":
                forms.add("Confined");
                forms.add("Unbound");
                break;
            case "houndoom":
                forms.add("Male");
                forms.add("Female");
                forms.add("Mega");
                break;
            case "hypno":
                forms.add("Male");
                forms.add("Female");
                break;
            case "jellicent":
                forms.add("Male");
                forms.add("Female");
                break;
            case "kadabra":
                forms.add("Male");
                forms.add("Female");
                break;
            case "kangaskhan":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "keldeo":
                forms.add("Ordinary");
                forms.add("Resolute");
                break;
            case "kricketot":
                forms.add("Male");
                forms.add("Female");
                break;
            case "kricketune":
                forms.add("Male");
                forms.add("Female");
                break;
            case "kyogre":
                forms.add("Normal");
                forms.add("Primal");
                break;
            case "kyurem":
                forms.add("Normal");
                forms.add("Black");
                forms.add("White");
                break;
            case "landorus":
                forms.add("Incarnate");
                forms.add("Therian");
                break;
            case "latias":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "latios":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "ledian":
                forms.add("Male");
                forms.add("Female");
                break;
            case "ledyba":
                forms.add("Male");
                forms.add("Female");
                break;
            case "lopunny":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "lucario":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "ludicolo":
                forms.add("Male");
                forms.add("Female");
                break;
            case "lumineon":
                forms.add("Male");
                forms.add("Female");
                break;
            case "luxio":
                forms.add("Male");
                forms.add("Female");
                break;
            case "luxray":
                forms.add("Male");
                forms.add("Female");
                break;
            case "magikarp":
                forms.add("Male");
                forms.add("Female");
                break;
            case "mamoswine":
                forms.add("Male");
                forms.add("Female");
                break;
            case "manectric":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "mawile":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "medicham":
                forms.add("Male");
                forms.add("Female");
                forms.add("Mega");
                break;
            case "meditite":
                forms.add("Male");
                forms.add("Female");
                break;
            case "meganium":
                forms.add("Male");
                forms.add("Female");
                break;
            case "meloetta":
                forms.add("Aria");
                forms.add("Pirouette");
                break;
            case "meowstic":
                forms.add("Male");
                forms.add("Female");
                break;
            case "metagross":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "mewtwo":
                forms.add("Normal");
                forms.add("Mega (X)");
                forms.add("Mega (Y)");
                break;
            case "milotic":
                forms.add("Male");
                forms.add("Female");
                break;
            case "murkrow":
                forms.add("Male");
                forms.add("Female");
                break;
            case "numel":
                forms.add("Male");
                forms.add("Female");
                break;
            case "nuzleaf":
                forms.add("Male");
                forms.add("Female");
                break;
            case "octillery":
                forms.add("Male");
                forms.add("Female");
                break;
            case "pachirisu":
                forms.add("Male");
                forms.add("Female");
                break;
            case "pidgeot":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "pikachu":
                forms.add("Male");
                forms.add("Female");
                forms.add("Cosplay");
                break;
            case "piloswine":
                forms.add("Male");
                forms.add("Female");
                break;
            case "pinsir":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "politoed":
                forms.add("Male");
                forms.add("Female");
                break;
            case "pumpkaboo":
                forms.add("Small");
                forms.add("Average");
                forms.add("Large");
                forms.add("Super");
                break;
            case "pyroar":
                forms.add("Male");
                forms.add("Female");
                break;
            case "quagsire":
                forms.add("Male");
                forms.add("Female");
                break;
            case "raichu":
                forms.add("Male");
                forms.add("Female");
                break;
            case "raticate":
                forms.add("Male");
                forms.add("Female");
                break;
            case "rattata":
                forms.add("Male");
                forms.add("Female");
                break;
            case "rayquaza":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "relicanth":
                forms.add("Male");
                forms.add("Female");
                break;
            case "rhydon":
                forms.add("Male");
                forms.add("Female");
                break;
            case "rhyhorn":
                forms.add("Male");
                forms.add("Female");
                break;
            case "rhyperior":
                forms.add("Male");
                forms.add("Female");
                break;
            case "roselia":
                forms.add("Male");
                forms.add("Female");
                break;
            case "roserade":
                forms.add("Male");
                forms.add("Female");
                break;
            case "rotom":
                forms.add("Normal");
                forms.add("Fan");
                forms.add("Frost");
                forms.add("Heat");
                forms.add("Mow");
                forms.add("Wash");
                break;
            case "sableye":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "salamence":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "sawsbuck":
                forms.add("Autumn");
                forms.add("Spring");
                forms.add("Summer");
                forms.add("Winter");
                break;
            case "sceptile":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "scizor":
                forms.add("Male");
                forms.add("Female");
                forms.add("Mega");
                break;
            case "scyther":
                forms.add("Male");
                forms.add("Female");
                break;
            case "seaking":
                forms.add("Male");
                forms.add("Female");
                break;
            case "sharpedo":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "shaymin":
                forms.add("Land");
                forms.add("Sky");
                break;
            case "shellos":
                forms.add("East");
                forms.add("West");
                break;
            case "shiftry":
                forms.add("Male");
                forms.add("Female");
                break;
            case "shinx":
                forms.add("Male");
                forms.add("Female");
                break;
            case "slowbro":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "sneasel":
                forms.add("Male");
                forms.add("Female");
                break;
            case "snover":
                forms.add("Male");
                forms.add("Female");
                break;
            case "staraptor":
                forms.add("Male");
                forms.add("Female");
                break;
            case "staravia":
                forms.add("Male");
                forms.add("Female");
                break;
            case "starly":
                forms.add("Male");
                forms.add("Female");
                break;
            case "steelix":
                forms.add("Male");
                forms.add("Female");
                forms.add("Mega");
                break;
            case "sudowoodo":
                forms.add("Male");
                forms.add("Female");
                break;
            case "swalot":
                forms.add("Male");
                forms.add("Female");
                break;
            case "swampert":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "tangrowth":
                forms.add("Male");
                forms.add("Female");
                break;
            case "thundurus":
                forms.add("Incarnate");
                forms.add("Therian");
                break;
            case "torchic":
                forms.add("Male");
                forms.add("Female");
                break;
            case "tornadus":
                forms.add("Incarnate");
                forms.add("Therian");
                break;
            case "toxicroak":
                forms.add("Male");
                forms.add("Female");
                break;
            case "tyranitar":
                forms.add("Normal");
                forms.add("Mega");
                break;
            case "unfezant":
                forms.add("Male");
                forms.add("Female");
                break;
            case "unown":
                forms.add("A");
                forms.add("B");
                forms.add("C");
                forms.add("D");
                forms.add("E");
                forms.add("F");
                forms.add("G");
                forms.add("H");
                forms.add("I");
                forms.add("J");
                forms.add("K");
                forms.add("L");
                forms.add("M");
                forms.add("N");
                forms.add("O");
                forms.add("P");
                forms.add("Q");
                forms.add("R");
                forms.add("S");
                forms.add("T");
                forms.add("U");
                forms.add("V");
                forms.add("W");
                forms.add("X");
                forms.add("Y");
                forms.add("Z");
                forms.add("?");
                forms.add("!");
                break;
            case "ursaring":
                forms.add("Male");
                forms.add("Female");
                break;
            case "venusaur":
                forms.add("Male");
                forms.add("Female");
                forms.add("Mega");
                break;
            case "vileplume":
                forms.add("Male");
                forms.add("Female");
                break;
            case "vivillon":
                forms.add("Archipelago");
                forms.add("Continental");
                forms.add("Elegant");
                forms.add("Fancy");
                forms.add("Garden");
                forms.add("High Plains");
                forms.add("Icy Snow");
                forms.add("Jungle");
                forms.add("Marine");
                forms.add("Meadow");
                forms.add("Modern");
                forms.add("Monsoon");
                forms.add("Ocean");
                forms.add("Poké Ball");
                forms.add("Polar");
                forms.add("River");
                forms.add("Sandstorm");
                forms.add("Savannah");
                forms.add("Sun");
                forms.add("Tundra");
                break;
            case "weavile":
                forms.add("Male");
                forms.add("Female");
                break;
            case "wobbuffet":
                forms.add("Male");
                forms.add("Female");
                break;
            case "wooper":
                forms.add("Male");
                forms.add("Female");
                break;
            case "wormadam":
                forms.add("Plant");
                forms.add("Sandy");
                forms.add("Trash");
                break;
            case "xatu":
                forms.add("Male");
                forms.add("Female");
                break;
            case "xerneas":
                forms.add("Active");
                forms.add("Neutral");
                break;
            case "zubat":
                forms.add("Male");
                forms.add("Female");
                break;
            default:
                return null;
        }

        return forms;
    }

    /**
     * @param name
     * @param modifier
     * @return
     */
    public static boolean passesModifierCheck(String name, String modifier) {
        switch (modifier) {
            case "-mega":
                return true;
            case "-megax":
                return true;
            case "-megay":
                return true;
            case "primal":
                return true;
            default:
                switch (name) {
                    case "deoxys":
                        return true;
                    case "wormadam":
                        return true;
                    case "rotom":
                        return true;
                    case "giratina":
                        return true;
                    case "shaymin":
                        return true;
                    case "darmanitan":
                        return true;
                    case "tornadus":
                        return true;
                    case "thundurus":
                        return true;
                    case "landorus":
                        return true;
                    case "kyurem":
                        return true;
                    case "meloetta":
                        return true;
                    case "aegislash":
                        return true;
                    case "pumpkaboo":
                        return true;
                    case "gourgeist":
                        return true;
                    case "hoopa":
                        return true;
                    default:
                        return false;
                }
        }
    }

    /**
     * Reads Pokemon names from pokemon.txt
     *
     * @return pokemon
     */
    public static String[] getPokemon() {
        String[] pokemon = new String[722];
        pokemon[0] = "---";

        try {
            //File file = new File(PKMNUtils.class.getClassLoader().getResource("resources/pokemon.txt").getPath());
            //FileReader fileReader = new FileReader(file);
            InputStream inputStream = PKMNUtils.class.getClassLoader().getResourceAsStream("pokemon.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            for (int i = 1; i < pokemon.length; i++) {
                pokemon[i] = reader.readLine();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return pokemon;
    }

    /**
     * Reads natures from nature.txt
     *
     * @return
     */
    public static String[] getNatures() {
        String[] natures = new String[25];

        try {
            //File file = new File(PKMNUtils.class.getClassLoader().getResource("resources/modifiers.txt").getPath());
            //FileReader fileReader = new FileReader(file);
            InputStream inputStream = PKMNUtils.class.getClassLoader().getResourceAsStream("modifiers.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            for (int i = 0; i < natures.length; i++) {
                natures[i] = reader.readLine();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return natures;
    }

    /**
     * Loads BaseStats into BASE_STATS HashMap from base_stats.txt
     *
     * @return
     */
    private static HashMap<String, Integer[]> loadBaseStats() {
        HashMap<String, Integer[]> baseStats = new HashMap<>(799);

        try {
            //File file = new File(PKMNUtils.class.getClassLoader().getResource("resources/base_stats.txt").getPath());
            //FileReader fileReader = new FileReader(file);
            InputStream inputStream = PKMNUtils.class.getClassLoader().getResourceAsStream("base_stats.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            for (int i = 0; i < 798; i++) {
                String[] data = reader.readLine().split(" ");

                String key = data[0];
                Integer[] stats = new Integer[6];

                for (int j = 1; j < 7; j++) {
                    stats[j - 1] = Integer.parseInt(data[j]);
                }

                baseStats.put(key, stats);
            }

            String noPokemon = "---";
            Integer[] stats = new Integer[6];

            for (int i = 0; i < 6; i++) {
                stats[i] = 0;
            }

            baseStats.put(noPokemon, stats);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return baseStats;
    }

    /**
     * Loads Pokemon int POKEMON ArrayList from pokemon.txt
     *
     * @return
     */
    private static TreeSet<String> loadPokemon() {
        TreeSet<String> pokemon = new TreeSet<>();

        try {
            InputStream inputStream = PKMNUtils.class.getClassLoader().getResourceAsStream("pokemon.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            for (int i = 0; i < 718; i++) {
                pokemon.add(reader.readLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return pokemon;
    }

    /**
     * Accesses Pokemon's base stats from the baseStat HashMap
     *
     * @param key
     * @return
     */
    public static Integer[] getBaseStats(String key) {
        if (BASE_STATS.containsKey(key)) {
            return BASE_STATS.get(key);
        } else {
            throw new IllegalArgumentException("cannot get base stats for " + key);
        }
    }

    /**
     * Searches POKEMON ArrayList
     *
     * @param text
     * @return
     */
    public static Object[] search(String text) {
        Vector<String> results = new Vector<>();

        if (text.equals("")) {
            return results.toArray();
        } else {
            for (String name : POKEMON) {
                if (name.toLowerCase().indexOf(text.toLowerCase()) > -1) {
                    results.add(name);
                }
            }

            if (results.size() == 0) {
                return null;
            } else {
                return results.toArray();
            }
        }
    }

    /**
     * Validates the Pokemon's name to match the format of the sprites
     *
     * @param name
     * @return
     */
    public static String validateName(String name) {
        name = name.toLowerCase();

        switch (name) {
            case "nidoran (f)":
                name = "nidoran_f";
                break;
            case "nidoran (m)":
                name = "nidoran_m";
                break;
            case "farfetch'd":
                name = "farfetchd";
                break;
            case "mr. mime":
                name = "mr._mime";
                break;
            case "mime jr.":
                name = "mime_jr";
                break;
            default:
                break;
        }

        return name;
    }

    public static boolean errorCheck(String name, String form) {
        ArrayList<String> formsArray = getForms(name);

        if (formsArray != null) {
            Object[] forms = formsArray.toArray();
            for (Object object : forms) {
                if (form.equals(object.toString())) {
                    return true;
                }
            }
        } else {
            if (form.equals("---")) {
                return true;
            }
        }

        return false;
    }
}
