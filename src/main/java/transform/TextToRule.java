package transform;

import data.input.Box;
import data.input.Rule;
import exception.InCorrectLinePatternException;
import org.apache.commons.lang3.StringUtils;
import transform.nlp.Stopword;

import java.util.ArrayList;
import java.util.List;

public class TextToRule {
    public static Rule transformStrLineToRule(String line) throws Exception {
        // "light red boxes contain 1 bright white box, 2 muted yellow boxes.",
        List<String> list = new ArrayList<>();
        String[] arrOfStr = line.split("contain", -2);

        if(arrOfStr.length !=2){
            throw new InCorrectLinePatternException("Wrong input line \""+line+"\" format doesnt contain \"contain\"");
        }

        for(String t : arrOfStr){
            t = t.replaceFirst("^ *", "");
            t = t.replaceFirst("\\s+$", "");
            list.add(t);
        }
        if(list.size() == 2) {
            return transform(list.get(0), list.get(1));
        }else {
            throw new InCorrectLinePatternException("Wrong input line, could'nt map line \""+ line + "\" to rule" );
        }
    }

    private static Rule transform(String parentBoxStr, String childrenBoxStr ){
        Box dataBox = getParent(parentBoxStr);
        List<Box> childBoxes = getChildren(childrenBoxStr);

        Rule node = new Rule<Box>();
        node.setData(dataBox);
        node.setContains(childBoxes);

        return node;
    }

    private static Box getParent(String parentStr){
        //parentStr = parentStr.replace("boxes", "").replace("box", "");
        parentStr = removeStopwords(parentStr);
        parentStr = parentStr.replaceFirst("\\s+$", "");
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

                childBoxStr = childBoxStr.replaceAll("\\d","");
                //childBoxStr = childBoxStr.replace(".","");
                //childBoxStr = childBoxStr.replace("boxes", "").replace("box", "");
                childBoxStr = removeStopwords(childBoxStr);
                childBoxStr = childBoxStr.replaceFirst("^ *", "");
                childBoxStr = childBoxStr.replaceFirst("\\s+$", "");

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
        int amount = Integer.parseInt(childBoxStr.replaceAll("[^0-9]", ""));
        return amount;
    }
}
