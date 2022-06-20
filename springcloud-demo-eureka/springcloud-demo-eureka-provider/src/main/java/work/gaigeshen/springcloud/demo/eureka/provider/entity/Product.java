package work.gaigeshen.springcloud.demo.eureka.provider.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private BigDecimal price;

  @Column
  private Integer quantity;

  @Column
  private String category;

  @Column
  private String description;

  @Column
  private String manufacturer;
}
