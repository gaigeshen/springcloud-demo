package work.gaigeshen.springcloud.demo.commons;

import java.util.Collections;
import java.util.List;

/**
 * 分页查询结果构建器
 *
 * @author gaigeshen
 */
public abstract class PageResponseCreator {

  /**
   * 无需调用此方法
   */
  private PageResponseCreator() { }

  /**
   * 构建分页查询结果
   *
   * @param pageParameters 分页查询参数
   * @param content 分页查询数据内容
   * @param total 数据总量
   * @return 分页查询结果
   * @param <C> 数据内容的类型
   */
  public static <C> PageResponse<C> create(PageParameters pageParameters, List<C> content, int total) {
    PageResponse<C> response = new PageResponse<>();
    response.setPage(pageParameters.getPage());
    response.setSize(pageParameters.getSize());
    response.setContent(content);
    response.setTotal(total);
    return response;
  }

  /**
   * 构建分页查询结果
   *
   * @param pageParameters 分页查询参数
   * @return 分页查询结果
   * @param <C> 数据内容的类型
   */
  public static <C> PageResponse<C> create(PageParameters pageParameters) {
    return create(pageParameters, Collections.emptyList(), 0);
  }
}
