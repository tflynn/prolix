package com.tsftsf.prolix.domain;

/**
 * Created by tracy on 2/14/15.
 */
public class RandomConfiguration {

  private static Integer RANDOM_LIST_SIZE = 1000;

  private static Integer RANDOM_RUN_SET_SIZE = 100;

  private static Integer RANDOM_RUN_REFRESH_INTERVAL_SECONDS = 10; // Default is 1 sec.

  public static Integer getRandomListSize() { return RANDOM_LIST_SIZE;}

  public static Integer getRandomRunSetSize() {return RANDOM_RUN_SET_SIZE;}

  public static Integer getRandomRunRefreshIntervalSeconds() {return RANDOM_RUN_REFRESH_INTERVAL_SECONDS;}

}
