package co.rodnan.restaurant.adapter.out.web.blahane;

import io.quarkus.arc.config.ConfigProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ConfigProperties(prefix = "blahane")
public class BlahaneMenuProperties {

    private BigDecimal menuPrice;

}
