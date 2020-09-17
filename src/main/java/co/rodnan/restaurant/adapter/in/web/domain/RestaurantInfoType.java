package co.rodnan.restaurant.adapter.in.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model stores information about the restaurant")
@Description("Model stores information about the restaurant")
public class RestaurantInfoType {

    @Schema(description = "Name of the restaurant")
    @Description("Name of the restaurant")
    private String name;

    @Schema(description = "Identifier of the restaurant")
    @Description("Identifier of the restaurant")
    private String identifier;

    @Schema(description = "URL of the restaurant")
    @Description("URL of the restaurant")
    private String url;

    @Schema(description = "Logo of the restaurant, an URL")
    @Description("Logo of the restaurant, an URL")
    private String logoUrl;
}
