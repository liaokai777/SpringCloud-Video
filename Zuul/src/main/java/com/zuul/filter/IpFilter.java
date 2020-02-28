package com.zuul.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zuul.util.IPUtils;
import com.zuul.util.StringUtils;

public class IpFilter extends ZuulFilter {
	//是否执行该过滤器，true 为执行，false 为不执行，这个也可以利用配置中心来实现，达到动态的开启和关闭过滤器
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}
	//执行自己的业务逻辑
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
        String ip = IPUtils.getIpAddress(ctx.getRequest());
        System.out.println("黑名单过滤器ip"+ip);
		// 在黑名单中禁用
        if (StringUtils.isBlank(ip)) {
        	//在 RequestContext 中设置一个值来标识是否成功，当为 true 的时候，后续的过滤器才执行，若为 false 则不执行。
            ctx.setSendZuulResponse(false);
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        return null;
	}
	/*1）pre
	可以在请求被路由之前调用。适用于身份认证的场景，认证通过后再继续执行下面的流程。
	2）route
	在路由请求时被调用。适用于灰度发布场景，在将要路由的时候可以做一些自定义的逻辑。
	3）post
	在 route 和 error 过滤器之后被调用。这种过滤器将请求路由到达具体的服务之后执行。适用于需要添加响应头，记录响应日志等应用场景。
	4）error
	处理请求时发生错误时被调用。在执行过程中发送错误时会进入 error 过滤器，可以用来统一记录错误信息。*/
	//过滤器类型，可选值有 pre、route、post、error
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}
	//过滤器的执行顺序，数值越小，优先级越高
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
