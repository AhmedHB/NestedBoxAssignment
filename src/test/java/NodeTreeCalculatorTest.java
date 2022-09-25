import build.LoadRules;
import calculate.ColorBoxCalculator;
import data.input.Box;
import data.input.Rule;
import exception.ColoredBoxNotFoundException;
import org.junit.jupiter.api.Test;
import sampledata.SampleData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NodeTreeCalculatorTest {

    @Test
    void testNrOfChildBoxesContainedExpect126BoxesOK() {
        final String coloredBox = "shiny gold";
        List<String> inputList = SampleData.getSampleDataWithShinyGoldRoot();
        List<Rule<Box>> rules = LoadRules.mapToRules(inputList);

        assertDoesNotThrow(() -> {
            int nrOfChildBoxes = ColorBoxCalculator.getNrOfChildBoxes(coloredBox, rules);
            assertEquals(126,nrOfChildBoxes);
        } );
    }

    @Test
    void testBoxesThatCanContainShinyBoxExpect4BoxesOK() {
        final String coloredBox = "shiny gold";
        List<String> inputList = SampleData.getSampleDataWithShinyGoldChild();
        List<Rule<Box>> rules = LoadRules.mapToRules(inputList);

        assertDoesNotThrow(() -> {
            int nrBoxesThatCanContainShinyBox = ColorBoxCalculator.getNrUniqueColorBoxesThatCanContainInExpectedColorBox(coloredBox, rules);
            assertEquals(4, nrBoxesThatCanContainShinyBox);
        } );
    }

    @Test
    void testWhatIfColoredBoxDoesntExistExpectExceptionOK() {
        final String coloredBox = "DoesntExist";
        List<String> inputList = SampleData.getSampleDataWithShinyGoldChild();
        List<Rule<Box>> rules = LoadRules.mapToRules(inputList);

        Throwable throwable =  assertThrows(Throwable.class, () -> ColorBoxCalculator.getNrOfChildBoxes(coloredBox, rules));
        assertEquals( ColoredBoxNotFoundException.class, throwable.getClass());
    }
}
