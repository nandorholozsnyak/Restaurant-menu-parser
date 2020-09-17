package co.rodnan.restaurant.adapter.in.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model stores information about the menu of the restaurant")
@Description("Model stores information about the menu of the restaurant")
public class DailyMenuInformationType {

    @Schema(description = "Price of the daily menu / if empty it means every menu item has different prices")
    @Description("Price of the daily menu / if empty it means every menu item has different prices")
    private BigDecimal price;

    @Schema(description = "List of the menu items")
    @Description("List of the menu items")
    private List<MenuItemType> menuItems;
}
