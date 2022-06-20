package work.gaigeshen.springcloud.demo.eureka.api.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
@Data
public class ProductQueryParameters {

  private String name;

  private String category;

  private BigDecimal lowPrice;

  private BigDecimal highPricie;
}
