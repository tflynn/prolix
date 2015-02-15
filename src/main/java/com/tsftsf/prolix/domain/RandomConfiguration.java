package com.tsftsf.prolix.domain;

/**
 * Created by tracy on 2/14/15.
 */
public class RandomConfiguration {

  private static Integer RANDOM_LIST_SIZE = 1000;

  private static Integer RANDOM_RUN_SET_SIZE = 100;

  private static Integer RANDOM_RUN_REFRESH_INTERVAL_SECONDS = 1; // Default is 1 sec.

  private static Integer RANDOM_RUN_SET_REFRESH_INTERVAL_SECONDS = 5; // Default is 5 sec.

  public static Integer getRandomListSize() { return RANDOM_LIST_SIZE;}

  public static Integer getRandomRunSetSize() {return RANDOM_RUN_SET_SIZE;}

  public static Integer getRandomRunRefreshIntervalSeconds() {return RANDOM_RUN_REFRESH_INTERVAL_SECONDS;}

  public static Integer getRandomRunSetRefreshIntervalSeconds() {return RANDOM_RUN_SET_REFRESH_INTERVAL_SECONDS;}
}
