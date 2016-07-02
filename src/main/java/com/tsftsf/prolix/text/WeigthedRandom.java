package com.tsftsf.prolix.text;

import com.tsftsf.prolix.random.DigRand;

import java.util.Map;
import java.util.stream.IntStream;

/**
 * Creates random characters and strings that respect character frequencies for the specified language
 */
public class WeigthedRandom {


    /**
     * Get a random character for the default language respecting relative character frequencies
     *
     * @param random a random value between 0.0 and 1.0
     * @return a random character respecting relative character frequencies
     */
    public static Character getWeightedRandomCharacter(Double random) {
        return getWeightedRandomCharacter(random, Encodings.defaultLocaleLanguage);
    }

    /**
     * Get a random character for the specified language respecting relative character frequencies
     *
     * @param random a random value between 0.0 and 1.0
     * @param language Language code - e.g. 'en-US'
     * @return a random character respecting relative character frequencies
     */
    public static Character getWeightedRandomCharacter(Double random, String language) {
        // Allow for case when the random value runs off the end of the CDF function
        // i.e. key is greater than highest value in CDF
        // In that case use the next lower key
        try {
            return LetterFrequencies.getCharacterCDF(language).higherEntry(random).getValue();
        } catch (NullPointerException npe) {
            return LetterFrequencies.getCharacterCDF(language).lowerEntry(random).getValue();
        }

    }

    /**
     * Get a random string for the specified language respecting relative character frequencies
     * @param length the length of the requested string
     * @param language language code - e.g. 'en-US'
     * @return a string of random characters respecting relative character frequencies
     */
    public static String getWeigthedRandomString(Integer length, String language) {
        DigRand digRand = new DigRand();
        StringBuilder sb = new StringBuilder(length);
        IntStream.rangeClosed(1, length).forEach(
            i -> {
                double randomProbablilty = digRand.nextDoubleAsProbability();
                sb.append(getWeightedRandomCharacter(randomProbablilty, language).toString());
            }
        );
        return sb.toString();
    }

    /**
     * Get a random string for the default language respecting relative character frequencies
     * @param length the length of the requested string
     * @return a string of random characters respecting relative character frequencies
     */
    public static String getWeigthedRandomString(Integer length) {
        return getWeigthedRandomString(length,  Encodings.defaultLocaleLanguage);
    }


}
