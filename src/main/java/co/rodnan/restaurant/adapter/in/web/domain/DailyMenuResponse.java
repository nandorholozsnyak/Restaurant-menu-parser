package co.rodnan.restaurant.adapter.in.web.domain;

import lombok.Value;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Value
@Schema(description = "Model containing requested restaurant's daily menu")
public class DailyMenuResponse {

    @Schema(description = "Requested daily menu")
    DailyMenuInformationType dailyMenu;
}
