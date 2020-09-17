package co.rodnan.restaurant.adapter.in.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model containing information about menu items")
@Description("Model containing information about menu items")
public class MenuItemType {

    @Schema(description = "Name of the menu item")
    @Description("Name of the menu item")
    private String name;

    @Schema(description = "Type of the menu item: SOUP, MAIN_COURSE, DESSERT")
    @Description("Type of the menu item: SOUP, MAIN_COURSE, DESSERT")
    private String type;

    @Schema(description = "Price of the menu / if empty it means every menu item has the SAME price")
    @Description("Price of the menu / if empty it means every menu item has the SAME price")
    private BigDecimal price;

}
