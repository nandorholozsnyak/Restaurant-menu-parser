package co.rodnan.restaurant.adapter.in.web.mapper;

import co.rodnan.restaurant.adapter.in.web.domain.RestaurantInfoType;
import co.rodnan.restaurant.domain.Restaurant;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RestaurantMapper {

    RestaurantInfoType mapToInfoType(Restaurant restaurant);

}
