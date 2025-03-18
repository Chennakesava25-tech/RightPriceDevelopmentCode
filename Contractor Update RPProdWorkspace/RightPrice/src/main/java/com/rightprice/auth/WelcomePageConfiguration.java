package com.rightprice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.rightprice.auth.config.RequestInterceptor;
import com.rightprice.auth.config.UserTypeCheckInterceptor;

/**
 * Configure the welcome page 
 * 
 */
@Configuration
public class WelcomePageConfiguration extends WebMvcConfigurerAdapter implements WebMvcConfigurer {

    /**
     * redirect a user to the welcome page when he visits tha app without a
     * destination url.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/login.jsp");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
    
    @Autowired
   	RequestInterceptor requestInterceptor;
      
      /*@Autowired
      UserTypeCheckInterceptor  usertypecheckinterceptor;*/
   	
   	@Override
   	public void addInterceptors(InterceptorRegistry registry) {
   		registry.addInterceptor(requestInterceptor);
//   		registry.addInterceptor(usertypecheckinterceptor);
   	}
}