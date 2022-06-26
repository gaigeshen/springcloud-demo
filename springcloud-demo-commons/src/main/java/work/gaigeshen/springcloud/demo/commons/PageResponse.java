package work.gaigeshen.springcloud.demo.commons;

import lombok.Data;

import java.util.List;

/**
 *
 * @author gaigeshen
 */
@Data
public class PageResponse<C> {

  private int page;

  private int size;

  private int total;

  private List<C> content;

}
