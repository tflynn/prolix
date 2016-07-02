package com.tsftsf.prolix.text;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class WeightedRandomTest {

    @Test
    public void testWeigthedRandomString() {
        String weightedRandomString1 = WeigthedRandom.getWeigthedRandomString(100);
        String weightedRandomString2 = WeigthedRandom.getWeigthedRandomString(100);
//        System.out.println("weightedRandomString1: " + weightedRandomString1);
//        System.out.println("weightedRandomString2: " + weightedRandomString2);
        assertNotEquals(
                "Two random weighted strings should not be equal" ,
                weightedRandomString1,
                weightedRandomString2);
    }
}
