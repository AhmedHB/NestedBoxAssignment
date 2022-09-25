package transform;

import data.input.Box;
import data.input.Rule;
import exception.InCorrectLinePatternException;
import org.apache.commons.lang3.StringUtils;
import transform.nlp.Stopword;

import java.util.ArrayList;
import java.util.List;

public class TextToRule {

    public static final String S_$ = "\\s+$";
    public static final String REGEX = "^ *";
    public static final String D = "\\d";
    public static final String REGEXNOTNUMMBER = "[^0-9]";

    private TextToRule(){}
    public static Rule<Box> transformStrLineToRule(String line) throws Exception {
        // "light red boxes contain 1 bright white box, 2 muted yellow boxes.",
        List<String> list = new ArrayList<>();
        String[] arrOfStr = line.split("contain", -2);

        if(arrOfStr.length !=2){
            throw new InCorrectLinePatternException("Wrong input line \""+line+"\" format doesnt contain \"contain\"");
        }

        for(String t : arrOfStr){
            t = t.replaceFirst(REGEX, "");
            t = t.replaceFirst(S_$, "");
            list.add(t);
        }
        if(list.size() == 2) {
            return transform(list.get(0), list.get(1));
        }else {
            throw new InCorrectLinePatternException("Wrong input line, could'nt map line \""+ line + "\" to rule" );
        }
    }

    private static Rule<Box> transform(String parentBoxStr, String childrenBoxStr ){
        Box dataBox = getParent(parentBoxStr);
        List<Box> childBoxes = getChildren(childrenBoxStr);
        Rule<Box> node = new Rule<>();
        node.setData(dataBox);
        node.setContains(childBoxes);
        return node;
    }

    private static Box getParent(String parentStr){
        parentStr = removeStopwords(parentStr);
        parentStr = parentStr.replaceFirst(S_$, "");
        Box parentBox = new Box();
        parentBox.setColor(parentStr);
        parentBox.setAmount(1);
        return parentBox;
    }

    private static List<Box> getChildren(String childrenStr){
        List<Box> childBoxes = new ArrayList<>();
        String[] childBoxesStr = childrenStr.split(",");

        for(String childBoxStr : childBoxesStr){
            if(!childBoxStr.contains("no other boxes")){
                Box b = new Box();
                int amount = getAmount(childBoxStr);
                b.setAmount(amount);

                childBoxStr = childBoxStr.replaceAll(D,"");
                childBoxStr = removeStopwords(childBoxStr);
                childBoxStr = childBoxStr.replaceFirst(REGEX, "");
                childBoxStr = childBoxStr.replaceFirst(S_$, "");

                b.setColor(childBoxStr);
                childBoxes.add(b);
            }
        }

        return childBoxes;
    }

    private static String removeStopwords(String str){
        for(String stopword : Stopword.stopwords){
            str = StringUtils.replaceIgnoreCase(str, stopword,"");
        }
        return str;
    }

    private static int getAmount(String childBoxStr){
        return Integer.parseInt(childBoxStr.replaceAll(REGEXNOTNUMMBER, ""));
    }
}
