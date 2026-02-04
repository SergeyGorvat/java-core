package core.collection.task2;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyCounter {
    public static <T> Map<T, Long> getFrequencyCounter(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }

        return Arrays.stream(array)
                .collect(Collectors.groupingBy(
                        element -> element,
                        Collectors.counting()
                ));

    }
}
