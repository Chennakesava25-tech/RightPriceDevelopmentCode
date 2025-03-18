package com.rightprice.auth.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rightprice.auth.util.AppLoger;

@Controller
public class CustomErrorControlar implements ErrorController  {
	
	String logoutSuccessUrl = "https://wac.myeviden.com/sso_mand2fa_2023/SingleLogoutService";
	
    @RequestMapping(value = {"/error"}, method = RequestMethod.GET)
    public void handleError(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    	if(statusCode!=null)
    	{
    		Integer statusCode1 = Integer.valueOf(statusCode.toString());
    		AppLoger.APPLOGGER.info(statusCode1);
    		if(statusCode1 == HttpStatus.NOT_FOUND.value() || statusCode1 == HttpStatus.UNAUTHORIZED.value()
    				|| statusCode1 == HttpStatus.INTERNAL_SERVER_ERROR.value() || statusCode1 == HttpStatus.BAD_REQUEST.value())
    		{
    			response.sendRedirect("https://wac.myeviden.com/sso_mand2fa_2023/SingleLogoutService");
    		}
    	}
         response.sendRedirect("https://wac.myeviden.com/sso_mand2fa_2023/SingleLogoutService");
    }
    @Override
    public String getErrorPath() {
        return logoutSuccessUrl;
    }
}