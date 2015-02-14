package com.tsftsf.profilx.random;

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
}
