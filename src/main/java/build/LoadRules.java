package build;

import data.input.Box;
import data.input.Rule;
import exception.ThrowingFunction;
import transform.TextToRule;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LoadRules {
    public static List<Rule<Box>> mapToRules(List<String> lines) {
        if(lines == null || lines.isEmpty()){
            return Collections.<Rule<Box>>emptyList();
        }

        return lines.stream()
                .map(ThrowingFunction.uncheck(LoadRules::transform))
                .collect(Collectors.toList());
    }

    private static Rule<Box> transform(String line) throws Exception{
        return TextToRule.transformStrLineToRule(line);
    }
}
