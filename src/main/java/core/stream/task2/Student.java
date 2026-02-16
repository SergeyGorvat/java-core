package core.stream.task2;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class Student {
    private String name;
    private Map<String, Integer> grades;

    @Override
    public String toString() {
        return "Student: " + "name = " + name + ", grades = " + grades;
    }
}
