package work.gaigeshen.springcloud.demo.eureka.provider.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private Date createTime;

  @Column
  private Date updateTime;

  @ManyToMany(mappedBy = "roles")
  private Set<User> users = new HashSet<>();
}
