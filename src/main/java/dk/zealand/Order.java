package dk.zealand;

public class Order {
    private final int id;
    private final String dish;
    private final int quantity;
    private final String status;

    public Order(int id, String dish, int quantity, String status) {
        this.id = id;
        this.dish = dish;
        this.quantity = quantity;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDish() {
        return dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }
}
