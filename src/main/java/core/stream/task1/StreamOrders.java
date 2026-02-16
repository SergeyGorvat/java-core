package core.stream.task1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamOrders {
    public List<Map.Entry<String, Double>> getTopProducts(List<Order> orders, int topCount) {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order::getCost)
                ))
                .entrySet().stream()
                .sorted((a, b) ->
                        Double.compare(b.getValue(), a.getValue()))
                .limit(topCount)
                .toList();
    }

    public void printTopProducts(List<Map.Entry<String, Double>> topProducts) {
        System.out.println("ТРИ САМЫХ ДОРОГИХ ТОВАРА:");

        double totalCost = 0;
        int rank = 1;

        for (Map.Entry<String, Double> entry : topProducts) {
            System.out.println("   #" + rank + " " + entry.getKey() +
                    " = " + entry.getValue() + " рублей");
            totalCost += entry.getValue();
            rank++;
        }

        System.out.println("\nОБЩАЯ СТОИМОСТЬ ТОП-3: " + totalCost + " рублей");
    }
}
