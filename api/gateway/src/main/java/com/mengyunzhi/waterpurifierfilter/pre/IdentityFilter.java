package com.mengyunzhi.waterpurifierfilter.pre;

import com.mengyunzhi.waterpurifierfilter.service.IdentityFilterService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chuhang on 2017/6/19.
 * 身份验证过滤器（用于判断请求是不是我们的用户发出的）
 */
public class IdentityFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(IdentityFilter.class);

    @Autowired
    private IdentityFilterService identityFilterService;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        // 获取请求
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        //获取参数信息，并进行验证
        String timestamp = request.getParameter("timestamp");
        String randomString = request.getParameter("randomString");
        String encryptionInfo = request.getParameter("encryptionInfo");
        // 验证信息是否为我们的客户发送的，若不是，拦截
        if (!identityFilterService.isTrue(timestamp, randomString, encryptionInfo)) {
            ctx.setSendZuulResponse(false);
        }
        return null;
    }

}
