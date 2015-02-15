package com.tsftsf.prolix.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

/**
 * Created by tracy on 2/15/15.
 */
public class RandomNumberPoolTimed {

  /*
  private class TimerThread extends Thread {

    Logger timerLogger = LoggerFactory.getLogger(TimerThread.class);
    private Instant previousInstant;
    private Long timerWait = RandomConfiguration.getRandomNumberPoolTimerWaitMillis();

    public void run() {
      while (true) {
        try {
          if (previousInstant == null) {
            previousInstant = Instant.now();
          }
          Instant currentInstant = Instant.now();
          // If a second has elapsed since the last messages were queued
          if (currentInstant.getEpochSecond() > previousInstant.getEpochSecond()) {
            // Send messages to queues
            //populateQueue.put(currentInstant);
            //deleteQueue.put(previousInstant);
            System.out.println("TimerThread: Second incremented to " + currentInstant.getEpochSecond());
            previousInstant = currentInstant;
          }
          System.out.println("TimerThread: about to sleep");
          yield();
          sleep(timerWait);
        } catch (Exception e) {
          timerLogger.error("Timer thread was interrupted. Exiting ...",e);
          break;
        }
      }
      System.exit(1);
    }
  }

  private ArrayBlockingQueue<Instant> populateQueue = new ArrayBlockingQueue<Instant>(5);

  private ArrayBlockingQueue<Instant> deleteQueue = new ArrayBlockingQueue<Instant>(5);

  */

  static Logger logger = LoggerFactory.getLogger(RandomNumberPoolTimed.class);

  private Integer bucketSize;

  private Integer totalBuckets;

  private static RandomNumberPoolTimed instance;

  private static Integer randomRunRefreshIntervalSeconds = RandomConfiguration.getRandomRunRefreshIntervalSeconds();

  private RandomNumberPoolTimed() {
    this(RandomConfiguration.getRandomNumberPoolTotalBuckets(),RandomConfiguration.getRandomNumberPoolBucketSize());
  }

  private RandomNumberPoolTimed(Integer totalBuckets, Integer bucketSize) {
    this.totalBuckets = totalBuckets;
    this.bucketSize = bucketSize;
  }

  public static RandomNumberPoolTimed getInstance() {
    if (RandomNumberPoolTimed.instance == null) {
      RandomNumberPoolTimed.instance = new RandomNumberPoolTimed();
    }
    return RandomNumberPoolTimed.instance;
  }

  public static RandomNumberPoolTimed getInstance(Integer totalBuckets,Integer bucketSize) {
    if (RandomNumberPoolTimed.instance == null) {
      RandomNumberPoolTimed.instance = new RandomNumberPoolTimed(totalBuckets,bucketSize);
    }
    return RandomNumberPoolTimed.instance;
  }

  private Instant previousInstant;
  private RandomNumberRun currentRandomNumberRun;

  public RandomNumberRun get() {
    if (currentRandomNumberRun == null) {
      currentRandomNumberRun = new RandomNumberRun();
    }
    if (previousInstant == null) {
      previousInstant = Instant.now();
    }
    Instant currentInstant = Instant.now();
    // If a second has elapsed since the last time a run was generated

    System.out.println(String.format("Difference %d Current instant %d Previous instant %d",
        (currentInstant.getEpochSecond() - previousInstant.getEpochSecond()),
        currentInstant.getEpochSecond(),
        previousInstant.getEpochSecond()));

    if ((currentInstant.getEpochSecond() - previousInstant.getEpochSecond()) > randomRunRefreshIntervalSeconds) {
      System.out.println("Getting new RandomNumberRun");
      currentRandomNumberRun = new RandomNumberRun();
      previousInstant = currentInstant;
    }
    return currentRandomNumberRun;
  }
}
