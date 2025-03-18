/*package com.rightprice.auth.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.schema.impl.XSAnyImpl;
import org.opensaml.xml.schema.impl.XSStringImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rightprice.auth.model.CheckUserType;
import com.rightprice.auth.model.User;
import com.rightprice.auth.repository.RightPriceRepository;
import com.rightprice.auth.service.SecurityService;
import com.rightprice.auth.service.UserService;
import com.rightprice.auth.util.AppLoger;
import com.rightprice.auth.validator.UserValidator;

@Controller
public final class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private RightPriceRepository rightpricerepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);
        
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model, String error, String logout, HttpServletRequest request, HttpServletResponse response) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
    	Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
        AppLoger.APPLOGGER.info("1");
        List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
        AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
        for (AttributeStatement statement : attributeStatements) {
          for (Attribute attribute : statement.getAttributes()) {
            XMLObject xmlObject = attribute.getAttributeValues().get(0);
            AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
            if (xmlObject instanceof XSStringImpl) {
              map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
              AppLoger.APPLOGGER.info("Map is : " +map);
            } else {
              map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
              AppLoger.APPLOGGER.info("Map is : " +map);
            }
          }
        }
        String dasID = map.get("uid");
        String fName = map.get("firstname");
        String lname = map.get("surname");
        String atosMailID = map.get("mail");
        AppLoger.APPLOGGER.info("Username is :" +dasID);
        AppLoger.APPLOGGER.info("fName is :" +fName);
        AppLoger.APPLOGGER.info("lName is :" +lname);
        AppLoger.APPLOGGER.info("Atos email Id is :" +atosMailID);
    	String username1 = rightpricerepository.getSyntelId(dasID);
    	AppLoger.APPLOGGER.info("Syntelligence ID is -- : "+username1);
    	//SyntelID and DAS ID mapping should be here
    	
    	if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
    	
    	AppLoger.APPLOGGER.info("Fetching the available roles ENTRY");
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
//		List<String> authority = rightpricerepository.getUserRoles(username1);
		grantedAuths.add(new SimpleGrantedAuthority("USER"));
		if(username1.isEmpty())
    	{
		if(rightpricerepository.getDeligateUserCount(dasID) == 0) {
			List<String> authority = rightpricerepository.getUserRoles(dasID);
			AppLoger.APPLOGGER.info("Role is : " +authority);

		if(authority.get(0).contains("GFT"))
		{
		CheckUserType.userTypeId = 1;	
		}
		else if(authority.get(0).contains("BUH")||authority.get(0).contains("CDO")
				||authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User"))
		{
			CheckUserType.userTypeId = 2;		
		}
		else if(authority.get(0).contains("DUH"))
		{
			CheckUserType.userTypeId = 3;
		}
		else if(authority.get(0).contains("Quality"))
		{
			CheckUserType.userTypeId = 4;
		}
		else if(authority.get(0).contains("Delivery"))
		{
			CheckUserType.userTypeId = 5;
		}
		else if(authority.get(0).contains("Others"))
		{
			CheckUserType.userTypeId = 6;
		}
		else if(authority.get(0).contains("Audit"))
		{
			CheckUserType.userTypeId = 8;
		}
		else if(authority.get(0).contains("ISSDUSER"))
		{
			CheckUserType.userTypeId = 9;
		}
		AppLoger.APPLOGGER.info("User Roles");

		for (String auth : authority) {
			AppLoger.APPLOGGER.info("Auth is : " +auth);
			grantedAuths.add(new SimpleGrantedAuthority(auth));
		}
		} else {
			
			CheckUserType.userTypeId = 7;	
			
		}
    	}
		else
		{
			if(rightpricerepository.getDeligateUserCount(username1) == 0) {
				List<String> authority = rightpricerepository.getUserRoles(username1);
				AppLoger.APPLOGGER.info("Role is : " +authority);

			if(authority.get(0).contains("GFT"))
			{
			CheckUserType.userTypeId = 1;	
			}
			else if(authority.get(0).contains("BUH")||authority.get(0).contains("CDO")
					||authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
					|| authority.get(0).contains("LEVELl2User"))
			{
				CheckUserType.userTypeId = 2;		
			}
			else if(authority.get(0).contains("DUH"))
			{
				CheckUserType.userTypeId = 3;
			}
			else if(authority.get(0).contains("Quality"))
			{
				CheckUserType.userTypeId = 4;
			}
			else if(authority.get(0).contains("Delivery"))
			{
				CheckUserType.userTypeId = 5;
			}
			else if(authority.get(0).contains("Others"))
			{
				CheckUserType.userTypeId = 6;
			}
			else if(authority.get(0).contains("Audit"))
			{
				CheckUserType.userTypeId = 8;
			}
			else if(authority.get(0).contains("ISSDUSER"))
			{
				CheckUserType.userTypeId = 9;
			}
			AppLoger.APPLOGGER.info("User Roles");

			for (String auth : authority) {
				AppLoger.APPLOGGER.info("Auth is : " +auth);
				grantedAuths.add(new SimpleGrantedAuthority(auth));
			}
			} else {
				
				CheckUserType.userTypeId = 7;	
				
			}
	    }
		
		
		HttpSession session = request.getSession();
		if(username1 == null || username1.isEmpty())
		{
			session.setAttribute("username", dasID);
		}
		else
		{
		session.setAttribute("username", username1);
		}
	//	session.setAttribute("userType", userType1);
		session.setAttribute("firstname", fName);
		session.setAttribute("surname", lname);
		session.setAttribute("mail", atosMailID);
		session.setAttribute("userRole", grantedAuths );
		if(rightpricerepository.getDeligateUserCount(dasID) != 0 ||rightpricerepository.getDeligateUserCount(username1) != 0)
		{
			try {
				response.sendRedirect("/RightPrice-DAS/DeligateUserAccess");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return "Rightprice/MyDashBoard";
    }
    

}
*/