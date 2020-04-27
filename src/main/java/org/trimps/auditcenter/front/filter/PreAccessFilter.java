package org.trimps.auditcenter.front.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: front
 * @description: ${description}
 * @author: cys
 * @create: 2020-04-27 20:54
 **/
@Component
public class PreAccessFilter extends ZuulFilter {

    private boolean shouldFilter = true;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        //优先级，数字越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否执行该过滤器，true代表需要过滤
        return shouldFilter;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println(request.getMethod());
        System.out.println(request.getRequestURL().toString());
        System.out.println(request.getMethod());

        ResponseEntity<String> headerResponseEntity = restTemplate.getForEntity("http://localhost:8080/hello",String.class);



        ctx.addZuulRequestHeader("testheader",headerResponseEntity.getBody());



        return null;
    }
}
