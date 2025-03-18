package com.rightprice.auth.web;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rightprice.auth.model.CheckUserType;
import com.rightprice.auth.repository.RightPriceRepository;
import com.rightprice.auth.util.AppLoger;

import io.swagger.annotations.Api;

@Controller
@Api("Right Price URL Mapping")
public class RightPriceMapping {

	@Autowired
	private RightPriceRepository rightPriceRepository;
	String userName = "";
	String userRoleRP="";
	String key = "Bar12345Bar12345";
	String ive = "RandomInitVector";
	List<String> authority = new ArrayList<String>();

	// URL mapping goes here !!!
	/*
	 * @RequestMapping(value = {"/MyDashBoard" }, method = RequestMethod.GET)
	 * public String MyDashBoard(Principal principal, Model model) { userName =
	 * principal.getName(); if (principal != null) {
	 * model.addAttribute("username", principal.getName()); } return
	 * "Rightprice/MyDashBoard"; }
	 */
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String MyDashBoard(Model model, String error, String logout, HttpServletRequest request,
			HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		AppLoger.APPLOGGER.info("Syntelligence ID is -- : " + username1);
		// SyntelID and DAS ID mapping should be here

		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		AppLoger.APPLOGGER.info("Fetching the available roles ENTRY");
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		List<GrantedAuthority> grantedAuthsEncrypt = new ArrayList<>();
		// List<String> authority =
		// rightpricerepository.getUserRoles(username1);
		grantedAuths.add(new SimpleGrantedAuthority("USER"));
		
		
		if (username1.isEmpty()) {
			if (rightPriceRepository.getDeligateUserCount(dasID) == 0) {
				List<String> authority = rightPriceRepository.getUserRoles(dasID);
				
				AppLoger.APPLOGGER.info("Role is : " + authority);

				if (authority.get(0).contains("GFT")) {
					CheckUserType.userTypeId = 1;
				} else if (authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
						|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
						|| authority.get(0).contains("LEVELl2User")) {
					CheckUserType.userTypeId = 2;
				} else if (authority.get(0).contains("DUH")) {
					CheckUserType.userTypeId = 3;
				} else if (authority.get(0).contains("RiskManagers")) {
					CheckUserType.userTypeId = 4;
				} else if (authority.get(0).contains("Delivery")) {
					CheckUserType.userTypeId = 5;
				} else if (authority.get(0).contains("Others")) {
					CheckUserType.userTypeId = 6;
				} else if (authority.get(0).contains("Audit")) {
					CheckUserType.userTypeId = 8;
				} else if (authority.get(0).contains("PSDelivery")) {
					CheckUserType.userTypeId = 9;
				} else if (authority.get(0).contains("PSApprover")) {
					CheckUserType.userTypeId = 10;
				} else if (authority.get(0).contains("PSReport")) {
					CheckUserType.userTypeId = 11;
				}
				AppLoger.APPLOGGER.info("User Roles");

				for (String auth : authority) {
					AppLoger.APPLOGGER.info("Auth is : " + auth);
					grantedAuths.add(new SimpleGrantedAuthority(auth));
				}
				for (int i=0;i<grantedAuths.size();i++) {
					if(i==0){
						userRoleRP=grantedAuths.get(i).getAuthority();
						userRoleRP=encrypt(key, ive, userRoleRP);
						grantedAuthsEncrypt.add(new SimpleGrantedAuthority(userRoleRP));
					}
					if(i==1){
						userRoleRP=grantedAuths.get(i).getAuthority();
						userRoleRP=encrypt(key, ive, userRoleRP);
						grantedAuthsEncrypt.add(new SimpleGrantedAuthority(userRoleRP));
					}
					if(i==grantedAuths.size()-1){
						userRoleRP=grantedAuths.get(i).getAuthority();
						//userRoleRP=encrypt(key, ive, userRoleRP);
						grantedAuthsEncrypt.add(new SimpleGrantedAuthority(userRoleRP));
					}
					else if(i>1 && i<grantedAuths.size()-1){
						userRoleRP=grantedAuths.get(i).getAuthority();
						grantedAuthsEncrypt.add(new SimpleGrantedAuthority(userRoleRP));
					}
					
				}
			} else {

				CheckUserType.userTypeId = 7;

			}
		} else {
			if (rightPriceRepository.getDeligateUserCount(username1) == 0) {
				List<String> authority = rightPriceRepository.getUserRoles(username1);
				AppLoger.APPLOGGER.info("Role is : " + authority);

				if (authority.get(0).contains("GFT")) {
					CheckUserType.userTypeId = 1;
				} else if (authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
						|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
						|| authority.get(0).contains("LEVELl2User")) {
					CheckUserType.userTypeId = 2;
				} else if (authority.get(0).contains("DUH")) {
					CheckUserType.userTypeId = 3;
				} else if (authority.get(0).contains("RiskManagers")) {
					CheckUserType.userTypeId = 4;
				} else if (authority.get(0).contains("Delivery")) {
					CheckUserType.userTypeId = 5;
				} else if (authority.get(0).contains("Others")) {
					CheckUserType.userTypeId = 6;
				} else if (authority.get(0).contains("Audit")) {
					CheckUserType.userTypeId = 8;
				} else if (authority.get(0).contains("PSDelivery")) {
					CheckUserType.userTypeId = 9;
				} else if (authority.get(0).contains("PSApprover")) {
					CheckUserType.userTypeId = 10;
				} else if (authority.get(0).contains("PSReport")) {
					CheckUserType.userTypeId = 11;
				}
				AppLoger.APPLOGGER.info("User Roles");

				for (String auth : authority) {
					AppLoger.APPLOGGER.info("Auth is : " + auth);
					grantedAuths.add(new SimpleGrantedAuthority(auth));
				}
				for (int i=0;i<grantedAuths.size();i++) {
					if(i==0){
						userRoleRP=grantedAuths.get(i).getAuthority();
						userRoleRP=encrypt(key, ive, userRoleRP);
						grantedAuthsEncrypt.add(new SimpleGrantedAuthority(userRoleRP));
					}
					if(i==1){
						userRoleRP=grantedAuths.get(i).getAuthority();
						userRoleRP=encrypt(key, ive, userRoleRP);
						grantedAuthsEncrypt.add(new SimpleGrantedAuthority(userRoleRP));
					}
					if(i==grantedAuths.size()-1){
						userRoleRP=grantedAuths.get(i).getAuthority();
						userRoleRP=encrypt(key, ive, userRoleRP);
						grantedAuthsEncrypt.add(new SimpleGrantedAuthority(userRoleRP));
					}
					else if(i>1 && i<grantedAuths.size()-1){
						userRoleRP=grantedAuths.get(i).getAuthority();
						grantedAuthsEncrypt.add(new SimpleGrantedAuthority(userRoleRP));
					}
					
				}
				

				
				
			} else {

				CheckUserType.userTypeId = 7;

			}
		}
		String username = "";
		
		HttpSession session = request.getSession();
		
		String das=encrypt(key, ive, dasID);
		//String roleUser=encrypt(key, ive, authority.get(0));
		if (username1 == null || username1.isEmpty()) {
			username = encrypt(key, ive, dasID);
			session.setAttribute("username", dasID);
			session.setAttribute("user", username);

		} else {
			username = encrypt(key, ive, username1);
			session.setAttribute("username", username1);
			session.setAttribute("user", username);
		}
		// session.setAttribute("userType", userType1);
		session.setAttribute("dasid", dasID);
		session.setAttribute("firstname", fName);
		session.setAttribute("surname", lname);
		session.setAttribute("mail", atosMailID);
		session.setAttribute("userRole", grantedAuths);
		session.setAttribute("das", das);
		session.setAttribute("roleUser", grantedAuthsEncrypt);
		if (rightPriceRepository.getDeligateUserCount(dasID) != 0
				|| rightPriceRepository.getDeligateUserCount(username1) != 0) {
			try {
				response.sendRedirect("/RightPrice-DAS/DeligateUserAccess");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "Rightprice/MyDashBoard";

	}

	@RequestMapping(value = { "/DeligateUserAccess", "/RightPrice-DAS/DeligateUserAccess" }, method = RequestMethod.GET)
	public String DeligateUserAccess(Model model) {
		return "Rightprice/DeligateUserAccess";
	}

	@RequestMapping(value = { "/MyDashBoard", "/RightPrice-DAS/MyDashBoard" }, method = RequestMethod.GET)
	public String MyDashBoard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/MyDashBoard";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "Portal/HomePage";
	}

	@RequestMapping(value = { "/RiskManagersDashBoard", "/RightPrice-DAS/RiskManagersDashBoard" }, method = RequestMethod.GET)
	public String RiskManagersDashBoard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/RiskManagersDashBoard";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterAllowances", "/RightPrice-DAS/MasterAllowances" }, method = RequestMethod.GET)
	public String MasterAllowances(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterAllowances";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterCampusHire", "/RightPrice-DAS/MasterCampusHire" }, method = RequestMethod.GET)
	public String MasterCampusHire(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterCampusHire";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterCity", "/RightPrice-DAS/MasterCity" }, method = RequestMethod.GET)
	public String MasterCity(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterCity";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterCommonCostParameters",
			"/RightPrice-DAS/MasterCommonCostParameters" }, method = RequestMethod.GET)
	public String MasterCommonCostParameters(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterCommonCostParameters";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterCountryForex", "/RightPrice-DAS/MasterCountryForex" }, method = RequestMethod.GET)
	public String MasterCountryForex(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterCountryForex";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterCountryVisa", "/RightPrice-DAS/MasterCountryVisa" }, method = RequestMethod.GET)
	public String MasterCountryVisa(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterCountryVisa";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterDeliveryTeam", "/RightPrice-DAS/MasterDeliveryTeam" }, method = RequestMethod.GET)
	public String MasterDeliveryTeam(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterDeliveryTeam";
		} else {
			return "Portal/403";
		}
	}

	/*
	 * @RequestMapping(value =
	 * {"/MasterRightPriceAudit","/RightPrice-DAS/MasterRightPriceAudit"},
	 * method = RequestMethod.GET) public String MasterRightPriceAudit(Model
	 * model) { return "Rightprice/MasterRightPriceAudit"; }
	 */
	@RequestMapping(value = { "/MasterRightPriceRate",
			"/RightPrice-DAS/MasterRightPriceRate" }, method = RequestMethod.GET)
	public String MasterRightPriceRate(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterRightPriceRate";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterRoles", "/RightPrice-DAS/MasterRoles" }, method = RequestMethod.GET)
	public String MasterRoles(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterRoles";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterSalary", "/RightPrice-DAS/MasterSalary" }, method = RequestMethod.GET)
	public String MasterSalary(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterSalary";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MastersDesignation", "/RightPrice-DAS/MastersDesignation" }, method = RequestMethod.GET)
	public String MastersDesignation(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MastersDesignation";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MastersLOB", "/RightPrice-DAS/MastersLOB" }, method = RequestMethod.GET)
	public String MastersLOB(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MastersLOB";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MastersMiscellaneousCost",
			"/RightPrice-DAS/MastersMiscellaneousCost" }, method = RequestMethod.GET)
	public String MastersMiscellaneousCost(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MastersMiscellaneousCost";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MastersSubPractice", "/RightPrice-DAS/MastersSubPractice" }, method = RequestMethod.GET)
	public String MastersSubPractice(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MastersSubPractice";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MastersVertical", "/RightPrice-DAS/MastersVertical" }, method = RequestMethod.GET)
	public String MastersVertical(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MastersVertical";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterTaxParameters",
			"/RightPrice-DAS/MasterTaxParameters" }, method = RequestMethod.GET)
	public String MasterTaxParameters(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterTaxParameters";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterAssumptionsParameters",
			"/RightPrice-DAS/MasterAssumptionsParameters" }, method = RequestMethod.GET)
	public String MasterAssumptionsParameters(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterAssumptionsParameters";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterRateCard", "/RightPrice-DAS/MasterRateCard" }, method = RequestMethod.GET)
	public String MasterRateCard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterRateCard";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterCustomerUpdate",
			"/RightPrice-DAS/MasterCustomerUpdate" }, method = RequestMethod.GET)
	public String MasterCustomerUpdate(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterCustomerUpdate";
		} else {
			return "Portal/403";
		}
	}
	// Url Mapping for Right Price LookUps

	/*
	 * @RequestMapping(value = {"/Lookup","/RightPrice-DAS/Lookup"}, method =
	 * RequestMethod.GET) public String Lookup(Model model) { return
	 * "Rightprice/Lookup"; }
	 */

	// Url Mapping for Right Price Rate Card Creation

	@RequestMapping(value = { "/RateCardCreationDetails",
			"/RightPrice-DAS/RateCardCreationDetails" }, method = RequestMethod.GET)
	public String RateCardCreationDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/RateCardCreationDetails";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RateCardCreationRoleSelection",
			"RightPrice/RateCardCreationRoleSelection" }, method = RequestMethod.GET)
	public String RateCardCreationRoleSelection(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/RateCardCreationRoleSelection";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RateCardCreationAddContractorRole",
			"RightPrice/RateCardCreationAddContractorRole" }, method = RequestMethod.GET)
	public String RateCardCreationAddContractorRole(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/RateCardCreationAddContractorRole";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RateCardContractorRole",
			"RightPrice/RateCardContractorRole" }, method = RequestMethod.GET)
	public String RateCardContractorRole(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/RateCardContractorRole";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RateCardCreationRoleUtilizationAndRates",
			"RightPrice/RateCardCreationRoleUtilizationAndRates" }, method = RequestMethod.GET)
	public String RateCardCreationRoleUtilizationAndRates(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/RateCardCreationRoleUtilizationAndRates";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RateCardCreationSummary",
			"RightPrice/RateCardCreationSummary" }, method = RequestMethod.GET)
	public String RateCardCreationSummary(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/RateCardCreationSummary";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RateCardCreationFinaliseRateCard",
			"RightPrice/RateCardCreationFinaliseRateCard" }, method = RequestMethod.GET)
	public String RateCardCreationFinaliseRateCard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/RateCardCreationFinaliseRateCard";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RateCardCompletionStage",
			"RightPrice/RateCardCompletionStage" }, method = RequestMethod.GET)
	public String RateCardCompletionStage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/RateCardCompletionStage";
		} else {
			return "Portal/403";
		}
	}

	// URL Mapping for Right Price Create Deal
	@RequestMapping(value = { "/TMDealCreationDetails",
			"/RightPrice-DAS/TMDealCreationDetails" }, method = RequestMethod.GET)
	public String TMDealCreationDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/TMDealCreationDetails";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationDetails",
			"/RightPrice-DAS/FPDealCreationDetails" }, method = RequestMethod.GET)
	public String FPDealCreationDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationDetails";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/TMDealCreationRateCardAndProjectDetails",
			"/RightPrice-DAS/TMDealCreationRateCardAndProjectDetails" }, method = RequestMethod.GET)
	public String TMDealCreationRateCardAndProjectDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/TMDealCreationRateCardAndProjectDetails";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/TMDealCreationTeamDetails",
			"/RightPrice-DAS/TMDealCreationTeamDetails" }, method = RequestMethod.GET)
	public String TMDealCreationTeamDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/TMDealCreationTeamDetails";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/TMDealCreationRoleSelection",
			"/RightPrice-DAS/TMDealCreationRoleSelection" }, method = RequestMethod.GET)
	public String TMDealCreationRoleSelection(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/TMDealCreationRoleSelection";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/TMDealCreationAddContractorRole",
			"/RightPrice-DAS/TMDealCreationAddContractorRole" }, method = RequestMethod.GET)
	public String TMDealCreationAddContractorRole(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/TMDealCreationAddContractorRole";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/TMDealCreationStaffing",
			"/RightPrice-DAS/TMDealCreationStaffing" }, method = RequestMethod.GET)
	public String TMDealCreationStaffing(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/TMDealCreationStaffing";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/TMDealCreationProjectSpecificCost",
			"/RightPrice-DAS/TMDealCreationProjectSpecificCost" }, method = RequestMethod.GET)
	public String TMDealCreationProjectSpecificCost(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/TMDealCreationProjectSpecificCost";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/TMDealCreationUploadEstimationRelatedDocuments",
			"/RightPrice-DAS/TMDealCreationUploadEstimationRelatedDocuments" }, method = RequestMethod.GET)
	public String TMDealCreationUploadEstimationRelatedDocuments(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/TMDealCreationUploadEstimationRelatedDocuments";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/TMDealCreationSummary",
			"/RightPrice-DAS/TMDealCreationSummary" }, method = RequestMethod.GET)
	public String TMDealCreationSummary(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/TMDealCreationSummary";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/TMDealCreationFinaliseDeal",
			"/RightPrice-DAS/TMDealCreationFinaliseDeal" }, method = RequestMethod.GET)
	public String TMDealCreationFinaliseDeal(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/TMDealCreationFinaliseDeal";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationRateCardAndProjectDetails",
			"/RightPrice-DAS/FPDealCreationRateCardAndProjectDetails" }, method = RequestMethod.GET)
	public String FPDealCreationRateCardAndProjectDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationRateCardAndProjectDetails";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationContractualTerms",
			"/RightPrice-DAS/FPDealCreationContractualTerms" }, method = RequestMethod.GET)
	public String FPDealCreationContractualTerms(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationContractualTerms";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationTeamDetails",
			"/RightPrice-DAS/FPDealCreationTeamDetails" }, method = RequestMethod.GET)
	public String FPDealCreationTeamDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationTeamDetails";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationApplicationDetails",
			"/RightPrice-DAS/FPDealCreationApplicationDetails" }, method = RequestMethod.GET)
	public String FPDealCreationApplicationDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationApplicationDetails";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationRoleSelection",
			"/RightPrice-DAS/FPDealCreationRoleSelection" }, method = RequestMethod.GET)
	public String FPDealCreationRoleSelection(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationRoleSelection";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationAddContractorRole",
			"/RightPrice-DAS/FPDealCreationAddContractorRole" }, method = RequestMethod.GET)
	public String FPDealCreationAddContractorRole(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationAddContractorRole";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationStaffing",
			"/RightPrice-DAS/FPDealCreationStaffing" }, method = RequestMethod.GET)
	public String FPDealCreationStaffing(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationStaffing";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationCostInputs",
			"/RightPrice-DAS/FPDealCreationCostInputs" }, method = RequestMethod.GET)
	public String FPDealCreationCostInputs(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationCostInputs";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationProjectSpecificCost",
			"/RightPrice-DAS/FPDealCreationProjectSpecificCost" }, method = RequestMethod.GET)
	public String FPDealCreationProjectSpecificCost(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationProjectSpecificCost";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationUploadEstimationRelatedDocuments",
			"/RightPrice-DAS/FPDealCreationUploadEstimationRelatedDocuments" }, method = RequestMethod.GET)
	public String FPDealCreationUploadEstimationRelatedDocuments(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationUploadEstimationRelatedDocuments";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationWhatIfApplicationwise",
			"/RightPrice-DAS/FPDealCreationWhatIfApplicationwise" }, method = RequestMethod.GET)
	public String FPDealCreationWhatIfApplicationwise(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationWhatIfApplicationwise";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationCostSummary",
			"/RightPrice-DAS/FPDealCreationCostSummary" }, method = RequestMethod.GET)
	public String FPDealCreationCostSummary(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationCostSummary";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationYearlySummary",
			"/RightPrice-DAS/FPDealCreationYearlySummary" }, method = RequestMethod.GET)
	public String FPDealCreationYearlySummary(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationYearlySummary";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationSummary",
			"/RightPrice-DAS/FPDealCreationSummary" }, method = RequestMethod.GET)
	public String FPDealCreationSummary(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationSummary";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationFinaliseDeal",
			"/RightPrice-DAS/FPDealCreationFinaliseDeal" }, method = RequestMethod.GET)
	public String FPDealCreationFinaliseDeal(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationFinaliseDeal";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationCalculationDetails",
			"/RightPrice-DAS/FPDealCreationCalculationDetails" }, method = RequestMethod.GET)
	public String FPDealCreationCalculationDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit")) {
			return "Rightprice/FPDealCreationCalculationDetails";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RPAccessControl", "/RightPrice-DAS/RPAccessControl" }, method = RequestMethod.GET)
	public String RPAccessControl(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("ISSDUSER")) {
			return "Rightprice/RPAccessControl";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FpPricingDetails", "/RightPrice-DAS/FpPricingDetails" }, method = RequestMethod.GET)
	public String FpPricingDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit") || authority.get(0).contains("ISSDUSER")) {
			return "Rightprice/FpPricingDetails";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RateCardCreationGMRateDetails",
			"/RightPrice-DAS/RateCardCreationGMRateDetails" }, method = RequestMethod.GET)
	public String RateCardCreationGMRateDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/RateCardCreationGMRateDetails";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationManualDealSummary",
			"/RightPrice-DAS/FPDealCreationManualDealSummary" }, method = RequestMethod.GET)
	public String FPDealCreationManualDealSummary(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit") || authority.get(0).contains("ISSDUSER")) {
			return "Rightprice/FPDealCreationManualDealSummary";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPManualDealCreationApproval",
			"/RightPrice-DAS/FPManualDealCreationApproval" }, method = RequestMethod.GET)
	public String FPDealCreationApproval(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit") || authority.get(0).contains("ISSDUSER")) {
			return "Rightprice/FPManualDealCreationApproval";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/ResourceForcast", "/RightPrice-DAS/ResourceForcast" }, method = RequestMethod.GET)
	public String ResourceForcast(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/ResourceForcast";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RFOpportunity", "/RightPrice-DAS/RFOpportunity" }, method = RequestMethod.GET)
	public String RFOpportunity(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("Delivery") || authority.get(0).contains("Audit")) {
			return "Rightprice/RFOpportunity";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/FPDealCreationFinalizeDeal",
			"/RightPrice-DAS/FPDealCreationFinalizeDeal" }, method = RequestMethod.GET)
	public String FPDealCreationFinalize(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit") || authority.get(0).contains("ISSDUSER")) {
			return "Rightprice/FPDealCreationFinalizeDeal";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RCGFTUpload", "/RightPrice-DAS/RCGFTUpload" }, method = RequestMethod.GET)
	public String RCGFTUpload(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit") || authority.get(0).contains("ISSDUSER")) {
			return "Rightprice/RCGFTUpload";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MasterAtosRC", "/RightPrice-DAS/MasterAtosRC" }, method = RequestMethod.GET)
	public String MasterAtosRateCard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MasterAtosRC";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/MastersPractice", "/RightPrice-DAS/MastersPractice" }, method = RequestMethod.GET)
	public String MastersPractice(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT")) {
			return "Rightprice/MastersPractice";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/ActiveRateCard", "/RightPrice-DAS/ActiveRateCard" }, method = RequestMethod.GET)
	public String activeRateCard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit") || authority.get(0).contains("ISSDUSER")) {
			return "Rightprice/ActiveRateCard";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/SDealReport", "/RightPrice-DAS/SDealReport" }, method = RequestMethod.GET)
	public String SDealReport(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit") || authority.get(0).contains("ISSDUSER")) {
			return "Rightprice/SDealReport";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/Document", "/RightPrice-DAS/Document" }, method = RequestMethod.GET)
	public String Document(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit") || authority.get(0).contains("ISSDUSER")) {
			return "Rightprice/Document";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RightPrice-DAS/atosDataPrePrivacy", "/atosDataPrePrivacy" }, method = RequestMethod.GET)
	public String atosDataPrePrivacy(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit") || authority.get(0).contains("ISSDUSER")) {
			return "Rightprice/atosDataPrePrivacy";
		} else {
			return "Portal/403";
		}
	}

	@RequestMapping(value = { "/RightPrice-DAS/dataPrivacy", "/dataPrivacy" }, method = RequestMethod.GET)
	public String dataPrivacy(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
		AppLoger.APPLOGGER.info("1");
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
		AppLoger.APPLOGGER.info("attributeStatements are : " + attributeStatements);
		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				XMLObject xmlObject = attribute.getAttributeValues().get(0);
				AppLoger.APPLOGGER.info("XMLObject is : " + xmlObject);
				if (xmlObject instanceof XSStringImpl) {
					map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
					AppLoger.APPLOGGER.info("Map is : " + map);
				} else {
					map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
					AppLoger.APPLOGGER.info("Map is : " + map);
				}
			}
		}
		String dasID = map.get("uid");
		String fName = map.get("firstname");
		String lname = map.get("surname");
		String atosMailID = map.get("mail");
		AppLoger.APPLOGGER.info("Username is :" + dasID);
		AppLoger.APPLOGGER.info("fName is :" + fName);
		AppLoger.APPLOGGER.info("lName is :" + lname);
		AppLoger.APPLOGGER.info("Atos email Id is :" + atosMailID);
		String username1 = rightPriceRepository.getSyntelId(dasID);
		if (username1.isEmpty()) {
			authority = rightPriceRepository.getUserRoles(dasID);
		} else {
			authority = rightPriceRepository.getUserRoles(username1);
		}
		if (authority.get(0).contains("GFT") || authority.get(0).contains("BUH") || authority.get(0).contains("CDO")
				|| authority.get(0).contains("CEO") || authority.get(0).contains("LEVELl1User")
				|| authority.get(0).contains("LEVELl2User") || authority.get(0).contains("DUH")
				|| authority.get(0).contains("RiskManagers") || authority.get(0).contains("Delivery")
				|| authority.get(0).contains("Audit") || authority.get(0).contains("ISSDUSER")) {
			return "Rightprice/dataPrivacy";
		} else {
			return "Portal/403";
		}
	}

	/*
	 * @RequestMapping(value = {"/",
	 * "/atosDataValidate","/RightPrice-DAS/atosDataPrivacy"}, method =
	 * RequestMethod.GET) public String atosDataPrePrivacy(Principal
	 * principal,Model model) { if(principal != null) {
	 * model.addAttribute("username", principal.getName()); } return
	 * "Rightprice/atosDataPrePrivacy"; //return "Portal/HomePage"; }
	 */

	@RequestMapping(value = { "/RightPrice-DAS/PreSalesPortal", "/PreSalesPortal" }, method = RequestMethod.GET)
	public String PreSalesPortal(Model model) {
		return "Rightprice/PreSalesPortal";
	}

	@RequestMapping(value = { "/RightPrice-DAS/PreSalesView", "/PreSalesView" }, method = RequestMethod.GET)
	public String PreSalesView(Model model) {
		return "Rightprice/PreSalesView";
	}

	@RequestMapping(value = { "/RightPrice-DAS/PreSalesApprove", "/PreSalesApprove" }, method = RequestMethod.GET)
	public String PreSalesApprove(Model model) {
		return "Rightprice/PreSalesApprove";
	}

	@RequestMapping(value = { "/RightPrice-DAS/PreSalesApproverScreen",
			"/PreSalesApproverScreen" }, method = RequestMethod.GET)
	public String PreSalesApproverScreen(Model model) {
		return "Rightprice/PreSalesApproverScreen";
	}

	@RequestMapping(value = { "/RightPrice-DAS/PreSalesUpdate", "/PreSalesUpdate" }, method = RequestMethod.GET)
	public String PreSalesUpdate(Model model) {
		return "Rightprice/PreSalesUpdate";
	}

	@RequestMapping(value = { "/RightPrice-DAS/PreSalesApprovedReport",
			"/PreSalesApprovedReport" }, method = RequestMethod.GET)
	public String PreSalesApprovedReport(Model model) {
		return "Rightprice/PreSalesApprovedReport";
	}

	@RequestMapping(value = { "/RightPrice-DAS/PreSalesWBSNumberMapping",
			"/PreSalesWBSNumberMapping" }, method = RequestMethod.GET)
	public String PreSalesWBSNumberMapping(Model model) {
		return "Rightprice/PreSalesWBSNumberMapping";
	}

	@RequestMapping(value = { "/RightPrice-DAS/GFTReqData", "/GFTReqData" }, method = RequestMethod.GET)
	public String GFTReqData(Model model) {
		return "Rightprice/GFTReqData";
	}

	@RequestMapping(value = { "/RightPrice-DAS/RPviewDetails", "/RPviewDetails" }, method = RequestMethod.GET)
	public String RPviewDetails(Model model) {
		return "Rightprice/RPviewDetails";
	}

	@RequestMapping(value = { "/RateCardCreationDetails_New",
			"/RightPrice-DAS/RateCardCreationDetails_New" }, method = RequestMethod.GET)
	public String RateCardCreationDetails_New(Model model) {
		return "Rightprice/RateCardCreationDetails_New";
	}

	@RequestMapping(value = { "/RightPrice-DAS/DealCreationDetails_RP",
			"/DealCreationDetails_RP" }, method = RequestMethod.GET)
	public String DealCreationDetails_RP(Model model) {
		return "Rightprice/DealCreationDetails_RP";
	}

	@RequestMapping(value = { "/RightPrice-DAS/RoleMngmt", "/RoleMngmt" }, method = RequestMethod.GET)
	public String RoleMngmt(Model model) {
		return "Rightprice/RoleMngmt";

	}

	public static String encrypt(String key, String initVector, String value) {
		byte[] encrypted = null;
		byte[] enc = null;
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Key keys = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			 cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//			cipher.init(Cipher.ENCRYPT_MODE, keys);
//			encrypted = cipher.doFinal(value.getBytes());
			enc = cipher.doFinal(value.getBytes("UTF-8"));
//			return DatatypeConverter.printBase64Binary(encrypted);
			
			  
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Encrypted: "+ Base64.getEncoder().encodeToString(enc));
		return Base64.getEncoder().encodeToString(enc);
//		return DatatypeConverter.printBase64Binary(encrypted);
	}

	public static String decrypt(String key, String initVector, String encrypted) {
		try {
			// IvParameterSpec iv = new
			// IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Key keys = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			// cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			cipher.init(Cipher.DECRYPT_MODE, keys);
			return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
			// byte[] original =
			// cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));
			//
			// return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	//manglam updated
	@RequestMapping(value = { "/RightPrice-DAS/Wonreports", "/Wonreports" }, method = RequestMethod.GET)
	public String Wonreports(Model model) {
		return "Rightprice/Wonreports";
	}
	
}
