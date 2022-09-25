package build;

import data.input.Box;
import data.input.Rule;
import exception.ThrowingFunction;
import transform.TextToRule;

import java.util.Collections;
import java.util.List;

public class LoadRules {
    private LoadRules(){}
    public static List<Rule<Box>> mapToRules(List<String> lines) {
        if(lines == null || lines.isEmpty()){
            return Collections.emptyList();
        }

        return lines.stream()
                .map(ThrowingFunction.uncheck(LoadRules::transform))
                .toList();
    }

    private static Rule<Box> transform(String line) throws Exception{
        return TextToRule.transformStrLineToRule(line);
    }
}
