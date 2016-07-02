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
        Table of relative frequencies of characters in US English. Case is ignored
     */
    private static Map<Character, Double> LANGUAGE_LETTER_FREQUENCIES_ENGLISH_RELATIVE =
            Collections.unmodifiableMap(Stream.of(
                    new AbstractMap.SimpleEntry<>('a', 8.167),
                    new AbstractMap.SimpleEntry<>('b', 1.492),
                    new AbstractMap.SimpleEntry<>('c', 2.782),
                    new AbstractMap.SimpleEntry<>('d', 1.492),
                    new AbstractMap.SimpleEntry<>('e', 4.253),
                    new AbstractMap.SimpleEntry<>('f', 1.492),
                    new AbstractMap.SimpleEntry<>('g', 2.015),
                    new AbstractMap.SimpleEntry<>('h', 6.094),
                    new AbstractMap.SimpleEntry<>('i', 6.966),
                    new AbstractMap.SimpleEntry<>('j', 0.153),
                    new AbstractMap.SimpleEntry<>('k', 0.772),
                    new AbstractMap.SimpleEntry<>('l', 4.025),
                    new AbstractMap.SimpleEntry<>('m', 2.406),
                    new AbstractMap.SimpleEntry<>('n', 6.749),
                    new AbstractMap.SimpleEntry<>('o', 7.507),
                    new AbstractMap.SimpleEntry<>('p', 1.929),
                    new AbstractMap.SimpleEntry<>('q', 0.095),
                    new AbstractMap.SimpleEntry<>('r', 5.987),
                    new AbstractMap.SimpleEntry<>('s', 6.327),
                    new AbstractMap.SimpleEntry<>('t', 9.056),
                    new AbstractMap.SimpleEntry<>('u', 2.758),
                    new AbstractMap.SimpleEntry<>('v', 0.978),
                    new AbstractMap.SimpleEntry<>('w', 2.361),
                    new AbstractMap.SimpleEntry<>('x', 0.150),
                    new AbstractMap.SimpleEntry<>('y', 1.974),
                    new AbstractMap.SimpleEntry<>('z', 0.074)
                    )
                    .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

    /*
     Map of <language, relative character frequencies> for available
     */
    private static Map<String, Map<Character, Double>> LANGUAGE_LETTER_FREQUENCIES_RELATIVE =
            Collections.unmodifiableMap(Stream.of(
                    new AbstractMap.SimpleEntry<>("en_US", LANGUAGE_LETTER_FREQUENCIES_ENGLISH_RELATIVE))
                    .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

    /*
     Get the cumulative distribution function for characters in the default character encoding language
     */
    private static TreeMap<Double, Character> getCharacterCDF() {
        String charsetEncoding = Encodings.defaultCharsetEncoding;
        return getCharacterCDF(charsetEncoding);
    }

    /*
     Cumulative distribution function for characters for the current language
     */
    private static Map<String, TreeMap<Double, Character>> languageCharacterCDF =
            new HashMap<>();

    /**
     * Get the cumulative distribution function for characters in the specified language. Generate if needed
     *
     * @param language Standard language code
     * @return a Treemap of [cum probability, character] for the specified language
     * @throws IllegalArgumentException if the language is not supported (has no character frequency table)
     */
    public static TreeMap<Double, Character> getCharacterCDF(String language) {
        if (! languageCharacterCDF.containsKey(language)) {
            double sum = 0.0;
            double partialSum = 0.0;
            TreeMap<Double, Character> charCDF = new TreeMap<>();
            // Calculate sum of relative densities
            // Deal with missing map
            if ( ! LANGUAGE_LETTER_FREQUENCIES_RELATIVE.containsKey(language)) {
                throw new IllegalArgumentException(
                        String.format("Language %s has no letter frequencies defined", language));
            }
            Map<Character, Double> languageLetterFrequenciesRelative =
                    LANGUAGE_LETTER_FREQUENCIES_RELATIVE.get(language);

            // Calculate the total of all letter frequencies
            for(Map.Entry<Character, Double> entry : languageLetterFrequenciesRelative.entrySet()) {
                sum += entry.getValue();
            }
            // Normalize all entry values to a cumulative value between 0.0 and 1.0
            for(Map.Entry<Character, Double> entry : languageLetterFrequenciesRelative.entrySet()) {
                charCDF.put(partialSum / sum, entry.getKey());
                partialSum += entry.getValue(); // cumulative probability
            }
            languageCharacterCDF.put(language,charCDF);
        }
        return languageCharacterCDF.get(language);
    }

    /**
     * Get a random character respecting character frequencies for the specified language
     *
     * @param random a random double between 0.0 and 1.0
     * @param language Standard language code
     * @return Random character
     * @throws IllegalArgumentException if the language is not supported (has no character frequency table)
     */
    public static Character getWeightedRandomCharacter(Double random, String language) {
        return getCharacterCDF(language).higherEntry(random).getValue();
    }

    /**
     * Get a random character respecting character frequencies for the default language
     *
     * @param random a random double between 0.0 and 1.0
     * @return Random character
     * @throws IllegalArgumentException if the language is not supported (has no character frequency table)
     */
    public static Character getWeightedRandomCharacter(Double random) {
        String charsetEncoding = Encodings.defaultCharsetEncoding;
        return getWeightedRandomCharacter(random, charsetEncoding);
    }


}
