package work.gaigeshen.springcloud.demo.eureka.api.commons;

import lombok.Data;

@Data
public class Response<D> {

  private int code;

  private String message;

  private D data;

}
