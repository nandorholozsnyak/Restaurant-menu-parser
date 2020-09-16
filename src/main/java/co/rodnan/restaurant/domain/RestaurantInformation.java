package co.rodnan.restaurant.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class RestaurantInformation {

    String name;
    String identifier;
    String url;
    String logo;

}
