package core.stream.task1;

import java.util.List;
import java.util.Map;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        System.out.println("ИСХОДНЫЕ ЗАКАЗЫ:");
        orders.forEach(System.out::println);

        System.out.println("");

        StreamOrders streamOrders = new StreamOrders();
        List<Map.Entry<String, Double>> topProducts = streamOrders.getTopProducts(orders, 3);
        streamOrders.printTopProducts(topProducts);
    }
}