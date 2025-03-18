package com.rightprice.auth.config;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rightprice.auth.util.AppLoger;
import com.rightprice.auth.util.EcrptDerptjava;

@Component
@Configuration
@EnableTransactionManagement
public class RequestInterceptor extends HandlerInterceptorAdapter {
	static int checkedValue = 0;


 @Override
 public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response, Object object) throws Exception {
	
	AppLoger.APPLOGGER.info("In preHandle we are Intercepting the Request");
	AppLoger.APPLOGGER.info("____________________________________________ preHandle Satrt");
	
	String ip = getClientIpAddr(request);
	InetAddress ipaddress = InetAddress.getByName(ip);
	String IP = ipaddress.getHostAddress();
	String requestURI = request.getRequestURI();
	AppLoger.APPLOGGER.info("RequestURI::" + requestURI);
	HttpSession session = request.getSession();
	String SessionId =session.getId();
	AppLoger.APPLOGGER.info("SessionId : " + session.getId());
	String setSession= (String) session.getAttribute("checkSession");
	session.getAttribute("checkSession");
	AppLoger.APPLOGGER.info("setSession : "+setSession);
	String key = "Bar12345Bar12345"; // 128 bit key
    String initVector = "RandomInitVector"; // 16 bytes IV
	
		if (!(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/")) 
			&&
			!(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/SessionHijackPrevention")) 
			&& 
			!(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/error")) 
			&& 
			!(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/login?logout"))
			) {
			AppLoger.APPLOGGER.info("=====================");
			
			if (session.getAttribute("checkSession") != null) {
						try {
							if(!(EcrptDerptjava.decrypt(key, initVector,session.getAttribute("checkSession").toString()).equalsIgnoreCase(session.getId()+"$"+IP+"$"+getBrowser(request))))
								{	
								AppLoger.APPLOGGER.info("getAttribute checkSession"+session.getAttribute("checkSession").toString());
								AppLoger.APPLOGGER.info(EcrptDerptjava.decrypt(key, initVector,session.getAttribute("checkSession").toString()));
								AppLoger.APPLOGGER.info("Session Hijacked");
								//checkedValue++;
								response.sendRedirect("/RightPrice-DAS/login?logout");
								
							}else{
								AppLoger.APPLOGGER.info("Session is Ok");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}
	}
	
	AppLoger.APPLOGGER.info("____________________________________________ preHandle End");
	return true;
 }


@Override
 public void postHandle(HttpServletRequest request, HttpServletResponse response, 
		Object object, ModelAndView model)
		throws Exception {}

 @Override
 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
		Object object, Exception arg3)
		throws Exception {}
 
 public static String getClientIpAddr(HttpServletRequest request) {
     String ip = request.getHeader("X-Forwarded-For");
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("Proxy-Client-IP");
     }
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("WL-Proxy-Client-IP");
     }
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("HTTP_CLIENT_IP");
     }
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("HTTP_X_FORWARDED_FOR");
     }
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getRemoteAddr();
     }
     return ip;
 }
 
 public static String getBrowser(HttpServletRequest request){
	   String browserType = request.getHeader("User-Agent");
	   String  userAgent       =   browserType;
     String  user            =   userAgent.toLowerCase();
     String browser = "";
     //----------browser-------------------------------------
     if (user.contains("msie"))
     {
         String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
         browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
     } else if (user.contains("safari") && user.contains("version"))
     {
         browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
     } else if ( user.contains("opr") || user.contains("opera"))
     {
         if(user.contains("opera"))
             browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
         else if(user.contains("opr"))
             browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
     } else if (user.contains("chrome"))
     {
         browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
     } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
     {
         //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
         browser = "Netscape-?";

     } else if (user.contains("firefox"))
     {
         browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
     } else if(user.contains("rv"))
     {
         browser="IE";
     } else
     {
         browser = "UnKnown, More-Info: "+userAgent;
     }
     return browser;
}
 
 private static final String[] IP_HEADER_CANDIDATES = {
	        "X-Forwarded-For",
	        "Proxy-Client-IP",
	        "WL-Proxy-Client-IP",
	        "HTTP_X_FORWARDED_FOR",
	        "HTTP_X_FORWARDED",
	        "HTTP_X_CLUSTER_CLIENT_IP",
	        "HTTP_CLIENT_IP",
	        "HTTP_FORWARDED_FOR",
	        "HTTP_FORWARDED",
	        "HTTP_VIA",
	        "REMOTE_ADDR" };

	public static String getClientIpAddress(HttpServletRequest request) {
	    for (String header : IP_HEADER_CANDIDATES) {
	        String ip = request.getHeader(header);
	        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
	            return ip;
	        }
	    }
	    return request.getRemoteAddr();
	}
 
		  
}