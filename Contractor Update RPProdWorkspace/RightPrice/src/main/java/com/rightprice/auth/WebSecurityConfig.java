package com.rightprice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.extensions.saml2.config.SAMLConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private CustomAuthenticationProvider CustomAuthenticationProvider;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//    	String logoutSuccessUrl = "https://wac.das.myatos.net/portal/logout_confirm.jsp";
    	String logoutSuccessUrl = "https://wacstg.das.myatos.net/portal/logout_confirm.jsp";
        /*http
                .authorizeRequests()
                    .antMatchers("/resources/**", "/registration").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/MyDashBoard",false)
                    .permitAll()
                    .and()
                .logout().logoutSuccessUrl(logoutSuccessUrl)
                    .permitAll();*/
    	
    	 http
         
		 .csrf().disable().authorizeRequests()   	
	   	 .antMatchers("/saml/**","/resources/**").permitAll()
	   	 .antMatchers("/saml/SSO").permitAll()
	   	 .anyRequest().authenticated()
	   	 .and()
				.apply(SAMLConfigurer.saml())
					.serviceProvider().
						keyStore()
							/*.storeFilePath("classpath:presales.jks")
							.password("syntel123")
							.keyname("presales")
							.keyPassword("syntel123") */
						.storeFilePath("classpath:RPAkeystore.jks")
						.password("atos123$")
						.keyname("RightPriceAtos")
						.keyPassword("atos123$") 
							.and()
//							----------------------Enable Configuration for Local---------------------------------------
						.protocol("https")
						.hostname("localhost")
						.entityId("rightprice-local.myatos-syntel.net")
//						----------------------Enable Configuration for UAT---------------------------------------
//						.hostname("admin-uat.myatos-syntel.net")
//						.entityId("rightprice-uat.myatos-syntel.net")
//						----------------------Enable Configuration for DEV---------------------------------------
						/*.hostname("admin-dev.myatos-syntel.net")
						.entityId("rightprice-dev.myatos-syntel.net")*/
//						----------------------Enable Configuration for PROD---------------------------------------
						/*.hostname("rightprice.myatos-syntel.net")
						.entityId("rightprice.myatos-syntel.net")*/
						.basePath("/RightPrice-DAS")
						.and()
					.identityProvider()
						.discoveryEnabled(true)
						.metadataFilePath("classpath:metadata/wacstg.das.myatos.net-cond2fa_256.xml"); 
//						.metadataFilePath("classpath:metadata/prd_cond2fa.xml");
			http.
	   	 	logout()
	   	 	.logoutSuccessUrl(logoutSuccessUrl);
    }

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    	auth.authenticationProvider(this.CustomAuthenticationProvider);
    }
}