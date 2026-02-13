package core.stream.task1;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Order {
    private String product;
    private double cost;

    @Override
    public String toString() {
        return "Заказ: " + getProduct() + " = " + getCost() + " рублей";
    }
}