package work.gaigeshen.springcloud.demo.eureka.provider.entity;

import com.querydsl.sql.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {

  @Column("id")
  private Long id;

  @Column("name")
  private String name;

  @Column("price")
  private BigDecimal price;

  @Column("quantity")
  private Integer quantity;

  @Column("category")
  private String category;

  @Column("description")
  private String description;

  @Column("manufacturer")
  private String manufacturer;

  @Column("create_time")
  private Date createTime;

  @Column("update_time")
  private Date updateTime;
}
