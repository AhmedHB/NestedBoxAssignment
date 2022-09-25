import build.LoadRules;
import data.input.Box;
import data.input.Rule;
import org.junit.jupiter.api.Test;
import sampledata.SampleData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadRulesTest {

    @Test
    void testLoadRulesWithNullExpectEmptyRuleListOK(){
        List<Rule<Box>> rules = LoadRules.mapToRules(null);
        assertTrue(rules.isEmpty());
    }

    @Test
    void testLoadRulesWithEmptyListExpectEmptyRuleListOK(){
        List<String> inputList = new ArrayList<>();
        List<Rule<Box>> rules = LoadRules.mapToRules(inputList);
        assertTrue(rules.isEmpty());
    }

    @Test
    void testLoadRulesWithListExpectRulesListOK(){
        List<String> inputList = SampleData.getSampleDataWithShinyGoldRoot();
        List<Rule<Box>> rules = LoadRules.mapToRules(inputList);
        assertFalse(rules.isEmpty());
        assertEquals(7, rules.size());
    }

    @Test
    void testLoadRulesWithWrongDataExpectErrorOK(){
        Throwable throwable =  assertThrows(Throwable.class, () -> {
            List<String> inputList = SampleData.getWrongFormattedLines();
            LoadRules.mapToRules(inputList);
        });
        assertEquals(RuntimeException.class, throwable.getClass());
    }
}
