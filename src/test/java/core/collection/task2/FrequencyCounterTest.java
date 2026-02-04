package core.collection.task2;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FrequencyCounterTest {

    @Test
    void frequencyCounterTest() {
        String[] words = {"apple", "banana", "apple", "cherry", "apple"};

        Map<String, Long> expected = new HashMap<>();
        expected.put("apple", 3L);
        expected.put("banana", 1L);
        expected.put("cherry", 1L);

        Map<String, Long> actual = FrequencyCounter.getFrequencyCounter(words);

        assertEquals(expected, actual);
    }
}