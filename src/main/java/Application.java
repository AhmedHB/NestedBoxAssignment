import build.LoadRules;
import calculate.ColorBoxCalculator;
import data.input.Box;
import data.input.Rule;
import file.ReadFile;

import java.util.List;

public class Application {
    private static final String KEYWORD_INPUT_FILENAME = "src/main/resources/input.txt";
    private static final String KEYWORD_COLORBOX_SHINY_GOLD = "shiny gold";

    public static void main(String[] arg){
        /*  ####  Load rule file ####  */
        List<String> inputList = ReadFile.readLines(KEYWORD_INPUT_FILENAME);
        List<Rule<Box>> rules = LoadRules.mapToRules(inputList);
        System.out.println("Number of rules: " + rules.size());

        /* ####  Q1 #### */
        int nrBoxesThatCanContainShinyBox = 0;
        try {
            nrBoxesThatCanContainShinyBox = ColorBoxCalculator.getNrUniqueColorBoxesThatCanContainInExpectedColorBox(KEYWORD_COLORBOX_SHINY_GOLD, rules);
        } catch (Exception e) {
           e.printStackTrace();
        }
        System.out.println("Answer 1: " + nrBoxesThatCanContainShinyBox + " nr of unique color boxes that can eventually contain " + KEYWORD_COLORBOX_SHINY_GOLD);

        /* ####  Q2 #### */
        int nrOfChildBoxes = 0;
        try {
            nrOfChildBoxes = ColorBoxCalculator.getNrOfChildBoxes(KEYWORD_COLORBOX_SHINY_GOLD, rules);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Answer 2: " + KEYWORD_COLORBOX_SHINY_GOLD + " contains: " + nrOfChildBoxes + " of color boxes");
    }
}