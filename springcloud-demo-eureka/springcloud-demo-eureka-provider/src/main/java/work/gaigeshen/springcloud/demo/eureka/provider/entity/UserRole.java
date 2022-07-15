package work.gaigeshen.springcloud.demo.eureka.provider.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author gaigeshen
 */
@Data
// @Entity
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Long userId;

  @Column
  private Long roleId;

  @Column
  private Date createTime;
}
