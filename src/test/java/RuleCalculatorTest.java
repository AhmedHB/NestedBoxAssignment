import build.LoadRules;
import calculate.RuleCalculator;
import data.input.Box;
import data.input.Rule;
import exception.ColoredBoxNotFoundException;
import org.junit.jupiter.api.Test;
import sampledata.SampleData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RuleCalculatorTest {

    @Test
    void testNrBoxesThatCanContainColoredBoxExpect4BoxesOK() {
        List<String> inputList = SampleData.getSampleDataWithShinyGoldChild();
        var rules = LoadRules.mapToRules(inputList);

        assertDoesNotThrow(() -> {
            int nrBoxesThatCanContainShinyBox;
            RuleCalculator rc = new RuleCalculator();
            nrBoxesThatCanContainShinyBox = rc.getNrBoxesThatCanContainColoredBox("shiny gold",rules);
            assertEquals(4, nrBoxesThatCanContainShinyBox);
        } );
    }

    @Test
    void testWhatIfColoredBoxDoesntExistExpectExceptionOK() {
        List<String> inputList = SampleData.getSampleDataWithShinyGoldChild();
        List<Rule<Box>> rules = LoadRules.mapToRules(inputList);

        Throwable throwable =  assertThrows(Throwable.class, () -> {
            RuleCalculator rc = new RuleCalculator();
            rc.getNrBoxesThatCanContainColoredBox("DoesntExist",rules);
        } );
        assertEquals(ColoredBoxNotFoundException.class, throwable.getClass());
    }
}
