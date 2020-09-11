package co.rodnan.restaurant.domain;

import lombok.Value;

import java.util.List;

@Value
public class RestaurantListResponse {

    List<Restaurant> restaurants;

}
