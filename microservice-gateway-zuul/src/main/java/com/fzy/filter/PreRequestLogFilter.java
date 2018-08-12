package com.fzy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Fucai
 * @date 2018/8/11
 */

public class PreRequestLogFilter extends ZuulFilter {

  private static final Logger logger=LoggerFactory.getLogger(PreRequestLogFilter.class);

  @Override
  public String filterType() {
    /**
     * 返回的类型可为： pre、route、post、error
     */
    return "pre";
  }

  @Override
  public int filterOrder() {
    /**
     * 返回一个int值来指定执行顺序
     */
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    /**
     * 返回一个boolean值来指定是否需要执行过滤器
     */
    return true;
  }

  @Override
  public Object run() {
    RequestContext context=RequestContext.getCurrentContext();
    HttpServletRequest request=context.getRequest();
    PreRequestLogFilter.logger.info(String.format("send %s request to %s",request.getMethod(),request.getRequestURL().toString()));
    return null;
  }
}
