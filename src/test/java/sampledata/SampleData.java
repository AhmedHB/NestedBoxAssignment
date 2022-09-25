package sampledata;

import java.util.Arrays;
import java.util.List;

public class SampleData {
    public static List<String> getSampleDataWithShinyGoldRoot(){
        String[] sampleData = {
                "shiny gold boxes contain 2 dark red boxes.",
                "dark red boxes contain 2 dark orange boxes.",
                "dark orange boxes contain 2 dark yellow boxes.",
                "dark yellow boxes contain 2 dark green boxes.",
                "dark green boxes contain 2 dark blue boxes.",
                "dark blue boxes contain 2 dark violet boxes.",
                "dark violet boxes contain no other boxes."

        };
        return Arrays.asList(sampleData);
    }

    public static List<String> getSampleDataWithShinyGoldChild(){
        String[] sampleData = {
                "bright white boxes contain 1 shiny gold box.",
                "muted yellow boxes contain 1 shiny gold box, 1 dark green box.",
                "dark orange boxes contain 1 bright white boxes, 1 muted yellow box.",
                "light red boxes contain 1 bright white box, 1 muted yellow box."
        };
        return Arrays.asList(sampleData);
    }

    public static List<String> getWrongFormatedLines(){
        String[] sampleData = {
                ".....",
        };
        return Arrays.asList(sampleData);
    }


}
