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

  private static Integer randomRunSetRefreshIntervalSeconds = RandomConfiguration.getRandomRunSetRefreshIntervalSeconds();

  private Instant randomNumberRunPreviousInstant;

  private RandomNumberRun currentRandomNumberRun;

  private Instant randomNumberRunSetPreviousInstant;

  private RandomNumberRunSet currentRandomNumberRunSet;

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
    if (randomNumberRunPreviousInstant == null) {
      randomNumberRunPreviousInstant = Instant.now();
    }
    Instant currentInstant = Instant.now();
    // If a second has elapsed since the last time a run was generated

//    System.out.println(String.format("Difference %d Current instant %d Previous instant %d",
//        (currentInstant.getEpochSecond() - previousInstant.getEpochSecond()),
//        currentInstant.getEpochSecond(),
//        previousInstant.getEpochSecond()));

    if ((currentInstant.getEpochSecond() - randomNumberRunPreviousInstant.getEpochSecond()) > randomRunRefreshIntervalSeconds) {
//      System.out.println("Getting new RandomNumberRun");
      currentRandomNumberRun = new RandomNumberRun();
      randomNumberRunPreviousInstant = currentInstant;
    }
    return currentRandomNumberRun;
  }

  public RandomNumberRunSet getRandomNumberRunSet() {
    if (currentRandomNumberRunSet == null) {
      currentRandomNumberRunSet = new RandomNumberRunSet();
    }
    if (randomNumberRunSetPreviousInstant == null) {
      randomNumberRunSetPreviousInstant = Instant.now();
    }
    Instant currentInstant = Instant.now();
    // If a second has elapsed since the last time a run was generated

//    System.out.println(String.format("Difference %d Current instant %d Previous instant %d",
//        (currentInstant.getEpochSecond() - previousInstant.getEpochSecond()),
//        currentInstant.getEpochSecond(),
//        previousInstant.getEpochSecond()));

    if ((currentInstant.getEpochSecond() - randomNumberRunSetPreviousInstant.getEpochSecond()) > randomRunSetRefreshIntervalSeconds) {
//      System.out.println("Getting new RandomNumberRun");
      currentRandomNumberRunSet = new RandomNumberRunSet();
      randomNumberRunSetPreviousInstant = currentInstant;
    }
    return currentRandomNumberRunSet;
  }

}
