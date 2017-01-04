package org.liuyang.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

	@Override
	public Object run() {
		//业务逻辑
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String accessToken = request.getParameter("request");

		logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

		if (!StringUtils.hasText(accessToken)) {
			logger.warn("access token is empty");
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(401);
			return null;
		}
		logger.info("access token ok");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		//是否过滤
		return true;
	}

	@Override
	public int filterOrder() {
		//过滤顺序
		return 1;
	}

	@Override
	public String filterType() {
		//过滤器类型
		//pre：可以在请求被路由之前调用
		//routing：在路由请求时候被调用
		//post：在routing和error过滤器之后被调用
		//error：处理请求时发生错误时被调用
		return "pre";
	}

}
