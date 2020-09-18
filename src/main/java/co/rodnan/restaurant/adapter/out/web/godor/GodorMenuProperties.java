package co.rodnan.restaurant.adapter.out.web.godor;

import io.quarkus.arc.config.ConfigProperties;
import lombok.Data;

import javax.inject.Singleton;
import java.math.BigDecimal;

@Data
@ConfigProperties(prefix = "godor")
public class GodorMenuProperties {

    private BigDecimal menuPrice;

    private BigDecimal soupPrice;

    private BigDecimal mainCoursePrice;

}
