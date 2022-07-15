package work.gaigeshen.springcloud.demo.eureka.provider.config;

import java.util.ArrayList;

/**
 *
 * @author gaigeshen
 */
public class Page<E> extends ArrayList<E> {

  private final int index;

  private final int size;

  private int total;

  public Page(int index, int size) {
    this.index = index;
    this.size = size;
  }

  public int getIndex() {
    return index;
  }

  public int getSize() {
    return size;
  }

  public int getTotal() {
    return total;
  }

  void setTotal(int total) {
    this.total = total;
  }

}
