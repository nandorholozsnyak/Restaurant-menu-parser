package co.rodnan.restaurant.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class Restaurant {

    String name;
    String identifier;
    String url;
    String logoUrl;
}
