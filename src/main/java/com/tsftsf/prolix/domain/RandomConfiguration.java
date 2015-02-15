package com.tsftsf.prolix.domain;

/**
 * Created by tracy on 2/14/15.
 */
public class RandomConfiguration {

  private static Integer RANDOM_LIST_SIZE = 1000;

  private static Integer RANDOM_RUN_SET_SIZE = 100;

  private static Integer RANDOM_RUN_REFRESH_INTERVAL_SECONDS = 10;

  private static Integer RANDOM_NUMBER_POOL_BUCKET_SIZE = 100000;

  private static Integer RANDOM_NUMBER_POOL_TOTAL_BUCKETS = 5;

  private static Long RANDOM_NUMBER_POOL_TIMER_WAIT_MILLIS = 100L;

  public static Integer getRandomListSize() { return RANDOM_LIST_SIZE;}

  public static Integer getRandomRunSetSize() {return RANDOM_RUN_SET_SIZE;}

  public static Integer getRandomRunRefreshIntervalSeconds() {return RANDOM_RUN_REFRESH_INTERVAL_SECONDS;}

  public static Integer getRandomNumberPoolBucketSize() {return RANDOM_NUMBER_POOL_BUCKET_SIZE;}

  public static Integer getRandomNumberPoolTotalBuckets() {return RANDOM_NUMBER_POOL_TOTAL_BUCKETS;}

  public static Long getRandomNumberPoolTimerWaitMillis() {return RANDOM_NUMBER_POOL_TIMER_WAIT_MILLIS;}

}
