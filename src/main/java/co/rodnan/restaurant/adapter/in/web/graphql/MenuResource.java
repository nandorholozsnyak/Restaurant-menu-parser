package co.rodnan.restaurant.adapter.in.web.graphql;

import co.rodnan.restaurant.adapter.in.web.graphql.domain.MenuItemType;
import co.rodnan.restaurant.adapter.in.web.graphql.domain.RestaurantInfoType;
import co.rodnan.restaurant.application.port.in.DailyMenuUseCase;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Source;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@GraphQLApi
@ApplicationScoped
@RequiredArgsConstructor
public class MenuResource {

    private final DailyMenuUseCase dailyMenuUseCase;

    @Description("Menus of the given restaurant")
    public List<MenuItemType> dailyMenu(@Source RestaurantInfoType restaurant) {
        return dailyMenuUseCase.getDailyMenuByRestaurantIdentifier(restaurant.getIdentifier())
                .stream()
                .map(menuItem -> MenuItemType.builder()
                        .name(menuItem.getName())
                        .type(menuItem.getType().name())
                        .build())
                .collect(Collectors.toList());
    }

}
