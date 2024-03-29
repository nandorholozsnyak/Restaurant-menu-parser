package co.rodnan.restaurant.adapter.in.web.graphql;

import co.rodnan.restaurant.adapter.in.web.domain.DailyMenuInformationType;
import co.rodnan.restaurant.adapter.in.web.domain.MenuItemType;
import co.rodnan.restaurant.adapter.in.web.domain.RestaurantInfoType;
import co.rodnan.restaurant.adapter.in.web.mapper.MenuMapper;
import co.rodnan.restaurant.application.port.in.DailyMenuUseCase;
import co.rodnan.restaurant.domain.MenuInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Source;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@GraphQLApi
@ApplicationScoped
@RequiredArgsConstructor
public class MenuResource {

    private final MenuMapper menuMapper;
    private final DailyMenuUseCase dailyMenuUseCase;

    @Description("Menus of the given restaurant")
    public DailyMenuInformationType dailyMenu(@Source RestaurantInfoType restaurant) {
        log.trace(">> dailyMenu(restaurant:[{}])", restaurant);
        return mapToDailyMenuInformationType(dailyMenuUseCase.getDailyMenuByRestaurantIdentifier(restaurant.getIdentifier()));
    }

    private DailyMenuInformationType mapToDailyMenuInformationType(MenuInformation menuInformation) {
        return DailyMenuInformationType.builder()
                .price(Objects.nonNull(menuInformation.getPrice()) ? menuInformation.getPrice() : BigDecimal.ZERO)
                .menuItems(mapToMenuItemTypeList(menuInformation))
                .build();
    }

    private List<MenuItemType> mapToMenuItemTypeList(MenuInformation menuInformation) {
        return menuInformation.getMenuItems()
                .stream()
                .map(menuMapper::mapToMenuItemType)
                .collect(Collectors.toList());
    }

}
