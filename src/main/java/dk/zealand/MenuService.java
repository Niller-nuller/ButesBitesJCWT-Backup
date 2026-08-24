package dk.zealand;

import java.util.List;

public class MenuService {
    public List<Dish> getDishes() {
        return List.of(
                new Dish("Festivalburger", 59),
                new Dish("Sprøde fritter", 35),
                new Dish("Vegansk bowl", 65)
        );
    }
}
