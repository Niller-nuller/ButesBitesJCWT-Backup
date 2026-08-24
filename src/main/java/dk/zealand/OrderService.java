package dk.zealand;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static final int MAX_ORDERS = 10;
    private final List<Order> orders = new ArrayList<>();
    private int nextId = 1;

    public Order createOrder(String dishName, int quantity) {
        if (dishName == null || dishName.isBlank()) {
            throw new IllegalArgumentException("Ugyldig ret valgt.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Antallet skal være større end 0.");
        }

        if (orders.size() >= MAX_ORDERS) {
            throw new IllegalStateException("Der kan højst gemmes ti bestillinger.");
        }

        if (!isValidDish(dishName)) {
            throw new IllegalArgumentException("Ugyldig ret valgt.");
        }

        Order order = new Order(nextId++, dishName, quantity, "MODTAGET");
        orders.add(order);
        return order;
    }

    public boolean isValidDish(String dishName) {
        for (Dish dish : new MenuService().getDishes()) {
            if (dish.getName().equalsIgnoreCase(dishName)) {
                return true;
            }
        }
        return false;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }
}
