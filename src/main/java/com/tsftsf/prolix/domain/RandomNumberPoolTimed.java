package com.tsftsf.prolix.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

/**
 * Created by tracy on 2/15/15.
 */
public class RandomNumberPoolTimed {

  static Logger logger = LoggerFactory.getLogger(RandomNumberPoolTimed.class);

  private static RandomNumberPoolTimed instance;

  private static Integer randomRunRefreshIntervalSeconds = RandomConfiguration.getRandomRunRefreshIntervalSeconds();

  private Instant previousInstant;
  private RandomNumberRun currentRandomNumberRun;


  private RandomNumberPoolTimed() {
  }

  public static RandomNumberPoolTimed getInstance() {
    if (RandomNumberPoolTimed.instance == null) {
      RandomNumberPoolTimed.instance = new RandomNumberPoolTimed();
    }
    return RandomNumberPoolTimed.instance;
  }

  public RandomNumberRun getRandomNumberRun() {
    if (currentRandomNumberRun == null) {
      currentRandomNumberRun = new RandomNumberRun();
    }
    if (previousInstant == null) {
      previousInstant = Instant.now();
    }
    Instant currentInstant = Instant.now();
    // If a second has elapsed since the last time a run was generated

//    System.out.println(String.format("Difference %d Current instant %d Previous instant %d",
//        (currentInstant.getEpochSecond() - previousInstant.getEpochSecond()),
//        currentInstant.getEpochSecond(),
//        previousInstant.getEpochSecond()));

    if ((currentInstant.getEpochSecond() - previousInstant.getEpochSecond()) > randomRunRefreshIntervalSeconds) {
//      System.out.println("Getting new RandomNumberRun");
      currentRandomNumberRun = new RandomNumberRun();
      previousInstant = currentInstant;
    }
    return currentRandomNumberRun;
  }
}
