package co.rodnan.restaurant.adapter.out.web.godor;

import co.rodnan.restaurant.application.port.out.RestaurantMenuParser;
import co.rodnan.restaurant.domain.MenuInformation;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GodorRestaurantMenuParser implements RestaurantMenuParser {

    @Override
    public MenuInformation parseMenu() {
        return MenuInformation.EMPTY;
    }

    @Override
    public String getRestaurantName() {
        return "Gödör";
    }


}
