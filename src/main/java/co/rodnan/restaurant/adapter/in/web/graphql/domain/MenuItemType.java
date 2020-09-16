package co.rodnan.restaurant.adapter.in.web.graphql.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.eclipse.microprofile.graphql.Description;

@Value
@Builder
@RequiredArgsConstructor
public class MenuItemType {

    @Description("Name of the menu item")
    String name;
    @Description("Type of the menu item: SOUP, MAIN_COURSE, DESSERT")
    String type;

}
