package com.meadowsapps.pkmn;

import java.io.InputStream;

/**
 * Created by Dylan on 9/15/16.
 */
public class PkmnUtils {

    public static InputStream getResourceAsStream(String path) {
        return PkmnUtils.class.getClassLoader().getResourceAsStream(path);
    }
}
