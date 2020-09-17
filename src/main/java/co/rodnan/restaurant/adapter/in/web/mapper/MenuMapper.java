package co.rodnan.restaurant.adapter.in.web.mapper;

import co.rodnan.restaurant.adapter.in.web.domain.MenuItemType;
import co.rodnan.restaurant.domain.MenuItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MenuMapper {

    MenuItemType mapToMenuItemType(MenuItem menuItem);
}
