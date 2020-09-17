package co.rodnan.restaurant.adapter.in.web.rest.menu;

import co.rodnan.restaurant.adapter.in.web.domain.DailyMenuInformationType;
import co.rodnan.restaurant.adapter.in.web.domain.DailyMenuResponse;
import co.rodnan.restaurant.adapter.in.web.domain.MenuItemType;
import co.rodnan.restaurant.adapter.in.web.mapper.MenuMapper;
import co.rodnan.restaurant.application.port.in.DailyMenuUseCase;
import co.rodnan.restaurant.domain.MenuInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class MenuRestControllerImpl implements MenuRestController {

    private final MenuMapper menuMapper;
    private final DailyMenuUseCase dailyMenuUseCase;

    @Override
    public DailyMenuResponse getDailyMenu(String identifier) {
        log.trace(">> dailyMenu(identifier:[{}])", identifier);
        return new DailyMenuResponse(mapToDailyMenuInformationType(dailyMenuUseCase.getDailyMenuByRestaurantIdentifier(identifier)));
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
