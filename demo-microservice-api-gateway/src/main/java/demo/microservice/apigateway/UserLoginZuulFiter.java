package demo.microservice.apigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

/**
 * 用户登录信息拦截
 * @author rcl
 * @date 2018/6/10 10:41
 */
@Component
public class UserLoginZuulFiter extends ZuulFilter {


    @Override
    public String filterType() {
        // 设置过滤器类型为：pre
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 设置执行顺序
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 该过滤器需要执行
        return true;
    }

    @Override
    public Object run() {
        //编写业务逻辑
        RequestContext requestContext = RequestContext.getCurrentContext();
//        requestContext.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
//        requestContext.setResponseStatusCode(401); // 设置响应状态码
        return null;
    }
}
