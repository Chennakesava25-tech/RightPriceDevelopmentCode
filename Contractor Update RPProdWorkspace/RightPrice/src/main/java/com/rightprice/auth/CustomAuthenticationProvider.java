package com.rightprice.auth;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.rightprice.auth.model.CheckUserType;
import com.rightprice.auth.repository.RightPriceRepository;
import com.rightprice.auth.util.AppLoger;






@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private RightPriceRepository rightPriceRepository;
	/*private AuthorizationBusinessLogic authorizationBusinessLogic = null;

	// HttpSession httpSession = null;

	public AuthorizationBusinessLogic getAuthorizationBusinessLogic() {
		return authorizationBusinessLogic;
	}

	@Autowired
	public void setAuthorizationBusinessLogic(AuthorizationBusinessLogic authorizationBusinessLogic) {
		this.authorizationBusinessLogic = authorizationBusinessLogic;
	}*/
	
	boolean isTestingMode=false;
	
	/*
	 * Change the testing mode to false if LDAP authentication is required
	 */
	
	
	@Override 
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		AppLoger.APPLOGGER.info("Current User :"+ userName);
		String password = authentication.getCredentials().toString();
		System.out.println("Encrypted Password................................"+password);
		try {
			String key = "Bar12345Bar12345"; // 128 bit key
	        String initVector = "RandomInitVector"; // 16 bytes IV
			password = decrypt(key, initVector, password);
			System.out.println("Decrypted Password................................"+password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AppLoger.APPLOGGER.info("Exception Caught while password decryption : "+ e);
			e.printStackTrace();
		}
		if (authorizedUser(userName, password)) {
			AppLoger.APPLOGGER.info("Fetching the available roles ENTRY");
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(new SimpleGrantedAuthority("USER"));
			if(rightPriceRepository.getDeligateUserCount(userName) == 0) {
				List<String> authority = rightPriceRepository.getUserRoles(userName);

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
			else if(authority.get(0).contains("RiskManagers"))
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
				AppLoger.APPLOGGER.info(auth);
				grantedAuths.add(new SimpleGrantedAuthority(auth));
			}
			} else {
				
				CheckUserType.userTypeId = 7;	
				
			}
			
			Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, grantedAuths);
			AppLoger.APPLOGGER.info("Roles Available :"+auth.getAuthorities());
			return auth;
		} else {
			throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
		}
	}

	private boolean authorizedUser(String userName, String password) {
		if (null == userName || "".equalsIgnoreCase(userName.trim()) || null == password
				|| "".equalsIgnoreCase(password.trim())) {
			AppLoger.APPLOGGER.info("Empty username or password..!");
			return false;
		}
		if (!isTestingMode) {
			LdapContext ctx = null;
			String result;
			String strSessionId;
			try {
				Hashtable env = new Hashtable();
				env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
				env.put(javax.naming.Context.SECURITY_AUTHENTICATION, "Simple");
				env.put(javax.naming.Context.SECURITY_PRINCIPAL, "SYNTELORG\\"+ userName);
				env.put(javax.naming.Context.SECURITY_CREDENTIALS, password);
				env.put(javax.naming.Context.PROVIDER_URL,"ldap://Vmarcdc04:389");
				ctx = new InitialLdapContext(env, null);
				result = "Login Successful";
				result = "Success";

				AppLoger.APPLOGGER.info("LDAP reply : TRUE");
				AppLoger.APPLOGGER.info("User "+ userName +" authenicated successfully..!");

				return true;

			} catch (NamingException nex) {
				AppLoger.APPLOGGER.info("LDAP reply : FALSE");
				AppLoger.APPLOGGER.info("Exception Caught while LDAP authentication : "+ nex);
				result = "Login Failed";
				AppLoger.APPLOGGER.info("user "+ userName +" Authenication unsuccessful..!");
				return false;
			}
		}
		else{
			AppLoger.APPLOGGER.info("Authenticated in Testing environment");
			AppLoger.APPLOGGER.info("User "+ userName +" authenicated successfully in Test eenv..!");
			return true;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	public static String decrypt(String key, String initVector, String encrypted) {
		AppLoger.APPLOGGER.info("decrypt ENTRY");
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));
            
            AppLoger.APPLOGGER.info("decrypt EXIT");
            return new String(original);
        } catch (Exception ex) {
        	AppLoger.APPLOGGER.info("Exception Caught while Decrypting password : "+ ex);
            ex.printStackTrace();
        }

        AppLoger.APPLOGGER.info("decrypt EXIT");
        return null;
    }
}