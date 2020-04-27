package org.trimps.auditcenter.front.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

/**
 * @program: front
 * @description: ${description}
 * @author: cys
 * @create: 2020-04-27 21:15
 **/
@Component
public class ErrorFilter extends ZuulFilter {


    @Override
    public String filterType() {
        //异常过滤器
        return "error";
    }

    @Override
    public int filterOrder() {
        //优先级，数字越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否执行该过滤器，true代表需要过滤
        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        System.out.println(ctx.getResponseBody());

        ctx.setResponseBody("出现异常");

        return null;

    }
}
