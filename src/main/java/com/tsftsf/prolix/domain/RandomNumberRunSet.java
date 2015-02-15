package com.tsftsf.prolix.domain;

import com.tsftsf.profilx.random.DigRand;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tracy on 2/14/15.
 */
public class RandomNumberRunSet {

  private String version;

  private List<RandomNumberRun> randomNumberRuns = new ArrayList<RandomNumberRun>(RandomConfiguration.getRandomRunSetSize());

  public RandomNumberRunSet() {
    this.version = Version.RANDOM_RUN_SET_VERSION;
    int totalSets = RandomConfiguration.getRandomRunSetSize();
    DigRand identifierDigRand = new DigRand();
    System.out.println("RandomNumberRunSet start " + LocalDateTime.now().toString());
    int totalRandomNumbers = 0 ;
    for (int i = 0 ; i < totalSets; i++) {
      Long randomIdentifier = identifierDigRand.nextPositiveLong();
      RandomNumberRun randomNumberRun = new RandomNumberRun();
      randomNumberRun.setIdentifier(randomIdentifier);
      randomNumberRuns.add(randomNumberRun);
      totalRandomNumbers += randomNumberRun.getRandomNumbers().size();
    }
    System.out.println(String.format("RandomNumberRunSet end %s total random numbers generated %d",LocalDateTime.now().toString(), totalRandomNumbers));

  }

  public RandomNumberRunSet(String version, Long identifier, List<RandomNumberRun> randomNumberRuns) {
    this.version = version;
    this.randomNumberRuns = randomNumberRuns;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public List<RandomNumberRun> getRandomNumberRuns() {
    return randomNumberRuns;
  }

  public void setRandomNumberRuns(List<RandomNumberRun> randomNumberRuns) {
    this.randomNumberRuns = randomNumberRuns;
  }
}
