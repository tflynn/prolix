package com.tsftsf.prolix.text;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Transforms text into various forms
 */
public class TextTransformer {

    private static final Integer DEFAULT_MIN_EXTRA_CHARS = 16;

    private Integer minExtraChars = DEFAULT_MIN_EXTRA_CHARS;

    private static final Integer DEFAULT_MAX_EXTRA_CHARS = 1024;

    private Integer maxExtraChars = DEFAULT_MAX_EXTRA_CHARS;

    private static final Integer DEFAULT_MAX_RANDOMS_PER_SEED = 128;

    private Integer maxRandomsPerSeed = DEFAULT_MAX_RANDOMS_PER_SEED;

    public TextTransformer withMinExtraChars(Integer minExtraChars) {
        this.minExtraChars = minExtraChars;
        return this;
    }

    public TextTransformer withMaxExtraChars(Integer maxExtraChars) {
        this.maxExtraChars = maxExtraChars;
        return this;
    }

    public TextTransformer withMaxRandomsPerSeed(Integer maxRandomsPerSeed) {
        this.maxRandomsPerSeed = maxRandomsPerSeed;
        return this;
    }


    /**
     * Obfuscate text
     *
     * @param text Text to obfuscate
     * @param seeds Random seeds
     * @return Obfuscated text
     */
    public String obfuscate(String text, List<Long> seeds) {

        // Convert list of (strongly) random seeds into a LinkedList so it doesn't need to be indexed
        LinkedList<Long> seedStack = new LinkedList(seeds);

        // Convert text into linied list of unicode strings, one per character
        LinkedList<String> unicodeStrings = new LinkedList(TextHelper.stringToListUnicodeStrings(text));

        StringBuilder result = new StringBuilder(unicodeStrings.size() * 2);

        int pos = 0;
        Random numExtraCharacters = new Random();
        while (unicodeStrings.size() > 0) {
            if (pos % maxRandomsPerSeed == 0) {
                if (seedStack.size() == 0) {
                    throw new RuntimeException("Insufficient number of random seeds to obfuscate amount of text specified");
                }
                numExtraCharacters = new Random(seedStack.pop());
            }
            result.append(unicodeStrings.pop());
            Integer charsToAdd = numExtraCharacters.nextInt(maxExtraChars - minExtraChars) + minExtraChars;
            String randomString = WeigthedRandom.getWeigthedRandomString(charsToAdd);
            result.append(randomString);
            // Only count the position of 'real' characters
            pos += 1;
        }

        return result.toString();
    }

    /**
     * Clarify text
     *
     * @param text Test to clarify
     * @param seeds Random seeds
     * @return Obfuscated text
     */
    public String clarify(String text, List<Long> seeds) {

        // Convert list of (strongly) random seeds into a LinkedList so it doesn't need to be indexed
        LinkedList<Long> seedStack = new LinkedList(seeds);

        // Convert text into linied list of unicode strings, one per character
        LinkedList<String> unicodeStrings = new LinkedList(TextHelper.stringToListUnicodeStrings(text));

        StringBuilder result = new StringBuilder();

        int pos = 0;
        Random numExtraCharacters = new Random();
        while (unicodeStrings.size() > 0) {
            if (pos % maxRandomsPerSeed == 0) {
                if (seedStack.size() == 0) {
                    throw new RuntimeException("Insufficient number of random seeds to clarify amount of text specified");
                }
                numExtraCharacters = new Random(seedStack.pop());
            }
            // First character / string is the one we want
            result.append(unicodeStrings.pop());
            Integer charsToSkip = numExtraCharacters.nextInt(maxExtraChars - minExtraChars) + minExtraChars;
            // Skip the next group of random characters / strings
            IntStream.range(0, charsToSkip ).forEach(i -> unicodeStrings.pop());
            // Only count the position of 'real' characters
            pos += 1;
        }
        return result.toString();
    }

}
