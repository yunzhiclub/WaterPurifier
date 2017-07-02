package com.mengyunzhi.waterpurifierfilter.pre;

import com.mengyunzhi.waterpurifierfilter.service.IdentityFilterService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

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
    public Object run()  {
        // 获取请求
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        //定义并赋空值
        String timestamp, randomString, encryptionInfo;
        timestamp = randomString = encryptionInfo ="";
        //判断请求方法
        if (request.getMethod().equals("GET")) {
            timestamp = request.getParameter("timestamp");
            randomString = request.getParameter("randomString");
            encryptionInfo = request.getParameter("encryptionInfo");
        } else {
            try {
                //获取请求string类型body（暂时无法将string反序列化json格式，暂时采取截取字符串的方法）
                StringBuilder sb = new StringBuilder();
                BufferedReader reader = request.getReader();
                try {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                } finally {
                    reader.close();
                }

                //正则表达式，匹配英文字母或者数字
                String s = "[^A-Za-z0-9]";
                Pattern pattern = Pattern.compile(s);

                //获取加密信息
                encryptionInfo = pattern.matcher(sb.substring(sb.lastIndexOf(":"), sb.lastIndexOf("}"))).replaceAll("").trim();
                //获取随机字符串
                String temp = sb.substring(0, sb.lastIndexOf(":")-1);
                randomString = pattern.matcher(temp.substring(temp.lastIndexOf(":"), temp.lastIndexOf(","))).replaceAll("").trim();
                //获取时间戳
                String temp2 = sb.substring(0,sb.lastIndexOf(",")-1);
                timestamp = temp2.substring(temp2.indexOf("timestamp")+12, temp2.lastIndexOf(","));
            } catch (IOException e) {
                System.out.println("IOException:" + e);
            }
        }
        // 验证信息是否为我们的客户发送的，若不是(除获取当前时间戳外)，拦截
        if (!identityFilterService.isTrue(timestamp, randomString, encryptionInfo) || request.getRequestURL().indexOf("/api/getCurrentTime") > 0) {
            ctx.setSendZuulResponse(false);
        }
        return null;
    }

}
