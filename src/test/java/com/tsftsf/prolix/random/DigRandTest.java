package com.tsftsf.prolix.random;

import com.tsftsf.prolix.random.DigRand;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by tracy on 2/14/15.
 */
public class DigRandTest {

  @Test
  public void testNextPositiveInt() {
    DigRand digRand = new DigRand();
    int int1 = digRand.nextPositiveInt();
    int int2 = digRand.nextPositiveInt();
    //System.out.println(String.format("int 1 %d int 2 %d",new Integer(int1), new Integer(int2)));
    assertNotEquals("Two random ints should not be equal", new Integer(int1), new Integer(int2));
  }

  @Test
  public void testNextPositiveLong() {
    DigRand digRand = new DigRand();
    long long1 = digRand.nextPositiveLong();
    long long2 = digRand.nextPositiveLong();
    //System.out.println(String.format("long 1 %d long 2 %d",new Long(long1), new Long(long2)));
    assertNotEquals("Two random longs should not be equal", new Long(long1), new Long(long2));
  }

  @Test
  public void testNextDoubleAsProbability() {
    DigRand digRand = new DigRand();
    Double double1 = digRand.nextDoubleAsProbability();
    Double double2 = digRand.nextDoubleAsProbability();
    //System.out.println(String.format("double 1 %s double 2 %s",double1.toString(), double2.toString()));
    assertNotEquals("Two random doubles should not be equal", double1, double2);
  }
}
