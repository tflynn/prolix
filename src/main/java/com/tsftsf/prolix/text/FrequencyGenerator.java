package com.tsftsf.prolix.text;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * Created by Tracy Flynn on 7/3/16.
 */
public class FrequencyGenerator {

    private String englishSampleTextFileName = "english_text_sample.txt";
    private String relativeResourcePath = "src/main/resources/rawtext";

    private HashMap<String,Long> characterFrequencies = new HashMap<>();
    private TreeMap<String,Double> relativeCharacterFrequencies = new TreeMap<>();

    private void countCharAsString(String s) {
        Long count = 0L;
        if (characterFrequencies.containsKey(s)) {
            count = characterFrequencies.get(s);
        }
        count += 1L;
        characterFrequencies.put(s,count);
    }

    private void processLine(String line) {
        // Process non-BMP characters correctly
        TextHelper.stringToListUnicodeStrings(line).forEach(s -> countCharAsString(s));
    }

    private void generateRelativeFrequencies() {
        double sum = 0.0;
        for(Map.Entry<String, Long> entry : characterFrequencies.entrySet()) {
            sum += (double) entry.getValue();
        }
        for(Map.Entry<String, Long> entry : characterFrequencies.entrySet()) {
            relativeCharacterFrequencies.put(entry.getKey(), ((double) entry.getValue()) / sum );
        }
    }

    private void processFile(String fileName) {
        try {
            String currentDir = Paths.get(".").toAbsolutePath().normalize().toString();
            Path fullFilePath = Paths.get(currentDir, relativeResourcePath, fileName);
            Stream<String> lines = Files.lines(fullFilePath);
            lines.forEach(line -> {
                processLine(line.trim());
            });
            generateRelativeFrequencies();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void outputRelativeFrequencies() {
        for(Map.Entry<String, Double> entry : relativeCharacterFrequencies.entrySet()) {
            System.out.println(
                    String.format("new AbstractMap.SimpleEntry<>(\"%s\", %f),", entry.getKey(), entry.getValue()));
        }
    }

    public void run() {
        processFile(englishSampleTextFileName);
        outputRelativeFrequencies();
    }

    public static void main(String[] args) {
        new FrequencyGenerator().run();
    }
}
