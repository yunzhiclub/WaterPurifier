package com.mengyunzhi.waterpurifierfilter.pre;

import com.google.common.collect.Maps;
import com.mengyunzhi.waterpurifierfilter.service.IdentityFilterService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by chuhang on 2017/6/19.
 * 身份验证过滤器（用于判断请求是不是我们的用户发出的）
 */
@SessionAttributes("user")
public class IdentityFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(IdentityFilter.class);

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
    public Object run()  {
        // 获取请求内容
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //获取3rd_session
        String threeRdSession = request.getHeader("threeRdSession");
        //如果threeRdSession不为空，则认为是微信端发送送的请求
        if (threeRdSession != null) {
            //获取openId
            HttpSession session = request.getSession();
            Object openIdAndSessionKey = session.getAttribute(threeRdSession);
            // 判断是否成功请求微信服务器，或者是其他非法请求
            String openid = "";
            try{
                openid = JSONObject.fromObject(openIdAndSessionKey).getString("openid");
            } catch(JSONException e){
                System.out.println("JSONException" + e);
            }
            //增加请求参数，根据客户端请求的参数3rd_session，从session中获取客户的openId
            ctx.addZuulRequestHeader("openid", openid);
        }
        //验证请求，若不合法，则拦截，反之
        this.verifyRequest(ctx);
        return null;
    }

    /**
     * 验证请求，若不合法，则拦截，反之
     * @param ctx
     */
    public void verifyRequest(RequestContext ctx) {
        //获取请求
        HttpServletRequest request = ctx.getRequest();
        //定义并赋空值
        String timestamp = request.getHeader("timestamp");
        String randomString = request.getHeader("randomString");
        String encryptionInfo = request.getHeader("encryptionInfo");
        // 验证信息是否为我们的客户发送的，若不是(除获取当前时间戳外)，拦截
        if (!identityFilterService.isTrue(timestamp, randomString, encryptionInfo) && request.getRequestURL().indexOf("/api/getCurrentTime") < 0) {
            ctx.setSendZuulResponse(false);
        }
        return;
    }
}
