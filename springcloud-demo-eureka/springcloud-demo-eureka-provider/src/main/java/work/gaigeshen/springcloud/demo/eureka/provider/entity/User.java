package work.gaigeshen.springcloud.demo.eureka.provider.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String username;

  @Column
  private String password;

  @Column
  private Date createTime;

  @Column
  private Date updateTime;

  @JoinTable(name = "user_role",
          joinColumns = { @JoinColumn(name = "user_id") },
          inverseJoinColumns = { @JoinColumn(name = "role_id") })
  @ManyToMany
  private Set<Role> roles = new HashSet<>();
}
