package com.tsftsf.prolix.text;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Created by Tracy Flynn on 7/3/16.
 */
public class TextHelperTest {

    @Test
    public void testStringToListUnicodeStrings() {
        // "AêñüC学𐐀東D"
        String original = new String("A" + "\u00ea" + "\u00f1" + "\u00fc" + "C" + "\u5B66\uD801\uDC00\u6771" + "D");
        List<String> unicodeStrings = TextHelper.stringToListUnicodeStrings(original);
        assertEquals("There should be 9 unicode strings in the test string", 9, unicodeStrings.size());
    }
}
