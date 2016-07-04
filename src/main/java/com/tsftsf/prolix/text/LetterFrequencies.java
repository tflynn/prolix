package com.tsftsf.prolix.text;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.concurrent.ConcurrentMap;

/**
 * Letter frequencies and letter cumulative distribution functions by language
 */
public class LetterFrequencies {

    /*
        Use a String with a single character to represent a single character to allow any legal Unicode "character"
        See discussion in https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html
     */

    /*
        Table of relative frequencies of characters in US English. Case is ignored
     */
    private static Map<String, Double> LANGUAGE_LETTER_FREQUENCIES_ENGLISH_RELATIVE =
            Collections.unmodifiableMap(Stream.of(
                    new AbstractMap.SimpleEntry<>("0", 0.000091),
                    new AbstractMap.SimpleEntry<>("1", 0.000254),
                    new AbstractMap.SimpleEntry<>("2", 0.000048),
                    new AbstractMap.SimpleEntry<>("3", 0.000082),
                    new AbstractMap.SimpleEntry<>("4", 0.000060),
                    new AbstractMap.SimpleEntry<>("5", 0.000060),
                    new AbstractMap.SimpleEntry<>("6", 0.000051),
                    new AbstractMap.SimpleEntry<>("7", 0.000067),
                    new AbstractMap.SimpleEntry<>("8", 0.000054),
                    new AbstractMap.SimpleEntry<>("9", 0.000039),
                    new AbstractMap.SimpleEntry<>("A", 0.001969),
                    new AbstractMap.SimpleEntry<>("B", 0.001080),
                    new AbstractMap.SimpleEntry<>("C", 0.001443),
                    new AbstractMap.SimpleEntry<>("D", 0.000974),
                    new AbstractMap.SimpleEntry<>("E", 0.000947),
                    new AbstractMap.SimpleEntry<>("F", 0.000405),
                    new AbstractMap.SimpleEntry<>("G", 0.000442),
                    new AbstractMap.SimpleEntry<>("H", 0.001894),
                    new AbstractMap.SimpleEntry<>("I", 0.007953),
                    new AbstractMap.SimpleEntry<>("J", 0.000783),
                    new AbstractMap.SimpleEntry<>("K", 0.000036),
                    new AbstractMap.SimpleEntry<>("L", 0.000699),
                    new AbstractMap.SimpleEntry<>("M", 0.002538),
                    new AbstractMap.SimpleEntry<>("N", 0.001089),
                    new AbstractMap.SimpleEntry<>("O", 0.000750),
                    new AbstractMap.SimpleEntry<>("P", 0.001900),
                    new AbstractMap.SimpleEntry<>("Q", 0.000039),
                    new AbstractMap.SimpleEntry<>("R", 0.000393),
                    new AbstractMap.SimpleEntry<>("S", 0.001612),
                    new AbstractMap.SimpleEntry<>("T", 0.002541),
                    new AbstractMap.SimpleEntry<>("U", 0.000188),
                    new AbstractMap.SimpleEntry<>("V", 0.000097),
                    new AbstractMap.SimpleEntry<>("W", 0.001603),
                    new AbstractMap.SimpleEntry<>("X", 0.000030),
                    new AbstractMap.SimpleEntry<>("Y", 0.001270),
                    new AbstractMap.SimpleEntry<>("Z", 0.000017),
                    new AbstractMap.SimpleEntry<>("a", 0.057670),
                    new AbstractMap.SimpleEntry<>("b", 0.009913),
                    new AbstractMap.SimpleEntry<>("c", 0.018304),
                    new AbstractMap.SimpleEntry<>("d", 0.034127),
                    new AbstractMap.SimpleEntry<>("e", 0.094856),
                    new AbstractMap.SimpleEntry<>("f", 0.014967),
                    new AbstractMap.SimpleEntry<>("g", 0.014220),
                    new AbstractMap.SimpleEntry<>("h", 0.044790),
                    new AbstractMap.SimpleEntry<>("i", 0.049482),
                    new AbstractMap.SimpleEntry<>("j", 0.000790),
                    new AbstractMap.SimpleEntry<>("k", 0.006298),
                    new AbstractMap.SimpleEntry<>("l", 0.030476),
                    new AbstractMap.SimpleEntry<>("m", 0.018122),
                    new AbstractMap.SimpleEntry<>("n", 0.050907),
                    new AbstractMap.SimpleEntry<>("o", 0.061297),
                    new AbstractMap.SimpleEntry<>("p", 0.013077),
                    new AbstractMap.SimpleEntry<>("q", 0.001059),
                    new AbstractMap.SimpleEntry<>("r", 0.045459),
                    new AbstractMap.SimpleEntry<>("s", 0.047153),
                    new AbstractMap.SimpleEntry<>("t", 0.069731),
                    new AbstractMap.SimpleEntry<>("u", 0.023507),
                    new AbstractMap.SimpleEntry<>("v", 0.007559),
                    new AbstractMap.SimpleEntry<>("w", 0.017055),
                    new AbstractMap.SimpleEntry<>("x", 0.001364),
                    new AbstractMap.SimpleEntry<>("y", 0.017387),
                    new AbstractMap.SimpleEntry<>("z", 0.000327),
                    new AbstractMap.SimpleEntry<>(" ", 0.054145),
                    new AbstractMap.SimpleEntry<>(",", 0.004459),
                    new AbstractMap.SimpleEntry<>(".", 0.005096)
                    ).collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

    /*
     Map of <language, relative character frequencies> for available
     */
    private static Map<String, Map<String, Double>> LANGUAGE_LETTER_FREQUENCIES_RELATIVE =
            Collections.unmodifiableMap(Stream.of(
                    new AbstractMap.SimpleEntry<>("en_US", LANGUAGE_LETTER_FREQUENCIES_ENGLISH_RELATIVE))
                    .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

    /*
     Get the cumulative distribution function for characters in the default character encoding language
     */
    private static TreeMap<Double, String> getCharacterCDF() {
        String charsetEncoding = Encodings.defaultCharsetEncoding;
        return getCharacterCDF(charsetEncoding);
    }

    /*
     Cumulative distribution function for characters for the current language
     */
    private static Map<String, TreeMap<Double, String>> languageCharacterCDF =
            new HashMap<>();

    /**
     * Get the cumulative distribution function for characters in the specified language. Generate if needed
     *
     * @param language Standard language code
     * @return a Treemap of [cum probability, character] for the specified language
     * @throws IllegalArgumentException if the language is not supported (has no character frequency table)
     */
    public static TreeMap<Double, String> getCharacterCDF(String language) {
        if (!languageCharacterCDF.containsKey(language)) {
            TreeMap<Double, String> charCDF = new TreeMap<>();
            // Calculate sum of relative densities
            // Deal with missing map
            if (!LANGUAGE_LETTER_FREQUENCIES_RELATIVE.containsKey(language)) {
                throw new IllegalArgumentException(
                        String.format("Language %s has no letter frequencies defined", language));
            }
            Map<String, Double> languageLetterFrequenciesRelative =
                    LANGUAGE_LETTER_FREQUENCIES_RELATIVE.get(language);

            double sum = 0.0;
            // Calculate the total of all letter frequencies
            for (Map.Entry<String, Double> entry : languageLetterFrequenciesRelative.entrySet()) {
                sum += entry.getValue();
            }

            // Normalize and order all entry values based on relative frequency
            TreeMap<Double,String> orderedRelativeFrequencies = new TreeMap<>();
            for (Map.Entry<String, Double> entry : languageLetterFrequenciesRelative.entrySet()) {
                orderedRelativeFrequencies.put(entry.getValue() / sum, entry.getKey());
            }

            double partialSum = 0.0;
            //Generate cumulative frequencies between 0.0 and 1.0
            for (Map.Entry<Double, String> entry : orderedRelativeFrequencies.entrySet()) {
                charCDF.put(partialSum / sum, entry.getValue());
                partialSum += entry.getKey(); // cumulative probability
            }
            languageCharacterCDF.put(language, charCDF);
        }
        return languageCharacterCDF.get(language);
    }

    /**
     * Get a random character respecting character frequencies for the specified language
     *
     * @param random   a random double between 0.0 and 1.0
     * @param language Standard language code
     * @return Random character
     * @throws IllegalArgumentException if the language is not supported (has no character frequency table)
     */
    public static String getWeightedRandomCharacter(Double random, String language) {
        return getCharacterCDF(language).higherEntry(random).getValue();
    }

    /**
     * Get a random character respecting character frequencies for the default language
     *
     * @param random a random double between 0.0 and 1.0
     * @return Random character
     * @throws IllegalArgumentException if the language is not supported (has no character frequency table)
     */
    public static String getWeightedRandomCharacter(Double random) {
        String charsetEncoding = Encodings.defaultCharsetEncoding;
        return getWeightedRandomCharacter(random, charsetEncoding);
    }


}
