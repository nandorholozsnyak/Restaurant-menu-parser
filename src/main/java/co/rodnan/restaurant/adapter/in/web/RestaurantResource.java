package co.rodnan.restaurant.adapter.in.web;

import co.rodnan.restaurant.application.action.RestaurantAggregatorAction;
import co.rodnan.restaurant.domain.RestaurantListResponse;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import javax.enterprise.context.ApplicationScoped;

@GraphQLApi
@ApplicationScoped
@RequiredArgsConstructor
public class RestaurantResource {

    private final RestaurantAggregatorAction restaurantAggregatorAction;

    @Query
    public RestaurantListResponse getRestaurantMenus() {
        return restaurantAggregatorAction.getRestaurantMenus();
    }

}
