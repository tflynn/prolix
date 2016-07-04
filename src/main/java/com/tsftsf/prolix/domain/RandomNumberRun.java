package com.tsftsf.prolix.domain;

import com.tsftsf.prolix.random.DigRand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by tracy on 2/14/15.
 */
public class RandomNumberRun {

    private String version;

    private Long identifier;

    private List<Long> randomNumbers = new ArrayList<Long>(RandomConfiguration.getRandomListSize());

    public RandomNumberRun() {
        DigRand digRand = new DigRand();
        this.version = Version.RANDOM_IMPLEMENTATION_VERSION;
        this.identifier = digRand.nextPositiveLong();
        Integer runTotal = RandomConfiguration.getRandomListSize();
        //System.out.println("Start RandomNumberRun " + LocalDateTime.now().toString());
        this.randomNumbers = IntStream
                .rangeClosed(1, runTotal)
                .mapToObj(i -> digRand.nextPositiveLong())
                .collect(Collectors.toList());
        //System.out.println("End RandomNumberRun " + LocalDateTime.now().toString());
    }

    public RandomNumberRun(String version, List<Long> randomNumbers) {
        this.version = version;
        this.randomNumbers = randomNumbers;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Long> getRandomNumbers() {
        return randomNumbers;
    }

    public void setRandomNumbers(List<Long> randomNumbers) {
        this.randomNumbers = randomNumbers;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }
}
