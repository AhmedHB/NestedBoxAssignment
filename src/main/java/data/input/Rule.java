package data.input;

import lombok.Data;
import lombok.ToString;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@ToString
public class Rule<T extends Box>  {
    private T data;
    private List<T> contains;

    public String getColorForData(){
        return data.getColor();
    }

    public List<String> getColorsForContains(){
        return contains.stream().map(Box::getColor).collect(Collectors.toList());
    }

    public boolean contains(String color){
        Optional<T> found = contains.stream().filter(x -> x.getColor().equalsIgnoreCase(color)).findFirst();
        return found.isPresent();
    }
}
