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

    public <B extends Box> String getColorForData(){
        return ((B) data).getColor();
    }

    public <B extends Box> List<String> getColorsForContains(){
        return contains.stream().map(x->x.getColor()).collect(Collectors.toList());
    }

    public boolean contains(String color){
        Optional<T> found = contains.stream().filter(x -> x.getColor().equalsIgnoreCase(color)).findFirst();
        return found.isPresent();
    }
}
