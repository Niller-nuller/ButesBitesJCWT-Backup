package dk.zealand;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final MenuService MENU_SERVICE = new MenuService();
    private static final OrderService ORDER_SERVICE = new OrderService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("ByteBites – festivalens foodtruck");

        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> showDishes();
                case "2" -> createOrder(scanner);
                case "0" -> running = false;
                default -> System.out.println(
                        "Ugyldigt valg. Vælg 0, 1 eller 2."
                );
            }
        }

        System.out.println("Programmet er afsluttet.");
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Vis retter");
        System.out.println("2. Opret bestilling");
        System.out.println("0. Afslut");
        System.out.print("Vælg: ");
    }

    private static void showDishes() {
        List<Dish> dishes = MENU_SERVICE.getDishes();
        System.out.println("Retter:");

        for (int i = 0; i < dishes.size(); i++) {
            Dish dish = dishes.get(i);
            System.out.printf("%d. %s - %d kr%n", i + 1, dish.getName(), dish.getPrice());
        }
    }

    private static void createOrder(Scanner scanner) {
        List<Dish> dishes = MENU_SERVICE.getDishes();
        System.out.println("Vælg ret:");

        for (int i = 0; i < dishes.size(); i++) {
            Dish dish = dishes.get(i);
            System.out.printf("%d. %s%n", i + 1, dish.getName());
        }

        System.out.print("Ret: ");
        String chosenDish = scanner.nextLine().trim();

        int selectedIndex;
        try {
            selectedIndex = Integer.parseInt(chosenDish);
        } catch (NumberFormatException e) {
            System.out.println("Ugyldig ret valgt. Vælg venligst en af de viste retter.");
            return;
        }

        if (selectedIndex < 1 || selectedIndex > dishes.size()) {
            System.out.println("Ugyldig ret valgt. Vælg venligst en af de viste retter.");
            return;
        }

        System.out.print("Antal: ");
        String quantityInput = scanner.nextLine().trim();
        int quantity;
        try {
            quantity = Integer.parseInt(quantityInput);
        } catch (NumberFormatException e) {
            System.out.println("Ugyldigt antal. Indtast et positivt heltal.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Ugyldigt antal. Antallet skal være større end 0.");
            return;
        }

        try {
            Order order = ORDER_SERVICE.createOrder(dishes.get(selectedIndex - 1).getName(), quantity);
            System.out.println("Bestilling oprettet:");
            System.out.printf("#%d %s x %d - %s%n",
                    order.getId(),
                    order.getDish(),
                    order.getQuantity(),
                    order.getStatus());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
