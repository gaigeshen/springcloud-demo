package work.gaigeshen.springcloud.demo.eureka.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.springcloud.demo.commons.PageParameters;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductQueryParameters extends PageParameters {

  private String name;

  private String category;

  private BigDecimal lowPrice;

  private BigDecimal highPricie;
}
