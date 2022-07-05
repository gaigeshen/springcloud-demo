package work.gaigeshen.springcloud.demo.eureka.api.dto.product;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
@Data
public class ProductQueryResponse {

  private Long id;

  private String name;

  private BigDecimal price;

  private Integer quantity;

  private String category;

  private String description;

  private String manufacturer;

}
