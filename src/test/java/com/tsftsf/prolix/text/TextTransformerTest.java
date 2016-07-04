package com.tsftsf.prolix.text;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import com.tsftsf.prolix.domain.RandomNumberRun;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by Tracy Flynn on 7/3/16.
 */
public class TextTransformerTest {

    @Test
    public void testTextTransformer() {
        List<Long> randomSeeds = new RandomNumberRun().getRandomNumbers();
        String clearText = "something good and jolly to confuse people with";
        TextTransformer textTransformer = new TextTransformer()
                .withMinExtraChars(16)
                .withMaxExtraChars(1024)
                .withMaxRandomsPerSeed(16);
        String obfuscatedText = textTransformer.obfuscate(clearText, randomSeeds);
        String clarifiedText = textTransformer.clarify(obfuscatedText, randomSeeds);
//        System.out.println(String.format("clearText \"%s\"", clearText));
//        System.out.println(String.format("obfuscatedText \"%s\"", obfuscatedText));
//        System.out.println(String.format("clarifiedText \"%s\"", clarifiedText));
        assert(clearText.contentEquals(clarifiedText));
    }

}
