package co.rodnan.restaurant.adapter.out.web.beerandwurst;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class BeerAndWurstMenuResponse {

    private String price;
    private List<BeerAndWurstMenu> menuItems;

}

