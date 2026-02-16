package core.stream.task2;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelStreamCollectMapAdvancedExample {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        System.out.println("\nИСХОДНЫЕ ДАННЫЕ СТУДЕНТОВ:");
        System.out.println("───────────────────────────────────────────────────────────────");
        students.forEach(System.out::println);


        Map<String, Double> averageGradesBySubject = students.parallelStream()
                .flatMap(student -> student.getGrades()
                        .entrySet().stream()
                        .map(entry -> new AbstractMap.SimpleEntry<>(
                                entry.getKey(),
                                (double) entry.getValue()
                        ))
                )
                .collect(Collectors.groupingByConcurrent(
                        AbstractMap.SimpleEntry::getKey,
                        Collectors.averagingDouble(AbstractMap.SimpleEntry::getValue)
                ));

        System.out.println("\n СРЕДНИЕ ОЦЕНКИ ПО ВСЕМ ПРЕДМЕТАМ:");
        System.out.println("───────────────────────────────────────────────────────────────");

        averageGradesBySubject.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.printf("   %-10s -> %.2f%n",
                        entry.getKey(), entry.getValue()));

        System.out.println("───────────────────────────────────────────────────────────────\n");
    }
}
