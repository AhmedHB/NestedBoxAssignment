package rules;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchRuleUtil {
    private SearchRuleUtil(){}
    public static <T> List<T> search(Collection<T> collectionObject,
                                     Function<T, String> searchPropertyAccessor, String searchText) {

        return collectionObject.stream()
                .filter(item -> Objects.equals(searchPropertyAccessor.apply(item), searchText))
                .collect(Collectors.toList());
    }

    public static <T> List<T> searchInList(Collection<T> collectionObject,  Function<T, List<String>> searchPropertyAccessor, String searchText) {
        return collectionObject.stream()
                .filter(item -> contains(searchPropertyAccessor.apply(item), searchText))
                .collect(Collectors.toList());
    }

    private static <T> boolean contains(List<T> list,String searchText ){
        return list.contains(searchText);
    }
}
