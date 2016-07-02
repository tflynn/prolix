package com.tsftsf.prolix.random;

import net.nullschool.util.DigitalRandom;

/**
 * Proxy for net.nullschool.util.DigitalRandom
 */
public class DigRand {

  private DigitalRandom digitalRandom = null;

  public DigRand() {
    if (digitalRandom == null) {
      this.digitalRandom = new DigitalRandom();
    }
  }

  public DigRand(Long seed) {

  }

  /**
   * Get the next random int
   *
   * @return int value
   */
  public int nextPositiveInt() {
    return this.digitalRandom.nextInt(Integer.MAX_VALUE);
  }

  /**
   * Get the next random long
   *
   * @return int value
   */
  public long nextPositiveLong() {
    return this.digitalRandom.nextLong(Long.MAX_VALUE);
  }

  /**
   * Get the next random double
   *
   * @return double value
   */
  public double nextPositiveDouble() { return this.digitalRandom.nextDouble(0.0, Double.MAX_VALUE); }


  /**
   * Get the next double value as a probability between 0.0 (inc) and 1.0 (exclusive)
   *
   * @return double value
   */
  public double nextDoubleAsProbability() {return this.digitalRandom.nextDouble(0.0, 1.0); }
}
