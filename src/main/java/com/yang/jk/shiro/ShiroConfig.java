package com.yang.jk.shiro;

import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @auther yhjStart
 * @create 2022-03-31 16:15
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public Realm realm() {
        return new TokenRealm(new TokenCredentialsMatcher());
    }
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean1() {
        FilterRegistrationBean registration =
                new FilterRegistrationBean(errorFilter());
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registration.addUrlPatterns("/*");
        registration.setName("errorFilter");
        return registration;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration =
                new FilterRegistrationBean(tokenFilter());
        registration.setEnabled(false);
        return registration;
    }
//    @Bean
//    public AnonymousFilter anonymousFilter() {
//        return new AnonymousFilter();
//    }


//        @Bean
//        public Authorizer authorizer(){
//            return new ModularRealmAuthorizer();
//        }

    /**
     * 注意大坑,这里shiro注入方法名必须为shiroFilterFactoryBean
     * @param
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        filterBean.setSecurityManager(new DefaultWebSecurityManager(realm()));
        HashMap<String, Filter> stringFilterHashMap = new HashMap<>();
        stringFilterHashMap.put("token",tokenFilter());
//        自定义filter
        filterBean.setFilters(stringFilterHashMap);
        //设置请求路径过滤
        HashMap<String, String> urlmap = new LinkedHashMap<>();
        urlmap.put("/sysUser/Login","anon"); //不用验证直接通过
        urlmap.put("/sysUser/captcha","anon");
        urlmap.put("/swagger-ui/**","anon");
        urlmap.put("/swagger-ui.html","anon");
        urlmap.put("/swagger-resources/**","anon");
        urlmap.put("/v2/api-docs/**","anon");
        urlmap.put("/static/**","anon");
        urlmap.put("/webjars/springfox-swagger-ui/**","anon");
        urlmap.put(ErrorFilter.ERROR_URI,"anon");
        urlmap.put("/**","token");
        filterBean.setFilterChainDefinitionMap(urlmap);
        return filterBean;

    }

    /**
     * 解决加上RequiresPermissions导致的404问题
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator proxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
