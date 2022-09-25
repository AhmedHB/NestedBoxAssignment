package exception;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {
    R apply(T t) throws Exception;

    static <T, R, E extends Exception> Function<T, R> uncheck(ThrowingFunction<T, R, E> fn) {
        return t -> {
            try {
                return fn.apply(t);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}






