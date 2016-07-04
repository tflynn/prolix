package com.tsftsf.prolix.text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tracy Flynn on 7/3/16.
 */
public class TextHelper
{
    /**
     * Convert a string to a list of strings, one per Unicode character, respecting non-BMP Unicode characters
     *
     * @param original String, possibly containing non-BMP characters
     * @return List of strings, one per Unicode character
     */
    public static List<String> stringToListUnicodeStrings(String original) {
        List<String> result = new ArrayList<String>(original.length());
        original.codePoints().forEach(cp -> result.add(new String(Character.toChars((int) cp))));
        return result;
    }
}
