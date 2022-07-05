package work.gaigeshen.springcloud.demo.eureka.provider.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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

  @Column
  private Date createTime;

  @Column
  private Date updateTime;
}
